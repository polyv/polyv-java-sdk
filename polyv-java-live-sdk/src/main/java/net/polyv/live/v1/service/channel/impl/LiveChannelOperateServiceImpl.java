package net.polyv.live.v1.service.channel.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.util.StringUtils;
import net.polyv.live.v1.config.LiveGlobalConfig;
import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.channel.operate.LiveChannelAdvertListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelAdvertListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelAuthTokenRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelAuthTokenResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelCallbackSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelCallbackSettingResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelCaptureRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelDetailRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInitRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInitResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelPasswordSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelTransmitListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelTransmitListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelPPTRecordRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelTokenRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateDiskVideosStreamRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelResponse;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelTokenRequest;
import net.polyv.live.v1.entity.channel.operate.LiveDeleteChannelListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveDeleteChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveDeleteDiskVideosStreamRequest;
import net.polyv.live.v1.entity.channel.operate.LiveDeleteSonChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveListChannelPPTRecordRequest;
import net.polyv.live.v1.entity.channel.operate.LiveListChannelPPTRecordResponse;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveUpdateChannelCallbackSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveUpdateChannelMaxViewerRequest;
import net.polyv.live.v1.entity.channel.operate.LiveUpdateChannelStreamRequest;
import net.polyv.live.v1.entity.channel.operate.LiveUpdateSonChannelInfoRequest;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.channel.ILiveChannelOperateService;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 直播频道操作实现类
 * @author: thomas
 **/
@Slf4j
public class LiveChannelOperateServiceImpl extends LiveBaseService implements ILiveChannelOperateService {
    
    /**
     * 创建直播频道
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/create-channel/
     * @param liveChannelRequest 直播频道请求实体
     * @return 频道数据
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.CHANNEL_CREATE_URL;
        LiveChannelResponse liveChannelResponse = this.postFormBodyReturnOne(url, liveChannelRequest, LiveChannelResponse.class);
        return liveChannelResponse;
    }
    
    /**
     * 创建并初始化频道
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/basic-create/
     * @param liveChannelInitRequest 创建并初始化频道请求体
     * @return 创建并初始化频道返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelInitResponse createChannelInit(LiveChannelInitRequest liveChannelInitRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_BASIC_CREATE_URL;
        if (liveChannelInitRequest != null && liveChannelInitRequest.getBasicSetting() != null &&
                StringUtils.isBlank(liveChannelInitRequest.getBasicSetting().getOnlyOneLiveEnabled())) {
            liveChannelInitRequest.getBasicSetting().setOnlyOneLiveEnabled("N");
        }
        LiveChannelInitResponse liveChannelInitResponse = this.postJsonBodyReturnOne(url, liveChannelInitRequest,
                LiveChannelInitResponse.class);
        return liveChannelInitResponse;
    }
    
    /**
     * 批量创建频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/batch-create-channels/
     * @param liveCreateChannelListRequest 批量创建频道请求体
     * @return 批量创建频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCreateChannelListResponse createChannelList(LiveCreateChannelListRequest liveCreateChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_CREATE_URL;
        LiveCreateChannelListResponse liveCreateChannelListResponse = this.postJsonBodyReturnOne(url,
                liveCreateChannelListRequest, LiveCreateChannelListResponse.class);
        return liveCreateChannelListResponse;
    }
    
    /**
     * 修改频道的相关设置
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/update-channel-detail-setting/
     * @param liveChannelSettingRequest 修改频道的相关设置请求体
     * @return 修改频道的相关设置返回体
     * @throws IOException 异常
     */
   
    @Override
    public Boolean updateChannelSetting(LiveChannelSettingRequest liveChannelSettingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_BASIC_UPDATE_URL;
        Map<String, String> signMap = LiveSignUtil.getSignMap(liveChannelSettingRequest);
        signMap.put("channelId", liveChannelSettingRequest.getChannelId() + "");
        String liveChannelSettingResponse = this.postJsonBodyReturnOne(url, signMap, liveChannelSettingRequest, String.class);
        return "success".equals(liveChannelSettingResponse);
    }
    
