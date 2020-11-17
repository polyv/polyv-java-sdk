package net.polyv.live.v1.service.web;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.web.interact.LiveChannelDonateRequest;
import net.polyv.live.v1.entity.web.interact.LiveChannelDonateResponse;
import net.polyv.live.v1.entity.web.interact.LiveUpdateChannelCashRequest;
import net.polyv.live.v1.entity.web.interact.LiveUpdateChannelGoodRequest;
import net.polyv.live.v1.entity.web.interact.LiveUpdateChannelWxShareRequest;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.web.impl.LiveWebInteractServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 页面互动
 * @author: sadboy
 **/
@Slf4j
public class LiveWebInteractImplTest extends BaseTest {
    
    /**
     * 测试设置道具打赏
     * 返回：true代表设置成功，false代表设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelGood() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelGoodRequest liveUpdateChannelGoodRequest = new LiveUpdateChannelGoodRequest();
        Boolean liveUpdateChannelGoodResponse;
        try {
            List<LiveUpdateChannelGoodRequest.ChannelGood> channelGoods =
                    new ArrayList<LiveUpdateChannelGoodRequest.ChannelGood>();
            LiveUpdateChannelGoodRequest.ChannelGood channelGood = new LiveUpdateChannelGoodRequest.ChannelGood();
            channelGood.setGoodName("佛跳墙")
                    .setGoodImg("//livestatic.videocc.net/uploaded/images/webapp/channel/donate/07-diamond.png")
                    .setGoodPrice(9999.99)
                    .setGoodEnabled("Y");
            channelGoods.add(channelGood);
            liveUpdateChannelGoodRequest.setChannelId(createChannel())
                    .setEnabled("Y")
                    .setGoods(channelGoods)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelGoodResponse = new LiveWebInteractServiceImpl().updateChannelGood(
                    liveUpdateChannelGoodRequest);
            Assert.assertNotNull(liveUpdateChannelGoodResponse);
            if (liveUpdateChannelGoodResponse) {
                //to do something ......
                log.debug("测试设置道具打赏成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置现金打赏
     * 描述：用于设置频道或者全局现金打赏
     * 约束：2.带上频道号为设置频道现金打赏，不带频道号默认为全局现金打赏设置
     * 返回：true表示设置成功，false表示设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelCash() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelCashRequest liveUpdateChannelCashRequest = new LiveUpdateChannelCashRequest();
        Boolean liveUpdateChannelCashResponse;
        try {
            Double[] floats = {0.88d, 6.66d, 8.88d, 18.11d, 66.60d, 88.89d};
            List<Double> cashes = Arrays.asList(floats);
            liveUpdateChannelCashRequest.setChannelId(createChannel())
                    .setCashes(cashes)
                    .setCashMin(0.02d)
                    .setEnabled("Y")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelCashResponse = new LiveWebInteractServiceImpl().updateChannelCash(
                    liveUpdateChannelCashRequest);
            Assert.assertNotNull(liveUpdateChannelCashResponse);
            if (liveUpdateChannelCashResponse) {
                //to do something ......
                log.debug("测试设置现金打赏成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询打赏设置
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelDonate() throws Exception, NoSuchAlgorithmException {
        LiveChannelDonateRequest liveChannelDonateRequest = new LiveChannelDonateRequest();
        LiveChannelDonateResponse liveChannelDonateResponse;
        try {
            liveChannelDonateRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelDonateResponse = new LiveWebInteractServiceImpl().getChannelDonate(liveChannelDonateRequest);
            Assert.assertNotNull(liveChannelDonateResponse);
            if (liveChannelDonateResponse != null) {
                //to do something ......
                log.debug("测试查询打赏设置成功,{}", JSON.toJSONString(liveChannelDonateResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置频道微信分享信息
     * 描述：用于修改频道的微信分享相关设置
     * 返回：true为设置成功，false为设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelWxShare() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelWxShareRequest liveUpdateChannelWxShareRequest = new LiveUpdateChannelWxShareRequest();
        Boolean liveUpdateChannelWxShareResponse;
        try {
            liveUpdateChannelWxShareRequest.setChannelId(createChannel())
                    .setWxShareTitle("赚钱之道（第一场）")
                    .setWxShareDesc("XXX带你钱生钱")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelWxShareResponse = new LiveWebInteractServiceImpl().updateChannelWxShare(
                    liveUpdateChannelWxShareRequest);
            Assert.assertTrue(liveUpdateChannelWxShareResponse);
            if (liveUpdateChannelWxShareResponse) {
                //to do something ......
                log.debug("测试设置频道微信分享信息成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试用例结束
     */
}
