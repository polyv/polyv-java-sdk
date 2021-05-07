package net.polyv.vod.v1.service.play;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.play.payersettings.VodGetPlaySafeTokenRequest;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlaySafeTokenResponse;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListRequest;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListResponse;

/**
 * 播放器设置
 * @author: fangyan
 */
public interface IVodPlayerSettingsService {
    /**
     * 获取用户下所有播放器列表
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-play/v-api-play-player/player-list/
     * @param vodGetPlayerListRequest 获取用户下所有播放器列表请求实体
     * @return 获取用户下所有播放器列表返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodGetPlayerListResponse> getPlayerList(VodGetPlayerListRequest vodGetPlayerListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取PlaySafeToken
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-play/create-playsafe-token/
     * @param vodGetPlaySafeTokenRequest 获取PlaySafeToken请求实体
     * @return 获取PlaySafeToken返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetPlaySafeTokenResponse getPlaySafeToken(VodGetPlaySafeTokenRequest vodGetPlaySafeTokenRequest)
            throws IOException, NoSuchAlgorithmException;
}
