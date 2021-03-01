package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListRequest;
import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListResponse;
import net.polyv.vod.v1.entity.manage.query.VodSearchVideoListRequest;
import net.polyv.vod.v1.entity.manage.query.VodSearchVideoListResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodQueryServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 查找视频
 * @author: sadboy
 **/
@Slf4j
public class VodQueryServiceImplTest extends BaseTest {
    
    /**
     * 测试根据授权播放开关状态查询视频
     * TODO 完成返回实体字段
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void queryVideoList() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoListRequest vodQueryVideoListRequest = new VodQueryVideoListRequest();
        VodQueryVideoListResponse vodQueryVideoListResponse = null;
        try {
            vodQueryVideoListRequest.setPlayAuth(1).setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoListResponse = new VodQueryServiceImpl().queryVideoList(vodQueryVideoListRequest);
            Assert.assertNotNull(vodQueryVideoListResponse);
            if (vodQueryVideoListResponse != null) {
                log.debug("测试根据授权播放开关状态查询视频成功,{}", JSON.toJSONString(vodQueryVideoListRequest));
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
     * 测试查找视频
     * 描述：按视频标题、分类、标签等条件查找视频
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void searchVideoList() throws IOException, NoSuchAlgorithmException {
        VodSearchVideoListRequest vodSearchVideoListRequest = new VodSearchVideoListRequest();
        List<VodSearchVideoListResponse> vodSearchVideoListResponse = null;
        try {
            vodSearchVideoListRequest.setCategoryId("1602300731843").setRequestId(VodSignUtil.generateUUID());
            vodSearchVideoListResponse = new VodQueryServiceImpl().searchVideoList(vodSearchVideoListRequest);
            Assert.assertNotNull(vodSearchVideoListResponse);
            if (vodSearchVideoListResponse != null) {
                log.debug("测试根据授权播放开关状态查询视频成功,{}", JSON.toJSONString(vodSearchVideoListResponse));
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
     * junit代码结束
     */
}
