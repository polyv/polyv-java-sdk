package net.polyv.live.service.player;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.chat.LiveSetChatAdminDataRequest;
import net.polyv.live.entity.player.LiveSetPlayerImgRequest;
import net.polyv.live.entity.player.LiveSetPlayerLogoRequest;
import net.polyv.live.entity.player.LiveSetWarmupEnableRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.chat.impl.LiveChatRoomServiceImpl;
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
                .setLogoOpacity(1f)
                .setRequestId(LiveSignUtil.generateUUID());
        Boolean result = new LivePlayerServiceImpl().setPlayerLogo(liveSetPlayerLogoRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试设置播放器Logo成功{}", JSON.toJSONString(result));
        }
        
    }
    
}
