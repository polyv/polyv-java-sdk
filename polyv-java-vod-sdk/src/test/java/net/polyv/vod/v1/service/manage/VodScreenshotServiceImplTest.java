package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.screenshot.VodCreateScreenshotTaskRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodScreenshotServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 视频截图
 * @author: fangyan
 */
@Slf4j
public class VodScreenshotServiceImplTest extends BaseTest {
    
    /**
     * 测试添加指定时间点截图任务
     * 约束：2、接口用于添加时间点截图任务，每个任务允许截图20张
     * 返回：任务id
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateScreenshotTask() throws IOException, NoSuchAlgorithmException {
        VodCreateScreenshotTaskRequest vodCreateScreenshotTaskRequest = new VodCreateScreenshotTaskRequest();
        Integer vodCreateScreenshotTaskResponse = null;
        try {
            vodCreateScreenshotTaskRequest.setUploadTime(new Date())
                    .setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setOffsetTimes("8")
                    .setRequestId(VodSignUtil.generateUUID());
            vodCreateScreenshotTaskResponse = new VodScreenshotServiceImpl().createScreenshotTask(
                    vodCreateScreenshotTaskRequest);
            Assert.assertNotNull(vodCreateScreenshotTaskResponse);
            if (vodCreateScreenshotTaskResponse != null) {
                log.debug("测试添加指定时间点截图任务成功,{}", vodCreateScreenshotTaskResponse);
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
