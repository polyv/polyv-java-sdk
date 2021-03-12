package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
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
import net.polyv.vod.v1.entity.manage.info.VodGetVideosPlayTimesRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideosPlayTimesResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideosSizeRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideosSizeResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetWeChatShareVideoInfoRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetWeChatShareVideoInfoResponse;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameResponse;
import net.polyv.vod.v1.entity.manage.info.VodQueryVideoPasswordRequest;
import net.polyv.vod.v1.entity.manage.info.VodQueryVideoPasswordResponse;
import net.polyv.vod.v1.entity.manage.info.VodQueryVideoPasswordVO;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodInfoService;

/**
 * @author: sadboy
 **/
public class VodInfoServiceImpl extends VodBaseService implements IVodInfoService {
    
    /**
     * 获取单个视频的打点信息
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/getkeyframe/
     * @param vodListVideoKeyFrameRequest 获取单个视频的打点信息请求实体
     * @return 获取单个视频的打点信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodListVideoKeyFrameResponse listVideoKeyFrame(VodListVideoKeyFrameRequest vodListVideoKeyFrameRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.LIST_VIDEO_KEY_FRAME_URL, VodGlobalConfig.getUserId(),
                vodListVideoKeyFrameRequest.getVideoId());
        return super.getReturnOne(url, vodListVideoKeyFrameRequest, VodListVideoKeyFrameResponse.class);
    }
    
    /**
     * 根据视频vid查询视频的授权播放开关状态
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/authplay-status/
     * @param vodGetVideoPlayStatusRequest 根据视频vid查询视频的授权播放开关状态请求实体
     * @return 根据视频vid查询视频的授权播放开关状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean getVideoPlayStatus(VodGetVideoPlayStatusRequest vodGetVideoPlayStatusRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_VIDEO_PLAY_STATUS_URL);
        return "1".equals(super.getReturnOne(url, vodGetVideoPlayStatusRequest, String.class));
    }
    
    /**
     * 批量获取答题日志
     * URL地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/batch-answer-log/
     * @param vodGetVideoExamLogRequest 批量获取答题日志请求实体
     * @return 批量获取答题日志返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetVideoExamLogResponse getVideoExamLog(VodGetVideoExamLogRequest vodGetVideoExamLogRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_VIDEO_EXAM_LOG_URL);
        VodGetVideoExamLogResponse vodGetVideoExamLogResponse = new VodGetVideoExamLogResponse();
        List<VodGetVideoExamLogResponse.ExamLog> returnList = super.getReturnList(url, vodGetVideoExamLogRequest,
                VodGetVideoExamLogResponse.ExamLog.class);
        vodGetVideoExamLogResponse.setContents(returnList);
        return vodGetVideoExamLogResponse;
    }
    
    /**
     * 根据分类批量获取视频时长和大小
     * URL地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-duration-by-category/
     * @param vodGetVideoSizeRequest
     * @return 根据分类批量获取视频时长和大小返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetVideoSizeResponse> getVideoSize(VodGetVideoSizeRequest vodGetVideoSizeRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_VIDEO_SIZE_URL);
        return super.getReturnList(url, vodGetVideoSizeRequest, VodGetVideoSizeResponse.class);
    }
    
    /**
     * 获取微信分享页的视频相关信息接口
     * URL地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/wechat-video-info/
     * @param vodGetWeChatShareVideoInfoRequest
     * @return 获取微信分享页的视频相关信息接口返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetWeChatShareVideoInfoResponse getWeChatShareVideoInfo(
            VodGetWeChatShareVideoInfoRequest vodGetWeChatShareVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_WECHAT_SHARE_VIDEO_INFO_URL);
        return super.getReturnOne(url, vodGetWeChatShareVideoInfoRequest, VodGetWeChatShareVideoInfoResponse.class);
    }
    
    /**
     * 获取视频播放预览时长接口
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-preview-duration/
     * @param vodGetVideoPreviewDurationRequest 获取视频播放预览时长接口请求实体
     * @return Integer
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Integer getVideoPreviewDuration(VodGetVideoPreviewDurationRequest vodGetVideoPreviewDurationRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_VIDEO_PREVIEW_DURATION_URL);
        return super.getReturnOne(url, vodGetVideoPreviewDurationRequest, Integer.class);
    }
    
    /**
     * 获取单个视频信息
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-video-msg/
     * @param vodGetVideoRequest 获取单个视频信息请求实体
     * @return 获取单个视频信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetVideoResponse getVideo(VodGetVideoRequest vodGetVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_VIDEO_URL);
        List<VodGetVideoResponse> returnList = super.getReturnList(url, vodGetVideoRequest, VodGetVideoResponse.class);
        if (returnList.isEmpty()) {
            return null;
        }
        return returnList.get(0);
    }
    
    /**
     * 获取单个视频的首图
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-image/
     * @param vodGetVideoFirstImageRequest 获取单个视频的首图请求实体
     * @return String
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String getVideoFirstImage(VodGetVideoFirstImageRequest vodGetVideoFirstImageRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_VIDEO_FIRST_IMAGE_URL);
        return super.getReturnOne(url, vodGetVideoFirstImageRequest, String.class);
    }
    
    /**
     * 获取单个视频的问答题目
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/get-video-exam/
     * @param vodGetVideoExamRequest 获取单个视频的问答题目请求实体
     * @return List
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public List<VodGetVideoExamResponse> getVideoExam(VodGetVideoExamRequest vodGetVideoExamRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_VIDEO_EXAM_URL);
        return super.getReturnList(url, vodGetVideoExamRequest, VodGetVideoExamResponse.class);
    }
    
    /**
     * 查询视频密码
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/video-setting-page/
     * @param vodQueryVideoPasswordRequest 查询视频密码请求实体
     * @return 查询视频密码返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodQueryVideoPasswordResponse queryVideoPassword(VodQueryVideoPasswordRequest vodQueryVideoPasswordRequest)
            throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPasswordResponse vodQueryVideoPasswordResponse = new VodQueryVideoPasswordResponse();
        String url = VodURL.getRealUrl(VodURL.QUERY_VIDEO_PASSWORD_URL);
        VodQueryVideoPasswordVO vodQueryVideoPasswordVO = super.getReturnOne(url, vodQueryVideoPasswordRequest,
                VodQueryVideoPasswordVO.class);
        if (vodQueryVideoPasswordVO == null) {
            return null;
        }
        if (vodQueryVideoPasswordVO.getIsShowPassword() != null) {
            vodQueryVideoPasswordResponse.setIsShowPassword(vodQueryVideoPasswordVO.getIsShowPassword());
        }
        if (vodQueryVideoPasswordVO.getVideoId() != null) {
            vodQueryVideoPasswordResponse.setVideoId(vodQueryVideoPasswordVO.getVideoId());
        }
        // 是否含有视频信息
        boolean hasVideoInfo = vodQueryVideoPasswordVO.getVideoInfo() != null;
        // 是否含有视频扩展信息
        boolean hasVideoInfoExt = hasVideoInfo && vodQueryVideoPasswordVO.getVideoInfo().getVideoInfoExt() != null;
        // 是否含有密码信息
        boolean hasPassword =
                hasVideoInfoExt && vodQueryVideoPasswordVO.getVideoInfo().getVideoInfoExt().getPassword() != null;
        if (hasVideoInfo && vodQueryVideoPasswordVO.getVideoInfo().getTitle() != null) {
            vodQueryVideoPasswordResponse.setTitle(vodQueryVideoPasswordVO.getVideoInfo().getTitle());
        }
        if (hasPassword) {
            vodQueryVideoPasswordResponse.setPassword(
                    vodQueryVideoPasswordVO.getVideoInfo().getVideoInfoExt().getPassword());
        }
        return vodQueryVideoPasswordResponse;
    }
    
    /**
     * 批量获取视频的时长和大小
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/info/
     * @param vodGetVideosSizeRequest 批量获取视频的时长和大小请求实体
     * @return 批量获取视频的时长和大小返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetVideosSizeResponse> getVideosSize(VodGetVideosSizeRequest vodGetVideosSizeRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_VIDEOS_SIZE_URL);
        return super.getReturnList(url, vodGetVideosSizeRequest, VodGetVideosSizeResponse.class);
    }
    
    /**
     * 批量获取视频播放次数
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/getplaytimes/
     * @param vodGetVideosPlayTimesRequest 批量获取视频播放次数请求实体
     * @return 批量获取视频播放次数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetVideosPlayTimesResponse> getVideosPlayTimes(
            VodGetVideosPlayTimesRequest vodGetVideosPlayTimesRequest) throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.GET_VIDEOS_PLAY_SIZE_URL);
        return super.getReturnList(url, vodGetVideosPlayTimesRequest, VodGetVideosPlayTimesResponse.class);
    }
    
}
