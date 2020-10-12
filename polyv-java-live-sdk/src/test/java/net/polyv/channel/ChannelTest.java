package net.polyv.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.channel.LiveChannelDetailRequest;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.entity.channel.LiveChannelInitRequest;
import net.polyv.live.entity.channel.LiveChannelInitRequest.AuthSettings;
import net.polyv.live.entity.channel.LiveChannelInitResponse;
import net.polyv.live.entity.channel.LiveChannelPasswordSettingRequest;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.entity.channel.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.dto.LiveChannelBasicDTO;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;
import net.polyv.live.util.JsonUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class ChannelTest {
    /**
     * 系统账号密钥配置
     */
    public ChannelTest() {
        
        
        String appId = "frlr1zazn3";
        String appSecret = "5d5ade8f71f24bb9a2d1176cd607dd17";
        String userId = "1b448be323";
        LiveGlobalConfig.init(appId, userId, appSecret);
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
            log.debug("频道创建成功" + JSON.toJSONString(liveChannelResponse));
        }
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
        LiveChannelInitRequest.AuthSettings codeAuthSettings = liveChannelInitRequest.new AuthSettings().setRank(1)
                .setAuthType(LiveConstant.AuthType.CODE.getDesc())
                .setEnabled("Y")
                .setAuthCode("123456")
                .setQcodeTips("提示文案")
                .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
        List<LiveChannelInitRequest.AuthSettings> authSettings = new ArrayList<>();
        authSettings.add(codeAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 验证码观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试创建并初始化频道 付费观看  TODO 未通过测试
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
        LiveChannelInitRequest.AuthSettings payAuthSettings = liveChannelInitRequest.new AuthSettings().setRank(1)
                .setAuthType(LiveConstant.AuthType.PAY.getDesc())
                .setPayAuthTips("欢迎使用POLYV直播平台")
                .setPrice(0.01f)
                .setWatchEndTime("2022-01-01 00:00:00")
                .setValidTimePeriod(720)
                .setEnabled("Y");
        List<LiveChannelInitRequest.AuthSettings> authSettings = new ArrayList<>();
        authSettings.add(payAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 付费观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试创建并初始化频道 白名单观看   TODO 未通过测试
     * @throws IOException 异常
     */
    @Test
    public void testCreateChannelInitPhone() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitRequest.BasicSetting basicSetting = liveChannelInitRequest.new BasicSetting().setName(
                "创建并初始化频道-白名单观看")
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
        //白名单观看,设置观看条件为白名单观看时，必须已经存在白名单数据
        LiveChannelInitRequest.AuthSettings phoneAuthSettings = liveChannelInitRequest.new AuthSettings().setRank(2)
                .setAuthType(LiveConstant.AuthType.PHONE.getDesc())
                .setEnabled("Y")
                .setAuthTips("这是提示文案");
        List<LiveChannelInitRequest.AuthSettings> authSettings = new ArrayList<>();
        authSettings.add(phoneAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 白名单观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
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
        LiveChannelInitRequest.AuthSettings infoAuthSettings = liveChannelInitRequest.new AuthSettings().setRank(1)
                .setAuthType(LiveConstant.AuthType.INFO.getDesc())
                .setEnabled("Y")
                .setInfoFields(infoFields);
        List<LiveChannelInitRequest.AuthSettings> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 登记观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
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
        LiveChannelInitRequest.AuthSettings infoAuthSettings = liveChannelInitRequest.new AuthSettings().setRank(1)
                .setAuthType(LiveConstant.AuthType.CUSTOM.getDesc())
                .setEnabled("Y")
                .setCustomKey("ttttt")
                .setCustomUri("http://www.polyv.net");
        List<LiveChannelInitRequest.AuthSettings> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 自定义授权观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
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
        LiveChannelInitRequest.AuthSettings infoAuthSettings = liveChannelInitRequest.new AuthSettings().setRank(1)
                .setAuthType(LiveConstant.AuthType.EXTERNAL.getDesc())
                .setEnabled("Y")
                .setExternalKey("externalKey")
                .setExternalUri("http://www.baidu.com")
                .setExternalRedirectUri("http://www.polyv.net");
        List<LiveChannelInitRequest.AuthSettings> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 外部授权观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
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
        LiveChannelInitRequest.AuthSettings infoAuthSettings = liveChannelInitRequest.new AuthSettings().setRank(1)
                .setAuthType(LiveConstant.AuthType.DIRECT.getDesc())
                .setEnabled("Y")
                .setDirectKey("directKey");
        List<LiveChannelInitRequest.AuthSettings> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        LiveChannelInitResponse liveChannelInitResponse = new LiveChannelServiceImpl().createChannelInit(
                liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 直接授权观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试修改频道的相关设置
     */
    @Test
    public void testUpdateChannelSetting() throws IOException {
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
        LiveChannelSettingRequest.AuthSettings authSetting = liveChannelSettingRequest.new AuthSettings().setAuthType(
                LiveConstant.AuthType.CODE.getDesc())
                .setRank(1)
                .setEnabled("Y")
                .setAuthCode("123456")
                .setQcodeTips("提示文案")
                .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
        List<LiveChannelSettingRequest.AuthSettings> authSettings = new ArrayList<>();
        authSettings.add(authSetting);
        liveChannelSettingRequest.setChannelId(1940343).setBasicSetting(basicSetting).setAuthSettings(authSettings);
        String liveChannelSettingResponse = new LiveChannelServiceImpl().updateChannelSetting(
                liveChannelSettingRequest);
        Assert.assertNull(liveChannelSettingResponse);
        if (liveChannelSettingResponse == null) {
            //to do something ......
            log.debug("测试修改频道的相关设置成功");
        }
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
            log.debug("频道批量创建成功" + JSON.toJSONString(liveCreateChannelListResponse));
        }
    }
    
    /**
     * 测试设置频道详情：修改密码功能
     * @throws IOException
     */
    @Test
    public void testUpdateChannelDetailPassword() throws IOException, NoSuchAlgorithmException {
        Integer channelId = 1938888;
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
            log.debug(String.format("频道%s修改密码为%s成功%s", channelId, newPassword, liveChannelDetailResponse));
        }
    }
    
    /**
     * TODO 频道号动态获取，scene写成枚举类型
     * 测试设置频道详情：修改scene字段
     * @throws IOException
     */
    @Test
    public void testUpdateChannelScene() throws IOException, NoSuchAlgorithmException {
        Integer channelId = 1938888;
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
            log.debug(String.format("频道%s修改scene为%s成功%s", channelId, value, liveChannelDetailResponse));
        }
    }
    
    /**
     * TODO 频道号动态获取
     * 测试设置频道详情：设置最大同时观看人数
     * @throws IOException
     */
    @Test
    public void testUpdateChannel() throws IOException, NoSuchAlgorithmException {
        Integer channelId = 1938888;
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
            log.debug(String.format("频道%s修改maxViewer为%s成功%s", channelId, value, liveChannelDetailResponse));
        }
    }
    
    /**
     * 查询课件重制任务列表
     * @throws IOException
     */
    @Test
    public void testListPPTRecord() throws IOException, NoSuchAlgorithmException {
        LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest = new LiveListChannelPPTRecordRequest();
        liveListChannelPPTRecordRequest.setChannelId(1940343).setPage(1);
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse = new LiveChannelServiceImpl().listPPTRecord(
                liveListChannelPPTRecordRequest);
        Assert.assertNotNull(liveListChannelPPTRecordResponse);
        if (liveListChannelPPTRecordResponse != null) {
            //to do something ......
            log.debug("查询课件重制任务列表信息成功" + JSON.toJSONString(liveListChannelPPTRecordResponse));
        }
    }
    
    /**
     * 设置频道密码
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelPassword() throws IOException, NoSuchAlgorithmException {
        LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest = new LiveChannelPasswordSettingRequest();
        liveChannelPasswordSettingRequest.setChannelId(1940343).setPasswd("987654");
        String updateChannelPasswordResponse = new LiveChannelServiceImpl().updateChannelPassword(
                liveChannelPasswordSettingRequest);
        Assert.assertNotNull(updateChannelPasswordResponse);
        if ("true".equals(updateChannelPasswordResponse)) {
            log.debug("设置频道密码成功" + JSON.toJSONString(updateChannelPasswordResponse));
        }
    }
}
