package net.polyv.live.service.account;

import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;

import java.io.IOException;

/**
 *  直播账号级管理
 * @author: sadboy
 * @date: 2020/9/29
 **/
public interface ILiveAccountService {

    LiveListAccountDetailResponse listAccountDetail(LiveListAccountDetailRequest liveListAccountDetailRequest) throws IOException;

}
