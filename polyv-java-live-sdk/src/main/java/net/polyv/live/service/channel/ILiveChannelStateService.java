package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.channel.state.LiveChannelStreamInfoRequest;
import net.polyv.live.entity.channel.state.LiveChannelStreamInfoResponse;
import net.polyv.live.entity.channel.state.LiveCutoffChannelStreamRequest;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusRequest;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusResponse;
import net.polyv.live.entity.channel.state.LiveResumeChannelStreamRequest;

/**
 * 直播直播状态接口
 * @author: sadboy
 **/
public interface ILiveChannelStateService {
    
    /**
     * 恢复直播频道推流
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/resume/
     * @param liveResumeChannelStreamRequest 恢复直播频道推流请求体
     * @return 恢复直播频道推流返回体，success为成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean resumeChannelStream(LiveResumeChannelStreamRequest liveResumeChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 禁止直播频道推流
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/hqzbxx/banpush/
     * @param liveCutoffChannelStreamRequest 禁止直播频道推流请求实体
     * @return 禁止直播频道推流返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean cutoffChannelStream(LiveCutoffChannelStreamRequest liveCutoffChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量查询频道直播流状态
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/live-status/
     * @param liveListChannelStreamStatusRequest 批量查询频道直播流状态请求实体
     * @return 批量查询频道直播流状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelStreamStatusResponse listChannelLiveStream(
            LiveListChannelStreamStatusRequest liveListChannelStreamStatusRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道实时推流信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/hqzbxx/get-stream-info/
     * @param liveChannelStreamInfoRequest 查询频道实时推流信息请求实体
     * @return 查询频道实时推流信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelStreamInfoResponse channelStreamInfo(LiveChannelStreamInfoRequest liveChannelStreamInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
