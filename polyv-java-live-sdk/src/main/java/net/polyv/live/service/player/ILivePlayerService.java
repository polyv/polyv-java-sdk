package net.polyv.live.service.player;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.player.LiveSetPlayerHeaderAdvertRequest;
import net.polyv.live.entity.player.LiveSetPlayerImgRequest;
import net.polyv.live.entity.player.LiveSetPlayerLogoRequest;
import net.polyv.live.entity.player.LiveSetPlayerPauseAdvertRequest;
import net.polyv.live.entity.player.LiveSetWarmupEnableRequest;
import net.polyv.live.entity.player.LiveSetWarmupVedioRequest;

/**
 * 直播签到管理
 * @author: thomas
 **/
public interface ILivePlayerService {

    /**
     * 设置播放器暖场图片，API地址：https://dev.polyv.net/2017/liveproduct/l-player/updatecoverimage/
     * @param liveSetPlayerImgRequest 设置播放器暖场图片请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean setPlayerImg(LiveSetPlayerImgRequest liveSetPlayerImgRequest)
            throws IOException, NoSuchAlgorithmException;
    /**
     * 设置频道的暖场设置开关，API地址：https://dev.polyv.net/2019/liveproduct/l-player/set-warmup-enabled/
     * @param liveSetWarmupEnableRequest 设置频道的暖场设置开关
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean setPlayerWarmupEnable(LiveSetWarmupEnableRequest liveSetWarmupEnableRequest)
            throws IOException, NoSuchAlgorithmException;
    
    
    /**
     *设置播放器Logo，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatelogo/
     * @param liveSetWarmupEnableRequest 设置播放器Logo请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
   
    public Boolean setPlayerLogo(LiveSetPlayerLogoRequest liveSetWarmupEnableRequest)
            throws IOException, NoSuchAlgorithmException ;
    /**
     *设置播放器暂停广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatestop/
     * @param liveSetPlayerPauseAdvertRequest 设置播放器暂停广告请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    public Boolean setPlayerPauseAdvert(LiveSetPlayerPauseAdvertRequest liveSetPlayerPauseAdvertRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     *设置播放器片头广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatehead/
     * @param liveSetPlayerHeaderAdvertRequest 设置播放器片头广告请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    public Boolean setPlayerHeaderAdvert(LiveSetPlayerHeaderAdvertRequest liveSetPlayerHeaderAdvertRequest)
            throws IOException, NoSuchAlgorithmException ;
    
    /**
     * 设置播放器暖场视频，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatewarmupflv/
     * @param liveSetWarmupVedioRequest 设置播放器暖场视频请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    public Boolean setPlayerWarmUpVedio(LiveSetWarmupVedioRequest liveSetWarmupVedioRequest)
            throws IOException, NoSuchAlgorithmException;
}
