package net.polyv.live.service.channel.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.security.NoSuchAlgorithmException;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.channel.LiveChannelAuthTokenRequest;
import net.polyv.live.entity.channel.LiveChannelAuthTokenResponse;
import net.polyv.live.entity.channel.LiveChannelBasicInfoRequest;
import net.polyv.live.entity.channel.LiveChannelBasicInfoResponse;
import net.polyv.live.entity.channel.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.LiveChannelInfoRequest;
import net.polyv.live.entity.channel.LiveChannelInfoResponse;
import net.polyv.live.entity.channel.LiveChannelInitRequest;
import net.polyv.live.entity.channel.LiveChannelInitResponse;
import net.polyv.live.entity.channel.LiveChannelPasswordSettingRequest;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.entity.channel.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.LiveCreateChannelTokenRequest;
import net.polyv.live.entity.channel.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.LiveCreateSonChannelTokenRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelListRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.channel.LiveSonChannelInfoRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoResponse;
import net.polyv.live.entity.channel.LiveUpdateSonChannelInfoRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.channel.ILiveChannelService;
import net.polyv.live.util.LiveSignUtil;
import net.polyv.live.util.MapUtil;

/**
 * 直播频道管理
 * @author: thomas
 **/
@Slf4j
public class LiveChannelServiceImpl extends LiveBaseService implements ILiveChannelService {
    /**
     * 直播频道创建
     * @param liveChannelRequest 直播频道请求实体
     * @return 频道数据
     * @throws IOException 客户端和服务器读写异常
     */
    @Override
    public LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.CHANNEL_CREATE_URL;
        LiveChannelResponse liveChannelResponse = this.basePost(url, liveChannelRequest, LiveChannelResponse.class);
        return liveChannelResponse;
    }
    
    /**
     * 创建并初始化频道
     * @param liveChannelInitRequest 请求体
     * @return 响应体
     * @throws IOException 异常
     */
    @Override
    public LiveChannelInitResponse createChannelInit(LiveChannelInitRequest liveChannelInitRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_BASIC_CREATE_URL;
        LiveChannelInitResponse liveChannelInitResponse = this.basePostJson(url, liveChannelInitRequest,
                LiveChannelInitResponse.class);
        return liveChannelInitResponse;
    }
    
    /**
     * 批量创建频道
     * @param liveCreateChannelListRequest 批量创建频道请求体
     * @return 批量创建频道返回体
     * @throws IOException 异常
     */
    @Override
    public LiveCreateChannelListResponse createChannelList(LiveCreateChannelListRequest liveCreateChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_CREATE_URL;
        LiveCreateChannelListResponse liveCreateChannelListResponse = this.basePostJson(url,
                liveCreateChannelListRequest, LiveCreateChannelListResponse.class);
        return liveCreateChannelListResponse;
    }
    
    /**
     * 修改频道的相关设置
     * @param liveChannelSettingRequest 修改频道的相关设置请求体
     * @return 修改频道的相关设置返回体
     * @throws IOException 异常
     */
    @SneakyThrows
    @Override
    public String updateChannelSetting(LiveChannelSettingRequest liveChannelSettingRequest) throws IOException {
        String url = LiveURL.CHANNEL_BASIC_UPDATE_URL;
        Map<String, String> signMap = MapUtil.getSignMap(liveChannelSettingRequest);
        signMap.put("channelId", liveChannelSettingRequest.getChannelId() + "");
        return this.basePostJson(url, signMap, liveChannelSettingRequest, String.class);
    }
    
    /**
     * 设置频道详情
     * 注意：设置前，请确认您的套餐是否包含对应场景
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return 频道详情
     * @throws IOException 异常
     */
    @Override
    public String updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest)
            throws IOException, NoSuchAlgorithmException {
        //此处password字段与channelPasswd都表示频道密码，先做兼容
        if ("channelPasswd".equals(liveChannelDetailRequest.getField())) {
            liveChannelDetailRequest.setField("password");
        }
        String url = LiveURL.CHANNEL_DETAIL_SET_URL;
        return this.basePost(url, liveChannelDetailRequest, String.class);
    }
    
    /**
     * 查询课件重制任务列表
     * @param liveListChannelPPTRecordRequest 查询课件重制任务列表请求体
     * @return 课件重制任务列表返回体
     * @throws IOException 异常
     */
    @Override
    public LiveListChannelPPTRecordResponse listPPTRecord(
            LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_PPTRECORD_URL;
        return this.baseGet(url, liveListChannelPPTRecordRequest, LiveListChannelPPTRecordResponse.class);
    }
    
    /**
     * 设置频道密码
     * @param liveChannelPasswordSettingRequest 设置频道密码请求体
     * @return 设置频道密码返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateChannelPassword(LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelPasswordSettingRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PWD_SET_URL, liveChannelPasswordSettingRequest.getUserId());
        return this.basePost(url, liveChannelPasswordSettingRequest, String.class);
    }
    
    /**
     * 删除直播频道
     * @param liveDeleteChannelRequest 删除直播频道请求体
     * @return 删除直播频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String deleteChannel(LiveDeleteChannelRequest liveDeleteChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        liveDeleteChannelRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_DELETE_URL, liveDeleteChannelRequest.getChannelId());
        return this.basePost(url, liveDeleteChannelRequest, String.class);
    }
    
    /**
     * 批量删除频道
     * @param liveDeleteChannelListRequest 批量删除频道请求体
     * @return 批量删除频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String deleteChannelList(LiveDeleteChannelListRequest liveDeleteChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_DELETE_URL;
        return this.basePostJson(url, liveDeleteChannelListRequest, String.class);
    }
    
    /**
     * 设置频道单点登陆token
     * @param liveCreateChannelTokenRequest 设置频道单点登陆token请求体
     * @return 设置频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String createChannelToken(LiveCreateChannelTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_TOKEN_CREATE_URL, liveCreateChannelTokenRequest.getChannelId());
        return this.basePost(url, liveCreateChannelTokenRequest, String.class);
    }
    
    /**
     * 查询频道信息
     * @param liveChannelInfoRequest 查询频道信息请求体
     * @return 查询频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelInfoResponse channelInfo(LiveChannelInfoRequest liveChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_GET_URL, liveChannelInfoRequest.getChannelId());
        LiveChannelInfoResponse liveChannelInfoResponse = this.baseGet(url, liveChannelInfoRequest,
                LiveChannelInfoResponse.class);
        return liveChannelInfoResponse;
    }
    
    /**
     * 查询频道基本信息
     * @param liveChannelBasicInfoRequest 查询频道基本信息请求体
     * @return 查询频道基本信息返回提
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelBasicInfoResponse channelBasicInfo(LiveChannelBasicInfoRequest liveChannelBasicInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_BASIC_INFO_URL;
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = this.basePost(url, liveChannelBasicInfoRequest,
                LiveChannelBasicInfoResponse.class);
        return liveChannelBasicInfoResponse;
    }
    
    /**
     * 查询授权和连麦的token
     * @param liveCreateChannelTokenRequest 查询授权和连麦的token请求体
     * @return 查询授权和连麦的token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelAuthTokenResponse channelAuthToken(LiveChannelAuthTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_AUTH_TOKEN_URL;
        liveCreateChannelTokenRequest.setUserId(LiveGlobalConfig.USER_ID);
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse = this.basePost(url, liveCreateChannelTokenRequest,
                LiveChannelAuthTokenResponse.class);
        return liveChannelAuthTokenResponse;
    }
    
    /**
     * 创建子频道
     * @param liveCreateSonChannelRequest 创建子频道请求体
     * @return 创建子频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCreateSonChannelResponse createSonChannel(LiveCreateSonChannelRequest liveCreateSonChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_CREATE_URL, liveCreateSonChannelRequest.getChannelId());
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = this.basePost(url, liveCreateSonChannelRequest,
                LiveCreateSonChannelResponse.class);
        return liveCreateSonChannelResponse;
    }
    
    /**
     * 设置子频道信息
     * @param liveUpdateSonChannelInfoRequest 设置子频道信息请求体
     * @return 设置子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateSonChannelInfo(LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_INFO_UPDATE_URL,
                liveUpdateSonChannelInfoRequest.getChannelId());
        return this.basePost(url, liveUpdateSonChannelInfoRequest, String.class);
    }
    
    /**
     * 设置子频道单点登陆token
     * @param liveCreateSonChannelTokenRequest 设置子频道单点登陆token请求体
     * @return 设置子频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String createSonChannelToken(LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_TOKEN_CREATE_URL,
                liveCreateSonChannelTokenRequest.getAccount());
        String liveCreateSonChannelTokenResponse = this.basePost(url, liveCreateSonChannelTokenRequest, String.class);
        return liveCreateSonChannelTokenResponse;
    }
    
    /**
     * 查询子频道信息
     * @param liveSonChannelInfoRequest 查询子频道信息请求体
     * @return 查询子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveSonChannelInfoResponse sonChannelInfo(LiveSonChannelInfoRequest liveSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_INFO_GET_URL, liveSonChannelInfoRequest.getChannelId());
        LiveSonChannelInfoResponse liveSonChannelInfoResponse = this.baseGet(url, liveSonChannelInfoRequest,
                LiveSonChannelInfoResponse.class);
        return liveSonChannelInfoResponse;
    }
    
}
