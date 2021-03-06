package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.category.VodCreateCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodCreateCategoryResponse;
import net.polyv.vod.v1.entity.manage.category.VodDeleteCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodGetCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodGetCategoryResponse;
import net.polyv.vod.v1.entity.manage.category.VodGetCategorySizeRequest;
import net.polyv.vod.v1.entity.manage.category.VodMoveCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodUpdateCategoryNameRequest;
import net.polyv.vod.v1.entity.manage.category.VodUpdateCategoryProfileRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodCategoryServiceImpl;

/**
 * 视频分类
 * @author: fangyan
 */
@Slf4j
public class VodCategoryServiceImplTest extends BaseTest {
    
    /**
     * 测试新建视频分类
     * 描述：通过分类名称与上级分类目录id新建视频分类
     * 返回：新建视频分类ID
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
//    @Test
    public void testCreateCategory() throws IOException, NoSuchAlgorithmException {
        VodCreateCategoryRequest vodCreateCategoryRequest = new VodCreateCategoryRequest();
        VodCreateCategoryResponse vodCreateCategoryResponse = null;
        try {
            vodCreateCategoryRequest.setCategoryName("Junit测试")
                    .setParentId("1");
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
    
    /**
     * 测试查询视频分类
     * 描述：通过分类id查询分类下的树结构信息，含父子节点信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetCategory() throws IOException, NoSuchAlgorithmException {
        VodGetCategoryRequest vodGetCategoryRequest = new VodGetCategoryRequest();
        List<VodGetCategoryResponse> vodGetCategoryResponseList = null;
        try {
            vodGetCategoryRequest.setCategoryId("1");
            vodGetCategoryResponseList = new VodCategoryServiceImpl().getCategory(vodGetCategoryRequest);
            Assert.assertNotNull(vodGetCategoryResponseList);
            if (vodGetCategoryResponseList != null) {
                log.debug("测试查询视频分类成功,{}", JSON.toJSONString(vodGetCategoryResponseList));
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
     * 测试查询分类使用空间
     * 描述：通过分类id查询分类目录的使用空间
     * 返回：分类下的视频大小，单位为byte
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetCategorySize() throws IOException, NoSuchAlgorithmException {
        VodGetCategorySizeRequest vodGetCategorySizeRequest = new VodGetCategorySizeRequest();
        Long vodGetCategorySizeResponse = null;
        try {
            vodGetCategorySizeRequest.setCategoryId("1602671097888");
            vodGetCategorySizeResponse = new VodCategoryServiceImpl().getCategorySize(vodGetCategorySizeRequest);
            Assert.assertNotNull(vodGetCategorySizeResponse);
            if (vodGetCategorySizeResponse != null) {
                log.debug("测试通过分类ID查询目录使用空间成功");
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
     * 测试修改分类名称
     * 描述：通过分类id修改分类名称
     * 返回：true为修改成功，false为修改失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testUpdateCategoryName() throws IOException, NoSuchAlgorithmException {
        VodUpdateCategoryNameRequest vodUpdateCategoryNameRequest = new VodUpdateCategoryNameRequest();
        Boolean vodUpdateCategoryNameResponse = null;
        try {
            vodUpdateCategoryNameRequest.setCategoryId("1615536384688")
                    .setCategoryName("Junit测试(勿删)_3");
            vodUpdateCategoryNameResponse = new VodCategoryServiceImpl().updateCategoryName(
                    vodUpdateCategoryNameRequest);
            Assert.assertTrue(vodUpdateCategoryNameResponse);
            if (vodUpdateCategoryNameResponse) {
                log.debug("测试修改分类名称成功");
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
     * 测试修改分类属性
     * 描述：通过分类id修改分类属性
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
                    .setEncodeAAC(0);
            vodUpdateCategoryProfileResponse = new VodCategoryServiceImpl().updateCategoryProfile(
                    vodUpdateCategoryProfileRequest);
            Assert.assertTrue(vodUpdateCategoryProfileResponse);
            if (vodUpdateCategoryProfileResponse) {
                log.debug("测试修改分类属性成功");
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
     * 测试移动视频分类
     * 描述：通过分类id移动视频分类
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
                    .setDestCategoryId("1");
            vodMoveCategoryResponse = new VodCategoryServiceImpl().moveCategory(vodMoveCategoryRequest);
            Assert.assertTrue(vodMoveCategoryResponse);
            if (vodMoveCategoryResponse) {
                log.debug("测试移动视频分类成功");
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
     * 测试删除分类
     * 描述：通过分类id删除分类
     * 返回：true为删除成功，false为删除失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testDeleteCategory() throws IOException, NoSuchAlgorithmException {
        VodDeleteCategoryRequest vodDeleteCategoryRequest = new VodDeleteCategoryRequest();
        Boolean vodDeleteCategoryResponse = null;
        try {
            //准备测试数据
            String categoryID = super.createCategory();
            
            vodDeleteCategoryRequest.setCategoryId(categoryID);
            vodDeleteCategoryResponse = new VodCategoryServiceImpl().deleteCategory(vodDeleteCategoryRequest);
            Assert.assertTrue(vodDeleteCategoryResponse);
            if (vodDeleteCategoryResponse) {
                log.debug("测试删除分类成功");
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
