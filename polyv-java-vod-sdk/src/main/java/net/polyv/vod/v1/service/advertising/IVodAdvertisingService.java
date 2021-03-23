package net.polyv.vod.v1.service.advertising;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.advertising.VodCreateAdvertisingRequest;
import net.polyv.vod.v1.entity.advertising.VodDeleteAdvertisingRequest;
import net.polyv.vod.v1.entity.advertising.VodGetAdvertisingListRequest;
import net.polyv.vod.v1.entity.advertising.VodGetAdvertisingListResponse;

/**
 * 广告管理
 * @author: fangyan
 */
public interface IVodAdvertisingService {
    /**
     * 创建视频广告
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/ad-management/advertising-create/
     * @param vodCreateAdvertisingRequest 创建视频广告请求实体
     * @return String
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createAdvertising(VodCreateAdvertisingRequest vodCreateAdvertisingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除视频广告
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/ad-management/delet-advertising/
     * @param vodDeleteAdvertisingRequest 删除视频广告请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteAdvertising(VodDeleteAdvertisingRequest vodDeleteAdvertisingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取视频广告列表
     * API地址:https://dev.polyv.net/2018/videoproduct/v-api/ad-management/get-advertising/
     * @param vodGetAdvertisingListRequest 获取视频广告列表请求实体
     * @return 获取视频广告列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetAdvertisingListResponse getAdvertisingList(VodGetAdvertisingListRequest vodGetAdvertisingListRequest)
            throws IOException, NoSuchAlgorithmException;
}
