package net.polyv.vod.v1.service.subaccount.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.subaccount.edit.VodUpdateVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryVideoInfoResponse;
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
    
    /**
     * 查询视频信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/get-video-info/
     * @param vodQueryVideoInfoRequest 查询视频信息请求实体
     * @return 查询视频信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryVideoInfoResponse> getVideoInfo(VodQueryVideoInfoRequest vodQueryVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        return super.getReturnList(VodURL.GET_VIDEO_INFO_URL, vodQueryVideoInfoRequest,
                VodQueryVideoInfoResponse.class);
    }
    
    /**
     * 修改视频信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-video-info/
     * @param vodUpdateVideoInfoRequest 修改视频信息请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateVideoInfo(VodUpdateVideoInfoRequest vodUpdateVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        super.postFormBodyReturnOne(VodURL.UPDATE_INFO_URL, vodUpdateVideoInfoRequest, String.class);
        return true;
    }
    
}
