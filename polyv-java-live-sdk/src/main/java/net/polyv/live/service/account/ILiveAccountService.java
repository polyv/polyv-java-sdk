package net.polyv.live.service.account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.account.LiveAccountMicDurationRequest;
import net.polyv.live.entity.account.LiveAccountMicDurationResponse;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;

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
    
}
