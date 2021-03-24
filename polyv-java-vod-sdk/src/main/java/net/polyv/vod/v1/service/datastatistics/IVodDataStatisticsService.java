package net.polyv.vod.v1.service.datastatistics;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.datastatistics.VodGetVideoPlayLogRequest;
import net.polyv.vod.v1.entity.datastatistics.VodGetVideoPlayLogResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryPlayDomainNameStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryPlayDomainNameStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackRankingRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackRankingResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackStatisticsResponse;
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
}
