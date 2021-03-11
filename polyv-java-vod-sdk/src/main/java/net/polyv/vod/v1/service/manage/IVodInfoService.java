package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoFirstImageRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoPlayStatusRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoPreviewDurationRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoSizeRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoSizeResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetWeChatShareVideoInfoRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetWeChatShareVideoInfoResponse;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameResponse;

public interface IVodInfoService {
    
    /**
     * 获取单个视频的打点信息
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/getkeyframe/
     * @param vodListVideoKeyFrameRequest 获取单个视频的打点信息请求实体
     * @return 获取单个视频的打点信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodListVideoKeyFrameResponse listVideoKeyFrame(VodListVideoKeyFrameRequest vodListVideoKeyFrameRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 根据视频vid查询视频的授权播放开关状态
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/authplay-status/
     * @param vodGetVideoPlayStatusRequest 根据视频vid查询视频的授权播放开关状态请求实体
     * @return 根据视频vid查询视频的授权播放开关状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean getVideoPlayStatus(VodGetVideoPlayStatusRequest vodGetVideoPlayStatusRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量获取答题日志
     * URL地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/batch-answer-log/
     * @param vodGetVideoExamLogRequest 批量获取答题日志请求实体
     * @return 批量获取答题日志返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetVideoExamLogResponse getVideoExamLog(VodGetVideoExamLogRequest vodGetVideoExamLogRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 根据分类批量获取视频时长和大小
     * URL地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-duration-by-category/
     * @param vodGetVideoSizeRequest
     * @return 根据分类批量获取视频时长和大小返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodGetVideoSizeResponse> getVideoSize(VodGetVideoSizeRequest vodGetVideoSizeRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取微信分享页的视频相关信息接口
     * URL地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/wechat-video-info/
     * @param vodGetWeChatShareVideoInfoRequest
     * @return 获取微信分享页的视频相关信息接口返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetWeChatShareVideoInfoResponse getWeChatShareVideoInfo(
            VodGetWeChatShareVideoInfoRequest vodGetWeChatShareVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取视频播放预览时长接口
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-preview-duration/
     * @param vodGetVideoPreviewDurationRequest 获取视频播放预览时长接口请求实体
     * @return Integer
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Integer getVideoPreviewDuration(VodGetVideoPreviewDurationRequest vodGetVideoPreviewDurationRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取单个视频信息
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-video-msg/
     * @param vodGetVideoRequest 获取单个视频信息请求实体
     * @return 获取单个视频信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetVideoResponse getVideo(VodGetVideoRequest vodGetVideoRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取单个视频的首图
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-image/
     * @param vodGetVideoFirstImageRequest 获取单个视频的首图请求实体
     * @return String
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String getVideoFirstImage(VodGetVideoFirstImageRequest vodGetVideoFirstImageRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取单个视频的问答题目
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-video-exam/
     * @param vodGetVideoExamRequest 获取单个视频的问答题目请求实体
     * @return List
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    List<VodGetVideoExamResponse> getVideoExam(VodGetVideoExamRequest vodGetVideoExamRequest)throws IOException, NoSuchAlgorithmException;
}
