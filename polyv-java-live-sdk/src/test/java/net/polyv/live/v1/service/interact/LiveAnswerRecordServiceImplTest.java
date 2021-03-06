package net.polyv.live.v1.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.interact.LiveQuestionAnswerRecordRequest;
import net.polyv.live.v1.entity.interact.LiveQuestionAnswerRecordResponse;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.interact.impl.LiveAnswerRecordServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 答题卡管理
 * @author: thomas
 **/
@Slf4j
public class LiveAnswerRecordServiceImplTest extends BaseTest {
    
    
    /**
     * 查询频道答题卡答题结果
     * API地址：CHANNEL_QUESTION_ANSWER_RECORD_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetAnswerRecord() throws Exception, NoSuchAlgorithmException {
        LiveQuestionAnswerRecordRequest liveQuestionAnswerRecordRequest = new LiveQuestionAnswerRecordRequest();
        List<LiveQuestionAnswerRecordResponse> liveCheckinResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionAnswerRecordRequest.setChannelId(channelId);
            liveQuestionAnswerRecordRequest.setStartDate(getDate(2020, 10, 01)).setEndDate(getDate(2099, 10, 01));
            liveCheckinResponse = new LiveAnswerRecordServiceImpl().getAnswerRecord(liveQuestionAnswerRecordRequest);
            Assert.assertNotNull(liveCheckinResponse);
            if (liveCheckinResponse != null) {
                //to do something ......
                log.debug("测试查询频道答题卡答题结果成功{}", JSON.toJSONString(liveCheckinResponse));
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
