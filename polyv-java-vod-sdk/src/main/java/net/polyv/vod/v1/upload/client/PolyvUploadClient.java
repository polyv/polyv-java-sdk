package net.polyv.vod.v1.upload.client;

import java.io.File;
import java.util.List;

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
import net.polyv.common.v1.entity.CommonReqeust;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.SDKValidateUtil;
import net.polyv.common.v1.util.StringUtils;
import net.polyv.common.v1.validator.ViolationMsg;
import net.polyv.vod.v1.entity.upload.VodUploadVideoPartsRequest;
import net.polyv.vod.v1.entity.upload.VodUploadVideoRequest;
import net.polyv.vod.v1.entity.upload.vo.VodUploadVideoConfigRequest;
import net.polyv.vod.v1.entity.upload.vo.VodUploadVideoConfigResponse;
import net.polyv.vod.v1.upload.callback.UploadCallBack;
import net.polyv.vod.v1.upload.enumeration.UploadErrorMsg;
import net.polyv.vod.v1.upload.provide.PolyvCredentialProvider;
import net.polyv.vod.v1.upload.service.VodUploadVideoService;

/**
 * 保利威分片上传客户端
 * @author: sadboy
 **/
@Slf4j
public class PolyvUploadClient {
    
    /**
     * 上传分片大小
     */
    private int partitionSize = 1024 * 1024;
    
    /**
     * 分片上传进度文件存放位置
     */
    private String checkpoint = "checkpoint_location";
    
    /**
     * 上传线程数，根据服务器实际情况设置
     */
    private int threadNum = 5;
    
    private VodUploadVideoConfigResponse vodUploadVideoConfigResponse;
    
    /**
     * 使用默认参数构建上传客户端
     */
    public PolyvUploadClient() {
        checkArgument();
    }
    
    /**
     * 通过参数构建上传客户端
     * @param partitionSize 上传分片大小，范围100KB~5GB，100*1024代表100KB
     * @param checkpoint 分片上传进度文件存放位置（需要创建文件夹和文件权限），默认：checkpoint_location
     * @param threadNum 上传线程数，根据服务器实际情况设置，默认为5个
     */
    public PolyvUploadClient(int partitionSize, String checkpoint, int threadNum) {
        this.partitionSize = partitionSize;
        this.checkpoint = checkpoint;
        this.threadNum = threadNum;
        checkArgument();
    }
    
    /**
     * 分片上传视频，并返回视频id
     * @param commonRequest VodUploadVideoRequest或VodUploadVideoPartsRequest对象
     * @param callBack 上传进度和成功失败回调
     * @param printProcessLog 是否打印上传日志，true为打印，false为不打印
     * @return
     */
    public String uploadVideo(CommonReqeust commonRequest, UploadCallBack callBack, boolean printProcessLog){
        VodUploadVideoConfigRequest vodUploadVideoConfigRequest = null;
        if(commonRequest instanceof VodUploadVideoRequest){
            VodUploadVideoRequest vodUploadVideoRequest = (VodUploadVideoRequest) commonRequest;
            validateBean(vodUploadVideoRequest);
            vodUploadVideoConfigRequest = new VodUploadVideoConfigRequest(vodUploadVideoRequest);
        }else if(commonRequest instanceof VodUploadVideoPartsRequest){
            VodUploadVideoPartsRequest vodUploadVideoPartsRequest = (VodUploadVideoPartsRequest) commonRequest;
            validateBean(vodUploadVideoPartsRequest);
            vodUploadVideoConfigRequest = new VodUploadVideoConfigRequest(vodUploadVideoPartsRequest);
        }else{
            throw new PloyvSdkException(Constant.ERROR_CODE,"点播上传视频对象异常");
        }
        return uploadVideoParts(vodUploadVideoConfigRequest,callBack,printProcessLog);
    }
    
    /**
     * 以断点续传方式上传视频
     * @param vodUploadVideoConfigRequest 分片上传请求实体
     * @param callBack 上传回调
     * @param printProcessLog 是否打印上传日志
     * @return
     */
    private String uploadVideoParts(VodUploadVideoConfigRequest vodUploadVideoConfigRequest, UploadCallBack callBack, boolean printProcessLog) {
        long startTime = System.currentTimeMillis();
        vodUploadVideoConfigResponse = new VodUploadVideoService().initUploadQueue(
                vodUploadVideoConfigRequest, 3);
        boolean upload = startUpload(vodUploadVideoConfigRequest, callBack, printProcessLog);
        if(upload){
            log.info("upload success. cost {} ms", System.currentTimeMillis() - startTime);
        }else {
            log.error("upload failed. cost {} ms", System.currentTimeMillis() - startTime);
        }
        return vodUploadVideoConfigResponse.getVid();
    }
    
