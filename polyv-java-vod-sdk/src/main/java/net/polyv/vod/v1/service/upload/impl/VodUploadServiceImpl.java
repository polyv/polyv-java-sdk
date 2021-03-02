package net.polyv.vod.v1.service.upload.impl;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageRequest;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageUrlRequest;
import net.polyv.vod.v1.entity.upload.VodUploadHttpVideoListRequest;
import net.polyv.vod.v1.entity.upload.VodUploadPPTRequest;
import net.polyv.vod.v1.entity.upload.VodUploadWatermarkRequest;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.upload.IVodUploadService;

/**
 * @author: sadboy
 **/
public class VodUploadServiceImpl extends VodBaseService implements IVodUploadService {
    
    /**
     * 上传多个视频的预览图
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-upload/uploadcoverimage/
     * @param vodUploadCoverImageRequest 上传多个视频的预览图请求实体
     * @return 上传多个视频的预览图返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean uploadCoverImage(VodUploadCoverImageRequest vodUploadCoverImageRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPLOAD_COVER_IMAGE_URL);
        Map<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("image",vodUploadCoverImageRequest.getImage());
        super.uploadOneFile(url,vodUploadCoverImageRequest,fileMap,String.class);
        return true;
    }
    
    /**
     * 上传多个视频的预览图URL
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-upload/uploadcoverimageurl/
     * @param vodUploadCoverImageUrlRequest 上传多个视频的预览图URL请求实体
     * @return 上传多个视频的预览图URL返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean uploadCoverImageUrl(VodUploadCoverImageUrlRequest vodUploadCoverImageUrlRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPLOAD_COVER_IMAGE_URL_URL);
        super.postFormBodyReturnOne(url,vodUploadCoverImageUrlRequest,String.class);
        return true;
    }
    
    /**
     * 上传视频水印
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-upload/upload-watermark-by-category/
     * @param vodUploadWatermarkRequest 上传视频水印请求实体
     * @return 上传视频水印返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean uploadWatermark(VodUploadWatermarkRequest vodUploadWatermarkRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPLOAD_WATERMARK_IMAGE_URL);
        Map<String,File> fileMap =new HashMap<String,File>();
        fileMap.put("image",vodUploadWatermarkRequest.getImage());
        this.uploadOneFile(url,vodUploadWatermarkRequest,fileMap,String.class);
        return true;
    }
    
    /**
     * 远程批量上传视频
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-upload/grab-upload-multi/
     * @param vodUploadHttpVideoListRequest 远程批量上传视频请求实体
     * @return 远程批量上传视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean uploadHttpVideoList(VodUploadHttpVideoListRequest vodUploadHttpVideoListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPLOAD_HTTP_VIDEO_LIST_URL);
        super.postFormBodyReturnOne(url,vodUploadHttpVideoListRequest,String.class);
        return true;
    }
    
    /**
     * 上传PPT文件
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-upload/uploadppt/
     * @param vodUploadPPTRequest 上传PPT文件请求实体
     * @return 上传PPT文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean uploadPPT(VodUploadPPTRequest vodUploadPPTRequest) throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPLOAD_PPT_URL);
        super.postFormBodyReturnOne(url,vodUploadPPTRequest,String.class);
        return true;
    }
    
}
