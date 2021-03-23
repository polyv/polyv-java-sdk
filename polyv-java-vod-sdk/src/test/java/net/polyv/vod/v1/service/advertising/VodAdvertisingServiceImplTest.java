package net.polyv.vod.v1.service.advertising;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.advertising.VodCreateAdvertisingRequest;
import net.polyv.vod.v1.entity.advertising.VodDeleteAdvertisingRequest;
import net.polyv.vod.v1.entity.advertising.VodGetAdvertisingListRequest;
import net.polyv.vod.v1.entity.advertising.VodGetAdvertisingListResponse;
import net.polyv.vod.v1.entity.advertising.VodUpdateAdvertisingRequest;
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
//    @Test
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
    
    /**
     * 测试删除视频广告
     * 返回：true为删除成功，false为删除失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteAdvertising() throws IOException, NoSuchAlgorithmException {
        VodDeleteAdvertisingRequest vodDeleteAdvertisingRequest = new VodDeleteAdvertisingRequest();
        Boolean vodDeleteAdvertisingResponse = null;
        try {
            //准备测试数据
            String advertisingId = super.createAdvertising();
            
            vodDeleteAdvertisingRequest.setAdvertisingId(advertisingId).setRequestId(VodSignUtil.generateUUID());
            vodDeleteAdvertisingResponse = new VodAdvertisingServiceImpl().deleteAdvertising(
                    vodDeleteAdvertisingRequest);
            Assert.assertTrue(vodDeleteAdvertisingResponse);
            if (vodDeleteAdvertisingResponse) {
                log.debug("测试删除视频广告成功");
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
     * 测试获取视频广告列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetAdvertisingList() throws IOException, NoSuchAlgorithmException {
        VodGetAdvertisingListRequest vodGetAdvertisingListRequest = new VodGetAdvertisingListRequest();
        VodGetAdvertisingListResponse vodGetAdvertisingListResponse = null;
        try {
            vodGetAdvertisingListRequest.setCurrentPage(1).setPageSize(10).setRequestId(VodSignUtil.generateUUID());
            vodGetAdvertisingListResponse = new VodAdvertisingServiceImpl().getAdvertisingList(
                    vodGetAdvertisingListRequest);
            Assert.assertNotNull(vodGetAdvertisingListResponse);
            if (vodGetAdvertisingListResponse != null) {
                log.debug("测试删除视频广告成功,{}", JSON.toJSONString(vodGetAdvertisingListResponse));
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
     * 测试修改视频广告
     * 返回：true为修改成功，false为修改失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateAdvertising() throws IOException, NoSuchAlgorithmException {
        VodUpdateAdvertisingRequest vodUpdateAdvertisingRequest = new VodUpdateAdvertisingRequest();
        Boolean vodUpdateAdvertisingResponse = null;
        try {
            String filePath = getClass().getResource("/img/cover.jpg").getPath();
            vodUpdateAdvertisingRequest.setAdvertisingId("ea7a04430dd04d01853d")
                    .setCategoryIds("1")
                    .setStartDate(super.getDate(2021, 2, 22))
                    .setEndDate(super.getDate(2021, 3, 22))
                    .setTitle("测试广告(Junit测试,勿删)")
                    .setFile(new File(filePath))
                    .setSize(2)
                    .setCategoryIds("1")
                    .setLocation(1)
                    .setStatus(0)
                    .setUpTime("00:00:00")
                    .setOffTime("23:59:59")
                    .setNote("测试广告描述")
                    .setSkipAd("Y")
                    .setSkipOffset(1)
                    .setSkipButtonLabel("跳过广告")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateAdvertisingResponse = new VodAdvertisingServiceImpl().updateAdvertising(
                    vodUpdateAdvertisingRequest);
            Assert.assertTrue(vodUpdateAdvertisingResponse);
            if (vodUpdateAdvertisingResponse) {
                log.debug("测试修改视频广告成功");
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
