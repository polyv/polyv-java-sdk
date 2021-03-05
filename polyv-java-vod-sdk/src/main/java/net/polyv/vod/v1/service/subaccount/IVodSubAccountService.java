package net.polyv.vod.v1.service.subaccount;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.subaccount.query.VodSearchVideoListRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodSearchVideoListResponse;

/**
 * 子账号相关
 * @author: fangyan
 */
public interface IVodSubAccountService {
    
    /**
     * 搜索视频
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/list-video/
     * @param vodSearchVideoListRequest 搜索视频请求实体
     * @return 搜索视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodSearchVideoListResponse searchVideoList(VodSearchVideoListRequest vodSearchVideoListRequest)
            throws IOException, NoSuchAlgorithmException;
}
