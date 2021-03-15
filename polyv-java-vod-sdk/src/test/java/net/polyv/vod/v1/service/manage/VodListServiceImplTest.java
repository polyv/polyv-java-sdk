package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.list.VodGetByUploaderRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetByUploaderResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodListServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 视频列表
 * @author: fangyan
 */
@Slf4j
public class VodListServiceImplTest extends BaseTest {
    
    /**
     * 测试获取某分类下某子账号的视频列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetByUploader() throws IOException, NoSuchAlgorithmException {
        VodGetByUploaderRequest vodGetByUploaderRequest = new VodGetByUploaderRequest();
        VodGetByUploaderResponse vodGetByUploaderResponse = null;
        try {
            vodGetByUploaderRequest.setEmail("fangyan233@vip.qq.com")
                    .setOrderType(1)
                    .setCategoryId("1615536384688")
                    .setContainSubCategory(0)
                    .setPublished(0)
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetByUploaderResponse = new VodListServiceImpl().getByUploader(vodGetByUploaderRequest);
            Assert.assertNotNull(vodGetByUploaderResponse);
            if (vodGetByUploaderResponse != null) {
                log.debug("测试获取某分类下某子账号的视频列表成功，{}", JSON.toJSONString(vodGetByUploaderResponse));
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
