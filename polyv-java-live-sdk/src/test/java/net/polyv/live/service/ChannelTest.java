package net.polyv.live.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.channel.LiveChannelAuthTokenRequest;
import net.polyv.live.entity.channel.LiveChannelAuthTokenResponse;
import net.polyv.live.entity.channel.LiveChannelBasicInfoRequest;
import net.polyv.live.entity.channel.LiveChannelBasicInfoResponse;
import net.polyv.live.entity.channel.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.LiveChannelInfoRequest;
import net.polyv.live.entity.channel.LiveChannelInfoResponse;
import net.polyv.live.entity.channel.LiveChannelInitRequest;
import net.polyv.live.entity.channel.LiveChannelInitResponse;
import net.polyv.live.entity.channel.LiveChannelPasswordSettingRequest;
import net.polyv.live.entity.channel.LiveChannelPlaybackEnabledInfoRequest;
import net.polyv.live.entity.channel.LiveChannelPlaybackEnabledRequest;
import net.polyv.live.entity.channel.LiveChannelPlaybackSettingRequest;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.entity.channel.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.LiveChannelVideoListRequest;
import net.polyv.live.entity.channel.LiveChannelVideoListResponse;
import net.polyv.live.entity.channel.LiveChannelVideoOnlyRequest;
import net.polyv.live.entity.channel.LiveChannelVideoOnlyResponse;
import net.polyv.live.entity.channel.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.LiveCreateChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveCreateChannelTokenRequest;
import net.polyv.live.entity.channel.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.LiveCreateSonChannelTokenRequest;
import net.polyv.live.entity.channel.LiveCutoffChannelStreamRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelListRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelRequest;
import net.polyv.live.entity.channel.LiveDeleteSonChannelRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.channel.LiveListChannelSessionInfoRequest;
import net.polyv.live.entity.channel.LiveListChannelSessionInfoResponse;
import net.polyv.live.entity.channel.LiveListChannelStreamStatusRequest;
import net.polyv.live.entity.channel.LiveListChannelStreamStatusResponse;
import net.polyv.live.entity.channel.LiveListChannelVideoLibraryRequest;
import net.polyv.live.entity.channel.LiveListChannelVideoLibraryResponse;
import net.polyv.live.entity.channel.LiveResumeChannelStreamRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoListRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoListResponse;
import net.polyv.live.entity.channel.LiveSonChannelInfoRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoResponse;
import net.polyv.live.entity.channel.LiveUpdateSonChannelInfoRequest;
import net.polyv.live.entity.dto.LiveChannelBasicDTO;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

/**
 * @author: thomas
 **/
@Slf4j
public class ChannelTest extends BaseTest {
    /**
     * 系统账号密钥配置
     */
    public ChannelTest() {
        String appId = "frlr1zazn3";
        String appSecret = "5d5ade8f71f24bb9a2d1176cd607dd17";
        String userId = "1b448be323";
        LiveGlobalConfig.init(appId, userId, appSecret, 60 * 10000, 60);
        System.out.println("--初始化完成--");
    }
    
