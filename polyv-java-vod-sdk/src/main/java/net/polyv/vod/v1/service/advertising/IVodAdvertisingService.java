package net.polyv.vod.v1.service.advertising;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.advertising.VodCreateAdvertisingRequest;

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
}
