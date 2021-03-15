package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.category.VodCreateCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodMoveCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodUpdateCategoryProfileRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodCategoryServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 视频分类
 * @author: fangyan
 */
@Slf4j
public class VodCategoryServiceImplTest extends BaseTest {
    
    /**
     * 测试移动视频分类接口
     * 返回：true为修改成功，false为修改失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testMoveCategory() throws IOException, NoSuchAlgorithmException {
        VodMoveCategoryRequest vodMoveCategoryRequest = new VodMoveCategoryRequest();
        Boolean vodMoveCategoryResponse = null;
        try {
            vodMoveCategoryRequest.setCategoryId("1615536384688")
                    .setDestCategoryId("1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodMoveCategoryResponse = new VodCategoryServiceImpl().moveCategory(vodMoveCategoryRequest);
            Assert.assertTrue(vodMoveCategoryResponse);
            if (vodMoveCategoryResponse) {
                log.debug("测试移动视频分类接口成功");
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
     * 测试设置分类属性
     * 返回：true为修改成功，false为修改失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testUpdateCategoryProfile() throws IOException, NoSuchAlgorithmException {
        VodUpdateCategoryProfileRequest vodUpdateCategoryProfileRequest = new VodUpdateCategoryProfileRequest();
        Boolean vodUpdateCategoryProfileResponse = null;
        try {
            vodUpdateCategoryProfileRequest.setCategoryId("1615536384688")
                    .setIsSettings("Y")
                    .setKeepSource(0)
                    .setEncrypt(0)
                    .setHlsLevel("open")
                    .setIsEdu(0)
                    .setEncodeAAC(0)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateCategoryProfileResponse = new VodCategoryServiceImpl().updateCategoryProfile(
                    vodUpdateCategoryProfileRequest);
            Assert.assertTrue(vodUpdateCategoryProfileResponse);
            if (vodUpdateCategoryProfileResponse) {
                log.debug("测试设置分类属性成功");
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
     * 测试新建视频分类
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testCreateCategory() throws IOException, NoSuchAlgorithmException {
        VodCreateCategoryRequest vodCreateCategoryRequest = new VodCreateCategoryRequest();
        String vodCreateCategoryResponse = null;
        try {
            vodCreateCategoryRequest.setCategoryName("Junit测试_20210315")
                    .setParentId("1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodCreateCategoryResponse = new VodCategoryServiceImpl().createCategory(vodCreateCategoryRequest);
            Assert.assertNotNull(vodCreateCategoryResponse);
            if (vodCreateCategoryResponse != null) {
                log.debug("测试新建视频分类成功，{}", vodCreateCategoryResponse);
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
