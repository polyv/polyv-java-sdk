package net.polyv.live.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.interact.LiveQuestionnaireDetailRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireDetailResponse;
import net.polyv.live.entity.interact.LiveQuestionnaireDetailSetRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireDetailSetResponse;
import net.polyv.live.entity.interact.LiveQuestionnaireListRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireListResponse;
import net.polyv.live.entity.interact.LiveQuestionnaireResultPageRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireResultPageResponse;
import net.polyv.live.entity.interact.LiveQuestionnaireResultRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireResultResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.interact.impl.LiveQuestionnaireImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class LiveQuestionnaireImplTest extends BaseTest {
    
    /**
     * 查询频道问卷详情
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireDetailInfo() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        //创建频道问卷列表
        LiveQuestionnaireDetailSetResponse liveQuestionnaireDetailSetResponse = addQuestionnaireDetailInfo();
     
        //获取详情
        LiveQuestionnaireDetailRequest liveQuestionnaireDetailRequest = new LiveQuestionnaireDetailRequest();
        liveQuestionnaireDetailRequest.setChannelId(channelId)
                .setQuestionnaireId(liveQuestionnaireDetailSetResponse.getQuestionnaireId())
                .setRequestId(LiveSignUtil.generateUUID());
        LiveQuestionnaireDetailResponse liveQuestionnaireDetailResponse =
                new LiveQuestionnaireImpl().getQuestionnaireDetailInfo(
                liveQuestionnaireDetailRequest);
        Assert.assertNotNull(liveQuestionnaireDetailResponse);
        if (liveQuestionnaireDetailResponse != null) {
            //to do something ......
            log.debug("测试查询频道问卷详情成功{}", JSON.toJSONString(liveQuestionnaireDetailResponse));
        }
    }
    
    private LiveQuestionnaireDetailSetResponse addQuestionnaireDetailInfo()
            throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        //封装问卷请求对象
        LiveQuestionnaireDetailSetRequest liveQuestionnaireDetailSetRequest = new LiveQuestionnaireDetailSetRequest();
        liveQuestionnaireDetailSetRequest.setChannelId(channelId).setCustomQuestionnaireId(LiveSignUtil.generateUUID())
//                .setQuestionnaireId(LiveSignUtil.generateUUID())
                .setQuestionnaireTitle("测试试卷，明天会更好调查1").setRequestId(LiveSignUtil.generateUUID());
        
        //封装问卷题目
        LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail =
                liveQuestionnaireDetailSetRequest.new QuestionDetail();
        questionDetail.setQuestionId(LiveSignUtil.generateUUID())
                .setName("您的兴趣爱好？")
                .setAnswer("AB")
                .setScoreEnabled(LiveConstant.Flag.YES.getFlag())
                .setRequired(LiveConstant.Flag.YES.getFlag())
                .setOptions(Arrays.asList(new String[]{"篮球", "足球", "排球", "跑步"}))
                .setScore(20)
                .setType(LiveConstant.QuestionType.CHECK.getType());
        
        LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail1 =
                liveQuestionnaireDetailSetRequest.new QuestionDetail();
        questionDetail1.setQuestionId(LiveSignUtil.generateUUID())
                .setName("您的性别")
                .setScoreEnabled(LiveConstant.Flag.NO.getFlag())
                .setRequired(LiveConstant.Flag.YES.getFlag())
                .setOptions(Arrays.asList(new String[]{"M", "W"}))
                .setType(LiveConstant.QuestionType.RADIO.getType());
        
        LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail2 =
                liveQuestionnaireDetailSetRequest.new QuestionDetail();
        questionDetail2.setQuestionId(LiveSignUtil.generateUUID())
                .setName("您的职务？")
                .setScoreEnabled(LiveConstant.Flag.NO.getFlag())
                .setRequired(LiveConstant.Flag.YES.getFlag())
                .setType(LiveConstant.QuestionType.QUESTION.getType());
        
        
        liveQuestionnaireDetailSetRequest.setQuestions(Arrays.asList(
                new LiveQuestionnaireDetailSetRequest.QuestionDetail[]{questionDetail, questionDetail1,
                        questionDetail2}));
        
        //发送请求
        LiveQuestionnaireDetailSetResponse liveQuestionnaireDetailSetResponse =
                new LiveQuestionnaireImpl().setQuestionnaireDetailInfo(
                liveQuestionnaireDetailSetRequest);
        
        //判断结果
        Assert.assertNotNull(liveQuestionnaireDetailSetResponse);
        if (liveQuestionnaireDetailSetResponse != null) {
            //to do something ......
            log.debug("测试添加频道问卷成功{}", JSON.toJSONString(liveQuestionnaireDetailSetResponse));
            return liveQuestionnaireDetailSetResponse;
        }
        return null;
        
    }
    
    /**
     * 查询频道问卷列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireListInfo() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        
        LiveQuestionnaireListRequest liveQuestionnaireListRequest = new LiveQuestionnaireListRequest();
        liveQuestionnaireListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
        LiveQuestionnaireListResponse liveQuestionnaireListResponse = new LiveQuestionnaireImpl().getQuestionnaireListInfo(
                liveQuestionnaireListRequest);
        Assert.assertNotNull(liveQuestionnaireListResponse);
        if (liveQuestionnaireListResponse != null) {
            //to do something ......
            log.debug("测试查询频道问卷详情成功{}", JSON.toJSONString(liveQuestionnaireListResponse));
        }
    }
    
    /**
     * 设置查询频道问卷
     * 测试设置频道问卷信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetQuestionnaireDetailInfo() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        //封装问卷请求对象
        LiveQuestionnaireDetailSetRequest liveQuestionnaireDetailSetRequest = new LiveQuestionnaireDetailSetRequest();
        liveQuestionnaireDetailSetRequest.setChannelId(channelId).setCustomQuestionnaireId(LiveSignUtil.generateUUID())
//                .setQuestionnaireId("fs9skpv22f")
                .setQuestionnaireTitle("测试试卷，明天会更好调查2").setRequestId(LiveSignUtil.generateUUID());
        
        //封装问卷题目
        LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail =
                liveQuestionnaireDetailSetRequest.new QuestionDetail();
        questionDetail.setQuestionId(LiveSignUtil.generateUUID())
                .setName("您的兴趣爱好？")
                .setAnswer("A")
                .setScoreEnabled(LiveConstant.Flag.YES.getFlag())
                .setRequired(LiveConstant.Flag.YES.getFlag())
                .setOptions(Arrays.asList(new String[]{"篮球", "足球", "排球", "跑步","羽毛球"}))
                .setScore(20)
                .setType(LiveConstant.QuestionType.CHECK.getType());
        
        LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail1 =
                liveQuestionnaireDetailSetRequest.new QuestionDetail();
        questionDetail1.setQuestionId(LiveSignUtil.generateUUID())
                .setName("您的性别")
                .setScoreEnabled(LiveConstant.Flag.NO.getFlag())
                .setRequired(LiveConstant.Flag.YES.getFlag())
                .setOptions(Arrays.asList(new String[]{"M", "W"}))
                .setType(LiveConstant.QuestionType.RADIO.getType());
        
        LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail2 =
                liveQuestionnaireDetailSetRequest.new QuestionDetail();
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
        LiveQuestionnaireDetailSetResponse liveQuestionnaireDetailSetResponse =
                new LiveQuestionnaireImpl().setQuestionnaireDetailInfo(
                liveQuestionnaireDetailSetRequest);
        
        //判断结果
        Assert.assertNotNull(liveQuestionnaireDetailSetResponse);
        if (liveQuestionnaireDetailSetResponse != null) {
            //to do something ......
            log.debug("测试添加频道问卷成功{}", JSON.toJSONString(liveQuestionnaireDetailSetResponse));
        }
        
    }
    
    
    
    /**
     * 查询频道问卷结果
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireResultInfo() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveQuestionnaireResultRequest liveQuestionnaireResultRequest = new LiveQuestionnaireResultRequest();
        liveQuestionnaireResultRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
//        liveQuestionnaireResultRequest.setQuestionnaireId("fs9skpv22f");
        List<LiveQuestionnaireResultResponse> liveQuestionnaireResultResponse = new LiveQuestionnaireImpl().getQuestionnaireResultInfo(
                liveQuestionnaireResultRequest);
        Assert.assertNotNull(liveQuestionnaireResultResponse);
        if (liveQuestionnaireResultResponse != null) {
            //to do something ......
            log.debug("测试查询频道问卷结果成功{}", JSON.toJSONString(liveQuestionnaireResultResponse));
        }
    }
    
    /**
     * 分页查询频道问卷结果
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireResultPageInfo() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveQuestionnaireResultPageRequest liveQuestionnaireResultPageRequest = new LiveQuestionnaireResultPageRequest();
        liveQuestionnaireResultPageRequest.setChannelId(channelId).setPageSize(20).setCurrentPage(1).setRequestId(LiveSignUtil.generateUUID());
        LiveQuestionnaireResultPageResponse liveQuestionnaireResultPageResponse = new LiveQuestionnaireImpl().getQuestionnaireResultPageInfo(
                liveQuestionnaireResultPageRequest);
        Assert.assertNotNull(liveQuestionnaireResultPageRequest);
        if (liveQuestionnaireResultPageResponse != null) {
            //to do something ......
            log.debug("测试分页查询频道问卷结果成功{}", JSON.toJSONString(liveQuestionnaireResultPageResponse));
        }
    }
}
