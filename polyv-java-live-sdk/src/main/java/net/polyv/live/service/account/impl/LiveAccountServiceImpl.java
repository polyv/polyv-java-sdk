package net.polyv.live.service.account.impl;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.account.LiveAccountMicDurationRequest;
import net.polyv.live.entity.account.LiveAccountMicDurationResponse;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.entity.account.LiveUpdateAccountSwitchRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.account.ILiveAccountService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
    
}
