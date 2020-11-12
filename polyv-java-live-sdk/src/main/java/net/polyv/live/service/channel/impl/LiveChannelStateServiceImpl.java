package net.polyv.live.service.channel.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.channel.state.LiveChannelStreamEndRequest;
import net.polyv.live.entity.channel.state.LiveChannelStreamInfoRequest;
import net.polyv.live.entity.channel.state.LiveChannelStreamInfoResponse;
import net.polyv.live.entity.channel.state.LiveChannelStreamLiveRequest;
import net.polyv.live.entity.channel.state.LiveChannelStreamStatusResponse;
import net.polyv.live.entity.channel.state.LiveCutoffChannelStreamRequest;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusRequest;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusResponse;
import net.polyv.live.entity.channel.state.LiveResumeChannelStreamRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.channel.ILiveChannelStateService;

/**
 * 直播直播状态实现类
 * @author: sadboy
 **/
public class LiveChannelStateServiceImpl extends LiveBaseService implements ILiveChannelStateService {
    
    /**
     * 恢复直播频道推流
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/resume/
     * @param liveResumeChannelStreamRequest 恢复直播频道推流请求体
     * @return 恢复直播频道推流返回体，success为成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean resumeChannelStream(LiveResumeChannelStreamRequest liveResumeChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException {
        liveResumeChannelStreamRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_STREAM_RESUME_URL,
                liveResumeChannelStreamRequest.getChannelId());
        String liveResumeChannelStreamResponse = this.basePost(url, liveResumeChannelStreamRequest, String.class);
        return "success".equals(liveResumeChannelStreamResponse);
    }
    
    
    /**
     * 禁止直播频道推流
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/hqzbxx/banpush/
     * @param liveCutoffChannelStreamRequest 禁止直播频道推流请求实体
     * @return 禁止直播频道推流返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean cutoffChannelStream(LiveCutoffChannelStreamRequest liveCutoffChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException {
        liveCutoffChannelStreamRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_STREAM_CUTOFF_URL,
                liveCutoffChannelStreamRequest.getChannelId());
        String liveCutoffChannelStreamResponse = this.basePost(url, liveCutoffChannelStreamRequest, String.class);
        return "success".equals(liveCutoffChannelStreamResponse);
    }
    
    /**
     * 批量查询频道直播流状态
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/live-status/
     * @param liveListChannelStreamStatusRequest 批量查询频道直播流状态请求实体
     * @return 批量查询频道直播流状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelStreamStatusResponse listChannelLiveStream(
            LiveListChannelStreamStatusRequest liveListChannelStreamStatusRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIVE_STREAM_STATUS_LIST_URL;
        LiveChannelStreamStatusResponse[] liveChannelStreamStatusResponses = this.basePost(url,
                liveListChannelStreamStatusRequest, LiveChannelStreamStatusResponse[].class);
        if (liveChannelStreamStatusResponses == null) {
            liveChannelStreamStatusResponses = new LiveChannelStreamStatusResponse[]{};
        }
        LiveListChannelStreamStatusResponse liveListChannelStreamStatusResponse =
                new LiveListChannelStreamStatusResponse();
        liveListChannelStreamStatusResponse.setChannelInfo(Arrays.asList(liveChannelStreamStatusResponses));
        return liveListChannelStreamStatusResponse;
    }
    
    /**
     * 查询频道实时推流信息(讲师未进入直播间或未开启上课等情况，将抛出"channel status not live"异常)，
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/hqzbxx/get-stream-info/
     * @param liveChannelStreamInfoRequest 查询频道实时推流信息请求实体
     * @return 查询频道实时推流信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelStreamInfoResponse channelStreamInfo(LiveChannelStreamInfoRequest liveChannelStreamInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIVE_STREAM_INFO_URL;
        LiveChannelStreamInfoResponse liveChannelStreamInfoResponse = this.baseGet(url, liveChannelStreamInfoRequest,
                LiveChannelStreamInfoResponse.class);
        return liveChannelStreamInfoResponse;
    }
    
    /**
     * 设置频道流状态为直播中
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/live/
     * @param liveChannelStreamLiveRequest 设置频道流状态为直播中请求实体
     * @return 设置频道流状态为直播中返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setChannelStreamLive(LiveChannelStreamLiveRequest liveChannelStreamLiveRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelStreamLiveRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_STREAM_LIVE_URL,liveChannelStreamLiveRequest.getChannelId());
        String liveChannelStreamLiveResponse = this.basePost(url,liveChannelStreamLiveRequest,String.class);
        return "success".equals(liveChannelStreamLiveResponse);
    }
    
    /**
     * 设置频道为无直播状态
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/set-channal-status-end/
     * @param liveChannelStreamEndRequest 设置频道为无直播状态设置频道为无直播状态请求实体
     * @return 设置频道为无直播状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean channelStreamEnd(LiveChannelStreamEndRequest liveChannelStreamEndRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelStreamEndRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_STREAM_END_URL,liveChannelStreamEndRequest.getChannelId());
        String liveChannelStreamEndResponse = this.basePost(url,liveChannelStreamEndRequest,String.class);
        return "success".equals(liveChannelStreamEndResponse);
    }
    
}
