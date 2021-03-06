package net.polyv.live.v1.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireDetailRequest;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireDetailResponse;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireDetailSetRequest;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireDetailSetResponse;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireListRequest;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireListResponse;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireResultPageRequest;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireResultPageResponse;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireResultRequest;
import net.polyv.live.v1.entity.interact.LiveQuestionnaireResultResponse;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.interact.impl.LiveQuestionnaireServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 问卷管理
 * @author: thomas
 **/
@Slf4j
public class LiveQuestionnaireServiceImplTest extends BaseTest {
    
    /**
     * 查询频道问卷列表
     * 描述：接口用于获取频道的问卷列表
     * API地址：CHANNEL_QUESTIONNAIRE_LIST_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireListInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireListRequest liveQuestionnaireListRequest = new LiveQuestionnaireListRequest();
        LiveQuestionnaireListResponse liveQuestionnaireListResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireListRequest.setChannelId(channelId);
            liveQuestionnaireListResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireListInfo(
                    liveQuestionnaireListRequest);
            Assert.assertNotNull(liveQuestionnaireListResponse);
            if (liveQuestionnaireListResponse != null) {
                //to do something ......
                log.debug("测试查询频道问卷列表成功{}", JSON.toJSONString(liveQuestionnaireListResponse));
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
     * 查询频道问卷详情
     * 描述：1、接口用于查询频道问卷详情
     * 描述：2、问卷ID 可以从获取频道问卷列表中获取
     * API地址：CHANNEL_QUESTIONNAIRE_DETAIL_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireDetailInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireDetailRequest liveQuestionnaireDetailRequest = new LiveQuestionnaireDetailRequest();
        LiveQuestionnaireDetailResponse liveQuestionnaireDetailResponse = null;
        try {
            String channelId = super.createChannel();
            //获取详情
            liveQuestionnaireDetailRequest.setChannelId(channelId)
                    .setQuestionnaireId("fs9skpv22f");
            liveQuestionnaireDetailResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireDetailInfo(
                    liveQuestionnaireDetailRequest);
            Assert.assertNotNull(liveQuestionnaireDetailResponse);
            if (liveQuestionnaireDetailResponse != null) {
                //to do something ......
                log.debug("测试查询频道问卷详情成功{}", JSON.toJSONString(liveQuestionnaireDetailResponse));
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
     * 查询频道问卷结果
     * 描述：接口用于查询直播问卷的答题结果及统计
     * API地址：CHANNEL_QUESTIONNAIRE_ANSWER_RECORD_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireResultInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireResultRequest liveQuestionnaireResultRequest = new LiveQuestionnaireResultRequest();
        List<LiveQuestionnaireResultResponse> liveQuestionnaireResultResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireResultRequest.setChannelId(channelId);
            liveQuestionnaireResultRequest.setStartDate(getDate(2020, 10, 01)).setEndDate(getDate(2099, 12, 12));
//        liveQuestionnaireResultRequest.setQuestionnaireId("fs9skpv22f");
            liveQuestionnaireResultResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireResultInfo(
                    liveQuestionnaireResultRequest);
            Assert.assertNotNull(liveQuestionnaireResultResponse);
            if (liveQuestionnaireResultResponse != null) {
                //to do something ......
                log.debug("测试查询频道问卷结果成功{}", JSON.toJSONString(liveQuestionnaireResultResponse));
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
     * 分页查询问卷结果
     * 描述：分页查询直播问卷的答题结果及统计
     * API地址：CHANNEL_QUESTIONNAIRE_ANSWER_RECORD_PAGE_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireResultPageInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireResultPageRequest liveQuestionnaireResultPageRequest =
                new LiveQuestionnaireResultPageRequest();
        LiveQuestionnaireResultPageResponse liveQuestionnaireResultPageResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireResultPageRequest.setChannelId(channelId)
                    .setStartDate(getDate(2020, 10, 01))
                    .setEndDate(getDate(2099, 12, 12))
                    .setPageSize(20)
                    .setCurrentPage(1);
            liveQuestionnaireResultPageResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireResultPageInfo(
                    liveQuestionnaireResultPageRequest);
            Assert.assertNotNull(liveQuestionnaireResultPageRequest);
            if (liveQuestionnaireResultPageResponse != null) {
                //to do something ......
                log.debug("测试分页查询频道问卷结果成功{}", JSON.toJSONString(liveQuestionnaireResultPageResponse));
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
     * 设置频道问卷信息
     * 描述：接口用于编辑或添加问卷信息，为全量增加或修改
     * API地址：CHANNEL_QUESTIONNAIRE_DETAIL_SET_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetQuestionnaireDetailInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireDetailSetRequest liveQuestionnaireDetailSetRequest = new LiveQuestionnaireDetailSetRequest();
        LiveQuestionnaireDetailSetResponse liveQuestionnaireDetailSetResponse = null;
        try {
            String channelId = super.createChannel();
            //封装问卷请求对象
            liveQuestionnaireDetailSetRequest.setChannelId(channelId)
                    .setCustomQuestionnaireId(LiveSignUtil.generateUUID())
//                .setQuestionnaireId("fs9skpv22f")
                    .setQuestionnaireTitle("测试试卷，明天会更好调查2");
            
            //封装问卷题目
            LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail =
                    new LiveQuestionnaireDetailSetRequest.QuestionDetail();
            questionDetail.setQuestionId(LiveSignUtil.generateUUID())
                    .setName("您的兴趣爱好？")
                    .setAnswer("A")
                    .setScoreEnabled(LiveConstant.Flag.YES.getFlag())
                    .setRequired(LiveConstant.Flag.YES.getFlag())
                    .setOptions(Arrays.asList(new String[]{"篮球", "足球", "排球", "跑步", "羽毛球"}))
                    .setScore(20)
                    .setType(LiveConstant.QuestionType.CHECK.getType());
            
            LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail1 =
                    new LiveQuestionnaireDetailSetRequest.QuestionDetail();
            questionDetail1.setQuestionId(LiveSignUtil.generateUUID())
                    .setName("您的性别")
                    .setScoreEnabled(LiveConstant.Flag.NO.getFlag())
                    .setRequired(LiveConstant.Flag.YES.getFlag())
                    .setOptions(Arrays.asList(new String[]{"M", "W"}))
                    .setType(LiveConstant.QuestionType.RADIO.getType());
            
            LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail2 =
                    new LiveQuestionnaireDetailSetRequest.QuestionDetail();
            questionDetail2.setQuestionId(LiveSignUtil.generateUUID())
                    .setName("您的职务？")
                    .setScoreEnabled(LiveConstant.Flag.NO.getFlag())
                    .setRequired(LiveConstant.Flag.YES.getFlag())
                    .setType(LiveConstant.QuestionType.QUESTION.getType());
            
            //将问卷题目和问卷关联
            liveQuestionnaireDetailSetRequest.setQuestions(Arrays.asList(
                    new LiveQuestionnaireDetailSetRequest.QuestionDetail[]{questionDetail, questionDetail1,
                            questionDetail2}));
            
            //发送请求
            liveQuestionnaireDetailSetResponse = new LiveQuestionnaireServiceImpl().setQuestionnaireDetailInfo(
                    liveQuestionnaireDetailSetRequest);
            
            //判断结果
            Assert.assertNotNull(liveQuestionnaireDetailSetResponse);
            if (liveQuestionnaireDetailSetResponse != null) {
                //to do something ......
                log.debug("测试添加频道问卷成功{}", JSON.toJSONString(liveQuestionnaireDetailSetResponse));
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
