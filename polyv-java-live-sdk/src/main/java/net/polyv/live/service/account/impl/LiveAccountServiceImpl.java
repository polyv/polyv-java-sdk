package net.polyv.live.service.account.impl;

import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.account.ILiveAccountService;

import java.io.IOException;

/**
 * 直播账号级管理
 * @author: sadboy
 * @date: 2020/9/29
 **/
public class LiveAccountServiceImpl extends LiveBaseService implements ILiveAccountService {

    /**
     * 查询账号下所有频道详细信息
     * @param liveListAccountDetailRequest 查询所有频道详细信息请求实体
     * @return 账号下所有频道详细信息返回实体
     * @throws IOException
     */
    @Override
    public LiveListAccountDetailResponse listAccountDetail(LiveListAccountDetailRequest liveListAccountDetailRequest) throws IOException {
        String url = LiveURL.ACCOUNT_LIST_CHANNEL_DETAIL_URL;
        LiveListAccountDetailResponse liveListAccountDetailResponse = this.basePost(url, liveListAccountDetailRequest, LiveListAccountDetailResponse.class);
        return liveListAccountDetailResponse;
    }

}
