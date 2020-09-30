package net.polyv.live.service.channel;

import java.io.IOException;

import net.polyv.live.entity.channel.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;

/**
 * 直播频道管理
 * @author: thomas
 
 **/
public interface ILiveChannelService {
    
    /**
     * 直播频道创建
     * @param liveChannelRequest  直播频道请求实体
     * @return 频道数据
     * @throws IOException 客户端和服务器读写异常
     */
    LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest) throws IOException;

    /**
     * 设置直播频道详情
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return  频道详情
     * @throws IOException 异常
     */
    String updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest) throws IOException;

}
