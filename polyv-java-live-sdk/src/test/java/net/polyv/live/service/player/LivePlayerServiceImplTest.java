package net.polyv.live.service.player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.player.LiveSetPlayerHeaderAdvertRequest;
import net.polyv.live.entity.player.LiveSetPlayerImgRequest;
import net.polyv.live.entity.player.LiveSetPlayerLogoRequest;
import net.polyv.live.entity.player.LiveSetPlayerPauseAdvertRequest;
import net.polyv.live.entity.player.LiveSetWarmupEnableRequest;
import net.polyv.live.entity.player.LiveSetWarmupVedioRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.player.impl.LivePlayerServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class LivePlayerServiceImplTest extends BaseTest {
    
    
    /**
     * 设置播放器暖场图片
     * 描述：1、修改播放器的暖场图片
     * 描述：2、暖场视频和暖场图片是处于非直播状态时，播放器显示的画面，两者在同一时间只能显示一种，以最晚设置者为准，若想删除暖场画面，则将coverImage或warmUpFlv的值设为"http://"。
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetChatAdminData() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setCoverImage("https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                            "/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg")
                    .setCoverHref("http://www.baidu.com")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置播放器暖场图片成功{}", JSON.toJSONString(result));
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
     * 设置频道的暖场设置开关
     * 描述：用于设置频道的暖场开关
     * 返回：true 设置成功 ， false 设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerWarmupEnable() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetWarmupEnableRequest liveSetWarmupEnableRequest = new LiveSetWarmupEnableRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetWarmupEnableRequest.setChannelId(channelId)
                    .setWarmUpEnabled(LiveConstant.Flag.YES.getFlag())
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerWarmupEnable(liveSetWarmupEnableRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置频道的暖场设置开关成功{}", JSON.toJSONString(result));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerLogo() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerLogoRequest liveSetPlayerLogoRequest = new LiveSetPlayerLogoRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerLogoRequest.setChannelId(channelId)
                    .setLogoHref("http://www.baidu.com")
                    .setLogoPosition(LiveConstant.LogoPosition.BL.getPosition())
                    .setLogoImage(
                            "https://c-ssl.duitang.com/uploads/blog/202009/01/20200901155255_e8037.thumb.1000_0.jpg")
                    .setLogoOpacity(1D)
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerLogo(liveSetPlayerLogoRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置播放器Logo成功{}", JSON.toJSONString(result));
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
     * 返回：true 设置成功，false 设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerPauseAdvert() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerPauseAdvertRequest liveSetPlayerPauseAdvertRequest = new LiveSetPlayerPauseAdvertRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerPauseAdvertRequest.setChannelId(channelId)
                    .setEnabled(LiveConstant.Flag.YES.getFlag())
                    .setStopAdvertHref("http://www.baidu.com")
                    .setStopAdvertImage("https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                            "/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerPauseAdvert(liveSetPlayerPauseAdvertRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置播放器暂停广告成功{}", JSON.toJSONString(result));
                
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
     * 返回：true 设置成功，false 设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerHeaderAdvert() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerHeaderAdvertRequest liveSetPlayerHeaderAdvertRequest = new LiveSetPlayerHeaderAdvertRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerHeaderAdvertRequest.setChannelId(channelId)
                    .setEnabled(LiveConstant.Flag.YES.getFlag())
                    .setHeadAdvertDuration(5)
                    .setHeadAdvertHeight(100)
                    .setHeadAdvertType(LiveConstant.HeadAdvertType.IMAGE.getDesc())
                    .setHeadAdvertWidth(100)
                    .setHeadAdvertHref("http://www.baidu.com")
                    .setHeadAdvertMediaUrl("https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                            "/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerHeaderAdvert(liveSetPlayerHeaderAdvertRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设设置播放器片头广告成功{}", JSON.toJSONString(result));
                
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerWarmUpVedio() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetWarmupVedioRequest.setChannelId(channelId)
                    .setWarmUpFlv("http://www.w3school.com.cn/example/html5/mov_bbb.mp4")
                    .setWarmUpFlv("http://")//删除视频
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置播放器暖场视频成功{}", JSON.toJSONString(result));
                
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
