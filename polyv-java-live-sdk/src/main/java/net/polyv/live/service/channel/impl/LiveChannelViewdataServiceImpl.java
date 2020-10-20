package net.polyv.live.service.channel.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
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
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.channel.ILiveChannelViewdataService;

/**
 * 直播观看数据实现类
 * @author: sadboy
 **/
public class LiveChannelViewdataServiceImpl extends LiveBaseService implements ILiveChannelViewdataService {
    
    /**
     * 获取频道一定时间范围之内的历史最高并发人数,粒度可以支持到分钟
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/gksj/get-max-history-concurrent/
     * @param liveChannelMaxHistoryConcurrentRequest 获取频道一定时间范围之内的历史最高并发人数请求实体
     * @return 获取频道一定时间范围之内的历史最高并发人数返回实体，返回并发人数，如：100
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Integer maxChannelHistoryConcurrent(
            LiveChannelMaxHistoryConcurrentRequest liveChannelMaxHistoryConcurrentRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_MAX_HISTORY_CONCURRENT_URL;
        Integer liveChannelMaxHistoryConcurrentResponse = (Integer) this.baseGet(url,
                liveChannelMaxHistoryConcurrentRequest, Integer.class);
        return liveChannelMaxHistoryConcurrentResponse;
    }
    
    /**
     * 分页获取连麦情况使用详情
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/gksj/mic-detail-list/
     * @param liveListChannelMicRequest 分页获取连麦情况使用详情请求实体
     * @return 分页获取连麦情况使用详情返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelMicResponse listChannelMic(LiveListChannelMicRequest liveListChannelMicRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_MIC_LIST_URL;
        LiveListChannelMicResponse liveListChannelMicResponse = this.baseGet(url, liveListChannelMicRequest,
                LiveListChannelMicResponse.class);
        return liveListChannelMicResponse;
    }
    
    /**
     * 分页查询频道观看日志
     * 1. 如果查询一段时间的记录，可以传：startTime、endTime （startTime和endTime 必须在同一个月），如果查询某天的记录，则传currentDay；
     * 2. startTime、endTime 和 currentDay不能都不传；
     * 3. currentDay与startTime、endTime 同时传将使用currentDay的值。
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/viewlog-page/
     * @param liveChannelViewlogRequest 分页查询频道观看日志请求实体
     * @return 分页查询频道观看日志返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelViewlogResponse listChannelViewlog(LiveListChannelViewlogRequest liveChannelViewlogRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_VIEW_LOGS_GET_URL, liveChannelViewlogRequest.getChannelId());
        LiveListChannelViewlogResponse liveListChannelViewlogResponse = this.baseGet(url, liveChannelViewlogRequest,
                LiveListChannelViewlogResponse.class);
        return liveListChannelViewlogResponse;
    }
    
    /**
     * 查询多个频道汇总的统计数据
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/channel_play_summary/
     * @param liveListChannelSummaryRequest 查询多个频道汇总的统计数据请求实体
     * @return 查询多个频道汇总的统计数据返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelSummaryResponse listChannelSummary(
            LiveListChannelSummaryRequest liveListChannelSummaryRequest) throws IOException, NoSuchAlgorithmException {
        liveListChannelSummaryRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_SUMMARY_LIST_GET_URL,
                liveListChannelSummaryRequest.getUserId());
        LiveListChannelSummaryResponse.ChannelSummary[] channelSummaries = this.basePost(url,
                liveListChannelSummaryRequest, LiveListChannelSummaryResponse.ChannelSummary[].class);
        channelSummaries =
                channelSummaries == null ? new LiveListChannelSummaryResponse.ChannelSummary[]{} : channelSummaries;
        LiveListChannelSummaryResponse liveListChannelSummaryResponse = new LiveListChannelSummaryResponse();
        liveListChannelSummaryResponse.setChannelSummarys(Arrays.asList(channelSummaries));
        return liveListChannelSummaryResponse;
    }
    
    /**
     * 查询多个频道的实时在线人数
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/get-realtime-viewers/
     * @param liveListChannelViewerCountRequest 查询多个频道的实时在线人数请求实体
     * @return 查询多个频道的实时在线人数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelViewerCountResponse listChannelViewerCount(
            LiveListChannelViewerCountRequest liveListChannelViewerCountRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_REAL_TIME_VIEWERS_GET_URL;
        LiveListChannelViewerCountResponse.ChannelViewerCount[] channelViewerCounts = this.baseGet(url,
                liveListChannelViewerCountRequest, LiveListChannelViewerCountResponse.ChannelViewerCount[].class);
        channelViewerCounts = channelViewerCounts == null ?
                channelViewerCounts = new LiveListChannelViewerCountResponse.ChannelViewerCount[]{} :
                channelViewerCounts;
        LiveListChannelViewerCountResponse liveListChannelViewerCountResponse =
                new LiveListChannelViewerCountResponse();
        liveListChannelViewerCountResponse.setChannelViewerCounts(Arrays.asList(channelViewerCounts));
        return liveListChannelViewerCountResponse;
    }
    
    /**
     * 查询频道的历史并发人数
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/gksj/concurrency/
     * @param liveChannelViewerConcurrenceRequest 查询频道的历史并发人数请求实体
     * @return 查询频道的历史并发人数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelViewerConcurrenceResponse channelViewerConcurrence(
            LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_VIEWER_CONCURRENCE_URL;
        LiveChannelViewerConcurrenceResponse.ChannelViewerConcurrence[] channelViewerConcurrences = this.baseGet(url,
                liveChannelViewerConcurrenceRequest,
                LiveChannelViewerConcurrenceResponse.ChannelViewerConcurrence[].class);
        channelViewerConcurrences = channelViewerConcurrences == null ?
                new LiveChannelViewerConcurrenceResponse.ChannelViewerConcurrence[]{} : channelViewerConcurrences;
        LiveChannelViewerConcurrenceResponse liveChannelViewerConcurrenceResponse =
                new LiveChannelViewerConcurrenceResponse();
        liveChannelViewerConcurrenceResponse.setChannelViewerConcurrences(Arrays.asList(channelViewerConcurrences));
        return liveChannelViewerConcurrenceResponse;
    }
    
}
