package net.polyv.live.service;

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
import net.polyv.live.entity.account.LiveCreateAccountTokenRequest;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.entity.account.LiveUpdateAccountSwitchRequest;
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
        log.debug(JSON.toJSONString(liveListAccountResponse.setChannelList(channelList)));
        System.out.println(JSON.parseObject("{\"result\":[\"2\",\"3\",\"4\",\"5\"]}", LiveListAccountResponse.class)
                .getChannelList());
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
     * 查询账号下的频道列表(频道号列表)
     * TODO 等待api端修改后再写
     * @throws IOException
     */
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
            log.debug("分页查询账号下所有频道详细信息成功,{}", JSON.toJSONString(liveListAccountResponse));
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
    
}
