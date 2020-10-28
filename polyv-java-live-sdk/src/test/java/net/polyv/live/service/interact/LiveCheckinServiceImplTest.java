package net.polyv.live.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.entity.interact.LiveCheckinListRequest;
import net.polyv.live.entity.interact.LiveCheckinListResponse;
import net.polyv.live.entity.interact.LiveCheckinMetadataBySessionIdRequest;
import net.polyv.live.entity.interact.LiveCheckinMetadataBySessionIdResponse;
import net.polyv.live.entity.interact.LiveCheckinRequest;
import net.polyv.live.entity.interact.LiveCheckinResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.interact.impl.LiveCheckinServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class LiveCheckinServiceImplTest extends BaseTest {
    
    
    /**
     * 查询签到结果
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetCheckinListInfo() throws IOException, NoSuchAlgorithmException {
        LiveCheckinListRequest liveCheckinListRequest = new LiveCheckinListRequest();
        LiveCheckinListResponse checkinListInfo = null;
        try {
            String channelId = super.createChannel();
            
            liveCheckinListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
//        liveCheckinListRequest.setDate("2020-10-20").setSessionId("fs9v9y4nxf");
            checkinListInfo = new LiveCheckinServiceImpl().getCheckinListInfo(liveCheckinListRequest);
            Assert.assertNotNull(checkinListInfo);
            if (checkinListInfo != null) {
                //to do something ......
                log.debug("测试查询签到结果成功{}", JSON.toJSONString(checkinListInfo));
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
     * 查询指定签到ID的签到记录
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetCheckinInfoById() throws IOException, NoSuchAlgorithmException {
        LiveCheckinRequest liveCheckinRequest = new LiveCheckinRequest();
        List<LiveCheckinResponse> liveCheckinResponse = null;
        try {
            String channelId = super.createChannel();
            liveCheckinRequest.setChannelId(channelId)
                    .setCheckinId("d91a7c60-1299-11eb-8c65-c70c1c")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCheckinResponse = new LiveCheckinServiceImpl().getCheckinInfoById(liveCheckinRequest);
            Assert.assertNotNull(liveCheckinResponse);
            if (liveCheckinResponse != null) {
                //to do something ......
                log.debug("测试查询指定签到ID的签到记录成功{}", JSON.toJSONString(liveCheckinResponse));
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
     * 依据指定直播场次sessionId查询签到场次信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetCheckinMetadataBySessionId() throws IOException, NoSuchAlgorithmException {
        LiveCheckinMetadataBySessionIdRequest liveCheckinMetadataBySessionIdRequest =
                new LiveCheckinMetadataBySessionIdRequest();
        List<LiveCheckinMetadataBySessionIdResponse> liveCheckinResponse = null;
        try {
            String channelId = super.createChannel();
            
            liveCheckinMetadataBySessionIdRequest.setChannelId(channelId)
                    .setSessionId("fs9v9y4nxf")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCheckinResponse = new LiveCheckinServiceImpl().getCheckinMetadataBySessionId(
                    liveCheckinMetadataBySessionIdRequest);
            Assert.assertNotNull(liveCheckinResponse);
            if (liveCheckinResponse != null) {
                //to do something ......
                log.debug("测试依据指定直播场次sessionId查询签到场次信息成功{}", JSON.toJSONString(liveCheckinResponse));
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
    
}
