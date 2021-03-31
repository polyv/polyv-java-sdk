package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.screenshot.VodCreateScreenshotTaskRequest;
import net.polyv.vod.v1.entity.manage.screenshot.VodGetScreenshotTaskStatusRequest;
import net.polyv.vod.v1.entity.manage.screenshot.VodGetScreenshotTaskStatusResponse;
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
     * 回调说明：如设置了callbackUrl，值为http://example.polyv.net/snapshot-callback.do
     * 回调说明：那么截图任务完成后，polyv会回调该接口，并带上签名信息，开发者可以通过签名信息来校验调用是否为polyv的合法调用，具体的签名规则：md5("snapshot" + vid + secretKey)
     * 回调说明：。如vid="e6b23c6f51350f106556806a576b1942_e"，secretKey="testKey"，那么sign="3adb60893894d422d00ed2efae8c41f3"
     * 回调说明：(小写md5)。最终回调的url为http://example.polyv.net/snapshot-callback.do?sign=3adb60893894d422d00ed2efae8c41f3
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
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
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
    
    /**
     * 测试获取截图任务状态
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetScreenshotTaskStatus() throws IOException, NoSuchAlgorithmException {
        VodGetScreenshotTaskStatusRequest vodGetScreenshotTaskStatusRequest = new VodGetScreenshotTaskStatusRequest();
        VodGetScreenshotTaskStatusResponse vodGetScreenshotTaskStatusResponse = null;
        try {
            vodGetScreenshotTaskStatusRequest.setTaskId(1146).setRequestId(VodSignUtil.generateUUID());
            vodGetScreenshotTaskStatusResponse = new VodScreenshotServiceImpl().getScreenshotTaskStatus(
                    vodGetScreenshotTaskStatusRequest);
            Assert.assertNotNull(vodGetScreenshotTaskStatusResponse);
            if (vodGetScreenshotTaskStatusResponse != null) {
                log.debug("测试获取截图任务状态成功,{}", JSON.toJSONString(vodGetScreenshotTaskStatusResponse));
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
