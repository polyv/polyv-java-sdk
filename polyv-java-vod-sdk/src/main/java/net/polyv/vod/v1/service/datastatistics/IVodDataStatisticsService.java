package net.polyv.vod.v1.service.datastatistics;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.datastatistics.VodGetVideoPlayLogRequest;
import net.polyv.vod.v1.entity.datastatistics.VodGetVideoPlayLogResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryPlayDomainNameStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryPlayDomainNameStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoDeviceStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoDeviceStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoGeographicStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoGeographicStatisticsResponse;
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
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewLogByDayRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewLogByDayResponse;

/**
 * 数据统计
 * @author: fangyan
 */
public interface IVodDataStatisticsService {
    /**
     * 获取某一天视频观看日志
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-data/viewlog/
     * @param vodQueryViewLogByDayRequest 获取某一天视频观看日志请求实体
     * @return 获取某一天视频观看日志返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodQueryViewLogByDayResponse> queryViewLogByDay(VodQueryViewLogByDayRequest vodQueryViewLogByDayRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量获取视频观看日志
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-data/viewlog_monthly/
     * @param vodGetVideoPlayLogRequest 批量获取视频观看日志请求实体
     * @return 批量获取视频观看日志返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetVideoPlayLogResponse getVideoPlayLog(VodGetVideoPlayLogRequest vodGetVideoPlayLogRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频播放量统计数据接口
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/videoview/
     * @param vodQueryVideoPlaybackStatisticsRequest 查询视频播放量统计数据接口请求实体
     * @return 查询视频播放量统计数据接口返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodQueryVideoPlaybackStatisticsResponse> queryVideoPlaybackStatistics(
            VodQueryVideoPlaybackStatisticsRequest vodQueryVideoPlaybackStatisticsRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频播放量排行接口
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/ranklist/
     * @param vodQueryVideoPlaybackRankingRequest 查询视频播放量排行接口请求实体
     * @return 查询视频播放量排行接口返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodQueryVideoPlaybackRankingResponse queryVideoPlaybackRanking(
            VodQueryVideoPlaybackRankingRequest vodQueryVideoPlaybackRankingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询播放域名统计数据接口
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/domainstats/
     * @param vodQueryPlayDomainNameStatisticsRequest 查询播放域名统计数据接口请求实体
     * @return 查询播放域名统计数据接口返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodQueryPlayDomainNameStatisticsResponse> queryPlayDomainNameStatistics(
            VodQueryPlayDomainNameStatisticsRequest vodQueryPlayDomainNameStatisticsRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频终端环境统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/device/
     * @param vodQueryVideoDeviceStatisticsRequest 查询视频终端环境统计数据请求实体
     * @return 查询视频终端环境统计数据返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodQueryVideoDeviceStatisticsResponse queryVideoDeviceStatistics(
            VodQueryVideoDeviceStatisticsRequest vodQueryVideoDeviceStatisticsRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频播放时段统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/hourly/
     * @param vodQueryVideoPlaybackHourlyStatisticsRequest 查询视频播放时段统计数据请求实体
     * @return 查询视频播放时段统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodQueryVideoPlaybackHourlyStatisticsResponse> queryVideoPlaybackHourlyStatistics(
            VodQueryVideoPlaybackHourlyStatisticsRequest vodQueryVideoPlaybackHourlyStatisticsRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频播放地理位置统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/geo/
     * @param vodQueryVideoGeographicStatisticsRequest 查询视频播放地理位置统计数据请求实体
     * @return 查询视频播放地理位置统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodQueryVideoGeographicStatisticsResponse> queryVideoGeographicStatistics(
            VodQueryVideoGeographicStatisticsRequest vodQueryVideoGeographicStatisticsRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频观众量统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/data-visitor/
     * @param vodQueryVideoViewershipRequest 查询视频观众量统计数据请求实体
     * @return 查询视频观众量统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodQueryVideoViewershipResponse> queryVideoViewership(
            VodQueryVideoViewershipRequest vodQueryVideoViewershipRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频某个时段的播放流量统计数据
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-data/traffic-video/
     * @param vodQueryVideoPlaybackFlowSizeStatisticsRequest 查询视频某个时段的播放流量统计数据请求实体
     * @return 查询视频某个时段的播放流量统计数据返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodQueryVideoPlaybackFlowSizeStatisticsResponse> queryVideoPlaybackFlowSizeStatistics(
            VodQueryVideoPlaybackFlowSizeStatisticsRequest vodQueryVideoPlaybackFlowSizeStatisticsRequest)
            throws IOException, NoSuchAlgorithmException;
}
