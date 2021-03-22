package net.polyv.vod.v1.service.encryptionsettings;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.encryptionsettings.VodGetEncryptionSettingsRequest;
import net.polyv.vod.v1.entity.encryptionsettings.VodGetEncryptionSettingsResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.encryptionsettings.impl.VodEncryptionSettingsServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 加密设置
 * @author: fangyan
 */
@Slf4j
public class VodEncryptionSettingsServiceImplTest extends BaseTest {
    /**
     * 测试获取账号加密设置
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetEncryptionSettings() throws IOException, NoSuchAlgorithmException {
        VodGetEncryptionSettingsRequest vodGetEncryptionSettingsRequest = new VodGetEncryptionSettingsRequest();
        VodGetEncryptionSettingsResponse vodGetEncryptionSettingsResponse = null;
        try {
            vodGetEncryptionSettingsRequest.setRequestId(VodSignUtil.generateUUID());
            vodGetEncryptionSettingsResponse = new VodEncryptionSettingsServiceImpl().getEncryptionSettings(
                    vodGetEncryptionSettingsRequest);
            Assert.assertNotNull(vodGetEncryptionSettingsResponse);
            if (vodGetEncryptionSettingsResponse != null) {
                log.debug("测试获取账号加密设置成功,{}", JSON.toJSONString(vodGetEncryptionSettingsResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
}
