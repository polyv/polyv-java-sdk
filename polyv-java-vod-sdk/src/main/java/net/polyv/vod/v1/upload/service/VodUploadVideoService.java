package net.polyv.vod.v1.upload.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.constant.Constant;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.entity.VodCommonRequest;
import net.polyv.vod.v1.entity.upload.vo.VodUploadVideoConfigRequest;
import net.polyv.vod.v1.entity.upload.vo.VodUploadVideoConfigResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.util.VodSignUtil;


/**
 * 上传相关的Rest Api
 */
@Slf4j
public class VodUploadVideoService extends VodBaseService {
    
    private static final String INIT_UPLOAD_TASK_URI = "http://api.polyv.net/v2/uploadvideo/%s/init";
    private static final String GET_TOKEN_URI = "http://api.polyv.net/v2/uploadvideo/%s/token";
    
    /**
     * 初始化上传信息，不可能为空
     * @param uploadConfigRequest
     * @param retry 重试次数
     * @return
     */
    public VodUploadVideoConfigResponse initUploadQueue(VodUploadVideoConfigRequest uploadConfigRequest, int retry) {
        VodUploadVideoConfigResponse vodUploadVideoConfigResponse = null;
        try {
            vodUploadVideoConfigResponse = super.postFormBodyReturnOne(getFormatterUrl(INIT_UPLOAD_TASK_URI),
                    uploadConfigRequest, VodUploadVideoConfigResponse.class);
        } catch (IOException e) {
            log.info("上传视频基础信息失败", e);
            throw new PloyvSdkException(Constant.ERROR_CODE, "上传视频基础信息失败");
        } catch (NoSuchAlgorithmException e) {
            log.info("上传视频基础信息失败", e);
            throw new PloyvSdkException(Constant.ERROR_CODE, "上传视频基础信息失败");
        }
        if (vodUploadVideoConfigResponse == null) {
            if(retry > 0){
                return initUploadQueue(uploadConfigRequest, --retry);
            }else{
                throw new PloyvSdkException(Constant.ERROR_CODE, "上传视频基础信息失败");
            }
        }
        vodUploadVideoConfigResponse.setStartTime(System.currentTimeMillis());
        return vodUploadVideoConfigResponse;
    }
    
    
    /**
     * 获取上传token，不可能为空
     * @param retry 重试次数
     * @return
     */
    public VodUploadVideoConfigResponse getUploadToken(int retry) {
        VodUploadVideoConfigResponse vodUploadVideoConfigResponse = null;
        try {
            VodCommonRequest vodCommonRequest = new VodCommonRequest();
            vodCommonRequest.setRequestId(VodSignUtil.generateUUID());
            vodUploadVideoConfigResponse = super.getReturnOne(getFormatterUrl(GET_TOKEN_URI), vodCommonRequest,
                    VodUploadVideoConfigResponse.class);
        } catch (IOException e) {
            log.info("重新获取token失败", e);
            throw new PloyvSdkException(Constant.ERROR_CODE, "重新获取token失败");
        } catch (NoSuchAlgorithmException e) {
            log.info("重新获取token失败", e);
            throw new PloyvSdkException(Constant.ERROR_CODE, "重新获取token失败");
        }
        if (vodUploadVideoConfigResponse == null) {
            if(retry > 0){
                return getUploadToken(--retry);
            }else{
                throw new PloyvSdkException(Constant.ERROR_CODE, "重新获取token失败");
            }
        }
        return vodUploadVideoConfigResponse;
    }
    
    private String getFormatterUrl(String url){
        return String.format(url, VodGlobalConfig.getUserId());
    }
}

