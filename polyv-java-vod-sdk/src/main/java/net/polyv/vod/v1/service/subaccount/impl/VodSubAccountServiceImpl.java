package net.polyv.vod.v1.service.subaccount.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.subaccount.query.VodSearchVideoListRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodSearchVideoListResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.subaccount.IVodSubAccountService;

/**
 * 子账号相关
 * @author: fangyan
 */
public class VodSubAccountServiceImpl extends VodBaseService implements IVodSubAccountService {
    
    /**
     * 搜索视频
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/list-video/
     * @param vodSearchVideoListRequest 搜索视频请求实体
     * @return 搜索视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodSearchVideoListResponse searchVideoList(VodSearchVideoListRequest vodSearchVideoListRequest)
            throws IOException, NoSuchAlgorithmException {
        return super.getReturnOne(VodURL.LIST_VIDEO_URL, vodSearchVideoListRequest, VodSearchVideoListResponse.class);
    }
}
