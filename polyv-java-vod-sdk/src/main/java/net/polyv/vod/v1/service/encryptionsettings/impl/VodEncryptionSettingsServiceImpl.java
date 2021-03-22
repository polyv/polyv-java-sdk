package net.polyv.vod.v1.service.encryptionsettings.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.encryptionsettings.VodGetEncryptionSettingsRequest;
import net.polyv.vod.v1.entity.encryptionsettings.VodGetEncryptionSettingsResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.encryptionsettings.IVodEncryptionSettingsService;

/**
 * 加密设置
 * @author: fangyan
 */
public class VodEncryptionSettingsServiceImpl extends VodBaseService implements IVodEncryptionSettingsService {
    /**
     * 获取账号加密设置
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-encryptionsetting/get-playsafe/
     * @param vodGetEncryptionSettingsRequest 获取账号加密设置请求实体
     * @return 获取账号加密设置请返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetEncryptionSettingsResponse getEncryptionSettings(
            VodGetEncryptionSettingsRequest vodGetEncryptionSettingsRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_ENCRYPTION_SETTINGS_URL, VodGlobalConfig.getUserId());
        vodGetEncryptionSettingsRequest.setUserId(VodGlobalConfig.getUserId());
        return super.getReturnOne(url, vodGetEncryptionSettingsRequest, VodGetEncryptionSettingsResponse.class);
    }
}
