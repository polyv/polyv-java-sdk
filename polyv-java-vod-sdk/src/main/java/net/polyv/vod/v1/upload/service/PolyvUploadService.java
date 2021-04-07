package net.polyv.vod.v1.upload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;

import net.polyv.vod.v1.upload.bean.vo.UploadConfigResponseData;
import net.polyv.vod.v1.upload.bean.vo.VideoInfo;
import net.polyv.vod.v1.upload.callback.UploadCallBack;
import net.polyv.vod.v1.upload.config.PolyvUploadChunkConfig;
import net.polyv.vod.v1.upload.config.PolyvUserConfig;
import net.polyv.vod.v1.upload.enumeration.UploadErrorMsg;
import net.polyv.vod.v1.upload.provider.PolyvCredentialProvider;
import net.polyv.vod.v1.upload.rest.UploadVideoRestApi;
import net.polyv.vod.v1.upload.utils.JsonUtil;
import sun.net.ProgressEvent;
import sun.net.ProgressListener;

/**
 * 上传视频服务
 */
public class PolyvUploadService {
    
    private static final Logger logger = LoggerFactory.getLogger(PolyvUploadService.class);
    
    private PolyvUserConfig userConfig;
    private long partitionSize;
    private String checkpoint;
    private int threadNum;
    
    public PolyvUploadService(PolyvUserConfig userConfig, long partitionSize, String checkpoint, int threadNum) {
        this.userConfig = userConfig;
        this.partitionSize = partitionSize;
        this.checkpoint = checkpoint;
        this.threadNum = threadNum;
    }
    
    /**
     * 初始化上传任务，将上传配置写入视频信息中
     * @return
     */
    public boolean initUploadTask(VideoInfo videoInfo) {
        UploadConfigResponseData result = UploadVideoRestApi.initUploadQueue(videoInfo, userConfig, 3);
        if (result == null) {
            return false;
        }
        PolyvUploadChunkConfig uploadConfig = new PolyvUploadChunkConfig(partitionSize, checkpoint, threadNum);
        uploadConfig.setAccessId(result.getAccessId());
        uploadConfig.setAccessKey(result.getAccessKey());
        uploadConfig.setBucket(result.getBucketName());
        uploadConfig.setEndpoint(result.getEndpoint());
        uploadConfig.setToken(result.getToken());
        uploadConfig.setExpiration(result.getExpiration());
        uploadConfig.setDir(result.getDir());
        uploadConfig.setDomain(result.getDomain());
        uploadConfig.setValidityTime(result.getValidityTime());
        videoInfo.setVideoPoolId(result.getVid());
        videoInfo.setCallBack(result.getCallback());
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
                uploadConfig.getDir() + videoInfo.getVideoPoolId() + "." + FilenameUtils.getExtension(videoInfo.getFileLocation());
        String fileLocation = videoInfo.getFileLocation();
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
        Callback callback = JsonUtil.stringToBean(videoInfo.getCallBack(), Callback.class);
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
                        if(!printProcessLog){
                            break;
                        }
                        logger.info("【{}】vid={}, Start to upload......", videoInfo.getTitle(),
                                videoInfo.getVideoPoolId());
                        logger.info("【{}】File size is {} bytes", videoPoolId, this.totalFileSize);
                        break;
                    case REQUEST_CONTENT_LENGTH_EVENT:
                        this.totalBytes = bytes;
                        if(!printProcessLog){
                            break;
                        }
                        logger.info("【{}】{} bytes in total will be uploaded to Server", videoPoolId, this.totalBytes);
                        break;
                    case REQUEST_BYTE_TRANSFER_EVENT:
                        this.bytesWritten += bytes;
                        eventCallBack.process(videoPoolId, totalFileSize - totalBytes + this.bytesWritten,
                                this.totalFileSize);
                        if(!printProcessLog){
                            break;
                        }
                        if (this.totalBytes != -1) {
                            int percent =
                                    (int) ((totalFileSize - totalBytes + this.bytesWritten) * 100.0 / this.totalFileSize);
                            logger.info("【{}】{} bytes have been written at this time, upload progress: {}%({}/{})",
                                    videoPoolId, bytes, percent, totalFileSize - totalBytes + this.bytesWritten,
                                    this.totalFileSize);
                        } else {
                            logger.info("【{}】{} bytes have been written at this time, upload ratio: unknown({}/...)",
                                    videoPoolId, bytes, totalFileSize - totalBytes + this.bytesWritten);
                        }
                        break;
                    case TRANSFER_COMPLETED_EVENT:
                        this.succeed = true;
                        eventCallBack.complete(videoPoolId);
                        if(!printProcessLog){
                            break;
                        }
                        logger.info("【{}】Succeed to upload, {} bytes have been transferred in total", videoPoolId,
                                totalFileSize - totalBytes + this.bytesWritten);
                        break;
                    case TRANSFER_FAILED_EVENT:
                        eventCallBack.error(videoPoolId, UploadErrorMsg.ERROR_UPLOAD_PART);
                        if(!printProcessLog){
                            break;
                        }
                        logger.info("【{}】Failed to upload, {} bytes have been transferred", videoPoolId,
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
            if (("InvalidAccessKeyId".equals(e.getErrorCode()) || "SecurityTokenExpired".equals(e.getErrorCode())) && retry > 0) {
                logger.info("token is expired. reupload the video. retry={}, requestId={}", retry, e.getRequestId());
                ossClient = reBuildOssClient();
                return triggerUpload(videoPoolId, ossClient, uploadFileRequest, --retry, eventCallBack);
            }
            logger.error(e.getMessage(), e);
            eventCallBack.error(videoPoolId, UploadErrorMsg.ERROR_UPLOAD_TOKEN_EXPIRE);
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
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
        long currentTime = System.currentTimeMillis();
        UploadConfigResponseData result = UploadVideoRestApi.getUploadToken(userConfig, 3);
        if (result == null) {
            return null;
        }
        
        String accessKeyId = result.getAccessId();
        String accessKeySecret = result.getAccessKey();
        String securityToken = result.getToken();
        String domain = result.getDomain();
        long validityTime = result.getValidityTime();
        
        // 创建OSSClient实例。
        return buildOssClient(domain, accessKeyId, accessKeySecret, securityToken, currentTime + validityTime);
    }
    
    private OSS buildOssClient(String endpoint, String accessKeyId, String accessKeySecret, String securityToken,
                               long expireTime) {
        
        ClientBuilderConfiguration ossConfig = new ClientBuilderConfiguration();
        ossConfig.setSupportCname(true);
        return new OSSClient(endpoint, new PolyvCredentialProvider(accessKeyId, accessKeySecret, securityToken,
                expireTime, userConfig), ossConfig);
    }
}
