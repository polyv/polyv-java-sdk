package net.polyv.vod.v1.upload.entry;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.polyv.common.v1.constant.Constant;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.StringUtils;
import net.polyv.vod.v1.upload.bean.vo.VideoInfo;
import net.polyv.vod.v1.upload.callback.UploadCallBack;
import net.polyv.vod.v1.upload.config.PolyvUploadChunkConfig;
import net.polyv.vod.v1.upload.enumeration.UploadErrorMsg;
import net.polyv.vod.v1.upload.service.PolyvUploadService;


/**
 * 常规视频上传的入口
 * 可以配置一些高级设置，比如分片大小，回调信息等
 */
public class PolyvUploadClient extends PolyvBasicClient {
    
    protected PolyvUploadChunkConfig polyvUploadChunkConfig; // 分片上传的配置
    
    protected PolyvUploadService polyvUploadService;
    private static final Logger logger = LoggerFactory.getLogger(PolyvUploadClient.class);
    
    /**
     * 初始化入口client
     * @param partitionSize 分片大小
     * @param checkpoint checkpoint文件夹
     * @param threadNum 处理线程数
     */
    public PolyvUploadClient(int partitionSize, String checkpoint, int threadNum) {
        this.polyvUploadChunkConfig = new PolyvUploadChunkConfig(partitionSize, checkpoint, threadNum);
        this.polyvUploadService = new PolyvUploadService(partitionSize, checkpoint, threadNum);
    }
    
    /**
     * 以断点续传的方式上传视频
     * @param videoInfo
     * @return
     */
    public String uploadVideoParts(VideoInfo videoInfo, UploadCallBack callBack, boolean printProcessLog) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isBlank(polyvUploadChunkConfig.getCheckPointDir())) {
            logger.error("checkpoint dir is blank");
            throw new PloyvSdkException(Constant.ERROR_CODE, "checkpoint dir is blank");
        }
        File check = new File(polyvUploadChunkConfig.getCheckPointDir());
        if (!check.exists() && !check.mkdirs()) {
            logger.error("checkpoint dir created failed. please check check the permissions");
            throw new PloyvSdkException(Constant.ERROR_CODE, "checkpoint dir created failed. please check check the permissions");
        }
    
        File file = videoInfo.getFile();
        if (file == null || !file.exists()) {
            logger.error("file is null or not exist");
            throw new PloyvSdkException(Constant.ERROR_CODE, "file is null or not exist");
        }
        
        if (videoInfo.getFileSize() == null || videoInfo.getFileSize() == 0) {
            videoInfo.setFileSize(file.length());
        }
        
        if (StringUtils.isBlank(videoInfo.getTitle())) {
            videoInfo.setTitle(file.getName());
        }
        if (videoInfo.getCataId() == null || "0".equals(videoInfo.getCataId())) {
            videoInfo.setCataId("1");
        }
        
        //初始化任务
        if (polyvUploadService.initUploadTask(videoInfo)) {
            //开始上传
            if (polyvUploadService.startUpload(videoInfo, callBack, printProcessLog)) {
                logger.info("upload success. cost {} ms", System.currentTimeMillis() - startTime);
            } else {
                logger.error("upload failed. cost {} ms", System.currentTimeMillis() - startTime);
            }
            return videoInfo.getVideoPoolId();
        } else {
            callBack.error("unknown_vid", UploadErrorMsg.ERROR_INIT);
        }
        logger.error("upload failed.");
        return null;
    }
    
}
