package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.web.interact.LiveUpdateChannelGoodRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.web.impl.LiveWebInteractServiceImpl;

/**
 * 页面互动
 * @author: sadboy
 **/
@Slf4j
public class LiveWebInteractImplTest extends BaseTest {
    
    /**
     * 测试设置道具打赏
     * TODO 未通过测试
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testUpdateChannelGood() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelGoodRequest liveUpdateChannelGoodRequest = new LiveUpdateChannelGoodRequest();
        Boolean liveUpdateChannelGoodResponse;
        List<LiveUpdateChannelGoodRequest.ChannelGood> channelGoods = new ArrayList<>();
        LiveUpdateChannelGoodRequest.ChannelGood channelGood = new LiveUpdateChannelGoodRequest.ChannelGood();
        channelGood.setGoodName("佛跳墙")
                .setGoodImg("//livestatic.videocc.net/uploaded/images/webapp/channel/donate/07-diamond.png")
                .setGoodPrice(9999.99)
                .setGoodEnabled("Y");
        channelGoods.add(channelGood);
        liveUpdateChannelGoodRequest.setChannelId(1965681).setEnabled("Y").setGoods(channelGoods);
        liveUpdateChannelGoodResponse = new LiveWebInteractServiceImpl().updateChannelGood(liveUpdateChannelGoodRequest);
        Assert.assertNotNull(liveUpdateChannelGoodResponse);
        if (liveUpdateChannelGoodResponse) {
            //to do something ......
            log.debug("测试设置道具打赏成功");
        }
    }
    
}
