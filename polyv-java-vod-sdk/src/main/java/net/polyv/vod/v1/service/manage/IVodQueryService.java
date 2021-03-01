package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListRequest;
import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListResponse;
import net.polyv.vod.v1.entity.manage.query.VodSearchVideoListRequest;
import net.polyv.vod.v1.entity.manage.query.VodSearchVideoListResponse;

/**
 * @author: sadboy
 **/
public interface IVodQueryService {
    
    /**
     * 根据授权播放开关状态查询视频
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-search/list-by-playauth/
     * @param vodQueryVideoListRequest 根据授权播放开关状态查询视频请求实体
     * @return 根据授权播放开关状态查询视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodQueryVideoListResponse queryVideoList(VodQueryVideoListRequest vodQueryVideoListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查找视频
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-search/search-videos/
     * @param vodSearchVideoListRequest 查找视频请求实体
     * @return 查找视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodSearchVideoListResponse> searchVideoList(VodSearchVideoListRequest vodSearchVideoListRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
