package net.polyv.vod.v1.service.subaccount;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountAddCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountDeleteCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountDeleteVideoRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateCategoryProfileRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateVideoCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryCategoryResponse;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryVideoInfoResponse;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountSearchVideoListRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountSearchVideoListResponse;
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
        VodSubAccountSearchVideoListRequest vodSubAccountSearchVideoListRequest = new VodSubAccountSearchVideoListRequest();
        VodSubAccountSearchVideoListResponse vodSubAccountSearchVideoListResponse = null;
        try {
            vodSubAccountSearchVideoListRequest.setCategoryId("1602300731843")
                    .setTitle("学习英语")
                    .setUploader("主账号")
                    .setStatus("61")
                    .setContainSubCate("Y")
                    .setStartTime(super.getDate(2021, 1, 4, 10, 35))
                    .setEndTime(super.getDate(2021, 2, 5, 10, 35))
                    .setSort("creationTimeDesc")
                    .setRequestId(VodSignUtil.generateUUID());
            vodSubAccountSearchVideoListResponse = new VodSubAccountServiceImpl().searchVideoList(
                    vodSubAccountSearchVideoListRequest);
            Assert.assertNotNull(vodSubAccountSearchVideoListResponse);
            if (vodSubAccountSearchVideoListResponse != null) {
                log.debug("测试搜索视频成功,{}", JSON.toJSONString(vodSubAccountSearchVideoListResponse));
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
        VodSubAccountQueryVideoInfoRequest vodSubAccountQueryVideoInfoRequest = new VodSubAccountQueryVideoInfoRequest();
        List<VodSubAccountQueryVideoInfoResponse> vodSubAccountQueryVideoInfoResponseList = null;
        try {
            vodSubAccountQueryVideoInfoRequest.setVideoIds(
                    "1b448be32355403dad586f7468e63e23_1,1b448be323a146649ad0cc89d0faed9c_1")
                    .setFilters("basicInfo,metaData,transcodeInfo,snapshotInfo")
                    .setRequestId(VodSignUtil.generateUUID());
            vodSubAccountQueryVideoInfoResponseList = new VodSubAccountServiceImpl().getVideoInfo(
                    vodSubAccountQueryVideoInfoRequest);
            Assert.assertNotNull(vodSubAccountQueryVideoInfoResponseList);
            if (vodSubAccountQueryVideoInfoResponseList != null) {
                log.debug("测试查询视频信息,{}", JSON.toJSONString(vodSubAccountQueryVideoInfoResponseList));
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
     * 返回：true为修改成功，false为修改失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testUpdateVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodSubAccountUpdateVideoInfoRequest vodSubAccountUpdateVideoInfoRequest = new VodSubAccountUpdateVideoInfoRequest();
        Boolean vodUpdateVideoInfoResponse = null;
        try {
            vodSubAccountUpdateVideoInfoRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setTitle("junit合并并修改_1")
                    .setDesc("这是一个通过junit合并的视频_1")
                    .setTag("junit测试_1")
                    .setPublishUrl(null)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoInfoResponse = new VodSubAccountServiceImpl().updateVideoInfo(
                    vodSubAccountUpdateVideoInfoRequest);
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
     * 返回：true为修改成功，false为修改失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testUpdateVideoCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountUpdateVideoCategoryRequest vodSubAccountUpdateVideoCategoryRequest = new VodSubAccountUpdateVideoCategoryRequest();
        Boolean vodUpdateVideoCategoryResponse = null;
        try {
            vodSubAccountUpdateVideoCategoryRequest.setVideoIds(
                    "1b448be323a146649ad0cc89d0faed9c_1,1b448be32389b93ea8be08bf0d257043_1")
                    .setCategoryId("1602300731843")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoCategoryResponse = new VodSubAccountServiceImpl().updateVideoCategory(
                    vodSubAccountUpdateVideoCategoryRequest);
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
     * TODO 老版本上传视频无法使用，上传视频完成后再修改
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
//    @Test
    public void testDeleteVideo() throws IOException, NoSuchAlgorithmException {
        VodSubAccountDeleteVideoRequest vodSubAccountDeleteVideoRequest = new VodSubAccountDeleteVideoRequest();
        Boolean vodDeleteVideoResponse = null;
        try {
            vodSubAccountDeleteVideoRequest.setVideoId("1b448be3238415eee2fa40753737255b_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteVideoResponse = new VodSubAccountServiceImpl().deleteVideo(vodSubAccountDeleteVideoRequest);
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
        VodSubAccountQueryCategoryRequest vodSubAccountQueryCategoryRequest = new VodSubAccountQueryCategoryRequest();
        VodSubAccountQueryCategoryResponse vodSubAccountQueryCategoryResponse = null;
        try {
            vodSubAccountQueryCategoryRequest.setCategoryId("1608891483165")
                    .setCurrentPage(1)
                    .setPageSize(20)
                    .setRequestId(VodSignUtil.generateUUID());
            vodSubAccountQueryCategoryResponse = new VodSubAccountServiceImpl().queryCategory(vodSubAccountQueryCategoryRequest);
            Assert.assertNotNull(vodSubAccountQueryCategoryResponse);
            if (vodSubAccountQueryCategoryResponse != null) {
                log.debug("测试查询视频分类成功,{}", JSON.toJSONString(vodSubAccountQueryCategoryResponse));
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
//    @Test
    public void testAddCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountAddCategoryRequest vodSubAccountAddCategoryRequest = new VodSubAccountAddCategoryRequest();
        String vodDeleteVideoResponse = null;
        try {
            vodSubAccountAddCategoryRequest.setName("junit测试新增分类20210309")
                    .setParentId(null)
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteVideoResponse = new VodSubAccountServiceImpl().addCategory(vodSubAccountAddCategoryRequest);
            Assert.assertNotNull(vodDeleteVideoResponse);
            if (vodDeleteVideoResponse != null) {
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
    
    /**
     * 测试修改视频分类信息
     * 返回：true为修改成功，false为修改失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testUpdateCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountUpdateCategoryRequest vodSubAccountUpdateCategoryRequest = new VodSubAccountUpdateCategoryRequest();
        Boolean vodUpdateCategoryResponse = null;
        try {
            vodSubAccountUpdateCategoryRequest.setCategoryId("1602671097888")
                    .setCategoryName("Junit测试(勿删)_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateCategoryResponse = new VodSubAccountServiceImpl().updateCategory(
                    vodSubAccountUpdateCategoryRequest);
            Assert.assertTrue(vodUpdateCategoryResponse);
            if (vodUpdateCategoryResponse) {
                log.debug("修改视频分类信息成功");
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
     * 测试删除视频分类
     * 返回：true为删除成功，false为删除失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testDeleteCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountDeleteCategoryRequest vodSubAccountDeleteCategoryRequest = new VodSubAccountDeleteCategoryRequest();
        Boolean vodDeleteCategoryResponse = null;
        try {
            //生成测试数据
            String categoryId = super.addCategory();
            vodSubAccountDeleteCategoryRequest.setCategoryId(categoryId).setRequestId(VodSignUtil.generateUUID());
            vodDeleteCategoryResponse = new VodSubAccountServiceImpl().deleteCategory(
                    vodSubAccountDeleteCategoryRequest);
            Assert.assertTrue(vodDeleteCategoryResponse);
            if (vodDeleteCategoryResponse) {
                log.debug("删除视频分类成功");
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
     * 测试修改视频分类属性设置
     * 返回：true为修改成功，false为修改失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testUpdateCategoryProfile() throws IOException, NoSuchAlgorithmException {
        VodSubAccountUpdateCategoryProfileRequest vodSubAccountUpdateCategoryProfileRequest = new VodSubAccountUpdateCategoryProfileRequest();
        Boolean vodUpdateCategoryProfileResponse = null;
        try {
            vodSubAccountUpdateCategoryProfileRequest.setCategoryId("1615286323771")
                    .setEnabled("Y")
                    .setKeepSource(0)
                    .setEncrypt(0)
                    .setEncryptLevel("open")
                    .setIsEdu(0)
                    .setEncodeAAC(0)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateCategoryProfileResponse = new VodSubAccountServiceImpl().updateCategoryProfile(
                    vodSubAccountUpdateCategoryProfileRequest);
            Assert.assertTrue(vodUpdateCategoryProfileResponse);
            if (vodUpdateCategoryProfileResponse) {
                log.debug("修改视频分类属性设置成功");
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