    /**
     * 测试创建频道
     * @throws IOException
     */
    @Test
    public void testCreateChannel() throws IOException, NoSuchAlgorithmException {
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        liveChannelRequest.setName("Spring 知识精讲")
                .setChannelPasswd("666888")
                .setRequestId("2860257a405447e1bbbe9161da2dee72");
        LiveChannelResponse liveChannelResponse = new LiveChannelServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        if (liveChannelResponse != null) {
            //to do something ......
            log.debug("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelResponse.getChannelId());
    }
    
    /**
     * 测试创建并初始化频道 验证码观看
     * @throws IOException 异常
     */
    @Test
    public void testCreateChannelInitCode() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
                "创建并初始化频道-验证码观看")
                .setChannelPasswd("123321")
                .setAutoPlay(1)
                .setPlayerColor("#666666")
                .setScene(LiveConstant.SceneType.ALONE.getDesc())
                .setCategoryId(340019)
                .setMaxViewer(0)
                .setStartTime(1602306535000l)
                .setDesc("这是一个描述")
                .setPublisher("sadboy主讲")
                .setLinkMicLimit(-1)
                .setPureRtcEnabled("N")
                .setReceiveChannelIds("213");
        liveChannelInitRequest.setBasicSetting(basicSetting);
        //验证码观看
        LiveChannelInitRequest.AuthSetting codeAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.CODE.getDesc())
                .setEnabled("Y")
                .setAuthCode("123456")
                .setQcodeTips("提示文案")
                .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(codeAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 验证码观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelInitResponse.getChannelId());
    }
    
    /**
     * 测试创建并初始化频道 付费观看
     * @throws IOException 异常
     */
    @Test
    public void testCreateChannelInitPay() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
                "创建并初始化频道-付费观看")
                .setChannelPasswd("123321")
                .setAutoPlay(1)
                .setPlayerColor("#666666")
                .setScene(LiveConstant.SceneType.ALONE.getDesc())
                .setCategoryId(340019)
                .setMaxViewer(0)
                .setStartTime(1602306535000l)
                .setDesc("这是一个描述")
                .setPublisher("sadboy主讲")
                .setLinkMicLimit(-1)
                .setPureRtcEnabled("N")
                .setReceiveChannelIds("213");
        liveChannelInitRequest.setBasicSetting(basicSetting);
        //付费观看
        LiveChannelInitRequest.AuthSetting payAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.PAY.getDesc())
                .setPayAuthTips("欢迎使用POLYV直播平台")
                .setPrice(0.01f)
                .setWatchEndTime("1602578396000")
