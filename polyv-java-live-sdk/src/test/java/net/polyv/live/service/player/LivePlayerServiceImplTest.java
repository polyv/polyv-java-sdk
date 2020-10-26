package net.polyv.live.service.player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
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
     *
     * 设置播放器暖场图片，API地址：https://dev.polyv.net/2019/liveproduct/zblts/send-admin-msg/
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetChatAdminData() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        Integer channelId = super.createChannel();
        LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
        liveSetChatAdminDataRequest.setChannelId(channelId)
                .setCoverImage("http://pic.sc.chinaz.com/files/pic/pic9/202010/bpic21538.jpg")
                .setCoverHref("http://www.baidu.com")
                .setRequestId(LiveSignUtil.generateUUID());
        Boolean result = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试设置播放器暖场图片成功{}", JSON.toJSONString(result));
        }
        
    }
    
    
    /**
     *
     * 设置频道的暖场设置开关，API地址：https://dev.polyv.net/2019/liveproduct/zblts/send-admin-msg/
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerWarmupEnable() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        Integer channelId = super.createChannel();
        LiveSetWarmupEnableRequest liveSetWarmupEnableRequest = new LiveSetWarmupEnableRequest();
        liveSetWarmupEnableRequest.setChannelId(channelId)
               .setWarmUpEnabled(LiveConstant.Flag.YES.getFlag())
                .setRequestId(LiveSignUtil.generateUUID());
        Boolean result = new LivePlayerServiceImpl().setPlayerWarmupEnable(liveSetWarmupEnableRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试设置频道的暖场设置开关成功{}", JSON.toJSONString(result));
        }
        
    }
    
   
    
    /**
     *
     *设置播放器Logo，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatelogo/
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerLogo() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        Integer channelId = super.createChannel();
        LiveSetPlayerLogoRequest liveSetPlayerLogoRequest = new LiveSetPlayerLogoRequest();
        liveSetPlayerLogoRequest.setChannelId(channelId)
                .setLogoHref("http://www.baidu.com/huava")
                .setLogoPosition(LiveConstant.LogoPosition.BL.getPosition())
                .setLogoImage("https://c-ssl.duitang.com/uploads/item/202005/07/20200507133619_rpiso.thumb.1000_0.jpeg")
                .setLogoOpacity(1D)
                .setRequestId(LiveSignUtil.generateUUID());
        Boolean result = new LivePlayerServiceImpl().setPlayerLogo(liveSetPlayerLogoRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试设置播放器Logo成功{}", JSON.toJSONString(result));
        }
    }
 
    /**
     *
     *设置播放器暂停广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatestop/
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerPauseAdvert() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        Integer channelId = super.createChannel();
        LiveSetPlayerPauseAdvertRequest liveSetPlayerPauseAdvertRequest = new LiveSetPlayerPauseAdvertRequest();
        liveSetPlayerPauseAdvertRequest.setChannelId(channelId)
                .setEnabled(LiveConstant.Flag.YES.getFlag())
                .setStopAdvertHref("http://www.baidu.com")
                .setStopAdvertImage("https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg")
                .setRequestId(LiveSignUtil.generateUUID());
        Boolean result = new LivePlayerServiceImpl().setPlayerPauseAdvert(liveSetPlayerPauseAdvertRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试设置播放器暂停广告成功{}", JSON.toJSONString(result));
            
        }
    }
    
    
    /**
     *
     *设置播放器片头广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatehead/
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerHeaderAdvert() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        Integer channelId = super.createChannel();
        LiveSetPlayerHeaderAdvertRequest liveSetPlayerHeaderAdvertRequest = new LiveSetPlayerHeaderAdvertRequest();
        liveSetPlayerHeaderAdvertRequest.setChannelId(channelId)
                .setEnabled(LiveConstant.Flag.YES.getFlag())
                .setHeadAdvertDuration(5)
                .setHeadAdvertHeight(100)
                .setHeadAdvertType(LiveConstant.HeadAdvertType.IMAGE.getDesc())
                .setHeadAdvertWidth(100)
                .setHeadAdvertHref("http://www.baidu.com")
                .setHeadAdvertMediaUrl("https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg")
                .setRequestId(LiveSignUtil.generateUUID());
        Boolean result = new LivePlayerServiceImpl().setPlayerHeaderAdvert(liveSetPlayerHeaderAdvertRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试设设置播放器片头广告成功{}", JSON.toJSONString(result));
            
        }
    }
    
    /**
     * 设置播放器暖场视频，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatewarmupflv/
     * @param liveSetWarmupVedioRequest 设置播放器暖场视频请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setPlayerLogo( liveSetWarmupVedioRequest)
            throws IOException, NoSuchAlgorithmException
    
    /**
     *
     *设置播放器暖场视频，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatewarmupflv/
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetPlayerWarmUpVedio() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        Integer channelId = super.createChannel();
        LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
        liveSetWarmupVedioRequest.setChannelId(channelId)
                .setWarmUpFlv("https://v.cnezsoft.com/zentao/introduction_catelog.mp4?sign=e1119d6ab99b07ab28c2f0508acc76e7&t=5f966aea")
                .setRequestId(LiveSignUtil.generateUUID());
        Boolean result = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试设置播放器暖场视频成功{}", JSON.toJSONString(result));
            
        }
    }
}
