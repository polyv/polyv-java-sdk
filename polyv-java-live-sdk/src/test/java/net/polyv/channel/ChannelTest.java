package net.polyv.channel;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

/**
 * @author: thomas
 * @date: 2020/9/18
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
            //todo something ......
            log.debug("频道创建成功" + JSON.toJSONString(liveChannelResponse));
        }
    }
    
    
}
