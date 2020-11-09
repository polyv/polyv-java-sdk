package net.polyv.live.service.account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
import net.polyv.live.entity.account.LiveCreateCategoryRequest;
import net.polyv.live.entity.account.LiveCreateCategoryResponse;
import net.polyv.live.entity.account.LiveDeleteCategoryRequest;
import net.polyv.live.entity.account.LiveListAccountChannelBasicRequest;
import net.polyv.live.entity.account.LiveListAccountChannelBasicResponse;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.entity.account.LiveListCategoryRequest;
import net.polyv.live.entity.account.LiveListCategoryResponse;
import net.polyv.live.entity.account.LiveUpdateAccountSwitchRequest;
import net.polyv.live.entity.account.LiveUpdateCategoryRequest;

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
     * @throws NoSuchAlgorithmException 异常
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
    Boolean updateAccountSwitch(LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置账号单点登录的token
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zhsz/set-user-login-token/
     * @param liveCreateAccountTokenRequest 设置账号单点登录的token请求实体
     * @return 设置账号单点登录的token返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean createAccountToken(LiveCreateAccountTokenRequest liveCreateAccountTokenRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置直播状态回调通知url
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/set-stream-callback/
     * @param liveAccountStreamCallbackRequest 设置直播状态回调通知url请求体
     * @return 设置直播状态回调通知url返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateStreamCallbackUrl(LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置转存成功回调通知url
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/set-playback-callback/
     * @param liveAccountPlaybackCallbackRequest 设置转存成功回调通知url请求实体
     * @return 设置转存成功回调通知url返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updatePlaybackCallbackUrl(LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置录制回调通知url
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/set-record-callback/
     * @param liveAccountRecordCallbackRequest 设置录制回调通知url请求实体
     * @return 设置录制回调通知url返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateRecordCallbackUrl(LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询功能开关状态接口
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/switch-get/
     * @param liveAccountSwitchRequest 查询功能开关状态接口请求实体
     * @return 查询功能开关状态接口返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveAccountSwitchResponse accountSwitch(LiveAccountSwitchRequest liveAccountSwitchRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询账号下所有频道缩略信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/get-simple-channel-list/
     * @param liveListAccountChannelBasicRequest 查询账号下所有频道缩略信息请求实体
     * @return 查询账号下所有频道缩略信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListAccountChannelBasicResponse listChannelBasic(
            LiveListAccountChannelBasicRequest liveListAccountChannelBasicRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询账户分钟数
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zhsz/get-user-durations/
     * @param liveAccountUserDurationsRequest 查询账户分钟数请求实体
     * @return 查询账户分钟数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveAccountUserDurationsResponse userDurations(LiveAccountUserDurationsRequest liveAccountUserDurationsRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 创建账号下直播分类
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zhsz/create-category/
     * @param liveCreateCategoryRequest 创建账号下直播分类请求实体
     * @return 创建账号下直播分类返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveCreateCategoryResponse createCategory(LiveCreateCategoryRequest liveCreateCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询账号下直播分类
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/get-category-list/
     * @param liveCategoryRequest 查询账号下直播分类请求实体
     * @return 查询账号下直播分类返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListCategoryResponse listCategory(LiveListCategoryRequest liveCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改直播频道分类名称
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zhsz/update-category-name/
     * @param liveUpdateCategoryRequest 修改直播频道分类名称请求实体
     * @return 修改直播频道分类名称返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateCategory(LiveUpdateCategoryRequest liveUpdateCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除直播频道分类
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zhsz/delete-category/
     * @param liveDeleteCategoryRequest 删除直播频道分类请求实体
     * @return 删除直播频道分类返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteCategory(LiveDeleteCategoryRequest liveDeleteCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
