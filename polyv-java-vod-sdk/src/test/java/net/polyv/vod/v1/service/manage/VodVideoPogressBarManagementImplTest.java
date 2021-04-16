package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoAllKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSaveVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodEditServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodInfoServiceImpl;

/**
 * 视频进度条打点
 * @author: fangyan
 */
@Slf4j
public class VodVideoPogressBarManagementImplTest extends BaseTest {
    
    /**
     * 测试设置视频打点
     * 描述：通过视频id设置视频的打点信息
     * 约束：2、请求入参seconds(打点秒数【第seconds秒】)必须要小于视频长度;
     * 约束：3、请求入参desc(打点描述)的个数必须要和seconds的个数相同。
     * 返回：true为打点成功，false为打点失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSaveVideoKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodSaveVideoKeyFrameRequest vodSaveVideoKeyFrameRequest = new VodSaveVideoKeyFrameRequest();
        Boolean vodSaveVideoKeyFrameResponse = null;
        try {
            vodSaveVideoKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setDesc("junit测试打点1,junit测试打点2,junit测试打点3")
                    .setSeconds("24,60,120")
                    .setBtnSettingSwitch("Y")
                    .setBtnDesc("保利威")
                    .setBtnHref("http://www.polyv.net");
            vodSaveVideoKeyFrameResponse = new VodEditServiceImpl().saveVideoKeyFrame(vodSaveVideoKeyFrameRequest);
            Assert.assertTrue(vodSaveVideoKeyFrameResponse);
            if (vodSaveVideoKeyFrameResponse) {
                log.debug("测试设置视频打点成功");
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
     * 测试查询单个视频的打点信息
     * 描述：通过视频id查询单个视频的打点信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListVideoKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodListVideoKeyFrameRequest vodListVideoKeyFrameRequest = new VodListVideoKeyFrameRequest();
        VodListVideoKeyFrameResponse vodListVideoKeyFrameResponse = null;
        try {
            vodListVideoKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be32343357d5c4784d9ffd1bf5c_1");
            vodListVideoKeyFrameResponse = new VodInfoServiceImpl().listVideoKeyFrame(vodListVideoKeyFrameRequest);
            Assert.assertNotNull(vodListVideoKeyFrameResponse);
            if (vodListVideoKeyFrameResponse != null) {
                log.debug("测试查询单个视频的打点信息成功,{}", JSON.toJSONString(vodListVideoKeyFrameResponse));
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
     * 测试删除视频指定时间点的打点信息
     * 描述：通过视频id与时间点删除视频指定时间点的打点信息
     * 返回：true为删除成功，false为删除失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteVideoKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoKeyFrameRequest vodDeleteVideoKeyFrameRequest = new VodDeleteVideoKeyFrameRequest();
        Boolean vodDeleteVideoKeyFrameResponse = null;
        try {
            vodDeleteVideoKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1").setTimes("24,120");
            vodDeleteVideoKeyFrameResponse = new VodEditServiceImpl().deleteVideoKeyFrame(
                    vodDeleteVideoKeyFrameRequest);
            Assert.assertTrue(vodDeleteVideoKeyFrameResponse);
            if (vodDeleteVideoKeyFrameResponse) {
                log.debug("测试删除视频指定时间点的打点信息成功");
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
     * 删除视频的全部打点信息
     * 描述：通过视频id删除视频的全部打点信息
     * 返回：true为删除全部打点信息成功，false为删除失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteVideoAllKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoAllKeyFrameRequest vodDeleteVideoAllKeyFrameRequest = new VodDeleteVideoAllKeyFrameRequest();
        Boolean vodDeleteVideoAllKeyFrameResponse = null;
        try {
            vodDeleteVideoAllKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1");
            vodDeleteVideoAllKeyFrameResponse = new VodEditServiceImpl().deleteVideoAllKeyFrame(
                    vodDeleteVideoAllKeyFrameRequest);
            Assert.assertTrue(vodDeleteVideoAllKeyFrameResponse);
            if (vodDeleteVideoAllKeyFrameResponse) {
                log.debug("测试删除视频的全部打点信息成功");
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
