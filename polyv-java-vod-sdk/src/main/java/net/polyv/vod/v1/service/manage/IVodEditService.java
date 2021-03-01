package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.edit.VodClipVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodConcatVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodConcatVideoResponse;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSaveVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSetVideoPreviewDurationRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoPlayStatusRequest;

/**
 * @author: sadboy
 **/
public interface IVodEditService {
    
    /**
     * 根据vid批量修改视频的授权播放开关状态
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/set-authplay/
     * @param vodUpdateVideoPlayStatusRequest 根据vid批量修改视频的授权播放开关状态请求实体
     * @return 根据vid批量修改视频的授权播放开关状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateVideoPlayStatus(VodUpdateVideoPlayStatusRequest vodUpdateVideoPlayStatusRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 提交视频裁剪任务
     * URL地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/clip/
     * @param vodClipVideoRequest 提交视频裁剪任务请求实体
     * @return 提交视频裁剪任务返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String clipVideo(VodClipVideoRequest vodClipVideoRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 合并视频
     * URL地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/videoconcat/
     * @param vodConcatVideoRequest 合并视频请求实体
     * @return 合并视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodConcatVideoResponse concatVideo(VodConcatVideoRequest vodConcatVideoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置视频打点
     * URL地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/setkeyframe/
     * @param vodSaveVideoKeyFrameRequest 设置视频打点请求实体
     * @return 设置视频打点返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean saveVideoKeyFrame(VodSaveVideoKeyFrameRequest vodSaveVideoKeyFrameRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除视频指定时间点的打点信息
     * URL地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/deletekeyframebytime/
     * @param vodDeleteVideoKeyFrameRequest 删除视频指定时间点的打点信息请求实体
     * @return 删除视频指定时间点的打点信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteVideoKeyFrame(VodDeleteVideoKeyFrameRequest vodDeleteVideoKeyFrameRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置视频的播放预览时长
     * URL地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/setting-preview-duration/
     * @param vodSetVideoPreviewDurationRequest 设置视频的播放预览时长请求实体
     * @return 设置视频的播放预览时长返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean setVideoPreviewDuration(VodSetVideoPreviewDurationRequest vodSetVideoPreviewDurationRequest)
            throws IOException, NoSuchAlgorithmException;
}
