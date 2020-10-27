package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.web.interact.LiveChannelDonateRequest;
import net.polyv.live.entity.web.interact.LiveChannelDonateResponse;
import net.polyv.live.entity.web.interact.LiveUpdateChannelCashRequest;
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
        liveUpdateChannelGoodResponse = new LiveWebInteractServiceImpl().updateChannelGood(
                liveUpdateChannelGoodRequest);
        Assert.assertNotNull(liveUpdateChannelGoodResponse);
        if (liveUpdateChannelGoodResponse) {
            //to do something ......
            log.debug("测试设置道具打赏成功");
        }
    }
    
    /**
     * 测试设置现金打赏
     * 描述:用于设置频道或者全局现金打赏
     * 约束:2.带上频道号为设置频道现金打赏，不带频道号默认为全局现金打赏设置
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelCash() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelCashRequest liveUpdateChannelCashRequest = new LiveUpdateChannelCashRequest();
        Boolean liveUpdateChannelCashResponse;
        Double[] floats = {0.88d, 6.66d, 8.88d, 18.11d, 66.60d, 88.89d};
        List<Double> cashes = Arrays.asList(floats);
        liveUpdateChannelCashRequest.setChannelId(1965681).setCashes(cashes).setCashMin(0.02d).setEnabled("Y");
        liveUpdateChannelCashResponse = new LiveWebInteractServiceImpl().updateChannelCash(liveUpdateChannelCashRequest);
        Assert.assertNotNull(liveUpdateChannelCashResponse);
        if (liveUpdateChannelCashResponse) {
            //to do something ......
            log.debug("测试设置现金打赏成功");
        }
    }
    
    /**
     * 测试查询打赏设置
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelDonate() throws IOException, NoSuchAlgorithmException {
        LiveChannelDonateRequest liveChannelDonateRequest = new LiveChannelDonateRequest();
        LiveChannelDonateResponse liveChannelDonateResponse;
        liveChannelDonateRequest.setChannelId(1965681);
        liveChannelDonateResponse = new LiveWebInteractServiceImpl().channelDonate(
                liveChannelDonateRequest);
        Assert.assertNotNull(liveChannelDonateResponse);
        if (liveChannelDonateResponse != null) {
            //to do something ......
            log.debug("测试查询打赏设置成功,{}", JSON.toJSONString(liveChannelDonateResponse));
        }
    }
    
}
