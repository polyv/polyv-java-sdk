package net.polyv.live.service.channel;

import java.io.IOException;

import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;

/**
 * @author: thomas
 * @date: 2020/9/22
 **/
public interface ILiveChannelService {
    
    LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest) throws IOException;
    
}
