package net.polyv.live.v1.service.interact.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.constant.LiveURL;
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
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.interact.ILiveQuestionnaireService;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 直播互动管理
 * @author: thomas
 **/
@Slf4j
public class LiveQuestionnaireServiceImpl extends LiveBaseService implements ILiveQuestionnaireService {
    
    /**
     * 查询频道问卷详情，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/get-questionnaire-detail/
     * @param liveQuestionnaireDetailRequest 频道问卷详情请求实体
     * @return 频道问卷详情响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveQuestionnaireDetailResponse getQuestionnaireDetailInfo(
            LiveQuestionnaireDetailRequest liveQuestionnaireDetailRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_QUESTIONNAIRE_DETAIL_URL;
        LiveQuestionnaireDetailResponse LiveQuestionnaireDetailResponse = this.postFormBodyReturnOne(url,
                liveQuestionnaireDetailRequest, LiveQuestionnaireDetailResponse.class);
        return LiveQuestionnaireDetailResponse;
    }
    
    /**
     * 查询频道问卷列表 ,API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/list-questionaire/
     * @param liveQuestionnaireListRequest 查询频道问卷列表请求实体
     * @return 查询频道问卷列表响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveQuestionnaireListResponse getQuestionnaireListInfo(
            LiveQuestionnaireListRequest liveQuestionnaireListRequest) throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_QUESTIONNAIRE_LIST_URL;
        return this.postFormBodyReturnOne(url, liveQuestionnaireListRequest,
                LiveQuestionnaireListResponse.class);
      
    }
    
    /**
     * 设置频道问卷信息，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/add-edit-questionnaire/
     * @param liveQuestionnaireDetailSetRequest 设置频道问卷信息请求实体
     * @return 设置频道问卷信息响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    @Override
    public LiveQuestionnaireDetailSetResponse setQuestionnaireDetailInfo(
            LiveQuestionnaireDetailSetRequest liveQuestionnaireDetailSetRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_QUESTIONNAIRE_DETAIL_SET_URL;
        Map<String, String> signMap = LiveSignUtil.getSignMap(liveQuestionnaireDetailSetRequest);
        signMap.put("channelId", liveQuestionnaireDetailSetRequest.getChannelId());
        return this.postJsonBodyReturnOne(url, signMap,
                liveQuestionnaireDetailSetRequest, LiveQuestionnaireDetailSetResponse.class);
     
    }
    
    /**
     * 查询频道问卷结果，API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbhd/questionnaire/
     * @param liveQuestionnaireResultRequest 查询频道问卷结果请求实体
     * @return 查询频道问卷结果响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    @Override
    public List<LiveQuestionnaireResultResponse> getQuestionnaireResultInfo(
            LiveQuestionnaireResultRequest liveQuestionnaireResultRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_QUESTIONNAIRE_ANSWER_RECORD_URL;
        return this.getReturnList(url,
                liveQuestionnaireResultRequest, LiveQuestionnaireResultResponse.class);
        
    }
    
    /**
     * 分页查询问卷结果，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/list-questionnaire-by-page/
     * @param liveQuestionnaireResultPageRequest 分页查询问卷结果请求实体
     * @return 分页查询频道问卷结果响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    @Override
    public LiveQuestionnaireResultPageResponse getQuestionnaireResultPageInfo(
            LiveQuestionnaireResultPageRequest liveQuestionnaireResultPageRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_QUESTIONNAIRE_ANSWER_RECORD_PAGE_URL;
        return this.getReturnOne(url,
                liveQuestionnaireResultPageRequest, LiveQuestionnaireResultPageResponse.class);
        
    }
    
}
