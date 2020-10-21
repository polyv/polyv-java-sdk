package net.polyv.live.service.chat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.live.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.entity.chat.LiveSetTeacherDataRequest;
import net.polyv.live.entity.interact.LiveCheckinListRequest;
import net.polyv.live.entity.interact.LiveCheckinListResponse;
import net.polyv.live.entity.interact.LiveCheckinMetadataBySessionIdRequest;
import net.polyv.live.entity.interact.LiveCheckinMetadataBySessionIdResponse;
import net.polyv.live.entity.interact.LiveCheckinRequest;
import net.polyv.live.entity.interact.LiveCheckinResponse;

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
    
    

}
