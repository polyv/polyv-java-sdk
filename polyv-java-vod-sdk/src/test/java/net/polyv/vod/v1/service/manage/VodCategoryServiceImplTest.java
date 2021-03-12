package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.category.VodMoveCategoryRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodCategoryServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 视频分类
 * @author: fangyan
 */
@Slf4j
public class VodCategoryServiceImplTest extends BaseTest {
    
    private static boolean testMoveCategoryFlag;
    
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
            // testMoveCategoryFlag为false则移动目标目录
            // testMoveCategoryFlag为true则恢复目标目录
            if (testMoveCategoryFlag) {
                vodMoveCategoryRequest.setCategoryId("1615536384688")
                        .setDestCategoryId("1")
                        .setRequestId(VodSignUtil.generateUUID());
            } else {
                vodMoveCategoryRequest.setCategoryId("1615536384688")
                        .setDestCategoryId("1602671097888")
                        .setRequestId(VodSignUtil.generateUUID());
            }
            vodMoveCategoryResponse = new VodCategoryServiceImpl().moveCategory(vodMoveCategoryRequest);
            Assert.assertTrue(vodMoveCategoryResponse);
            if (vodMoveCategoryResponse) {
                log.debug("测试移动视频分类接口成功");
            }
            if (testMoveCategoryFlag == false) {
                testMoveCategoryFlag = true;
                testMoveCategory();
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
