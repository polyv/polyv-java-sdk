package net.polyv.live.v1.service.quick;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.v1.entity.quick.QuickCreatePPTChannelRequest;
import net.polyv.live.v1.entity.quick.QuickCreateVideoChannelRequest;
import net.polyv.live.v1.quick.LiveChannelManager;
import net.polyv.live.v1.service.BaseTest;

/**
 * 频道快速操作
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelManagerTest extends BaseTest {
    
    /**
     * 快速创建三分屏频道
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateEasyPPT() throws IOException, NoSuchAlgorithmException {
        QuickCreatePPTChannelRequest quickCreatePPTChannelRequest = new QuickCreatePPTChannelRequest();
        LiveChannelBasicInfoResponse easyPPTChannel;
        quickCreatePPTChannelRequest.setName("快速创建三分屏频道")
                .setChannelPasswd(getRandomString(6))
                .setLinkMicLimit(5)
                .setPublisher("sadboy教授")
                .setPureRtcEnabled("Y")
                .setCoverImg("https://wwwimg.polyv.net/assets/dist/images/v2020/page-home/brand-advantage/row-2-3.svg")
                .setSplashImg(
                        "https://wwwimg.polyv.net/assets/dist/images/v2020/news-info-md/product-dynamic-bg_v3.jpg")
                .setDesc("POLYV保利威是广州易方信息科技股份有限公司旗下拥有自主知识产权的视频云计算服务平台，其中包含 云点播 、云直播 " +
                        "和其它视频服务，提供API、SDK技术支持，并拥有国家专利级别的PlaySafe®视频版权保护技术及三套CDN加速，致力为用户提供稳定、安全、快速的企业级视频云服务。");
        easyPPTChannel = LiveChannelManager.createEasyPPT(quickCreatePPTChannelRequest);
        Assert.assertNotNull(easyPPTChannel);
        log.debug("快速创建三分屏频道成功，{}", JSON.toJSONString(easyPPTChannel));
    }
    
    /**
     * 快速创建纯视频频道
     * 约束：2、同时设置暖场图片和暖场视频只生效暖场视频。
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateEasyVideo() throws IOException, NoSuchAlgorithmException {
        QuickCreateVideoChannelRequest quickCreateVideoChannelRequest = new QuickCreateVideoChannelRequest();
        LiveChannelBasicInfoResponse easyVideo;
        quickCreateVideoChannelRequest.setName("快速创建纯视频频道")
                .setChannelPasswd(getRandomString(6))
                .setLinkMicLimit(5)
                .setPublisher("sadboy教授")
                .setPureRtcEnabled("Y")
                .setCoverImg("https://wwwimg.polyv.net/assets/dist/images/v2020/page-home/brand-advantage/row-2-3.svg")
                .setSplashImg(
                        "https://wwwimg.polyv.net/assets/dist/images/v2020/news-info-md/product-dynamic-bg_v3.jpg")
                .setDesc("POLYV保利威是广州易方信息科技股份有限公司旗下拥有自主知识产权的视频云计算服务平台，其中包含 云点播 、云直播 " +
                        "和其它视频服务，提供API、SDK技术支持，并拥有国家专利级别的PlaySafe®视频版权保护技术及三套CDN加速，致力为用户提供稳定、安全、快速的企业级视频云服务。")
                .setCoverImage("https://s1.videocc.net/live-watch/assets/img/default-splash-img.07657078.jpg")
                .setCoverHref("http://www.baidu.com");
//              .setWarmUpFlv("http://www.w3school.com.cn/example/html5/mov_bbb.mp4");
        easyVideo = LiveChannelManager.createEasyVideo(quickCreateVideoChannelRequest);
        Assert.assertNotNull(easyVideo);
        log.debug("快速创建纯视频频道成功，{}", JSON.toJSONString(easyVideo));
    }
    
    /**
     * 测试用例结束
     */
}
