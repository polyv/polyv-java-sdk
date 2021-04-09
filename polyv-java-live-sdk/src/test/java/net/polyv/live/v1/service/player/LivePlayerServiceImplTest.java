package net.polyv.live.v1.service.player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.player.LiveSetPlayerHeaderAdvertRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerImgRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerLogoRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerPauseAdvertRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerUrlMarqueeRequest;
import net.polyv.live.v1.entity.player.LiveSetWarmupEnableRequest;
import net.polyv.live.v1.entity.player.LiveSetWarmupVedioRequest;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.player.impl.LivePlayerServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 播放器
 * @author: thomas
 **/
@Slf4j
public class LivePlayerServiceImplTest extends BaseTest {
    
    /**
     * 设置频道的暖场设置开关
     * 描述：用于设置频道的暖场开关
     * 返回：true 设置成功 ， false 设置失败
     * API地址：PLAYER_SET_WARMUP_ENABLE_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerWarmupEnable() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetWarmupEnableRequest liveSetWarmupEnableRequest = new LiveSetWarmupEnableRequest();
        Boolean liveSetWarmupEnableResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetWarmupEnableRequest.setChannelId(channelId)
                    .setWarmUpEnabled(LiveConstant.Flag.YES.getFlag());
            liveSetWarmupEnableResponse = new LivePlayerServiceImpl().setPlayerWarmupEnable(liveSetWarmupEnableRequest);
            Assert.assertNotNull(liveSetWarmupEnableResponse);
            if (liveSetWarmupEnableResponse != null) {
                //to do something ......
                log.debug("测试设置频道的暖场设置开关成功{}", JSON.toJSONString(liveSetWarmupEnableResponse));
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
     * 设置播放器暖场图片
     * 描述：1、修改播放器的暖场图片
     * 描述：2、暖场视频和暖场图片是处于非直播状态时，播放器显示的画面，两者在同一时间只能显示一种，以最晚设置者为准，若想删除暖场画面，则将coverImage或warmUpFlv的值设为"http://"。
     * 返回：true 设置成功 ， false 设置失败
     * API地址：PLAYER_SET_IMG_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerImg() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
        Boolean liveSetChatAdminDataResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setCoverImage("https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                            "/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg")
                    .setCoverHref("http://www.baidu.com");
            liveSetChatAdminDataResponse = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
            Assert.assertNotNull(liveSetChatAdminDataResponse);
            if (liveSetChatAdminDataResponse) {
                //to do something ......
                log.debug("测试设置播放器暖场图片成功 ");
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
     * 设置播放器暖场视频
     * 描述：1、修改播放器的暖场视频
     * 描述：2、暖场视频和暖场图片是处于非直播状态时，播放器显示的画面，两者在同一时间只能显示一种，以最晚设置者为准，若想删除暖场画面，则将coverImage或warmUpFlv的值设为"http://"。
     * 返回：true 设置成功，false 设置失败
     * API地址：PLAYER_SET_CHANNEL_WARMUP_VEDIO_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerWarmUpVedio() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
        Boolean liveSetWarmupVedioResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetWarmupVedioRequest.setChannelId(channelId)
                    .setWarmUpFlv("http://www.w3school.com.cn/example/html5/mov_bbb.mp4");
//                    .setWarmUpFlv("http://")//删除视频
            liveSetWarmupVedioResponse = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
            Assert.assertNotNull(liveSetWarmupVedioResponse);
            if (liveSetWarmupVedioResponse != null) {
                //to do something ......
                log.debug("测试设置播放器暖场视频成功{}", JSON.toJSONString(liveSetWarmupVedioResponse));
                
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
     * 设置播放器Logo
     * 返回：true 设置成功， fales 设置失败
     * API地址：PLAYER_SET_CHANNEL_LOGO_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerLogo() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerLogoRequest liveSetPlayerLogoRequest = new LiveSetPlayerLogoRequest();
        Boolean liveSetPlayerLogoResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerLogoRequest.setChannelId(channelId)
                    .setLogoHref("http://www.baidu.com")
                    .setLogoPosition(LiveConstant.LogoPosition.BL.getPosition())
                    .setLogoImage(
                            "https://c-ssl.duitang.com/uploads/blog/202009/01/20200901155255_e8037.thumb.1000_0.jpg")
                    .setLogoOpacity(0.32f);
            liveSetPlayerLogoResponse = new LivePlayerServiceImpl().setPlayerLogo(liveSetPlayerLogoRequest);
            Assert.assertNotNull(liveSetPlayerLogoResponse);
            if (liveSetPlayerLogoResponse != null) {
                //to do something ......
                log.debug("测试设置播放器Logo成功{}", JSON.toJSONString(liveSetPlayerLogoResponse));
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
     * 设置播放器片头广告
     * 描述：设置某频道播放器的片头广告
     * 约束：2、设置片头广告并不一定会展示，需要调用 设置频道默认项开关 把广告通用设置开关关闭
     * 返回：true 设置成功，false 设置失败
     * API地址：PLAYER_SET_CHANNEL_HEADER_ADVERT_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerHeaderAdvert() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerHeaderAdvertRequest liveSetPlayerHeaderAdvertRequest = new LiveSetPlayerHeaderAdvertRequest();
        Boolean liveSetPlayerHeaderAdvertResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerHeaderAdvertRequest.setChannelId(channelId)
                    .setEnabled(LiveConstant.Flag.YES.getFlag())
                    .setHeadAdvertDuration(5)
                    .setHeadAdvertHeight(100)
                    .setHeadAdvertType(LiveConstant.HeadAdvertType.IMAGE.getDesc())
                    .setHeadAdvertWidth(100)
                    .setHeadAdvertHref("http://www.baidu.com")
                    .setHeadAdvertMediaUrl(
                            "https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                                    "/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg");
            liveSetPlayerHeaderAdvertResponse = new LivePlayerServiceImpl().setPlayerHeaderAdvert(liveSetPlayerHeaderAdvertRequest);
            Assert.assertNotNull(liveSetPlayerHeaderAdvertResponse);
            if (liveSetPlayerHeaderAdvertResponse != null) {
                //to do something ......
                log.debug("测试设设置播放器片头广告成功{}", JSON.toJSONString(liveSetPlayerHeaderAdvertResponse));
                
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
     * 设置播放器暂停广告
     * 描述：用于设置某频道播放器的暂停广告
     * 约束：2、设置暂停广告并不一定会展示，需要调用 设置频道默认项开关 把广告通用设置开关关闭
     * 返回：true 设置成功，false 设置失败
     * API地址：PLAYER_SET_CHANNEL_PAUSE_ADVERT_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerPauseAdvert() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerPauseAdvertRequest liveSetPlayerPauseAdvertRequest = new LiveSetPlayerPauseAdvertRequest();
        Boolean liveSetPlayerPauseAdvertResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerPauseAdvertRequest.setChannelId(channelId)
                    .setEnabled(LiveConstant.Flag.YES.getFlag())
                    .setStopAdvertHref("http://www.baidu.com")
                    .setStopAdvertImage("https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                            "/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg");
            liveSetPlayerPauseAdvertResponse = new LivePlayerServiceImpl().setPlayerPauseAdvert(liveSetPlayerPauseAdvertRequest);
            Assert.assertNotNull(liveSetPlayerPauseAdvertResponse);
            if (liveSetPlayerPauseAdvertResponse != null) {
                //to do something ......
                log.debug("测试设置播放器暂停广告成功{}", JSON.toJSONString(liveSetPlayerPauseAdvertResponse));
                
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
     * 测试设置播放器自定义url跑马灯
     * 描述：可以设置播放器防录屏自定义url跑马灯开关，在开启时需提交url参数。
     * 返回：true 设置成功，false 设置失败
     * API地址：SET_PLAYER_URL_MARQUEE_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerUrlMarquee() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerUrlMarqueeRequest liveSetPlayerUrlMarqueeRequest = new LiveSetPlayerUrlMarqueeRequest();
        Boolean liveSetPlayerUrlMarqueeResponse;
        try {
            String channelId = super.createChannel();
            liveSetPlayerUrlMarqueeRequest.setChannelId(channelId)
                    .setMarqueeRestrict("N");
            liveSetPlayerUrlMarqueeResponse = new LivePlayerServiceImpl().setPlayerUrlMarquee(
                    liveSetPlayerUrlMarqueeRequest);
            Assert.assertTrue(liveSetPlayerUrlMarqueeResponse);
            if (liveSetPlayerUrlMarqueeResponse) {
                //to do something ......
                log.debug("测试设置播放器自定义url跑马灯成功");
                
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
