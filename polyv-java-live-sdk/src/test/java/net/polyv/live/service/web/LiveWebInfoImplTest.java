package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.web.info.LiveUpdateChannelNameRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelPublisherRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.web.impl.LiveWebInfoServiceImpl;

/**
 * @author: sadboy
 **/
@Slf4j
public class LiveWebInfoImplTest extends BaseTest {
    
    /**
     * 测试设置频道名称
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelName() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelNameRequest liveUpdateChannelNameRequest = new LiveUpdateChannelNameRequest();
        liveUpdateChannelNameRequest.setChannelId(1965681).setName("Junit测试(勿删)");
        String liveUpdateChannelNameResponse = new LiveWebInfoServiceImpl().updateChannelName(
                liveUpdateChannelNameRequest);
        Assert.assertNotNull(liveUpdateChannelNameResponse);
        if ("true".equals(liveUpdateChannelNameResponse)) {
            //to do something ......
            log.debug("测试设置频道名称成功,{}", liveUpdateChannelNameResponse);
        }
    }
    
    /**
     * 测试设置主持人姓名
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelPublisher() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelPublisherRequest liveUpdateChannelPublisherRequest = new LiveUpdateChannelPublisherRequest();
        liveUpdateChannelPublisherRequest.setChannelId(1965681).setPublisher("主讲人sadboy");
        String liveUpdateChannelPublisherResponse = new LiveWebInfoServiceImpl().updateChannelPublisher(
                liveUpdateChannelPublisherRequest);
        Assert.assertNotNull(liveUpdateChannelPublisherResponse);
        if ("true".equals(liveUpdateChannelPublisherResponse)) {
            //to do something ......
            log.debug("测试设置主持人姓名成功,{}", liveUpdateChannelPublisherResponse);
        }
    }
    
}
