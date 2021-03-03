package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoPlayStatusRequest;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodInfoServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 视频信息
 * @author: sadboy
 **/
@Slf4j
public class VodInfoServiceImplTest extends BaseTest {
    
    /**
     * 测试获取单个视频的打点信息
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
                    .setVideoId("1b448be32343357d5c4784d9ffd1bf5c_1").setRequestId(VodSignUtil.generateUUID());
            vodListVideoKeyFrameResponse = new VodInfoServiceImpl().listVideoKeyFrame(vodListVideoKeyFrameRequest);
            Assert.assertNotNull(vodListVideoKeyFrameResponse);
            if (vodListVideoKeyFrameResponse != null) {
                log.debug("测试获取单个视频的打点信息成功,{}", vodListVideoKeyFrameResponse);
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
     * 测试根据视频vid查询视频的授权播放开关状态
     * 返回：true为开关开启，false为开关关闭
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetVideoPlayStatus() throws IOException, NoSuchAlgorithmException {
        VodGetVideoPlayStatusRequest vodGetVideoPlayStatusRequest = new VodGetVideoPlayStatusRequest();
        Boolean vodGetVideoPlayStatusResponse = null;
        try {
            vodGetVideoPlayStatusRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be32343357d5c4784d9ffd1bf5c_1").setRequestId(VodSignUtil.generateUUID());
            vodGetVideoPlayStatusResponse = new VodInfoServiceImpl().getVideoPlayStatus(vodGetVideoPlayStatusRequest);
            Assert.assertNotNull(vodGetVideoPlayStatusResponse);
            if (vodGetVideoPlayStatusResponse != null) {
                log.debug("测试根据视频vid查询视频的授权播放开关状态成功,{}", vodGetVideoPlayStatusResponse);
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
