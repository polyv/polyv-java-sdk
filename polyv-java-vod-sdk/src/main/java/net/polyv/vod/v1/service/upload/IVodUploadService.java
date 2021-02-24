package net.polyv.vod.v1.service.upload;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.upload.VodUploadCoverImageRequest;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageUrlRequest;

/**
 * @author: sadboy
 **/
public interface IVodUploadService {
    
    /**
     * 上传多个视频的预览图
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-upload/uploadcoverimage/
     * @param vodUploadCoverImageRequest 上传多个视频的预览图请求实体
     * @return 上传多个视频的预览图返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean uploadCoverImage(VodUploadCoverImageRequest vodUploadCoverImageRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 上传多个视频的预览图URL
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-upload/uploadcoverimageurl/
     * @param vodUploadCoverImageUrlRequest 上传多个视频的预览图URL请求实体
     * @return 上传多个视频的预览图URL返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean uploadCoverImageUrl(VodUploadCoverImageUrlRequest vodUploadCoverImageUrlRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
