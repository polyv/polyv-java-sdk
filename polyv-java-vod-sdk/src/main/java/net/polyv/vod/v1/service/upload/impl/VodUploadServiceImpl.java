package net.polyv.vod.v1.service.upload.impl;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageRequest;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageUrlRequest;
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
    
}
