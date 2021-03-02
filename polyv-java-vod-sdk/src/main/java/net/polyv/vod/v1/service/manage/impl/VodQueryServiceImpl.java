package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListRequest;
import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListResponse;
import net.polyv.vod.v1.entity.manage.query.VodSearchVideoListRequest;
import net.polyv.vod.v1.entity.manage.query.VodSearchVideoListResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodQueryService;

/**
 * @author: sadboy
 **/
public class VodQueryServiceImpl extends VodBaseService implements IVodQueryService {
    
    /**
     * 根据授权播放开关状态查询视频
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-search/list-by-playauth/
     * @param vodQueryVideoListRequest 根据授权播放开关状态查询视频请求实体
     * @return 根据授权播放开关状态查询视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodQueryVideoListResponse queryVideoList(VodQueryVideoListRequest vodQueryVideoListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.QUERY_VIDEO_LIST_URL);
        return super.getReturnOne(url, vodQueryVideoListRequest, VodQueryVideoListResponse.class);
    }
    
    /**
     * 查找视频
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-search/search-videos/
     * @param vodSearchVideoListRequest 查找视频请求实体
     * @return 查找视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodSearchVideoListResponse> searchVideoList(VodSearchVideoListRequest vodSearchVideoListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.SEARCH_VIDEO_LIST_URL);
        return super.postFormBodyReturnList(url, vodSearchVideoListRequest, VodSearchVideoListResponse.class);
//        return super.postFormBodyReturnOne(url, vodSearchVideoListRequest, VodSearchVideoListResponse.class);
    }
}
