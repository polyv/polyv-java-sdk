package net.polyv.live.v1.service.quick;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListRequest;
import net.polyv.live.v1.entity.quick.QuickCreatePPTChannelRequest;
import net.polyv.live.v1.entity.quick.QuickCreateVideoChannelRequest;
import net.polyv.live.v1.quick.LiveChannelManager;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 频道快速操作
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelManagerTest extends BaseTest {
    
    /**
     * 快速创建三分屏频道
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
    @Test
    public void testCreateEasyPPT() throws IOException, NoSuchAlgorithmException {
        QuickCreatePPTChannelRequest quickCreatePPTChannelRequest = new QuickCreatePPTChannelRequest();
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH,instance.get(Calendar.DAY_OF_MONTH)+1);
        LiveChannelManager.ChannelInfo channelInfo;
        quickCreatePPTChannelRequest.setName("快速创建三分屏频道")
                .setChannelPasswd(getRandomString(6))
                .setLinkMicLimit(5)
                .setPublisher("thomas教授")
                .setPureRtcEnabled("Y")
                .setStartTime(instance.getTime().getTime())
                .setCoverImg("https://wwwimg.polyv.net/assets/dist/images/v2020/page-home/brand-advantage/row-2-3.svg")
                .setSplashImg(
                        "https://wwwimg.polyv.net/assets/dist/images/v2020/news-info-md/product-dynamic-bg_v3.jpg")
                .setDesc("POLYV保利威是广州易方信息科技股份有限公司旗下拥有自主知识产权的视频云计算服务平台，其中包含 云点播 、云直播 " +
                        "和其它视频服务，提供API、SDK技术支持，并拥有国家专利级别的PlaySafe®视频版权保护技术及三套CDN加速，致力为用户提供稳定、安全、快速的企业级视频云服务。")
               
                .setNickname("thomas-gogo")
                .setActor("大师")
                .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2069606413,3553249962&fm=26&gp=0.jpg")
              
                .setCoverImage("https://s1.videocc.net/live-watch/assets/img/default-splash-img.07657078.jpg")
                .setCoverHref("http://www.baidu.com")
//              .setWarmUpFlv("http://www.w3school.com.cn/example/html5/mov_bbb.mp4");
                .setRequestId(LiveSignUtil.generateUUID());
        channelInfo = LiveChannelManager.createEasyPPT(quickCreatePPTChannelRequest);
        Assert.assertNotNull(channelInfo);
        log.debug("快速创建三分屏频道成功，{}", JSON.toJSONString(channelInfo));
        
        
        
        
    }
    
    /**
     * 快速创建三分屏频道并批量创建子频道
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
    @Test
    public void testCreateEasyPPTAndSonChannel() throws IOException, NoSuchAlgorithmException {
        QuickCreatePPTChannelRequest quickCreatePPTChannelRequest = new QuickCreatePPTChannelRequest();
        LiveChannelManager.ChannelInfo channelInfo;
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH,instance.get(Calendar.DAY_OF_MONTH)+1);
        quickCreatePPTChannelRequest.setName("快速创建三分屏频道-子频道")
                .setChannelPasswd(getRandomString(6))
                .setLinkMicLimit(5)
                .setPublisher("thomas教授")
                .setPureRtcEnabled("Y")
                .setStartTime(instance.getTime().getTime())
                .setCoverImg("https://wwwimg.polyv.net/assets/dist/images/v2020/page-home/brand-advantage/row-2-3.svg")
                .setSplashImg(
                        "https://wwwimg.polyv.net/assets/dist/images/v2020/news-info-md/product-dynamic-bg_v3.jpg")
                .setDesc("POLYV保利威是广州易方信息科技股份有限公司旗下拥有自主知识产权的视频云计算服务平台，其中包含 云点播 、云直播 " +
                        "和其它视频服务，提供API、SDK技术支持，并拥有国家专利级别的PlaySafe®视频版权保护技术及三套CDN加速，致力为用户提供稳定、安全、快速的企业级视频云服务。") .setNickname("thomas-gogo")
                .setActor("大师")
                .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2069606413,3553249962&fm=26&gp=0.jpg")
                .setCoverImage("https://s1.videocc.net/live-watch/assets/img/default-splash-img.07657078.jpg")
                .setCoverHref("http://www.baidu.com")
//              .setWarmUpFlv("http://www.w3school.com.cn/example/html5/mov_bbb.mp4");
                .setRequestId(LiveSignUtil.generateUUID());
        
        LiveCreateSonChannelListRequest liveCreateSonChannelListRequest = new LiveCreateSonChannelListRequest();
        List<LiveCreateSonChannelListRequest.SonChannel> sonChannels =
                new ArrayList<LiveCreateSonChannelListRequest.SonChannel>();
        LiveCreateSonChannelListRequest.SonChannel sonChannel1 = new LiveCreateSonChannelListRequest.SonChannel();
        sonChannel1.setRole("Guest")
                .setNickname("嘉宾大大")
                .setPasswd(getRandomString(10))
                .setActor("教授")
                .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                        "3965499425&fm=26&gp=0.jpg");
        sonChannels.add(sonChannel1);
        sonChannel1 = new LiveCreateSonChannelListRequest.SonChannel();
        sonChannel1.setRole(null)
                .setNickname("助教大大")
                .setPasswd(getRandomString(10))
                .setActor("王者")
                .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                        "3965499425&fm=26&gp=0.jpg");
        sonChannels.add(sonChannel1);
        liveCreateSonChannelListRequest
                .setSonChannels(sonChannels);
        channelInfo = LiveChannelManager.createEasyPPT(quickCreatePPTChannelRequest, liveCreateSonChannelListRequest);
        Assert.assertNotNull(channelInfo);
        log.debug("快速创建三分屏频道成功，{}", JSON.toJSONString(channelInfo));
    }
    
    /**
     * 快速创建纯视频频道
     * 约束：2、同时设置暖场图片和暖场视频只生效暖场视频。
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
    @Test
    public void testCreateEasyVideo() throws IOException, NoSuchAlgorithmException {
        QuickCreateVideoChannelRequest quickCreateVideoChannelRequest = new QuickCreateVideoChannelRequest();
        LiveChannelManager.ChannelInfo channelInfo;
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH,instance.get(Calendar.DAY_OF_MONTH)+1);
        quickCreateVideoChannelRequest.setName("快速创建纯视频频道")
                .setChannelPasswd(getRandomString(6))
                .setLinkMicLimit(5)
                .setPublisher("thomas教授")
                .setStartTime(instance.getTime().getTime())
                .setPureRtcEnabled("Y")
                .setCoverImg("https://wwwimg.polyv.net/assets/dist/images/v2020/page-home/brand-advantage/row-2-3.svg")
                .setSplashImg(
                        "https://wwwimg.polyv.net/assets/dist/images/v2020/news-info-md/product-dynamic-bg_v3.jpg")
                .setDesc("POLYV保利威是广州易方信息科技股份有限公司旗下拥有自主知识产权的视频云计算服务平台，其中包含 云点播 、云直播 " +
                        "和其它视频服务，提供API、SDK技术支持，并拥有国家专利级别的PlaySafe®视频版权保护技术及三套CDN加速，致力为用户提供稳定、安全、快速的企业级视频云服务。")
                .setCoverImage("https://s1.videocc.net/live-watch/assets/img/default-splash-img.07657078.jpg")
                .setCoverHref("http://www.baidu.com")
//              .setWarmUpFlv("http://www.w3school.com.cn/example/html5/mov_bbb.mp4");
                .setNickname("thomas-gogo")
                .setActor("大师")
                .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2069606413,3553249962&fm=26&gp=0.jpg")
                .setRequestId(LiveSignUtil.generateUUID());
        channelInfo = LiveChannelManager.createEasyVideo(quickCreateVideoChannelRequest);
        Assert.assertNotNull(channelInfo);
        log.debug("快速创建纯视频频道成功，{}", JSON.toJSONString(channelInfo));
    }
    
    /**
     * 测试用例结束
     */
}