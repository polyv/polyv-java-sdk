package net.polyv.live.service.chat;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.chat.LiveBadWordRequest;
import net.polyv.live.entity.chat.LiveBadWordResponse;
import net.polyv.live.entity.chat.LiveChatBannedIPRequest;
import net.polyv.live.entity.chat.LiveChatDelSingleMsgRequest;
import net.polyv.live.entity.chat.LiveCleanChannelAllMsgRequest;
import net.polyv.live.entity.chat.LiveDelBannedDataRequest;
import net.polyv.live.entity.chat.LiveGetAccountBadWordRequest;
import net.polyv.live.entity.chat.LiveGetBadwordIPRequest;
import net.polyv.live.entity.chat.LiveGetBannedListRequest;
import net.polyv.live.entity.chat.LiveGetChatAdminDataRequest;
import net.polyv.live.entity.chat.LiveGetChatAdminDataResponse;
import net.polyv.live.entity.chat.LiveGetConsultingRecordRequest;
import net.polyv.live.entity.chat.LiveGetConsultingRecordResponse;
import net.polyv.live.entity.chat.LiveGetHistoryChatMsgRequest;
import net.polyv.live.entity.chat.LiveGetHistoryChatMsgResponse;
import net.polyv.live.entity.chat.LiveGetQuestionStatisticalRequest;
import net.polyv.live.entity.chat.LiveGetQuestionStatisticalResponse;
import net.polyv.live.entity.chat.LiveKickedListRequest;
import net.polyv.live.entity.chat.LiveKickedListResponse;
import net.polyv.live.entity.chat.LiveSendChatMsgRequest;
import net.polyv.live.entity.chat.LiveSendChatMsgResponse;
import net.polyv.live.entity.chat.LiveSetChatAdminDataRequest;
import net.polyv.live.entity.chat.LiveSetTeacherDataRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.chat.impl.LiveChatRoomServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class LiveChatRoomServiceImplTest extends BaseTest {
    
    /**
     * 通过HTTP接口发送聊天消息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSendChatMsg() throws IOException, NoSuchAlgorithmException {
        LiveSendChatMsgRequest liveSendChatMsgRequest = new LiveSendChatMsgRequest();
        LiveSendChatMsgResponse liveSendChatMsgResponse = null;
        try {
            Integer channelId = super.createChannel();
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
    
    
    /**
     * 设置讲师信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetChannelTeacherMsg() throws IOException, NoSuchAlgorithmException {
        LiveSetTeacherDataRequest liveSetTeacherDataRequest = new LiveSetTeacherDataRequest();
        Boolean result = null;
        try {
            Integer channelId = super.createChannel();
            liveSetTeacherDataRequest.setChannelId(channelId)
                    .setNickname("thomas-gogo")
                    .setActor("大师")
                    .setPasswd("123456")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                            "3965499425&fm=26&gp=0" + ".jpg")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().setChannelTeacherMsg(liveSetTeacherDataRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置讲师信息成功{}", JSON.toJSONString(result));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAddBannedIP() throws IOException, NoSuchAlgorithmException {
        LiveChatBannedIPRequest liveChatBannedIPRequest = new LiveChatBannedIPRequest();
        List<String> bannedIPList = null;
        try {
            Integer channelId = super.createChannel();
            liveChatBannedIPRequest.setIp("192.168.1.1")
                    .setChannelId(channelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            bannedIPList = new LiveChatRoomServiceImpl().addBannedIP(liveChatBannedIPRequest);
            Assert.assertNotNull(bannedIPList);
            if (bannedIPList != null) {
                //to do something ......
                log.debug("测试设置聊天室禁言ip成功{}", JSON.toJSONString(bannedIPList));
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
     * 批量导入频道严禁词
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAddBadWord() throws IOException, NoSuchAlgorithmException {
        LiveBadWordRequest liveBadWordRequest = new LiveBadWordRequest();
        LiveBadWordResponse liveBadWordResponse = null;
        try {
            Integer channelId = super.createChannel();
            liveBadWordRequest
//                .setChannelId(channelId)
                    .setWords(Arrays.asList(new String[]{"你好", "逗逼", "傻子"})).setRequestId(LiveSignUtil.generateUUID());
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
     * 查询频道禁言列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetBannedList() throws IOException, NoSuchAlgorithmException {
        LiveGetBannedListRequest liveGetBannedListRequest = new LiveGetBannedListRequest();
        List<String> result = null;
        try {
            Integer channelId = super.createChannel();
            liveGetBannedListRequest.setChannelId(channelId).setType(LiveConstant.BannedType.IP.getType());
//                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().getBannedList(liveGetBannedListRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试查询频道禁言列表成功{}", JSON.toJSONString(result));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetKickedList() throws IOException, NoSuchAlgorithmException {
        LiveKickedListRequest liveKickedListRequest = new LiveKickedListRequest();
        List<LiveKickedListResponse> liveKickedListResponsesList = null;
        try {
            Integer channelId = super.createChannel();
            liveKickedListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveKickedListResponsesList = new LiveChatRoomServiceImpl().getKickedList(liveKickedListRequest);
            Assert.assertNotNull(liveKickedListResponsesList);
            if (liveKickedListResponsesList != null) {
                //to do something ......
                Integer channelId1 = liveKickedListResponsesList.get(0).getChannelId();
                log.debug("取第一个信息频道 {}", channelId1);
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelBadworkList() throws IOException, NoSuchAlgorithmException {
        LiveGetBadwordIPRequest liveGetBadwordIPRequest = new LiveGetBadwordIPRequest();
        List<String> result = null;
        try {
            Integer channelId = super.createChannel();
            liveGetBadwordIPRequest.setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType())
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().getChannelBadworkList(liveGetBadwordIPRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试查询频道严禁词/禁言IP列表成功{}", JSON.toJSONString(result));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetAccountBadworkList() throws IOException, NoSuchAlgorithmException {
        LiveGetAccountBadWordRequest liveGetAccountBadWordRequest = new LiveGetAccountBadWordRequest();
        List<String> result = null;
        try {
            liveGetAccountBadWordRequest.setRequestId(LiveSignUtil.generateUUID());
            Integer channelId = super.createChannel();
            result = new LiveChatRoomServiceImpl().getAccountBadworkList(liveGetAccountBadWordRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试查询账号严禁词列表成功{}", JSON.toJSONString(result));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDelBanned() throws IOException, NoSuchAlgorithmException {
        LiveDelBannedDataRequest liveDelBannedDataRequest = new LiveDelBannedDataRequest();
        Boolean result = null;
        try {
            Integer channelId = super.createChannel();
            liveDelBannedDataRequest.setContent("192.168.1.1")
                    .setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType())
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().delBanned(liveDelBannedDataRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试删除禁言IP/严禁词成功{}", JSON.toJSONString(result));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChatAdminData() throws IOException, NoSuchAlgorithmException {
        LiveGetChatAdminDataRequest liveGetChatAdminDataRequest = new LiveGetChatAdminDataRequest();
        LiveGetChatAdminDataResponse liveGetChatAdminDataResponse = null;
        try {
            Integer channelId = super.createChannel();
            liveGetChatAdminDataRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
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
     * 查询历史聊天信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetHistroyChatMsg() throws IOException, NoSuchAlgorithmException {
        LiveGetHistoryChatMsgRequest liveGetHistoryChatMsgRequest = new LiveGetHistoryChatMsgRequest();
        List<LiveGetHistoryChatMsgResponse> liveGetHistoryChatMsgResponsesList = null;
        try {
            Integer channelId = super.createChannel();
            liveGetHistoryChatMsgRequest.setChannelId(channelId)
                    .setStartDay("2020-10-1")
                    .setEndDay("2099-12-12")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveGetHistoryChatMsgResponsesList = new LiveChatRoomServiceImpl().getHistroyChatMsg(
                    liveGetHistoryChatMsgRequest);
            Assert.assertNotNull(liveGetHistoryChatMsgResponsesList);
            if (liveGetHistoryChatMsgResponsesList != null) {
                //to do something ......
                log.debug("测试查询历史聊天信息成功{}", JSON.toJSONString(liveGetHistoryChatMsgResponsesList));
                log.debug("第一个元素 {} ", liveGetHistoryChatMsgResponsesList.get(0));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDelChatSingleMsg() throws IOException, NoSuchAlgorithmException {
        LiveChatDelSingleMsgRequest liveChatDelSingleMsgRequest = new LiveChatDelSingleMsgRequest();
        Boolean result = null;
        
        //获取已经存在的消息id开始
        String msgId = "";
        LiveSendChatMsgRequest liveSendChatMsgRequest = new LiveSendChatMsgRequest();
        LiveSendChatMsgResponse liveSendChatMsgResponse = null;
        Integer channelId = super.createChannel();
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
            msgId = liveSendChatMsgResponse.getMsgId();
            log.debug("测试通过HTTP接口发送聊天消息成功,消息ID {}", msgId);
        }
        Assert.assertNotEquals(0, msgId.trim().length());
        //获取已经存在的消息id结束
        
        try {
            liveChatDelSingleMsgRequest.setId(msgId)
                    .setChannelId(channelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().delChatSingleMsg(liveChatDelSingleMsgRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试删除单条聊天记录成功{}", JSON.toJSONString(result));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetChatAdminData() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetChatAdminDataRequest liveSetChatAdminDataRequest = new LiveSetChatAdminDataRequest();
        Boolean result = null;
        try {
            Integer channelId = super.createChannel();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setNickname("你个老头")
                    .setActor("娇娇")
                    .setAvatar(new File("/data/img/b.jpg"))
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().setChatAdminData(liveSetChatAdminDataRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置聊天室管理员信息成功{}", JSON.toJSONString(result));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetConsultingRecord() throws IOException, NoSuchAlgorithmException {
        LiveGetConsultingRecordRequest liveGetConsultingRecordRequest = new LiveGetConsultingRecordRequest();
        List<LiveGetConsultingRecordResponse> responses = null;
        try {
            Integer channelId = super.createChannel();
            liveGetConsultingRecordRequest.setChannelId(channelId)
                    .setBegin(0)
                    .setEnd(10)
                    .setRequestId(LiveSignUtil.generateUUID());
            responses = new LiveChatRoomServiceImpl().getConsultingRecord(liveGetConsultingRecordRequest);
            Assert.assertNotNull(responses);
            if (responses != null) {
                //to do something ......
                log.debug("测试查询咨询提问记录成功{}", JSON.toJSONString(responses));
                log.debug("第一个元素 {} ", responses.get(0).getContent());
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
     * 查询频道的问答统计结果
     * TODO 待验证
    * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetQuestionStatistical() throws IOException, NoSuchAlgorithmException {
        LiveGetQuestionStatisticalRequest liveGetQuestionStatisticalRequest = new LiveGetQuestionStatisticalRequest();
        List<LiveGetQuestionStatisticalResponse> result = null;
        try {
            Integer channelId = super.createChannel();
            liveGetQuestionStatisticalRequest.setChannelId(channelId)
                    .setStartTime("2020-10-1 00:00:00")
                    .setEndTime("2020-10-30 12:20:20")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().getQuestionStatistical(liveGetQuestionStatisticalRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试查询咨询提问记录成功{}", JSON.toJSONString(result));
//            log.debug("第一个元素 {} ", result.get(0));
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCleanChannelAllMsg() throws IOException, NoSuchAlgorithmException {
        LiveCleanChannelAllMsgRequest liveCleanChannelAllMsgRequest = new LiveCleanChannelAllMsgRequest();
        Boolean result = null;
        try {
            Integer channelId = super.createChannel();
            liveCleanChannelAllMsgRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().cleanChannelAllMsg(liveCleanChannelAllMsgRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试删除频道聊天记录成功{}", JSON.toJSONString(result));
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
