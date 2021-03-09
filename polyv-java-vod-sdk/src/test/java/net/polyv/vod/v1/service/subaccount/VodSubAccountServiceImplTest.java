package net.polyv.vod.v1.service.subaccount;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.subaccount.edit.VodAddCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.edit.VodDeleteVideoRequest;
import net.polyv.vod.v1.entity.subaccount.edit.VodUpdateVideoCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.edit.VodUpdateVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryCategoryResponse;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryVideoInfoResponse;
import net.polyv.vod.v1.entity.subaccount.query.VodSearchVideoListRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodSearchVideoListResponse;
import net.polyv.vod.v1.service.SubBaseTest;
import net.polyv.vod.v1.service.subaccount.impl.VodSubAccountServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 子账号相关
 * @author: fangyan
 */
@Slf4j
public class VodSubAccountServiceImplTest extends SubBaseTest {
    
    /**
     * 测试搜索视频
     * 描述：按视频标题、分类、标签等条件查找视频
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testSearchVideoList() throws IOException, NoSuchAlgorithmException {
        VodSearchVideoListRequest vodSearchVideoListRequest = new VodSearchVideoListRequest();
        VodSearchVideoListResponse vodSearchVideoListResponse = null;
        try {
            vodSearchVideoListRequest.setCategoryId("1602300731843")
                    .setTitle("学习英语")
                    .setUploader("主账号")
                    .setStatus("61")
                    .setContainSubCate("Y")
                    .setStartTime(super.getDate(2021, 1, 4, 10, 35))
                    .setEndTime(super.getDate(2021, 2, 5, 10, 35))
                    .setSort("creationTimeDesc")
                    .setRequestId(VodSignUtil.generateUUID());
            vodSearchVideoListResponse = new VodSubAccountServiceImpl().searchVideoList(vodSearchVideoListRequest);
            Assert.assertNotNull(vodSearchVideoListResponse);
            if (vodSearchVideoListResponse != null) {
                log.debug("测试搜索视频成功,{}", JSON.toJSONString(vodSearchVideoListResponse));
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
     * 测试查询视频信息
     * 描述：按视频ID查询视频信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoInfoRequest vodQueryVideoInfoRequest = new VodQueryVideoInfoRequest();
        List<VodQueryVideoInfoResponse> vodQueryVideoInfoResponse = null;
        try {
            vodQueryVideoInfoRequest.setVideoIds(
                    "1b448be32355403dad586f7468e63e23_1,1b448be323a146649ad0cc89d0faed9c_1")
                    .setFilters("basicInfo,metaData,transcodeInfo,snapshotInfo")
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoInfoResponse = new VodSubAccountServiceImpl().getVideoInfo(vodQueryVideoInfoRequest);
            Assert.assertNotNull(vodQueryVideoInfoResponse);
            if (vodQueryVideoInfoResponse != null) {
                log.debug("测试查询视频信息,{}", JSON.toJSONString(vodQueryVideoInfoResponse));
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
     * 测试修改视频信息
     * 描述：根据视频ID修改视频信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testUpdateVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoInfoRequest vodUpdateVideoInfoRequest = new VodUpdateVideoInfoRequest();
        Boolean vodUpdateVideoInfoResponse = null;
        try {
            vodUpdateVideoInfoRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setTitle("junit合并并修改_1")
                    .setDesc("这是一个通过junit合并的视频_1")
                    .setTag("junit测试_1")
                    .setPublishUrl(null)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoInfoResponse = new VodSubAccountServiceImpl().updateVideoInfo(vodUpdateVideoInfoRequest);
            Assert.assertTrue(vodUpdateVideoInfoResponse);
            if (vodUpdateVideoInfoResponse) {
                log.debug("测试修改视频信息成功");
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
     * 测试批量修改视频所属分类
     * 描述：根据视频ID批量修改视频所属分类
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testUpdateVideoCategory() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoCategoryRequest vodUpdateVideoCategoryRequest = new VodUpdateVideoCategoryRequest();
        Boolean vodUpdateVideoCategoryResponse = null;
        try {
            vodUpdateVideoCategoryRequest.setVideoIds(
                    "1b448be323a146649ad0cc89d0faed9c_1,1b448be32389b93ea8be08bf0d257043_1")
                    .setCategoryId(1602300731843L)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoCategoryResponse = new VodSubAccountServiceImpl().updateVideoCategory(
                    vodUpdateVideoCategoryRequest);
            Assert.assertTrue(vodUpdateVideoCategoryResponse);
            if (vodUpdateVideoCategoryResponse) {
                log.debug("批量修改视频所属分类成功");
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
     * 描述：根据视频ID删除视频
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testDeleteVideo() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoRequest vodDeleteVideoRequest = new VodDeleteVideoRequest();
        Boolean vodDeleteVideoResponse = null;
        try {
            vodDeleteVideoRequest.setVideoId("1b448be3238415eee2fa40753737255b_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteVideoResponse = new VodSubAccountServiceImpl().deleteVideo(vodDeleteVideoRequest);
            Assert.assertTrue(vodDeleteVideoResponse);
            if (vodDeleteVideoResponse) {
                log.debug("删除视频成功");
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
     * 测试查询视频分类
     * 描述：根据分类ID查询视频分类
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryCategory() throws IOException, NoSuchAlgorithmException {
        VodQueryCategoryRequest vodQueryCategoryRequest = new VodQueryCategoryRequest();
        VodQueryCategoryResponse vodQueryCategoryResponse = null;
        try {
            vodQueryCategoryRequest.setCategoryId("1608891483165")
                    .setCurrentPage(1)
                    .setPageSize(20)
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryCategoryResponse = new VodSubAccountServiceImpl().queryCategory(vodQueryCategoryRequest);
            Assert.assertNotNull(vodQueryCategoryResponse);
            if (vodQueryCategoryResponse != null) {
                log.debug("测试查询视频分类成功,{}", JSON.toJSONString(vodQueryCategoryResponse));
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
     * 测试新增视频分类
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testAddCategory() throws IOException, NoSuchAlgorithmException {
        VodAddCategoryRequest vodAddCategoryRequest = new VodAddCategoryRequest();
        Boolean vodDeleteVideoResponse = null;
        try {
            vodAddCategoryRequest.setName("junit测试新增分类20210309")
                    .setParentId("1602671097888")
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteVideoResponse = new VodSubAccountServiceImpl().addCategory(vodAddCategoryRequest);
            Assert.assertTrue(vodDeleteVideoResponse);
            if (vodDeleteVideoResponse) {
                log.debug("新增视频分类成功");
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
