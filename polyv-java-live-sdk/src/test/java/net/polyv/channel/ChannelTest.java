package net.polyv.channel;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

/**
 * @author: niaonao
 * @date: 2020/9/18
 **/
@Slf4j
public class ChannelTest extends BaseTest {
    
    
    @Test
    public void testCreateChannel() throws IOException {
        LiveChannelRequest liveChannelRequest =  new  LiveChannelRequest();
        liveChannelRequest.setName( "Spring 知识精讲")
                .setChannelPasswd("666888")
                .setRequestId("2860257a405447e1bbbe9161da2dee72");
        LiveChannelResponse liveChannelResponse = new LiveChannelServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        if(liveChannelResponse != null  ){
            //todo something ......
           log.debug("频道创建成功"+JSON.toJSONString(liveChannelResponse));
        }
    }
    
    
    
    
    
}
