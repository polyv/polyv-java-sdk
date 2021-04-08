package net.polyv.vod.v1.upload.rest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;
import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.upload.bean.vo.UploadConfigRequest;
import net.polyv.vod.v1.upload.bean.vo.UploadConfigResponse;
import net.polyv.vod.v1.upload.bean.vo.VideoInfo;
import net.polyv.vod.v1.upload.bean.vo.VodGetUploadTokenRequest;
import net.polyv.vod.v1.util.VodSignUtil;


/**
 * 上传相关的Rest Api
 */
@Slf4j
public class VodUploadVideoService extends VodBaseService {
    
    private static final String INIT_UPLOAD_TASK_URI = "http://api.polyv.net/v2/uploadvideo/%s/init";
    private static final String GET_TOKEN_URI = "http://api.polyv.net/v2/uploadvideo/%s/token";
    
    /**
     * 初始化上传信息
     * @param videoInfo
     * @param retry 重试次数
     * @return
     */
    public UploadConfigResponse initUploadQueue(VideoInfo videoInfo, int retry)
            throws IOException, NoSuchAlgorithmException {
        UploadConfigRequest uploadConfigRequest = videoInfo.convert();
        uploadConfigRequest.setRequestId(VodSignUtil.generateUUID());
        String url = String.format(INIT_UPLOAD_TASK_URI, VodGlobalConfig.getUserId());
        UploadConfigResponse uploadConfigResponse = super.postFormBodyReturnOne(url, uploadConfigRequest,
                UploadConfigResponse.class);
        if (uploadConfigResponse == null && retry > 0) {
            return initUploadQueue(videoInfo, --retry);
        }
        return uploadConfigResponse;
    }
    
    
    /**
     * 获取上传token
     * @param retry
     * @return
     */
    public UploadConfigResponse getUploadToken(int retry) throws IOException, NoSuchAlgorithmException {
        String url = String.format(GET_TOKEN_URI, VodGlobalConfig.getUserId());
        VodGetUploadTokenRequest vodGetUploadTokenRequest = new VodGetUploadTokenRequest();
        vodGetUploadTokenRequest.setRequestId(VodSignUtil.generateUUID());
        UploadConfigResponse vodGetUploadTokenResponse = super.getReturnOne(url, vodGetUploadTokenRequest,
                UploadConfigResponse.class);
        if (vodGetUploadTokenResponse == null && retry > 0) {
            return getUploadToken(--retry);
        }
        return vodGetUploadTokenResponse;
    }
}