//                .setValidTimePeriod(720)
                .setEnabled("Y");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(payAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 付费观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelInitResponse.getChannelId());
    }
    
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
     * 测试创建并初始化频道 登记观看
     * @throws IOException 异常
     */
    @Test
    public void testCreateChannelInitInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
                "创建并初始化频道-登记观看")
                .setChannelPasswd("123321")
                .setAutoPlay(1)
                .setPlayerColor("#666666")
                .setScene(LiveConstant.SceneType.ALONE.getDesc())
                .setCategoryId(340019)
                .setMaxViewer(0)
                .setStartTime(1602306535000l)
                .setDesc("这是一个描述")
                .setPublisher("sadboy主讲")
                .setLinkMicLimit(-1)
                .setPureRtcEnabled("N")
                .setReceiveChannelIds("213");
        liveChannelInitRequest.setBasicSetting(basicSetting);
        //登记观看
        LiveChannelInitRequest.InfoField nameInfo = liveChannelInitRequest.new InfoField().setName("姓名")
                .setType("name")
                .setPlaceholder("请输入姓名")
                .setSms("N");
        LiveChannelInitRequest.InfoField sexInfo = liveChannelInitRequest.new InfoField().setName("姓名")
                .setType("option")
                .setPlaceholder("请选择性别")
                .setOptions("男,女")
                .setSms("N");
        List<LiveChannelInitRequest.InfoField> infoFields = new ArrayList<>();
        infoFields.add(nameInfo);
        infoFields.add(sexInfo);
        LiveChannelInitRequest.AuthSetting infoAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.INFO.getDesc())
                .setEnabled("Y")
                .setInfoFields(infoFields);
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 登记观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelInitResponse.getChannelId());
    }
    
    /**
     * 测试创建并初始化频道 自定义授权观看
     * @throws IOException 异常
     */
    @Test
    public void testCreateChannelInitCustom() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
                "创建并初始化频道-自定义授权观看")
                .setChannelPasswd("123321")
                .setAutoPlay(1)
                .setPlayerColor("#666666")
                .setScene(LiveConstant.SceneType.ALONE.getDesc())
                .setCategoryId(340019)
                .setMaxViewer(0)
                .setStartTime(1602306535000l)
                .setDesc("这是一个描述")
                .setPublisher("sadboy主讲")
                .setLinkMicLimit(-1)
                .setPureRtcEnabled("N")
                .setReceiveChannelIds("213");
        liveChannelInitRequest.setBasicSetting(basicSetting);
        //自定义授权观看
        LiveChannelInitRequest.AuthSetting infoAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.CUSTOM.getDesc())
                .setEnabled("Y")
                .setCustomKey("ttttt")
                .setCustomUri("http://www.polyv.net");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 自定义授权观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelInitResponse.getChannelId());
    }
    
    /**
     * 测试创建并初始化频道 外部授权观看
     * @throws IOException 异常
     */
    @Test
    public void testCreateChannelInitExternal() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
                "创建并初始化频道-外部授权观看")
                .setChannelPasswd("123321")
                .setAutoPlay(1)
                .setPlayerColor("#666666")
                .setScene(LiveConstant.SceneType.ALONE.getDesc())
                .setCategoryId(340019)
                .setMaxViewer(0)
                .setStartTime(1602306535000l)
                .setDesc("这是一个描述")
                .setPublisher("sadboy主讲")
                .setLinkMicLimit(-1)
                .setPureRtcEnabled("N")
                .setReceiveChannelIds("213");
        liveChannelInitRequest.setBasicSetting(basicSetting);
        //自定义授权观看
        LiveChannelInitRequest.AuthSetting infoAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.EXTERNAL.getDesc())
                .setEnabled("Y")
                .setExternalKey("externalKey")
                .setExternalUri("http://www.baidu.com")
                .setExternalRedirectUri("http://www.polyv.net");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 外部授权观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelInitResponse.getChannelId());
    }
    
    /**
     * 测试创建并初始化频道 直接授权观看
     * @throws IOException 异常
     */
    @Test
    public void testCreateChannelInitDirect() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
                "创建并初始化频道-直接授权观看")
                .setChannelPasswd("123321")
                .setAutoPlay(1)
                .setPlayerColor("#666666")
                .setScene(LiveConstant.SceneType.ALONE.getDesc())
                .setCategoryId(340019)
                .setMaxViewer(0)
                .setStartTime(1602306535000l)
                .setDesc("这是一个描述")
                .setPublisher("sadboy主讲")
                .setLinkMicLimit(-1)
                .setPureRtcEnabled("N")
                .setReceiveChannelIds("213");
        liveChannelInitRequest.setBasicSetting(basicSetting);
        //自定义授权观看
        LiveChannelInitRequest.AuthSetting infoAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.DIRECT.getDesc())
                .setEnabled("Y")
                .setDirectKey("directKey");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 直接授权观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelInitResponse.getChannelId());
    }
    
    /**
     * 测试修改频道的相关设置
     */
    @Test
    public void testUpdateChannelSetting() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        LiveChannelSettingRequest.BasicSetting basicSetting = liveChannelSettingRequest.new BasicSetting().setName(
                "9999999999")
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
        String liveChannelSettingResponse = new LiveChannelServiceImpl().updateChannelSetting(
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
     * 测试批量创建频道
     * @throws IOException
     */
    @Test
    public void testCreateChannelList() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelListRequest liveCreateChannelListRequest = new LiveCreateChannelListRequest();
        List<LiveChannelBasicDTO> channels = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            LiveChannelBasicDTO liveChannel = new LiveChannelBasicDTO();
            liveChannel.setName("批量创建" + i)
                    .setChannelPasswd("123456" + i)
                    .setCourseId("c" + i)
                    .setAutoPlay(1)
                    .setPlayerColor("#666666")
                    .setScene(LiveConstant.SceneType.ALONE.getDesc())
                    .setCategoryId(340019);
            channels.add(liveChannel);
        }
        liveCreateChannelListRequest.setChannels(channels).setRequestId("123456");
        LiveCreateChannelListResponse liveCreateChannelListResponse = new LiveChannelServiceImpl().createChannelList(
                liveCreateChannelListRequest);
        Assert.assertNotNull(liveCreateChannelListResponse);
        if (liveCreateChannelListResponse != null) {
            //to do something ......
            log.debug("频道批量创建成功{}", JSON.toJSONString(liveCreateChannelListResponse));
        }
        
        //删除测试数据
        List<LiveChannelResponse> channelsResponse = liveCreateChannelListResponse.getChannels();
        for (LiveChannelResponse temp : channelsResponse) {
            deleteChannel(temp.getChannelId());
        }
    }
    
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
        String liveChannelDetailResponse = new LiveChannelServiceImpl().updateChannelDetail(liveChannelDetailRequest);
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
        
        String value = "alone";
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("scene")
                .setValue(value)
                .setRequestId("2860257a405447e1bbbe9161da2dee74");
        String liveChannelDetailResponse = new LiveChannelServiceImpl().updateChannelDetail(liveChannelDetailRequest);
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
        String liveChannelDetailResponse = new LiveChannelServiceImpl().updateChannelDetail(liveChannelDetailRequest);
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
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse = new LiveChannelServiceImpl().listPPTRecord(
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
        String updateChannelPasswordResponse = new LiveChannelServiceImpl().updateChannelPassword(
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
     * 删除直播频道
     */
    @Test
    public void testDeleteChannel() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
        liveDeleteChannelRequest.setChannelId(channelId);
        String liveDeleteChannelResponse = new LiveChannelServiceImpl().deleteChannel(liveDeleteChannelRequest);
        Assert.assertNotNull(liveDeleteChannelResponse);
        if ("true".equals(liveDeleteChannelResponse)) {
            //to do something ......
            log.debug("删除直播频道成功{}", JSON.toJSONString(liveDeleteChannelResponse));
        }
    }
    
    /**
     * 批量删除频道
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDeleteChannelList() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer[] channelIds = new Integer[]{createChannel(), createChannel(), createChannel()};
        
        LiveDeleteChannelListRequest liveDeleteChannelListRequest = new LiveDeleteChannelListRequest();
        liveDeleteChannelListRequest.setChannelIds(channelIds);
        String liveDeleteChannelListResponse = new LiveChannelServiceImpl().deleteChannelList(
                liveDeleteChannelListRequest);
        Assert.assertNotNull(liveDeleteChannelListResponse);
        if ("true".equals(liveDeleteChannelListResponse)) {
            //to do something ......
            log.debug("批量删除频道成功{}", JSON.toJSONString(liveDeleteChannelListResponse));
        }
    }
    
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
        String liveCreateChannelTokenResponse = new LiveChannelServiceImpl().createChannelToken(
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
        LiveChannelInfoResponse liveChannelInfoResponse = new LiveChannelServiceImpl().channelInfo(
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
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = new LiveChannelServiceImpl().channelBasicInfo(
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
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse = new LiveChannelServiceImpl().channelAuthToken(
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
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = new LiveChannelServiceImpl().createSonChannel(
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
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = new LiveChannelServiceImpl().createSonChannel(
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
        String updateSonChannelInfoResponse = new LiveChannelServiceImpl().updateSonChannelInfo(
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
        String liveCreateSonChannelTokenResponse = new LiveChannelServiceImpl().createSonChannelToken(
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
        LiveSonChannelInfoResponse liveSonChannelInfoResponse = new LiveChannelServiceImpl().sonChannelInfo(
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
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse = new LiveChannelServiceImpl().sonChannelInfoList(
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
        String liveDeleteSonChannelRespose = new LiveChannelServiceImpl().deleteSonChannel(liveDeleteSonChannelRequest);
        Assert.assertNotNull(liveDeleteSonChannelRespose);
        if ("true".equals(liveDeleteSonChannelRespose)) {
            //to do something ......
            log.debug("测试删除子频道成功{}", liveDeleteSonChannelRespose);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试恢复直播频道推流
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testResumeChannelStream() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveResumeChannelStreamRequest liveResumeChannelStreamRequest = new LiveResumeChannelStreamRequest();
        liveResumeChannelStreamRequest.setChannelId(channelId);
        String liveResumeChannelStreamResponse = new LiveChannelServiceImpl().resumeChannelStream(
                liveResumeChannelStreamRequest);
        Assert.assertNotNull(liveResumeChannelStreamResponse);
        if ("success".equals(liveResumeChannelStreamResponse)) {
            //to do something ......
            log.debug("恢复直播频道推流成功{}", liveResumeChannelStreamResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 禁止直播频道推流
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCutoffChannelStream() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId = createChannel();
        
        LiveCutoffChannelStreamRequest liveCutoffChannelStreamRequest = new LiveCutoffChannelStreamRequest();
        liveCutoffChannelStreamRequest.setChannelId(channelId);
        String liveCutoffChannelStreamResponse = new LiveChannelServiceImpl().cutoffChannelStream(
                liveCutoffChannelStreamRequest);
        Assert.assertNotNull(liveCutoffChannelStreamResponse);
        if ("success".equals(liveCutoffChannelStreamResponse)) {
            //to do something ......
            log.debug("禁止直播频道推流成功{}", liveCutoffChannelStreamResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 批量查询频道直播流状态
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelLiveStream() throws IOException, NoSuchAlgorithmException {
        //准备测试数据
        Integer channelId0 = createChannel();
        Integer channelId1 = createChannel();
        
        LiveListChannelStreamStatusRequest liveListChannelStreamStatusRequest =
                new LiveListChannelStreamStatusRequest();
        liveListChannelStreamStatusRequest.setChannelIds(String.format("%s,%s", channelId0, channelId1));
        LiveListChannelStreamStatusResponse liveListChannelStreamStatusResponse =
                new LiveChannelServiceImpl().listChannelLiveStream(
                liveListChannelStreamStatusRequest);
        Assert.assertNotNull(liveListChannelStreamStatusResponse);
        if (liveListChannelStreamStatusResponse != null) {
            //to do something ......
            log.debug("批量查询频道直播流状态成功{}", JSON.toJSONString(liveListChannelStreamStatusResponse));
        }
        
        //删除测试数据
        deleteChannel(channelId0);
        deleteChannel(channelId1);
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
     * 测试查询频道录制视频信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelVideo() throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
        liveChannelVideoListRequest.setChannelId(1951952)
                .setStartDate("2020-01-01")
                .setEndDate("2020-10-14")
                .setSessionId(null);
        LiveChannelVideoListResponse liveChannelVideoListResponse = new LiveChannelServiceImpl().listChannelVideo(
                liveChannelVideoListRequest);
        Assert.assertNotNull(liveChannelVideoListResponse);
        if (liveChannelVideoListResponse != null) {
            //to do something ......
            log.debug("查询频道录制视频信息成功{}", JSON.toJSONString(liveChannelVideoListResponse));
        }
    }
    
    /**
     * 测试设置频道回放设置
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelPlaybackSetting() throws IOException, NoSuchAlgorithmException {
        int channelId = 1951952;
        List<String> videoIds = listChannelVideoIds(channelId);
        LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest = new LiveChannelPlaybackSettingRequest();
        liveChannelPlaybackSettingRequest.setChannelId(channelId)
                .setPlaybackEnabled("Y")
                .setType("single")
                .setOrigin("playback")
                .setVideoId(videoIds.get(0));
        String liveChannelPlaybackSettingResponse = new LiveChannelServiceImpl().channelPlaybackSetting(
                liveChannelPlaybackSettingRequest);
        Assert.assertNotNull(liveChannelPlaybackSettingResponse);
        if (liveChannelPlaybackSettingResponse != null) {
            //to do something ......
            log.debug("设置频道回放设置成功{}", JSON.toJSONString(liveChannelPlaybackSettingResponse));
        }
    }
    
    /**
     * 测试设置后台回放开关
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelPlayBackEnabledSetting() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest = new LiveChannelPlaybackEnabledRequest();
        liveChannelPlaybackEnabledRequest.setChannelId(1951952).setPlayBackEnabled("Y");
        Integer liveChannelPlaybackEnabledResponse = new LiveChannelServiceImpl().channelPlayBackEnabledSetting(
                liveChannelPlaybackEnabledRequest);
        Assert.assertNotNull(liveChannelPlaybackEnabledResponse);
        if (liveChannelPlaybackEnabledResponse != null) {
            //to do something ......
            log.debug("测试设置后台回放开关成功{}", liveChannelPlaybackEnabledResponse);
        }
    }
    
    /**
     * 测试查询视频库列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelVideoLibrary() throws IOException, NoSuchAlgorithmException {
        LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest =
                new LiveListChannelVideoLibraryRequest();
        liveListChannelVideoLibraryRequest.setChannelId(1951952).setListType("playback");
        LiveListChannelVideoLibraryResponse liveListChannelVideoLibraryResponse =
                new LiveChannelServiceImpl().listChannelVideoLibrary(
                liveListChannelVideoLibraryRequest);
        Assert.assertNotNull(liveListChannelVideoLibraryResponse);
        if (liveListChannelVideoLibraryResponse != null) {
            //to do something ......
            log.debug("测试查询视频库列表成功{}", JSON.toJSONString(liveListChannelVideoLibraryResponse));
        }
    }
    
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
     * 测试查询频道直播场次信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelSessionInfo() throws IOException, NoSuchAlgorithmException {
        LiveListChannelSessionInfoRequest liveListChannelSessionInfoRequest = new LiveListChannelSessionInfoRequest();
        liveListChannelSessionInfoRequest.setChannelId(1951952)
                .setStartDate("2020-10-01")
                .setEndDate("2020-10-24")
                .setCurrentPage(1);
        LiveListChannelSessionInfoResponse liveListChannelSessionInfoResponse =
                new LiveChannelServiceImpl().listChannelSessionInfo(
                liveListChannelSessionInfoRequest);
        Assert.assertNotNull(liveListChannelSessionInfoResponse);
        if (liveListChannelSessionInfoResponse != null) {
            //to do something ......
            log.debug("测试查询频道直播场次信息成功{}", JSON.toJSONString(liveListChannelSessionInfoResponse));
        }
    }
    
    /**
     * 测试查询指定文件ID的录制文件信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelVideoOnly() throws IOException, NoSuchAlgorithmException {
        int channelId = 1951952;
        String fileId = listChannelFileIds(channelId).get(0);
        LiveChannelVideoOnlyRequest liveChannelVideoOnlyRequest = new LiveChannelVideoOnlyRequest();
        liveChannelVideoOnlyRequest.setChannelId(1951952).setFileId(fileId);
        LiveChannelVideoOnlyResponse liveChannelVideoOnlyResponse = new LiveChannelServiceImpl().channelVideoOnly(
                liveChannelVideoOnlyRequest);
        Assert.assertNotNull(liveChannelVideoOnlyResponse);
        if (liveChannelVideoOnlyResponse != null) {
            //to do something ......
            log.debug("测试查询指定文件ID的录制文件信息成功{}", JSON.toJSONString(liveChannelVideoOnlyResponse));
        }
    }
    
    /**
     * 测试查询频道的回放开关状态
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelPlayBackEnabledInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledInfoRequest liveChannelPlaybackEnabledInfoRequest =
                new LiveChannelPlaybackEnabledInfoRequest();
        liveChannelPlaybackEnabledInfoRequest.setChannelId(1951952);
        String liveChannelPlaybackEnabledInfoResponse = new LiveChannelServiceImpl().channelPlayBackEnabledInfo(
                liveChannelPlaybackEnabledInfoRequest);
        Assert.assertNotNull(liveChannelPlaybackEnabledInfoResponse);
        if (liveChannelPlaybackEnabledInfoResponse != null) {
            //to do something ......
            log.debug("测试查询频道的回放开关状态成功{}", liveChannelPlaybackEnabledInfoResponse);
        }
    }
    
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

}
