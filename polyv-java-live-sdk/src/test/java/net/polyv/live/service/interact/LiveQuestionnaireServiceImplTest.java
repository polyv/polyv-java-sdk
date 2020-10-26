package net.polyv.live.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.interact.LiveListLotteryRequest;
import net.polyv.live.entity.interact.LiveListLotteryResponse;
import net.polyv.live.entity.interact.LiveLotteryWinnerDetailRequest;
import net.polyv.live.entity.interact.LiveLotteryWinnerDetailResponse;
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
import net.polyv.live.entity.interact.LiveSetLotteryWinnerInfoRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.interact.impl.LiveQuestionnaireServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class LiveQuestionnaireServiceImplTest extends BaseTest {
    
    /**
     * 测试设置频道问卷信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetQuestionnaireDetailInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireDetailSetRequest liveQuestionnaireDetailSetRequest = new LiveQuestionnaireDetailSetRequest();
        LiveQuestionnaireDetailSetResponse liveQuestionnaireDetailSetResponse = null;
        Integer channelId = super.createChannel();
        //封装问卷请求对象
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
                .setOptions(Arrays.asList(new String[]{"篮球", "足球", "排球", "跑步", "羽毛球"}))
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
        liveQuestionnaireDetailSetResponse = new LiveQuestionnaireServiceImpl().setQuestionnaireDetailInfo(
                liveQuestionnaireDetailSetRequest);
        
        //判断结果
        Assert.assertNotNull(liveQuestionnaireDetailSetResponse);
        if (liveQuestionnaireDetailSetResponse != null) {
            //to do something ......
            log.debug("测试添加频道问卷成功{}", JSON.toJSONString(liveQuestionnaireDetailSetResponse));
        }
        
    }
    
    /**
     * 查询频道问卷列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireListInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireListRequest liveQuestionnaireListRequest = new LiveQuestionnaireListRequest();
        LiveQuestionnaireListResponse liveQuestionnaireListResponse = null;
        Integer channelId = super.createChannel();
        
        liveQuestionnaireListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
        liveQuestionnaireListResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireListInfo(
                liveQuestionnaireListRequest);
        Assert.assertNotNull(liveQuestionnaireListResponse);
        if (liveQuestionnaireListResponse != null) {
            //to do something ......
            log.debug("测试查询频道问卷列表成功{}", JSON.toJSONString(liveQuestionnaireListResponse));
        }
    }
    
    /**
     * 查询频道问卷详情
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireDetailInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireDetailRequest liveQuestionnaireDetailRequest = new LiveQuestionnaireDetailRequest();
        LiveQuestionnaireDetailResponse liveQuestionnaireDetailResponse = null;
        Integer channelId = super.createChannel();
        //获取详情
        liveQuestionnaireDetailRequest.setChannelId(channelId)
                .setQuestionnaireId("fs9skpv22f")
                .setRequestId(LiveSignUtil.generateUUID());
        liveQuestionnaireDetailResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireDetailInfo(
                liveQuestionnaireDetailRequest);
        Assert.assertNotNull(liveQuestionnaireDetailResponse);
        if (liveQuestionnaireDetailResponse != null) {
            //to do something ......
            log.debug("测试查询频道问卷详情成功{}", JSON.toJSONString(liveQuestionnaireDetailResponse));
        }
    }
    
    
    /**
     * 查询频道问卷结果
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionnaireResultInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireResultRequest liveQuestionnaireResultRequest = new LiveQuestionnaireResultRequest();
        List<LiveQuestionnaireResultResponse> liveQuestionnaireResultResponse = null;
        Integer channelId = super.createChannel();
        
        liveQuestionnaireResultRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
//        liveQuestionnaireResultRequest.setQuestionnaireId("fs9skpv22f");
        liveQuestionnaireResultResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireResultInfo(
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
        LiveQuestionnaireResultPageRequest liveQuestionnaireResultPageRequest =
                new LiveQuestionnaireResultPageRequest();
        LiveQuestionnaireResultPageResponse liveQuestionnaireResultPageResponse = null;
        Integer channelId = super.createChannel();
        liveQuestionnaireResultPageRequest.setChannelId(channelId)
                .setPageSize(20)
                .setCurrentPage(1)
                .setRequestId(LiveSignUtil.generateUUID());
        liveQuestionnaireResultPageResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireResultPageInfo(
                liveQuestionnaireResultPageRequest);
        Assert.assertNotNull(liveQuestionnaireResultPageRequest);
        if (liveQuestionnaireResultPageResponse != null) {
            //to do something ......
            log.debug("测试分页查询频道问卷结果成功{}", JSON.toJSONString(liveQuestionnaireResultPageResponse));
        }
    }
    
    /**
     * 测试获取频道抽奖记录列表
     * TODO 测试未通过
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testListLottery() throws IOException, NoSuchAlgorithmException {
        LiveListLotteryRequest liveListLotteryRequest = new LiveListLotteryRequest();
        LiveListLotteryResponse liveListLotteryResponse;
        liveListLotteryRequest.setChannelId(super.createChannel())
                .setStartTime(1601481600000l)
                .setEndTime(1605024000000l)
                .setPageSize(1);
        liveListLotteryResponse = new LiveQuestionnaireServiceImpl().listLottery(liveListLotteryRequest);
        Assert.assertNotNull(liveListLotteryResponse);
        if (liveListLotteryResponse != null) {
            //to do something ......
            log.debug("测试获取频道抽奖记录列表成功，{}", JSON.toJSONString(liveListLotteryResponse));
        }
    }
    
    /**
     * 测试获取频道单场抽奖的中奖记录
     * TODO 测试未通过
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testLotteryWinnerDetail() throws IOException, NoSuchAlgorithmException {
        LiveLotteryWinnerDetailRequest liveLotteryWinnerDetailRequest = new LiveLotteryWinnerDetailRequest();
        LiveLotteryWinnerDetailResponse liveLotteryWinnerDetailResponse;
        liveLotteryWinnerDetailRequest.setChannelId(super.createChannel()).setLotteryId("1211");
        liveLotteryWinnerDetailResponse = new LiveQuestionnaireServiceImpl().lotteryWinnerDetail(
                liveLotteryWinnerDetailRequest);
        Assert.assertNotNull(liveLotteryWinnerDetailResponse);
        if (liveLotteryWinnerDetailResponse != null) {
            //to do something ......
            log.debug("测试获取频道单场抽奖的中奖记录成功，{}", JSON.toJSONString(liveLotteryWinnerDetailResponse));
        }
    }
    
    /**
     * 测试设置抽奖中奖者信息
     * TODO 未通过测试
     * 描述：用于提交中奖者填写的信息
     * 约束：2.只能成功保存一次观众中奖信息
     * 约束：3.中奖信息需在7天内提交保存，否则会失效
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testSetLotteryWinnerInfo() throws IOException, NoSuchAlgorithmException {
        LiveSetLotteryWinnerInfoRequest liveSetLotteryWinnerInfoRequest = new LiveSetLotteryWinnerInfoRequest();
        Boolean liveSetLotteryWinnerInfoResponse;
        liveSetLotteryWinnerInfoRequest.setChannelId(super.createChannel())
                .setLotteryId("")
                .setWinnerCode("")
                .setViewerId("")
                .setName("")
                .setTelephone("")
                .setReceiveInfo("");
        liveSetLotteryWinnerInfoResponse = new LiveQuestionnaireServiceImpl().setLotteryWinnerInfo(liveSetLotteryWinnerInfoRequest);
        Assert.assertNotNull(liveSetLotteryWinnerInfoResponse);
        if (liveSetLotteryWinnerInfoResponse) {
            //to do something ......
            log.debug("测试设置抽奖中奖者信息成功");
        }
    }
    
}
