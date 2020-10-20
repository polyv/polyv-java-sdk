package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.channel.doc.LiveListChannelDocRequest;
import net.polyv.live.entity.channel.doc.LiveListChannelDocResponse;
import net.polyv.live.entity.channel.operate.LiveChannelAuthTokenRequest;
import net.polyv.live.entity.channel.operate.LiveChannelAuthTokenResponse;
import net.polyv.live.entity.channel.operate.LiveChannelBasicInfoRequest;
import net.polyv.live.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.entity.channel.operate.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInfoRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInfoResponse;
import net.polyv.live.entity.channel.operate.LiveChannelPasswordSettingRequest;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelTokenRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelTokenRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListResponse;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoResponse;
import net.polyv.live.entity.channel.operate.LiveUpdateSonChannelInfoRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyResponse;
import net.polyv.live.entity.channel.viewdata.LiveChannelMaxHistoryConcurrentRequest;
import net.polyv.live.entity.channel.viewdata.LiveChannelViewerConcurrenceRequest;
import net.polyv.live.entity.channel.viewdata.LiveChannelViewerConcurrenceResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelMicRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelMicResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelSummaryRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelSummaryResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewerCountRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewerCountResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewlogRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewlogResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.channel.impl.LiveChannelOperateServiceImpl;

/**
 * @author: thomas
 **/
@Slf4j
public class LiveChannelOperateImplTest extends BaseTest {
    
    /**
     * 测试创建频道,注释避免频繁创建
     * @throws IOException
     */
//    @Test
//    public void testCreateChannel() throws IOException, NoSuchAlgorithmException {
//        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
//        liveChannelRequest.setName("Spring 知识精讲")
//                .setChannelPasswd("666888")
//                .setRequestId("2860257a405447e1bbbe9161da2dee72");
//        LiveChannelResponse liveChannelResponse = new LiveChannelServiceImpl().createChannel(liveChannelRequest);
//        Assert.assertNotNull(liveChannelResponse);
//        if (liveChannelResponse != null) {
//            //to do something ......
//            log.debug("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
//        }
//
//        //删除测试数据
//        deleteChannel(liveChannelResponse.getChannelId());
//    }
    
