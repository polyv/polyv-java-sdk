package net.polyv.vod.v1.service.play;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.play.list.VodGetOnePlayListRequest;
import net.polyv.vod.v1.entity.play.list.VodGetOnePlayListResponse;

/**
 * 播放列表
 * @author: fangyan
 */
public interface IVodPlayListService {
    /**
     * 获取单个播放列表
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-play/v-api-play-playlist/play-list/
     * @param vodGetOnePlayListRequest 获取单个播放列表请求实体
     * @return 获取单个播放列表返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodGetOnePlayListResponse> getOnePlayList(VodGetOnePlayListRequest vodGetOnePlayListRequest)
            throws IOException, NoSuchAlgorithmException;
}
