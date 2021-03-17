package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListResponse;
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
}
