package net.polyv.live.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.polyv.live.entity.channel.operate.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInitRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInitResponse;
import net.polyv.live.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.entity.channel.operate.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordResponse;
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
    public LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        LiveChannelResponse liveChannelResponse =  new LiveChannelServiceImpl().createChannel(liveChannelRequest);
        return  liveChannelResponse;
    }
    
    /**
     * 批量创建频道demo
     * @param liveCreateChannelListRequest
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "批量创建直播频道，成功创建返回所创建频道的基本信息。留意，如果响应失败，则表示全部频道都失败，不会有部份成功、部份失败的结果",notes = "调用示例：参考polyv-java-live-sdk单元测试ChannelTest.testCreateChannelList()方法。<a target=\"_blank\"  href=\"http://47.115.173.234:3000/#/channel/channelManager?id=sdk%e9%a2%91%e9%81%93%e6%93%8d%e4%bd%9c\">频道创建</a>    ")
    @PostMapping("/createChannelList")
    @ResponseBody
    public LiveCreateChannelListResponse createChannelList(LiveCreateChannelListRequest liveCreateChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelListResponse liveCreateChannelListResponse =  new LiveChannelServiceImpl().createChannelList(liveCreateChannelListRequest);
        return  liveCreateChannelListResponse;
    }
    
    /**
     * 创建并初始化频道demo
     * @param liveChannelInitRequest
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "创建并初始化频道，用于创建频道并进行相关设置。创建成功返回频道信息。",notes = "调用示例：参考polyv-java-live-sdk单元测试ChannelTest.testCreateChannelInitCode()等方法，可设置不同的观看条件。<a target=\"_blank\"  href=\"http://47.115.173.234:3000/#/channel/channelManager?id=sdk%e9%a2%91%e9%81%93%e6%93%8d%e4%bd%9c\">创建并初始化频道</a>    ")
    @PostMapping("/createChannelInit")
    @ResponseBody
    public LiveChannelInitResponse createChannelInit(@RequestBody LiveChannelInitRequest liveChannelInitRequest)
            throws IOException, NoSuchAlgorithmException {
        LiveChannelInitResponse liveChannelInitResponse =  new LiveChannelServiceImpl().createChannelInit(liveChannelInitRequest);
        return  liveChannelInitResponse;
    }
    
    @ApiOperation(value = "设置频道详情，成功时为true，错误时为\"\"",notes = "调用示例：参考polyv-java-live-sdk单元测试ChannelTest.testCreateChannel()方法。<a target=\"_blank\"  href=\"http://47.115.173.234:3000/#/channel/channelManager?id=sdk%e9%a2%91%e9%81%93%e6%93%8d%e4%bd%9c\">频道创建</a>    ")
    @PostMapping("/updateChannelDetail")
    @ResponseBody
    public String updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest)
            throws IOException, NoSuchAlgorithmException {
        return new LiveChannelServiceImpl().updateChannelDetail(liveChannelDetailRequest);
    }
    
    @ApiOperation(value = "查询课件重制任务列表，成功时返回分页的课件重制任务列表",notes = "调用示例：参考polyv-java-live-sdk单元测试ChannelTest.testListPPTRecord()方法。<a target=\"_blank\"  href=\"http://47.115.173.234:3000/#/channel/channelManager?id=sdk%e9%a2%91%e9%81%93%e6%93%8d%e4%bd%9c\">频道创建</a>    ")
    @PostMapping("/listPPTRecord")
    @ResponseBody
    public LiveListChannelPPTRecordResponse listPPTRecord(LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException {
        return new LiveChannelServiceImpl().listPPTRecord(liveListChannelPPTRecordRequest);
    }
}
