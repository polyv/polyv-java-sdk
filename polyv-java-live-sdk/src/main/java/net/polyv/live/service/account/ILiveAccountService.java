package net.polyv.live.service.account;

import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;

import java.io.IOException;

/**
 *  直播账号级管理
 * @author: sadboy
 **/
public interface ILiveAccountService {

    LiveListAccountDetailResponse listAccountDetail(LiveListAccountDetailRequest liveListAccountDetailRequest) throws IOException;

    LiveListAccountResponse listAccount(LiveListAccountRequest liveListAccountRequest) throws IOException;
}
