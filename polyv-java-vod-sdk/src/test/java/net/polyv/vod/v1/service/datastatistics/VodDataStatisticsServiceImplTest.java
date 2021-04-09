package net.polyv.vod.v1.service.datastatistics;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
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
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.datastatistics.impl.VodDataStatisticsServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 数据统计
 * @author: fangyan
 */
@Slf4j
public class VodDataStatisticsServiceImplTest extends BaseTest {
    
    /**
     * 测试获取某一天视频观看日志
     * 约束：2、从播放行为产生到数据可查询的间隔时间为1~2小时。但是每条观看记录所消耗的流量计算（flowSize字段）依赖于CDN日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2
     * 约束：号晚上汇总计算，在3号才可查询到流量数据。
     * 约束：3、注意视频ID和分类ID为空时，获取账号当天所有视频日志；当视频ID为空、分类ID不为空时，查询对应分类ID下的日志；当视频ID不为空时查询对应视频ID的日志
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryViewLogByDay() throws IOException, NoSuchAlgorithmException {
        VodQueryViewLogByDayRequest vodQueryViewLogByDayRequest = new VodQueryViewLogByDayRequest();
        List<VodQueryViewLogByDayResponse> vodQueryViewLogByDayResponseList = null;
        try {
            vodQueryViewLogByDayRequest.setDay(super.getDate(2021, 2, 4))
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setCategoryId("1602300731843")
                    .setSessionId(null)
                    .setViewerId(null);
            vodQueryViewLogByDayResponseList = new VodDataStatisticsServiceImpl().queryViewLogByDay(
                    vodQueryViewLogByDayRequest);
            Assert.assertNotNull(vodQueryViewLogByDayResponseList);
            if (vodQueryViewLogByDayResponseList != null) {
                log.debug("测试获取某一天视频观看日志成功,{}", JSON.toJSONString(vodQueryViewLogByDayResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试批量获取视频观看日志
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoPlayLog() throws IOException, NoSuchAlgorithmException {
        VodGetVideoPlayLogRequest vodGetVideoPlayLogRequest = new VodGetVideoPlayLogRequest();
        VodGetVideoPlayLogResponse vodGetVideoPlayLogResponse = null;
        try {
            vodGetVideoPlayLogRequest.setMonth(super.getDate(2021, 2, 1))
                    .setStartTime(super.getDate(2021, 2, 1))
                    .setEndTime(super.getDate(2021, 2, 31))
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setCurrentDay(null)
                    .setCurrentPage(1)
                    .setPageSize(10);
            vodGetVideoPlayLogResponse = new VodDataStatisticsServiceImpl().getVideoPlayLog(vodGetVideoPlayLogRequest);
            Assert.assertNotNull(vodGetVideoPlayLogResponse);
            if (vodGetVideoPlayLogResponse != null) {
                log.debug("测试批量获取视频观看日志成功,{}", JSON.toJSONString(vodGetVideoPlayLogResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询视频播放量排行
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoPlaybackRanking() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlaybackRankingRequest vodQueryVideoPlaybackRankingRequest =
                new VodQueryVideoPlaybackRankingRequest();
        VodQueryVideoPlaybackRankingResponse vodQueryVideoPlaybackRankingResponse = null;
        try {
            vodQueryVideoPlaybackRankingRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24));
            vodQueryVideoPlaybackRankingResponse = new VodDataStatisticsServiceImpl().queryVideoPlaybackRanking(
                    vodQueryVideoPlaybackRankingRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackRankingResponse);
            if (vodQueryVideoPlaybackRankingResponse != null) {
                log.debug("测试查询视频播放量排行成功,{}", JSON.toJSONString(vodQueryVideoPlaybackRankingResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询视频播放量统计数据
     * 约束：2、查询视频播放量统计数据，从播放行为产生到数据可查询的间隔时间为1~2小时。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoPlaybackStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlaybackStatisticsRequest vodQueryVideoPlaybackStatisticsRequest =
                new VodQueryVideoPlaybackStatisticsRequest();
        List<VodQueryVideoPlaybackStatisticsResponse> vodQueryVideoPlaybackStatisticsResponseList = null;
        try {
            vodQueryVideoPlaybackStatisticsRequest.setDr("7days")
                    .setPeriod("daily");
            vodQueryVideoPlaybackStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoPlaybackStatistics(
                    vodQueryVideoPlaybackStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackStatisticsResponseList);
            if (vodQueryVideoPlaybackStatisticsResponseList != null) {
                log.debug("测试查询视频播放量统计数据成功,{}", JSON.toJSONString(vodQueryVideoPlaybackStatisticsResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询播放域名统计数据
     * 约束：2、查询播放域名统计数据
     * 约束：3、从播放行为产生到数据可查询的间隔时间为1~2小时。但是消耗流量（PCFlowSize字段）的计算依赖于CDN日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2
     * 约束：3、号晚上汇总计算，在3号才可查询到流量数据。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryPlayDomainNameStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryPlayDomainNameStatisticsRequest vodQueryPlayDomainNameStatisticsRequest =
                new VodQueryPlayDomainNameStatisticsRequest();
        List<VodQueryPlayDomainNameStatisticsResponse> vodQueryPlayDomainNameStatisticsResponseList = null;
        try {
            vodQueryPlayDomainNameStatisticsRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24));
            vodQueryPlayDomainNameStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryPlayDomainNameStatistics(
                    vodQueryPlayDomainNameStatisticsRequest);
            Assert.assertNotNull(vodQueryPlayDomainNameStatisticsResponseList);
            if (vodQueryPlayDomainNameStatisticsResponseList != null) {
                log.debug("测试查询播放域名统计数据成功,{}", JSON.toJSONString(vodQueryPlayDomainNameStatisticsResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询视频终端环境统计数据
     * 约束：2、查询视频终端环境统计数据，包括浏览器环境，操作系统环境，终端环境。从播放行为产生到数据可查询的间隔时间为1~2小时。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoDeviceStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoDeviceStatisticsRequest vodQueryVideoDeviceStatisticsRequest =
                new VodQueryVideoDeviceStatisticsRequest();
        VodQueryVideoDeviceStatisticsResponse vodQueryVideoDeviceStatisticsResponse = null;
        try {
            vodQueryVideoDeviceStatisticsRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24));
            vodQueryVideoDeviceStatisticsResponse = new VodDataStatisticsServiceImpl().queryVideoDeviceStatistics(
                    vodQueryVideoDeviceStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoDeviceStatisticsResponse);
            if (vodQueryVideoDeviceStatisticsResponse != null) {
                log.debug("测试查询视频终端环境统计数据成功,{}", JSON.toJSONString(vodQueryVideoDeviceStatisticsResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询视频播放时段统计数据
     * 约束：2、从播放行为产生到数据可查询的间隔时间为1~2小时，但是统计结果中流量消耗（PCFlowSize、mobileFlowSize字段）的计算依赖于CDN
     * 约束：2、日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2号晚上汇总计算，在3号才可查询到流量数据。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoPlaybackHourlyStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlaybackHourlyStatisticsRequest vodQueryVideoPlaybackHourlyStatisticsRequest =
                new VodQueryVideoPlaybackHourlyStatisticsRequest();
        List<VodQueryVideoPlaybackHourlyStatisticsResponse> vodQueryVideoPlaybackHourlyStatisticsResponseList = null;
        try {
            vodQueryVideoPlaybackHourlyStatisticsRequest.setDr("7days");
            vodQueryVideoPlaybackHourlyStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoPlaybackHourlyStatistics(
                    vodQueryVideoPlaybackHourlyStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackHourlyStatisticsResponseList);
            if (vodQueryVideoPlaybackHourlyStatisticsResponseList != null) {
                log.debug("测试查询视频播放时段统计数据成功,{}", JSON.toJSONString(vodQueryVideoPlaybackHourlyStatisticsResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询视频某个时段的播放流量统计数据
     * 约束：2、自2018年7月10日起，才可以统计到单个视频的移动端流量数据，在此之前没有移动端流量数据
     * 约束：3、流量消耗的计算依赖于CDN日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2号晚上汇总计算，在3号才可查询到流量数据
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoPlaybackFlowSizeStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlaybackFlowSizeStatisticsRequest vodQueryVideoPlaybackFlowSizeStatisticsRequest =
                new VodQueryVideoPlaybackFlowSizeStatisticsRequest();
        List<VodQueryVideoPlaybackFlowSizeStatisticsResponse> vodQueryVideoPlaybackFlowSizeStatisticsResponseList =
                null;
        try {
            vodQueryVideoPlaybackFlowSizeStatisticsRequest.setDr("7days")
                    .setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24));
            vodQueryVideoPlaybackFlowSizeStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoPlaybackFlowSizeStatistics(
                            vodQueryVideoPlaybackFlowSizeStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackFlowSizeStatisticsResponseList);
            if (vodQueryVideoPlaybackFlowSizeStatisticsResponseList != null) {
                log.debug("测试查询视频某个时段的播放流量统计数据成功,{}",
                        JSON.toJSONString(vodQueryVideoPlaybackFlowSizeStatisticsResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询视频播放地理位置统计数据
     * 约束：2、从播放行为产生到数据可查询的间隔时间为1~2小时，但是统计结果中流量消耗（PCFlowSize、mobileFlowSize字段）的计算依赖于CDN
     * 约束：2、日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2号晚上汇总计算，在3号才可查询到流量数据。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoGeographicStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoGeographicStatisticsRequest vodQueryVideoGeographicStatisticsRequest =
                new VodQueryVideoGeographicStatisticsRequest();
        List<VodQueryVideoGeographicStatisticsResponse> vodQueryVideoGeographicStatisticsResponseList = null;
        try {
            vodQueryVideoGeographicStatisticsRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24));
            vodQueryVideoGeographicStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoGeographicStatistics(
                    vodQueryVideoGeographicStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoGeographicStatisticsResponseList);
            if (vodQueryVideoGeographicStatisticsResponseList != null) {
                log.debug("测试查询视频播放地理位置统计数据成功,{}", JSON.toJSONString(vodQueryVideoGeographicStatisticsResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询视频观众量统计数据
     * 约束：2、按照日期区间或区段及视频ID查询视频的观众量统计数据，不传vid参数就表示查询用户下所有视频的观众量。
     * 约束：2、从播放行为产生到数据可查询的间隔时间为1~2小时。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoViewership() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoViewershipRequest vodQueryVideoViewershipRequest = new VodQueryVideoViewershipRequest();
        List<VodQueryVideoViewershipResponse> vodQueryVideoViewershipResponseList = null;
        try {
            vodQueryVideoViewershipRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24));
            vodQueryVideoViewershipResponseList = new VodDataStatisticsServiceImpl().queryVideoViewership(
                    vodQueryVideoViewershipRequest);
            Assert.assertNotNull(vodQueryVideoViewershipResponseList);
            if (vodQueryVideoViewershipResponseList != null) {
                log.debug("测试查询视频观众量统计数据成功,{}", JSON.toJSONString(vodQueryVideoViewershipResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询视频的播放时长统计数据
     * 约束：2、按照日期区间或时段查询视频播放时长统计数据，从播放行为产生到数据可查询的间隔时间为1~2小时。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoPlayTimeStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlayTimeStatisticsRequest vodQueryVideoPlayTimeStatisticsRequest =
                new VodQueryVideoPlayTimeStatisticsRequest();
        List<VodQueryVideoPlayTimeStatisticsResponse> vodQueryVideoPlayTimeStatisticsResponseList = null;
        try {
            vodQueryVideoPlayTimeStatisticsRequest.setDr("7days")
                    .setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24));
            vodQueryVideoPlayTimeStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoPlayTimeStatistics(
                    vodQueryVideoPlayTimeStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoPlayTimeStatisticsResponseList);
            if (vodQueryVideoPlayTimeStatisticsResponseList != null) {
                log.debug("测试查询视频的播放时长统计数据成功,{}", JSON.toJSONString(vodQueryVideoPlayTimeStatisticsResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询单个视频的观看热点统计数据
     * 约束：2、按照日期区间或时间段查询单个视频的观看热点统计数据，从播放行为产生到数据可查询的间隔时间为1~2小时。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoViewingHotspotStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoViewingHotspotStatisticsRequest vodQueryVideoViewingHotspotStatisticsRequest =
                new VodQueryVideoViewingHotspotStatisticsRequest();
        List<VodQueryVideoViewingHotspotStatisticsResponse> vodQueryVideoViewingHotspotStatisticsResponseList = null;
        try {
            vodQueryVideoViewingHotspotStatisticsRequest.setDr("7days")
                    .setVideoId("1b448be3230a0194d959426ae005645f_1")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24));
            vodQueryVideoViewingHotspotStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoViewingHotspotStatistics(
                    vodQueryVideoViewingHotspotStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoViewingHotspotStatisticsResponseList);
            if (vodQueryVideoViewingHotspotStatisticsResponseList != null) {
                log.debug("测试查询单个视频的观看热点统计数据成功,{}",
                        JSON.toJSONString(vodQueryVideoViewingHotspotStatisticsResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询视频的观看比例统计数据
     * 约束：2、查询单个视频或全部视频在一定时间范围内的观看比例统计数据，从播放行为产生到数据可查询的间隔时间为1~2小时。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoViewingRatioStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoViewingRatioStatisticsRequest vodQueryVideoViewingRatioStatisticsRequest =
                new VodQueryVideoViewingRatioStatisticsRequest();
        List<VodQueryVideoViewingRatioStatisticsResponse> vodQueryVideoViewingRatioStatisticsResponseList = null;
        try {
            vodQueryVideoViewingRatioStatisticsRequest.setDr("7days");
            vodQueryVideoViewingRatioStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoViewingRatioStatistics(
                    vodQueryVideoViewingRatioStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoViewingRatioStatisticsResponseList);
            if (vodQueryVideoViewingRatioStatisticsResponseList != null) {
                log.debug("测试查询视频的观看比例统计数据成功,{}", JSON.toJSONString(vodQueryVideoViewingRatioStatisticsResponseList));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试获取视频观看完成度
     * 约束：2、该接口可查看某一观众累计观看某一视频的完成度情况。无论观众使用哪种终端、分多少次观看，接口返回的是最终的汇总的完成度。比如，视频A时长为50分钟，观众使用PC
     * 约束：2、H5观看了第0&sim;20分钟，使用手机H5观看了第10~30分钟，又使用APP观看了第40&sim;50分钟，累计观看时长为20+20+10=50分钟，但观看的视频内容是 0&sim;30 和 40~50
     * 约束：2、的部分。虽然累计观看时长与视频时长相同，但完成度为 (30+10)/50=80%。
     * 约束：3、数据隔天更新一次
     * 约束：4、该接口需联系客服开通后才能使用
     * 返回：已完成进度比例
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoViewingCompletion() throws IOException, NoSuchAlgorithmException {
        VodGetVideoViewingCompletionRequest vodGetVideoViewingCompletionRequest =
                new VodGetVideoViewingCompletionRequest();
        Float vodGetVideoViewingCompletionResponse = null;
        try {
            vodGetVideoViewingCompletionRequest.setVideoId("1b448be3230a0194d959426ae005645f_1")
                    .setViewerId("1555313336634");
            vodGetVideoViewingCompletionResponse = new VodDataStatisticsServiceImpl().getVideoViewingCompletion(
                    vodGetVideoViewingCompletionRequest);
            Assert.assertNotNull(vodGetVideoViewingCompletionResponse);
            if (vodGetVideoViewingCompletionResponse != null) {
                log.debug("测试获取视频观看完成度成功,已完成进度比例{}", vodGetVideoViewingCompletionResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试高级分析-分页查询观看行为列表
     * 约束：2、高级分析功能介绍详见：http://dev.polyv.net/2019/videoproduct/v-manual/v-manual-statistic/advance-analysis/
     * 约束：3、由于数据量和计算量大，数据分析结果次日才可查询。
     * 约束：4、查询的时间跨度不超过31天；
     * 约束：5、当start有值而end为空时，返回开始日期后31天后的数据；
     * 约束：6、当start为空而end不为空时，返回结束日期前31天内的数据；
     * 约束：7、当start、end参数均为空时，返回最近31天的数据。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryViewingBehaviorList() throws IOException, NoSuchAlgorithmException {
        VodQueryViewingBehaviorListRequest vodQueryViewingBehaviorListRequest =
                new VodQueryViewingBehaviorListRequest();
        VodQueryViewingBehaviorListResponse vodQueryViewingBehaviorListResponse = null;
        try {
            vodQueryViewingBehaviorListRequest.setStartTime(super.getDate(2021, 2, 1))
                    .setEndTime(super.getDate(2021, 2, 30))
                    .setVideoId("1b448be3230a0194d959426ae005645f_1")
                    .setCurrentPage(1)
                    .setPageSize(10);
            vodQueryViewingBehaviorListResponse = new VodDataStatisticsServiceImpl().queryViewingBehaviorList(
                    vodQueryViewingBehaviorListRequest);
            Assert.assertNotNull(vodQueryViewingBehaviorListResponse);
            if (vodQueryViewingBehaviorListResponse != null) {
                log.debug("测试高级分析-分页查询观看行为列表成功{}", JSON.toJSONString(vodQueryViewingBehaviorListResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试高级分析–根据视频id查询视频分析数据
     * 说明：2、高级分析功能介绍详见：http://dev.polyv.net/2019/videoproduct/v-manual/v-manual-statistic/advance-analysis/
     * 说明：3、由于数据量和计算量大，数据分析结果次日才可查询。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoAnalysisData() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoAnalysisDataRequest vodQueryVideoAnalysisDataRequest = new VodQueryVideoAnalysisDataRequest();
        VodQueryVideoAnalysisDataResponse vodQueryVideoAnalysisDataResponse = null;
        try {
            vodQueryVideoAnalysisDataRequest.setVideoId("1b448be3230a0194d959426ae005645f_1");
            vodQueryVideoAnalysisDataResponse = new VodDataStatisticsServiceImpl().queryVideoAnalysisData(
                    vodQueryVideoAnalysisDataRequest);
            Assert.assertNotNull(vodQueryVideoAnalysisDataResponse);
            if (vodQueryVideoAnalysisDataResponse != null) {
                log.debug("测试高级分析–根据视频id查询视频分析数据成功{}", JSON.toJSONString(vodQueryVideoAnalysisDataResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试高级分析–根据观众id查询观众分析结果
     * 说明：2、高级分析功能介绍详见：http://dev.polyv.net/2019/videoproduct/v-manual/v-manual-statistic/advance-analysis/
     * 说明：3、由于数据量和计算量大，数据分析结果次日才可查询。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryAudienceAnalysisResults() throws IOException, NoSuchAlgorithmException {
        VodQueryAudienceAnalysisResultsRequest vodQueryAudienceAnalysisResultsRequest =
                new VodQueryAudienceAnalysisResultsRequest();
        VodQueryAudienceAnalysisResultsResponse vodQueryAudienceAnalysisResultsResponse = null;
        try {
            vodQueryAudienceAnalysisResultsRequest.setViewerId("1555313336634");
            vodQueryAudienceAnalysisResultsResponse = new VodDataStatisticsServiceImpl().queryAudienceAnalysisResults(
                    vodQueryAudienceAnalysisResultsRequest);
            Assert.assertNotNull(vodQueryAudienceAnalysisResultsResponse);
            if (vodQueryAudienceAnalysisResultsResponse != null) {
                log.debug("测试高级分析–根据观众id查询观众分析结果成功{}", JSON.toJSONString(vodQueryAudienceAnalysisResultsResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试用例结束
     */
}
