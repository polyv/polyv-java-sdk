package net.polyv.vod.v1.service.play;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlaySafeTokenRequest;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlaySafeTokenResponse;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListRequest;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.play.impl.VodPlayerSettingsServiceImpl;

/**
 * 播放器设置
 * @author: fangyan
 */
@Slf4j
public class VodPlayerSettingsServiceImplTest extends BaseTest {
    /**
     * 测试查询用户下所有播放器列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetPlayerList() throws IOException, NoSuchAlgorithmException {
        VodGetPlayerListRequest vodGetPlayerListRequest = new VodGetPlayerListRequest();
        List<VodGetPlayerListResponse> vodGetPlayerListResponseList = null;
        try {
            vodGetPlayerListResponseList = new VodPlayerSettingsServiceImpl().getPlayerList(vodGetPlayerListRequest);
            Assert.assertNotNull(vodGetPlayerListResponseList);
            if (vodGetPlayerListResponseList != null) {
                log.debug("测试查询用户下所有播放器列表成功,{}", JSON.toJSONString(vodGetPlayerListResponseList));
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
     * 测试获取Playsafe Token
     * 描述：通过用户id与视频id获取播放凭证，用于播放加密视频
     * 约束：2、如果一个token（播放凭证）尚未过期，此时使用相同的 videoId、viewerId、viewerIp、isWxa 参数值请求该接口，则会复用原来的token，并延长原token的有效期。
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetPlaySafeToken() throws IOException, NoSuchAlgorithmException {
        VodGetPlaySafeTokenRequest vodGetPlaySafeTokenRequest = new VodGetPlaySafeTokenRequest();
        VodGetPlaySafeTokenResponse vodGetPlaySafeTokenResponse = null;
        try {
            vodGetPlaySafeTokenRequest.setVideoId("1b448be323b68b2999802799a98dba54_1")
                    .setViewerId("testViewerId")
                    .setViewerIp("192.168.0.8")
                    .setViewerName("TestViewName")
                    .setExpires(Long.parseLong("60"))
                    .setDisposable(Boolean.TRUE)
                    .setIsWxa(0);
            vodGetPlaySafeTokenResponse = new VodPlayerSettingsServiceImpl().getPlaySafeToken(
                    vodGetPlaySafeTokenRequest);
            Assert.assertNotNull(vodGetPlaySafeTokenResponse);
            if (vodGetPlaySafeTokenResponse != null) {
                log.debug("测试获取Playsafe Token成功,{}", JSON.toJSONString(vodGetPlaySafeTokenResponse));
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
