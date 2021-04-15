package net.polyv.vod.v1.service.encryptionsettings;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.encryptionsettings.VodGetEncryptionSettingsRequest;
import net.polyv.vod.v1.entity.encryptionsettings.VodGetEncryptionSettingsResponse;
import net.polyv.vod.v1.entity.encryptionsettings.VodUpdateEncryptionSettingsRequest;
import net.polyv.vod.v1.entity.encryptionsettings.VodUpdateEncryptionSettingsResponse;

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
    
    /**
     * 设置账号加密设置
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-encryptionsetting/set-playsafe/
     * @param vodUpdateEncryptionSettingsRequest 设置账号加密设置请求实体
     * @return 设置账号加密设置返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodUpdateEncryptionSettingsResponse updateEncryptionSettings(
            VodUpdateEncryptionSettingsRequest vodUpdateEncryptionSettingsRequest)
            throws IOException, NoSuchAlgorithmException;
}
