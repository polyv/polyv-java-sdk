package net.polyv.live.v1.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.live.v1.entity.interact.LiveQuestionAnswerRecordRequest;
import net.polyv.live.v1.entity.interact.LiveQuestionAnswerRecordResponse;

/**
 * 直播签到管理
 * @author: thomas
 **/
public interface ILiveAnswerRecordService {
    
    /**
     * 查询频道答题卡答题结果，API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbhd/answer_records/
     * @param liveQuestionAnswerRecordRequest 查询频道答题卡答题结果请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<LiveQuestionAnswerRecordResponse> getAnswerRecord(
            LiveQuestionAnswerRecordRequest liveQuestionAnswerRecordRequest)
            throws IOException, NoSuchAlgorithmException;
     
}