    /**
     * 测试创建并初始化频道 验证码观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
//    public void testCreateChannelInitCode() throws IOException, NoSuchAlgorithmException {
//        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
//        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
//                "创建并初始化频道-验证码观看")
//                .setChannelPasswd("123321")
//                .setAutoPlay(1)
//                .setPlayerColor("#666666")
//                .setScene(LiveConstant.SceneType.ALONE.getDesc())
//                .setCategoryId(340019)
//                .setMaxViewer(0)
//                .setStartTime(1602306535000l)
//                .setDesc("这是一个描述")
//                .setPublisher("sadboy主讲")
//                .setLinkMicLimit(-1)
//                .setPureRtcEnabled("N")
//                .setReceiveChannelIds("213");
//        liveChannelInitRequest.setBasicSetting(basicSetting);
//        //验证码观看
//        LiveChannelInitRequest.AuthSetting codeAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
//                .setAuthType(LiveConstant.AuthType.CODE.getDesc())
//                .setEnabled("Y")
//                .setAuthCode("123456")
//                .setQcodeTips("提示文案")
//                .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
//        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
//        authSettings.add(codeAuthSettings);
//        liveChannelInitRequest.setAuthSettings(authSettings);
//        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
//                liveChannelInitRequest);
//        Assert.assertNotNull(liveChannelInitResponse);
//        if (liveChannelInitResponse != null) {
//            //to do something ......
//            log.debug("测试创建并初始化频道 验证码观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
//        }
//
//        //删除测试数据
//        deleteChannel(liveChannelInitResponse.getChannelId());
//    }
    
    /**
     * 测试创建并初始化频道 付费观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
//    public void testCreateChannelInitPay() throws IOException, NoSuchAlgorithmException {
//        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
//        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
//                "创建并初始化频道-付费观看")
//                .setChannelPasswd("123321")
//                .setAutoPlay(1)
//                .setPlayerColor("#666666")
//                .setScene(LiveConstant.SceneType.ALONE.getDesc())
//                .setCategoryId(340019)
//                .setMaxViewer(0)
//                .setStartTime(1602306535000l)
//                .setDesc("这是一个描述")
//                .setPublisher("sadboy主讲")
//                .setLinkMicLimit(-1)
//                .setPureRtcEnabled("N")
//                .setReceiveChannelIds("213");
//        liveChannelInitRequest.setBasicSetting(basicSetting);
//        //付费观看
//        LiveChannelInitRequest.AuthSetting payAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
//                .setAuthType(LiveConstant.AuthType.PAY.getDesc())
//                .setPayAuthTips("欢迎使用POLYV直播平台")
//                .setPrice(0.01f)
//                .setWatchEndTime("1602578396000")
////                .setValidTimePeriod(720)
//                .setEnabled("Y");
//        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
//        authSettings.add(payAuthSettings);
//        liveChannelInitRequest.setAuthSettings(authSettings);
//        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
//                liveChannelInitRequest);
//        Assert.assertNotNull(liveChannelInitResponse);
//        if (liveChannelInitResponse != null) {
//            //to do something ......
//            log.debug("测试创建并初始化频道 付费观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
//        }
//
//        //删除测试数据
//        deleteChannel(liveChannelInitResponse.getChannelId());
//    }
    
    /**
     * 测试创建并初始化频道 白名单观看   TODO 未通过测试
     * @throws IOException 异常
     */
//    @Test
//    public void testCreateChannelInitPhone() throws IOException, NoSuchAlgorithmException {
//        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
//        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
//                "创建并初始化频道-白名单观看")
//                .setChannelPasswd("123321")
//                .setAutoPlay(1)
//                .setPlayerColor("#666666")
//                .setScene(LiveConstant.SceneType.ALONE.getDesc())
//                .setCategoryId(340019)
//                .setMaxViewer(0)
//                .setStartTime(1602306535000l)
//                .setDesc("这是一个描述")
//                .setPublisher("sadboy主讲")
//                .setLinkMicLimit(-1)
//                .setPureRtcEnabled("N")
//                .setReceiveChannelIds("213");
//        liveChannelInitRequest.setBasicSetting(basicSetting);
//        //白名单观看,设置观看条件为白名单观看时，必须已经存在白名单数据
//        LiveChannelInitRequest.AuthSetting phoneAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
//                .setAuthType(LiveConstant.AuthType.PHONE.getDesc())
//                .setEnabled("Y")
//                .setAuthTips("这是提示文案");
//        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
//        authSettings.add(phoneAuthSettings);
//        liveChannelInitRequest.setAuthSettings(authSettings);
//        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
//                liveChannelInitRequest);
//        Assert.assertNotNull(liveChannelInitResponse);
//        if (liveChannelInitResponse != null) {
//            //to do something ......
//            log.debug("测试创建并初始化频道 白名单观看创建成功{}" , JSON.toJSONString(liveChannelInitResponse));
//        }
//    }
    