    /**
     * 设置频道详情
     * 注意：设置前，请确认您的套餐是否包含对应场景
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/detail-update/
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return 频道详情
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest)
            throws IOException, NoSuchAlgorithmException {
        //此处password字段与channelPasswd都表示频道密码，先做兼容
        if ("channelPasswd".equals(liveChannelDetailRequest.getField())) {
            liveChannelDetailRequest.setField("password");
        }
        String url = LiveURL.CHANNEL_DETAIL_SET_URL;
        String liveChannelDetailResponse = this.postFormBodyReturnOne(url, liveChannelDetailRequest, String.class);
        return "true".equals(liveChannelDetailResponse);
    }
    
    /**
     * 查询课件重制任务列表
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/pptrecord-list/
     * @param liveListChannelPPTRecordRequest 查询课件重制任务列表请求体
     * @return 课件重制任务列表返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelPPTRecordResponse listPPTRecord(
            LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_PPTRECORD_URL;
        return this.getReturnOne(url, liveListChannelPPTRecordRequest, LiveListChannelPPTRecordResponse.class);
    }
    
    /**
     * 设置频道密码
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/updatepasswd/
     * @param liveChannelPasswordSettingRequest 设置频道密码请求体
     * @return 设置频道密码返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelPassword(LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelPasswordSettingRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PWD_SET_URL, liveChannelPasswordSettingRequest.getUserId());
        String updateChannelPasswordResponse = this.postFormBodyReturnOne(url, liveChannelPasswordSettingRequest, String.class);
        return "true".equals(updateChannelPasswordResponse);
    }
    
    /**
     * 删除直播频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/deletechannel/
     * @param liveDeleteChannelRequest 删除直播频道请求体
     * @return 删除直播频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteChannel(LiveDeleteChannelRequest liveDeleteChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        liveDeleteChannelRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_DELETE_URL, liveDeleteChannelRequest.getChannelId());
        String liveDeleteChannelResponse = this.postFormBodyReturnOne(url, liveDeleteChannelRequest, String.class);
        return "true".equals(liveDeleteChannelResponse);
    }
    
    /**
     * 批量删除频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/batch-delete-channels/
     * @param liveDeleteChannelListRequest 批量删除频道请求体
     * @return 批量删除频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteChannelList(LiveDeleteChannelListRequest liveDeleteChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_DELETE_URL;
        String liveDeleteChannelListResponse = this.postJsonBodyReturnOne(url, liveDeleteChannelListRequest, String.class);
        return "true".equals(liveDeleteChannelListResponse);
    }
    
    /**
     * 设置频道单点登陆token
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/set-token-1/
     * @param liveCreateChannelTokenRequest 设置频道单点登陆token请求体
     * @return 设置频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean createChannelToken(LiveCreateChannelTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_TOKEN_CREATE_URL, liveCreateChannelTokenRequest.getChannelId());
        String liveCreateChannelTokenResponse = this.postFormBodyReturnOne(url, liveCreateChannelTokenRequest, String.class);
        String success = "success";
        return success.equals(liveCreateChannelTokenResponse);
    }
    
    /**
     * 查询频道信息
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/getchannelid-2/
     * @param liveChannelInfoRequest 查询频道信息请求体
     * @return 查询频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelInfoResponse getChannelInfo(LiveChannelInfoRequest liveChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_GET_URL, liveChannelInfoRequest.getChannelId());
        LiveChannelInfoResponse liveChannelInfoResponse = this.getReturnOne(url, liveChannelInfoRequest,
                LiveChannelInfoResponse.class);
        return liveChannelInfoResponse;
    }
    
    /**
     * 查询频道基本信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/get-detail-setting/
     * @param liveChannelBasicInfoRequest 查询频道基本信息请求体
     * @return 查询频道基本信息返回提
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelBasicInfoResponse getChannelBasicInfo(LiveChannelBasicInfoRequest liveChannelBasicInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_BASIC_INFO_URL;
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = this.postFormBodyReturnOne(url, liveChannelBasicInfoRequest,
                LiveChannelBasicInfoResponse.class);
        if(liveChannelBasicInfoResponse.getStartTime() != null && liveChannelBasicInfoResponse.getStartTime().getTime() == 0){
            liveChannelBasicInfoResponse.setStartTime(null);
        }
        return liveChannelBasicInfoResponse;
    }
    
    /**
     * 查询授权和连麦的token
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/get-chat-token/
     * @param liveCreateChannelTokenRequest 查询授权和连麦的token请求体
     * @return 查询授权和连麦的token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelAuthTokenResponse getChannelAuthToken(LiveChannelAuthTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_AUTH_TOKEN_URL;
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse = this.postFormBodyReturnOne(url, liveCreateChannelTokenRequest,
                LiveChannelAuthTokenResponse.class);
        return liveChannelAuthTokenResponse;
    }
    
    /**
     * 创建子频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/add-account/
     * @param liveCreateSonChannelRequest 创建子频道请求体
     * @return 创建子频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCreateSonChannelResponse createSonChannel(LiveCreateSonChannelRequest liveCreateSonChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_CREATE_URL, liveCreateSonChannelRequest.getChannelId());
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = this.postFormBodyReturnOne(url, liveCreateSonChannelRequest,
                LiveCreateSonChannelResponse.class);
        return liveCreateSonChannelResponse;
    }
    
    /**
     * 设置子频道信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/update-account/
     * @param liveUpdateSonChannelInfoRequest 设置子频道信息请求体
     * @return 设置子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateSonChannelInfo(LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_INFO_UPDATE_URL,
                liveUpdateSonChannelInfoRequest.getChannelId());
        String updateSonChannelInfoResponse = this.postFormBodyReturnOne(url, liveUpdateSonChannelInfoRequest, String.class);
        return "success".equals(updateSonChannelInfoResponse);
    }
    
    /**
     * 设置子频道单点登陆token
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/set-account-token/
     * @param liveCreateSonChannelTokenRequest 设置子频道单点登陆token请求体
     * @return 设置子频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean createSonChannelToken(LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_TOKEN_CREATE_URL,
                liveCreateSonChannelTokenRequest.getAccount());
        String liveCreateSonChannelTokenResponse = this.postFormBodyReturnOne(url, liveCreateSonChannelTokenRequest, String.class);
        return "success".equals(liveCreateSonChannelTokenResponse);
    }
    
    /**
     * 查询子频道信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-account/
     * @param liveSonChannelInfoRequest 查询子频道信息请求体
     * @return 查询子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveSonChannelInfoResponse getSonChannelInfo(LiveSonChannelInfoRequest liveSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_INFO_GET_URL, liveSonChannelInfoRequest.getChannelId());
        LiveSonChannelInfoResponse liveSonChannelInfoResponse = this.getReturnOne(url, liveSonChannelInfoRequest,
                LiveSonChannelInfoResponse.class);
        return liveSonChannelInfoResponse;
    }
    
    /**
     * 查询频道号下所有子频道信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-accounts/
     * @param liveSonChannelInfoListRequest 查询频道号下所有子频道信息请求体
     * @return 查询频道号下所有子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveSonChannelInfoListResponse getSonChannelInfoList(
            LiveSonChannelInfoListRequest liveSonChannelInfoListRequest) throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_ACCOUNTS_GET_URL, liveSonChannelInfoListRequest.getChannelId());
        LiveSonChannelInfoResponse[] liveSonChannelInfoResponses = this.getReturnOne(url, liveSonChannelInfoListRequest,
                LiveSonChannelInfoResponse[].class);
        if (liveSonChannelInfoResponses == null) {
            liveSonChannelInfoResponses = new LiveSonChannelInfoResponse[]{};
        }
        LiveSonChannelInfoListResponse liveSonChannelInfoListResponse = new LiveSonChannelInfoListResponse();
        liveSonChannelInfoListResponse.setSonChannelInfos(Arrays.asList(liveSonChannelInfoResponses));
        return liveSonChannelInfoListResponse;
    }
    
    /**
     * 删除子频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/delete-account/
     * @param liveDeleteSonChannelRequest 删除子频道请求体
     * @return 删除子频道返回体 true为删除成功，空字符串为删除失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteSonChannel(LiveDeleteSonChannelRequest liveDeleteSonChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_DELETE_URL, liveDeleteSonChannelRequest.getChannelId());
        String liveDeleteSonChannelResponse = this.postFormBodyReturnOne(url, liveDeleteSonChannelRequest, String.class);
        return "true".equals(liveDeleteSonChannelResponse);
    }
    
    /**
     * 创建重制课件任务
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/add-record-task/
     * @param liveCreateChannelPPTRecordRequest 创建重制课件任务请求实体
     * @return 创建重制课件任务返回实体，成功返回""
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean createChannelPPTRecordTask(LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_PPTRECORD_CREATE__URL;
        String liveCreateChannelPPTRecordResponse = this.postFormBodyReturnOne(url, liveCreateChannelPPTRecordRequest, String.class);
        return "success".equals(liveCreateChannelPPTRecordResponse);
    }
    
    /**
     * 查询频道回调设置接口
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/get-callback-setting/
     * @param liveChannelCallbackSettingRequest 查询频道回调设置接口请求实体
     * @return 查询频道回调设置接口返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelCallbackSettingResponse getChannelCallbackSetting(
            LiveChannelCallbackSettingRequest liveChannelCallbackSettingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.GET_CHANNEL_CALLBACK_SETTING_URL;
        return this.getReturnOne(url, liveChannelCallbackSettingRequest, LiveChannelCallbackSettingResponse.class);
    }
    
    /**
     * 设置频道回调设置
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/update-setting/
     * @param liveUpdateChannelCallbackSettingRequest 设置频道回调设置请求实体
     * @return 设置频道回调设置返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelCallbackSetting(
            LiveUpdateChannelCallbackSettingRequest liveUpdateChannelCallbackSettingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_CHANNEL_CALLBACK_SETTING_URL;
        String liveUpdateChannelCallbackSettingResponse = this.postFormBodyReturnOne(url, liveUpdateChannelCallbackSettingRequest,
                String.class);
        return "success".equals(liveUpdateChannelCallbackSettingResponse);
    }
    
    /**
     * 批量创建子频道
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/batch-create/
     * @param liveCreateSonChannelListRequest 批量创建子频道请求实体
     * @return 批量创建子频道返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCreateSonChannelListResponse createSonChannelList(
            LiveCreateSonChannelListRequest liveCreateSonChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CREATE_SON_CHANNEL_LIST_URL;
        Map<String, String> map = LiveSignUtil.getSignMap(liveCreateSonChannelListRequest);
        map.put("channelId", liveCreateSonChannelListRequest.getChannelId());
        List<LiveCreateSonChannelListResponse.SonChannelInfo> sonChannelInfos = this.postJsonBodyReturnList(url, map,
                liveCreateSonChannelListRequest, JSON.toJSONString(liveCreateSonChannelListRequest.getSonChannels()),
                LiveCreateSonChannelListResponse.SonChannelInfo.class);
        LiveCreateSonChannelListResponse liveCreateSonChannelListResponse = new LiveCreateSonChannelListResponse();
        liveCreateSonChannelListResponse.setSonChannelInfos(sonChannelInfos);
        return liveCreateSonChannelListResponse;
    }
    
    /**
     * 获取账号或频道转播列表信息
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/get-transmit-associations/
     * @param liveChannelTransmitListRequest 获取账号或频道转播列表信息请求实体
     * @return 获取账号或频道转播列表信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelTransmitListResponse getChannelTransmitList(
            LiveChannelTransmitListRequest liveChannelTransmitListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_TRANSMIT_LIST_URL;
        List<LiveChannelTransmitListResponse.ChannelTransmit> channelTransmits = this.getReturnList(url,
                liveChannelTransmitListRequest, LiveChannelTransmitListResponse.ChannelTransmit.class);
        LiveChannelTransmitListResponse liveChannelTransmitListResponse = new LiveChannelTransmitListResponse();
        liveChannelTransmitListResponse.setChannelTransmits(channelTransmits);
        return liveChannelTransmitListResponse;
    }
    
    /**
     * 设置频道最大在线人数
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/setmaxviewerv2/
     * @param liveUpdateChannelMaxViewerRequest 设置频道最大在线人数请求实体
     * @return 设置频道最大在线人数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelMaxViewer(LiveUpdateChannelMaxViewerRequest liveUpdateChannelMaxViewerRequest)
            throws IOException, NoSuchAlgorithmException {
        liveUpdateChannelMaxViewerRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.UPDATE_CHANNEL_MAX_VIEWER_URL,
                liveUpdateChannelMaxViewerRequest.getChannelId());
        String liveUpdateChannelMaxViewerResponse = this.postFormBodyReturnOne(url, liveUpdateChannelMaxViewerRequest, String.class);
        return "success".equals(liveUpdateChannelMaxViewerResponse);
    }
    
    /**
     * 查询频道广告列表
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/channel-advert-list/
     * @param liveChannelAdvertListRequest 查询频道广告列表请求实体
     * @return 查询频道广告列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelAdvertListResponse getChannelAdvertList(LiveChannelAdvertListRequest liveChannelAdvertListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_ADVERT_LIST_GET_URL;
        List<LiveChannelAdvertListResponse.ChannelAdvert> channelAdverts = this.getReturnList(url,
                liveChannelAdvertListRequest, LiveChannelAdvertListResponse.ChannelAdvert.class);
        LiveChannelAdvertListResponse liveChannelAdvertListResponse = new LiveChannelAdvertListResponse();
        liveChannelAdvertListResponse.setChannelAdverts(channelAdverts);
        return liveChannelAdvertListResponse;
    }
    
    /**
     * 查询频道直播截图
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-capture-image/
     * @param liveChannelCaptureRequest 查询频道直播截图请求实体
     * @return 查询频道直播截图返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String getChannelCapture(LiveChannelCaptureRequest liveChannelCaptureRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_CAPTURE_URL, liveChannelCaptureRequest.getChannelId());
        return this.postFormBodyReturnOne(url, liveChannelCaptureRequest, String.class);
    }
    
    /**
     * 修改直播推流方式
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/update-stream-type/
     * @param liveUpdateChannelStreamRequest 修改直播推流方式请求实体
     * @return 修改直播推流方式返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelStream(LiveUpdateChannelStreamRequest liveUpdateChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_CHANNEL_STREAM_URL;
        String liveUpdateChannelStreamResponse = this.postFormBodyReturnOne(url, liveUpdateChannelStreamRequest, String.class);
        return "success".equals(liveUpdateChannelStreamResponse);
    }
    
    /**
     * 设置硬盘推流直播
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/add-disk-videos/
     * @param liveCreateDiskVideosStreamRequest 设置硬盘推流直播请求实体
     * @return 设置硬盘推流直播返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean createDiskVideosStream(LiveCreateDiskVideosStreamRequest liveCreateDiskVideosStreamRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_DISK_VIDEOS_STREAM_URL;
        String liveCreateDiskVideosStreamResponse = this.postFormBodyReturnOne(url, liveCreateDiskVideosStreamRequest, String.class);
        return "success".equals(liveCreateDiskVideosStreamResponse);
    }
    
    /**
     * 删除硬盘推流的视频
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/delete-disk-videos/
     * @param liveDeleteDiskVideosStreamRequest 删除硬盘推流的视频请求实体
     * @return 删除硬盘推流的视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteDiskVideosStream(LiveDeleteDiskVideosStreamRequest liveDeleteDiskVideosStreamRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.DELETE_DISK_VIDEOS_STREAM_URL;
        String liveDeleteDiskVideosStreamResponse = this.postFormBodyReturnOne(url, liveDeleteDiskVideosStreamRequest, String.class);
        return "success".equals(liveDeleteDiskVideosStreamResponse);
    }
    
}
