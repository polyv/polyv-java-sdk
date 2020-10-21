package net.polyv.live.service.chat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.chat.LiveBadWordRequest;
import net.polyv.live.entity.chat.LiveBadWordResponse;
import net.polyv.live.entity.chat.LiveChatBannedIPRequest;
import net.polyv.live.entity.chat.LiveDelBannedDataRequest;
import net.polyv.live.entity.chat.LiveGetBadwordIPRequest;
import net.polyv.live.entity.chat.LiveGetBannedListRequest;
import net.polyv.live.entity.chat.LiveGetChatAdminResponse;
import net.polyv.live.entity.chat.LiveKickedListRequest;
import net.polyv.live.entity.chat.LiveKickedListResponse;
import net.polyv.live.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.entity.chat.LiveSetTeacherDataRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.chat.impl.LiveChatRoomImpl;
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
        liveSendChatMsgRequest.setChannelId(channelId)
                .setMsg("hello 大家好")
                .setPic("http://s1.videocc.net/default-img/avatar/teacher.png")
                .setNickName("thomas")
                .setRequestId(LiveSignUtil.generateUUID());
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
        liveSetTeacherDataRequest.setChannelId(channelId)
                .setNickname("thomas-gogo")
                .setActor("大师")
                .setPasswd("123456")
                .setAvatar(
                        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740,3965499425&fm=26&gp=0" +
                                ".jpg")
                .setRequestId(LiveSignUtil.generateUUID());
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
    
    /**
     * 批量导入频道严禁词
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAddBadWord() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveBadWordRequest liveBadWordRequest = new LiveBadWordRequest();
        liveBadWordRequest
//                .setChannelId(channelId)
                .setWords(Arrays.asList(new String[]{"你好1", "逗逼1", "傻子"}))
                .setRequestId(LiveSignUtil.generateUUID());
        LiveBadWordResponse liveBadWordResponse = new LiveChatRoomImpl().addBadWord(liveBadWordRequest);
        Assert.assertNotNull(liveBadWordResponse);
        if (liveBadWordResponse != null) {
            //to do something ......
            log.debug("测试批量导入频道严禁词成功{}", JSON.toJSONString(liveBadWordResponse));
        }
    }
    
    /**
     * 查询频道禁言列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetBannedList() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveGetBannedListRequest liveGetBannedListRequest = new LiveGetBannedListRequest();
        liveGetBannedListRequest.setChannelId(channelId)
                .setType(LiveConstant.BannedType.USER_ID.getType())
                .setRequestId(LiveSignUtil.generateUUID());
        List<String> result = new LiveChatRoomImpl().getBannedList(liveGetBannedListRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试查询频道禁言列表成功{}", JSON.toJSONString(result));
        }
    }
    
    
    /**
     * 查询频道踢人列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetKickedList() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveKickedListRequest liveKickedListRequest = new LiveKickedListRequest();
        liveKickedListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
        List<LiveKickedListResponse> result = new LiveChatRoomImpl().getKickedList(liveKickedListRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试查询频道踢人列表成功{}", JSON.toJSONString(result));
        }
    }
    
    
    /**
     * 删除禁言IP/严禁词
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDelBanned() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveDelBannedDataRequest liveDelBannedDataRequest = new LiveDelBannedDataRequest();
        liveDelBannedDataRequest.setContent("192.168.1.3")
                .setChannelId(channelId)
                .setType(LiveConstant.BannedType.IP.getType())
                .setRequestId(LiveSignUtil.generateUUID());
        String result = new LiveChatRoomImpl().delBanned(liveDelBannedDataRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试删除禁言IP/严禁词成功{}", JSON.toJSONString(result));
        }
    }
    
    
    
    
    /**
     * 查询频道严禁词/禁言IP列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetBadworkList() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        LiveGetBadwordIPRequest liveGetBadwordIPRequest = new LiveGetBadwordIPRequest();
        liveGetBadwordIPRequest.setChannelId(channelId).setType(LiveConstant.BannedType.BADWORD.getType()).setRequestId(LiveSignUtil.generateUUID());
        List<String> result = new LiveChatRoomImpl().getBadworkList(liveGetBadwordIPRequest);
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试查询频道严禁词/禁言IP列表成功{}", JSON.toJSONString(result));
        }
    }
    
    
    /**
     * 查询账号严禁词列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetAccountBadworkList() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        List<String> result = new LiveChatRoomImpl().getAccountBadworkList(LiveSignUtil.generateUUID());
        Assert.assertNotNull(result);
        if (result != null) {
            //to do something ......
            log.debug("测试查询账号严禁词列表成功{}", JSON.toJSONString(result));
        }
    }
    
    
    
//    /**
//     * 删除频道聊天记录
//     * @throws IOException
//     * @throws NoSuchAlgorithmException
//     */
//    @Test
//    public void testCleanChannelAllMsg() throws IOException, NoSuchAlgorithmException {
//        Integer channelId = super.createChannel();
//        String requestId = LiveSignUtil.generateUUID();
//        String result = new LiveChatRoomImpl().cleanChannelAllMsg(channelId,requestId);
//        Assert.assertNotNull(result);
//        if (result != null) {
//            //to do something ......
//            log.debug("测试删除频道聊天记录成功{}", JSON.toJSONString(result));
//        }
//    }
    
    
    
    
    /**
     * 查询聊天室管理员信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChatAdminData() throws IOException, NoSuchAlgorithmException {
        Integer channelId = super.createChannel();
        String requestId = LiveSignUtil.generateUUID();
        LiveGetChatAdminResponse liveGetChatAdminResponse = new LiveChatRoomImpl().getChatAdminData(channelId,requestId);
        Assert.assertNotNull(liveGetChatAdminResponse);
        if (liveGetChatAdminResponse != null) {
            //to do something ......
            log.debug("测试查询聊天室管理员信息成功{}", JSON.toJSONString(liveGetChatAdminResponse));
        }
    }
    
}
