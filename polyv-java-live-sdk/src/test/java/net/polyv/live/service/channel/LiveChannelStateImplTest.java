package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.entity.channel.state.LiveChannelStreamInfoRequest;
import net.polyv.live.entity.channel.state.LiveChannelStreamInfoResponse;
import net.polyv.live.entity.channel.state.LiveCutoffChannelStreamRequest;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusRequest;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusResponse;
import net.polyv.live.entity.channel.state.LiveResumeChannelStreamRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.channel.impl.LiveChannelStateServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * 直播状态
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelStateImplTest extends BaseTest {
    
    /**
     * 测试查询频道实时推流信息
     * 约束：2、讲师未进入直播间或未开启上课等情况，将抛出"channel status not live"异常
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testChannelStreamInfo() throws Exception, NoSuchAlgorithmException {
        LiveChannelStreamInfoRequest liveChannelStreamInfoRequest = new LiveChannelStreamInfoRequest();
        LiveChannelStreamInfoResponse liveChannelStreamInfoResponse;
        try {
            liveChannelStreamInfoRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelStreamInfoResponse = new LiveChannelStateServiceImpl().channelStreamInfo(
                    liveChannelStreamInfoRequest);
            Assert.assertNotNull(liveChannelStreamInfoResponse);
            if (liveChannelStreamInfoResponse != null) {
                //to do something ......
                log.debug("批量查询频道直播流状态成功{}", JSON.toJSONString(liveChannelStreamInfoResponse));
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
     * 批量查询频道直播流状态
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelLiveStream() throws Exception, NoSuchAlgorithmException {
        LiveListChannelStreamStatusRequest liveListChannelStreamStatusRequest =
                new LiveListChannelStreamStatusRequest();
        LiveListChannelStreamStatusResponse liveListChannelStreamStatusResponse;
        try {
            //准备测试数据
            String channelId0 = createChannel();
            String channelId1 = getAloneChannelId();
            
            liveListChannelStreamStatusRequest.setChannelIds(String.format("%s,%s", channelId0, channelId1))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelStreamStatusResponse = new LiveChannelStateServiceImpl().listChannelLiveStream(
                    liveListChannelStreamStatusRequest);
            Assert.assertNotNull(liveListChannelStreamStatusResponse);
            if (liveListChannelStreamStatusResponse != null) {
                //to do something ......
                log.debug("批量查询频道直播流状态成功{}", JSON.toJSONString(liveListChannelStreamStatusResponse));
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
     * 禁止直播频道推流
     * 返回：true为禁止推流成功，false为失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCutoffChannelStream() throws Exception, NoSuchAlgorithmException {
        LiveCutoffChannelStreamRequest liveCutoffChannelStreamRequest = new LiveCutoffChannelStreamRequest();
        Boolean liveCutoffChannelStreamResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveCutoffChannelStreamRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveCutoffChannelStreamResponse = new LiveChannelStateServiceImpl().cutoffChannelStream(
                    liveCutoffChannelStreamRequest);
            Assert.assertNotNull(liveCutoffChannelStreamResponse);
            if (liveCutoffChannelStreamResponse) {
                //to do something ......
                log.debug("禁止直播频道推流成功");
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
     * 测试恢复直播频道推流
     * 返回：true为恢复成功，false为恢复失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testResumeChannelStream() throws Exception, NoSuchAlgorithmException {
        LiveResumeChannelStreamRequest liveResumeChannelStreamRequest = new LiveResumeChannelStreamRequest();
        Boolean liveResumeChannelStreamResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveResumeChannelStreamRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveResumeChannelStreamResponse = new LiveChannelStateServiceImpl().resumeChannelStream(
                    liveResumeChannelStreamRequest);
            Assert.assertNotNull(liveResumeChannelStreamResponse);
            if (liveResumeChannelStreamResponse) {
                //to do something ......
                log.debug("恢复直播频道推流成功");
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
