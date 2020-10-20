package net.polyv.live.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.interact.LiveCheckinListRequest;
import net.polyv.live.entity.interact.LiveCheckinListResponse;
import net.polyv.live.entity.interact.LiveCheckinRequest;
import net.polyv.live.entity.interact.LiveCheckinResponse;
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
import net.polyv.live.service.interact.impl.LiveCheckinImpl;
import net.polyv.live.service.interact.impl.LiveInteractImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class LiveCheckinImplTest extends BaseTest {
    
    
    /**
     * 查询签到结果
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetCheckinListInfo() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveCheckinListRequest liveCheckinListRequest = new LiveCheckinListRequest();
        liveCheckinListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
//        liveCheckinListRequest.setDate("2020-10-20").setSessionId("fs9v9y4nxf");
        LiveCheckinListResponse checkinListInfo = new LiveCheckinImpl().getCheckinListInfo(liveCheckinListRequest);
        Assert.assertNotNull(checkinListInfo);
        if (checkinListInfo != null) {
            //to do something ......
            log.debug("测试查询签到结果成功{}", JSON.toJSONString(checkinListInfo));
        }
    }
    
    /**
     * 查询指定签到ID的签到记录
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetCheckinInfoById() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveCheckinRequest liveCheckinRequest = new LiveCheckinRequest();
        liveCheckinRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
        LiveCheckinResponse liveCheckinResponse = new LiveCheckinImpl().getCheckinInfoById(liveCheckinRequest);
        Assert.assertNotNull(liveCheckinResponse);
        if (liveCheckinResponse != null) {
            //to do something ......
            log.debug("测试查询指定签到ID的签到记录成功{}", JSON.toJSONString(liveCheckinResponse));
        }
    }
    
}
