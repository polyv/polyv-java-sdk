package net.polyv.vod.v1.service.play;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListRequest;
import net.polyv.vod.v1.entity.play.payersettings.VodGetPlayerListResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.play.impl.VodPlayerSettingsServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 播放器设置
 * @author: fangyan
 */
@Slf4j
public class VodPlayerSettingsServiceImplTest extends BaseTest {
    /**
     * 测试获取用户下所有播放器列表接口
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetPlayerList() throws IOException, NoSuchAlgorithmException {
        VodGetPlayerListRequest vodGetPlayerListRequest = new VodGetPlayerListRequest();
        List<VodGetPlayerListResponse> vodGetPlayerListResponseList = null;
        try {
            vodGetPlayerListRequest.setRequestId(VodSignUtil.generateUUID());
            vodGetPlayerListResponseList = new VodPlayerSettingsServiceImpl().getPlayerList(vodGetPlayerListRequest);
            Assert.assertNotNull(vodGetPlayerListResponseList);
            if (vodGetPlayerListResponseList != null) {
                log.debug("测试获取用户下所有播放器列表接口成功,{}", JSON.toJSONString(vodGetPlayerListResponseList));
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
