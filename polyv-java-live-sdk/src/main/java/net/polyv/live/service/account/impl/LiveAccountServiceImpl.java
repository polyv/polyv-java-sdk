package net.polyv.live.service.account.impl;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.account.ILiveAccountService;

import java.io.IOException;

/**
 * 直播账号级管理
 * @author: sadboy
 **/
public class LiveAccountServiceImpl extends LiveBaseService implements ILiveAccountService {

    /**
     * 查询账号下所有频道详细信息
     * @param liveListAccountDetailRequest 查询所有频道详细信息请求实体
     * @return 账号下所有频道详细信息返回实体
     * @throws IOException 异常
     */
    @Override
    public LiveListAccountDetailResponse listAccountDetail(LiveListAccountDetailRequest liveListAccountDetailRequest) throws IOException {
        String url = LiveURL.ACCOUNT_LIST_CHANNEL_DETAIL_URL;
        LiveListAccountDetailResponse liveListAccountDetailResponse = this.basePost(url, liveListAccountDetailRequest, LiveListAccountDetailResponse.class);
        return liveListAccountDetailResponse;
    }
    
    /**
     * 查询账号下的频道列表(返回频道号列表)
     * @param liveListAccountRequest 查询账号下的频道列表请求实体
     * @return 查询账号下的频道列表返回实体
     * @throws IOException 异常
     */
    @Override
    public LiveListAccountResponse listAccount(LiveListAccountRequest liveListAccountRequest) throws IOException {
        String url = LiveURL.getRealUrl(LiveURL.ACCOUNT_LIST_CHANNEL_URL, LiveGlobalConfig.USER_ID);
        this.basePost(url, liveListAccountRequest, LiveListAccountResponse.class);
        return null;
    }
    
}
