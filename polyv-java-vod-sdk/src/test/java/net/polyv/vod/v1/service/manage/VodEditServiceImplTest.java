package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.edit.VodClipVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodConcatVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodConcatVideoResponse;
import net.polyv.vod.v1.entity.manage.edit.VodSaveVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoPlayStatusRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodEditServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 编辑视频
 * @author: sadboy
 **/
@Slf4j
public class VodEditServiceImplTest extends BaseTest {
    
    /**
     * 测试根据vid批量修改视频的授权播放开关状态
     * 描述：根据vid设置单个视频/多个视频的授权播放开关状态
     * 返回：true为修改成功，false为修改失败
     */
    @Test
    public void testUpdateVideoPlayStatus() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoPlayStatusRequest vodUpdateVideoPlayStatusRequest = new VodUpdateVideoPlayStatusRequest();
        Boolean vodUpdateVideoPlayStatusResponse = null;
        try {
            vodUpdateVideoPlayStatusRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3238618df117f9302327f28d6_1")
                    .setPlayAuth(1)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoPlayStatusResponse = new VodEditServiceImpl().updateVideoPlayStatus(
                    vodUpdateVideoPlayStatusRequest);
            Assert.assertTrue(vodUpdateVideoPlayStatusResponse);
            if (vodUpdateVideoPlayStatusResponse) {
                log.debug("测试根据vid批量修改视频的授权播放开关状态成功");
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
     * 测试提交视频裁剪任务
     * 返回：接口请求成功会返回裁剪后新视频的vid
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testClipVideo() throws IOException, NoSuchAlgorithmException {
        VodClipVideoRequest vodClipVideoRequest = new VodClipVideoRequest();
        String vodClipVideoResponse = null;
        try {
            vodClipVideoRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be3238618df117f9302327f28d6_1")
                    .setTitle("junit裁剪")
                    .setTimeFrame("[{\"start\":1,\"end\":6}]")
                    .setRequestId(VodSignUtil.generateUUID());
            vodClipVideoResponse = new VodEditServiceImpl().clipVideo(vodClipVideoRequest);
            Assert.assertNotNull(vodClipVideoResponse);
            if (vodClipVideoResponse != null) {
                log.debug("测试提交视频裁剪任务成功,{}", vodClipVideoResponse);
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
     * 测试合并视频
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testConcatVideo() throws IOException, NoSuchAlgorithmException {
        VodConcatVideoRequest vodConcatVideoRequest = new VodConcatVideoRequest();
        VodConcatVideoResponse vodConcatVideoResponse = null;
        try {
            vodConcatVideoRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3238618df117f9302327f28d6_1,1b448be3234134f5a73bdddd6e88a9a5_1")
                    .setTitle("junit合并")
                    .setCategoryId("1602300731843")
                    .setScreenCap(1)
                    .setRequestId(VodSignUtil.generateUUID());
            vodConcatVideoResponse = new VodEditServiceImpl().concatVideo(vodConcatVideoRequest);
            Assert.assertNotNull(vodConcatVideoResponse);
            if (vodConcatVideoResponse != null) {
                log.debug("测试合并视频成功,{}", vodConcatVideoResponse);
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
     * 测试设置视频打点
     * 约束：2、seconds(打点秒数【第seconds秒】)必须要小于视频长度;
     * 约束：3、desc(打点描述)的个数必须要和seconds的个数相同。
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testSaveVideoKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodSaveVideoKeyFrameRequest vodSaveVideoKeyFrameRequest = new VodSaveVideoKeyFrameRequest();
        Boolean vodSaveVideoKeyFrameResponse = null;
        try {
            vodSaveVideoKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setDesc("junit测试打点1,junit测试打点2")
                    .setSeconds("24,60")
                    .setBtnSettingSwitch("Y")
                    .setBtnDesc("这是打点设置的开关")
                    .setBtnHref("http://www.polyv.net")
                    .setRequestId(VodSignUtil.generateUUID());
            vodSaveVideoKeyFrameResponse = new VodEditServiceImpl().saveVideoKeyFrame(vodSaveVideoKeyFrameRequest);
            Assert.assertTrue(vodSaveVideoKeyFrameResponse);
            if (vodSaveVideoKeyFrameResponse) {
                log.debug("测试合并视频成功");
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
     * junit结束
     */
    
}
