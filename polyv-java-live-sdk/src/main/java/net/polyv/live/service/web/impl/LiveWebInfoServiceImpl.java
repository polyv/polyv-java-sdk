package net.polyv.live.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.web.info.LiveChannelSplashRequest;
import net.polyv.live.entity.web.info.LiveChannelSplashResponse;
import net.polyv.live.entity.web.info.LiveUpdateChannelNameRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelPublisherRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.web.ILiveWebInfoService;

/**
 * @author: sadboy
 **/
public class LiveWebInfoServiceImpl extends LiveBaseService implements ILiveWebInfoService {
    
    /**
     * 设置频道名称
     * API地址：https://dev.polyv.net/2016/liveproduct/l-api/szgkygg/ymxxsz/updatechannelname/
     * @param liveUpdateChannelNameRequest 设置频道名称请求实体
     * @return 设置频道名称返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateChannelName(LiveUpdateChannelNameRequest liveUpdateChannelNameRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_NAME_SET_URL, liveUpdateChannelNameRequest.getChannelId());
        String liveUpdateChannelNameResponse = this.basePost(url, liveUpdateChannelNameRequest, String.class);
        return liveUpdateChannelNameResponse;
    }
    
    /**
     * 设置主持人姓名
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/setpublisher/
     * @param liveUpdateChannelPublisherRequest 设置主持人姓名请求实体
     * @return 设置主持人姓名返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateChannelPublisher(LiveUpdateChannelPublisherRequest liveUpdateChannelPublisherRequest)
            throws IOException, NoSuchAlgorithmException {
        liveUpdateChannelPublisherRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PUBLISHER_SET_URL,
                liveUpdateChannelPublisherRequest.getUserId());
        String liveUpdateChannelPublisherResponse = this.basePost(url, liveUpdateChannelPublisherRequest, String.class);
        return liveUpdateChannelPublisherResponse;
    }
    
    /**
     * 查询直播引导图开关状态及URL
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/getsplash/
     * @param liveChannelSplashRequest 查询直播引导图开关状态及URL请求实体
     * @return 查询直播引导图开关状态及URL返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelSplashResponse channelSplash(LiveChannelSplashRequest liveChannelSplashRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_SPLASH_GET_URL, liveChannelSplashRequest.getChannelId());
        LiveChannelSplashResponse liveChannelSplashResponse = this.basePost(url, liveChannelSplashRequest,
                LiveChannelSplashResponse.class);
        return liveChannelSplashResponse;
    }
    
}
