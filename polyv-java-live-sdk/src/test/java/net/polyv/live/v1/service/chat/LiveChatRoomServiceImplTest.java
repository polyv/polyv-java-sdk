package net.polyv.live.v1.service.chat;

import java.io.File;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.constant.LiveConstant;
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
import net.polyv.live.v1.entity.chat.LiveKickedListRequest;
import net.polyv.live.v1.entity.chat.LiveKickedListResponse;
import net.polyv.live.v1.entity.chat.LiveSendChannelChatRequest;
import net.polyv.live.v1.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.v1.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.v1.entity.chat.LiveSendCustomChatRequest;
import net.polyv.live.v1.entity.chat.LiveSetChatAdminDataRequest;
import net.polyv.live.v1.entity.chat.LiveSetTeacherDataRequest;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.chat.impl.LiveChatRoomServiceImpl;

/**
 * 聊天室
 * @author: thomas
 **/
@Slf4j
public class LiveChatRoomServiceImplTest extends BaseTest {
    
    /**
     * 批量导入频道严禁词
     * API地址：CHAT_SET_BAD_WORD_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAddBadWord() throws Exception, NoSuchAlgorithmException {
        LiveBadWordRequest liveBadWordRequest = new LiveBadWordRequest();
        LiveBadWordResponse liveBadWordResponse = null;
        try {
            String channelId = super.createChannel();
            liveBadWordRequest.setChannelId(channelId)
                    .setWords(Arrays.asList(new String[]{"你好", "逗逼", "傻子"}));
            liveBadWordResponse = new LiveChatRoomServiceImpl().addBadWord(liveBadWordRequest);
            Assert.assertNotNull(liveBadWordResponse);
            if (liveBadWordResponse != null) {
                //to do something ......
                log.debug("测试批量导入频道严禁词成功{}", JSON.toJSONString(liveBadWordResponse));
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
     * 通过HTTP接口发送聊天消息
     * 描述：可指定发言者的头像、头衔、昵称，无需连接聊天室，通过HTTP接口发送聊天文本内容
     * API地址：CHAT_SEND_MSG_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSendChatMsg() throws Exception, NoSuchAlgorithmException {
        LiveSendChatMsgRequest liveSendChatMsgRequest = new LiveSendChatMsgRequest();
        LiveSendChatMsgResponse liveSendChatMsgResponse = null;
        try {
            String channelId = super.createChannel();
            liveSendChatMsgRequest.setChannelId(channelId)
                    .setMsg("hello 大家好-通过API发过来的测试信息")
                    .setPic("https://5b0988e595225.cdn.sohucs.com/q_70,c_zoom," +
                            "w_640/images/20190129/e3b0d6311b1a411fa68125fc03b8ef67.jpeg")
                    .setNickName("thomas")
                    .setFreeReview(LiveConstant.Flag.YES.getFlag());
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
    
    /**
     * 查询历史聊天信息
     * 描述：查询一段时间内的聊天记录，时间格式为yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss。如未提交具体时间，只提交了日期，开始时间默认为日期当天的 00:00:00，结束时间为日期当天的23:59:59
     * API地址：CHAT_GET_HISTORY_MSG_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetHistoryChatMsg() throws Exception, NoSuchAlgorithmException {
        LiveGetHistoryChatMsgRequest liveGetHistoryChatMsgRequest = new LiveGetHistoryChatMsgRequest();
        List<LiveGetHistoryChatMsgResponse> liveGetHistoryChatMsgResponsesList = null;
        try {
            String channelId = super.createChannel();
            liveGetHistoryChatMsgRequest.setChannelId(channelId)
                    .setStartDay(getDate(2020, 10, 01))
                    .setEndDay(getDate(2099, 12, 12))
                    .setPageSize(2);
            liveGetHistoryChatMsgResponsesList = new LiveChatRoomServiceImpl().getHistoryChatMsg(
                    liveGetHistoryChatMsgRequest);
            Assert.assertNotNull(liveGetHistoryChatMsgResponsesList);
            if (liveGetHistoryChatMsgResponsesList != null) {
                //to do something ......
                log.debug("测试查询历史聊天信息成功{}", JSON.toJSONString(liveGetHistoryChatMsgResponsesList));
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
     * 查询聊天室管理员信息
     * API地址：CHAT_GET_ADMIN_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChatAdminData() throws Exception, NoSuchAlgorithmException {
        LiveGetChatAdminDataRequest liveGetChatAdminDataRequest = new LiveGetChatAdminDataRequest();
        LiveGetChatAdminDataResponse liveGetChatAdminDataResponse = null;
        try {
            String channelId = super.createChannel();
            liveGetChatAdminDataRequest.setChannelId(channelId);
            liveGetChatAdminDataResponse = new LiveChatRoomServiceImpl().getChatAdminData(liveGetChatAdminDataRequest);
            Assert.assertNotNull(liveGetChatAdminDataResponse);
            if (liveGetChatAdminDataResponse != null) {
                //to do something ......
                log.debug("测试查询聊天室管理员信息成功{}", JSON.toJSONString(liveGetChatAdminDataResponse));
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
     * 查询频道禁言列表
     * 返回：返回当前频道的禁言列表
     * API地址：CHAT_GET_CHANNEL_BANNED_LIST_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetBannedList() throws Exception, NoSuchAlgorithmException {
        LiveGetBannedListRequest liveGetBannedListRequest = new LiveGetBannedListRequest();
        List<String> liveGetBannedListResponseList = null;
        try {
            String channelId = super.createChannel();
            liveGetBannedListRequest.setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType());
            liveGetBannedListResponseList = new LiveChatRoomServiceImpl().getBannedList(liveGetBannedListRequest);
            Assert.assertNotNull(liveGetBannedListResponseList);
            if (liveGetBannedListResponseList != null) {
                //to do something ......
                log.debug("测试查询频道禁言列表成功{}", JSON.toJSONString(liveGetBannedListResponseList));
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
     * 查询频道踢人列表
     * API地址：CHAT_LIST_KICKED_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetKickedList() throws Exception, NoSuchAlgorithmException {
        LiveKickedListRequest liveKickedListRequest = new LiveKickedListRequest();
        List<LiveKickedListResponse> liveKickedListResponsesList = null;
        try {
            String channelId = super.createChannel();
            liveKickedListRequest.setChannelId(channelId);
            liveKickedListResponsesList = new LiveChatRoomServiceImpl().getKickedList(liveKickedListRequest);
            Assert.assertNotNull(liveKickedListResponsesList);
            if (liveKickedListResponsesList != null) {
                //to do something ......
                log.debug("测试查询频道踢人列表成功{}", JSON.toJSONString(liveKickedListResponsesList));
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
     * 查询频道严禁词/禁言IP列表
     * 描述：接口用于获取频道的严禁词或者禁言IP列表
     * 返回：获取当前频道的严禁词/禁言IP列表
     * API地址：CHAT_GET_BAKWORD_WORD_IP_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelBadworkList() throws Exception, NoSuchAlgorithmException {
        LiveGetBadwordIPRequest liveGetBadwordIPRequest = new LiveGetBadwordIPRequest();
        List<String> liveGetBadwordIPResponseList = null;
        try {
            String channelId = super.createChannel();
            liveGetBadwordIPRequest.setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType());
            liveGetBadwordIPResponseList = new LiveChatRoomServiceImpl().getChannelBadworkList(liveGetBadwordIPRequest);
            Assert.assertNotNull(liveGetBadwordIPResponseList);
            if (liveGetBadwordIPResponseList != null) {
                //to do something ......
                log.debug("测试查询频道严禁词/禁言IP列表成功{}", JSON.toJSONString(liveGetBadwordIPResponseList));
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
     * 查询账号严禁词列表
     * 描述：接口用于获取账号下通用设置的严禁词列表
     * 返回：当前保利威账号下通用设置的严禁词列表
     * API地址：CHAT_GET_ACCOUNT_BAKWORD_WORD_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetAccountBadworkList() throws Exception, NoSuchAlgorithmException {
        LiveGetAccountBadWordRequest liveGetAccountBadWordRequest = new LiveGetAccountBadWordRequest();
        List<String> liveGetAccountBadWordResponseList = null;
        try {
            liveGetAccountBadWordResponseList = new LiveChatRoomServiceImpl().getAccountBadworkList(liveGetAccountBadWordRequest);
            Assert.assertNotNull(liveGetAccountBadWordResponseList);
            if (liveGetAccountBadWordResponseList != null) {
                //to do something ......
                log.debug("测试查询账号严禁词列表成功{}", JSON.toJSONString(liveGetAccountBadWordResponseList));
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
     * 查询咨询提问记录
     * API地址：CHAT_GET_CONSULTING_RECORD_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetConsultingRecord() throws Exception, NoSuchAlgorithmException {
        LiveGetConsultingRecordRequest liveGetConsultingRecordRequest = new LiveGetConsultingRecordRequest();
        List<LiveGetConsultingRecordResponse> liveGetConsultingRecordResponseList = null;
        try {
            String channelId = super.createChannel();
            liveGetConsultingRecordRequest.setChannelId(channelId)
                    .setBegin(0)
                    .setEnd(10);
            liveGetConsultingRecordResponseList = new LiveChatRoomServiceImpl().getConsultingRecord(liveGetConsultingRecordRequest);
            Assert.assertNotNull(liveGetConsultingRecordResponseList);
            if (liveGetConsultingRecordResponseList != null) {
                //to do something ......
                log.debug("测试查询咨询提问记录成功{}", JSON.toJSONString(liveGetConsultingRecordResponseList));
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
     * 设置讲师信息
     * 返回：true 设置讲师信息成功，false 设置讲师信息失败
     * API地址：CHAT_SET_TEACHER_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetChannelTeacherMsg() throws Exception, NoSuchAlgorithmException {
        LiveSetTeacherDataRequest liveSetTeacherDataRequest = new LiveSetTeacherDataRequest();
        Boolean liveSetTeacherDataResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetTeacherDataRequest.setChannelId(channelId)
                    .setNickname("thomas-gogo")
                    .setActor("大师")
                    .setPasswd("123456")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                            "3965499425&fm=26&gp=0" + ".jpg");
            liveSetTeacherDataResponse = new LiveChatRoomServiceImpl().setChannelTeacherMsg(liveSetTeacherDataRequest);
            Assert.assertNotNull(liveSetTeacherDataResponse);
            if (liveSetTeacherDataResponse != null) {
                //to do something ......
                log.debug("测试设置讲师信息成功{}", JSON.toJSONString(liveSetTeacherDataResponse));
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
     * 设置聊天室禁言ip
     * 返回：当前所有的禁言ip列表
     * API地址：CHAT_BANNED_IP_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAddBannedIP() throws Exception, NoSuchAlgorithmException {
        LiveChatBannedIPRequest liveChatBannedIPRequest = new LiveChatBannedIPRequest();
        List<String> liveChatBannedIPResponseList = null;
        try {
            String channelId = super.createChannel();
            liveChatBannedIPRequest.setIp("192.168.1.1")
                    .setChannelId(channelId);
            liveChatBannedIPResponseList = new LiveChatRoomServiceImpl().addBannedIP(liveChatBannedIPRequest);
            Assert.assertNotNull(liveChatBannedIPResponseList);
            if (liveChatBannedIPResponseList != null) {
                //to do something ......
                log.debug("测试设置聊天室禁言ip成功{}", JSON.toJSONString(liveChatBannedIPResponseList));
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
     * 设置聊天室管理员信息
     * 返回：true 设置成功，false 设置失败
     * API地址：CHAT_SET_ADMIN_DATA_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetChatAdminData() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetChatAdminDataRequest liveSetChatAdminDataRequest = new LiveSetChatAdminDataRequest();
        Boolean liveSetChatAdminDataResponse = null;
        try {
            String channelId = super.createChannel();
            String path = getClass().getResource("/img/b.jpg").getPath();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setNickname("你个老头")
                    .setActor("娇娇")
                    .setAvatar(new File(path));
            
            liveSetChatAdminDataResponse = new LiveChatRoomServiceImpl().setChatAdminData(liveSetChatAdminDataRequest);
            Assert.assertTrue(liveSetChatAdminDataResponse);
            if (liveSetChatAdminDataResponse != null) {
                //to do something ......
                log.debug("测试设置聊天室管理员信息成功{}", JSON.toJSONString(liveSetChatAdminDataResponse));
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
     * 删除禁言IP/严禁词
     * 返回：true 删除成功 ，false 删除失败
     * API地址：CHAT_DEL_BANNED_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDelBanned() throws Exception, NoSuchAlgorithmException {
        LiveDelBannedDataRequest liveDelBannedDataRequest = new LiveDelBannedDataRequest();
        Boolean liveDelBannedDataResponse = null;
        try {
            //创建一个禁言IP（192.168.1.1）用于测试，实际业务过程中，此代码可以删除
            testAddBannedIP();
            //正式业务逻辑
            String channelId = super.createChannel();
            liveDelBannedDataRequest.setContent("192.168.1.1")
                    .setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType());
            liveDelBannedDataResponse = new LiveChatRoomServiceImpl().delBanned(liveDelBannedDataRequest);
            Assert.assertNotNull(liveDelBannedDataResponse);
            if (liveDelBannedDataResponse != null) {
                //to do something ......
                log.debug("测试删除禁言IP/严禁词成功{}", JSON.toJSONString(liveDelBannedDataResponse));
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
     * 删除单条聊天记录
     * 描述：根据聊天的id删除对应聊天记录
     * 返回：true 删除成功， false 删除失败
     * API地址：CHAT_SEND_MSG_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDelChatSingleMsg() throws Exception, NoSuchAlgorithmException {
        LiveChatDelSingleMsgRequest liveChatDelSingleMsgRequest = new LiveChatDelSingleMsgRequest();
        Boolean liveChatDelSingleMsgResponse = null;
        
        String channelId = super.createChannel();
        //获取已经存在的消息id
        //new LiveChatRoomServiceImpl().sendChatMsg(liveSendChatMsgRequest)
        String msgId = getMsgId(channelId);
        
        try {
            liveChatDelSingleMsgRequest.setId(msgId).setChannelId(channelId);
            liveChatDelSingleMsgResponse = new LiveChatRoomServiceImpl().delChatSingleMsg(liveChatDelSingleMsgRequest);
            Assert.assertNotNull(liveChatDelSingleMsgResponse);
            if (liveChatDelSingleMsgResponse != null) {
                //to do something ......
                log.debug("测试删除单条聊天记录成功{}", JSON.toJSONString(liveChatDelSingleMsgResponse));
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
     * 删除频道聊天记录
     * 返回：true 删除成功， false 删除失败
     * API地址：CHAT_CLEAN_CHANNEL_MSG_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCleanChannelAllMsg() throws Exception, NoSuchAlgorithmException {
        LiveCleanChannelAllMsgRequest liveCleanChannelAllMsgRequest = new LiveCleanChannelAllMsgRequest();
        Boolean liveCleanChannelAllMsgResponse = null;
        try {
            String channelId = super.createChannel();
            liveCleanChannelAllMsgRequest.setChannelId(channelId);
            liveCleanChannelAllMsgResponse = new LiveChatRoomServiceImpl().cleanChannelAllMsg(liveCleanChannelAllMsgRequest);
            Assert.assertNotNull(liveCleanChannelAllMsgResponse);
            if (liveCleanChannelAllMsgResponse != null) {
                //to do something ......
                log.debug("测试删除频道聊天记录成功{}", JSON.toJSONString(liveCleanChannelAllMsgResponse));
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
     * 测试管理员发送频道聊天信息
     * 返回：true 发送成功， false 发送失败
     * API地址：SEND_ADMIN_MSG_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSendChannelChat() throws Exception, NoSuchAlgorithmException {
        LiveSendChannelChatRequest liveSendChannelChatRequest = new LiveSendChannelChatRequest();
        Boolean liveSendChannelChatResponse;
        try {
            String channelId = super.createChannel();
            liveSendChannelChatRequest.setChannelId(channelId)
                    .setContent("请同学们认真学习")
                    .setRole("ADMIN");
            liveSendChannelChatResponse = new LiveChatRoomServiceImpl().sendChannelChat(liveSendChannelChatRequest);
            Assert.assertTrue(liveSendChannelChatResponse);
            if (liveSendChannelChatResponse) {
                //to do something ......
                log.debug("测试管理员发送频道聊天信息成功");
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
     * 测试发送自定义聊天信息
     * 返回：true 发送成功， false 发送失败
     * API地址：SEND_CUSTOM_MSG_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSendCustomChat() throws Exception, NoSuchAlgorithmException {
        LiveSendCustomChatRequest liveSendCustomChatRequest = new LiveSendCustomChatRequest();
        Boolean liveSendCustomChatResponse;
        try {
            String channelId = super.createChannel();
            liveSendCustomChatRequest.setChannelId(channelId)
                    .setContent("请同学们认真学习");
            liveSendCustomChatResponse = new LiveChatRoomServiceImpl().sendCustomChat(liveSendCustomChatRequest);
            Assert.assertTrue(liveSendCustomChatResponse);
            if (liveSendCustomChatResponse) {
                //to do something ......
                log.debug("测试发送自定义聊天信息成功");
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
