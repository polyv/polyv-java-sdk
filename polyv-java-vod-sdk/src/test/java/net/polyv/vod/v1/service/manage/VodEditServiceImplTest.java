package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.category.VodMoveVideoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoListRequest;
import net.polyv.vod.v1.entity.manage.edit.VodRecoverDelListRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSetVideoForbiddenRequest;
import net.polyv.vod.v1.entity.manage.edit.VodSetVideoPreviewDurationRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoHlsLevelListRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoInfoRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoInfoResponse;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoPlayStatusRequest;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoSettingRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodCategoryServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodEditServiceImpl;

/**
 * 修改视频信息
 * @author: sadboy
 **/
@Slf4j
public class VodEditServiceImplTest extends BaseTest {
    
    /**
     * 测试修改单个视频信息
     * 描述：通过视频id等参数修改单个视频信息
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
                    .setTitle("junit合并并修改");
            vodUpdateVideoInfoResponse = new VodEditServiceImpl().updateVideoInfo(vodUpdateVideoInfoRequest);
            Assert.assertNotNull(vodUpdateVideoInfoResponse);
            if (vodUpdateVideoInfoResponse != null) {
                log.debug("测试修改单个视频信息成功，{}", JSON.toJSONString(vodUpdateVideoInfoResponse));
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
     * 测试修改视频的授权播放开关
     * 描述：通过视频id修改单个视频或多个视频的授权播放开关状态
     * 返回：true为修改成功，false为修改失败
     */
    @Test
    public void testUpdateVideoPlayStatus() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoPlayStatusRequest vodUpdateVideoPlayStatusRequest = new VodUpdateVideoPlayStatusRequest();
        Boolean vodUpdateVideoPlayStatusResponse = null;
        try {
            vodUpdateVideoPlayStatusRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3238618df117f9302327f28d6_1").setPlayAuth(1);
            vodUpdateVideoPlayStatusResponse = new VodEditServiceImpl().updateVideoPlayStatus(
                    vodUpdateVideoPlayStatusRequest);
            Assert.assertTrue(vodUpdateVideoPlayStatusResponse);
            if (vodUpdateVideoPlayStatusResponse) {
                log.debug("测试修改视频的授权播放开关成功");
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
     * 测试批量修改视频的授权方式
     * 描述：通过视频id与加密授权参数批量修改视频的授权方式
     * 返回：true为修改视频授权方式成功，false为修改授权方式失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateVideoHlsLevelList() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoHlsLevelListRequest vodUpdateVideoHlsLevelListRequest = new VodUpdateVideoHlsLevelListRequest();
        Boolean vodUpdateVideoHlsLevelListResponse = null;
        try {
            vodUpdateVideoHlsLevelListRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be323a146649ad0cc89d0faed9c_1").setHlsLevel("open");
            vodUpdateVideoHlsLevelListResponse = new VodEditServiceImpl().updateVideoHlsLevelList(
                    vodUpdateVideoHlsLevelListRequest);
            Assert.assertTrue(vodUpdateVideoHlsLevelListResponse);
            if (vodUpdateVideoHlsLevelListResponse) {
                log.debug("测试批量修改视频的授权方式成功");
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
     * 测试修改视频的播放预览时长
     * 描述：1、通过视频id修改视频的预览时长。
     * 描述：2、使用点播后台视频列表，选择视频，复制右侧预览代码即可播放预览视频
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
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1").setDuration(60);
            vodSetVideoPreviewDurationResponse = new VodEditServiceImpl().setVideoPreviewDuration(
                    vodSetVideoPreviewDurationRequest);
            Assert.assertTrue(vodSetVideoPreviewDurationResponse);
            if (vodSetVideoPreviewDurationResponse) {
                log.debug("测试修改视频的播放预览时长成功");
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
     * 测试修改视频禁播与解禁
     * 描述：通过视频id修改视频禁播与解禁状态
     * 约束：2、禁播后会将视频状态（status）设置成53，一次最多只能操作500个vid
     * 约束：3、只能修改”已发布”状态的视频为禁播状态，只能修改“已禁播”状态的视频为已发布状态
     * 约束：4、当请求中的视频包含多种状态时，只对符合条件的视频进行状态修改操作，并返回成功；若没有符合条件的vid则返回错误。
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
                    .setVideoIds("1b448be323a146649ad0cc89d0faed9c_1").setForbidden(0);
            vodSetVideoForbiddenResponse = new VodEditServiceImpl().setVideoForbidden(vodSetVideoForbiddenRequest);
            Assert.assertTrue(vodSetVideoForbiddenResponse);
            if (vodSetVideoForbiddenResponse) {
                log.debug("测试修改视频禁播与解禁成功");
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
     * 描述：通过视频id批量修改视频密码
     * 返回：true为修改成功，false为删除失败
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
                    .setTitle("junit测试");
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
     * 测试移动视频到指定分类
     * 描述：通过视频id与分类id移动视频到指定分类
     * 返回：true为修改成功，false为修改失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testMoveVideo() throws IOException, NoSuchAlgorithmException {
        VodMoveVideoRequest vodMoveVideoRequest = new VodMoveVideoRequest();
        Boolean vodMoveVideoResponse = null;
        try {
            vodMoveVideoRequest.setCategoryId("1602300731843").setVideoIds("1b448be3230a0194d959426ae005645f_1");
            vodMoveVideoResponse = new VodCategoryServiceImpl().moveVideo(vodMoveVideoRequest);
            Assert.assertTrue(vodMoveVideoResponse);
            if (vodMoveVideoResponse) {
                log.debug("测试移动视频到指定分类成功");
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
     * 测试恢复回收站视频
     * 描述：通过视频id批量恢复回收站中的视频
     * 约束：2、接口支持批量恢复，一次性最多支持恢复100个视频。
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testRecoverDelList() throws IOException, NoSuchAlgorithmException {
        VodRecoverDelListRequest vodRecoverDelListRequest = new VodRecoverDelListRequest();
        Boolean vodRecoverDelListResponse = null;
        try {
            vodRecoverDelListRequest
                    //可通过 new VodListServiceImpl().getDelList()获取
                    .setVideoIds("1b448be3232a3206fbbf59f58594d428_1,1b448be32302cab82e0189d115beedd8_1");
            vodRecoverDelListResponse = new VodEditServiceImpl().recoverDelList(vodRecoverDelListRequest);
            Assert.assertTrue(vodRecoverDelListResponse);
            if (vodRecoverDelListResponse) {
                log.debug("测试恢复回收站视频成功");
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
     * 描述：1、通过视频id批量删除视频
     * 描述：2、删除分为逻辑删除与物理删除，逻辑删除可通过恢复回收站视频接口恢复被删除的视频，而物理删除则不可恢复
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
                    .setDeleteType(1);
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
     * junit结束
     */
    
}
