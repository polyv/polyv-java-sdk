package net.polyv.live.v1.service.web;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.web.info.LiveChannelCountDownRequest;
import net.polyv.live.v1.entity.web.info.LiveChannelCountDownResponse;
import net.polyv.live.v1.entity.web.info.LiveChannelLikesRequest;
import net.polyv.live.v1.entity.web.info.LiveChannelLikesResponse;
import net.polyv.live.v1.entity.web.info.LiveChannelSplashRequest;
import net.polyv.live.v1.entity.web.info.LiveChannelSplashResponse;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelCountDownRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelLikesRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelLogoRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelNameRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelPublisherRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelSplashRequest;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.web.impl.LiveWebInfoServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 页面信息
 * @author: sadboy
 **/
@Slf4j
public class LiveWebInfoImplTest extends BaseTest {
    
    /**
     * 测试设置频道名称
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelName() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelNameRequest liveUpdateChannelNameRequest = new LiveUpdateChannelNameRequest();
        Boolean liveUpdateChannelNameResponse;
        try {
            liveUpdateChannelNameRequest.setChannelId(createChannel())
                    .setName("Junit测试(勿删)")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelNameResponse = new LiveWebInfoServiceImpl().updateChannelName(
                    liveUpdateChannelNameRequest);
            Assert.assertNotNull(liveUpdateChannelNameResponse);
            if (liveUpdateChannelNameResponse) {
                //to do something ......
                log.debug("测试设置频道名称成功");
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
     * 测试设置主持人姓名
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelPublisher() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelPublisherRequest liveUpdateChannelPublisherRequest = new LiveUpdateChannelPublisherRequest();
        Boolean liveUpdateChannelPublisherResponse;
        try {
            liveUpdateChannelPublisherRequest.setChannelId(createChannel())
                    .setPublisher("主讲人sadboy")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelPublisherResponse = new LiveWebInfoServiceImpl().updateChannelPublisher(
                    liveUpdateChannelPublisherRequest);
            Assert.assertNotNull(liveUpdateChannelPublisherResponse);
            if (liveUpdateChannelPublisherResponse) {
                //to do something ......
                log.debug("测试设置主持人姓名成功");
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
     * 测试查询直播引导图开关状态及URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelSplash() throws Exception, NoSuchAlgorithmException {
        LiveChannelSplashRequest liveChannelSplashRequest = new LiveChannelSplashRequest();
        LiveChannelSplashResponse liveChannelSplashResponse;
        try {
            liveChannelSplashRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelSplashResponse = new LiveWebInfoServiceImpl().getChannelSplash(liveChannelSplashRequest);
            Assert.assertNotNull(liveChannelSplashResponse);
            if (liveChannelSplashResponse != null) {
                //to do something ......
                log.debug("测试查询直播引导图开关状态及URL成功,{}", JSON.toJSONString(liveChannelSplashResponse));
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
     * 测试设置频道点赞数和观看热度值
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelLikes() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelLikesRequest liveUpdateChannelLikesRequest = new LiveUpdateChannelLikesRequest();
        Boolean liveUpdateChannelLikesResponse;
        try {
            liveUpdateChannelLikesRequest.setChannelId(createChannel())
                    .setLikes(9999)
                    .setViewers(9999)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelLikesResponse = new LiveWebInfoServiceImpl().updateChannelLikes(
                    liveUpdateChannelLikesRequest);
            Assert.assertNotNull(liveUpdateChannelLikesResponse);
            if (liveUpdateChannelLikesResponse) {
                //to do something ......
                log.debug("测试设置频道点赞数和观看热度值成功");
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
     * 测试设置频道图标
     * 返回：成功返回图标地址，如://livestatic.videocc.net/uploaded/images/2017/03/******.jpg
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelLogo() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelLogoRequest liveUpdateChannelLogoRequest = new LiveUpdateChannelLogoRequest();
        String liveUpdateChannelLogoResponse;
        try {
            String path = getClass().getResource("/img/elephant.png").getPath();
            liveUpdateChannelLogoRequest.setChannelId(createChannel())
                    .setImgfile(new File(path))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelLogoResponse = new LiveWebInfoServiceImpl().updateChannelLogo(
                    liveUpdateChannelLogoRequest);
            Assert.assertNotNull(liveUpdateChannelLogoResponse);
            if (liveUpdateChannelLogoResponse != null) {
                //to do something ......
                log.debug("测试设置频道图标成功,{}", liveUpdateChannelLogoResponse);
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
     * 测试设置引导开关以及引导图片
     * 返回：设置引导页未上传图片，成功返回success；
     * 返回：设置引导页同时上传图片;成功返回地址，如：//xxx.videocc.net/uploaded/images/2017/03/******.jpg
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelSplash() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelSplashRequest liveUpdateChannelSplashRequest = new LiveUpdateChannelSplashRequest();
        String liveUpdateChannelSplashResponse;
        try {
            String path = getClass().getResource("/img/elephant.png").getPath();
            liveUpdateChannelSplashRequest.setChannelId(createChannel())
                    .setSplashEnabled("N")
                    .setImgfile(new File(path))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelSplashResponse = new LiveWebInfoServiceImpl().updateChannelSplash(
                    liveUpdateChannelSplashRequest);
            Assert.assertNotNull(liveUpdateChannelSplashResponse);
            if (liveUpdateChannelSplashResponse != null) {
                log.debug("设置引导开关以及引导图片成功,{}", liveUpdateChannelSplashResponse);
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
     * 测试查询频道点赞数和观众热度值
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelLikes() throws Exception, NoSuchAlgorithmException {
        LiveChannelLikesRequest liveChannelLikesRequest = new LiveChannelLikesRequest();
        LiveChannelLikesResponse liveChannelLikesResponse;
        try {
            liveChannelLikesRequest.setChannelIds("1965681").setRequestId(LiveSignUtil.generateUUID());
            liveChannelLikesResponse = new LiveWebInfoServiceImpl().getChannelLikes(liveChannelLikesRequest);
            Assert.assertNotNull(liveChannelLikesResponse);
            if (liveChannelLikesResponse != null) {
                //to do something ......
                log.debug("测试查询频道点赞数和观众热度值成功,{}", JSON.toJSONString(liveChannelLikesResponse));
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
     * 测试设置频道直播倒计时信息
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelCountDown() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelCountDownRequest liveUpdateChannelCountDownRequest = new LiveUpdateChannelCountDownRequest();
        Boolean liveUpdateChannelCountDownResponse;
        try {
            liveUpdateChannelCountDownRequest.setChannelId(createChannel())
                    .setBookingEnabled("Y")
                    .setStartTime(getDate(2020, 11, 11, 11, 11, 11))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelCountDownResponse = new LiveWebInfoServiceImpl().updateChannelCountDown(
                    liveUpdateChannelCountDownRequest);
            Assert.assertTrue(liveUpdateChannelCountDownResponse);
            if (liveUpdateChannelCountDownResponse) {
                //to do something ......
                log.debug("测试设置频道直播倒计时信息成功");
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
     * 测试查询频道直播倒计时信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelCountDown() throws Exception, NoSuchAlgorithmException {
        LiveChannelCountDownRequest liveChannelCountDownRequest = new LiveChannelCountDownRequest();
        LiveChannelCountDownResponse liveChannelCountDownResponse;
        try {
            liveChannelCountDownRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelCountDownResponse = new LiveWebInfoServiceImpl().getChannelCountDown(
                    liveChannelCountDownRequest);
            Assert.assertNotNull(liveChannelCountDownResponse);
            if (liveChannelCountDownResponse != null) {
                //to do something ......
                log.debug("测试查询频道直播倒计时信息成功,{}", JSON.toJSONString(liveChannelCountDownResponse));
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
