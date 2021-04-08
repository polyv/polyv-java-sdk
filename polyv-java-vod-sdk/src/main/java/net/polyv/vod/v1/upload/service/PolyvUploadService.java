package net.polyv.vod.v1.upload.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.constant.Constant;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.upload.bean.vo.UploadConfigResponse;
import net.polyv.vod.v1.upload.bean.vo.VideoInfo;
import net.polyv.vod.v1.upload.callback.UploadCallBack;
import net.polyv.vod.v1.upload.config.PolyvUploadChunkConfig;
import net.polyv.vod.v1.upload.enumeration.UploadErrorMsg;
import net.polyv.vod.v1.upload.provider.PolyvCredentialProvider;
import net.polyv.vod.v1.upload.rest.VodUploadVideoService;

/**
 * 上传视频服务
 */
@Slf4j
public class PolyvUploadService {
    
    private long partitionSize;
    private String checkpoint;
    private int threadNum;
    
    public PolyvUploadService(long partitionSize, String checkpoint, int threadNum) {
        this.partitionSize = partitionSize;
        this.checkpoint = checkpoint;
        this.threadNum = threadNum;
    }
    
    /**
     * 初始化上传任务，将上传配置写入视频信息中
     * @return
     */
    public boolean initUploadTask(VideoInfo videoInfo) {
        UploadConfigResponse uploadConfigResponse = null;
        try {
            uploadConfigResponse = new VodUploadVideoService().initUploadQueue(videoInfo, 3);
            if (uploadConfigResponse == null) {
                throw new PloyvSdkException(Constant.ERROR_CODE, "初始化UploadTask失败");
            }
        } catch (IOException e) {
            log.info("上传配置失败", e);
            throw new PloyvSdkException(Constant.ERROR_CODE, "上传配置失败");
        } catch (NoSuchAlgorithmException e) {
            log.info("上传配置失败", e);
            throw new PloyvSdkException(Constant.ERROR_CODE, "上传配置失败");
        }
        PolyvUploadChunkConfig uploadConfig = new PolyvUploadChunkConfig(partitionSize, checkpoint, threadNum);
        uploadConfig = uploadConfig.setAliOssArgument(uploadConfigResponse);
        videoInfo.setVideoPoolId(uploadConfigResponse.getVid());
        videoInfo.setCallBack(uploadConfigResponse.getCallback());
        videoInfo.setPolyvUploadChunkConfig(uploadConfig);
        videoInfo.setStartTime(System.currentTimeMillis());
        return true;
    }
    
    /**
     * 构建client上传视频
     * @return
     */
    public boolean startUpload(VideoInfo videoInfo, UploadCallBack eventCallBack, boolean printProcessLog) {
        PolyvUploadChunkConfig uploadConfig = videoInfo.getPolyvUploadChunkConfig();
        String accessKeyId = uploadConfig.getAccessId();
        String accessKeySecret = uploadConfig.getAccessKey();
        String bucketName = uploadConfig.getBucket();
        String securityToken = uploadConfig.getToken();
        String domain = uploadConfig.getDomain();
        int taskNum = uploadConfig.getThreadNum() == 0 ? 5 : uploadConfig.getThreadNum();
        long validityTime = uploadConfig.getValidityTime();
        
        String objectName =
                uploadConfig.getDir() + videoInfo.getVideoPoolId() + "." + getExtension(videoInfo.getFile().getPath());
        String fileLocation = videoInfo.getFile().getPath();
        long partitionSize = uploadConfig.getPartitionSize() == null ? 1024 * 1024 : uploadConfig.getPartitionSize();
        String checkpoint = uploadConfig.getCheckPointDir() + "/" + videoInfo.getVideoPoolId() + ".ucp";
        
        
        OSS ossClient = buildOssClient(domain, accessKeyId, accessKeySecret, securityToken,
                videoInfo.getStartTime() + validityTime * 1000);
        
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("text/plain");
        
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, objectName);
        
        uploadFileRequest.setUploadFile(fileLocation);
        // 指定上传并发线程数，默认为1。
        uploadFileRequest.setTaskNum(taskNum);
        // 指定上传的分片大小，范围为100KB~5GB，默认为1M。
        uploadFileRequest.setPartSize(partitionSize);
        // 开启断点续传，默认关闭。
        uploadFileRequest.setEnableCheckpoint(true);
        // 记录本地分片上传结果的文件。开启断点续传功能时需要设置此参数，上传过程中的进度信息会保存在该文件中，如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。默认与待上传的本地文件同目录，为uploadFile.ucp。
        uploadFileRequest.setCheckpointFile(checkpoint);
        // 文件的元数据。
        uploadFileRequest.setObjectMetadata(meta);
        // 设置上传成功回调，参数为Callback类型。
        
