package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoFirstImageRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoPlayStatusRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoPreviewDurationRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoSizeRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoSizeResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideosPlayTimesRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideosPlayTimesResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideosSizeRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideosSizeResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetWeChatShareVideoInfoRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetWeChatShareVideoInfoResponse;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameResponse;
import net.polyv.vod.v1.entity.manage.info.VodQueryVideoPasswordRequest;
import net.polyv.vod.v1.entity.manage.info.VodQueryVideoPasswordResponse;
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
                log.debug("测试获取单个视频的打点信息成功,{}", JSON.toJSONString(vodListVideoKeyFrameResponse));
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
            Assert.assertTrue(vodGetVideoPlayStatusResponse);
            if (vodGetVideoPlayStatusResponse) {
                log.debug("测试根据视频vid查询视频的授权播放开关状态成功");
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
     * 测试批量获取答题日志
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetVideoExamLog() throws IOException, NoSuchAlgorithmException {
        VodGetVideoExamLogRequest vodGetVideoExamLogRequest = new VodGetVideoExamLogRequest();
        VodGetVideoExamLogResponse vodGetVideoExamLogResponse = null;
        try {
            vodGetVideoExamLogRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3230a0194d959426ae005645f_1")
                    .setStart(super.getDate(2021, 2, 1))
                    .setEnd(super.getDate(2021, 3, 12))
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideoExamLogResponse = new VodInfoServiceImpl().getVideoExamLog(vodGetVideoExamLogRequest);
            Assert.assertNotNull(vodGetVideoExamLogResponse);
            if (vodGetVideoExamLogResponse != null) {
                log.debug("测试批量获取答题日志成功,{}", JSON.toJSONString(vodGetVideoExamLogResponse));
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
     * 测试根据分类批量获取视频时长和大小
     * 约束：当传了videoIds时，按照videoIds查询；当仅传categoryIds时，按照categoryIds查询；videoIds和categoryIds不能同时为空；同时传以videoIds为准
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoSize() throws IOException, NoSuchAlgorithmException {
        VodGetVideoSizeRequest vodGetVideoSizeRequest = new VodGetVideoSizeRequest();
        List<VodGetVideoSizeResponse> vodGetVideoSizeResponseList = null;
        try {
            vodGetVideoSizeRequest.setVideoIds("1b448be323a146649ad0cc89d0faed9c_1")
                    .setCategoryIds("1602300731843")
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideoSizeResponseList = new VodInfoServiceImpl().getVideoSize(vodGetVideoSizeRequest);
            Assert.assertNotNull(vodGetVideoSizeResponseList);
            if (vodGetVideoSizeResponseList != null) {
                log.debug("测试根据分类批量获取视频时长和大小成功,{}", JSON.toJSONString(vodGetVideoSizeResponseList));
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
     * 测试获取微信分享页的视频相关信息接口
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetWeChatShareVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodGetWeChatShareVideoInfoRequest vodGetWeChatShareVideoInfoRequest = new VodGetWeChatShareVideoInfoRequest();
        VodGetWeChatShareVideoInfoResponse vodGetWeChatShareVideoInfoResponse = null;
        try {
            vodGetWeChatShareVideoInfoRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetWeChatShareVideoInfoResponse = new VodInfoServiceImpl().getWeChatShareVideoInfo(
                    vodGetWeChatShareVideoInfoRequest);
            Assert.assertNotNull(vodGetWeChatShareVideoInfoResponse);
            if (vodGetWeChatShareVideoInfoResponse != null) {
                log.debug("测试获取微信分享页的视频相关信息接口成功,{}", JSON.toJSONString(vodGetWeChatShareVideoInfoResponse));
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
     * 测试获取视频播放预览时长接口
     * 返回：视频播放预览时长，单位：秒
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoPreviewDuration() throws IOException, NoSuchAlgorithmException {
        VodGetVideoPreviewDurationRequest vodGetVideoPreviewDurationRequest = new VodGetVideoPreviewDurationRequest();
        Integer vodGetVideoPreviewDurationResponse = null;
        try {
            vodGetVideoPreviewDurationRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideoPreviewDurationResponse = new VodInfoServiceImpl().getVideoPreviewDuration(
                    vodGetVideoPreviewDurationRequest);
            Assert.assertNotNull(vodGetVideoPreviewDurationResponse);
            if (vodGetVideoPreviewDurationResponse != null) {
                log.debug("测试获取视频播放预览时长接口成功,{}", JSON.toJSONString(vodGetVideoPreviewDurationResponse));
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
     * 测试获取单个视频信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideo() throws IOException, NoSuchAlgorithmException {
        VodGetVideoRequest vodGetVideoRequest = new VodGetVideoRequest();
        VodGetVideoResponse vodGetVideoResponse = null;
        try {
            vodGetVideoRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideoResponse = new VodInfoServiceImpl().getVideo(vodGetVideoRequest);
            Assert.assertNotNull(vodGetVideoResponse);
            if (vodGetVideoResponse != null) {
                log.debug("测试获取单个视频信息成功,{}", JSON.toJSONString(vodGetVideoResponse));
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
     * 测试获取单个视频的首图
     * 返回：首图地址
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoFirstImage() throws IOException, NoSuchAlgorithmException {
        VodGetVideoFirstImageRequest vodGetVideoFirstImageRequest = new VodGetVideoFirstImageRequest();
        String vodGetVideoFirstImageResponse = null;
        try {
            vodGetVideoFirstImageRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setThumbnail(1)
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideoFirstImageResponse = new VodInfoServiceImpl().getVideoFirstImage(vodGetVideoFirstImageRequest);
            Assert.assertNotNull(vodGetVideoFirstImageResponse);
            if (vodGetVideoFirstImageResponse != null) {
                log.debug("测试获取单个视频的首图成功,{}", vodGetVideoFirstImageResponse);
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
     * 测试获取单个视频的问答题目
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoExam() throws IOException, NoSuchAlgorithmException {
        VodGetVideoExamRequest vodGetVideoExamRequest = new VodGetVideoExamRequest();
        List<VodGetVideoExamResponse> vodGetVideoExamResponseList = null;
        try {
            vodGetVideoExamRequest.setVideoId("1b448be3230a0194d959426ae005645f_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideoExamResponseList = new VodInfoServiceImpl().getVideoExam(vodGetVideoExamRequest);
            Assert.assertNotNull(vodGetVideoExamResponseList);
            if (vodGetVideoExamResponseList != null) {
                log.debug("测试获取单个视频的问答题目成功,{}", JSON.toJSONString(vodGetVideoExamResponseList));
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
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryVideoPassword() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPasswordRequest vodQueryVideoPasswordRequest = new VodQueryVideoPasswordRequest();
        VodQueryVideoPasswordResponse vodQueryVideoPasswordResponse = null;
        try {
            vodQueryVideoPasswordRequest.setVideoId("1b448be3234134f5a73bdddd6e88a9a5_1")
                    .setRequestId(VodSignUtil.generateUUID());
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
     * 批量获取视频的时长和大小
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideosSize() throws IOException, NoSuchAlgorithmException {
        VodGetVideosSizeRequest vodGetVideosSizeRequest = new VodGetVideosSizeRequest();
        List<VodGetVideosSizeResponse> vodGetVideosSizeResponseList = null;
        try {
            vodGetVideosSizeRequest.setVideoIds("1b448be323a146649ad0cc89d0faed9c_1,1b448be32389b93ea8be08bf0d257043_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideosSizeResponseList = new VodInfoServiceImpl().getVideosSize(vodGetVideosSizeRequest);
            Assert.assertNotNull(vodGetVideosSizeResponseList);
            if (vodGetVideosSizeResponseList != null) {
                log.debug("测试批量获取视频的时长和大小成功,{}", JSON.toJSONString(vodGetVideosSizeResponseList));
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
     * 批量获取视频播放次数
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideosPlayTimes() throws IOException, NoSuchAlgorithmException {
        VodGetVideosPlayTimesRequest vodGetVideosPlayTimesRequest = new VodGetVideosPlayTimesRequest();
        List<VodGetVideosPlayTimesResponse> vodGetVideosPlayTimesResponseList = null;
        try {
            vodGetVideosPlayTimesRequest.setVideoIds(
                    "1b448be3230a0194d959426ae005645f_1,1b448be323a146649ad0cc89d0faed9c_1")
                    .setRealTime(0)
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideosPlayTimesResponseList = new VodInfoServiceImpl().getVideosPlayTimes(
                    vodGetVideosPlayTimesRequest);
            Assert.assertNotNull(vodGetVideosPlayTimesResponseList);
            if (vodGetVideosPlayTimesResponseList != null) {
                log.debug("测试批量获取视频播放次数成功,{}", JSON.toJSONString(vodGetVideosPlayTimesResponseList));
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
