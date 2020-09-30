package net.polyv.live.action;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.polyv.live.entity.channel.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

/**
 * 渠道管理测试类接入DEMO
 * @author: thomas
 **/
@Controller
@RequestMapping("/channel")
@Api(value="直播频道管理",tags="直播频道管理")
public class ChannelAction {
    
    /**
     * 创建渠道demo
     * @param liveChannelRequest
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "创建一个直播频道，成功创建返回该频道的基本信息",notes = "调用示例：参考polyv-java-live-sdk单元测试ChannelTest.testCreateChannel()方法。<a target=\"_blank\"  href=\"http://47.115.173.234:3000/#/channel/channelManager?id=sdk%e9%a2%91%e9%81%93%e6%93%8d%e4%bd%9c\">频道创建</a>    ")
    @PostMapping("/createChannel")
    @ResponseBody
    public LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest) throws IOException {
        LiveChannelResponse liveChannelResponse =  new LiveChannelServiceImpl().createChannel(liveChannelRequest);
        return  liveChannelResponse;
    }

    @ApiOperation(value = "设置频道详情，成功时为true，错误时为\"\"",notes = "调用示例：参考polyv-java-live-sdk单元测试ChannelTest.testCreateChannel()方法。<a target=\"_blank\"  href=\"http://47.115.173.234:3000/#/channel/channelManager?id=sdk%e9%a2%91%e9%81%93%e6%93%8d%e4%bd%9c\">频道创建</a>    ")
    @PostMapping("/updateChannelDetail")
    @ResponseBody
    public String updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest) throws IOException {
        return new LiveChannelServiceImpl().updateChannelDetail(liveChannelDetailRequest);
    }
}
