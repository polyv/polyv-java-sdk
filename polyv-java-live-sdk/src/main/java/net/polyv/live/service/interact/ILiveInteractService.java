package net.polyv.live.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.interact.LiveQuestionnaireDetailRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireDetailResponse;
import net.polyv.live.entity.interact.LiveQuestionnaireDetailSetRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireDetailSetResponse;
import net.polyv.live.entity.interact.LiveQuestionnaireListRequest;
import net.polyv.live.entity.interact.LiveQuestionnaireListResponse;

/**
 * 直播频道管理
 * @author: thomas
 **/
public interface ILiveInteractService {
    
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
    LiveQuestionnaireDetailSetResponse setQuestionnaireDetailInfo(LiveQuestionnaireDetailSetRequest liveQuestionDetailSetRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
