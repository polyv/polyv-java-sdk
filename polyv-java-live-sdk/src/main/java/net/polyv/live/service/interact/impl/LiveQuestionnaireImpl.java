package net.polyv.live.service.interact.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
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
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.interact.ILiveQuestionnaireService;
import net.polyv.live.util.LiveSignUtil;

/**
 * 直播互动管理
 * @author: thomas
 **/
@Slf4j
public class LiveQuestionnaireImpl extends LiveBaseService implements ILiveQuestionnaireService {
    
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
        LiveQuestionnaireDetailResponse LiveQuestionnaireDetailResponse = this.basePost(url,
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
        LiveQuestionnaireListResponse liveQuestionnarieListResponse = this.basePost(url, liveQuestionnaireListRequest,
                LiveQuestionnaireListResponse.class);
        return liveQuestionnarieListResponse;
    }
    /**
     * 获取签名字段
     * @param liveQuestionnaireDetailSetRequest signmap 来源
     * @return 签名字段MAP
     */
    private Map<String, String> getSignMap( LiveQuestionnaireDetailSetRequest liveQuestionnaireDetailSetRequest) {
        if (StringUtils.isBlank(liveQuestionnaireDetailSetRequest.getRequestId())) {
            liveQuestionnaireDetailSetRequest.setRequestId(LiveSignUtil.generateUUID());
        }
        
        liveQuestionnaireDetailSetRequest.setAppId(LiveGlobalConfig.APP_ID);
        if (StringUtils.isBlank(liveQuestionnaireDetailSetRequest.getTimestamp())) {
            liveQuestionnaireDetailSetRequest.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put("appId", liveQuestionnaireDetailSetRequest.getAppId());
        tempMap.put("timestamp", liveQuestionnaireDetailSetRequest.getTimestamp());
        tempMap.put("requestId", liveQuestionnaireDetailSetRequest.getRequestId());
        tempMap.put("channelId", liveQuestionnaireDetailSetRequest.getChannelId().toString());
        return tempMap;
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
        Map<String, String> signMap = getSignMap(liveQuestionnaireDetailSetRequest);
        LiveQuestionnaireDetailSetResponse liveQuestionnaireDetailSetResponse = this.basePostJson(url,signMap,
                liveQuestionnaireDetailSetRequest, LiveQuestionnaireDetailSetResponse.class);
        return liveQuestionnaireDetailSetResponse;
    }
    /**
     * 查询频道问卷结果，API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbhd/questionnaire/
     * @param liveQuestionnaireResultRequest 查询频道问卷结果请求实体
     * @return 查询频道问卷结果响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    @Override
    public List<LiveQuestionnaireResultResponse>  getQuestionnaireResultInfo(
            LiveQuestionnaireResultRequest liveQuestionnaireResultRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_QUESTIONNAIRE_ANSWER_RECORD_URL;
        List<LiveQuestionnaireResultResponse> liveQuestionnaireResultResponse = this.baseGetReturnArray(url,
                liveQuestionnaireResultRequest, LiveQuestionnaireResultResponse.class);
        return liveQuestionnaireResultResponse;
    }
    
    /**
     * 分页查询频道问卷结果，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/list-questionnaire-by-page/
     * @param liveQuestionnaireResultPageRequest 分页查询频道问卷结果请求实体
     * @return 分页查询频道问卷结果响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    @Override
    public LiveQuestionnaireResultPageResponse getQuestionnaireResultPageInfo(
            LiveQuestionnaireResultPageRequest liveQuestionnaireResultPageRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_QUESTIONNAIRE_ANSWER_RECORD_PAGE_URL;
        LiveQuestionnaireResultPageResponse liveQuestionnaireResultPageResponse = this.baseGet(url,
                liveQuestionnaireResultPageRequest, LiveQuestionnaireResultPageResponse.class);
        return liveQuestionnaireResultPageResponse;
    }
    
    
}
