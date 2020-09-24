package net.polyv.live.action;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.service.channel.ILiveChannelService;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

/**
 * @author: thomas
 * @date: 2020/9/24
 **/
@Controller
@RequestMapping("/channel")
@Api(value="直播频道管理",tags="直播频道管理")
public class ChannelAction {
    
    @ApiOperation(value = "创建一个直播频道，返回该频道的基本信息" )
    @PostMapping("/createChannel")
    @ResponseBody

    public LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest) throws IOException {
        ILiveChannelService liveChannelService = new LiveChannelServiceImpl();
        
        LiveChannelResponse liveChannelResponse = liveChannelService.createChannel(liveChannelRequest);
        return  liveChannelResponse;
    }
}