    /**
     * 测试创建并初始化频道 登记观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
//    public void testCreateChannelInitInfo() throws IOException, NoSuchAlgorithmException {
//        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
//        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
//                "创建并初始化频道-登记观看")
//                .setChannelPasswd("123321")
//                .setAutoPlay(1)
//                .setPlayerColor("#666666")
//                .setScene(LiveConstant.SceneType.ALONE.getDesc())
//                .setCategoryId(340019)
//                .setMaxViewer(0)
//                .setStartTime(1602306535000l)
//                .setDesc("这是一个描述")
//                .setPublisher("sadboy主讲")
//                .setLinkMicLimit(-1)
//                .setPureRtcEnabled("N")
//                .setReceiveChannelIds("213");
//        liveChannelInitRequest.setBasicSetting(basicSetting);
//        //登记观看
//        LiveChannelInitRequest.InfoField nameInfo = liveChannelInitRequest.new InfoField().setName("姓名")
//                .setType("name")
//                .setPlaceholder("请输入姓名")
//                .setSms("N");
//        LiveChannelInitRequest.InfoField sexInfo = liveChannelInitRequest.new InfoField().setName("姓名")
//                .setType("option")
//                .setPlaceholder("请选择性别")
//                .setOptions("男,女")
//                .setSms("N");
//        List<LiveChannelInitRequest.InfoField> infoFields = new ArrayList<>();
//        infoFields.add(nameInfo);
//        infoFields.add(sexInfo);
//        LiveChannelInitRequest.AuthSetting infoAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
//                .setAuthType(LiveConstant.AuthType.INFO.getDesc())
//                .setEnabled("Y")
//                .setInfoFields(infoFields);
//        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
//        authSettings.add(infoAuthSettings);
//        liveChannelInitRequest.setAuthSettings(authSettings);
//        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
//                liveChannelInitRequest);
//        Assert.assertNotNull(liveChannelInitResponse);
//        if (liveChannelInitResponse != null) {
//            //to do something ......
//            log.debug("测试创建并初始化频道 登记观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
//        }
//
//        //删除测试数据
//        deleteChannel(liveChannelInitResponse.getChannelId());
//    }
    
    /**
     * 测试创建并初始化频道 自定义授权观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
//    public void testCreateChannelInitCustom() throws IOException, NoSuchAlgorithmException {
//        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
//        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
//                "创建并初始化频道-自定义授权观看")
//                .setChannelPasswd("123321")
//                .setAutoPlay(1)
//                .setPlayerColor("#666666")
//                .setScene(LiveConstant.SceneType.ALONE.getDesc())
//                .setCategoryId(340019)
//                .setMaxViewer(0)
//                .setStartTime(1602306535000l)
//                .setDesc("这是一个描述")
//                .setPublisher("sadboy主讲")
//                .setLinkMicLimit(-1)
//                .setPureRtcEnabled("N")
//                .setReceiveChannelIds("213");
//        liveChannelInitRequest.setBasicSetting(basicSetting);
//        //自定义授权观看
//        LiveChannelInitRequest.AuthSetting infoAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
//                .setAuthType(LiveConstant.AuthType.CUSTOM.getDesc())
//                .setEnabled("Y")
//                .setCustomKey("ttttt")
//                .setCustomUri("http://www.polyv.net");
//        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
//        authSettings.add(infoAuthSettings);
//        liveChannelInitRequest.setAuthSettings(authSettings);
//        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
//                liveChannelInitRequest);
//        Assert.assertNotNull(liveChannelInitResponse);
//        if (liveChannelInitResponse != null) {
//            //to do something ......
//            log.debug("测试创建并初始化频道 自定义授权观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
//        }
//
//        //删除测试数据
//        deleteChannel(liveChannelInitResponse.getChannelId());
//    }
    
    /**
     * 测试创建并初始化频道 外部授权观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
//    public void testCreateChannelInitExternal() throws IOException, NoSuchAlgorithmException {
//        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
//        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
//                "创建并初始化频道-外部授权观看")
//                .setChannelPasswd("123321")
//                .setAutoPlay(1)
//                .setPlayerColor("#666666")
//                .setScene(LiveConstant.SceneType.ALONE.getDesc())
//                .setCategoryId(340019)
//                .setMaxViewer(0)
//                .setStartTime(1602306535000l)
//                .setDesc("这是一个描述")
//                .setPublisher("sadboy主讲")
//                .setLinkMicLimit(-1)
//                .setPureRtcEnabled("N")
//                .setReceiveChannelIds("213");
//        liveChannelInitRequest.setBasicSetting(basicSetting);
//        //自定义授权观看
//        LiveChannelInitRequest.AuthSetting infoAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
//                .setAuthType(LiveConstant.AuthType.EXTERNAL.getDesc())
//                .setEnabled("Y")
//                .setExternalKey("externalKey")
//                .setExternalUri("http://www.baidu.com")
//                .setExternalRedirectUri("http://www.polyv.net");
//        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
//        authSettings.add(infoAuthSettings);
//        liveChannelInitRequest.setAuthSettings(authSettings);
//        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
//                liveChannelInitRequest);
//        Assert.assertNotNull(liveChannelInitResponse);
//        if (liveChannelInitResponse != null) {
//            //to do something ......
//            log.debug("测试创建并初始化频道 外部授权观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
//        }
//
//        //删除测试数据
//        deleteChannel(liveChannelInitResponse.getChannelId());
//    }
    
    /**
     * 测试创建并初始化频道 直接授权观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
//    public void testCreateChannelInitDirect() throws IOException, NoSuchAlgorithmException {
//        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
//        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
//                "创建并初始化频道-直接授权观看")
//                .setChannelPasswd("123321")
//                .setAutoPlay(1)
//                .setPlayerColor("#666666")
//                .setScene(LiveConstant.SceneType.ALONE.getDesc())
//                .setCategoryId(340019)
//                .setMaxViewer(0)
//                .setStartTime(1602306535000l)
//                .setDesc("这是一个描述")
//                .setPublisher("sadboy主讲")
//                .setLinkMicLimit(-1)
//                .setPureRtcEnabled("N")
//                .setReceiveChannelIds("213");
//        liveChannelInitRequest.setBasicSetting(basicSetting);
//        //自定义授权观看
//        LiveChannelInitRequest.AuthSetting infoAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
//                .setAuthType(LiveConstant.AuthType.DIRECT.getDesc())
//                .setEnabled("Y")
//                .setDirectKey("directKey");
//        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
//        authSettings.add(infoAuthSettings);
//        liveChannelInitRequest.setAuthSettings(authSettings);
//        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
//                liveChannelInitRequest);
//        Assert.assertNotNull(liveChannelInitResponse);
//        if (liveChannelInitResponse != null) {
//            //to do something ......
//            log.debug("测试创建并初始化频道 直接授权观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
//        }
//
//        //删除测试数据
//        deleteChannel(liveChannelInitResponse.getChannelId());
//    }
    
    /**
     * 测试修改频道的相关设置
     */
    @Test
    public void testUpdateChannelSetting() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        LiveChannelSettingRequest.BasicSetting basicSetting = liveChannelSettingRequest.new BasicSetting().setName(
                "Junit测试(勿删)")
                .setChannelPasswd("123321")
                .setCategoryId(340019)
                .setMaxViewer(0)
                .setPageView(1000)
                .setLikes(2000)
                .setCoverImg("https://www.polyv.net/")
                .setStartTime(1602306535000l)
                .setDesc("这是一个描述")
                .setPublisher("sadboy主讲")
                .setLinkMicLimit(-1)
                .setReceiveChannelIds("213");
        LiveChannelSettingRequest.AuthSetting authSetting = liveChannelSettingRequest.new AuthSetting().setAuthType(
                LiveConstant.AuthType.CODE.getDesc())
                .setRank(1)
                .setEnabled("Y")
                .setAuthCode("123456")
                .setQcodeTips("提示文案")
                .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
        List<LiveChannelSettingRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(authSetting);
        liveChannelSettingRequest.setChannelId(channelId).setBasicSetting(basicSetting).setAuthSettings(authSettings);
        String liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                liveChannelSettingRequest);
        Assert.assertNotNull(liveChannelSettingResponse);
        if (liveChannelSettingResponse == null) {
            //to do something ......
            log.debug("测试修改频道的相关设置成功");
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试批量创建频道，注释避免频繁创建
     * @throws IOException
     */
//    @Test
//    public void testCreateChannelList() throws IOException, NoSuchAlgorithmException {
//        LiveCreateChannelListRequest liveCreateChannelListRequest = new LiveCreateChannelListRequest();
//        List<LiveChannelBasicDTO> channels = new ArrayList<>();
//        for (int i = 0; i <= 2; i++) {
//            LiveChannelBasicDTO liveChannel = new LiveChannelBasicDTO();
//            liveChannel.setName("批量创建" + i)
//                    .setChannelPasswd("123456" + i)
//                    .setCourseId("c" + i)
//                    .setAutoPlay(1)
//                    .setPlayerColor("#666666")
//                    .setScene(LiveConstant.SceneType.ALONE.getDesc())
//                    .setCategoryId(340019);
//            channels.add(liveChannel);
//        }
//        liveCreateChannelListRequest.setChannels(channels).setRequestId("123456");
//        LiveCreateChannelListResponse liveCreateChannelListResponse = new LiveChannelServiceImpl().createChannelList(
//                liveCreateChannelListRequest);
//        Assert.assertNotNull(liveCreateChannelListResponse);
//        if (liveCreateChannelListResponse != null) {
//            //to do something ......
//            log.debug("频道批量创建成功{}", JSON.toJSONString(liveCreateChannelListResponse));
//        }
//
//        //删除测试数据
//        List<LiveChannelResponse> channelsResponse = liveCreateChannelListResponse.getChannels();
//        for (LiveChannelResponse temp : channelsResponse) {
//            deleteChannel(temp.getChannelId());
//        }
//    }
    
    /**
     * 测试设置频道详情：修改密码功能
     * @throws IOException
     */
    @Test
    public void testUpdateChannelDetailPassword() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        String newPassword = "1234567";
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("channelPasswd")
                .setValue(newPassword)
                .setRequestId("2860257a405447e1bbbe9161da2dee73");
        String liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(liveChannelDetailRequest);
        Assert.assertNotNull(liveChannelDetailResponse);
        if ("true".equals(liveChannelDetailResponse)) {
            //to do something ......
            log.debug("频道{}修改密码为{}成功{}", channelId, newPassword, liveChannelDetailResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试设置频道详情：修改scene字段
     * @throws IOException
     */
    @Test
    public void testUpdateChannelScene() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        String value = "ppt";
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("scene")
                .setValue(value)
                .setRequestId("2860257a405447e1bbbe9161da2dee74");
        String liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(liveChannelDetailRequest);
        Assert.assertNotNull(liveChannelDetailResponse);
        if ("true".equals(liveChannelDetailResponse)) {
            //to do something ......
            log.debug("频道{}修改scene为{}成功{}", channelId, value, liveChannelDetailResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试设置频道详情：设置最大同时观看人数
     * @throws IOException
     */
    @Test
    public void testUpdateChannel() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        String value = "2147483647";
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("maxViewer")
                .setValue(value)
                .setRequestId("2860257a405447e1bbbe9161da2dee75");
        String liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(liveChannelDetailRequest);
        Assert.assertNotNull(liveChannelDetailResponse);
        if ("true".equals(liveChannelDetailResponse)) {
            //to do something ......
            log.debug("频道{}修改maxViewer为{}成功{}", channelId, value, liveChannelDetailResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 查询课件重制任务列表
     * @throws IOException
     */
    @Test
    public void testListPPTRecord() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest = new LiveListChannelPPTRecordRequest();
        liveListChannelPPTRecordRequest.setChannelId(channelId).setCurrentPage(1);
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().listPPTRecord(
                liveListChannelPPTRecordRequest);
        Assert.assertNotNull(liveListChannelPPTRecordResponse);
        if (liveListChannelPPTRecordResponse != null) {
            //to do something ......
            log.debug("查询课件重制任务列表信息成功{}", JSON.toJSONString(liveListChannelPPTRecordResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 设置频道密码
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelPassword() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest = new LiveChannelPasswordSettingRequest();
        liveChannelPasswordSettingRequest.setChannelId(channelId).setPasswd("987654");
        String updateChannelPasswordResponse = new LiveChannelOperateServiceImpl().updateChannelPassword(
                liveChannelPasswordSettingRequest);
        Assert.assertNotNull(updateChannelPasswordResponse);
        if ("true".equals(updateChannelPasswordResponse)) {
            //to do something ......
            log.debug("设置频道密码成功{}", JSON.toJSONString(updateChannelPasswordResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 删除直播频道，注释避免测试数据出错
     */
//    @Test
//    public void testDeleteChannel() throws IOException, NoSuchAlgorithmException {
//        //准备测试数据
//        Integer channelId = createChannel();
//
//        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
//        liveDeleteChannelRequest.setChannelId(channelId);
//        String liveDeleteChannelResponse = new LiveChannelServiceImpl().deleteChannel(liveDeleteChannelRequest);
//        Assert.assertNotNull(liveDeleteChannelResponse);
//        if ("true".equals(liveDeleteChannelResponse)) {
//            //to do something ......
//            log.debug("删除直播频道成功{}", JSON.toJSONString(liveDeleteChannelResponse));
//        }
//    }
    
    /**
     * 批量删除频道，注释避免测试数据出错
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testDeleteChannelList() throws IOException, NoSuchAlgorithmException {
//        //准备测试数据
//        Integer[] channelIds = new Integer[]{createChannel(), createChannel(), createChannel()};
//
//        LiveDeleteChannelListRequest liveDeleteChannelListRequest = new LiveDeleteChannelListRequest();
//        liveDeleteChannelListRequest.setChannelIds(channelIds);
//        String liveDeleteChannelListResponse = new LiveChannelServiceImpl().deleteChannelList(
//                liveDeleteChannelListRequest);
//        Assert.assertNotNull(liveDeleteChannelListResponse);
//        if ("true".equals(liveDeleteChannelListResponse)) {
//            //to do something ......
//            log.debug("批量删除频道成功{}", JSON.toJSONString(liveDeleteChannelListResponse));
//        }
//    }
    
    /**
     * 测试设置频道单点登陆token
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateChannelToken() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveCreateChannelTokenRequest liveCreateChannelTokenRequest = new LiveCreateChannelTokenRequest();
        liveCreateChannelTokenRequest.setChannelId(channelId).setToken("testToken");
        String liveCreateChannelTokenResponse = new LiveChannelOperateServiceImpl().createChannelToken(
                liveCreateChannelTokenRequest);
        Assert.assertNotNull(liveCreateChannelTokenResponse);
        if ("success".equals(liveCreateChannelTokenResponse)) {
            //to do something ......
            log.debug("设置频道单点登陆token成功{}", JSON.toJSONString(liveCreateChannelTokenResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试查询频道信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelInfo() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveChannelInfoRequest liveChannelInfoRequest = new LiveChannelInfoRequest();
        liveChannelInfoRequest.setChannelId(channelId);
        LiveChannelInfoResponse liveChannelInfoResponse = new LiveChannelOperateServiceImpl().channelInfo(
                liveChannelInfoRequest);
        Assert.assertNotNull(liveChannelInfoResponse);
        if (liveChannelInfoResponse != null) {
            //to do something ......
            log.debug("查询频道信息成功{}", JSON.toJSONString(liveChannelInfoResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试查询频道基本信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelBasicInfo() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        liveChannelBasicInfoRequest.setChannelId(channelId);
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().channelBasicInfo(
                liveChannelBasicInfoRequest);
        Assert.assertNotNull(liveChannelBasicInfoResponse);
        if (liveChannelBasicInfoResponse != null) {
            //to do something ......
            log.debug("查询频道基本信息成功{}", JSON.toJSONString(liveChannelBasicInfoResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试查询授权和连麦的token
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelAuthToken() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveChannelAuthTokenRequest liveChannelAuthTokenRequest = new LiveChannelAuthTokenRequest();
        liveChannelAuthTokenRequest.setChannelId(channelId).setRole(LiveConstant.Role.ADMIN.getDesc()).setOrigin(null);
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse = new LiveChannelOperateServiceImpl().channelAuthToken(
                liveChannelAuthTokenRequest);
        Assert.assertNotNull(liveChannelAuthTokenResponse);
        if (liveChannelAuthTokenResponse != null) {
            //to do something ......
            log.debug("查询授权和连麦的token成功{}", JSON.toJSONString(liveChannelAuthTokenResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试创建子频道-三分屏添加Guest
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateSonChannelGuest() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        liveCreateSonChannelRequest.setChannelId(channelId)
                .setRole("Guest")
                .setNickname("sadboy")
                .setActor("教授")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0");
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = new LiveChannelOperateServiceImpl().createSonChannel(
                liveCreateSonChannelRequest);
        Assert.assertNotNull(liveCreateSonChannelResponse);
        if (liveCreateSonChannelResponse != null) {
            //to do something ......
            log.debug("创建子频道成功{}", JSON.toJSONString(liveCreateSonChannelResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试创建子频道-非三分屏添加助教
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateSonChannelAssistant() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        liveCreateSonChannelRequest.setChannelId(channelId)
                .setRole(null)
                .setNickname("sadboy")
                .setActor("教授")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0");
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = new LiveChannelOperateServiceImpl().createSonChannel(
                liveCreateSonChannelRequest);
        Assert.assertNotNull(liveCreateSonChannelResponse);
        if (liveCreateSonChannelResponse != null) {
            //to do something ......
            log.debug("创建子频道成功{}", JSON.toJSONString(liveCreateSonChannelResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试设置子频道信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateSonChannelInfo() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        String sonChannelId = createSonChannel(channelId);
        
        LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest = new LiveUpdateSonChannelInfoRequest();
        liveUpdateSonChannelInfoRequest.setChannelId(channelId)
                .setAccount(sonChannelId)
                .setNickname("sadboy")
                .setPassword("137890")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0")
                .setActor("教授")
                .setPageTurnEnabled("Y")
                .setNotifyEnabled("Y");
        String updateSonChannelInfoResponse = new LiveChannelOperateServiceImpl().updateSonChannelInfo(
                liveUpdateSonChannelInfoRequest);
        Assert.assertNotNull(updateSonChannelInfoResponse);
        if ("success".equals(updateSonChannelInfoResponse)) {
            //to do something ......
            log.debug("设置子频道信息成功{}", updateSonChannelInfoResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试设置子频道单点登陆token
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateSonChannelToken() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        String sonChannelId = createSonChannel(channelId);
        
        LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest = new LiveCreateSonChannelTokenRequest();
        liveCreateSonChannelTokenRequest.setAccount(sonChannelId).setToken("sonChannelLogintoken");
        String liveCreateSonChannelTokenResponse = new LiveChannelOperateServiceImpl().createSonChannelToken(
                liveCreateSonChannelTokenRequest);
        Assert.assertNotNull(liveCreateSonChannelTokenResponse);
        if ("success".equals(liveCreateSonChannelTokenResponse)) {
            //to do something ......
            log.debug("设置子频道单点登陆token成功{}", liveCreateSonChannelTokenResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试查询子频道信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSonChannelInfo() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        String sonChannelId = createSonChannel(channelId);
        
        LiveSonChannelInfoRequest liveSonChannelInfoRequest = new LiveSonChannelInfoRequest();
        liveSonChannelInfoRequest.setAccount(sonChannelId).setChannelId(channelId);
        LiveSonChannelInfoResponse liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().sonChannelInfo(
                liveSonChannelInfoRequest);
        Assert.assertNotNull(liveSonChannelInfoResponse);
        if (liveSonChannelInfoResponse != null) {
            //to do something ......
            log.debug("测试查询子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试查询频道号下所有子频道信息
     */
    @Test
    public void testSonChannelInfoList() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        liveSonChannelInfoListRequest.setChannelId(channelId);
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().sonChannelInfoList(
                liveSonChannelInfoListRequest);
        Assert.assertNotNull(liveSonChannelInfoResponse);
        if (liveSonChannelInfoResponse != null) {
            //to do something ......
            log.debug("查询频道号下所有子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试删除子频道
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteSonChannel() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        String sonChannelId = createSonChannel(channelId);
        
        LiveDeleteSonChannelRequest liveDeleteSonChannelRequest = new LiveDeleteSonChannelRequest();
        liveDeleteSonChannelRequest.setChannelId(channelId).setAccount(sonChannelId);
        String liveDeleteSonChannelRespose = new LiveChannelOperateServiceImpl().deleteSonChannel(liveDeleteSonChannelRequest);
        Assert.assertNotNull(liveDeleteSonChannelRespose);
        if ("true".equals(liveDeleteSonChannelRespose)) {
            //to do something ......
            log.debug("测试删除子频道成功{}", liveDeleteSonChannelRespose);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    

    

    
    /**
     * 测试查询频道实时推流信息（讲师未进入直播间或未开启上课等情况，将抛出"channel status not live"异常）
     * 该测试类必须在开启直播中才能测试，先注释掉
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testchannelStreamInfo() throws IOException, NoSuchAlgorithmException {
//        LiveChannelStreamInfoRequest liveChannelStreamInfoRequest = new LiveChannelStreamInfoRequest();
//        liveChannelStreamInfoRequest.setChannelId(1951952);
//        LiveChannelStreamInfoResponse liveChannelStreamInfoResponse = new LiveChannelServiceImpl().channelStreamInfo(
//                liveChannelStreamInfoRequest);
//        Assert.assertNotNull(liveChannelStreamInfoResponse);
//        if (liveChannelStreamInfoResponse != null) {
//            //to do something ......
//            log.debug("批量查询频道直播流状态成功{}",JSON.toJSONString(liveChannelStreamInfoResponse));
//        }
//    }
    
    /**
     * 测试将点播中的视频添加到视频库
     * TODO 该测试用例字段vid不知从何处设置，需后台开发提供支持
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testAddChannelVideoPlayback() throws IOException, NoSuchAlgorithmException {
//        LiveCreateChannelVideoPlaybackRequest liveCreateChannelVideoPlaybackRequest =
//                new LiveCreateChannelVideoPlaybackRequest();
//        liveCreateChannelVideoPlaybackRequest.setChannelId(1958888)
//                .setVid("1b448be32340ff32f52c5db0f9e06a75_1")
//                .setSetAsDefault("N")
//                .setListType("playback");
//        LiveCreateChannelVideoPlaybackResponse liveCreateChannelVideoPlaybackResponse =
//                new LiveChannelServiceImpl().addChannelVideoPlayback(
//                liveCreateChannelVideoPlaybackRequest);
//        Assert.assertNotNull(liveCreateChannelVideoPlaybackResponse);
//        if (liveCreateChannelVideoPlaybackResponse != null) {
//            //to do something ......
//            log.debug("测试将点播中的视频添加到视频库成功{}", JSON.toJSONString(liveCreateChannelVideoPlaybackResponse));
//        }
//    }
    
    /**
     * 测试异步合并直播录制文件
     * TODO 删除生成的视频
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testMergeChannelVideoAsync() throws IOException, NoSuchAlgorithmException {
//        LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest = new LiveMergeChannelVideoAsyncRequest();
//        liveMergeChannelVideoAsyncRequest.setChannelId(1951952)
//                .setFileIds("dfcfabd4e3db60892b625aeddf80b242,4329a8920588b257c3d66414bd37f8d8")
//                .setFileName("测试合并-可删除")
//                .setCallbackUrl(null)
//                .setAutoConvert("Y")
//                .setMergeMp4("Y");
//        String liveMergeChannelVideoAsyncResponse = new LiveChannelServiceImpl().mergeChannelVideoAsync(
//                liveMergeChannelVideoAsyncRequest);
//        Assert.assertNotNull(liveMergeChannelVideoAsyncResponse);
//        if ("submit success".equals(liveMergeChannelVideoAsyncResponse)) {
//            //to do something ......
//            log.debug("测试异步合并直播录制文件,具体是否成功以回调为准{}", liveMergeChannelVideoAsyncResponse);
//        }
//    }
    
    /**
     * 测试异步批量转存录制文件到点播
     * TODO 删除生成的视频
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testConvertChannelVideoListAsync() throws IOException, NoSuchAlgorithmException {
//        LiveConvertChannelVideoListAsyncRequest liveConvertChannelVideoListAsyncRequest =
//                new LiveConvertChannelVideoListAsyncRequest();
//        liveConvertChannelVideoListAsyncRequest.setChannelId(1951952)
//                .setFileIds("dfcfabd4e3db60892b625aeddf80b242,4329a8920588b257c3d66414bd37f8d8")
//                .setFileName("删除-直播录制转点播")
//                .setCataId(null)
//                .setCallbackUrl(null);
//        String liveConvertChannelVideoResponse = new LiveChannelServiceImpl().convertChannelVideoListAsync(
//                liveConvertChannelVideoListAsyncRequest);
//        Assert.assertNotNull(liveConvertChannelVideoResponse);
//        if ("submit success".equals(liveConvertChannelVideoResponse)) {
//            //to do something ......
//            log.debug("测试异步批量转存录制文件到点播,具体是否成功以回调为准{}", liveConvertChannelVideoResponse);
//        }
//    }
    

    

    

    
    /**
     * 测试设置视频库列表排序
     * TODO 对接未通过，暂时注释
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testChannelVideoSort() throws IOException, NoSuchAlgorithmException {
//        List<String> videoIdList = listChannelVideoIds();
//        Collections.shuffle(videoIdList);
//        LiveChannelVideoSortRequest liveChannelVideoSortRequest = new LiveChannelVideoSortRequest();
//        liveChannelVideoSortRequest.setChannelId(1951952)
//                .setVideoIds(videoIdList.toArray(new String[]{}))
//                .setListType("playback");
//        String liveChannelVideoSortResponse = new LiveChannelServiceImpl().channelVideoSort(
//                liveChannelVideoSortRequest);
//        Assert.assertNotNull(liveChannelVideoSortResponse);
//        if ("".equals(liveChannelVideoSortResponse)) {
//            //to do something ......
//            log.debug("测试设置视频库列表排序成功{}", JSON.toJSONString(liveChannelVideoSortResponse));
//        }
//    }
    

    
    /**
     * 测试创建重制课件任务
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testCreateChannelPPTRecordTask() throws IOException, NoSuchAlgorithmException {
//        LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest = new LiveCreateChannelPPTRecordRequest();
//        liveCreateChannelPPTRecordRequest.setChannelId(1951952).setVideoId("07f5bbeb67");
//        String liveCreateChannelPPTRecordResponse = new LiveChannelServiceImpl().createChannelPPTRecordTask(
//                liveCreateChannelPPTRecordRequest);
//        Assert.assertNotNull(liveCreateChannelPPTRecordResponse);
//        if (liveCreateChannelPPTRecordResponse != null) {
//            //to do something ......
//            log.debug("测试创建重制课件任务成功{}", liveCreateChannelPPTRecordResponse);
//        }
//    }
    
    /**
     * 测试删除直播暂存中的录制文件
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testDeleteChannelVideo() throws IOException, NoSuchAlgorithmException {
//        LiveDeleteChannelVideoRequest liveDeleteChannelVideoRequest = new LiveDeleteChannelVideoRequest();
//        liveDeleteChannelVideoRequest.setChannelId(1951952).setStartTime("20201016111234");
//        String liveDeleteChannelVideoResponse = new LiveChannelServiceImpl().deleteChannelVideo(
//                liveDeleteChannelVideoRequest);
//        Assert.assertNotNull(liveDeleteChannelVideoResponse);
//        if (liveDeleteChannelVideoResponse != null) {
//            //to do something ......
//            log.debug("测试删除直播暂存中的录制文件");
//        }
//    }
    
    /**
     * 测试删除视频库列表中的视频
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testDeleteChannelPlaybackVideo() throws IOException, NoSuchAlgorithmException {
//        int channelId = 1951952;
//        String videoId = "07f5bbeb67";
//        LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest =
//                new LiveDeleteChannelPlaybackVideoRequest();
//        liveDeleteChannelPlaybackVideoRequest.setChannelId(channelId).setVideoId(videoId).setListType("playback");
//        String liveDeleteChannelPlaybackVideoResponse = new LiveChannelServiceImpl().deleteChannelPlaybackVideo
//        (liveDeleteChannelPlaybackVideoRequest);
//                Assert.assertNotNull(liveDeleteChannelPlaybackVideoResponse);
//        if ("success".equals(liveDeleteChannelPlaybackVideoResponse)) {
//            //to do something ......
//            log.debug("测试删除视频库列表中的视频成功");
//        }
//    }
    

    
    
    

    
}
