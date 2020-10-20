package net.polyv.live.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.interact.LiveCheckinListRequest;
import net.polyv.live.entity.interact.LiveCheckinListResponse;
import net.polyv.live.entity.interact.LiveCheckinMetadataBySessionIdRequest;
import net.polyv.live.entity.interact.LiveCheckinMetadataBySessionIdResponse;
import net.polyv.live.entity.interact.LiveCheckinRequest;
import net.polyv.live.entity.interact.LiveCheckinResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.interact.impl.LiveCheckinImpl;
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
        liveCheckinRequest.setChannelId(channelId).setCheckinId("d91a7c60-1299-11eb-8c65-c70c1c").setRequestId(LiveSignUtil.generateUUID());
        List<LiveCheckinResponse> liveCheckinResponse = new LiveCheckinImpl().getCheckinInfoById(liveCheckinRequest);
        Assert.assertNotNull(liveCheckinResponse);
        if (liveCheckinResponse != null) {
            //to do something ......
            log.debug("测试查询指定签到ID的签到记录成功{}", JSON.toJSONString(liveCheckinResponse));
        }
    }
    
   
    /**
     * 依据指定直播场次sessionId查询签到场次信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetCheckinMetadataBySessionId() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveCheckinMetadataBySessionIdRequest liveCheckinMetadataBySessionIdRequest = new LiveCheckinMetadataBySessionIdRequest();
        liveCheckinMetadataBySessionIdRequest.setChannelId(channelId).setSessionId("fs9v9y4nxf").setRequestId(LiveSignUtil.generateUUID());
        List<LiveCheckinMetadataBySessionIdResponse>  liveCheckinResponse = new LiveCheckinImpl().getCheckinMetadataBySessionId(liveCheckinMetadataBySessionIdRequest);
        Assert.assertNotNull(liveCheckinResponse);
        if (liveCheckinResponse != null) {
            //to do something ......
            log.debug("测试依据指定直播场次sessionId查询签到场次信息成功{}", JSON.toJSONString(liveCheckinResponse));
        }
    }
    
}
