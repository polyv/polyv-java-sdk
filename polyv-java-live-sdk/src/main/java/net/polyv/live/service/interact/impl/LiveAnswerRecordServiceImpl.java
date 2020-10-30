package net.polyv.live.service.interact.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.interact.LiveQuestionAnswerRecordRequest;
import net.polyv.live.entity.interact.LiveQuestionAnswerRecordResponse;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.interact.ILiveAnswerRecordService;

/**
 * 直播互动管理
 * @author: thomas
 **/
@Slf4j
public class LiveAnswerRecordServiceImpl extends LiveBaseService implements ILiveAnswerRecordService {
    
    /**
     * 查询频道答题卡答题结果，API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbhd/answer_records/
     * @param liveQuestionAnswerRecordRequest 查询频道答题卡答题结果请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<LiveQuestionAnswerRecordResponse> getAnswerRecord(
            LiveQuestionAnswerRecordRequest liveQuestionAnswerRecordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_QUESTION_ANSWER_RECORD_URL;
        return super.baseGetReturnArray(url,liveQuestionAnswerRecordRequest,LiveQuestionAnswerRecordResponse.class);
    }
}
