package net.polyv.vod.v1.service.play.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlaySafeTokenRequest;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlaySafeTokenResponse;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListRequest;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.play.IVodPlayerSettingsService;

/**
 * 播放器设置
 * @author: fangyan
 */
public class VodPlayerSettingsServiceImpl extends VodBaseService implements IVodPlayerSettingsService {
    
    /**
     * 获取用户下所有播放器列表
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-play/v-api-play-player/player-list/
     * @param vodGetPlayerListRequest 获取用户下所有播放器列表请求实体
     * @return 获取用户下所有播放器列表返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetPlayerListResponse> getPlayerList(VodGetPlayerListRequest vodGetPlayerListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_PLAYER_LIST_URL, VodGlobalConfig.getUserId());
        return super.getReturnList(url, vodGetPlayerListRequest, VodGetPlayerListResponse.class);
    }
    
    /**
     * 获取PlaySafeToken
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-play/create-playsafe-token/
     * @param vodGetPlaySafeTokenRequest 获取PlaySafeToken请求实体
     * @return 获取PlaySafeToken返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetPlaySafeTokenResponse getPlaySafeToken(VodGetPlaySafeTokenRequest vodGetPlaySafeTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        vodGetPlaySafeTokenRequest.setUserId(VodGlobalConfig.getUserId());
        return super.postFormBodyReturnOne(VodURL.VOD_GET_PLAY_SAFE_TOKEN_URL, vodGetPlaySafeTokenRequest,
                VodGetPlaySafeTokenResponse.class);
    }
}
