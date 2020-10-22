package net.polyv.live.service.account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.account.LiveAccountMicDurationRequest;
import net.polyv.live.entity.account.LiveAccountMicDurationResponse;
import net.polyv.live.entity.account.LiveAccountPlaybackCallbackRequest;
import net.polyv.live.entity.account.LiveAccountRecordCallbackRequest;
import net.polyv.live.entity.account.LiveAccountStreamCallbackRequest;
import net.polyv.live.entity.account.LiveAccountSwitchRequest;
import net.polyv.live.entity.account.LiveAccountSwitchResponse;
import net.polyv.live.entity.account.LiveAccountUserDurationsRequest;
import net.polyv.live.entity.account.LiveAccountUserDurationsResponse;
import net.polyv.live.entity.account.LiveCreateAccountTokenRequest;
import net.polyv.live.entity.account.LiveListAccountChannelBasicRequest;
import net.polyv.live.entity.account.LiveListAccountChannelBasicResponse;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.entity.account.LiveUpdateAccountSwitchRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.account.impl.LiveAccountServiceImpl;

/**
 * @author: sadboy
 **/
@Slf4j
public class LiveAccountImplTest extends BaseTest {
    
    @Test
    public void testJson() {
        LiveListAccountResponse liveListAccountResponse = new LiveListAccountResponse();
        ArrayList<Integer> channelList = new ArrayList<>();
        channelList.add(2);
        channelList.add(3);
        channelList.add(4);
        channelList.add(5);
        log.debug(JSON.toJSONString(liveListAccountResponse.setChannels(channelList)));
        System.out.println(JSON.parseObject("{\"result\":[\"2\",\"3\",\"4\",\"5\"]}", LiveListAccountResponse.class)
                .getChannels());
    }
    
    /**
     * 分页查询账号下所有频道详细信息成功
     * @throws IOException
     */
    @Test
    public void testListAccountDetail() throws IOException, NoSuchAlgorithmException {
        LiveListAccountDetailRequest liveListAccountDetailRequest = new LiveListAccountDetailRequest();
        liveListAccountDetailRequest.setCurrentPage(1);
        LiveListAccountDetailResponse liveListAccountDetailResponse = new LiveAccountServiceImpl().listAccountDetail(
                liveListAccountDetailRequest);
        Assert.assertNotNull(liveListAccountDetailResponse);
        if (liveListAccountDetailResponse != null) {
            //to do something ......
            log.debug("分页查询账号下所有频道详细信息成功,{}", JSON.toJSONString(liveListAccountDetailResponse));
        }
    }
    
