package net.polyv.vod.v1.service.manage;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.barrage.VodCreateBarrageRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodCreateBarrageResponse;
import net.polyv.vod.v1.entity.manage.barrage.VodDeleteBarrageRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListResponse;
import net.polyv.vod.v1.entity.manage.barrage.VodUploadBarrageRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodBarrageServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 视频弹幕
 * @author: fangyan
 */
@Slf4j
public class VodBarrageServiceImplTest extends BaseTest {
    
    /**
     * 测试分页查询用户下所有弹幕信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testQueryBarrageList() throws IOException, NoSuchAlgorithmException {
        VodQueryBarrageListRequest vodQueryBarrageListRequest = new VodQueryBarrageListRequest();
        VodQueryBarrageListResponse vodQueryBarrageListResponse = null;
        try {
            vodQueryBarrageListRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryBarrageListResponse = new VodBarrageServiceImpl().queryBarrageList(vodQueryBarrageListRequest);
            Assert.assertNotNull(vodQueryBarrageListResponse);
            if (vodQueryBarrageListResponse != null) {
                log.debug("测试分页查询用户下所有弹幕信息成功,{}", JSON.toJSONString(vodQueryBarrageListResponse));
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
     * 测试上传点播弹幕文件接口
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUploadBarrage() throws IOException, NoSuchAlgorithmException {
        VodUploadBarrageRequest vodUploadBarrageRequest = new VodUploadBarrageRequest();
        Boolean vodUploadBarrageResponse = null;
        try {
            String srtCN = getClass().getResource("/subtitle/srt(zh_CN).srt").getPath();
            vodUploadBarrageRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setFile(new File(srtCN))
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadBarrageResponse = new VodBarrageServiceImpl().uploadBarrage(vodUploadBarrageRequest);
            Assert.assertTrue(vodUploadBarrageResponse);
            if (vodUploadBarrageResponse) {
                log.debug("测试上传点播弹幕文件接口成功");
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
     * 测试创建视频弹幕接口
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateBarrage() throws IOException, NoSuchAlgorithmException {
        VodCreateBarrageRequest vodCreateBarrageRequest = new VodCreateBarrageRequest();
        VodCreateBarrageResponse vodCreateBarrageResponse = null;
        try {
            vodCreateBarrageRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setMsg("测试弹幕消息")
                    .setTime("00:00:08")
                    .setSessionId("88888888")
                    .setParam2("777777777")
                    .setFontSize(18)
                    .setFontMode("roll")
                    .setFontColor("0xFFFFFF")
                    .setRequestId(VodSignUtil.generateUUID());
            vodCreateBarrageResponse = new VodBarrageServiceImpl().createBarrage(vodCreateBarrageRequest);
            Assert.assertNotNull(vodCreateBarrageResponse);
            if (vodCreateBarrageResponse != null) {
                log.debug("测试创建视频弹幕接口成功，{}", JSON.toJSONString(vodCreateBarrageResponse));
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
     * 测试批量删除弹幕信息
     * 返回：true为批量删除弹幕成功，false为批量删除弹幕失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteBarrage() throws IOException, NoSuchAlgorithmException {
        VodDeleteBarrageRequest vodDeleteBarrageRequest = new VodDeleteBarrageRequest();
        Boolean vodDeleteBarrageResponse = null;
        try {
            //准备测试数据
            String barrageIds = super.getBarrageIdsByCreate();
            
            vodDeleteBarrageRequest.setBarrageIds(barrageIds).setRequestId(VodSignUtil.generateUUID());
            vodDeleteBarrageResponse = new VodBarrageServiceImpl().deleteBarrage(vodDeleteBarrageRequest);
            Assert.assertTrue(vodDeleteBarrageResponse);
            if (vodDeleteBarrageResponse) {
                log.debug("测试批量删除弹幕信息成功");
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
