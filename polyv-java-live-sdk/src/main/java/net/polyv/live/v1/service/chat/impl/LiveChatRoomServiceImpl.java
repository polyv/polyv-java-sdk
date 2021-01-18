package net.polyv.live.v1.service.chat.impl;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.config.LiveGlobalConfig;
import net.polyv.live.v1.constant.LiveURL;
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
import net.polyv.live.v1.entity.chat.LiveSendCustomChatRequest;
import net.polyv.live.v1.entity.chat.LiveSetChatAdminDataRequest;
import net.polyv.live.v1.entity.chat.LiveSetTeacherDataRequest;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.chat.ILiveChatRoomService;

/**
 * 直播签到管理
 * @author: thomas
 **/
@Slf4j
public class LiveChatRoomServiceImpl extends LiveBaseService implements ILiveChatRoomService {
    
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
        return super.postFormBodyReturnOne(url, liveSendChatMsgRequest, LiveSendChatMsgResponse.class);
    }
    
    /**
     * 设置讲师信息，API地址：https://dev.polyv.net/2019/liveproduct/zblts/update-channel-teacher/
     * @param liveSetTeacherDataRequest 设置讲师信息请求实体
     * @return 设置成功返回 "true" , 设置失败抛出异常 BussinessExcepiton
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setChannelTeacherMsg(LiveSetTeacherDataRequest liveSetTeacherDataRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHAT_SET_TEACHER_URL;
        return super.postFormBodyReturnOne(url, liveSetTeacherDataRequest, Boolean.class);
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
        return super.postFormBodyReturnList(url, liveChatBannedIPRequest, String.class);
    }
    
    /**
     * 批量导入频道严禁词，API地址：https://dev.polyv.net/2017/liveproduct/zblts/addforbiddenwords/
     * @param liveBadWordRequest 批量导入频道严禁词请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveBadWordResponse addBadWord(LiveBadWordRequest liveBadWordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_SET_BAD_WORD_URL, LiveGlobalConfig.getUserId());
        return super.postFormBodyReturnOne(url, liveBadWordRequest, LiveBadWordResponse.class);
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
        return super.getReturnList(url, liveGetBannedListRequest, String.class);
    }
    
    /**
     * 查询频道踢人列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/list-kicked/
     * @param liveKickedListRequest 查询频道踢人列表请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<LiveKickedListResponse> getKickedList(LiveKickedListRequest liveKickedListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHAT_LIST_KICKED_URL;
        return super.postFormBodyReturnList(url, liveKickedListRequest, LiveKickedListResponse.class);
    }
    
    /**
     * 删除禁言IP/严禁词，API地址：https://dev.polyv.net/2017/liveproduct/zblts/delbanned/
     * @param liveDelBannedDataRequest 删除禁言IP/严禁词请求实体
     * @return 当前账号下所有禁言IP列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean delBanned(LiveDelBannedDataRequest liveDelBannedDataRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_DEL_BANNED_URL, liveDelBannedDataRequest.getChannelId());
        return "success".equalsIgnoreCase(super.postFormBodyReturnOne(url, liveDelBannedDataRequest, String.class));
    }
    
    /**
     * 查询频道严禁词/禁言IP列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/get-channel-badwords/
     * @param liveGetBadwordIPRequest 查询频道严禁词/禁言IP列表请求实体
     * @return 禁言IP 或者 禁言词
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<String> getChannelBadworkList(LiveGetBadwordIPRequest liveGetBadwordIPRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHAT_GET_BAKWORD_WORD_IP_URL;
        return super.postFormBodyReturnList(url, liveGetBadwordIPRequest, String.class);
    }
    
    /**
     * 查询账号严禁词列表，API地址：https://dev.polyv.net/2019/liveproduct/zblts/user-badword-list/
     * @param liveGetAccountBadWordRequest 查询账号严禁词列表请求实体
     * @return 禁言词列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<String> getAccountBadworkList(LiveGetAccountBadWordRequest liveGetAccountBadWordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHAT_GET_ACCOUNT_BAKWORD_WORD_URL;
        return super.getReturnList(url, liveGetAccountBadWordRequest, String.class);
    }
    
    /**
     * 删除频道聊天记录，API地址：https://dev.polyv.net/2017/liveproduct/zblts/cleanchat/
     * @param liveCleanChannelAllMsgRequest 删除频道聊天记录请求实体
     * @return 频道号
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    @Override
    public Boolean cleanChannelAllMsg(LiveCleanChannelAllMsgRequest liveCleanChannelAllMsgRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_CLEAN_CHANNEL_MSG_URL,
                liveCleanChannelAllMsgRequest.getChannelId());
        return super.getReturnOne(url, liveCleanChannelAllMsgRequest, Boolean.class);
    }
    
    /**
     * 查询聊天室管理员信息，API地址：https://dev.polyv.net/2017/liveproduct/zblts/get-chat-admin/
     * @param liveGetChatAdminDataRequest 查询聊天室管理员信息请求实体
     * @return 频道号
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    @Override
    public LiveGetChatAdminDataResponse getChatAdminData(LiveGetChatAdminDataRequest liveGetChatAdminDataRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_GET_ADMIN_URL, liveGetChatAdminDataRequest.getChannelId());
        return super.postFormBodyReturnOne(url, liveGetChatAdminDataRequest, LiveGetChatAdminDataResponse.class);
    }
    
    /**
     * 查询历史聊天信息，API地址：https://dev.polyv.net/2017/liveproduct/zblts/gethistorymsg/
     * @param liveGetHistoryChatMsgRequest 查询历史聊天信息请求实体
     * @return 想要实体列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<LiveGetHistoryChatMsgResponse> getHistoryChatMsg(
            LiveGetHistoryChatMsgRequest liveGetHistoryChatMsgRequest) throws IOException, NoSuchAlgorithmException {
        String url =LiveURL.CHAT_GET_HISTORY_MSG_URL;
        return super.postFormBodyReturnList(url, liveGetHistoryChatMsgRequest, LiveGetHistoryChatMsgResponse.class);
    }
    
    /**
     * 删除单条聊天记录，API地址：https://dev.polyv.net/2017/liveproduct/zblts/delchat/
     * @param liveChatDelSingleMsgRequest 删除单条聊天记录请求实体
     * @return 是否成功
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    @Override
    public Boolean delChatSingleMsg(LiveChatDelSingleMsgRequest liveChatDelSingleMsgRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_DEL_CHANNEL_SINGLE_MSG_URL,
                liveChatDelSingleMsgRequest.getChannelId());
        return "success".equalsIgnoreCase(super.getReturnOne(url, liveChatDelSingleMsgRequest, String.class));
    }
    
    /**
     * 设置聊天室管理员信息，API地址：https://dev.polyv.net/2017/liveproduct/zblts/set-chat-admin/
     * @param liveSetChatAdminDataRequest 设置聊天室管理员信息请求实体
     * @return 是否成功
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    @Override
    public Boolean setChatAdminData(LiveSetChatAdminDataRequest liveSetChatAdminDataRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_SET_ADMIN_DATA_URL, liveSetChatAdminDataRequest.getChannelId());
        Map<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("avatar", liveSetChatAdminDataRequest.getAvatar());
        return "success".equals(super.uploadOneFile(url, liveSetChatAdminDataRequest, fileMap, String.class));
    }
    
    
    /**
     * 查询咨询提问记录，API地址：https://dev.polyv.net/2018/liveproduct/zblts/getquestion/
     * @param liveGetConsultingRecordRequest 查询咨询提问记录请求实体
     * @return 实体列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<LiveGetConsultingRecordResponse> getConsultingRecord(
            LiveGetConsultingRecordRequest liveGetConsultingRecordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_GET_CONSULTING_RECORD_URL,
                liveGetConsultingRecordRequest.getChannelId());
        return super.getReturnList(url, liveGetConsultingRecordRequest, LiveGetConsultingRecordResponse.class);
    }
    
    /**
     * 查询频道的问答统计结果，API地址：https://dev.polyv.net/2018/liveproduct/zblts/get-question-result/
     * @param liveGetQuestionStatisticalRequest 查询频道的问答统计结果请求实体
     * @return 想要实体列表
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Deprecated
    @Override
    public List<LiveGetQuestionStatisticalResponse> getQuestionStatistical(
            LiveGetQuestionStatisticalRequest liveGetQuestionStatisticalRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHAT_GET_QUERSTION_STATISTICAL_URL,
                liveGetQuestionStatisticalRequest.getChannelId());
        return super.postFormBodyReturnList(url, liveGetQuestionStatisticalRequest,
                LiveGetQuestionStatisticalResponse.class);
    }
    
    /**
     * 管理员发送频道聊天信息
     * API地址：https://dev.polyv.net/2018/liveproduct/zblts/chatsendmsg/
     * @param liveSendChannelChatRequest 管理员发送频道聊天信息请求实体
     * @return 管理员发送频道聊天信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean sendChannelChat(LiveSendChannelChatRequest liveSendChannelChatRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.SEND_ADMIN_MSG_URL;
        String liveSendChannelChatResponse = this.postFormBodyReturnOne(url, liveSendChannelChatRequest, String.class);
        return "The message send success!".equals(liveSendChannelChatResponse);
    }
    
    /**
     * 发送自定义聊天信息
     * API地址：https://dev.polyv.net/2016/liveproduct/zblts/send-chat/
     * @param liveSendCustomChatRequest 发送自定义聊天信息请求实体
     * @return 发送自定义聊天信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean sendCustomChat(LiveSendCustomChatRequest liveSendCustomChatRequest)
            throws IOException, NoSuchAlgorithmException {
        liveSendCustomChatRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.SEND_CUSTOM_MSG_URL,liveSendCustomChatRequest.getChannelId());
        String liveSendCustomChatResponse = this.postFormBodyReturnOne(url,liveSendCustomChatRequest,String.class);
        return "success".equals(liveSendCustomChatResponse);
    }
    
}
