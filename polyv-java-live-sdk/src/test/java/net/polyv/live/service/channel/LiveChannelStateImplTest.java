package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.channel.state.LiveCutoffChannelStreamRequest;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusRequest;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusResponse;
import net.polyv.live.entity.channel.state.LiveResumeChannelStreamRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.channel.impl.LiveChannelStateServiceImpl;

/**
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelStateImplTest extends BaseTest {
    
    /**
     * 批量查询频道直播流状态
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelLiveStream() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId0 = createChannel();
        Integer channelId1 = createChannel();
        
        LiveListChannelStreamStatusRequest liveListChannelStreamStatusRequest =
                new LiveListChannelStreamStatusRequest();
        liveListChannelStreamStatusRequest.setChannelIds(String.format("%s,%s", channelId0, channelId1));
        LiveListChannelStreamStatusResponse liveListChannelStreamStatusResponse =
                new LiveChannelStateServiceImpl().listChannelLiveStream(
                liveListChannelStreamStatusRequest);
        Assert.assertNotNull(liveListChannelStreamStatusResponse);
        if (liveListChannelStreamStatusResponse != null) {
            //to do something ......
            log.debug("批量查询频道直播流状态成功{}", JSON.toJSONString(liveListChannelStreamStatusResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId0);
        deleteChannel(channelId1);
    }
    
    /**
     * 测试恢复直播频道推流
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testResumeChannelStream() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveResumeChannelStreamRequest liveResumeChannelStreamRequest = new LiveResumeChannelStreamRequest();
        liveResumeChannelStreamRequest.setChannelId(channelId);
        String liveResumeChannelStreamResponse = new LiveChannelStateServiceImpl().resumeChannelStream(
                liveResumeChannelStreamRequest);
        Assert.assertNotNull(liveResumeChannelStreamResponse);
        if ("success".equals(liveResumeChannelStreamResponse)) {
            //to do something ......
            log.debug("恢复直播频道推流成功{}", liveResumeChannelStreamResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 禁止直播频道推流
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCutoffChannelStream() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveCutoffChannelStreamRequest liveCutoffChannelStreamRequest = new LiveCutoffChannelStreamRequest();
        liveCutoffChannelStreamRequest.setChannelId(channelId);
        String liveCutoffChannelStreamResponse = new LiveChannelStateServiceImpl().cutoffChannelStream(
                liveCutoffChannelStreamRequest);
        Assert.assertNotNull(liveCutoffChannelStreamResponse);
        if ("success".equals(liveCutoffChannelStreamResponse)) {
            //to do something ......
            log.debug("禁止直播频道推流成功{}", liveCutoffChannelStreamResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
}
