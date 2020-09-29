package net.polyv.live.service.channel;

import java.io.IOException;

import net.polyv.live.entity.channel.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;

/**
 * 直播频道管理
 * @author: thomas
 * @date: 2020/9/22
 **/
public interface ILiveChannelService {
    
    /**
     * 直播频道创建
     * @param liveChannelRequest  直播频道请求实体
     * @return
     * @throws IOException
     */
    LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest) throws IOException;

    /**
     * 设置直播频道详情
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return
     * @throws IOException
     */
    String updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest) throws IOException;

}
