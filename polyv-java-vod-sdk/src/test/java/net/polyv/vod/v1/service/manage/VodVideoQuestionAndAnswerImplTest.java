package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.edit.VodDeleteVideoExamRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamLogResponse;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamRequest;
import net.polyv.vod.v1.entity.manage.info.VodGetVideoExamResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodEditServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodInfoServiceImpl;

/**
 * 视频问答
 * @author: fangyan
 */
@Slf4j
public class VodVideoQuestionAndAnswerImplTest extends BaseTest {
    
    /**
     * 测试查询单个视频的问答题目
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetVideoExam() throws IOException, NoSuchAlgorithmException {
        VodGetVideoExamRequest vodGetVideoExamRequest = new VodGetVideoExamRequest();
        List<VodGetVideoExamResponse> vodGetVideoExamResponseList = null;
        try {
            vodGetVideoExamRequest.setVideoId("1b448be3230a0194d959426ae005645f_1");
            vodGetVideoExamResponseList = new VodInfoServiceImpl().getVideoExam(vodGetVideoExamRequest);
            Assert.assertNotNull(vodGetVideoExamResponseList);
            if (vodGetVideoExamResponseList != null) {
                log.debug("测试查询单个视频的问答题目成功,{}", JSON.toJSONString(vodGetVideoExamResponseList));
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
     * 测试批量查询答题日志
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetVideoExamLog() throws IOException, NoSuchAlgorithmException {
        VodGetVideoExamLogRequest vodGetVideoExamLogRequest = new VodGetVideoExamLogRequest();
        VodGetVideoExamLogResponse vodGetVideoExamLogResponse = null;
        try {
            vodGetVideoExamLogRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3230a0194d959426ae005645f_1")
                    .setStart(super.getDate(2021, 2, 1))
                    .setEnd(super.getDate(2021, 3, 12));
            vodGetVideoExamLogResponse = new VodInfoServiceImpl().getVideoExamLog(vodGetVideoExamLogRequest);
            Assert.assertNotNull(vodGetVideoExamLogResponse);
            if (vodGetVideoExamLogResponse != null) {
                log.debug("测试批量查询答题日志成功,{}", JSON.toJSONString(vodGetVideoExamLogResponse));
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
     * 测试删除单个视频的问答题目
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteVideoExam() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoExamRequest vodDeleteVideoExamRequest = new VodDeleteVideoExamRequest();
        Boolean vodDeleteVideoExamResponse = null;
        try {
            vodDeleteVideoExamRequest
                    //可通过 new VodListServiceImpl().getDelList()获取
                    .setVideoId("1b448be323ee722d75bbe7fc25343a06_1");
            vodDeleteVideoExamResponse = new VodEditServiceImpl().deleteVideoExam(vodDeleteVideoExamRequest);
            Assert.assertTrue(vodDeleteVideoExamResponse);
            if (vodDeleteVideoExamResponse) {
                log.debug("测试删除单个视频的问答题目成功");
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
