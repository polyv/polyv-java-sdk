package net.polyv.vod.v1.service.advertising;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.advertising.VodCreateAdvertisingRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.advertising.impl.VodAdvertisingServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 广告管理
 * @author: fangyan
 */
@Slf4j
public class VodAdvertisingServiceImplTest extends BaseTest {
    /**
     * 测试创建视频广告
     * 返回：广告ID
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateAdvertising() throws IOException, NoSuchAlgorithmException {
        VodCreateAdvertisingRequest vodCreateAdvertisingRequest = new VodCreateAdvertisingRequest();
        String vodCreateAdvertisingResponse = null;
        try {
            String filePath = getClass().getResource("/img/cover.jpg").getPath();
            vodCreateAdvertisingRequest.setStartDate(super.getDate(2021, 2, 22))
                    .setEndDate(super.getDate(2021, 3, 22))
                    .setTitle("测试广告")
                    .setFile(new File(filePath))
                    .setSize(2)
                    .setCategoryIds("1")
                    .setLocation(1)
                    .setStatus(10)
                    .setUpTime("00:00:00")
                    .setOffTime("23:59:59")
                    .setNote("测试广告描述")
                    .setSkipAd("Y")
                    .setSkipOffset(1)
                    .setSkipButtonLabel("跳过广告")
                    .setRequestId(VodSignUtil.generateUUID());
            vodCreateAdvertisingResponse = new VodAdvertisingServiceImpl().createAdvertising(
                    vodCreateAdvertisingRequest);
            Assert.assertNotNull(vodCreateAdvertisingResponse);
            if (vodCreateAdvertisingResponse != null) {
                log.debug("测试创建视频广告成功,{}", vodCreateAdvertisingResponse);
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
