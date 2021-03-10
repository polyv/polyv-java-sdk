package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoPlayStatusRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoSizeRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoSizeResponse;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameResponse;
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
    
}