    public boolean startUpload(VodUploadVideoConfigRequest videoInfo, UploadCallBack eventCallBack, boolean printProcessLog) {
        String accessKeyId = vodUploadVideoConfigResponse.getAccessId();
        String accessKeySecret = vodUploadVideoConfigResponse.getAccessKey();
        String bucketName = vodUploadVideoConfigResponse.getBucketName();
        String securityToken = vodUploadVideoConfigResponse.getToken();
        String domain = vodUploadVideoConfigResponse.getDomain();
        int taskNum = threadNum;
        long validityTime = vodUploadVideoConfigResponse.getValidityTime();
    
        final String vId = vodUploadVideoConfigResponse.getVid();
        String objectName =
                vodUploadVideoConfigResponse.getDir() +
                        vId + "." + getExtension(videoInfo.getFile().getPath());
        String fileLocation = videoInfo.getFile().getPath();
        String checkpointFile = checkpoint + "/" + vId + ".ucp";
        
        
        OSS ossClient = buildOssClient(domain, accessKeyId, accessKeySecret, securityToken,
                vodUploadVideoConfigResponse.getStartTime() + validityTime * 1000);
        
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
        uploadFileRequest.setCheckpointFile(checkpointFile);
        // 文件的元数据。
        uploadFileRequest.setObjectMetadata(meta);
        // 设置上传成功回调，参数为Callback类型。
        
        Callback callback = JSON.parseObject(vodUploadVideoConfigResponse.getCallback(), Callback.class);
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
                String videoPoolId = vId;
                switch (eventType) {
                    case TRANSFER_STARTED_EVENT:
                        eventCallBack.start(videoPoolId);
                        if (!printProcessLog) {
                            break;
                        }
                        log.info("【{}】vid={}, Start to upload......", videoInfo.getTitle(), videoPoolId);
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
        return triggerUpload(vId, ossClient, uploadFileRequest, 3, eventCallBack);
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
     * 验证参数必填等是否通过
     * @param e
     * @param <E>
     */
    private <E extends CommonReqeust> void validateBean(E e) {
        List<ViolationMsg> violationMsgList = SDKValidateUtil.validateBean(e);
        if (!violationMsgList.isEmpty()) {
            String errors = SDKValidateUtil.getViolationMsgStr(violationMsgList);
            errors = errors.substring(0, errors.length() - 3);
            errors = "输入参数 [" + e.getClass().getName() + "]对象校验失败 ,失败字段 [" + errors + "]";
            throw new PloyvSdkException(Constant.ERROR_CODE, errors);
        }
    }
    
    /**
     * 重新构建client
     * @return
     */
    private OSS reBuildOssClient() {
        //重新请求getToken获取新的上传参数
        VodUploadVideoConfigResponse vodUploadVideoConfigResponse = null;
        vodUploadVideoConfigResponse = new VodUploadVideoService().getUploadToken(3);
        
        String accessKeyId = vodUploadVideoConfigResponse.getAccessId();
        String accessKeySecret = vodUploadVideoConfigResponse.getAccessKey();
        String securityToken = vodUploadVideoConfigResponse.getToken();
        String domain = vodUploadVideoConfigResponse.getDomain();
        long validityTime = vodUploadVideoConfigResponse.getValidityTime();
        
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
    
    /**
     * 根据文件路径获取文件后缀
     * @param filename 文件路径，如：C:\demo\text.mp4
     * @return 文件后缀，如：mp4
     */
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
    
    /**
     * 验证参数及创建分片上传进度存放文件夹
     */
    private void checkArgument(){
        if(StringUtils.isBlank(checkpoint)){
            throw new PloyvSdkException(Constant.ERROR_CODE,"PolyvUploadClient中checkpoint参数不能为空");
        }
        File checkpointFile = new File(checkpoint);
        if (!checkpointFile.exists() && !checkpointFile.mkdirs()) {
            throw new PloyvSdkException(Constant.ERROR_CODE, "PolyvUploadClient中checkpoint路径创建失败，请检查权限。");
        }
        if(threadNum <= 0){
            threadNum = 5;
        }
        if(partitionSize < 100 * 1024 || partitionSize > 5*1024*1024*1024){
            partitionSize = 100 * 1024;
        }
    }
    
}
