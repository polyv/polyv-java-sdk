package net.polyv.live.service.account.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.account.LiveAccountMicDurationRequest;
import net.polyv.live.entity.account.LiveAccountMicDurationResponse;
import net.polyv.live.entity.account.LiveAccountPlaybackCallbackRequest;
import net.polyv.live.entity.account.LiveAccountRecordCallbackRequest;
import net.polyv.live.entity.account.LiveAccountStreamCallbackRequest;
import net.polyv.live.entity.account.LiveAccountSwitchRequest;
import net.polyv.live.entity.account.LiveAccountSwitchResponse;
import net.polyv.live.entity.account.LiveCreateAccountTokenRequest;
import net.polyv.live.entity.account.LiveListAccountChannelBasicRequest;
import net.polyv.live.entity.account.LiveListAccountChannelBasicResponse;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.entity.account.LiveUpdateAccountSwitchRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.account.ILiveAccountService;

/**
 * 直播账号级管理
 * @author: sadboy
 **/
public class LiveAccountServiceImpl extends LiveBaseService implements ILiveAccountService {
    
    /**
     * 查询账号下所有频道详细信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zhsz/chennel-detail/
     * @param liveListAccountDetailRequest 查询所有频道详细信息请求实体
     * @return 账号下所有频道详细信息返回实体
     * @throws IOException 异常
     */
    @Override
    public LiveListAccountDetailResponse listAccountDetail(LiveListAccountDetailRequest liveListAccountDetailRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ACCOUNT_LIST_CHANNEL_DETAIL_URL;
        LiveListAccountDetailResponse liveListAccountDetailResponse = this.basePost(url, liveListAccountDetailRequest,
                LiveListAccountDetailResponse.class);
        return liveListAccountDetailResponse;
    }
    
