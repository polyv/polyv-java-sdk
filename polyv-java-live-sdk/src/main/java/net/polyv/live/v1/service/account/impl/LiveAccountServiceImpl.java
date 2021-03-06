package net.polyv.live.v1.service.account.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.polyv.live.v1.config.LiveGlobalConfig;
import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.account.LiveAccountInfoRequest;
import net.polyv.live.v1.entity.account.LiveAccountInfoResponse;
import net.polyv.live.v1.entity.account.LiveAccountMicDurationRequest;
import net.polyv.live.v1.entity.account.LiveAccountMicDurationResponse;
import net.polyv.live.v1.entity.account.LiveAccountPlaybackCallbackRequest;
import net.polyv.live.v1.entity.account.LiveAccountRecordCallbackRequest;
import net.polyv.live.v1.entity.account.LiveAccountStreamCallbackRequest;
import net.polyv.live.v1.entity.account.LiveAccountSwitchRequest;
import net.polyv.live.v1.entity.account.LiveAccountSwitchResponse;
import net.polyv.live.v1.entity.account.LiveAccountUserDurationsRequest;
import net.polyv.live.v1.entity.account.LiveAccountUserDurationsResponse;
import net.polyv.live.v1.entity.account.LiveChannelIncomeDetailRequest;
import net.polyv.live.v1.entity.account.LiveChannelIncomeDetailResponse;
import net.polyv.live.v1.entity.account.LiveCreateAccountTokenRequest;
import net.polyv.live.v1.entity.account.LiveCreateCategoryRequest;
import net.polyv.live.v1.entity.account.LiveCreateCategoryResponse;
import net.polyv.live.v1.entity.account.LiveDeleteCategoryRequest;
import net.polyv.live.v1.entity.account.LiveListAccountChannelBasicRequest;
import net.polyv.live.v1.entity.account.LiveListAccountChannelBasicResponse;
import net.polyv.live.v1.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.v1.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.v1.entity.account.LiveListAccountRequest;
import net.polyv.live.v1.entity.account.LiveListAccountResponse;
import net.polyv.live.v1.entity.account.LiveListCategoryRequest;
import net.polyv.live.v1.entity.account.LiveListCategoryResponse;
import net.polyv.live.v1.entity.account.LiveUpdateAccountSwitchRequest;
import net.polyv.live.v1.entity.account.LiveUpdateCategoryRequest;
import net.polyv.live.v1.entity.account.LiveUpdateCategorySortRequest;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.account.ILiveAccountService;

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
        LiveListAccountDetailResponse liveListAccountDetailResponse = this.postFormBodyReturnOne(url, liveListAccountDetailRequest,
                LiveListAccountDetailResponse.class);
        for(LiveListAccountDetailResponse.LiveChannelDetail liveChannelDetail:liveListAccountDetailResponse.getContents()){
            Date startTime = liveChannelDetail.getStartTime();
            if(startTime != null && startTime.getTime() == 0){
                liveChannelDetail.setStartTime(null);
            }
        }
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
        String url = LiveURL.ACCOUNT_LIST_CHANNEL_URL;
        return this.getReturnOne(url, liveListAccountRequest, LiveListAccountResponse.class);
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
    public LiveAccountMicDurationResponse getMicDuration(LiveAccountMicDurationRequest liveAccountMicDurationRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ACCOUNT_MIC_DURATION_URL;
        return this.getReturnOne(url, liveAccountMicDurationRequest, LiveAccountMicDurationResponse.class);
        
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
    public Boolean updateAccountSwitch(LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ACCOUNT_SWITCH_UPDATE_URL;
        String liveUpdateAccountSwitchResponse = this.postFormBodyReturnOne(url, liveUpdateAccountSwitchRequest, String.class);
        return "true".equals(liveUpdateAccountSwitchResponse);
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
    public Boolean createAccountToken(LiveCreateAccountTokenRequest liveCreateAccountTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ACCOUNT_TOKEN_CREATE_URL;
        String liveCreateAccountTokenResponse = this.postFormBodyReturnOne(url, liveCreateAccountTokenRequest, String.class);
        return "success".equals(liveCreateAccountTokenResponse);
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
    public Boolean updateStreamCallbackUrl(LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest)
            throws IOException, NoSuchAlgorithmException {
        liveAccountStreamCallbackRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.ACCOUNT_STREAM_CALLBACK_URL,
                liveAccountStreamCallbackRequest.getUserId());
        String liveAccountStreamCallbackResponse = this.postFormBodyReturnOne(url, liveAccountStreamCallbackRequest, String.class);
        return "success".equals(liveAccountStreamCallbackResponse);
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
    public Boolean updatePlaybackCallbackUrl(LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest)
            throws IOException, NoSuchAlgorithmException {
        liveAccountPlaybackCallbackRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.ACCOUNT_PLAYBACK_CALLBACK_URL,
                liveAccountPlaybackCallbackRequest.getUserId());
        String liveAccountPlaybackCallbackResponse = this.postFormBodyReturnOne(url, liveAccountPlaybackCallbackRequest,
                String.class);
        return "success".equals(liveAccountPlaybackCallbackResponse);
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
    public Boolean updateRecordCallbackUrl(LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest)
            throws IOException, NoSuchAlgorithmException {
        liveAccountRecordCallbackRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.ACCOUNT_RECORD_CALLBACK_URL,
                liveAccountRecordCallbackRequest.getUserId());
        String liveAccountRecordCallbackResponse = this.postFormBodyReturnOne(url, liveAccountRecordCallbackRequest, String.class);
        return "success".equals(liveAccountRecordCallbackResponse);
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
    public LiveAccountSwitchResponse getAccountSwitch(LiveAccountSwitchRequest liveAccountSwitchRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ACCOUNT_SWITCH_URL;
        LiveAccountSwitchResponse.ChannelSwitch[] channelSwitches = this.getReturnOne(url, liveAccountSwitchRequest,
                LiveAccountSwitchResponse.ChannelSwitch[].class);
        channelSwitches = channelSwitches == null ? new LiveAccountSwitchResponse.ChannelSwitch[]{} : channelSwitches;
        return new LiveAccountSwitchResponse().setChannelSwitches(Arrays.asList(channelSwitches));
        
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
        return this.getReturnOne(url, liveListAccountChannelBasicRequest, LiveListAccountChannelBasicResponse.class);
        
    }
    
    /**
     * 查询账户分钟数
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zhsz/get-user-durations/
     * @return 查询账户分钟数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveAccountUserDurationsResponse getUserDurations(
            LiveAccountUserDurationsRequest liveAccountUserDurationsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.USER_DURATION_GET_URL;
        return this.postFormBodyReturnOne(url, liveAccountUserDurationsRequest, LiveAccountUserDurationsResponse.class);
        
    }
    
    /**
     * 创建账号下直播分类
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zhsz/create-category/
     * @param liveCreateCategoryRequest 创建账号下直播分类请求实体
     * @return 创建账号下直播分类返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCreateCategoryResponse createCategory(LiveCreateCategoryRequest liveCreateCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CREATE_CHANNEL_CATEGORY_URL;
        return this.postFormBodyReturnOne(url, liveCreateCategoryRequest, LiveCreateCategoryResponse.class);
    }
    
    /**
     * 查询账号下直播分类
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/get-category-list/
     * @param liveCategoryRequest 查询账号下直播分类请求实体
     * @return 查询账号下直播分类返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListCategoryResponse listCategory(LiveListCategoryRequest liveCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.LIST_CHANNEL_CATEGORY_URL;
        List<LiveListCategoryResponse.LiveCategory> liveCategories = this.postFormBodyReturnList(url, liveCategoryRequest,
                LiveListCategoryResponse.LiveCategory.class);
        LiveListCategoryResponse liveListCategoryResponse = new LiveListCategoryResponse();
        liveListCategoryResponse.setLiveCategories(liveCategories);
        return liveListCategoryResponse;
    }
    
    /**
     * 修改直播频道分类名称
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zhsz/update-category-name/
     * @param liveUpdateCategoryRequest 修改直播频道分类名称请求实体
     * @return 修改直播频道分类名称返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateCategory(LiveUpdateCategoryRequest liveUpdateCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_CHANNEL_CATEGORY_URL;
        String liveUpdateCategoryResponse = this.postFormBodyReturnOne(url, liveUpdateCategoryRequest, String.class);
        return "success".equals(liveUpdateCategoryResponse);
    }
    
    /**
     * 删除直播频道分类
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zhsz/delete-category/
     * @param liveDeleteCategoryRequest 删除直播频道分类请求实体
     * @return 删除直播频道分类返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteCategory(LiveDeleteCategoryRequest liveDeleteCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.DELETE_CHANNEL_CATEGORY_URL;
        String liveDeleteCategoryResponse = this.postFormBodyReturnOne(url,liveDeleteCategoryRequest,String.class);
        return "success".equals(liveDeleteCategoryResponse);
    }
    
    /**
     * 修改直播频道分类顺序
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zhsz/update-category-rank/
     * @param liveUpdateCategorySortRequest 修改直播频道分类顺序请求实体
     * @return 修改直播频道分类顺序返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateCategorySort(LiveUpdateCategorySortRequest liveUpdateCategorySortRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_CHANNEL_CATEGORY_SORT_URL;
        String liveUpdateCategorySortResponse = this.postFormBodyReturnOne(url, liveUpdateCategorySortRequest, String.class);
        return "success".equals(liveUpdateCategorySortResponse);
    }
    
    /**
     * 获取直播用户账号信息接口
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zhsz/get-user-info/
     * @param liveAccountInfoRequest 获取直播用户账号信息接口请求实体
     * @return 获取直播用户账号信息接口返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveAccountInfoResponse getAccountInfo(LiveAccountInfoRequest liveAccountInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.GET_ACCOUNT_INFO_URL;
        return this.getReturnOne(url, liveAccountInfoRequest, LiveAccountInfoResponse.class);
    }
    
    /**
     * 查询账号下所有/某个频道号收入详情
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zhsz/get-income-detail/
     * @param liveChannelIncomeDetailRequest 查询账号下所有/某个频道号收入详情请求实体
     * @return 查询账号下所有/某个频道号收入详情返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelIncomeDetailResponse getChannelIncomeDetail(
            LiveChannelIncomeDetailRequest liveChannelIncomeDetailRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelIncomeDetailRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.GET_CHANNEL_INCOME_DETAIL_URL,liveChannelIncomeDetailRequest.getUserId());
        return this.postFormBodyReturnOne(url,liveChannelIncomeDetailRequest,LiveChannelIncomeDetailResponse.class);
    }
    
}
