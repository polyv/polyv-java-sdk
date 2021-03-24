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
import net.polyv.vod.v1.entity.datastatistics.VodQueryPlayDomainNameStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryPlayDomainNameStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackRankingRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackRankingResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackStatisticsRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryVideoPlaybackStatisticsResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewLogByDayRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewLogByDayResponse;
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
                    .setViewerId(null)
                    .setRequestId(VodSignUtil.generateUUID());
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
                    .setPageSize(10)
                    .setRequestId(VodSignUtil.generateUUID());
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
     * 测试查询视频播放量统计数据接口
     * 约束：2、查询视频播放量统计数据接口，从播放行为产生到数据可查询的间隔时间为1~2小时。
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
                    .setPeriod("daily")
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoPlaybackStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoPlaybackStatistics(
                    vodQueryVideoPlaybackStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackStatisticsResponseList);
            if (vodQueryVideoPlaybackStatisticsResponseList != null) {
                log.debug("测试查询视频播放量统计数据接口成功,{}", JSON.toJSONString(vodQueryVideoPlaybackStatisticsResponseList));
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
     * 测试查询视频播放量排行接口
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
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoPlaybackRankingResponse = new VodDataStatisticsServiceImpl().queryVideoPlaybackRanking(
                    vodQueryVideoPlaybackRankingRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackRankingResponse);
            if (vodQueryVideoPlaybackRankingResponse != null) {
                log.debug("测试查询视频播放量排行接口成功,{}", JSON.toJSONString(vodQueryVideoPlaybackRankingResponse));
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
     * 测试查询播放域名统计数据接口
     * 约束：2、查询播放域名统计数据接口
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
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryPlayDomainNameStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryPlayDomainNameStatistics(
                    vodQueryPlayDomainNameStatisticsRequest);
            Assert.assertNotNull(vodQueryPlayDomainNameStatisticsResponseList);
            if (vodQueryPlayDomainNameStatisticsResponseList != null) {
                log.debug("测试查询播放域名统计数据接口成功,{}", JSON.toJSONString(vodQueryPlayDomainNameStatisticsResponseList));
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
}
