package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoPlayStatusRequest;
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
    
}
