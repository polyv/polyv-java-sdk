package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoFirstImageRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoPlayStatusRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoPreviewDurationRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoSizeRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoSizeResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideosPlayTimesRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideosPlayTimesResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetWeChatShareVideoInfoRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetWeChatShareVideoInfoResponse;
import net.polyv.vod.v1.entity.manage.info.VodQueryVideoPasswordRequest;
import net.polyv.vod.v1.entity.manage.info.VodQueryVideoPasswordResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodInfoServiceImpl;

/**
 * 查询单个视频信息
 * @author: sadboy
 **/
@Slf4j
public class VodInfoServiceImplTest extends BaseTest {
    
    /**
     * 测试查询单个视频信息
     * 描述：通过视频id查询单个视频的信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideo() throws IOException, NoSuchAlgorithmException {
        VodGetVideoRequest vodGetVideoRequest = new VodGetVideoRequest();
        VodGetVideoResponse vodGetVideoResponse = null;
        try {
            vodGetVideoRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1");
            vodGetVideoResponse = new VodInfoServiceImpl().getVideo(vodGetVideoRequest);
            Assert.assertNotNull(vodGetVideoResponse);
            if (vodGetVideoResponse != null) {
                log.debug("测试查询单个视频信息成功,{}", JSON.toJSONString(vodGetVideoResponse));
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
     * 测试查询视频授权播放开关
     * 描述：通过视频id查询视频授权播放开关状态
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
                    .setVideoId("1b448be32302cab82e0189d115beedd8_1");
            vodGetVideoPlayStatusResponse = new VodInfoServiceImpl().getVideoPlayStatus(vodGetVideoPlayStatusRequest);
            Assert.assertTrue(vodGetVideoPlayStatusResponse);
            if (vodGetVideoPlayStatusResponse) {
                log.debug("测试查询视频授权播放开关成功");
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
     * 测试查询视频时长和大小
     * 描述：通过视频id或分类id查询视频的时长和大小
     * 约束：2、当传了videoIds时，按照videoIds查询；当仅传categoryIds时，按照categoryIds查询；videoIds和categoryIds不能同时为空；同时传以videoIds为准
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoSize() throws IOException, NoSuchAlgorithmException {
        VodGetVideoSizeRequest vodGetVideoSizeRequest = new VodGetVideoSizeRequest();
        List<VodGetVideoSizeResponse> vodGetVideoSizeResponseList = null;
        try {
            vodGetVideoSizeRequest.setVideoIds("1b448be323a146649ad0cc89d0faed9c_1").setCategoryIds("1602300731843");
            vodGetVideoSizeResponseList = new VodInfoServiceImpl().getVideoSize(vodGetVideoSizeRequest);
            Assert.assertNotNull(vodGetVideoSizeResponseList);
            if (vodGetVideoSizeResponseList != null) {
                log.debug("测试根据分类批量查询视频时长和大小成功,{}", JSON.toJSONString(vodGetVideoSizeResponseList));
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
     * 测试查询微信分享页的视频信息
     * 描述：通过视频id查询微信分享页的视频信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetWeChatShareVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodGetWeChatShareVideoInfoRequest vodGetWeChatShareVideoInfoRequest = new VodGetWeChatShareVideoInfoRequest();
        VodGetWeChatShareVideoInfoResponse vodGetWeChatShareVideoInfoResponse = null;
        try {
            vodGetWeChatShareVideoInfoRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1");
            vodGetWeChatShareVideoInfoResponse = new VodInfoServiceImpl().getWeChatShareVideoInfo(
                    vodGetWeChatShareVideoInfoRequest);
            Assert.assertNotNull(vodGetWeChatShareVideoInfoResponse);
            if (vodGetWeChatShareVideoInfoResponse != null) {
                log.debug("测试查询微信分享页的视频相关信息成功,{}", JSON.toJSONString(vodGetWeChatShareVideoInfoResponse));
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
     * 测试查询视频播放预览时长
     * 描述：通过视频id查询视频播放预览时长
     * 返回：视频播放预览时长，单位：秒
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoPreviewDuration() throws IOException, NoSuchAlgorithmException {
        VodGetVideoPreviewDurationRequest vodGetVideoPreviewDurationRequest = new VodGetVideoPreviewDurationRequest();
        Integer vodGetVideoPreviewDurationResponse = null;
        try {
            vodGetVideoPreviewDurationRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1");
            vodGetVideoPreviewDurationResponse = new VodInfoServiceImpl().getVideoPreviewDuration(
                    vodGetVideoPreviewDurationRequest);
            Assert.assertNotNull(vodGetVideoPreviewDurationResponse);
            if (vodGetVideoPreviewDurationResponse != null) {
                log.debug("测试查询视频播放预览时长成功,{}", JSON.toJSONString(vodGetVideoPreviewDurationResponse));
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
     * 测试查询单个视频的首图
     * 描述：通过视频id查询单个视频的首图
     * 返回：首图地址
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoFirstImage() throws IOException, NoSuchAlgorithmException {
        VodGetVideoFirstImageRequest vodGetVideoFirstImageRequest = new VodGetVideoFirstImageRequest();
        String vodGetVideoFirstImageResponse = null;
        try {
            vodGetVideoFirstImageRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1").setThumbnail(1);
            vodGetVideoFirstImageResponse = new VodInfoServiceImpl().getVideoFirstImage(vodGetVideoFirstImageRequest);
            Assert.assertNotNull(vodGetVideoFirstImageResponse);
            if (vodGetVideoFirstImageResponse != null) {
                log.debug("测试查询单个视频的首图成功,{}", vodGetVideoFirstImageResponse);
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
     * 测试查询视频密码
     * 描述：通过视频id查询视频密码
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoPassword() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPasswordRequest vodQueryVideoPasswordRequest = new VodQueryVideoPasswordRequest();
        VodQueryVideoPasswordResponse vodQueryVideoPasswordResponse = null;
        try {
            vodQueryVideoPasswordRequest.setVideoId("1b448be3234134f5a73bdddd6e88a9a5_1");
            vodQueryVideoPasswordResponse = new VodInfoServiceImpl().queryVideoPassword(vodQueryVideoPasswordRequest);
            Assert.assertNotNull(vodQueryVideoPasswordResponse);
            if (vodQueryVideoPasswordResponse != null) {
                log.debug("测试查询视频密码成功,{}", JSON.toJSONString(vodQueryVideoPasswordResponse));
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
     * 批量查询视频播放次数
     * 描述：通过视频id批量查询视频播放次数
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideosPlayTimes() throws IOException, NoSuchAlgorithmException {
        VodGetVideosPlayTimesRequest vodGetVideosPlayTimesRequest = new VodGetVideosPlayTimesRequest();
        List<VodGetVideosPlayTimesResponse> vodGetVideosPlayTimesResponseList = null;
        try {
            vodGetVideosPlayTimesRequest.setVideoIds(
                    "1b448be3230a0194d959426ae005645f_1,1b448be323a146649ad0cc89d0faed9c_1").setRealTime(0);
            vodGetVideosPlayTimesResponseList = new VodInfoServiceImpl().getVideosPlayTimes(
                    vodGetVideosPlayTimesRequest);
            Assert.assertNotNull(vodGetVideosPlayTimesResponseList);
            if (vodGetVideosPlayTimesResponseList != null) {
                log.debug("测试批量查询视频播放次数成功,{}", JSON.toJSONString(vodGetVideosPlayTimesResponseList));
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
     * 测试用例结束
     */
}
