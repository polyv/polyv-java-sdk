package net.polyv.live.service.channel.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.security.NoSuchAlgorithmException;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.channel.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.LiveChannelInitRequest;
import net.polyv.live.entity.channel.LiveChannelInitResponse;
import net.polyv.live.entity.channel.LiveChannelPasswordSettingRequest;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.entity.channel.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.LiveDeleteChannelListRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordResponse;
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
    
    
}
