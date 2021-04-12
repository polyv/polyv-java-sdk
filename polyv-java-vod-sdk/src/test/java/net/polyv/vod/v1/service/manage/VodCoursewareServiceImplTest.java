package net.polyv.vod.v1.service.manage;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.courseware.VodDeleteCoursewareRequest;
import net.polyv.vod.v1.entity.manage.courseware.VodQueryCoursewareRequest;
import net.polyv.vod.v1.entity.manage.courseware.VodQueryCoursewareResponse;
import net.polyv.vod.v1.entity.manage.courseware.VodUploadCoursewareRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodCoursewareServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 课件管理
 * @author: fangyan
 */
@Slf4j
public class VodCoursewareServiceImplTest extends BaseTest {
    /**
     * 测试上传课件
     * 描述：1、上传课件，支持ppt、pptx及pdf文件格式。
     * 约束：2、接口只返回上传结果，课件转换结果需通过事件回调获取，详见：[回调通知说明](callBack?id=九、课件转换完成).
     * 返回：true为上传课件成功，false为上传课件失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUploadCourseware() throws IOException, NoSuchAlgorithmException {
        VodUploadCoursewareRequest vodUploadCoursewareRequest = new VodUploadCoursewareRequest();
        Boolean vodUploadCoursewareResponse = null;
        try {
            String coursewareFile = getClass().getResource("/courseware/Courseware.ppt").getPath();
            vodUploadCoursewareRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setCourseware(new File(coursewareFile));
            vodUploadCoursewareResponse = new VodCoursewareServiceImpl().uploadCourseware(vodUploadCoursewareRequest);
            Assert.assertTrue(vodUploadCoursewareResponse);
            if (vodUploadCoursewareResponse) {
                log.debug("测试上传课件成功");
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
     * 测试删除视频关联的课件
     * 返回：true为删除成功，false为删除失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteCourseware() throws IOException, NoSuchAlgorithmException {
        VodDeleteCoursewareRequest vodDeleteCoursewareRequest = new VodDeleteCoursewareRequest();
        Boolean vodDeleteCoursewareResponse = null;
        try {
            //准备测试数据
            uploadCourseware();
            
            vodDeleteCoursewareRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1");
            vodDeleteCoursewareResponse = new VodCoursewareServiceImpl().deleteCourseware(vodDeleteCoursewareRequest);
            Assert.assertTrue(vodDeleteCoursewareResponse);
            if (vodDeleteCoursewareResponse) {
                log.debug("测试删除视频关联的课件成功");
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
     * 测试查询视频关联的课件
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testQueryCourseware() throws IOException, NoSuchAlgorithmException {
        VodQueryCoursewareRequest vodQueryCoursewareRequest = new VodQueryCoursewareRequest();
        List<VodQueryCoursewareResponse> vodQueryCoursewareResponseList = null;
        try {
            vodQueryCoursewareRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1");
            vodQueryCoursewareResponseList = new VodCoursewareServiceImpl().queryCourseware(vodQueryCoursewareRequest);
            Assert.assertNotNull(vodQueryCoursewareResponseList);
            if (vodQueryCoursewareResponseList != null) {
                log.debug("测试查询视频关联的课件成功,{}", JSON.toJSONString(vodQueryCoursewareResponseList));
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
