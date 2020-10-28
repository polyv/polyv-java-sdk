package net.polyv.live.service.web;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.entity.web.info.LiveChannelCountDownRequest;
import net.polyv.live.entity.web.info.LiveChannelCountDownResponse;
import net.polyv.live.entity.web.info.LiveChannelLikesRequest;
import net.polyv.live.entity.web.info.LiveChannelLikesResponse;
import net.polyv.live.entity.web.info.LiveChannelSplashRequest;
import net.polyv.live.entity.web.info.LiveChannelSplashResponse;
import net.polyv.live.entity.web.info.LiveUpdateChannelCountDownRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelLikesRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelLogoRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelNameRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelPublisherRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelSplashRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.web.impl.LiveWebInfoServiceImpl;

/**
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
    public void testUpdateChannelName() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelNameRequest liveUpdateChannelNameRequest = new LiveUpdateChannelNameRequest();
        Boolean liveUpdateChannelNameResponse;
        try {
            liveUpdateChannelNameRequest.setChannelId(1965681).setName("Junit测试(勿删)");
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
    public void testUpdateChannelPublisher() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelPublisherRequest liveUpdateChannelPublisherRequest = new LiveUpdateChannelPublisherRequest();
        Boolean liveUpdateChannelPublisherResponse;
        try {
            liveUpdateChannelPublisherRequest.setChannelId(1965681).setPublisher("主讲人sadboy");
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
    public void testChannelSplash() throws IOException, NoSuchAlgorithmException {
        LiveChannelSplashRequest liveChannelSplashRequest = new LiveChannelSplashRequest();
        LiveChannelSplashResponse liveChannelSplashResponse;
        try {
            liveChannelSplashRequest.setChannelId(1965681);
            liveChannelSplashResponse = new LiveWebInfoServiceImpl().channelSplash(liveChannelSplashRequest);
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
    public void testUpdateChannelLikes() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelLikesRequest liveUpdateChannelLikesRequest = new LiveUpdateChannelLikesRequest();
        Boolean liveUpdateChannelLikesResponse;
        try {
            liveUpdateChannelLikesRequest.setChannelId(1965681).setLikes(9999).setViewers(9999);
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
     * 测试查询频道点赞数和观众热度值
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelLikes() throws IOException, NoSuchAlgorithmException {
        LiveChannelLikesRequest liveChannelLikesRequest = new LiveChannelLikesRequest();
        LiveChannelLikesResponse liveChannelLikesResponse;
        try {
            liveChannelLikesRequest.setChannelIds("1965681");
            liveChannelLikesResponse = new LiveWebInfoServiceImpl().channelLikes(liveChannelLikesRequest);
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
    public void testUpdateChannelCountDown() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelCountDownRequest liveUpdateChannelCountDownRequest = new LiveUpdateChannelCountDownRequest();
        Boolean liveUpdateChannelCountDownResponse;
        try {
            liveUpdateChannelCountDownRequest.setChannelId(1965681)
                    .setBookingEnabled("Y")
                    .setStartTime("2020-11-11 11:11:11");
            liveUpdateChannelCountDownResponse = new LiveWebInfoServiceImpl().updateChannelCountDown(
                    liveUpdateChannelCountDownRequest);
            Assert.assertNotNull(liveUpdateChannelCountDownResponse);
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
    public void testChannelCountDown() throws IOException, NoSuchAlgorithmException {
        LiveChannelCountDownRequest liveChannelCountDownRequest = new LiveChannelCountDownRequest();
        LiveChannelCountDownResponse liveChannelCountDownResponse;
        try {
            liveChannelCountDownRequest.setChannelId(1965681);
            liveChannelCountDownResponse = new LiveWebInfoServiceImpl().channelCountDown(liveChannelCountDownRequest);
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
     * 测试设置频道图标
     * 返回：成功返回图标地址，如://livestatic.videocc.net/uploaded/images/2017/03/******.jpg
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testUpdateChannelLogo() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelLogoRequest liveUpdateChannelLogoRequest = new LiveUpdateChannelLogoRequest();
        String liveUpdateChannelLogoResponse;
        try {
            String path = "C:\\Users\\T460\\Desktop\\elephant.png";
            liveUpdateChannelLogoRequest.setChannelId(1965681).setImgfile(new File(path));
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
//    @Test
    public void testUpdateChannelSplash() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelSplashRequest liveUpdateChannelSplashRequest = new LiveUpdateChannelSplashRequest();
        String liveUpdateChannelSplashResponse;
        try {
            String path = "C:\\Users\\T460\\Desktop\\fields.txt";
            liveUpdateChannelSplashRequest.setChannelId(1965681).setSplashEnabled("N").setImgfile(new File(path));
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
    
}
