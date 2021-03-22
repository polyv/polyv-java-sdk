package net.polyv.vod.v1.service.play.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.play.list.VodGetOnePlayListRequest;
import net.polyv.vod.v1.entity.play.list.VodGetOnePlayListResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.play.IVodPlayListService;

/**
 * 播放列表
 * @author: fangyan
 */
public class VodPlayListServiceImpl extends VodBaseService implements IVodPlayListService {
    
    /**
     * 获取单个播放列表
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-play/v-api-play-playlist/play-list/
     * @param vodGetOnePlayListRequest 获取单个播放列表请求实体
     * @return 获取单个播放列表返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetOnePlayListResponse> getOnePlayList(VodGetOnePlayListRequest vodGetOnePlayListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_ONE_PLAY_LIST_URL, VodGlobalConfig.getUserId(),
                vodGetOnePlayListRequest.getId());
        vodGetOnePlayListRequest.setUserId(VodGlobalConfig.getUserId());
        return super.getReturnList(url, vodGetOnePlayListRequest, VodGetOnePlayListResponse.class);
    }
}