        Callback callback = JSON.parseObject(videoInfo.getCallBack(), Callback.class);
        uploadFileRequest.setCallback(callback);
        uploadFileRequest.setProgressListener(new ProgressListener() {
            private long bytesWritten = 0;
            private long totalBytes = -1;
            private boolean succeed = false;
            private long totalFileSize = videoInfo.getFileSize();
            
            @Override
            public void progressChanged(ProgressEvent progressEvent) {
                long bytes = progressEvent.getBytes();
                ProgressEventType eventType = progressEvent.getEventType();
                String videoPoolId = videoInfo.getVideoPoolId();
                switch (eventType) {
                    case TRANSFER_STARTED_EVENT:
                        eventCallBack.start(videoPoolId);
                        if (!printProcessLog) {
                            break;
                        }
                        log.info("【{}】vid={}, Start to upload......", videoInfo.getTitle(), videoInfo.getVideoPoolId());
                        log.info("【{}】File size is {} bytes", videoPoolId, this.totalFileSize);
                        break;
                    case REQUEST_CONTENT_LENGTH_EVENT:
                        this.totalBytes = bytes;
                        if (!printProcessLog) {
                            break;
                        }
                        log.info("【{}】{} bytes in total will be uploaded to Server", videoPoolId, this.totalBytes);
                        break;
                    case REQUEST_BYTE_TRANSFER_EVENT:
                        this.bytesWritten += bytes;
                        eventCallBack.process(videoPoolId, totalFileSize - totalBytes + this.bytesWritten,
                                this.totalFileSize);
                        if (!printProcessLog) {
                            break;
                        }
                        if (this.totalBytes != -1) {
                            int percent = (int) ((totalFileSize - totalBytes + this.bytesWritten) * 100.0 /
                                    this.totalFileSize);
                            log.info("【{}】{} bytes have been written at this time, upload progress: {}%({}/{})",
                                    videoPoolId, bytes, percent, totalFileSize - totalBytes + this.bytesWritten,
                                    this.totalFileSize);
                        } else {
                            log.info("【{}】{} bytes have been written at this time, upload ratio: unknown({}/...)",
                                    videoPoolId, bytes, totalFileSize - totalBytes + this.bytesWritten);
                        }
                        break;
                    case TRANSFER_COMPLETED_EVENT:
                        this.succeed = true;
                        eventCallBack.complete(videoPoolId);
                        if (!printProcessLog) {
                            break;
                        }
                        log.info("【{}】Succeed to upload, {} bytes have been transferred in total", videoPoolId,
                                totalFileSize - totalBytes + this.bytesWritten);
                        break;
                    case TRANSFER_FAILED_EVENT:
                        eventCallBack.error(videoPoolId, UploadErrorMsg.ERROR_UPLOAD_PART);
                        if (!printProcessLog) {
                            break;
                        }
                        log.info("【{}】Failed to upload, {} bytes have been transferred", videoPoolId,
                                totalFileSize - totalBytes + this.bytesWritten);
                        break;
                    default:
                        break;
                }
            }
        });
        
        // 断点续传上传。
        return triggerUpload(videoInfo.getVideoPoolId(), ossClient, uploadFileRequest, 3, eventCallBack);
    }
    
    /**
     * 触发上传接口
     */
    private boolean triggerUpload(String videoPoolId, OSS ossClient, UploadFileRequest uploadFileRequest, int retry,
            UploadCallBack eventCallBack) {
        try {
            ossClient.uploadFile(uploadFileRequest);
            // 关闭OSSClient。
            ossClient.shutdown();
            eventCallBack.success(videoPoolId);
            return true;
        } catch (OSSException e) {
            //假如是token过期，需要更新token再重传
            if (("InvalidAccessKeyId".equals(e.getErrorCode()) || "SecurityTokenExpired".equals(e.getErrorCode())) &&
                    retry > 0) {
                log.info("token is expired. reupload the video. retry={}, requestId={}", retry, e.getRequestId());
                ossClient = reBuildOssClient();
                return triggerUpload(videoPoolId, ossClient, uploadFileRequest, --retry, eventCallBack);
            }
            log.error(e.getMessage(), e);
            eventCallBack.error(videoPoolId, UploadErrorMsg.ERROR_UPLOAD_TOKEN_EXPIRE);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
            eventCallBack.error(videoPoolId, UploadErrorMsg.ERROR_UPLOAD_EXCEPTION);
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return false;
    }
    
    /**
     * 重新构建client
     * @return
     */
    private OSS reBuildOssClient() {
        //重新请求getToken获取新的上传参数
        UploadConfigResponse uploadConfigResponse = null;
        try {
            uploadConfigResponse = new VodUploadVideoService().getUploadToken(3);
            if (uploadConfigResponse == null) {
                throw new PloyvSdkException(Constant.ERROR_CODE, "重建OssClient失败");
            }
        } catch (IOException e) {
            log.error("获取上传token失败", e);
            throw new PloyvSdkException(Constant.ERROR_CODE, "获取上传token失败");
        } catch (NoSuchAlgorithmException e) {
            log.error("获取上传token失败", e);
            throw new PloyvSdkException(Constant.ERROR_CODE, "获取上传token失败");
        }
        
        String accessKeyId = uploadConfigResponse.getAccessId();
        String accessKeySecret = uploadConfigResponse.getAccessKey();
        String securityToken = uploadConfigResponse.getToken();
        String domain = uploadConfigResponse.getDomain();
        long validityTime = uploadConfigResponse.getValidityTime();
        
        // 创建OSSClient实例。
        return buildOssClient(domain, accessKeyId, accessKeySecret, securityToken,
                System.currentTimeMillis() + validityTime);
    }
    
    private OSS buildOssClient(String endpoint, String accessKeyId, String accessKeySecret, String securityToken,
            long expireTime) {
        
        ClientBuilderConfiguration ossConfig = new ClientBuilderConfiguration();
        ossConfig.setSupportCname(true);
        return new OSSClient(endpoint,
                new PolyvCredentialProvider(accessKeyId, accessKeySecret, securityToken, expireTime), ossConfig);
    }
    
    private String getExtension(String filename) {
        if (filename == null) {
            return "";
        } else {
            int extensionPos = filename.lastIndexOf(46);
            if (extensionPos < 0) {
                return "";
            }
            return filename.substring(extensionPos + 1);
        }
    }
}
