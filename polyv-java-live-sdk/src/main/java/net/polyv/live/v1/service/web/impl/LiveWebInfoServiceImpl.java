package net.polyv.live.v1.service.web.impl;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.polyv.live.v1.config.LiveGlobalConfig;
import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.web.info.LiveChannelCountDownRequest;
import net.polyv.live.v1.entity.web.info.LiveChannelCountDownResponse;
import net.polyv.live.v1.entity.web.info.LiveChannelLikesRequest;
import net.polyv.live.v1.entity.web.info.LiveChannelLikesResponse;
import net.polyv.live.v1.entity.web.info.LiveChannelSplashRequest;
import net.polyv.live.v1.entity.web.info.LiveChannelSplashResponse;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelCountDownRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelLikesRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelLogoRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelNameRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelPublisherRequest;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelSplashRequest;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.web.ILiveWebInfoService;

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
    public Boolean updateChannelName(LiveUpdateChannelNameRequest liveUpdateChannelNameRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_NAME_SET_URL, liveUpdateChannelNameRequest.getChannelId());
        String liveUpdateChannelNameResponse = this.postFormBodyReturnOne(url, liveUpdateChannelNameRequest,
                String.class);
        return "true".equals(liveUpdateChannelNameResponse);
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
    public Boolean updateChannelPublisher(LiveUpdateChannelPublisherRequest liveUpdateChannelPublisherRequest)
            throws IOException, NoSuchAlgorithmException {
        liveUpdateChannelPublisherRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PUBLISHER_SET_URL,
                liveUpdateChannelPublisherRequest.getUserId());
        String liveUpdateChannelPublisherResponse = this.postFormBodyReturnOne(url, liveUpdateChannelPublisherRequest,
                String.class);
        return "true".equals(liveUpdateChannelPublisherResponse);
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
    public LiveChannelSplashResponse getChannelSplash(LiveChannelSplashRequest liveChannelSplashRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_SPLASH_GET_URL, liveChannelSplashRequest.getChannelId());
        return this.postFormBodyReturnOne(url, liveChannelSplashRequest, LiveChannelSplashResponse.class);
        
    }
    
    /**
     * 设置频道点赞数和观看热度值
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymxxsz/update-likes/
     * @param liveUpdateChannelLikesRequest 设置频道点赞数和观看热度值请求实体
     * @return 设置频道点赞数和观看热度值返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelLikes(LiveUpdateChannelLikesRequest liveUpdateChannelLikesRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_LIKES_UPDATE_URL, liveUpdateChannelLikesRequest.getChannelId());
        String liveUpdateChannelLikesResponse = this.postFormBodyReturnOne(url, liveUpdateChannelLikesRequest,
                String.class);
        return "success".equals(liveUpdateChannelLikesResponse);
    }
    
    /**
     * 查询频道点赞数和观众热度值
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/live-likes/
     * @param liveChannelLikesRequest 查询频道点赞数和观众热度值请求实体
     * @return 查询频道点赞数和观众热度值返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelLikesResponse getChannelLikes(LiveChannelLikesRequest liveChannelLikesRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIKES_GET_URL;
        List<LiveChannelLikesResponse.ChannelLikes> channelLikes = this.postFormBodyReturnList(url,
                liveChannelLikesRequest, LiveChannelLikesResponse.ChannelLikes.class);
        LiveChannelLikesResponse liveChannelLikesResponse = new LiveChannelLikesResponse();
        liveChannelLikesResponse.setChannelLikes(channelLikes);
        return liveChannelLikesResponse;
    }
    
    /**
     * 设置频道直播倒计时信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymxxsz/set-countdown/
     * @param liveUpdateChannelCountDownRequest 设置频道直播倒计时信息请求实体
     * @return 设置频道直播倒计时信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelCountDown(LiveUpdateChannelCountDownRequest liveUpdateChannelCountDownRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_UPDATE_COUNT_DOWN_URL,
                liveUpdateChannelCountDownRequest.getChannelId());
        String liveUpdateChannelCountDownResponse = this.postFormBodyReturnOne(url, liveUpdateChannelCountDownRequest,
                String.class);
        return "success".equals(liveUpdateChannelCountDownResponse);
    }
    
    /**
     * 查询频道直播倒计时信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymxxsz/get-countdown/
     * @param liveChannelCountDownRequest 查询频道直播倒计时信息请求实体
     * @return 查询频道直播倒计时信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelCountDownResponse getChannelCountDown(LiveChannelCountDownRequest liveChannelCountDownRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_COUNT_DOWN_URL, liveChannelCountDownRequest.getChannelId());
        LiveChannelCountDownResponse liveChannelCountDownResponse = this.postFormBodyReturnOne(url,
                liveChannelCountDownRequest, LiveChannelCountDownResponse.class);
        return liveChannelCountDownResponse;
    }
    
    /**
     * 设置频道图标
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/updatechannellogo/
     * @param liveUpdateChannelLogoRequest 设置频道图标请求实体
     * @return 设置频道图标返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateChannelLogo(LiveUpdateChannelLogoRequest liveUpdateChannelLogoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_LOGO_SET_URL, liveUpdateChannelLogoRequest.getChannelId());
        Map<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("imgfile", liveUpdateChannelLogoRequest.getImgfile());
        String liveUpdateChannelLogoResponse = this.uploadOneFile(url, liveUpdateChannelLogoRequest, fileMap,
                String.class);
        return liveUpdateChannelLogoResponse;
    }
    
    /**
     * 设置引导开关以及引导图片
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymxxsz/setsplash/
     * @param liveUpdateChannelSplashRequest 设置引导开关以及引导图片请求实体
     * @return 设置引导开关以及引导图片返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateChannelSplash(LiveUpdateChannelSplashRequest liveUpdateChannelSplashRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_SPLASH_SET_URL, liveUpdateChannelSplashRequest.getChannelId());
        String liveUpdateChannelSplashResponse;
        File imgFile = liveUpdateChannelSplashRequest.getImgfile();
        if (imgFile == null) {
            liveUpdateChannelSplashResponse = this.postFormBodyReturnOne(url, liveUpdateChannelSplashRequest,
                    String.class);
        } else {
            Map<String, File> fileMap = new HashMap<String, File>();
            fileMap.put("imgfile", imgFile);
            liveUpdateChannelSplashResponse = this.uploadOneFile(url, liveUpdateChannelSplashRequest, fileMap,
                    String.class);
        }
        return liveUpdateChannelSplashResponse;
    }
    
    
}
