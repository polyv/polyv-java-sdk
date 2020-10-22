package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.web.info.LiveChannelSplashRequest;
import net.polyv.live.entity.web.info.LiveChannelSplashResponse;
import net.polyv.live.entity.web.info.LiveUpdateChannelNameRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelPublisherRequest;
import net.polyv.live.entity.web.setting.LiveChannelGlobalSwitchRequest;

/**
 * @author: sadboy
 **/
public interface ILiveWebInfoService {
    
    /**
     * 设置频道名称
     * API地址：https://dev.polyv.net/2016/liveproduct/l-api/szgkygg/ymxxsz/updatechannelname/
     * @param liveUpdateChannelNameRequest 设置频道名称请求实体
     * @return 设置频道名称返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelName(LiveUpdateChannelNameRequest liveUpdateChannelNameRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置主持人姓名
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/setpublisher/
     * @param liveUpdateChannelPublisherRequest 设置主持人姓名请求实体
     * @return 设置主持人姓名返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelPublisher(LiveUpdateChannelPublisherRequest liveUpdateChannelPublisherRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询直播引导图开关状态及URL
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/getsplash/
     * @param liveChannelSplashRequest 查询直播引导图开关状态及URL请求实体
     * @return 查询直播引导图开关状态及URL返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelSplashResponse channelSplash(LiveChannelSplashRequest liveChannelSplashRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
