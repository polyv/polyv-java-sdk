package net.polyv.vod.v1.service.datastatistics.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.datastatistics.VodGetVideoPlayLogRequest;
import net.polyv.vod.v1.entity.datastatistics.VodGetVideoPlayLogResponse;
import net.polyv.vod.v1.entity.datastatistics.VodGetVideoViewingCompletionRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryAudienceAnalysisResultsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryAudienceAnalysisResultsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryPlayDomainNameStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryPlayDomainNameStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoAnalysisDataRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoAnalysisDataResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoDeviceStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoDeviceStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoGeographicStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoGeographicStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlayTimeStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlayTimeStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackFlowSizeStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackFlowSizeStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackHourlyStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackHourlyStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackRankingRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackRankingResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoViewershipRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoViewershipResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoViewingHotspotStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoViewingHotspotStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoViewingRatioStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoViewingRatioStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewLogByDayRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewLogByDayResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewingBehaviorListRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewingBehaviorListResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.datastatistics.IVodDataStatisticsService;

/**
 * 数据统计
 * @author: fangyan
 */
@Slf4j
public class VodDataStatisticsServiceImpl extends VodBaseService implements IVodDataStatisticsService {
    
    /**
     * 获取某一天视频观看日志
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-data/viewlog/
     * @param vodQueryViewLogByDayRequest 获取某一天视频观看日志请求实体
     * @return 获取某一天视频观看日志返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryViewLogByDayResponse> queryViewLogByDay(VodQueryViewLogByDayRequest vodQueryViewLogByDayRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.QUERY_VIEW_LOG_BY_DAY_URL);
        return super.getReturnList(url, vodQueryViewLogByDayRequest, VodQueryViewLogByDayResponse.class);
    }
    
    /**
     * 批量获取视频观看日志
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-data/viewlog_monthly/
     * @param vodGetVideoPlayLogRequest 批量获取视频观看日志请求实体
     * @return 批量获取视频观看日志返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetVideoPlayLogResponse getVideoPlayLog(VodGetVideoPlayLogRequest vodGetVideoPlayLogRequest)
            throws IOException, NoSuchAlgorithmException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(VodGetVideoPlayLogRequest.MONTH_FORMAT);
        String url = VodURL.getRealUrl(VodURL.VOD_GET_VIDEO_PLAY_LOG_URL, VodGlobalConfig.getUserId(),
                simpleDateFormat.format(vodGetVideoPlayLogRequest.getMonth()));
        VodGetVideoPlayLogResponse vodGetVideoPlayLogResponse = super.getReturnOne(url, vodGetVideoPlayLogRequest,
                VodGetVideoPlayLogResponse.class);
        vodGetVideoPlayLogResponse.setPageSize(vodGetVideoPlayLogRequest.getPageSize());
        return vodGetVideoPlayLogResponse;
    }
    
    /**
     * 查询视频播放量统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/videoview/
     * @param vodQueryVideoPlaybackStatisticsRequest 查询视频播放量统计数据请求实体
     * @return 查询视频播放量统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryVideoPlaybackStatisticsResponse> queryVideoPlaybackStatistics(
            VodQueryVideoPlaybackStatisticsRequest vodQueryVideoPlaybackStatisticsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_PLAYBACK_STATISTICS_URL);
        return super.getReturnList(url, vodQueryVideoPlaybackStatisticsRequest,
                VodQueryVideoPlaybackStatisticsResponse.class);
    }
    
    /**
     * 查询视频播放量排行
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/ranklist/
     * @param vodQueryVideoPlaybackRankingRequest 查询视频播放量排行请求实体
     * @return 查询视频播放量排行返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodQueryVideoPlaybackRankingResponse queryVideoPlaybackRanking(
            VodQueryVideoPlaybackRankingRequest vodQueryVideoPlaybackRankingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_PLAYBACK_RANKING_URL);
        return super.getReturnOne(url, vodQueryVideoPlaybackRankingRequest, VodQueryVideoPlaybackRankingResponse.class);
    }
    
    /**
     * 查询播放域名统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/domainstats/
     * @param vodQueryPlayDomainNameStatisticsRequest 查询播放域名统计数据请求实体
     * @return 查询播放域名统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryPlayDomainNameStatisticsResponse> queryPlayDomainNameStatistics(
            VodQueryPlayDomainNameStatisticsRequest vodQueryPlayDomainNameStatisticsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_PLAY_DOMAIN_NAME_STATISTICS_URL);
        return super.getReturnList(url, vodQueryPlayDomainNameStatisticsRequest,
                VodQueryPlayDomainNameStatisticsResponse.class);
    }
    
    /**
     * 查询视频终端环境统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/device/
     * @param vodQueryVideoDeviceStatisticsRequest 查询视频终端环境统计数据请求实体
     * @return 查询视频终端环境统计数据返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodQueryVideoDeviceStatisticsResponse queryVideoDeviceStatistics(
            VodQueryVideoDeviceStatisticsRequest vodQueryVideoDeviceStatisticsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_DEVICE_STATISTICS_URL);
        return super.getReturnOne(url, vodQueryVideoDeviceStatisticsRequest,
                VodQueryVideoDeviceStatisticsResponse.class);
    }
    
    /**
     * 查询视频播放时段统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/hourly/
     * @param vodQueryVideoPlaybackHourlyStatisticsRequest 查询视频播放时段统计数据请求实体
     * @return 查询视频播放时段统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryVideoPlaybackHourlyStatisticsResponse> queryVideoPlaybackHourlyStatistics(
            VodQueryVideoPlaybackHourlyStatisticsRequest vodQueryVideoPlaybackHourlyStatisticsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_PLAYBACK_HOURLY_STATISTICS_URL);
        return super.getReturnList(url, vodQueryVideoPlaybackHourlyStatisticsRequest,
                VodQueryVideoPlaybackHourlyStatisticsResponse.class);
    }
    
    /**
     * 查询视频播放地理位置统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/geo/
     * @param vodQueryVideoGeographicStatisticsRequest 查询视频播放地理位置统计数据请求实体
     * @return 查询视频播放地理位置统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryVideoGeographicStatisticsResponse> queryVideoGeographicStatistics(
            VodQueryVideoGeographicStatisticsRequest vodQueryVideoGeographicStatisticsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_GEOGRAPHIC_STATISTICS_URL);
        return super.getReturnList(url, vodQueryVideoGeographicStatisticsRequest,
                VodQueryVideoGeographicStatisticsResponse.class);
    }
    
    /**
     * 查询视频观众量统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/data-visitor/
     * @param vodQueryVideoViewershipRequest 查询视频观众量统计数据请求实体
     * @return 查询视频观众量统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryVideoViewershipResponse> queryVideoViewership(
            VodQueryVideoViewershipRequest vodQueryVideoViewershipRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_VIEWERSHIP_URL);
        return super.getReturnList(url, vodQueryVideoViewershipRequest, VodQueryVideoViewershipResponse.class);
    }
    
    /**
     * 查询视频某个时段的播放流量统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/traffic-video/
     * @param vodQueryVideoPlaybackFlowSizeStatisticsRequest 查询视频某个时段的播放流量统计数据请求实体
     * @return 查询视频某个时段的播放流量统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryVideoPlaybackFlowSizeStatisticsResponse> queryVideoPlaybackFlowSizeStatistics(
            VodQueryVideoPlaybackFlowSizeStatisticsRequest vodQueryVideoPlaybackFlowSizeStatisticsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_PLAYBACK_FLOW_SIZE_STATISTICS_URL,
                VodGlobalConfig.getUserId(), vodQueryVideoPlaybackFlowSizeStatisticsRequest.getVideoId());
        return super.getReturnList(url, vodQueryVideoPlaybackFlowSizeStatisticsRequest,
                VodQueryVideoPlaybackFlowSizeStatisticsResponse.class);
    }
    
    /**
     * 查询视频的播放时长统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/play-duration/
     * @param vodQueryVideoPlayTimeStatisticsRequest 查询视频的播放时长统计数据请求实体
     * @return 查询视频的播放时长统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryVideoPlayTimeStatisticsResponse> queryVideoPlayTimeStatistics(
            VodQueryVideoPlayTimeStatisticsRequest vodQueryVideoPlayTimeStatisticsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_PLAY_TIME_STATISTICS_URL, VodGlobalConfig.getUserId());
        return super.getReturnList(url, vodQueryVideoPlayTimeStatisticsRequest,
                VodQueryVideoPlayTimeStatisticsResponse.class);
    }
    
    /**
     * 查询单个视频的观看热点统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/videohot/
     * @param vodQueryVideoViewingHotspotStatisticsRequest 查询单个视频的观看热点统计数据请求实体
     * @return 查询单个视频的观看热点统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryVideoViewingHotspotStatisticsResponse> queryVideoViewingHotspotStatistics(
            VodQueryVideoViewingHotspotStatisticsRequest vodQueryVideoViewingHotspotStatisticsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_VIEWING_HOTSPOT_STATISTICS_URL,
                VodGlobalConfig.getUserId());
        return super.getReturnList(url, vodQueryVideoViewingHotspotStatisticsRequest,
                VodQueryVideoViewingHotspotStatisticsResponse.class);
    }
    
    /**
     * 查询视频的观看比例统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/play-ratio/
     * @param vodQueryVideoViewingRatioStatisticsRequest 查询视频的观看比例统计数据请求实体
     * @return 查询视频的观看比例统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryVideoViewingRatioStatisticsResponse> queryVideoViewingRatioStatistics(
            VodQueryVideoViewingRatioStatisticsRequest vodQueryVideoViewingRatioStatisticsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_VIEWING_RATIO_STATISTICS_URL,
                VodGlobalConfig.getUserId());
        return super.getReturnList(url, vodQueryVideoViewingRatioStatisticsRequest,
                VodQueryVideoViewingRatioStatisticsResponse.class);
    }
    
    /**
     * 获取视频观看完成度
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-data/engagement-get/
     * @param vodGetVideoViewingCompletionRequest 获取视频观看完成度请求实体
     * @return Integer
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Float getVideoViewingCompletion(VodGetVideoViewingCompletionRequest vodGetVideoViewingCompletionRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_VIDEO_VIEWING_COMPLETION_URL, VodGlobalConfig.getUserId());
        return super.getReturnOne(url, vodGetVideoViewingCompletionRequest, Float.class);
    }
    
    /**
     * 高级分析-分页查询观看行为列表
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-data/data-advance-detail/
     * @param vodQueryViewingBehaviorListRequest 高级分析-分页查询观看行为列表请求实体
     * @return 高级分析-分页查询观看行为列表返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodQueryViewingBehaviorListResponse queryViewingBehaviorList(
            VodQueryViewingBehaviorListRequest vodQueryViewingBehaviorListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIEWING_BEHAVIOR_LIST_URL, VodGlobalConfig.getUserId());
        VodQueryViewingBehaviorListResponse vodQueryViewingBehaviorListResponse = super.getReturnOne(url,
                vodQueryViewingBehaviorListRequest, VodQueryViewingBehaviorListResponse.class);
        vodQueryViewingBehaviorListResponse.setPageSize(vodQueryViewingBehaviorListRequest.getPageSize());
        return vodQueryViewingBehaviorListResponse;
    }
    
    /**
     * 高级分析–根据视频id查询视频分析数据
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-data/data-advance-video/
     * @param vodQueryVideoAnalysisDataRequest 高级分析-根据视频id查询视频分析数据请求实体
     * @return 高级分析-根据视频id查询视频分析数据返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodQueryVideoAnalysisDataResponse queryVideoAnalysisData(
            VodQueryVideoAnalysisDataRequest vodQueryVideoAnalysisDataRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_VIDEO_ANALYSIS_DATA_URL, VodGlobalConfig.getUserId());
        return super.getReturnOne(url, vodQueryVideoAnalysisDataRequest, VodQueryVideoAnalysisDataResponse.class);
    }
    
    /**
     * 高级分析–根据观众id查询观众分析结果
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-data/data-advance-viewer/
     * @param vodQueryAudienceAnalysisResultsRequest 高级分析–根据观众id查询观众分析结果请求实体
     * @return 高级分析–根据观众id查询观众分析结果返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodQueryAudienceAnalysisResultsResponse queryAudienceAnalysisResults(
            VodQueryAudienceAnalysisResultsRequest vodQueryAudienceAnalysisResultsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_AUDIENCE_ANALYSIS_RESULTS_URL, VodGlobalConfig.getUserId());
        return super.getReturnOne(url, vodQueryAudienceAnalysisResultsRequest,
                VodQueryAudienceAnalysisResultsResponse.class);
    }
}
