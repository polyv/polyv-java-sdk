package net.polyv.live.v1.service.chat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.live.v1.entity.chat.LiveBadWordRequest;
import net.polyv.live.v1.entity.chat.LiveBadWordResponse;
import net.polyv.live.v1.entity.chat.LiveChatBannedIPRequest;
import net.polyv.live.v1.entity.chat.LiveChatDelSingleMsgRequest;
import net.polyv.live.v1.entity.chat.LiveCleanChannelAllMsgRequest;
import net.polyv.live.v1.entity.chat.LiveDelBannedDataRequest;
import net.polyv.live.v1.entity.chat.LiveGetAccountBadWordRequest;
import net.polyv.live.v1.entity.chat.LiveGetBadwordIPRequest;
import net.polyv.live.v1.entity.chat.LiveGetBannedListRequest;
import net.polyv.live.v1.entity.chat.LiveGetChatAdminDataRequest;
import net.polyv.live.v1.entity.chat.LiveGetChatAdminDataResponse;
import net.polyv.live.v1.entity.chat.LiveGetConsultingRecordRequest;
import net.polyv.live.v1.entity.chat.LiveGetConsultingRecordResponse;
import net.polyv.live.v1.entity.chat.LiveGetHistoryChatMsgRequest;
import net.polyv.live.v1.entity.chat.LiveGetHistoryChatMsgResponse;
import net.polyv.live.v1.entity.chat.LiveGetQuestionStatisticalRequest;
import net.polyv.live.v1.entity.chat.LiveGetQuestionStatisticalResponse;
import net.polyv.live.v1.entity.chat.LiveKickedListRequest;
import net.polyv.live.v1.entity.chat.LiveKickedListResponse;
import net.polyv.live.v1.entity.chat.LiveSendChannelChatRequest;
import net.polyv.live.v1.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.v1.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.v1.entity.chat.LiveSetChatAdminDataRequest;
import net.polyv.live.v1.entity.chat.LiveSetTeacherDataRequest;

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
    Boolean setChannelTeacherMsg(LiveSetTeacherDataRequest liveSetTeacherDataRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置聊天室禁言ip，API地址：https://dev.polyv.net/2017/liveproduct/zblts/addbannedip/
     * @param liveChatBannedIPRequest 设置聊天室禁言ip请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<String> addBannedIP(LiveChatBannedIPRequest liveChatBannedIPRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量导入频道严禁词，API地址：https://dev.polyv.net/2017/liveproduct/zblts/addforbiddenwords/
     * @param liveBadWordRequest 批量导入频道严禁词请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveBadWordResponse addBadWord(LiveBadWordRequest liveBadWordRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道禁言列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/get-banned-list/
     * @param liveGetBannedListRequest 查询频道禁言列表请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<String> getBannedList(LiveGetBannedListRequest liveGetBannedListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道踢人列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/list-kicked/
     * @param liveKickedListRequest 查询频道踢人列表请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<LiveKickedListResponse> getKickedList(LiveKickedListRequest liveKickedListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除禁言IP/严禁词，API地址：https://dev.polyv.net/2017/liveproduct/zblts/delbanned/
     * @param liveDelBannedDataRequest 删除禁言IP/严禁词请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean delBanned(LiveDelBannedDataRequest liveDelBannedDataRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道严禁词/禁言IP列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/get-channel-badwords/
     * @param liveGetBadwordIPRequest 查询频道严禁词/禁言IP列表请求实体
     * @return 禁言IP 或者 禁言词
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<String> getChannelBadworkList(LiveGetBadwordIPRequest liveGetBadwordIPRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询账号严禁词列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/user-badword-list/
     * @param liveGetAccountBadWordRequest 查询账号严禁词列表请求实体
     * @return 禁言词列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<String> getAccountBadworkList(LiveGetAccountBadWordRequest liveGetAccountBadWordRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除频道聊天记录，API地址：https://dev.polyv.net/2017/liveproduct/zblts/cleanchat/
     * @param liveCleanChannelAllMsgRequest 删除频道聊天记录请求实体
     * @return 频道号
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    Boolean cleanChannelAllMsg(LiveCleanChannelAllMsgRequest liveCleanChannelAllMsgRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询聊天室管理员信息，API地址：https://dev.polyv.net/2017/liveproduct/zblts/get-chat-admin/
     * @param liveGetChatAdminDataRequest 查询聊天室管理员信息请求实体
     * @return 频道号
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    LiveGetChatAdminDataResponse getChatAdminData(LiveGetChatAdminDataRequest liveGetChatAdminDataRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询历史聊天信息，API地址：https://dev.polyv.net/2019/liveproduct/zblts/get-channel-badwords/
     * @param liveGetHistoryChatMsgRequest 查询历史聊天信息请求实体
     * @return 想要实体列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<LiveGetHistoryChatMsgResponse> getHistoryChatMsg(LiveGetHistoryChatMsgRequest liveGetHistoryChatMsgRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除单条聊天记录，API地址：https://dev.polyv.net/2017/liveproduct/zblts/delchat/
     * @param liveChatDelSingleMsgRequest 删除单条聊天记录请求实体
     * @return 是否成功
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    Boolean delChatSingleMsg(LiveChatDelSingleMsgRequest liveChatDelSingleMsgRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置聊天室管理员信息，API地址：https://dev.polyv.net/2017/liveproduct/zblts/set-chat-admin/
     * @param liveSetChatAdminDataRequest 设置聊天室管理员信息请求实体
     * @return 是否成功
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    Boolean setChatAdminData(LiveSetChatAdminDataRequest liveSetChatAdminDataRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询咨询提问记录，API地址：https://dev.polyv.net/2018/liveproduct/zblts/getquestion/
     * @param liveGetConsultingRecordRequest 查询咨询提问记录请求实体
     * @return 实体列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<LiveGetConsultingRecordResponse> getConsultingRecord(
            LiveGetConsultingRecordRequest liveGetConsultingRecordRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道的问答统计结果，API地址：https://dev.polyv.net/2018/liveproduct/zblts/get-question-result/
     * @param liveGetQuestionStatisticalRequest 查询频道的问答统计结果请求实体
     * @return 想要实体列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<LiveGetQuestionStatisticalResponse> getQuestionStatistical(
            LiveGetQuestionStatisticalRequest liveGetQuestionStatisticalRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 管理员发送频道聊天信息
     * API地址：https://dev.polyv.net/2018/liveproduct/zblts/chatsendmsg/
     * @param liveSendChannelChatRequest 管理员发送频道聊天信息请求实体
     * @return 管理员发送频道聊天信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean sendChannelChat(LiveSendChannelChatRequest liveSendChannelChatRequest)
            throws IOException, NoSuchAlgorithmException;
}
