package net.polyv.live.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.live.entity.interact.LiveListLotteryRequest;
import net.polyv.live.entity.interact.LiveListLotteryResponse;
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

/**
 * 直播互动管理
 * @author: thomas
 **/
public interface ILiveQuestionnaireService {
    
    /**
     * 查询频道问卷详情，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/get-questionnaire-detail/
     * @param liveQuestionDetailRequest 频道问卷详情请求实体
     * @return 频道问卷详情响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveQuestionnaireDetailResponse getQuestionnaireDetailInfo(LiveQuestionnaireDetailRequest liveQuestionDetailRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道问卷列表 ,API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/list-questionaire/
     * @param liveQuestionListRequest 查询频道问卷列表请求实体
     * @return 查询频道问卷列表响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveQuestionnaireListResponse getQuestionnaireListInfo(LiveQuestionnaireListRequest liveQuestionListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    
    /**
     * 设置频道问卷信息，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/add-edit-questionnaire/
     * @param liveQuestionDetailSetRequest 设置频道问卷信息请求实体
     * @return 设置频道问卷信息响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    LiveQuestionnaireDetailSetResponse setQuestionnaireDetailInfo(
            LiveQuestionnaireDetailSetRequest liveQuestionDetailSetRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道问卷结果，API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbhd/questionnaire/
     * @param liveQuestionnaireResultRequest 查询频道问卷结果请求实体
     * @return 查询频道问卷结果响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    List<LiveQuestionnaireResultResponse> getQuestionnaireResultInfo(
            LiveQuestionnaireResultRequest liveQuestionnaireResultRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 分页查询频道问卷结果，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/list-questionnaire-by-page/
     * @param liveQuestionnaireResultPageRequest 分页查询频道问卷结果请求实体
     * @return 分页查询频道问卷结果响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    LiveQuestionnaireResultPageResponse getQuestionnaireResultPageInfo(
            LiveQuestionnaireResultPageRequest liveQuestionnaireResultPageRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取频道抽奖记录列表
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbhd/list-lottery/
     * @param liveListLotteryRequest 获取频道抽奖记录列表请求实体
     * @return 获取频道抽奖记录列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListLotteryResponse listLottery(LiveListLotteryRequest liveListLotteryRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
