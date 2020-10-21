package net.polyv.live.service.account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.account.LiveAccountMicDurationRequest;
import net.polyv.live.entity.account.LiveAccountMicDurationResponse;
import net.polyv.live.entity.account.LiveAccountPlaybackCallbackRequest;
import net.polyv.live.entity.account.LiveAccountRecordCallbackRequest;
import net.polyv.live.entity.account.LiveAccountStreamCallbackRequest;
import net.polyv.live.entity.account.LiveCreateAccountTokenRequest;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.entity.account.LiveUpdateAccountSwitchRequest;

/**
 * 直播账号级管理
 * @author: sadboy
 **/
public interface ILiveAccountService {
    
    /**
     * 查询账号下所有频道详细信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zhsz/chennel-detail/
     * @param liveListAccountDetailRequest 查询所有频道详细信息请求实体
     * @return 账号下所有频道详细信息返回实体
     * @throws IOException 异常
     */
    LiveListAccountDetailResponse listAccountDetail(LiveListAccountDetailRequest liveListAccountDetailRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询账号下的频道列表(返回频道号列表)
     * API地址：https://dev.polyv.net/2016/liveproduct/l-api/zhsz/channels/
     * @param liveListAccountRequest 查询账号下的频道列表 请求体
     * @return 查询账号下的频道列表返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListAccountResponse listAccount(LiveListAccountRequest liveListAccountRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取账号连麦分钟数使用量与剩余量
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zhsz/mic-duration/
     * @param liveAccountMicDurationRequest 获取账号连麦分钟数使用量与剩余量请求实体
     * @return 获取账号连麦分钟数使用量与剩余量返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveAccountMicDurationResponse micDuration(LiveAccountMicDurationRequest liveAccountMicDurationRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置功能开关状态
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/switch-update/
     * @param liveUpdateAccountSwitchRequest 设置功能开关状态请求实体
     * @return 设置功能开关状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateAccountSwitch(LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置账号单点登录的token
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zhsz/set-user-login-token/
     * @param liveCreateAccountTokenRequest 设置账号单点登录的token请求实体
     * @return 设置账号单点登录的token返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createAccountToken(LiveCreateAccountTokenRequest liveCreateAccountTokenRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置直播状态回调通知url
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/set-stream-callback/
     * @param liveAccountStreamCallbackRequest 设置直播状态回调通知url请求体
     * @return 设置直播状态回调通知url返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateStreamCallbackUrl(LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置转存成功回调通知url
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/set-playback-callback/
     * @param liveAccountPlaybackCallbackRequest 设置转存成功回调通知url请求实体
     * @return 设置转存成功回调通知url返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updatePlaybackCallbackUrl(LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置录制回调通知url
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/set-record-callback/
     * @param liveAccountRecordCallbackRequest 设置录制回调通知url请求实体
     * @return 设置录制回调通知url返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateRecordCallbackUrl(LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
