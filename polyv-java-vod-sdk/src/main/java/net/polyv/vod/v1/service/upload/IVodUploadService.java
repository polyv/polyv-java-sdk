package net.polyv.vod.v1.service.upload;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.upload.VodUploadCoverImageRequest;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageUrlRequest;
import net.polyv.vod.v1.entity.upload.VodUploadHttpVideoListRequest;
import net.polyv.vod.v1.entity.upload.VodUploadPPTRequest;
import net.polyv.vod.v1.entity.upload.VodUploadWatermarkRequest;

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
    
    /**
     * 上传视频水印
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-upload/upload-watermark-by-category/
     * @param vodUploadWatermarkRequest 上传视频水印请求实体
     * @return 上传视频水印返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean uploadWatermark(VodUploadWatermarkRequest vodUploadWatermarkRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 远程批量上传视频
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-upload/grab-upload-multi/
     * @param vodUploadHttpVideoListRequest 远程批量上传视频请求实体
     * @return 远程批量上传视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean uploadHttpVideoList(VodUploadHttpVideoListRequest vodUploadHttpVideoListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 上传PPT文件
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-upload/uploadppt/
     * @param vodUploadPPTRequest 上传PPT文件请求实体
     * @return 上传PPT文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean uploadPPT(VodUploadPPTRequest vodUploadPPTRequest) throws IOException, NoSuchAlgorithmException;
    
}
