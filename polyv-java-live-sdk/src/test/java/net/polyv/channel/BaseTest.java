package net.polyv.channel;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.entity.channel.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.LiveDeleteChannelRequest;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

/**
 * @author: thomas
 
 **/
public class BaseTest {
//    BaseTest(){
//        String appId = "frlr1zazn3";
//        String appSecret = "5d5ade8f71f24bb9a2d1176cd607dd17";
//        String userId = "1b448be323";
//        LiveGlobalConfig.init(appId,userId,appSecret);
//        System.out.println("--初始化完成--");
//    }
    
    /**
     * 创建channel并返回channelId
     * @param liveChannelRequest
     * @return 频道id
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    protected Integer createChannel(LiveChannelRequest liveChannelRequest) throws IOException, NoSuchAlgorithmException {
        LiveChannelResponse liveChannelResponse = new LiveChannelServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        return liveChannelResponse.getChannelId();
    }
    
    /**
     * 删除频道，删除失败则断言出错
     * @param channelId 频道号
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    protected void deleteChannel(Integer channelId) throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest().setChannelId(channelId);
        String deleteChannel = new LiveChannelServiceImpl().deleteChannel(liveDeleteChannelRequest);
        Assert.assertTrue("true".equals(deleteChannel));
    }
    
    /**
     * 创建子频道并返回子频道id
     * @param liveCreateSonChannelRequest
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected String createSonChannel(LiveCreateSonChannelRequest liveCreateSonChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = new LiveChannelServiceImpl().createSonChannel(
                liveCreateSonChannelRequest);
        Assert.assertNotNull(liveCreateSonChannelResponse);
        return liveCreateSonChannelResponse.getAccount();
    }
    
}
