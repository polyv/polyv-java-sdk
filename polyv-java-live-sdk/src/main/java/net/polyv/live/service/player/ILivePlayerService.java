package net.polyv.live.service.player;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.live.entity.chat.LiveBadWordRequest;
import net.polyv.live.entity.chat.LiveBadWordResponse;
import net.polyv.live.entity.chat.LiveChatBannedIPRequest;
import net.polyv.live.entity.chat.LiveChatDelSingleMsgRequest;
import net.polyv.live.entity.chat.LiveDelBannedDataRequest;
import net.polyv.live.entity.chat.LiveGetBadwordIPRequest;
import net.polyv.live.entity.chat.LiveGetBannedListRequest;
import net.polyv.live.entity.chat.LiveGetChatAdminResponse;
import net.polyv.live.entity.chat.LiveGetConsultingRecordRequest;
import net.polyv.live.entity.chat.LiveGetConsultingRecordResponse;
import net.polyv.live.entity.chat.LiveGetHistoryChatMsgRequest;
import net.polyv.live.entity.chat.LiveGetHistoryChatMsgResponse;
import net.polyv.live.entity.chat.LiveGetQuestionStatisticalRequest;
import net.polyv.live.entity.chat.LiveGetQuestionStatisticalResponse;
import net.polyv.live.entity.chat.LiveKickedListRequest;
import net.polyv.live.entity.chat.LiveKickedListResponse;
import net.polyv.live.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.entity.chat.LiveSetChatAdminDataRequest;
import net.polyv.live.entity.chat.LiveSetTeacherDataRequest;
import net.polyv.live.entity.player.LiveSetPlayerImgRequest;
import net.polyv.live.entity.player.LiveSetPlayerLogoRequest;
import net.polyv.live.entity.player.LiveSetPlayerPauseAdvertRequest;
import net.polyv.live.entity.player.LiveSetWarmupEnableRequest;

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
}
