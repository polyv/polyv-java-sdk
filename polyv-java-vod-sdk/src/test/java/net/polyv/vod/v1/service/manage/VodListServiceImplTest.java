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
import net.polyv.vod.v1.entity.manage.list.VodGetDelListRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetDelListResponse;
import net.polyv.vod.v1.entity.manage.list.VodGetHotListRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetHotListResponse;
import net.polyv.vod.v1.entity.manage.list.VodGetIllegalListRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetIllegalListResponse;
import net.polyv.vod.v1.entity.manage.list.VodGetNewListRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetNewListResponse;
import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListRequest;
import net.polyv.vod.v1.entity.manage.query.VodQueryVideoListResponse;
import net.polyv.vod.v1.entity.manage.query.VodSearchVideoListRequest;
import net.polyv.vod.v1.entity.manage.query.VodSearchVideoListResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodListServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodQueryServiceImpl;

/**
 * 查询视频列表
 * @author: fangyan
 */
@Slf4j
public class VodListServiceImplTest extends BaseTest {
    
    /**
     * 测试查询视频列表
     * 描述：通过视频标题、分类、标签等条件分页查询视频列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSearchVideoList() throws IOException, NoSuchAlgorithmException {
        VodSearchVideoListRequest vodSearchVideoListRequest = new VodSearchVideoListRequest();
        VodSearchVideoListResponse vodSearchVideoListResponse = null;
        try {
            vodSearchVideoListRequest.setCategoryId("1602300731843");
            vodSearchVideoListResponse = new VodQueryServiceImpl().searchVideoList(vodSearchVideoListRequest);
            Assert.assertNotNull(vodSearchVideoListResponse);
            if (vodSearchVideoListResponse != null) {
                log.debug("测试查询视频列表成功,{}", JSON.toJSONString(vodSearchVideoListResponse));
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
     * 测试通过授权播放查询视频列表
     * 描述：通过授权播放开关状态查询视频列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testQueryVideoList() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoListRequest vodQueryVideoListRequest = new VodQueryVideoListRequest();
        VodQueryVideoListResponse vodQueryVideoListResponse = null;
        try {
            vodQueryVideoListRequest.setPlayAuth(1);
            vodQueryVideoListResponse = new VodQueryServiceImpl().queryVideoList(vodQueryVideoListRequest);
            Assert.assertNotNull(vodQueryVideoListResponse);
            if (vodQueryVideoListResponse != null) {
                log.debug("测试通过授权播放查询视频列表成功,{}", JSON.toJSONString(vodQueryVideoListResponse));
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
     * 测试查询子账号的视频列表
     * 描述：通过子账号邮箱查询子账号的视频列表
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
                    .setPublished(0);
            vodGetByUploaderResponse = new VodListServiceImpl().getByUploader(vodGetByUploaderRequest);
            Assert.assertNotNull(vodGetByUploaderResponse);
            if (vodGetByUploaderResponse != null) {
                log.debug("测试查询子账号的视频列表成功，{}", JSON.toJSONString(vodGetByUploaderResponse));
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
     * 测试查询最新视频/全部视频列表
     * 描述：1、通过时间范围或视频状态等信息查询视频列表
     * 描述：2、请求入参startTime（开始时间）和endTime（结束时间）的优先级最高
     * 描述：3、当请求入参startTime和endTime都不为空，则startTime＜＝时间区间<=endTime
     * 描述：4、当请求入参startTime为空，endTime不为空，则时间区间为<=endTime
     * 描述：5、当请求入参startTime不为空，endTime为空，则时间区间为>=startTime
     * 描述：6、当请求入参startTime和endTime都为空，则时间区间以startDate（开始日期）和endDate（结束日期）为准
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetNewList() throws IOException, NoSuchAlgorithmException {
        VodGetNewListRequest vodGetNewListRequest = new VodGetNewListRequest();
        VodGetNewListResponse vodGetNewListResponse = null;
        try {
            vodGetNewListRequest.setStartTime(super.getDate(2021, 1, 15, 9, 15, 15))
                    .setEndTime(super.getDate(2021, 3, 15, 9, 15, 15))
                    .setStartDate(null)
                    .setEndDate(null)
                    .setCategoryTree("1")
                    .setPublished(0)
                    .setCurrentPage(1)
                    .setPageSize(10);
            vodGetNewListResponse = new VodListServiceImpl().getNewList(vodGetNewListRequest);
            Assert.assertNotNull(vodGetNewListResponse);
            if (vodGetNewListResponse != null) {
                log.debug("测试查询最新视频/全部视频列表成功，{}", JSON.toJSONString(vodGetNewListResponse));
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
     * 测试查询最热视频列表
     * 描述：分页查询最热视频列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetHotList() throws IOException, NoSuchAlgorithmException {
        VodGetHotListRequest vodGetHotListRequest = new VodGetHotListRequest();
        VodGetHotListResponse vodGetHotListResponse = null;
        try {
            vodGetHotListRequest.setCurrentPage(1).setPageSize(10);
            vodGetHotListResponse = new VodListServiceImpl().getHotList(vodGetHotListRequest);
            Assert.assertNotNull(vodGetHotListResponse);
            if (vodGetHotListResponse != null) {
                log.debug("测试查询最热视频列表成功，{}", JSON.toJSONString(vodGetHotListResponse));
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
     * 测试查询视频回收站列表
     * 描述：分页查询视频回收站列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetDelList() throws IOException, NoSuchAlgorithmException {
        VodGetDelListRequest vodGetDelListRequest = new VodGetDelListRequest();
        VodGetDelListResponse vodGetDelListResponse = null;
        try {
            vodGetDelListRequest.setCurrentPage(1).setPageSize(10);
            vodGetDelListResponse = new VodListServiceImpl().getDelList(vodGetDelListRequest);
            Assert.assertNotNull(vodGetDelListResponse);
            if (vodGetDelListResponse != null) {
                log.debug("测试查询视频回收站列表成功，{}", JSON.toJSONString(vodGetDelListResponse));
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
     * 测试查询审核不通过视频列表
     * 描述：分页查询审核不通过视频列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetIllegalList() throws IOException, NoSuchAlgorithmException {
        VodGetIllegalListRequest vodGetIllegalListRequest = new VodGetIllegalListRequest();
        VodGetIllegalListResponse vodGetIllegalListResponse = null;
        try {
            vodGetIllegalListRequest.setCurrentPage(1).setPageSize(10);
            vodGetIllegalListResponse = new VodListServiceImpl().getIllegalList(vodGetIllegalListRequest);
            Assert.assertNotNull(vodGetIllegalListResponse);
            if (vodGetIllegalListResponse != null) {
                log.debug("测试查询审核不通过视频列表成功，{}", JSON.toJSONString(vodGetIllegalListResponse));
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
