package net.polyv.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
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
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.entity.channel.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.LiveCreateChannelTokenRequest;
import net.polyv.live.entity.channel.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.LiveDeleteChannelListRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.dto.LiveChannelBasicDTO;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

/**
 * @author: thomas
 **/
@Epic("频道管理")
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
    @Story("测试创建频道")
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
    @Story("测试创建并初始化频道 验证码观看")
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
            log.debug("测试创建并初始化频道 验证码观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试创建并初始化频道 付费观看  TODO 未通过测试
     * @throws IOException 异常
     */
    @Test
    @Story("测试创建并初始化频道 付费观看  TODO 未通过测试")
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
                .setWatchEndTime("2022-01-01 00:00:00")
                .setValidTimePeriod(720)
                .setEnabled("Y");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
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
     * 测试创建并初始化频道 白名单观看   TODO y未通过测试
     * @throws IOException 异常
     */
    @Test
    @Story("测试创建并初始化频道 白名单观看   TODO 未通过测试")
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
        LiveChannelInitRequest.AuthSetting phoneAuthSettings = liveChannelInitRequest.new AuthSetting().setRank(2)
                .setAuthType(LiveConstant.AuthType.PHONE.getDesc())
                .setEnabled("Y")
                .setAuthTips("这是提示文案");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
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
    @Story("测试创建并初始化频道 登记观看")
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
            log.debug("测试创建并初始化频道 登记观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试创建并初始化频道 自定义授权观看
     * @throws IOException 异常
     */
    @Test
    @Story("测试创建并初始化频道 自定义授权观看")
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
            log.debug("测试创建并初始化频道 自定义授权观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试创建并初始化频道 外部授权观看
     * @throws IOException 异常
     */
    @Test
    @Story("测试创建并初始化频道 外部授权观看")
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
            log.debug("测试创建并初始化频道 外部授权观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试创建并初始化频道 直接授权观看
     * @throws IOException 异常
     */
    @Test
    @Story("测试创建并初始化频道 直接授权观看")
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
            log.debug("测试创建并初始化频道 直接授权观看创建成功" + JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试修改频道的相关设置
     */
    @Test
    @Story("测试修改频道的相关设置")
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
        LiveChannelSettingRequest.AuthSetting authSetting = liveChannelSettingRequest.new AuthSetting().setAuthType(
                LiveConstant.AuthType.CODE.getDesc())
                .setRank(1)
                .setEnabled("Y")
                .setAuthCode("123456")
                .setQcodeTips("提示文案")
                .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
        List<LiveChannelSettingRequest.AuthSetting> authSettings = new ArrayList<>();
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
    @Story("测试批量创建频道")
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
    @Story("测试设置频道详情：修改密码功能")
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
    @Story("测试设置频道详情：修改scene字段")
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
    @Story("测试设置频道详情：设置最大同时观看人数")
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
    @Story("查询课件重制任务列表")
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
    @Story("设置频道密码")
    public void testUpdateChannelPassword() throws IOException, NoSuchAlgorithmException {
        LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest = new LiveChannelPasswordSettingRequest();
        liveChannelPasswordSettingRequest.setChannelId(1940343).setPasswd("987654");
        String updateChannelPasswordResponse = new LiveChannelServiceImpl().updateChannelPassword(
                liveChannelPasswordSettingRequest);
        Assert.assertNotNull(updateChannelPasswordResponse);
        if ("true".equals(updateChannelPasswordResponse)) {
            //to do something ......
            log.debug("设置频道密码成功" + JSON.toJSONString(updateChannelPasswordResponse));
        }
    }
    
    /**
     * 删除直播频道
     */
    @Test
    @Story("删除直播频道")
    public void testDeleteChannel() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
        liveDeleteChannelRequest.setChannelId(1938236);
        String liveDeleteChannelResponse = new LiveChannelServiceImpl().deleteChannel(liveDeleteChannelRequest);
        Assert.assertNotNull(liveDeleteChannelResponse);
        if ("true".equals(liveDeleteChannelResponse)) {
            //to do something ......
            log.debug("删除直播频道成功" + JSON.toJSONString(liveDeleteChannelResponse));
        }
    }
    
    /**
     * 批量删除频道
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    @Story("批量删除频道")
    public void testDeleteChannelList() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelListRequest liveDeleteChannelListRequest = new LiveDeleteChannelListRequest();
        liveDeleteChannelListRequest.setChannelIds(new Integer[]{1938719, 1938888});
        String liveDeleteChannelListResponse = new LiveChannelServiceImpl().deleteChannelList(
                liveDeleteChannelListRequest);
        Assert.assertNotNull(liveDeleteChannelListResponse);
        if ("true".equals(liveDeleteChannelListResponse)) {
            //to do something ......
            log.debug("批量删除频道成功" + JSON.toJSONString(liveDeleteChannelListResponse));
        }
    }
    
    /**
     * 测试设置频道单点登陆token
     */
    @Test
    @Story("测试设置频道单点登陆token")
    public void testCreateChannelToken() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelTokenRequest liveCreateChannelTokenRequest = new LiveCreateChannelTokenRequest();
        liveCreateChannelTokenRequest.setChannelId(1939188).setToken("testToken");
        String liveCreateChannelTokenResponse = new LiveChannelServiceImpl().createChannelToken(
                liveCreateChannelTokenRequest);
        Assert.assertNotNull(liveCreateChannelTokenResponse);
        if ("success".equals(liveCreateChannelTokenResponse)) {
            //to do something ......
            log.debug("设置频道单点登陆token成功" + JSON.toJSONString(liveCreateChannelTokenResponse));
        }
    }
    
    /**
     * 测试查询频道信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    @Story("测试查询频道信息")
    public void testChannelInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelInfoRequest liveChannelInfoRequest = new LiveChannelInfoRequest();
        liveChannelInfoRequest.setChannelId(1939188);
        LiveChannelInfoResponse liveChannelInfoResponse = new LiveChannelServiceImpl().channelInfo(
                liveChannelInfoRequest);
        Assert.assertNotNull(liveChannelInfoResponse);
        if (liveChannelInfoResponse != null) {
            //to do something ......
            log.debug("查询频道信息成功" + JSON.toJSONString(liveChannelInfoResponse));
        }
    }
    
    /**
     * 测试查询频道基本信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    @Story("测试查询频道基本信息")
    public void testChannelBasicInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        liveChannelBasicInfoRequest.setChannelId(1939188);
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = new LiveChannelServiceImpl().channelBasicInfo(
                liveChannelBasicInfoRequest);
        Assert.assertNotNull(liveChannelBasicInfoResponse);
        if (liveChannelBasicInfoResponse != null) {
            //to do something ......
            log.debug("查询频道基本信息成功" + JSON.toJSONString(liveChannelBasicInfoResponse));
        }
    }
    
    /**
     * 测试查询授权和连麦的token
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    @Story("测试查询授权和连麦的token")
    public void testChannelAuthToken() throws IOException, NoSuchAlgorithmException {
        LiveChannelAuthTokenRequest liveChannelAuthTokenRequest = new LiveChannelAuthTokenRequest();
        liveChannelAuthTokenRequest.setChannelId(1939188).setRole(LiveConstant.Role.ADMIN.getDesc()).setOrigin(null);
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse = new LiveChannelServiceImpl().channelAuthToken(
                liveChannelAuthTokenRequest);
        Assert.assertNotNull(liveChannelAuthTokenResponse);
        if (liveChannelAuthTokenResponse != null) {
            //to do something ......
            log.debug("查询授权和连麦的token成功" + JSON.toJSONString(liveChannelAuthTokenResponse));
        }
    }
    
    /**
     * 测试创建子频道 TODO 测试未通过
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    @Story("测试创建子频道 TODO 测试未通过")
    public void testCreateSonChannel() throws IOException, NoSuchAlgorithmException {
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        liveCreateSonChannelRequest.setChannelId(1939188)
                .setRole("Guest")
                .setNickname("sadboy")
                .setActor("教授")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0");
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = new LiveChannelServiceImpl().createSonChannel(
                liveCreateSonChannelRequest);
        Assert.assertNotNull(liveCreateSonChannelResponse);
        if (liveCreateSonChannelResponse != null) {
            //to do something ......
            log.debug("创建子频道成功" + JSON.toJSONString(liveCreateSonChannelResponse));
        }
    }
    
}
