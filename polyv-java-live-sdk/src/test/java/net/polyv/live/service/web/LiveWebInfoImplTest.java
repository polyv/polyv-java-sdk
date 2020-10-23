package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.web.info.LiveChannelCountDownRequest;
import net.polyv.live.entity.web.info.LiveChannelCountDownResponse;
import net.polyv.live.entity.web.info.LiveChannelLikesRequest;
import net.polyv.live.entity.web.info.LiveChannelLikesResponse;
import net.polyv.live.entity.web.info.LiveChannelSplashRequest;
import net.polyv.live.entity.web.info.LiveChannelSplashResponse;
import net.polyv.live.entity.web.info.LiveUpdateChannelCountDownRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelLikesRequest;
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
    
    /**
     * 测试查询直播引导图开关状态及URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelSplash() throws IOException, NoSuchAlgorithmException {
        LiveChannelSplashRequest liveChannelSplashRequest = new LiveChannelSplashRequest();
        liveChannelSplashRequest.setChannelId(1965681);
        LiveChannelSplashResponse liveChannelSplashResponse = new LiveWebInfoServiceImpl().channelSplash(
                liveChannelSplashRequest);
        Assert.assertNotNull(liveChannelSplashResponse);
        if (liveChannelSplashResponse != null) {
            //to do something ......
            log.debug("测试查询直播引导图开关状态及URL成功,{}", JSON.toJSONString(liveChannelSplashResponse));
        }
    }
    
    /**
     * 测试设置频道点赞数和观看热度值
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelLikes() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelLikesRequest liveUpdateChannelLikesRequest = new LiveUpdateChannelLikesRequest();
        liveUpdateChannelLikesRequest.setChannelId(1965681).setLikes(9999).setViewers(9999);
        String liveUpdateChannelLikesResponse = new LiveWebInfoServiceImpl().updateChannelLikes(
                liveUpdateChannelLikesRequest);
        Assert.assertNotNull(liveUpdateChannelLikesResponse);
        if ("success".equals(liveUpdateChannelLikesResponse)) {
            //to do something ......
            log.debug("测试设置频道点赞数和观看热度值成功,{}", liveUpdateChannelLikesResponse);
        }
    }
    
    /**
     * 测试查询频道点赞数和观众热度值
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelLikes() throws IOException, NoSuchAlgorithmException {
        LiveChannelLikesRequest liveChannelLikesRequest = new LiveChannelLikesRequest();
        liveChannelLikesRequest.setChannelIds("1965681");
        LiveChannelLikesResponse liveChannelLikesResponse = new LiveWebInfoServiceImpl().channelLikes(
                liveChannelLikesRequest);
        Assert.assertNotNull(liveChannelLikesResponse);
        if (liveChannelLikesResponse != null) {
            //to do something ......
            log.debug("测试查询频道点赞数和观众热度值成功,{}", JSON.toJSONString(liveChannelLikesResponse));
        }
    }
    
    /**
     * 测试设置频道直播倒计时信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelCountDown() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelCountDownRequest liveUpdateChannelCountDownRequest = new LiveUpdateChannelCountDownRequest();
        liveUpdateChannelCountDownRequest.setChannelId(1965681)
                .setBookingEnabled("Y")
                .setStartTime("2020-11-11 11:11:11");
        String liveUpdateChannelCountDownResponse = new LiveWebInfoServiceImpl().updateChannelCountDown(
                liveUpdateChannelCountDownRequest);
        Assert.assertNotNull(liveUpdateChannelCountDownResponse);
        if (liveUpdateChannelCountDownResponse != null) {
            //to do something ......
            log.debug("测试设置频道直播倒计时信息成功,{}", liveUpdateChannelCountDownResponse);
        }
    }
    
    /**
     * 测试查询频道直播倒计时信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelCountDown() throws IOException, NoSuchAlgorithmException {
        LiveChannelCountDownRequest liveChannelCountDownRequest = new LiveChannelCountDownRequest();
        liveChannelCountDownRequest.setChannelId(1965681);
        LiveChannelCountDownResponse liveChannelCountDownResponse = new LiveWebInfoServiceImpl().channelCountDown(
                liveChannelCountDownRequest);
        Assert.assertNotNull(liveChannelCountDownResponse);
        if (liveChannelCountDownResponse != null) {
            //to do something ......
            log.debug("测试查询频道直播倒计时信息成功,{}", JSON.toJSONString(liveChannelCountDownResponse));
        }
    }
    
    /**
     * 测试设置频道图标
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testUpdateChannelLogo() throws IOException, NoSuchAlgorithmException {
//        LiveUpdateChannelLogoRequest liveUpdateChannelLogoRequest = new LiveUpdateChannelLogoRequest();
//        String path = "C:\\Users\\T460\\Desktop\\elephant.png";
//        liveUpdateChannelLogoRequest.setChannelId(1965681).setImgfile(new File(path));
//        String liveUpdateChannelLogoResponse = new LiveWebInfoServiceImpl().updateChannelLogo
//        (liveUpdateChannelLogoRequest);
//        Assert.assertNotNull(liveUpdateChannelLogoResponse);
//        if (liveUpdateChannelLogoResponse != null) {
//            //to do something ......
//            log.debug("测试设置频道图标成功,{}", liveUpdateChannelLogoResponse);
//        }
//    }
    
    /**
     * 测试设置引导开关以及引导图片
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testUpdateChannelSplash() throws IOException, NoSuchAlgorithmException {
//        String path = "C:\\Users\\T460\\Desktop\\fields.txt";
//        LiveUpdateChannelSplashRequest liveUpdateChannelSplashRequest = new LiveUpdateChannelSplashRequest();
//        liveUpdateChannelSplashRequest.setChannelId(1965681).setSplashEnabled("N").setImgfile(new File(path));
//        String liveUpdateChannelSplashResponse = new LiveWebInfoServiceImpl().updateChannelSplash(
//                liveUpdateChannelSplashRequest);
//        Assert.assertNotNull(liveUpdateChannelSplashResponse);
//        if(liveUpdateChannelSplashResponse != null){
//            log.debug("设置引导开关以及引导图片成功,{}", liveUpdateChannelSplashResponse);
//        }
//    }
    

    
}
