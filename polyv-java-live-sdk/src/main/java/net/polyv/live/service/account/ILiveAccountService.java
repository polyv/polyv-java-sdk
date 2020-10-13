package net.polyv.live.service.account;

import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 *  直播账号级管理
 * @author: sadboy
 **/
public interface ILiveAccountService {

    LiveListAccountDetailResponse listAccountDetail(LiveListAccountDetailRequest liveListAccountDetailRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询账号下的频道列表(返回频道号列表)
     * @param liveListAccountRequest 查询账号下的频道列表 请求体
     * @return 查询账号下的频道列表返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListAccountResponse listAccount(LiveListAccountRequest liveListAccountRequest)
            throws IOException, NoSuchAlgorithmException;
}
