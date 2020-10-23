package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalResponse;
import net.polyv.live.entity.web.auth.LiveCreateChannelWriteListRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelAuthRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.web.impl.LiveWebAuthServiceImpl;

/**
 * Web观看页-页面观看条件测试用例
 * @author: sadboy
 **/
@Slf4j
public class LiveWebAuthImplTest extends BaseTest {
    
    /**
     * 测试添加单个白名单-全局白名单
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateChannelWriteList() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest = new LiveCreateChannelWriteListRequest();
        liveCreateChannelWriteListRequest.setRank(1).setCode("天王盖地虎1").setName("sadboy");
        String liveCreateChannelWriteListResponse = new LiveWebAuthServiceImpl().createChannelWriteList(
                liveCreateChannelWriteListRequest);
        Assert.assertNotNull(liveCreateChannelWriteListResponse);
        if ("success".equals(liveCreateChannelWriteListResponse)) {
            //to do something ......
            log.debug("测试添加单个白名单-全局白名单成功,{}", liveCreateChannelWriteListResponse);
        }
    }
    
    /**
     * 测试设置观看条件
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuth() throws IOException, NoSuchAlgorithmException {
        LiveChannelSettingRequest.AuthSetting authSetting =
                new LiveChannelSettingRequest().new AuthSetting().setAuthType(
                        LiveConstant.AuthType.CODE.getDesc())
                        .setRank(2)
                        .setEnabled("Y")
                        .setAuthCode("123456")
                        .setQcodeTips("提示文案测试2")
                        .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
        List<LiveChannelSettingRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(authSetting);
        LiveUpdateChannelAuthRequest liveUpdateChannelAuthRequest = new LiveUpdateChannelAuthRequest();
        liveUpdateChannelAuthRequest.setChannelId(1965681).setAuthSettings(authSettings);
        Boolean liveUpdateChannelAuthResponse = new LiveWebAuthServiceImpl().updateChannelAuth(
                liveUpdateChannelAuthRequest);
        Assert.assertNotNull(liveUpdateChannelAuthResponse);
        if (liveUpdateChannelAuthResponse) {
            log.debug("测试设置观看条件成功");
        }
    }
    
    /**
     * 测试通过接口设置外部授权
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelAuthExternal() throws IOException, NoSuchAlgorithmException {
        LiveChannelAuthExternalRequest liveChannelAuthExternalRequest = new LiveChannelAuthExternalRequest();
        liveChannelAuthExternalRequest.setChannelId(1965681).setExternalUri("https://dev.polyv.net/");
        LiveChannelAuthExternalResponse liveChannelAuthExternalResponse =
                new LiveWebAuthServiceImpl().updateChannelAuthExternal(
                liveChannelAuthExternalRequest);
        Assert.assertNotNull(liveChannelAuthExternalResponse);
        if (liveChannelAuthExternalResponse!=null) {
            //to do something ......
            log.debug("测试通过接口设置外部授权成功,{}", JSON.toJSONString(liveChannelAuthExternalResponse));
        }
    }
    
}
