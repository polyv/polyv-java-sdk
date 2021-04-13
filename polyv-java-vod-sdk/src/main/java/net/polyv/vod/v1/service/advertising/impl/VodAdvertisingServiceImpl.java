package net.polyv.vod.v1.service.advertising.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.advertising.VodCreateAdvertisingRequest;
import net.polyv.vod.v1.entity.advertising.VodDeleteAdvertisingRequest;
import net.polyv.vod.v1.entity.advertising.VodGetAdvertisingListRequest;
import net.polyv.vod.v1.entity.advertising.VodGetAdvertisingListResponse;
import net.polyv.vod.v1.entity.advertising.VodUpdateAdvertisingRequest;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.advertising.IVodAdvertisingService;

/**
 * 广告管理
 * @author: fangyan
 */
public class VodAdvertisingServiceImpl extends VodBaseService implements IVodAdvertisingService {
    /**
     * 创建视频广告
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/ad-management/advertising-create/
     * @param vodCreateAdvertisingRequest 创建视频广告请求实体
     * @return String
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String createAdvertising(VodCreateAdvertisingRequest vodCreateAdvertisingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_CREATE_ADVERTISING_URL, VodGlobalConfig.getUserId());
        if (vodCreateAdvertisingRequest.getFile() == null || !vodCreateAdvertisingRequest.getFile().exists()) {
            throw new FileNotFoundException("文件不存在");
        }
        HashMap files = new HashMap<String, File>(1);
        files.put(VodCreateAdvertisingRequest.FILE_NAME, vodCreateAdvertisingRequest.getFile());
        return super.uploadOneFile(url, vodCreateAdvertisingRequest, files, String.class);
    }
    
    /**
     * 删除视频广告
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/ad-management/delet-advertising/
     * @param vodDeleteAdvertisingRequest 删除视频广告请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteAdvertising(VodDeleteAdvertisingRequest vodDeleteAdvertisingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_DELETE_ADVERTISING_URL, VodGlobalConfig.getUserId());
        return super.postFormBodyReturnOne(url, vodDeleteAdvertisingRequest, Boolean.class);
    }
    
    /**
     * 获取视频广告列表
     * API地址:https://dev.polyv.net/2018/videoproduct/v-api/ad-management/get-advertising/
     * @param vodGetAdvertisingListRequest 获取视频广告列表请求实体
     * @return 获取视频广告列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetAdvertisingListResponse getAdvertisingList(VodGetAdvertisingListRequest vodGetAdvertisingListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_ADVERTISING_LIST_URL, VodGlobalConfig.getUserId());
        return super.getReturnOne(url, vodGetAdvertisingListRequest, VodGetAdvertisingListResponse.class);
    }
    
    /**
     * 修改视频广告
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/ad-management/edit/
     * @param vodUpdateAdvertisingRequest 修改视频广告请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateAdvertising(VodUpdateAdvertisingRequest vodUpdateAdvertisingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_UPDATE_ADVERTISING_URL, VodGlobalConfig.getUserId());
        return super.postFormBodyReturnOne(url, vodUpdateAdvertisingRequest, Boolean.class);
    }
}
