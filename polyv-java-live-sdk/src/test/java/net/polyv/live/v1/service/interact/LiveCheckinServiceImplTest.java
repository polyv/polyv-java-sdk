package net.polyv.live.v1.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.interact.LiveCheckinListRequest;
import net.polyv.live.v1.entity.interact.LiveCheckinListResponse;
import net.polyv.live.v1.entity.interact.LiveCheckinMetadataBySessionIdRequest;
import net.polyv.live.v1.entity.interact.LiveCheckinMetadataBySessionIdResponse;
import net.polyv.live.v1.entity.interact.LiveCheckinRequest;
import net.polyv.live.v1.entity.interact.LiveCheckinResponse;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.interact.impl.LiveCheckinServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 签到管理
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
    public void testGetCheckinListInfo() throws Exception, NoSuchAlgorithmException {
        LiveCheckinListRequest liveCheckinListRequest = new LiveCheckinListRequest();
        LiveCheckinListResponse liveCheckinListResponse = null;
        try {
            String channelId = super.createChannel();
            
            liveCheckinListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveCheckinListRequest.setDate(getDate(2020,10,20)).setSessionId("fs9v9y4nxf");
            liveCheckinListResponse = new LiveCheckinServiceImpl().getCheckinListInfo(liveCheckinListRequest);
            Assert.assertNotNull(liveCheckinListResponse);
            if (liveCheckinListResponse != null) {
                //to do something ......
                log.debug("测试查询签到结果成功{}", JSON.toJSONString(liveCheckinListResponse));
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
     * 描述：通过签到ID获取该次签到记录
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetCheckinInfoById() throws Exception, NoSuchAlgorithmException {
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
     * 描述：通过直播场次sessionId获取直播发起签到记录
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetCheckinMetadataBySessionId() throws Exception, NoSuchAlgorithmException {
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
    
    /**
     * 测试用例结束
     */
}
