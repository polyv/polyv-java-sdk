package net.polyv.live.service.chat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.entity.interact.LiveCheckinRequest;
import net.polyv.live.entity.interact.LiveCheckinResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.chat.impl.LiveChatRoomImpl;
import net.polyv.live.service.interact.impl.LiveCheckinImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class LiveChatRoomImplTest extends BaseTest {
   
    /**
     * 通过HTTP接口发送聊天消息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetCheckinInfoById() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveSendChatMsgRequest liveSendChatMsgRequest = new LiveSendChatMsgRequest();
        liveSendChatMsgRequest.setChannelId(channelId).setMsg("hello 大家好").setPic("http://s1.videocc.net/default-img/avatar/teacher.png").setNickName("thomas").setRequestId(LiveSignUtil.generateUUID());
        LiveSendChatMsgResponse liveSendChatMsgResponse = new LiveChatRoomImpl().sendChatMsg(liveSendChatMsgRequest);
        Assert.assertNotNull(liveSendChatMsgResponse);
        if (liveSendChatMsgResponse != null) {
            //to do something ......
            log.debug("测试通过HTTP接口发送聊天消息成功{}", JSON.toJSONString(liveSendChatMsgResponse));
        }
    }
}
