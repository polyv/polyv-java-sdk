package net.polyv.live.v1.service.player.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.player.LiveSetPlayerHeaderAdvertRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerImgRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerLogoRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerPauseAdvertRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerUrlMarqueeRequest;
import net.polyv.live.v1.entity.player.LiveSetWarmupEnableRequest;
import net.polyv.live.v1.entity.player.LiveSetWarmupVedioRequest;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.player.ILivePlayerService;

/**
 * 直播签到管理
 * @author: thomas
 **/
@Slf4j
public class LivePlayerServiceImpl extends LiveBaseService implements ILivePlayerService {
    
    /**
     * 设置播放器暖场图片，API地址：https://dev.polyv.net/2017/liveproduct/l-player/updatecoverimage/
     * @param liveSetPlayerImgRequest 设置播放器暖场图片请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setPlayerImg(LiveSetPlayerImgRequest liveSetPlayerImgRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.PLAYER_SET_IMG_URL, liveSetPlayerImgRequest.getChannelId());
        return super.postFormBodyReturnOne(url, liveSetPlayerImgRequest, Boolean.class);
    }
    
    /**
     * 设置频道的暖场设置开关，API地址：https://dev.polyv.net/2019/liveproduct/l-player/set-warmup-enabled/
     * @param liveSetWarmupEnableRequest 设置频道的暖场设置开关
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setPlayerWarmupEnable(LiveSetWarmupEnableRequest liveSetWarmupEnableRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.PLAYER_SET_WARMUP_ENABLE_URL;
        return "success".equalsIgnoreCase(super.postFormBodyReturnOne(url, liveSetWarmupEnableRequest, String.class));
    }
    
    /**
     * 设置播放器Logo，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatelogo/
     * @param liveSetWarmupEnableRequest 设置播放器Logo请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setPlayerLogo(LiveSetPlayerLogoRequest liveSetWarmupEnableRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.PLAYER_SET_CHANNEL_LOGO_URL, liveSetWarmupEnableRequest.getChannelId());
        return super.postFormBodyReturnOne(url, liveSetWarmupEnableRequest, Boolean.class);
    }
    
    /**
     * 设置播放器暂停广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatestop/
     * @param liveSetPlayerPauseAdvertRequest 设置播放器暂停广告请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setPlayerPauseAdvert(LiveSetPlayerPauseAdvertRequest liveSetPlayerPauseAdvertRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.PLAYER_SET_CHANNEL_PAUSE_ADVERT_URL,
                liveSetPlayerPauseAdvertRequest.getChannelId());
        Boolean result = super.postFormBodyReturnOne(url, liveSetPlayerPauseAdvertRequest, Boolean.class);
        if (result && LiveConstant.Flag.YES.getFlag().equals(liveSetPlayerPauseAdvertRequest.getEnabled())) {
            liveSetPlayerPauseAdvertRequest.setEnabled(null).setTimestamp(null).setSign(null);
            Boolean paramResult = super.postFormBodyReturnOne(url, liveSetPlayerPauseAdvertRequest, Boolean.class);
            return  paramResult;
        }else{
            return result ;
        }
    }
    
    /**
     * 设置播放器片头广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatehead/
     * @param liveSetPlayerHeaderAdvertRequest 设置播放器片头广告请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setPlayerHeaderAdvert(LiveSetPlayerHeaderAdvertRequest liveSetPlayerHeaderAdvertRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.PLAYER_SET_CHANNEL_HEADER_ADVERT_URL,
                liveSetPlayerHeaderAdvertRequest.getChannelId());
        Boolean result = super.postFormBodyReturnOne(url, liveSetPlayerHeaderAdvertRequest, Boolean.class);
        Boolean paramResult = Boolean.FALSE;
        if (result && LiveConstant.Flag.YES.getFlag().equals(liveSetPlayerHeaderAdvertRequest.getEnabled())) {
            liveSetPlayerHeaderAdvertRequest.setEnabled(null).setTimestamp(null).setSign(null);
            paramResult = super.postFormBodyReturnOne(url, liveSetPlayerHeaderAdvertRequest, Boolean.class);
            return paramResult;
        }else{
            return result ;
        }
    }
    
    /**
     * 设置播放器暖场视频，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatewarmupflv/
     * @param liveSetWarmupVedioRequest 设置播放器暖场视频请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setPlayerWarmUpVedio(LiveSetWarmupVedioRequest liveSetWarmupVedioRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.PLAYER_SET_CHANNEL_WARMUP_VEDIO_URL, liveSetWarmupVedioRequest.getChannelId());
        return super.postFormBodyReturnOne(url, liveSetWarmupVedioRequest, Boolean.class);
    }
    
    /**
     * 设置播放器自定义url跑马灯
     * API地址：https://dev.polyv.net/2017/liveproduct/l-player/set-diyurl-marquee/
     * @param liveSetPlayerUrlMarqueeRequest 设置播放器自定义url跑马灯请求实体
     * @return 设置播放器自定义url跑马灯返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setPlayerUrlMarquee(LiveSetPlayerUrlMarqueeRequest liveSetPlayerUrlMarqueeRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SET_PLAYER_URL_MARQUEE_URL,liveSetPlayerUrlMarqueeRequest.getChannelId());
        String liveSetPlayerUrlMarqueeResponse = this.postFormBodyReturnOne(url,liveSetPlayerUrlMarqueeRequest,String.class);
        return "success".equals(liveSetPlayerUrlMarqueeResponse);
    }
    
    
}
