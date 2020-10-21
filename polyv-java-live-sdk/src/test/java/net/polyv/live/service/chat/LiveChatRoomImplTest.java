package net.polyv.live.service.chat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.chat.LiveChatBannedIPRequest;
import net.polyv.live.entity.chat.LiveChatBannedIPResponse;
import net.polyv.live.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.entity.chat.LiveSetTeacherDataRequest;
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
    
 
    
    
    /**
     * 设置讲师信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSendChatMsg() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveSetTeacherDataRequest liveSetTeacherDataRequest = new LiveSetTeacherDataRequest();
        liveSetTeacherDataRequest.setChannelId(channelId).setNickname("thomas-gogo").setActor("大师").setPasswd("34523423432432432532543524324324324324324324326789").setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740,3965499425&fm=26&gp=0.jpg").setRequestId(LiveSignUtil.generateUUID());
        String result = new LiveChatRoomImpl().sendChatMsg(liveSetTeacherDataRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试设置讲师信息成功{}", JSON.toJSONString(result));
        }
    }
    
    /**
     * 设置聊天室禁言ip
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAddBannedIP() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveChatBannedIPRequest liveChatBannedIPRequest = new LiveChatBannedIPRequest();
        liveChatBannedIPRequest.setIp("192.168.1.3").setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
        List<String> bannedIPList = new LiveChatRoomImpl().addBannedIP(liveChatBannedIPRequest);
        Assert.assertNotNull(bannedIPList);
        if (bannedIPList != null) {
            //to do something ......
            log.debug("测试设置聊天室禁言ip成功{}", JSON.toJSONString(bannedIPList));
        }
    }
    
    
    
}
