package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodConstant;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.edit.VodClipVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodConcatVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodConcatVideoResponse;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoAllKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoListRequest;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSaveVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSetVideoForbiddenRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSetVideoPreviewDurationRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoHlsLevelListRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoInfoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoInfoResponse;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoPlayStatusRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoSettingRequest;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodEditService;

/**
 * @author: sadboy
 **/
public class VodEditServiceImpl extends VodBaseService implements IVodEditService {
    
    /**
     * 根据vid批量修改视频的授权播放开关状态
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/set-authplay/
     * @param vodUpdateVideoPlayStatusRequest 根据vid批量修改视频的授权播放开关状态请求实体
     * @return 根据vid批量修改视频的授权播放开关状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateVideoPlayStatus(VodUpdateVideoPlayStatusRequest vodUpdateVideoPlayStatusRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPDATE_VIDEO_PLAY_STATUS_URL);
        super.postFormBodyReturnOne(url, vodUpdateVideoPlayStatusRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 提交视频裁剪任务
     * URL地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/clip/
     * @param vodClipVideoRequest 提交视频裁剪任务请求实体
     * @return 提交视频裁剪任务返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String clipVideo(VodClipVideoRequest vodClipVideoRequest) throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.CLIP_VIDEO_URL);
        return super.postFormBodyReturnOne(url, vodClipVideoRequest, String.class);
    }
    
    /**
     * 合并视频
     * URL地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/videoconcat/
     * @param vodConcatVideoRequest 合并视频请求实体
     * @return 合并视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodConcatVideoResponse concatVideo(VodConcatVideoRequest vodConcatVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.CONCAT_VIDEO_URL);
        return super.postFormBodyReturnOne(url, vodConcatVideoRequest, VodConcatVideoResponse.class);
    }
    
    /**
     * 设置视频打点
     * URL地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/setkeyframe/
     * @param vodSaveVideoKeyFrameRequest 设置视频打点请求实体
     * @return 设置视频打点返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean saveVideoKeyFrame(VodSaveVideoKeyFrameRequest vodSaveVideoKeyFrameRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.SAVE_KEY_FRAME_URL);
        super.postFormBodyReturnOne(url, vodSaveVideoKeyFrameRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 删除视频指定时间点的打点信息
     * URL地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/deletekeyframebytime/
     * @param vodDeleteVideoKeyFrameRequest 删除视频指定时间点的打点信息请求实体
     * @return 删除视频指定时间点的打点信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteVideoKeyFrame(VodDeleteVideoKeyFrameRequest vodDeleteVideoKeyFrameRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.DELETE_KEY_FRAME_URL);
        super.postFormBodyReturnOne(url, vodDeleteVideoKeyFrameRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 设置视频的播放预览时长
     * URL地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/setting-preview-duration/
     * @param vodSetVideoPreviewDurationRequest 设置视频的播放预览时长请求实体
     * @return 设置视频的播放预览时长返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setVideoPreviewDuration(VodSetVideoPreviewDurationRequest vodSetVideoPreviewDurationRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.SET_PREVIEW_DURATION_URL);
        super.postFormBodyReturnOne(url, vodSetVideoPreviewDurationRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 视频禁播与解禁
     * URL地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/set-forbidden-status/
     * @param vodSetVideoForbiddenRequest 视频禁播与解禁请求实体
     * @return 视频禁播与解禁返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setVideoForbidden(VodSetVideoForbiddenRequest vodSetVideoForbiddenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.SET_VIDEO_FORBIDDEN_URL);
        super.postFormBodyReturnOne(url, vodSetVideoForbiddenRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 批量删除视频
     * URl地址：https://dev.polyv.net/2021/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/del-videos/
     * @param vodDeleteVideoListRequest 批量删除视频请求实体
     * @return 批量删除视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteVideoList(VodDeleteVideoListRequest vodDeleteVideoListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.DELETE_VIDEO_LIST_URL;
        vodDeleteVideoListRequest.setUserId(VodGlobalConfig.getUserId());
        super.postFormBodyReturnOne(url, vodDeleteVideoListRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 编辑单个视频的信息
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/video-info/
     * @param vodUpdateVideoInfoRequest 编辑单个视频的信息请求实体
     * @return 编辑单个视频的信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodUpdateVideoInfoResponse updateVideoInfo(VodUpdateVideoInfoRequest vodUpdateVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPDATE_VIDEO_INFO_URL);
        List<VodUpdateVideoInfoResponse> vodUpdateVideoInfoResponses = super.postFormBodyReturnList(url,
                vodUpdateVideoInfoRequest, VodUpdateVideoInfoResponse.class);
        if(vodUpdateVideoInfoResponses.isEmpty()){
            throw new PloyvSdkException(Constant.ERROR_CODE, "编辑单个视频的信息失败");
        }
        return vodUpdateVideoInfoResponses.get(0);
    }
    
    /**
     * 删除视频
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/del-video/
     * @param vodDeleteVideoRequest 删除视频请求实体
     * @return 删除视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteVideo(VodDeleteVideoRequest vodDeleteVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.DELETE_VIDEO_URL);
        super.postFormBodyReturnOne(url, vodDeleteVideoRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 修改视频密码
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/video-setting-save/
     * @param vodUpdateVideoSettingRequest 修改视频密码请求实体
     * @return 修改视频密码返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateVideoSetting(VodUpdateVideoSettingRequest vodUpdateVideoSettingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPDATE_VIDEO_SETTING);
        super.postFormBodyReturnOne(url,vodUpdateVideoSettingRequest,String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 删除视频的全部打点信息
     * URL地址：https://dev.polyv.net/2016/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/deletekeyframe/
     * @param vodDeleteVideoAllKeyFrameRequest 删除视频的全部打点信息请求实体
     * @return 删除视频的全部打点信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteVideoAllKeyFrame(VodDeleteVideoAllKeyFrameRequest vodDeleteVideoAllKeyFrameRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.DELETE_VIDEO_ALL_KEY_FRAME);
        super.postFormBodyReturnOne(url,vodDeleteVideoAllKeyFrameRequest,String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 批量修改视频的授权方式
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/hlslevel/
     * @param vodUpdateVideoHlsLevelListRequest 批量修改视频的授权方式请求实体
     * @return 批量修改视频的授权方式返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateVideoHlsLevelList(VodUpdateVideoHlsLevelListRequest vodUpdateVideoHlsLevelListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPDATE_VIDEO_HLS_LEVEL_URL);
        super.postFormBodyReturnOne(url,vodUpdateVideoHlsLevelListRequest,String.class);
        return Boolean.TRUE;
    }
    
}
