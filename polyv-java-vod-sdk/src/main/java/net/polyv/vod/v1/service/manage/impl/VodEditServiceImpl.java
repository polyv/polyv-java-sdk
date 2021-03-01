package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.edit.VodClipVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodConcatVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodConcatVideoResponse;
import net.polyv.vod.v1.entity.manage.edit.VodSaveVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoPlayStatusRequest;
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
        return true;
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
        return true;
    }
    
}
