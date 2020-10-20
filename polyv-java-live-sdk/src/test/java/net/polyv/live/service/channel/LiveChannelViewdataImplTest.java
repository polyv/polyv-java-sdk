package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.channel.viewdata.LiveChannelMaxHistoryConcurrentRequest;
import net.polyv.live.entity.channel.viewdata.LiveChannelViewerConcurrenceRequest;
import net.polyv.live.entity.channel.viewdata.LiveChannelViewerConcurrenceResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelMicRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelMicResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelSummaryRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelSummaryResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewerCountRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewerCountResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewlogRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewlogResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.channel.impl.LiveChannelViewdataServiceImpl;

/**
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelViewdataImplTest extends BaseTest {
    
    /**
     * 测试获取频道一定时间范围之内的历史最高并发人数,粒度可以支持到分钟
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testMaxChannelHistoryConcurrent() throws IOException, NoSuchAlgorithmException {
        int channelId = 1951952;
        long nowTime = System.currentTimeMillis();
        long startTime = nowTime - 30 * 24 * 60 * 60 * 1000l;
        LiveChannelMaxHistoryConcurrentRequest liveChannelMaxHistoryConcurrentRequest =
                new LiveChannelMaxHistoryConcurrentRequest();
        liveChannelMaxHistoryConcurrentRequest.setChannelId(channelId).setStartTime(startTime).setEndTime(nowTime);
        Integer liveChannelMaxHistoryConcurrentResponse =
                new LiveChannelViewdataServiceImpl().maxChannelHistoryConcurrent(
                liveChannelMaxHistoryConcurrentRequest);
        Assert.assertNotNull(liveChannelMaxHistoryConcurrentResponse);
        if (liveChannelMaxHistoryConcurrentResponse != null) {
            //to do something ......
            log.debug("测试获取频道一定时间范围之内的历史最高并发人数成功，并发人数为：{}", liveChannelMaxHistoryConcurrentResponse);
        }
    }
    
    /**
     * 测试分页获取连麦情况使用详情
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelMic() throws IOException, NoSuchAlgorithmException {
        LiveListChannelMicRequest liveListChannelMicRequest = new LiveListChannelMicRequest();
        liveListChannelMicRequest.setChannelIds("1951952,1958888").setStartDay("2020-01-01").setEndDay("2020-11-11");
        LiveListChannelMicResponse liveListChannelMicResponse = new LiveChannelViewdataServiceImpl().listChannelMic(
                liveListChannelMicRequest);
        Assert.assertNotNull(liveListChannelMicResponse);
        if (liveListChannelMicResponse != null) {
            //to do something ......
            log.debug("测试分页获取连麦情况使用详情成功，{}", JSON.toJSONString(liveListChannelMicResponse));
        }
    }
    
    /**
     * 测试分页查询频道观看日志
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelViewlog() throws IOException, NoSuchAlgorithmException {
        LiveListChannelViewlogRequest liveListChannelViewlogRequest = new LiveListChannelViewlogRequest();
        liveListChannelViewlogRequest.setChannelId(1951952).setCurrentDay("2020-10-14");
        LiveListChannelViewlogResponse liveListChannelViewlogResponse =
                new LiveChannelViewdataServiceImpl().listChannelViewlog(
                liveListChannelViewlogRequest);
        Assert.assertNotNull(liveListChannelViewlogResponse);
        if (liveListChannelViewlogResponse != null) {
            //to do something ......
            log.debug("测试分页查询频道观看日志成功，{}", JSON.toJSONString(liveListChannelViewlogResponse));
        }
    }
    
    /**
     * 测试查询多个频道汇总的统计数据
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelSummary() throws IOException, NoSuchAlgorithmException {
        LiveListChannelSummaryRequest liveListChannelSummaryRequest = new LiveListChannelSummaryRequest();
        liveListChannelSummaryRequest.setStartDate("2020-01-01")
                .setEndDate("2020-11-11")
                .setChannelIds("1951952,1958888");
        LiveListChannelSummaryResponse liveListChannelSummaryResponse =
                new LiveChannelViewdataServiceImpl().listChannelSummary(
                liveListChannelSummaryRequest);
        Assert.assertNotNull(liveListChannelSummaryResponse);
        if (liveListChannelSummaryResponse != null) {
            //to do something ......
            log.debug("测试查询多个频道汇总的统计数据成功，{}", JSON.toJSONString(liveListChannelSummaryResponse));
        }
    }
    
    /**
     * 测试查询多个频道的实时在线人数
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelViewerCount() throws IOException, NoSuchAlgorithmException {
        LiveListChannelViewerCountRequest liveListChannelViewerCountRequest = new LiveListChannelViewerCountRequest();
        liveListChannelViewerCountRequest.setChannelIds("1951952,1958888");
        LiveListChannelViewerCountResponse liveListChannelViewerCountResponse =
                new LiveChannelViewdataServiceImpl().listChannelViewerCount(
                liveListChannelViewerCountRequest);
        Assert.assertNotNull(liveListChannelViewerCountResponse);
        if (liveListChannelViewerCountResponse != null) {
            //to do something ......
            log.debug("测试查询多个频道的实时在线人数成功，{}", JSON.toJSONString(liveListChannelViewerCountResponse));
        }
    }
    
    /**
     * 测试查询频道的历史并发人数
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelViewerConcurrence() throws IOException, NoSuchAlgorithmException {
        Integer channelId = createChannel();
        LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest =
                new LiveChannelViewerConcurrenceRequest();
        liveChannelViewerConcurrenceRequest.setChannelId(channelId).setStartDate("2020-10-01").setEndDate("2020-11-11");
        LiveChannelViewerConcurrenceResponse liveChannelViewerConcurrenceResponse = new LiveChannelViewdataServiceImpl()
                .channelViewerConcurrence(liveChannelViewerConcurrenceRequest);
        Assert.assertNotNull(liveChannelViewerConcurrenceResponse);
        if (liveChannelViewerConcurrenceResponse != null) {
            //to do something ......
            log.debug("测试查询频道的历史并发人数成功，{}", JSON.toJSONString(liveChannelViewerConcurrenceResponse));
        }
    }
    
}
