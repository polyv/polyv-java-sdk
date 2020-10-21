package net.polyv.live.service.chat.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.chat.LiveBadWordRequest;
import net.polyv.live.entity.chat.LiveBadWordResponse;
import net.polyv.live.entity.chat.LiveChatBannedIPRequest;
import net.polyv.live.entity.chat.LiveGetBannedListRequest;
import net.polyv.live.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.entity.chat.LiveSetTeacherDataRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.chat.ILiveChatRoomService;

/**
 * 直播签到管理
 * @author: thomas
 **/
@Slf4j
public class LiveChatRoomImpl extends LiveBaseService implements ILiveChatRoomService {
    
    /**
     * 通过HTTP接口发送聊天消息请求实体，API地址：https://dev.polyv.net/2019/liveproduct/zblts/send-admin-msg/
     * @param liveSendChatMsgRequest 查询签到结果请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveSendChatMsgResponse sendChatMsg(LiveSendChatMsgRequest liveSendChatMsgRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHAT_SEND_MSG_URL;
        return super.basePost(url, liveSendChatMsgRequest, LiveSendChatMsgResponse.class);
        
    }
    
    /**
     * 设置讲师信息，API地址：https://dev.polyv.net/2019/liveproduct/zblts/update-channel-teacher/
     * @param liveSetTeacherDataRequest 设置讲师信息请求实体
     * @return 设置成功返回 "true" , 设置失败抛出异常 BussinessExcepiton
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String sendChatMsg(LiveSetTeacherDataRequest liveSetTeacherDataRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHAT_SET_TEACHER_URL;
        return super.basePost(url, liveSetTeacherDataRequest, String.class);
    }
    
    /**
     * 设置聊天室禁言ip，API地址：https://dev.polyv.net/2017/liveproduct/zblts/addbannedip/
     * @param liveChatBannedIPRequest 设置聊天室禁言ip请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<String> addBannedIP(LiveChatBannedIPRequest liveChatBannedIPRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_BANNED_IP_URL, liveChatBannedIPRequest.getChannelId());
        return super.basePost(url, liveChatBannedIPRequest, List.class);
    }
    
    /**
     * 批量导入频道严禁词，API地址：https://dev.polyv.net/2017/liveproduct/zblts/addforbiddenwords/
     * @param liveBadWordRequest 批量导入频道严禁词请求实体
     * @return  响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveBadWordResponse addBadWord(LiveBadWordRequest liveBadWordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_SET_BAD_WORD_URL, LiveGlobalConfig.USER_ID);
        return super.basePost(url, liveBadWordRequest, LiveBadWordResponse.class);
    }
    /**
     * 查询频道禁言列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/get-banned-list/
     * @param liveGetBannedListRequest 查询频道禁言列表请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<String> getBannedList(LiveGetBannedListRequest liveGetBannedListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHAT_GET_CHANNEL_BANNED_LIST_URL;
        return super.baseGet(url, liveGetBannedListRequest, List.class);
    }
}
