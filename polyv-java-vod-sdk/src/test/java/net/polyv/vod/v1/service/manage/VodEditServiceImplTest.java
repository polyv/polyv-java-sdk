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
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoListRequest;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSaveVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSetVideoForbiddenRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSetVideoPreviewDurationRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoInfoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoInfoResponse;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoPlayStatusRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoSettingRequest;
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
     * 测试删除视频指定时间点的打点信息
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
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setTimes("24,120")
                    .setRequestId(VodSignUtil.generateUUID());
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
     * 测试设置视频的播放预览时长
     * 描述：设置视频预览时长，使用点播后台视频列表，选择视频，复制右侧预览代码即可播放预览视频
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetVideoPreviewDuration() throws IOException, NoSuchAlgorithmException {
        VodSetVideoPreviewDurationRequest vodSetVideoPreviewDurationRequest = new VodSetVideoPreviewDurationRequest();
        Boolean vodSetVideoPreviewDurationResponse = null;
        try {
            vodSetVideoPreviewDurationRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setDuration(60)
                    .setRequestId(VodSignUtil.generateUUID());
            vodSetVideoPreviewDurationResponse = new VodEditServiceImpl().setVideoPreviewDuration(
                    vodSetVideoPreviewDurationRequest);
            Assert.assertTrue(vodSetVideoPreviewDurationResponse);
            if (vodSetVideoPreviewDurationResponse) {
                log.debug("测试设置视频的播放预览时长成功");
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
     * 测试视频禁播与解禁
     * 约束：2、禁播后会将视频状态（status）设置成53，一次最多只能操作500个vid
     * 约束：3、只能修改“已发布”状态的视频为禁播状态，只能修改“已禁播”状态的视频为已发布状态
     * 约束：4、当请求中的vid包含多种状态时，只对符合条件的vid进行状态修改操作，并返回成功；若没有符合条件的vid则返回错误。
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testSetVideoForbidden() throws IOException, NoSuchAlgorithmException {
        VodSetVideoForbiddenRequest vodSetVideoForbiddenRequest = new VodSetVideoForbiddenRequest();
        Boolean vodSetVideoForbiddenResponse = null;
        try {
            vodSetVideoForbiddenRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be323a146649ad0cc89d0faed9c_1")
                    .setForbidden(0)
                    .setRequestId(VodSignUtil.generateUUID());
            vodSetVideoForbiddenResponse = new VodEditServiceImpl().setVideoForbidden(vodSetVideoForbiddenRequest);
            Assert.assertTrue(vodSetVideoForbiddenResponse);
            if (vodSetVideoForbiddenResponse) {
                log.debug("测试设置视频的播放预览时长成功");
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
     * 测试批量删除视频
     * 约束：2、视频id一次最多提交500个；
     * 返回：true为批量删除成功，false为批量删除失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteVideoList() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoListRequest vodDeleteVideolistRequest = new VodDeleteVideoListRequest();
        Boolean vodDeleteVideoListResponse = null;
        try {
            vodDeleteVideolistRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3238ae0aa1020ac2807c9e8c9_1,1b448be323c12aa5e048c3fb5e10ca99_1")
                    .setDeleteType(1)
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteVideoListResponse = new VodEditServiceImpl().deleteVideoList(vodDeleteVideolistRequest);
            Assert.assertTrue(vodDeleteVideoListResponse);
            if (vodDeleteVideoListResponse) {
                log.debug("测试批量删除视频成功");
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
     * 测试编辑单个视频的信息
     * TODO 确认返回值是对象还是数组问题
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoInfoRequest vodUpdateVideoInfoRequest = new VodUpdateVideoInfoRequest();
        VodUpdateVideoInfoResponse vodUpdateVideoInfoResponse = null;
        try {
            vodUpdateVideoInfoRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setDesc("这是一个通过junit合并的视频")
                    .setTag("junit测试")
                    .setTitle("junit合并并修改")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoInfoResponse = new VodEditServiceImpl().updateVideoInfo(vodUpdateVideoInfoRequest);
            Assert.assertNotNull(vodUpdateVideoInfoResponse);
            if (vodUpdateVideoInfoResponse != null) {
                log.debug("测试编辑单个视频的信息成功，{}", vodUpdateVideoInfoResponse);
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
     * 测试删除视频
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteVideo() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoRequest vodDeleteVideoRequest = new VodDeleteVideoRequest();
        Boolean vodDeleteVideoResponse = null;
        try {
            vodDeleteVideoRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be3238ae0aa1020ac2807c9e8c9_1")
                    .setDeleteType(1)
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteVideoResponse = new VodEditServiceImpl().deleteVideo(vodDeleteVideoRequest);
            Assert.assertTrue(vodDeleteVideoResponse);
            if (vodDeleteVideoResponse) {
                log.debug("测试编辑单个视频的信息成功");
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
     * 测试修改视频密码
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateVideoSetting() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoSettingRequest vodUpdateVideoSettingRequest = new VodUpdateVideoSettingRequest();
        Boolean vodUpdateVideoSettingResponse = null;
        try {
            vodUpdateVideoSettingRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be32355403dad586f7468e63e23_1,1b448be323a9076c9941604ac1c667f9_1")
                    .setPassword(super.getRandomString(10))
                    .setPublishUrl(null)
                    .setTag("junit")
                    .setTitle("junit测试")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoSettingResponse = new VodEditServiceImpl().updateVideoSetting(vodUpdateVideoSettingRequest);
            Assert.assertTrue(vodUpdateVideoSettingResponse);
            if (vodUpdateVideoSettingResponse) {
                log.debug("测试修改视频密码成功");
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
