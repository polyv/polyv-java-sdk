package net.polyv.vod.v1.service.play;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListRequest;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListResponse;

/**
 * 播放器设置
 * @author: fangyan
 */
public interface IVodPlayerSettingsService {
    /**
     * 获取用户下所有播放器列表接口
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-play/v-api-play-player/player-list/
     * @param vodGetPlayerListRequest 获取用户下所有播放器列表接口请求实体
     * @return 获取用户下所有播放器列表接口返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodGetPlayerListResponse> getPlayerList(VodGetPlayerListRequest vodGetPlayerListRequest)
            throws IOException, NoSuchAlgorithmException;
}
