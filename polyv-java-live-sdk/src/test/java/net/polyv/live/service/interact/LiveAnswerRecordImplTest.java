package net.polyv.live.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ser.Serializers;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.interact.LiveCheckinRequest;
import net.polyv.live.entity.interact.LiveCheckinResponse;
import net.polyv.live.entity.interact.LiveQuestionAnswerRecordRequest;
import net.polyv.live.entity.interact.LiveQuestionAnswerRecordResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.interact.ILiveAnswerRecordService;
import net.polyv.live.service.interact.impl.LiveAnswerRecordImpl;
import net.polyv.live.service.interact.impl.LiveCheckinImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * 直播互动管理
 * @author: thomas
 **/
@Slf4j
public class LiveAnswerRecordImplTest extends BaseTest {
    
    /**
     * 查询频道答题卡答题结果
     */
    @Test
    public void testGetAnswerRecord() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveQuestionAnswerRecordRequest liveQuestionAnswerRecordRequest = new LiveQuestionAnswerRecordRequest();
        liveQuestionAnswerRecordRequest.setChannelId(channelId).setRequestId(
                LiveSignUtil.generateUUID());
        List<LiveQuestionAnswerRecordResponse> liveCheckinResponse = new LiveAnswerRecordImpl().getAnswerRecord(liveQuestionAnswerRecordRequest);
        Assert.assertNotNull(liveCheckinResponse);
        if (liveCheckinResponse != null) {
            //to do something ......
            log.debug("测试查询频道答题卡答题结果成功{}", JSON.toJSONString(liveCheckinResponse));
        }
    }
}
