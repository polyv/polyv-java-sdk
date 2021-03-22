package net.polyv.vod.v1.service.encryptionsettings;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.encryptionsettings.VodGetEncryptionSettingsRequest;
import net.polyv.vod.v1.entity.encryptionsettings.VodGetEncryptionSettingsResponse;

/**
 * 加密设置
 * @author: fangyan
 */
public interface IVodEncryptionSettingsService {
    /**
     * 获取账号加密设置
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-encryptionsetting/get-playsafe/
     * @param vodGetEncryptionSettingsRequest 获取账号加密设置请求实体
     * @return 获取账号加密设置请返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetEncryptionSettingsResponse getEncryptionSettings(
            VodGetEncryptionSettingsRequest vodGetEncryptionSettingsRequest)
            throws IOException, NoSuchAlgorithmException;
}