    /**
     * 测试查询账号下的频道列表(频道号列表)
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListAccount() throws IOException, NoSuchAlgorithmException {
        LiveListAccountRequest liveListAccountRequest = new LiveListAccountRequest();
        LiveListAccountResponse liveListAccountResponse = new LiveAccountServiceImpl().listAccount(
                liveListAccountRequest);
        Assert.assertNotNull(liveListAccountResponse);
        if (liveListAccountResponse != null) {
            //to do something ......
            log.debug("测试查询账号下的频道列表成功,{}", JSON.toJSONString(liveListAccountResponse));
        }
    }
    
    /**
     * 测试获取账号连麦分钟数使用量与剩余量
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testMicDuration() throws IOException, NoSuchAlgorithmException {
        LiveAccountMicDurationRequest liveAccountMicDurationRequest = new LiveAccountMicDurationRequest();
        LiveAccountMicDurationResponse liveAccountMicDurationResponse = new LiveAccountServiceImpl().micDuration(
                liveAccountMicDurationRequest);
        Assert.assertNotNull(liveAccountMicDurationResponse);
        if (liveAccountMicDurationResponse != null) {
            //to do something ......
            log.debug("测试获取账号连麦分钟数使用量与剩余量成功,{}", JSON.toJSONString(liveAccountMicDurationResponse));
        }
    }
    
    /**
     * 测试设置功能开关状态
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateAccountSwitch() throws IOException, NoSuchAlgorithmException {
        LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest = new LiveUpdateAccountSwitchRequest();
        liveUpdateAccountSwitchRequest.setType(LiveConstant.ChannelSwitch.AUTO_PLAY.getDesc()).setEnabled("N");
        String liveUpdateAccountSwitchResponse = new LiveAccountServiceImpl().updateAccountSwitch(
                liveUpdateAccountSwitchRequest);
        Assert.assertNotNull(liveUpdateAccountSwitchResponse);
        if ("true".equals(liveUpdateAccountSwitchResponse)) {
            //to do something ......
            log.debug("设置功能开关状态成功,{}", liveUpdateAccountSwitchResponse);
        }
    }
    
    /**
     * 测试设置账号单点登录的token
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateAccountToken() throws IOException, NoSuchAlgorithmException {
        LiveCreateAccountTokenRequest liveCreateAccountTokenRequest = new LiveCreateAccountTokenRequest();
        liveCreateAccountTokenRequest.setToken("5ZiQIhN0izj3NIMp");
        String liveCreateAccountTokenResponse = new LiveAccountServiceImpl().createAccountToken(
                liveCreateAccountTokenRequest);
        Assert.assertNotNull(liveCreateAccountTokenResponse);
        if ("success".equals(liveCreateAccountTokenResponse)) {
            //to do something ......
            log.debug("测试设置账号单点登录的token成功,{}", liveCreateAccountTokenResponse);
        }
    }
    
    /**
     * 测试设置直播状态回调通知url
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateStreamCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest = new LiveAccountStreamCallbackRequest();
        liveAccountStreamCallbackRequest.setUrl("http://www.abc.com/callback");
        String liveAccountStreamCallbackResponse = new LiveAccountServiceImpl().updateStreamCallbackUrl(
                liveAccountStreamCallbackRequest);
        Assert.assertNotNull(liveAccountStreamCallbackResponse);
        if ("success".equals(liveAccountStreamCallbackResponse)) {
            //to do something ......
            log.debug("测试设置直播状态回调通知url成功,{}", liveAccountStreamCallbackResponse);
        }
    }
    
    /**
     * 测试设置转存成功回调通知url
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdatePlaybackCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest =
                new LiveAccountPlaybackCallbackRequest();
        liveAccountPlaybackCallbackRequest.setUrl("http://www.abc.com/callback");
        String liveAccountPlaybackCallbackResponse = new LiveAccountServiceImpl().updatePlaybackCallbackUrl(
                liveAccountPlaybackCallbackRequest);
        Assert.assertNotNull(liveAccountPlaybackCallbackResponse);
        if (liveAccountPlaybackCallbackResponse != null) {
            //to do something ......
            log.debug("测试设置转存成功回调通知url成功,{}", liveAccountPlaybackCallbackResponse);
        }
    }
    
    /**
     * 测试设置录制回调通知url
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateRecordCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest = new LiveAccountRecordCallbackRequest();
        liveAccountRecordCallbackRequest.setUrl("http://www.abc.com/callback");
        String liveAccountRecordCallbackResponse = new LiveAccountServiceImpl().updateRecordCallbackUrl(
                liveAccountRecordCallbackRequest);
        Assert.assertNotNull(liveAccountRecordCallbackResponse);
        if (liveAccountRecordCallbackResponse != null) {
            //to do something ......
            log.debug("测试设置录制回调通知url成功,{}", liveAccountRecordCallbackResponse);
        }
    }
    
    /**
     * 测试查询功能开关状态接口
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAccountSwitch() throws IOException, NoSuchAlgorithmException {
        LiveAccountSwitchRequest liveAccountSwitchRequest = new LiveAccountSwitchRequest();
        liveAccountSwitchRequest.setChannelId(null);
        LiveAccountSwitchResponse liveAccountSwitchResponse = new LiveAccountServiceImpl().accountSwitch(
                liveAccountSwitchRequest);
        Assert.assertNotNull(liveAccountSwitchResponse);
        if (liveAccountSwitchResponse != null) {
            //to do something ......
            log.debug("测试查询功能开关状态接口成功,{}", JSON.toJSONString(liveAccountSwitchResponse));
        }
    }
    
    /**
     * 测试查询账号下所有频道缩略信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelBasic() throws IOException, NoSuchAlgorithmException {
        LiveListAccountChannelBasicRequest liveListAccountChannelBasicRequest =
                new LiveListAccountChannelBasicRequest();
        liveListAccountChannelBasicRequest.setCategoryId(null)
                .setWatchStatus("end")
                .setKeyword("勿删")
                .setPageSize(null)
                .setCurrentPage(1);
        LiveListAccountChannelBasicResponse liveListAccountChannelBasicResponse =
                new LiveAccountServiceImpl().listChannelBasic(
                liveListAccountChannelBasicRequest);
        Assert.assertNotNull(liveListAccountChannelBasicResponse);
        if (liveListAccountChannelBasicResponse != null) {
            //to do something ......
            log.debug("测试查询账号下所有频道缩略信息成功,{}", JSON.toJSONString(liveListAccountChannelBasicResponse));
        }
    }
    
    /**
     * 测试查询账户分钟数
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUserDurations() throws IOException, NoSuchAlgorithmException {
        LiveAccountUserDurationsRequest liveAccountUserDurationsRequest = new LiveAccountUserDurationsRequest();
        LiveAccountUserDurationsResponse liveAccountUserDurationsResponse = new LiveAccountServiceImpl().userDurations(
                liveAccountUserDurationsRequest);
        Assert.assertNotNull(liveAccountUserDurationsResponse);
        if (liveAccountUserDurationsResponse != null) {
            //to do something ......
            log.debug("测试查询账户分钟数成功,{}", JSON.toJSONString(liveAccountUserDurationsResponse));
        }
    }
    
}
