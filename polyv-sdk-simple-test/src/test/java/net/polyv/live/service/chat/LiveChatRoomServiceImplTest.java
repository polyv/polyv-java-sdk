package net.polyv.live.service.chat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.chat.impl.LiveChatRoomServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * 聊天室
 * @author: thomas
 **/
@Slf4j
public class LiveChatRoomServiceImplTest extends BaseTest {
    
    /**
     * 通过HTTP接口发送聊天消息
     * 描述：可指定发言者的头像、头衔、昵称，无需连接聊天室，通过HTTP接口发送聊天文本内容
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSendChatMsg() throws IOException, NoSuchAlgorithmException {
        LiveSendChatMsgRequest liveSendChatMsgRequest = new LiveSendChatMsgRequest();
        LiveSendChatMsgResponse liveSendChatMsgResponse = null;
        try {
            String channelId = super.createChannel();
            liveSendChatMsgRequest.setChannelId(channelId)
                    .setMsg("hello 大家好-通过API发过来的测试信息")
                    .setPic("https://5b0988e595225.cdn.sohucs.com/q_70,c_zoom," +
                            "w_640/images/20190129/e3b0d6311b1a411fa68125fc03b8ef67.jpeg")
                    .setNickName("thomas")
                    .setFreeReview(LiveConstant.Flag.YES.getFlag())
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSendChatMsgResponse = new LiveChatRoomServiceImpl().sendChatMsg(liveSendChatMsgRequest);
            Assert.assertNotNull(liveSendChatMsgResponse);
            if (liveSendChatMsgResponse != null) {
                //to do something ......
                log.debug("测试通过HTTP接口发送聊天消息成功,消息ID {}", liveSendChatMsgResponse.getMsgId());
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
