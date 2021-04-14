package net.polyv.live.v1.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.channel.viewdata.LiveChannelMaxHistoryConcurrentRequest;
import net.polyv.live.v1.entity.channel.viewdata.LiveChannelViewerConcurrenceRequest;
import net.polyv.live.v1.entity.channel.viewdata.LiveChannelViewerConcurrenceResponse;
import net.polyv.live.v1.entity.channel.viewdata.LiveListChannelMicRequest;
import net.polyv.live.v1.entity.channel.viewdata.LiveListChannelMicResponse;
import net.polyv.live.v1.entity.channel.viewdata.LiveListChannelSummaryRequest;
import net.polyv.live.v1.entity.channel.viewdata.LiveListChannelSummaryResponse;
import net.polyv.live.v1.entity.channel.viewdata.LiveListChannelViewerCountRequest;
import net.polyv.live.v1.entity.channel.viewdata.LiveListChannelViewerCountResponse;
import net.polyv.live.v1.entity.channel.viewdata.LiveListChannelViewlogRequest;
import net.polyv.live.v1.entity.channel.viewdata.LiveListChannelViewlogResponse;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.channel.impl.LiveChannelViewdataServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 观看数据
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelViewdataImplTest extends BaseTest {
    
    /**
     * 测试获取频道一定时间范围之内的历史最高并发人数
     * 描述：获取频道一定时间范围之内的历史最高并发人数，粒度可以支持到分钟
     * 返回：返回时间区间内的最高并发人数
     * API地址：CHANNEL_MAX_HISTORY_CONCURRENT_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetMaxChannelHistoryConcurrent() throws Exception, NoSuchAlgorithmException {
        LiveChannelMaxHistoryConcurrentRequest liveChannelMaxHistoryConcurrentRequest =
                new LiveChannelMaxHistoryConcurrentRequest();
        Integer liveChannelMaxHistoryConcurrentResponse;
        try {
            long nowTime = System.currentTimeMillis();
            long startTime = nowTime - 30 * 24 * 60 * 60 * 1000l;
            liveChannelMaxHistoryConcurrentRequest.setChannelId(createChannel())
                    .setStartTime(super.getDate(startTime))
                    .setEndTime(super.getDate(nowTime));
            liveChannelMaxHistoryConcurrentResponse = new LiveChannelViewdataServiceImpl().getMaxChannelHistoryConcurrent(
                    liveChannelMaxHistoryConcurrentRequest);
            Assert.assertNotNull(liveChannelMaxHistoryConcurrentResponse);
            if (liveChannelMaxHistoryConcurrentResponse != null) {
                //to do something ......
                log.debug("测试获取频道一定时间范围之内的历史最高并发人数成功，并发人数为：{}", liveChannelMaxHistoryConcurrentResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试分页获取连麦情况使用详情
     * API地址：CHANNEL_MIC_LIST_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelMic() throws Exception, NoSuchAlgorithmException {
        LiveListChannelMicRequest liveListChannelMicRequest = new LiveListChannelMicRequest();
        LiveListChannelMicResponse liveListChannelMicResponse;
        try {
            liveListChannelMicRequest.setChannelIds("1951952,1958888")
                    .setStartDay(getDate(2020, 1, 1))
                    .setEndDay(getDate(2020, 11, 11));
            liveListChannelMicResponse = new LiveChannelViewdataServiceImpl().listChannelMic(liveListChannelMicRequest);
            Assert.assertNotNull(liveListChannelMicResponse);
            if (liveListChannelMicResponse != null) {
                //to do something ......
                log.debug("测试分页获取连麦情况使用详情成功，{}", JSON.toJSONString(liveListChannelMicResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试分页查询频道观看日志
     * API地址：CHANNEL_VIEW_LOGS_GET_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelViewlog() throws Exception, NoSuchAlgorithmException {
        LiveListChannelViewlogRequest liveListChannelViewlogRequest = new LiveListChannelViewlogRequest();
        LiveListChannelViewlogResponse liveListChannelViewlogResponse;
        try {
            liveListChannelViewlogRequest.setChannelId(createChannel())
                    .setCurrentDay(getDate(2020, 11, 3));
            liveListChannelViewlogResponse = new LiveChannelViewdataServiceImpl().listChannelViewlog(
                    liveListChannelViewlogRequest);
            Assert.assertNotNull(liveListChannelViewlogResponse);
            if (liveListChannelViewlogResponse != null) {
                //to do something ......
                log.debug("测试分页查询频道观看日志成功，{}", JSON.toJSONString(liveListChannelViewlogResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询多个频道汇总的统计数据
     * API地址：CHANNEL_SUMMARY_LIST_GET_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelSummary() throws Exception, NoSuchAlgorithmException {
        LiveListChannelSummaryRequest liveListChannelSummaryRequest = new LiveListChannelSummaryRequest();
        LiveListChannelSummaryResponse liveListChannelSummaryResponse;
        try {
            liveListChannelSummaryRequest.setStartDate(getDate(2020, 01, 01))
                    .setEndDate(getDate(2020, 11, 11))
                    .setChannelIds("1951952,1958888");
            liveListChannelSummaryResponse = new LiveChannelViewdataServiceImpl().listChannelSummary(
                    liveListChannelSummaryRequest);
            Assert.assertNotNull(liveListChannelSummaryResponse);
            if (liveListChannelSummaryResponse != null) {
                //to do something ......
                log.debug("测试查询多个频道汇总的统计数据成功，{}", JSON.toJSONString(liveListChannelSummaryResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询多个频道的实时在线人数
     * API地址：CHANNEL_REAL_TIME_VIEWERS_GET_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelViewerCount() throws Exception, NoSuchAlgorithmException {
        LiveListChannelViewerCountRequest liveListChannelViewerCountRequest = new LiveListChannelViewerCountRequest();
        LiveListChannelViewerCountResponse liveListChannelViewerCountResponse;
        try {
            liveListChannelViewerCountRequest.setChannelIds("1951952,1958888");
            liveListChannelViewerCountResponse = new LiveChannelViewdataServiceImpl().listChannelViewerCount(
                    liveListChannelViewerCountRequest);
            Assert.assertNotNull(liveListChannelViewerCountResponse);
            if (liveListChannelViewerCountResponse != null) {
                //to do something ......
                log.debug("测试查询多个频道的实时在线人数成功，{}", JSON.toJSONString(liveListChannelViewerCountResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道的历史并发人数
     * API地址：CHANNEL_VIEWER_CONCURRENCE_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelViewerConcurrence() throws Exception, NoSuchAlgorithmException {
        LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest =
                new LiveChannelViewerConcurrenceRequest();
        LiveChannelViewerConcurrenceResponse liveChannelViewerConcurrenceResponse;
        try {
            liveChannelViewerConcurrenceRequest.setChannelId(createChannel())
                    .setStartDate(getDate(2020, 10, 01))
                    .setEndDate(getDate(2020,11,11));
            liveChannelViewerConcurrenceResponse = new LiveChannelViewdataServiceImpl().getChannelViewerConcurrence(
                    liveChannelViewerConcurrenceRequest);
            Assert.assertNotNull(liveChannelViewerConcurrenceResponse);
            if (liveChannelViewerConcurrenceResponse != null) {
                //to do something ......
                log.debug("测试查询频道的历史并发人数成功，{}", JSON.toJSONString(liveChannelViewerConcurrenceResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
