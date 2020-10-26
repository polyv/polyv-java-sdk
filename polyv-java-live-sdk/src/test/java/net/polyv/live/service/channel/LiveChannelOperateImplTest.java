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
import net.polyv.live.entity.channel.operate.LiveChannelAuthTokenRequest;
import net.polyv.live.entity.channel.operate.LiveChannelAuthTokenResponse;
import net.polyv.live.entity.channel.operate.LiveChannelBasicInfoRequest;
import net.polyv.live.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.entity.channel.operate.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInfoRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInfoResponse;
import net.polyv.live.entity.channel.operate.LiveChannelInitRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInitResponse;
import net.polyv.live.entity.channel.operate.LiveChannelPasswordSettingRequest;
import net.polyv.live.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.operate.LiveCreateChannelPPTRecordRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelTokenRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelTokenRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteChannelListRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteChannelRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListResponse;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoResponse;
import net.polyv.live.entity.channel.operate.LiveUpdateSonChannelInfoRequest;
import net.polyv.live.entity.dto.LiveChannelBasicDTO;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class LiveChannelOperateImplTest extends BaseTest {
    
    /**
     * 测试创建频道
     * 描述:创建一个直播频道，返回直播频道相关的基础信息。
     * 约束:这是需要文档说明的
     * 约束:222
     * @throws IOException
     */
//    @Test
    public void testCreateChannel() throws IOException, NoSuchAlgorithmException {
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        LiveChannelResponse liveChannelResponse = null;
        liveChannelRequest.setName("Spring 知识精讲")
                .setChannelPasswd("666888")
                .setAutoPlay(LiveConstant.AutoPlay.AOTU_PLAY.getFlag())
                .setScene(LiveConstant.SceneType.PPT.getDesc())
                .setMaxViewer(300)
                .setWatchLayout(LiveConstant.WatchLayout.PPT.getFlag())
//                .setLinkMicLimit(2)
//                .setPureRtcEnabled(LiveConstant.Flag.YES.getFlag())
                .setReceive(LiveConstant.Flag.YES.getFlag())
                .setRequestId(LiveSignUtil.generateUUID());
        liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        if (liveChannelResponse != null) {
            //to do something ......
            log.debug("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
        }
        
    }
    
    /**
     * 测试创建并初始化频道 验证码观看
     * @throws IOException 异常
     */
//    @Test
    public void testCreateChannelInitCode() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse = null;
        LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
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
        LiveChannelInitRequest.AuthSetting codeAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.CODE.getDesc())
                .setEnabled("Y")
                .setAuthCode("123456")
                .setQcodeTips("提示文案")
                .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(codeAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 验证码观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试创建并初始化频道 付费观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
    public void testCreateChannelInitPay() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse = null;
        LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
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
        LiveChannelInitRequest.AuthSetting payAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.PAY.getDesc())
                .setPayAuthTips("欢迎使用POLYV直播平台")
                .setPrice(0.01f)
                .setWatchEndTime("1602578396000")
//                .setValidTimePeriod(720)
                .setEnabled("Y");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(payAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
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
    public void testCreateChannelInitPhone() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse = null;
        LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
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
        LiveChannelInitRequest.AuthSetting phoneAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.PHONE.getDesc())
                .setEnabled("Y")
                .setAuthTips("这是提示文案");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(phoneAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 白名单观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
    }
    
    /**
     * 测试创建并初始化频道 登记观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
    public void testCreateChannelInitInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse;
        LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
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
        LiveChannelInitRequest.AuthSetting infoAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.INFO.getDesc())
                .setEnabled("Y")
                .setInfoFields(infoFields);
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 登记观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelInitResponse.getChannelId());
    }
    
    /**
     * 测试创建并初始化频道 自定义授权观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
    public void testCreateChannelInitCustom() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse;
        LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
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
        LiveChannelInitRequest.AuthSetting infoAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.CUSTOM.getDesc())
                .setEnabled("Y")
                .setCustomKey("ttttt")
                .setCustomUri("http://www.polyv.net");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 自定义授权观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelInitResponse.getChannelId());
    }
    
    /**
     * 测试创建并初始化频道 外部授权观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
    public void testCreateChannelInitExternal() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse;
        LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
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
        LiveChannelInitRequest.AuthSetting infoAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.EXTERNAL.getDesc())
                .setEnabled("Y")
                .setExternalKey("externalKey")
                .setExternalUri("http://www.baidu.com")
                .setExternalRedirectUri("http://www.polyv.net");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
        Assert.assertNotNull(liveChannelInitResponse);
        if (liveChannelInitResponse != null) {
            //to do something ......
            log.debug("测试创建并初始化频道 外部授权观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
        }
        
        //删除测试数据
        deleteChannel(liveChannelInitResponse.getChannelId());
    }
    
    /**
     * 测试创建并初始化频道 直接授权观看，注释避免频繁创建
     * @throws IOException 异常
     */
//    @Test
    public void testCreateChannelInitDirect() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse;
        LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
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
        LiveChannelInitRequest.AuthSetting infoAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                .setAuthType(LiveConstant.AuthType.DIRECT.getDesc())
                .setEnabled("Y")
                .setDirectKey("directKey");
        List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(infoAuthSettings);
        liveChannelInitRequest.setAuthSettings(authSettings);
        liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
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
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        String liveChannelSettingResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
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
        LiveChannelSettingRequest.AuthSetting authSetting = new LiveChannelSettingRequest.AuthSetting().setAuthType(
                LiveConstant.AuthType.CODE.getDesc())
                .setRank(1)
                .setEnabled("Y")
                .setAuthCode("123456")
                .setQcodeTips("提示文案")
                .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
        List<LiveChannelSettingRequest.AuthSetting> authSettings = new ArrayList<>();
        authSettings.add(authSetting);
        liveChannelSettingRequest.setChannelId(channelId).setBasicSetting(basicSetting).setAuthSettings(authSettings);
        liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
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
    public void testCreateChannelList() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelListRequest liveCreateChannelListRequest = new LiveCreateChannelListRequest();
        LiveCreateChannelListResponse liveCreateChannelListResponse;
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
        liveCreateChannelListResponse = new LiveChannelOperateServiceImpl().createChannelList(
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
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        String liveChannelDetailResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        String newPassword = "1234567";
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("channelPasswd")
                .setValue(newPassword)
                .setRequestId("2860257a405447e1bbbe9161da2dee73");
        liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(liveChannelDetailRequest);
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
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        String liveChannelDetailResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        String value = "ppt";
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("scene")
                .setValue(value)
                .setRequestId("2860257a405447e1bbbe9161da2dee74");
        liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(liveChannelDetailRequest);
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
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        String liveChannelDetailResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        String value = "2147483647";
        
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("maxViewer")
                .setValue(value)
                .setRequestId("2860257a405447e1bbbe9161da2dee75");
        liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(liveChannelDetailRequest);
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
        LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest = new LiveListChannelPPTRecordRequest();
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveListChannelPPTRecordRequest.setChannelId(channelId).setCurrentPage(1);
        liveListChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().listPPTRecord(
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
        LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest = new LiveChannelPasswordSettingRequest();
        String updateChannelPasswordResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveChannelPasswordSettingRequest.setChannelId(channelId).setPasswd("987654");
        updateChannelPasswordResponse = new LiveChannelOperateServiceImpl().updateChannelPassword(
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
    public void testDeleteChannel() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
        String liveDeleteChannelResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveDeleteChannelRequest.setChannelId(channelId);
        liveDeleteChannelResponse = new LiveChannelOperateServiceImpl().deleteChannel(liveDeleteChannelRequest);
        Assert.assertNotNull(liveDeleteChannelResponse);
        if ("true".equals(liveDeleteChannelResponse)) {
            //to do something ......
            log.debug("删除直播频道成功{}", JSON.toJSONString(liveDeleteChannelResponse));
        }
    }
    
    /**
     * 批量删除频道，注释避免测试数据出错
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteChannelList() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelListRequest liveDeleteChannelListRequest = new LiveDeleteChannelListRequest();
        String liveDeleteChannelListResponse;
        //准备测试数据
        Integer[] channelIds = new Integer[]{createChannel(), createChannel(), createChannel()};
        
        liveDeleteChannelListRequest.setChannelIds(channelIds);
        liveDeleteChannelListResponse = new LiveChannelOperateServiceImpl().deleteChannelList(
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
        LiveCreateChannelTokenRequest liveCreateChannelTokenRequest = new LiveCreateChannelTokenRequest();
        String liveCreateChannelTokenResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveCreateChannelTokenRequest.setChannelId(channelId).setToken("testToken");
        liveCreateChannelTokenResponse = new LiveChannelOperateServiceImpl().createChannelToken(
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
        LiveChannelInfoRequest liveChannelInfoRequest = new LiveChannelInfoRequest();
        LiveChannelInfoResponse liveChannelInfoResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveChannelInfoRequest.setChannelId(channelId);
        liveChannelInfoResponse = new LiveChannelOperateServiceImpl().channelInfo(liveChannelInfoRequest);
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
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveChannelBasicInfoRequest.setChannelId(channelId);
        liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().channelBasicInfo(
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
        LiveChannelAuthTokenRequest liveChannelAuthTokenRequest = new LiveChannelAuthTokenRequest();
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveChannelAuthTokenRequest.setChannelId(channelId).setRole(LiveConstant.Role.ADMIN.getDesc()).setOrigin(null);
        liveChannelAuthTokenResponse = new LiveChannelOperateServiceImpl().channelAuthToken(
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
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        LiveCreateSonChannelResponse liveCreateSonChannelResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveCreateSonChannelRequest.setChannelId(channelId)
                .setRole("Guest")
                .setNickname("sadboy")
                .setActor("教授")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0");
        liveCreateSonChannelResponse = new LiveChannelOperateServiceImpl().createSonChannel(
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
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        LiveCreateSonChannelResponse liveCreateSonChannelResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveCreateSonChannelRequest.setChannelId(channelId)
                .setRole(null)
                .setNickname("sadboy")
                .setActor("教授")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0");
        liveCreateSonChannelResponse = new LiveChannelOperateServiceImpl().createSonChannel(
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
        LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest = new LiveUpdateSonChannelInfoRequest();
        String updateSonChannelInfoResponse;
        //准备测试数据
        Integer channelId = createChannel();
        String sonChannelId = createSonChannel(channelId);
        
        liveUpdateSonChannelInfoRequest.setChannelId(channelId)
                .setAccount(sonChannelId)
                .setNickname("sadboy")
                .setPassword("137890")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0")
                .setActor("教授")
                .setPageTurnEnabled("Y")
                .setNotifyEnabled("Y");
        updateSonChannelInfoResponse = new LiveChannelOperateServiceImpl().updateSonChannelInfo(
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
        LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest = new LiveCreateSonChannelTokenRequest();
        String liveCreateSonChannelTokenResponse;
        //准备测试数据
        Integer channelId = createChannel();
        String sonChannelId = createSonChannel(channelId);
        
        liveCreateSonChannelTokenRequest.setAccount(sonChannelId).setToken("sonChannelLogintoken");
        liveCreateSonChannelTokenResponse = new LiveChannelOperateServiceImpl().createSonChannelToken(
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
        LiveSonChannelInfoRequest liveSonChannelInfoRequest = new LiveSonChannelInfoRequest();
        LiveSonChannelInfoResponse liveSonChannelInfoResponse;
        //准备测试数据
        Integer channelId = createChannel();
        String sonChannelId = createSonChannel(channelId);
        
        liveSonChannelInfoRequest.setAccount(sonChannelId).setChannelId(channelId);
        liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().sonChannelInfo(liveSonChannelInfoRequest);
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
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse;
        //准备测试数据
        Integer channelId = createChannel();
        
        liveSonChannelInfoListRequest.setChannelId(channelId);
        liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().sonChannelInfoList(
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
        LiveDeleteSonChannelRequest liveDeleteSonChannelRequest = new LiveDeleteSonChannelRequest();
        String liveDeleteSonChannelResponse;
        //准备测试数据
        Integer channelId = createChannel();
        String sonChannelId = createSonChannel(channelId);
        
        liveDeleteSonChannelRequest.setChannelId(channelId).setAccount(sonChannelId);
        liveDeleteSonChannelResponse = new LiveChannelOperateServiceImpl().deleteSonChannel(
                liveDeleteSonChannelRequest);
        Assert.assertNotNull(liveDeleteSonChannelResponse);
        if ("true".equals(liveDeleteSonChannelResponse)) {
            //to do something ......
            log.debug("测试删除子频道成功{}", liveDeleteSonChannelResponse);
        }
        
        //删除测试数据
        deleteChannel(channelId);
    }
    
    /**
     * 测试创建重制课件任务
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testCreateChannelPPTRecordTask() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest = new LiveCreateChannelPPTRecordRequest();
        String liveCreateChannelPPTRecordResponse;
        liveCreateChannelPPTRecordRequest.setChannelId(1951952).setVideoId("07f5bbeb67");
        liveCreateChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().createChannelPPTRecordTask(
                liveCreateChannelPPTRecordRequest);
        Assert.assertNotNull(liveCreateChannelPPTRecordResponse);
        if (liveCreateChannelPPTRecordResponse != null) {
            //to do something ......
            log.debug("测试创建重制课件任务成功{}", liveCreateChannelPPTRecordResponse);
        }
    }
    
    
}
