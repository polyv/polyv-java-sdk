package net.polyv.vod.v1.service.advertising.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.advertising.VodCreateAdvertisingRequest;
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
        vodCreateAdvertisingRequest.setUserId(VodGlobalConfig.getUserId());
        if (vodCreateAdvertisingRequest.getFile() == null || !vodCreateAdvertisingRequest.getFile().exists()) {
            throw new FileNotFoundException("文件不存在");
        }
        HashMap files = new HashMap<String, File>(1);
        files.put(vodCreateAdvertisingRequest.FILE_NAME, vodCreateAdvertisingRequest.getFile());
        return super.uploadOneFile(url, vodCreateAdvertisingRequest, files, String.class);
    }
}