    /**
     * 查询账号下的频道列表(返回频道号列表)
     * API地址：https://dev.polyv.net/2016/liveproduct/l-api/zhsz/channels/
     * @param liveListAccountRequest 查询账号下的频道列表 请求体
     * @return 查询账号下的频道列表返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListAccountResponse listAccount(LiveListAccountRequest liveListAccountRequest)
            throws IOException, NoSuchAlgorithmException {
        liveListAccountRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.ACCOUNT_LIST_CHANNEL_URL, liveListAccountRequest.getUserId());
        LiveListAccountResponse liveListAccountResponse = this.basePost(url, liveListAccountRequest,
                LiveListAccountResponse.class);
        return liveListAccountResponse;
    }
    
    /**
     * 获取账号连麦分钟数使用量与剩余量
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zhsz/mic-duration/
     * @param liveAccountMicDurationRequest 获取账号连麦分钟数使用量与剩余量请求实体
     * @return 获取账号连麦分钟数使用量与剩余量返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveAccountMicDurationResponse micDuration(LiveAccountMicDurationRequest liveAccountMicDurationRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ACCOUNT_MIC_DURATION_URL;
        LiveAccountMicDurationResponse liveAccountMicDurationResponse = this.baseGet(url, liveAccountMicDurationRequest,
                LiveAccountMicDurationResponse.class);
        return liveAccountMicDurationResponse;
    }
    
    /**
     * 设置功能开关状态
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/switch-update/
     * @param liveUpdateAccountSwitchRequest 设置功能开关状态请求实体
     * @return 设置功能开关状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateAccountSwitch(LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ACCOUNT_SWITCH_UPDATE_URL;
        String liveUpdateAccountSwitchResponse = this.basePost(url, liveUpdateAccountSwitchRequest, String.class);
        return liveUpdateAccountSwitchResponse;
    }
    
    /**
     * 设置账号单点登录的token
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zhsz/set-user-login-token/
     * @param liveCreateAccountTokenRequest 设置账号单点登录的token请求实体
     * @return 设置账号单点登录的token返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String createAccountToken(LiveCreateAccountTokenRequest liveCreateAccountTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ACCOUNT_TOKEN_CREATE_URL;
        String liveCreateAccountTokenResponse = this.basePost(url, liveCreateAccountTokenRequest, String.class);
        return liveCreateAccountTokenResponse;
    }
    
    /**
     * 设置直播状态回调通知url
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/set-stream-callback/
     * @param liveAccountStreamCallbackRequest 设置直播状态回调通知url请求体
     * @return 设置直播状态回调通知url返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateStreamCallbackUrl(LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest)
            throws IOException, NoSuchAlgorithmException {
        liveAccountStreamCallbackRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.ACCOUNT_STREAM_CALLBACK_URL,
                liveAccountStreamCallbackRequest.getUserId());
        String liveAccountStreamCallbackResponse = this.basePost(url, liveAccountStreamCallbackRequest, String.class);
        return liveAccountStreamCallbackResponse;
    }
    
    /**
     * 设置转存成功回调通知url
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/set-playback-callback/
     * @param liveAccountPlaybackCallbackRequest 设置转存成功回调通知url请求实体
     * @return 设置转存成功回调通知url返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updatePlaybackCallbackUrl(LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest)
            throws IOException, NoSuchAlgorithmException {
        liveAccountPlaybackCallbackRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.ACCOUNT_PLAYBACK_CALLBACK_URL,
                liveAccountPlaybackCallbackRequest.getUserId());
        String liveAccountPlaybackCallbackResponse = this.basePost(url, liveAccountPlaybackCallbackRequest,
                String.class);
        return liveAccountPlaybackCallbackResponse;
    }
    
    /**
     * 设置录制回调通知url
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/set-record-callback/
     * @param liveAccountRecordCallbackRequest 设置录制回调通知url请求实体
     * @return 设置录制回调通知url返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateRecordCallbackUrl(LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest)
            throws IOException, NoSuchAlgorithmException {
        liveAccountRecordCallbackRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.ACCOUNT_RECORD_CALLBACK_URL,
                liveAccountRecordCallbackRequest.getUserId());
        String liveAccountRecordCallbackResponse = this.basePost(url, liveAccountRecordCallbackRequest, String.class);
        return liveAccountRecordCallbackResponse;
    }
    
    /**
     * 查询功能开关状态接口
     * 注：isClosePreview当enabled值为Y时，表示的是关闭系统观看页;closeDanmu当enabled值为Y时，表示的是关闭弹幕;closeChaterList当enabled值为Y时，表示的是关闭在线列表
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/switch-get/
     * @param liveAccountSwitchRequest 查询功能开关状态接口请求实体
     * @return 查询功能开关状态接口返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveAccountSwitchResponse accountSwitch(LiveAccountSwitchRequest liveAccountSwitchRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ACCOUNT_SWITCH_URL;
        LiveAccountSwitchResponse.ChannelSwitch[] channelSwitches = this.baseGet(url, liveAccountSwitchRequest,
                LiveAccountSwitchResponse.ChannelSwitch[].class);
        channelSwitches = channelSwitches == null ? new LiveAccountSwitchResponse.ChannelSwitch[]{} : channelSwitches;
        LiveAccountSwitchResponse liveAccountSwitchResponse = new LiveAccountSwitchResponse().setChannelSwitches(
                Arrays.asList(channelSwitches));
        return liveAccountSwitchResponse;
    }
    
    
    /**
     * 查询账号下所有频道缩略信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/get-simple-channel-list/
     * @param liveListAccountChannelBasicRequest 查询账号下所有频道缩略信息请求实体
     * @return 查询账号下所有频道缩略信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListAccountChannelBasicResponse listChannelBasic(
            LiveListAccountChannelBasicRequest liveListAccountChannelBasicRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_URL;
        LiveListAccountChannelBasicResponse liveListAccountChannelBasicResponse = this.baseGet(url,
                liveListAccountChannelBasicRequest, LiveListAccountChannelBasicResponse.class);
        return liveListAccountChannelBasicResponse;
    }
    
}
