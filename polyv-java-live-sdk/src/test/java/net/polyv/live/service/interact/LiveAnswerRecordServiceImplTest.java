package net.polyv.live.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.entity.interact.LiveQuestionAnswerRecordRequest;
import net.polyv.live.entity.interact.LiveQuestionAnswerRecordResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.interact.impl.LiveAnswerRecordServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * 直播互动管理
 * @author: thomas
 **/
@Slf4j
public class LiveAnswerRecordServiceImplTest extends BaseTest {
    
    /**
     * 查询频道答题卡答题结果
     */
    @Test
    public void testGetAnswerRecord() throws IOException, NoSuchAlgorithmException {
        LiveQuestionAnswerRecordRequest liveQuestionAnswerRecordRequest = new LiveQuestionAnswerRecordRequest();
        List<LiveQuestionAnswerRecordResponse> liveCheckinResponse = null;
        try {
            Integer channelId = super.createChannel();
            liveQuestionAnswerRecordRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveCheckinResponse = new LiveAnswerRecordServiceImpl().getAnswerRecord(liveQuestionAnswerRecordRequest);
            Assert.assertNotNull(liveCheckinResponse);
            if (liveCheckinResponse != null) {
                //to do something ......
                log.debug("测试查询频道答题卡答题结果成功{}", JSON.toJSONString(liveCheckinResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
}
