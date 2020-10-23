package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.web.info.LiveChannelCountDownRequest;
import net.polyv.live.entity.web.info.LiveChannelCountDownResponse;
import net.polyv.live.entity.web.info.LiveChannelLikesRequest;
import net.polyv.live.entity.web.info.LiveChannelLikesResponse;
import net.polyv.live.entity.web.info.LiveChannelSplashRequest;
import net.polyv.live.entity.web.info.LiveChannelSplashResponse;
import net.polyv.live.entity.web.info.LiveUpdateChannelAuthRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelCountDownRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelLikesRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelLogoRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelNameRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelPublisherRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelSplashRequest;
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
    
    /**
     * 设置频道点赞数和观看热度值
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymxxsz/update-likes/
     * @param liveUpdateChannelLikesRequest 设置频道点赞数和观看热度值请求实体
     * @return 设置频道点赞数和观看热度值返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelLikes(LiveUpdateChannelLikesRequest liveUpdateChannelLikesRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道点赞数和观众热度值
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/live-likes/
     * @param liveChannelLikesRequest 查询频道点赞数和观众热度值请求实体
     * @return 查询频道点赞数和观众热度值返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelLikesResponse channelLikes(LiveChannelLikesRequest liveChannelLikesRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道直播倒计时信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymxxsz/set-countdown/
     * @param liveUpdateChannelCountDownRequest 设置频道直播倒计时信息请求实体
     * @return 设置频道直播倒计时信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelCountDown(LiveUpdateChannelCountDownRequest liveUpdateChannelCountDownRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道直播倒计时信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymxxsz/get-countdown/
     * @param liveChannelCountDownRequest 查询频道直播倒计时信息请求实体
     * @return 查询频道直播倒计时信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelCountDownResponse channelCountDown(LiveChannelCountDownRequest liveChannelCountDownRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道图标
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/updatechannellogo/
     * @param liveUpdateChannelLogoRequest 设置频道图标请求实体
     * @return 设置频道图标返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelLogo(LiveUpdateChannelLogoRequest liveUpdateChannelLogoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置引导开关以及引导图片
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/setsplash/
     * @param liveUpdateChannelSplashRequest 设置引导开关以及引导图片请求实体
     * @return 设置引导开关以及引导图片返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelSplash(LiveUpdateChannelSplashRequest liveUpdateChannelSplashRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置观看条件
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/channel-auth-update/
     * @param liveUpdateChannelAuthRequest 设置观看条件请求实体
     * @return 设置观看条件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelAuth(LiveUpdateChannelAuthRequest liveUpdateChannelAuthRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
