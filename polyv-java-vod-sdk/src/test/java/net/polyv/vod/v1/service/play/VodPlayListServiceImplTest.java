package net.polyv.vod.v1.service.play;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.play.list.VodGetOnePlayListRequest;
import net.polyv.vod.v1.entity.play.list.VodGetOnePlayListResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.play.impl.VodPlayListServiceImpl;

/**
 * 播放列表
 * @author: fangyan
 */
@Slf4j
public class VodPlayListServiceImplTest extends BaseTest {
    /**
     * 测试查询单个播放列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetOnePlayList() throws IOException, NoSuchAlgorithmException {
        VodGetOnePlayListRequest vodGetOnePlayListRequest = new VodGetOnePlayListRequest();
        List<VodGetOnePlayListResponse> vodGetOnePlayListResponseList = null;
        try {
            vodGetOnePlayListRequest.setId("1616396785347");
            vodGetOnePlayListResponseList = new VodPlayListServiceImpl().getOnePlayList(vodGetOnePlayListRequest);
            Assert.assertNotNull(vodGetOnePlayListResponseList);
            if (vodGetOnePlayListResponseList != null) {
                log.debug("测试查询单个播放列表成功,{}", JSON.toJSONString(vodGetOnePlayListResponseList));
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
