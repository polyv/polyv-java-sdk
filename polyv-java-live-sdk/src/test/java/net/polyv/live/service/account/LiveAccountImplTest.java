package net.polyv.live.service.account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
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
 * 账号级操作
 * @author: sadboy
 **/
@Slf4j
public class LiveAccountImplTest extends BaseTest {
    
    /**
     * 分页查询账号下所有频道详细信息
     * @throws IOException
     */
    @Test
    public void testListAccountDetail() throws IOException, NoSuchAlgorithmException {
        LiveListAccountDetailRequest liveListAccountDetailRequest = new LiveListAccountDetailRequest();
        LiveListAccountDetailResponse liveListAccountDetailResponse;
        try {
            liveListAccountDetailRequest.setCurrentPage(1);
            liveListAccountDetailResponse = new LiveAccountServiceImpl().listAccountDetail(
                    liveListAccountDetailRequest);
            Assert.assertNotNull(liveListAccountDetailResponse);
            if (liveListAccountDetailResponse != null) {
                //to do something ......
                log.debug("分页查询账号下所有频道详细信息成功,{}", JSON.toJSONString(liveListAccountDetailResponse));
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
     * 测试查询账号下的频道列表(频道号列表)
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListAccount() throws IOException, NoSuchAlgorithmException {
        LiveListAccountRequest liveListAccountRequest = new LiveListAccountRequest();
        LiveListAccountResponse liveListAccountResponse;
        try {
            liveListAccountResponse = new LiveAccountServiceImpl().listAccount(liveListAccountRequest);
            Assert.assertNotNull(liveListAccountResponse);
            if (liveListAccountResponse != null) {
                //to do something ......
                log.debug("测试查询账号下的频道列表成功,{}", JSON.toJSONString(liveListAccountResponse));
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
     * 测试获取账号连麦分钟数使用量与剩余量
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testMicDuration() throws IOException, NoSuchAlgorithmException {
        LiveAccountMicDurationRequest liveAccountMicDurationRequest = new LiveAccountMicDurationRequest();
        LiveAccountMicDurationResponse liveAccountMicDurationResponse;
        try {
            liveAccountMicDurationResponse = new LiveAccountServiceImpl().micDuration(liveAccountMicDurationRequest);
            Assert.assertNotNull(liveAccountMicDurationResponse);
            if (liveAccountMicDurationResponse != null) {
                //to do something ......
                log.debug("测试获取账号连麦分钟数使用量与剩余量成功,{}", JSON.toJSONString(liveAccountMicDurationResponse));
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
     * 测试设置功能开关状态
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateAccountSwitch() throws IOException, NoSuchAlgorithmException {
        LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest = new LiveUpdateAccountSwitchRequest();
        Boolean liveUpdateAccountSwitchResponse;
        try {
            liveUpdateAccountSwitchRequest.setType(LiveConstant.ChannelSwitch.AUTO_PLAY.getDesc()).setEnabled("N");
            liveUpdateAccountSwitchResponse = new LiveAccountServiceImpl().updateAccountSwitch(
                    liveUpdateAccountSwitchRequest);
            Assert.assertNotNull(liveUpdateAccountSwitchResponse);
            if (liveUpdateAccountSwitchResponse) {
                //to do something ......
                log.debug("设置功能开关状态成功");
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
     * 测试设置账号单点登录的token
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateAccountToken() throws IOException, NoSuchAlgorithmException {
        LiveCreateAccountTokenRequest liveCreateAccountTokenRequest = new LiveCreateAccountTokenRequest();
        Boolean liveCreateAccountTokenResponse;
        try {
            liveCreateAccountTokenRequest.setToken("5ZiQIhN0izj3NIMp");
            liveCreateAccountTokenResponse = new LiveAccountServiceImpl().createAccountToken(
                    liveCreateAccountTokenRequest);
            Assert.assertNotNull(liveCreateAccountTokenResponse);
            if (liveCreateAccountTokenResponse) {
                //to do something ......
                log.debug("测试设置账号单点登录的token成功");
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
     * 测试设置直播状态回调通知url
     * 返回：true为设置回调成功，false为设置回调失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateStreamCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest = new LiveAccountStreamCallbackRequest();
        Boolean liveAccountStreamCallbackResponse;
        try {
            liveAccountStreamCallbackRequest.setUrl("http://www.abc.com/callback");
            liveAccountStreamCallbackResponse = new LiveAccountServiceImpl().updateStreamCallbackUrl(
                    liveAccountStreamCallbackRequest);
            Assert.assertNotNull(liveAccountStreamCallbackResponse);
            if (liveAccountStreamCallbackResponse) {
                //to do something ......
                log.debug("测试设置直播状态回调通知url成功");
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
     * 测试设置转存成功回调通知url
     * 返回：true为设置回调成功，false为设置回调失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdatePlaybackCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest =
                new LiveAccountPlaybackCallbackRequest();
        Boolean liveAccountPlaybackCallbackResponse;
        try {
            liveAccountPlaybackCallbackRequest.setUrl("http://www.abc.com/callback");
            liveAccountPlaybackCallbackResponse = new LiveAccountServiceImpl().updatePlaybackCallbackUrl(
                    liveAccountPlaybackCallbackRequest);
            Assert.assertNotNull(liveAccountPlaybackCallbackResponse);
            if (liveAccountPlaybackCallbackResponse != null) {
                //to do something ......
                log.debug("测试设置转存成功回调通知url成功,{}", liveAccountPlaybackCallbackResponse);
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
     * 测试设置录制回调通知url
     * 返回：true为设置回调成功，false为设置回调失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateRecordCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest = new LiveAccountRecordCallbackRequest();
        Boolean liveAccountRecordCallbackResponse;
        try {
            liveAccountRecordCallbackRequest.setUrl("http://www.abc.com/callback");
            liveAccountRecordCallbackResponse = new LiveAccountServiceImpl().updateRecordCallbackUrl(
                    liveAccountRecordCallbackRequest);
            Assert.assertNotNull(liveAccountRecordCallbackResponse);
            if (liveAccountRecordCallbackResponse) {
                //to do something ......
                log.debug("测试设置录制回调通知url成功");
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
     * 测试查询功能开关状态接口
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAccountSwitch() throws IOException, NoSuchAlgorithmException {
        LiveAccountSwitchRequest liveAccountSwitchRequest = new LiveAccountSwitchRequest();
        LiveAccountSwitchResponse liveAccountSwitchResponse;
        try {
            liveAccountSwitchRequest.setChannelId(null);
            liveAccountSwitchResponse = new LiveAccountServiceImpl().accountSwitch(liveAccountSwitchRequest);
            Assert.assertNotNull(liveAccountSwitchResponse);
            if (liveAccountSwitchResponse != null) {
                //to do something ......
                log.debug("测试查询功能开关状态接口成功,{}", JSON.toJSONString(liveAccountSwitchResponse));
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
     * 测试查询账号下所有频道缩略信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelBasic() throws IOException, NoSuchAlgorithmException {
        LiveListAccountChannelBasicRequest liveListAccountChannelBasicRequest =
                new LiveListAccountChannelBasicRequest();
        LiveListAccountChannelBasicResponse liveListAccountChannelBasicResponse;
        try {
            liveListAccountChannelBasicRequest.setCategoryId(null)
                    .setWatchStatus("end")
                    .setKeyword("勿删")
                    .setPageSize(null)
                    .setCurrentPage(1);
            liveListAccountChannelBasicResponse = new LiveAccountServiceImpl().listChannelBasic(
                    liveListAccountChannelBasicRequest);
            Assert.assertNotNull(liveListAccountChannelBasicResponse);
            if (liveListAccountChannelBasicResponse != null) {
                //to do something ......
                log.debug("测试查询账号下所有频道缩略信息成功,{}", JSON.toJSONString(liveListAccountChannelBasicResponse));
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
     * 测试查询账户分钟数
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUserDurations() throws IOException, NoSuchAlgorithmException {
        LiveAccountUserDurationsRequest liveAccountUserDurationsRequest = new LiveAccountUserDurationsRequest();
        LiveAccountUserDurationsResponse liveAccountUserDurationsResponse;
        try {
            liveAccountUserDurationsResponse = new LiveAccountServiceImpl().userDurations(
                    liveAccountUserDurationsRequest);
            Assert.assertNotNull(liveAccountUserDurationsResponse);
            if (liveAccountUserDurationsResponse != null) {
                //to do something ......
                log.debug("测试查询账户分钟数成功,{}", JSON.toJSONString(liveAccountUserDurationsResponse));
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
