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
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

/**
 * 渠道管理测试类接入DEMO
 * @author: thomas
 * @date: 2020/9/24
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
    @ApiOperation(value = "创建一个直播频道，成功创建返回该频道的基本信息",notes = "调用示例：参考polyv-java-live-sdk单元测试ChannelTest.testCreateChannel()方法。<a target=\"_blank\"  href=\"http://47.115.173.234:3000/#/?id=_25-%e6%94%af%e6%8c%81%e5%a4%9a%e5%b9%b3%e5%8f%b0%e7%9b%b4%e6%92%ad\">ChannelTest.testCreateChannel()</a>    ")
    @PostMapping("/createChannel")
    @ResponseBody
    public LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest) throws IOException {
        LiveChannelResponse liveChannelResponse =  new LiveChannelServiceImpl().createChannel(liveChannelRequest);
        return  liveChannelResponse;
    }
    
    
}
