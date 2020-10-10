package net.polyv.channel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.channel.LiveChannelDetailRequest;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.entity.channel.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.dto.LiveChannelBasicDTO;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

/**
 * @author: thomas
 **/
@Slf4j
public class ChannelTest {
    /**
     * 系统账号密钥配置
     */
    public ChannelTest() {
        
        
        String appId = "frlr1zazn3";
        String appSecret = "5d5ade8f71f24bb9a2d1176cd607dd17";
        String userId = "1b448be323";
        LiveGlobalConfig.init(appId, userId, appSecret);
        System.out.println("--初始化完成--");
    }
    
    /**
     * 测试创建频道
     * @throws IOException
     */
    @Test
    public void testCreateChannel() throws IOException {
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        liveChannelRequest.setName("Spring 知识精讲")
                .setChannelPasswd("666888")
                .setRequestId("2860257a405447e1bbbe9161da2dee72");
        LiveChannelResponse liveChannelResponse = new LiveChannelServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        if (liveChannelResponse != null) {
            //to do something ......
            log.debug("频道创建成功" + JSON.toJSONString(liveChannelResponse));
        }
    }
    
    /**
     * 测试批量创建频道
     * @throws IOException
     */
    @Test
    public void testCreateChannelList() throws IOException {
        LiveCreateChannelListRequest liveCreateChannelListRequest = new LiveCreateChannelListRequest();
        List<LiveChannelBasicDTO> channels = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            LiveChannelBasicDTO liveChannel = new LiveChannelBasicDTO();
            liveChannel.setName("批量创建" + i)
                    .setChannelPasswd("123456" + i)
                    .setCourseId("c" + i)
                    .setAutoPlay(1)
                    .setPlayerColor("#666666")
                    .setScene(LiveConstant.SceneType.ALONE.getDesc())
                    .setCategoryId(340019);
            channels.add(liveChannel);
        }
        liveCreateChannelListRequest.setChannels(channels).setRequestId("123456");
        LiveCreateChannelListResponse liveCreateChannelListResponse = new LiveChannelServiceImpl().createChannelList(liveCreateChannelListRequest);
        Assert.assertNotNull(liveCreateChannelListResponse);
        if (liveCreateChannelListResponse != null) {
            //to do something ......
            log.debug("频道批量创建成功" + JSON.toJSONString(liveCreateChannelListResponse));
        }
    }
    
    /**
     * 测试设置频道详情：修改密码功能
     * @throws IOException
     */
    @Test
    public void testUpdateChannelPassword() throws IOException {
        Integer channelId = 1938888;
        String newPassword = "1234567";
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("channelPasswd")
                .setValue(newPassword)
                .setRequestId("2860257a405447e1bbbe9161da2dee73");
        String liveChannelDetailResponse = new LiveChannelServiceImpl().updateChannelDetail(liveChannelDetailRequest);
        Assert.assertNotNull(liveChannelDetailResponse);
        if ("true".equals(liveChannelDetailResponse)) {
            //to do something ......
            log.debug(String.format("频道%s修改密码为%s成功%s", channelId, newPassword, liveChannelDetailResponse));
        }
    }
    
    /**
     * TODO 频道号动态获取，scene写成枚举类型
     * 测试设置频道详情：修改scene字段
     * @throws IOException
     */
    @Test
    public void testUpdateChannelScene() throws IOException {
        Integer channelId = 1938888;
        String value = "alone";
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("scene")
                .setValue(value)
                .setRequestId("2860257a405447e1bbbe9161da2dee74");
        String liveChannelDetailResponse = new LiveChannelServiceImpl().updateChannelDetail(liveChannelDetailRequest);
        Assert.assertNotNull(liveChannelDetailResponse);
        if ("true".equals(liveChannelDetailResponse)) {
            //to do something ......
            log.debug(String.format("频道%s修改scene为%s成功%s", channelId, value, liveChannelDetailResponse));
        }
    }
    
    /**
     * TODO 频道号动态获取
     * 测试设置频道详情：设置最大同时观看人数
     * @throws IOException
     */
    @Test
    public void testUpdateChannel() throws IOException {
        Integer channelId = 1938888;
        String value = "2147483647";
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("maxViewer")
                .setValue(value)
                .setRequestId("2860257a405447e1bbbe9161da2dee75");
        String liveChannelDetailResponse = new LiveChannelServiceImpl().updateChannelDetail(liveChannelDetailRequest);
        Assert.assertNotNull(liveChannelDetailResponse);
        if ("true".equals(liveChannelDetailResponse)) {
            //to do something ......
            log.debug(String.format("频道%s修改maxViewer为%s成功%s", channelId, value, liveChannelDetailResponse));
        }
    }
    
    /**
     * 查询课件重制任务列表
     * @throws IOException
     */
    @Test
    public void testListPPTRecord() throws IOException {
        LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest = new LiveListChannelPPTRecordRequest();
        liveListChannelPPTRecordRequest.setChannelId(1940343).setPage(1);
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse = new LiveChannelServiceImpl().listPPTRecord(
                liveListChannelPPTRecordRequest);
        Assert.assertNotNull(liveListChannelPPTRecordResponse);
        if (liveListChannelPPTRecordResponse != null) {
            //to do something ......
            log.debug("查询课件重制任务列表信息成功" + JSON.toJSONString(liveListChannelPPTRecordResponse));
        }
    }
}
