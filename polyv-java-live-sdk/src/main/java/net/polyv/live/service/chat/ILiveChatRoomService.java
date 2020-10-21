package net.polyv.live.service.chat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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

/**
 * 直播签到管理
 * @author: thomas
 **/
public interface ILiveChatRoomService {

    /**
     * 通过HTTP接口发送聊天消息请求实体，API地址：https://dev.polyv.net/2019/liveproduct/zblts/send-admin-msg/
     * @param liveSendChatMsgRequest 查询签到结果请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveSendChatMsgResponse sendChatMsg(LiveSendChatMsgRequest liveSendChatMsgRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置讲师信息，API地址：https://dev.polyv.net/2019/liveproduct/zblts/update-channel-teacher/
     * @param liveSetTeacherDataRequest 设置讲师信息请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    String sendChatMsg(LiveSetTeacherDataRequest liveSetTeacherDataRequest)
            throws IOException, NoSuchAlgorithmException;
    
    
    /**
     * 设置聊天室禁言ip，API地址：https://dev.polyv.net/2017/liveproduct/zblts/addbannedip/
     * @param liveChatBannedIPRequest 设置聊天室禁言ip请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<String>  addBannedIP(LiveChatBannedIPRequest liveChatBannedIPRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量导入频道严禁词，API地址：https://dev.polyv.net/2017/liveproduct/zblts/addforbiddenwords/
     * @param liveBadWordRequest 批量导入频道严禁词请求实体
     * @return  响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveBadWordResponse addBadWord(LiveBadWordRequest liveBadWordRequest)
            throws IOException, NoSuchAlgorithmException;
    /**
     * 查询频道禁言列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/get-banned-list/
     * @param liveGetBannedListRequest 查询频道禁言列表请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<String>  getBannedList(LiveGetBannedListRequest liveGetBannedListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    
    /**
     * 查询频道踢人列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/list-kicked/
     * @param liveKickedListRequest 查询频道踢人列表请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<LiveKickedListResponse>  getKickedList(LiveKickedListRequest liveKickedListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除禁言IP/严禁词，API地址：https://dev.polyv.net/2017/liveproduct/zblts/delbanned/
     * @param liveDelBannedDataRequest 删除禁言IP/严禁词请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    String  delBanned(LiveDelBannedDataRequest liveDelBannedDataRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道严禁词/禁言IP列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/get-channel-badwords/
     * @param liveGetBadwordIPRequest 查询频道严禁词/禁言IP列表请求实体
     * @return 禁言IP 或者 禁言词
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<String>  getBadworkList(LiveGetBadwordIPRequest liveGetBadwordIPRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询账号严禁词列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/user-badword-list/
     * @param requestId 请求序列号
     * @return   禁言词列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<String>  getAccountBadworkList(String requestId)
            throws IOException, NoSuchAlgorithmException;
     
    
//    /**
//     * 删除频道聊天记录，API地址：https://dev.polyv.net/2017/liveproduct/zblts/cleanchat/
//     * @param channelId 需要删除聊天信息的频道ID
//     * @param requestId 请求序列号
//     * @return  频道号
//     * @throws IOException 客户端和服务器读写异常
//     * @throws NoSuchAlgorithmException 签名异常
//     */
//    String  cleanChannelAllMsg(Integer channelId ,String requestId)
//            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询聊天室管理员信息，API地址：https://dev.polyv.net/2017/liveproduct/zblts/get-chat-admin/
     * @param channelId 频道ID
     * @param requestId 请求序列号
     * @return  频道号
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    LiveGetChatAdminResponse  getChatAdminData(Integer channelId ,String requestId)
            throws IOException, NoSuchAlgorithmException;
    
    
    
    
    
}
