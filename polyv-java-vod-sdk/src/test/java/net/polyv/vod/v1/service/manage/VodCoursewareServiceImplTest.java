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
import net.polyv.vod.v1.entity.upload.VodUploadPPTRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodCoursewareServiceImpl;
import net.polyv.vod.v1.service.upload.impl.VodUploadServiceImpl;

/**
 * 课件管理
 * @author: fangyan
 */
@Slf4j
public class VodCoursewareServiceImplTest extends BaseTest {
    
    /**
     * 测试异步上传课件
     * 描述：通过视频id上传课件，支持ppt、pptx及pdf文件
     * 约束：2、接口只返回上传结果，课件转换结果需通过事件回调获取，详见：[回调通知说明](callBack?id=九、课件转换完成).
     * 返回：true为上传课件成功，false为上传课件失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
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
                log.debug("测试异步上传课件成功");
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
     * 测试同步上传课件
     * 描述：通过视频id与ppt控制文件上传ppt课件
     * 约束：2、ppt控制文件格式示例如下，每一行为：“秒数”+“:”+“标题”（注：ppt控制文件必须是UTF-8的编码格式，否则课件的章节标题会显示为乱码）
     * 返回：true为上传成功，false为上传失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testUploadPPT() throws IOException, NoSuchAlgorithmException {
        VodUploadPPTRequest vodUploadPPTRequest = new VodUploadPPTRequest();
        Boolean vodUploadPPTResponse = null;
        try {
            String pptFile = getClass().getResource("/file/PPT.pptx").getPath();
            String controlFile = getClass().getResource("/file/controlFile.txt").getPath();
            vodUploadPPTRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setPpt(new File(pptFile))
                    .setControlFile(new File(controlFile));
            vodUploadPPTResponse = new VodUploadServiceImpl().uploadPPT(vodUploadPPTRequest);
            Assert.assertTrue(vodUploadPPTResponse);
            if (vodUploadPPTResponse) {
                log.debug("测试同步上传课件成功");
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
     * 测试查询课件
     * 描述：通过视频id查询课件
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
                log.debug("测试查询课件成功,{}", JSON.toJSONString(vodQueryCoursewareResponseList));
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
     * 测试删除课件
     * 描述：通过视频id删除课件
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
                log.debug("测试删除课件成功");
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
