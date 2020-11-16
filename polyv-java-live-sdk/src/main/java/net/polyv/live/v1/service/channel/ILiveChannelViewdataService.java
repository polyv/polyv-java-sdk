package net.polyv.live.v1.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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

/**
 * 直播观看数据接口
 * @author: sadboy
 **/
public interface ILiveChannelViewdataService {
    
    /**
     * 获取频道一定时间范围之内的历史最高并发人数
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/gksj/get-max-history-concurrent/
     * @param liveChannelMaxHistoryConcurrentRequest 获取频道一定时间范围之内的历史最高并发人数请求实体
     * @return 获取频道一定时间范围之内的历史最高并发人数返回实体，返回并发人数，如：100
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Integer getMaxChannelHistoryConcurrent(LiveChannelMaxHistoryConcurrentRequest liveChannelMaxHistoryConcurrentRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 分页获取连麦情况使用详情
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/gksj/mic-detail-list/
     * @param liveListChannelMicRequest 分页获取连麦情况使用详情请求实体
     * @return 分页获取连麦情况使用详情返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelMicResponse listChannelMic(LiveListChannelMicRequest liveListChannelMicRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 分页查询频道观看日志
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/viewlog-page/
     * @param liveChannelViewlogRequest 分页查询频道观看日志请求实体
     * @return 分页查询频道观看日志返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelViewlogResponse listChannelViewlog(LiveListChannelViewlogRequest liveChannelViewlogRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询多个频道汇总的统计数据
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/channel_play_summary/
     * @param liveListChannelSummaryRequest 查询多个频道汇总的统计数据请求实体
     * @return 查询多个频道汇总的统计数据返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelSummaryResponse listChannelSummary(LiveListChannelSummaryRequest liveListChannelSummaryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询多个频道的实时在线人数
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/get-realtime-viewers/
     * @param liveListChannelViewerCountRequest 查询多个频道的实时在线人数请求实体
     * @return 查询多个频道的实时在线人数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelViewerCountResponse listChannelViewerCount(
            LiveListChannelViewerCountRequest liveListChannelViewerCountRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道的历史并发人数
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/gksj/concurrency/
     * @param liveChannelViewerConcurrenceRequest 查询频道的历史并发人数请求实体
     * @return 查询频道的历史并发人数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelViewerConcurrenceResponse getChannelViewerConcurrence(
            LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
