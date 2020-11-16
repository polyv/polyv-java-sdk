package net.polyv.live.v1.service.channel;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.channel.operate.LiveChannelAdvertListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelAdvertListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelAuthTokenRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelAuthTokenResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelCallbackSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelCallbackSettingResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelCaptureRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelDetailRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInitRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInitResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelPasswordSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelTransmitListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelTransmitListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelPPTRecordRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelTokenRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateDiskVideosStreamRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelResponse;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelTokenRequest;
import net.polyv.live.v1.entity.channel.operate.LiveDeleteChannelListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveDeleteChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveDeleteDiskVideosStreamRequest;
import net.polyv.live.v1.entity.channel.operate.LiveDeleteSonChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveListChannelPPTRecordRequest;
import net.polyv.live.v1.entity.channel.operate.LiveListChannelPPTRecordResponse;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveUpdateChannelCallbackSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveUpdateChannelMaxViewerRequest;
import net.polyv.live.v1.entity.channel.operate.LiveUpdateChannelStreamRequest;
import net.polyv.live.v1.entity.channel.operate.LiveUpdateSonChannelInfoRequest;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 频道操作
 * @author: thomas
 **/
@Slf4j
public class LiveChannelOperateImplTest extends BaseTest {
    
    /**
     * 测试创建频道
     * 描述：创建一个直播频道，返回直播频道相关的基础信息。
     * @throws Exception
     */
//    @Test
    public void testCreateChannel() throws Exception, NoSuchAlgorithmException {
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        LiveChannelResponse liveChannelResponse = null;
        try {
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
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试创建并初始化频道
     * @throws Exception 异常
     */
    @Test
    public void testCreateChannelInit() throws Exception, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse = null;
        try {
            LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
                    "创建并初始化频道-验证码观看")
                    .setChannelPasswd("123321")
                    .setAutoPlay(1)
                    .setPlayerColor("#666666")
                    .setScene(LiveConstant.SceneType.ALONE.getDesc())
                    .setCategoryId(340019)
                    .setMaxViewer(0)
                    .setStartTime(super.getDate(1602306535000l))
                    .setDesc("这是一个描述")
                    .setPublisher("sadboy主讲")
                    .setLinkMicLimit(-1)
                    .setPureRtcEnabled("N")
                    .setReceiveChannelIds("213");
            liveChannelInitRequest.setBasicSetting(basicSetting).setRequestId(LiveSignUtil.generateUUID());
            //验证码观看
            LiveChannelInitRequest.AuthSetting codeAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                    .setAuthType(LiveConstant.AuthType.CODE.getDesc())
                    .setEnabled("Y")
                    .setAuthCode("123456")
                    .setQcodeTips("提示文案")
                    .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
            List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<LiveChannelInitRequest.AuthSetting>();
            authSettings.add(codeAuthSettings);
            liveChannelInitRequest.setAuthSettings(authSettings);
            liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
            Assert.assertNotNull(liveChannelInitResponse);
            if (liveChannelInitResponse != null) {
                //to do something ......
                log.debug("测试创建并初始化频道 验证码观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
                deleteChannel(liveChannelInitResponse.getChannelId());
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试批量创建频道
     * @throws Exception
     */
//    @Test
    public void testCreateChannelList() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelListRequest liveCreateChannelListRequest = new LiveCreateChannelListRequest();
        LiveCreateChannelListResponse liveCreateChannelListResponse;
        try {
            List<LiveCreateChannelListRequest.LiveChannelBasic> channels =
                    new ArrayList<LiveCreateChannelListRequest.LiveChannelBasic>();
            for (int i = 0; i <= 2; i++) {
                LiveCreateChannelListRequest.LiveChannelBasic liveChannel =
                        new LiveCreateChannelListRequest.LiveChannelBasic();
                liveChannel.setName("批量创建" + i)
                        .setChannelPasswd("123456" + i)
                        .setCourseId("c" + i)
                        .setAutoPlay(1)
                        .setPlayerColor("#666666")
                        .setScene(LiveConstant.SceneType.ALONE.getDesc())
                        .setCategoryId(340019);
                channels.add(liveChannel);
            }
            liveCreateChannelListRequest.setChannels(channels)
                    .setRequestId("123456")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelListResponse = new LiveChannelOperateServiceImpl().createChannelList(
                    liveCreateChannelListRequest);
            Assert.assertNotNull(liveCreateChannelListResponse);
            if (liveCreateChannelListResponse != null) {
                //to do something ......
                log.debug("频道批量创建成功{}", JSON.toJSONString(liveCreateChannelListResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道信息
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelInfo() throws Exception, NoSuchAlgorithmException {
        LiveChannelInfoRequest liveChannelInfoRequest = new LiveChannelInfoRequest();
        LiveChannelInfoResponse liveChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveChannelInfoRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelInfoResponse = new LiveChannelOperateServiceImpl().getChannelInfo(liveChannelInfoRequest);
            Assert.assertNotNull(liveChannelInfoResponse);
            if (liveChannelInfoResponse != null) {
                //to do something ......
                log.debug("查询频道信息成功{}", JSON.toJSONString(liveChannelInfoResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道基本信息
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelBasicInfo() throws Exception, NoSuchAlgorithmException {
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveChannelBasicInfoRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().getChannelBasicInfo(
                    liveChannelBasicInfoRequest);
            Assert.assertNotNull(liveChannelBasicInfoResponse);
            if (liveChannelBasicInfoResponse != null) {
                //to do something ......
                log.debug("查询频道基本信息成功{}", JSON.toJSONString(liveChannelBasicInfoResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询授权和连麦的token
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelAuthToken() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthTokenRequest liveChannelAuthTokenRequest = new LiveChannelAuthTokenRequest();
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveChannelAuthTokenRequest.setChannelId(channelId)
                    .setRole(LiveConstant.Role.ADMIN.getDesc())
                    .setOrigin(null)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthTokenResponse = new LiveChannelOperateServiceImpl().getChannelAuthToken(
                    liveChannelAuthTokenRequest);
            Assert.assertNotNull(liveChannelAuthTokenResponse);
            if (liveChannelAuthTokenResponse != null) {
                //to do something ......
                log.debug("查询授权和连麦的token成功{}", JSON.toJSONString(liveChannelAuthTokenResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试修改频道的相关设置
     * 返回：true为设置成功，false为设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelSetting() throws Exception, NoSuchAlgorithmException {
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        Boolean liveChannelSettingResponse;
        try {
            //准备测试数据
            String channelId = getAloneChannelId();
            
            LiveChannelSettingRequest.BasicSetting basicSetting = liveChannelSettingRequest.new BasicSetting().setName(
                    "Junit测试(勿删)888")
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
            List<LiveChannelSettingRequest.AuthSetting> authSettings =
                    new ArrayList<LiveChannelSettingRequest.AuthSetting>();
            authSettings.add(authSetting);
            liveChannelSettingRequest.setChannelId(channelId)
                    .setBasicSetting(basicSetting)
                    .setAuthSettings(authSettings)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                    liveChannelSettingRequest);
            Assert.assertNotNull(liveChannelSettingResponse);
            if (liveChannelSettingResponse) {
                //to do something ......
                log.debug("测试修改频道的相关设置成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置频道详情
     * 返回：true为修改成功，false为修改失败
     * @throws Exception
     */
    @Test
    public void testUpdateChannelDetail() throws Exception, NoSuchAlgorithmException {
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        Boolean liveChannelDetailResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            String newPassword = getRandomString(16);
            liveChannelDetailRequest.setChannelId(channelId)
                    .setField("channelPasswd")
                    .setValue(newPassword)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(
                    liveChannelDetailRequest);
            Assert.assertNotNull(liveChannelDetailResponse);
            if (liveChannelDetailResponse) {
                //to do something ......
                log.debug("频道{}修改密码为{}成功", channelId, newPassword);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置频道密码
     * 返回：true为设置密码成功，false为设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelPassword() throws Exception, NoSuchAlgorithmException {
        LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest = new LiveChannelPasswordSettingRequest();
        Boolean updateChannelPasswordResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveChannelPasswordSettingRequest.setChannelId(channelId)
                    .setPasswd("987654")
                    .setRequestId(LiveSignUtil.generateUUID());
            updateChannelPasswordResponse = new LiveChannelOperateServiceImpl().updateChannelPassword(
                    liveChannelPasswordSettingRequest);
            Assert.assertNotNull(updateChannelPasswordResponse);
            if (updateChannelPasswordResponse) {
                //to do something ......
                log.debug("设置频道密码成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置频道单点登陆token
     * 返回：true为设置token成功，false为设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateChannelToken() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelTokenRequest liveCreateChannelTokenRequest = new LiveCreateChannelTokenRequest();
        Boolean liveCreateChannelTokenResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveCreateChannelTokenRequest.setChannelId(channelId)
                    .setToken(LiveSignUtil.generateUUID())
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelTokenResponse = new LiveChannelOperateServiceImpl().createChannelToken(
                    liveCreateChannelTokenRequest);
            Assert.assertNotNull(liveCreateChannelTokenResponse);
            if (liveCreateChannelTokenResponse) {
                //to do something ......
                log.debug("设置频道单点登陆token成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 删除直播频道
     * 返回：true为删除成功，false为删除失败
     */
//    @Test
    public void testDeleteChannel() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
        Boolean liveDeleteChannelResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveDeleteChannelRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelResponse = new LiveChannelOperateServiceImpl().deleteChannel(liveDeleteChannelRequest);
            Assert.assertNotNull(liveDeleteChannelResponse);
            if (liveDeleteChannelResponse) {
                //to do something ......
                log.debug("删除直播频道成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 批量删除频道
     * 返回：true为批量删除成功，false为批量删除失败，不存在部分成功
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteChannelList() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelListRequest liveDeleteChannelListRequest = new LiveDeleteChannelListRequest();
        Boolean liveDeleteChannelListResponse;
        try {
            //准备测试数据
            String[] channelIds = new String[]{createChannel(), createChannel(), createChannel()};
            
            liveDeleteChannelListRequest.setChannelIds(channelIds).setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelListResponse = new LiveChannelOperateServiceImpl().deleteChannelList(
                    liveDeleteChannelListRequest);
            Assert.assertNotNull(liveDeleteChannelListResponse);
            if (liveDeleteChannelListResponse) {
                //to do something ......
                log.debug("批量删除频道成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试创建子频道-三分屏添加Guest
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateSonChannelGuest() throws Exception, NoSuchAlgorithmException {
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        LiveCreateSonChannelResponse liveCreateSonChannelResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            List<String> sonChannelIds = getDelSonChannelIds();
            for (String temp : sonChannelIds) {
                deleteSonChannel(temp);
            }
            
            liveCreateSonChannelRequest.setChannelId(channelId)
                    .setRole("Guest")
                    .setNickname("sadboy")
                    .setActor("教授")
                    .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateSonChannelResponse = new LiveChannelOperateServiceImpl().createSonChannel(
                    liveCreateSonChannelRequest);
            Assert.assertNotNull(liveCreateSonChannelResponse);
            if (liveCreateSonChannelResponse != null) {
                //to do something ......
                log.debug("创建子频道成功{}", JSON.toJSONString(liveCreateSonChannelResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试创建子频道-非三分屏添加助教
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateSonChannelAssistant() throws Exception, NoSuchAlgorithmException {
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        LiveCreateSonChannelResponse liveCreateSonChannelResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            List<String> sonChannelIds = getDelSonChannelIds();
            for (String temp : sonChannelIds) {
                deleteSonChannel(temp);
            }
            
            liveCreateSonChannelRequest.setChannelId(channelId)
                    .setRole(null)
                    .setNickname("sadboy")
                    .setActor("教授")
                    .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateSonChannelResponse = new LiveChannelOperateServiceImpl().createSonChannel(
                    liveCreateSonChannelRequest);
            Assert.assertNotNull(liveCreateSonChannelResponse);
            if (liveCreateSonChannelResponse != null) {
                //to do something ......
                log.debug("创建子频道成功{}", JSON.toJSONString(liveCreateSonChannelResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询子频道信息
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetSonChannelInfo() throws Exception, NoSuchAlgorithmException {
        LiveSonChannelInfoRequest liveSonChannelInfoRequest = new LiveSonChannelInfoRequest();
        LiveSonChannelInfoResponse liveSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String sonChannelId = createSonChannel(channelId);
            
            liveSonChannelInfoRequest.setAccount(sonChannelId)
                    .setChannelId(channelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().getSonChannelInfo(liveSonChannelInfoRequest);
            Assert.assertNotNull(liveSonChannelInfoResponse);
            if (liveSonChannelInfoResponse != null) {
                //to do something ......
                log.debug("测试查询子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道号下所有子频道信息
     */
    @Test
    public void testGetSonChannelInfoList() throws Exception, NoSuchAlgorithmException {
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveSonChannelInfoListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().getSonChannelInfoList(
                    liveSonChannelInfoListRequest);
            Assert.assertNotNull(liveSonChannelInfoResponse);
            if (liveSonChannelInfoResponse != null) {
                //to do something ......
                log.debug("查询频道号下所有子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置子频道信息
     * 返回：true为设置成功，false为设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateSonChannelInfo() throws Exception, NoSuchAlgorithmException {
        LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest = new LiveUpdateSonChannelInfoRequest();
        Boolean updateSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String sonChannelId = createSonChannel(channelId);
            
            liveUpdateSonChannelInfoRequest.setChannelId(channelId)
                    .setAccount(sonChannelId)
                    .setNickname("sadboy")
                    .setPassword(getRandomString(16))
                    .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0")
                    .setActor("教授")
                    .setPageTurnEnabled("Y")
                    .setNotifyEnabled("Y")
                    .setRequestId(LiveSignUtil.generateUUID());
            updateSonChannelInfoResponse = new LiveChannelOperateServiceImpl().updateSonChannelInfo(
                    liveUpdateSonChannelInfoRequest);
            Assert.assertNotNull(updateSonChannelInfoResponse);
            if (updateSonChannelInfoResponse) {
                //to do something ......
                log.debug("设置子频道信息成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置子频道单点登陆token
     * 返回：true为设置子频道token成功，false为设置失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateSonChannelToken() throws Exception, NoSuchAlgorithmException {
        LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest = new LiveCreateSonChannelTokenRequest();
        Boolean liveCreateSonChannelTokenResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String sonChannelId = createSonChannel(channelId);
            
            liveCreateSonChannelTokenRequest.setAccount(sonChannelId)
                    .setToken(LiveSignUtil.generateUUID())
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateSonChannelTokenResponse = new LiveChannelOperateServiceImpl().createSonChannelToken(
                    liveCreateSonChannelTokenRequest);
            Assert.assertNotNull(liveCreateSonChannelTokenResponse);
            if (liveCreateSonChannelTokenResponse) {
                //to do something ......
                log.debug("设置子频道单点登陆token成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试删除子频道
     * 返回：true为删除成功，false为删除失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteSonChannel() throws Exception, NoSuchAlgorithmException {
        LiveDeleteSonChannelRequest liveDeleteSonChannelRequest = new LiveDeleteSonChannelRequest();
        Boolean liveDeleteSonChannelResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String sonChannelId = createSonChannel(channelId);
            
            liveDeleteSonChannelRequest.setChannelId(channelId)
                    .setAccount(sonChannelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteSonChannelResponse = new LiveChannelOperateServiceImpl().deleteSonChannel(
                    liveDeleteSonChannelRequest);
            Assert.assertNotNull(liveDeleteSonChannelResponse);
            if (liveDeleteSonChannelResponse) {
                //to do something ......
                log.debug("测试删除子频道成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试创建重制课件任务
     * 返回：true为创建成功，false为创建失败
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testCreateChannelPPTRecordTask() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest = new LiveCreateChannelPPTRecordRequest();
        Boolean liveCreateChannelPPTRecordResponse;
        try {
            liveCreateChannelPPTRecordRequest.setChannelId("1951952")
                    .setVideoId("07f5bbeb67")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().createChannelPPTRecordTask(
                    liveCreateChannelPPTRecordRequest);
            Assert.assertNotNull(liveCreateChannelPPTRecordResponse);
            if (liveCreateChannelPPTRecordResponse) {
                //to do something ......
                log.debug("测试创建重制课件任务成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 查询课件重制任务列表
     * @throws Exception
     */
    @Test
    public void testListPPTRecord() throws Exception, NoSuchAlgorithmException {
        LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest = new LiveListChannelPPTRecordRequest();
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveListChannelPPTRecordRequest.setChannelId(channelId)
                    .setStartTime(getDate(2020,1,1))
                    .setEndTime(getDate(2020,11,11))
                    .setCurrentPage(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().listPPTRecord(
                    liveListChannelPPTRecordRequest);
            Assert.assertNotNull(liveListChannelPPTRecordResponse);
            if (liveListChannelPPTRecordResponse != null) {
                //to do something ......
                log.debug("查询课件重制任务列表信息成功{}", JSON.toJSONString(liveListChannelPPTRecordResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道回调设置接口
     * @throws Exception
     */
    @Test
    public void testGetChannelCallbackSetting() throws Exception {
        LiveChannelCallbackSettingRequest liveChannelCallbackSettingRequest = new LiveChannelCallbackSettingRequest();
        LiveChannelCallbackSettingResponse liveChannelCallbackSettingResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveChannelCallbackSettingRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelCallbackSettingResponse = new LiveChannelOperateServiceImpl().getChannelCallbackSetting(
                    liveChannelCallbackSettingRequest);
            Assert.assertNotNull(liveChannelCallbackSettingResponse);
            if (liveChannelCallbackSettingResponse != null) {
                //to do something ......
                log.debug("测试查询频道回调设置接口成功，{}", JSON.toJSONString(liveChannelCallbackSettingResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置频道回调设置
     * 约束：2、如频道需要跟随用户设置，可以调用设置频道默认项开关接口
     * TODO 等待后台修改返回值
     * @throws Exception
     */
//    @Test
    public void testUpdateChannelCallbackSetting() throws Exception {
        LiveUpdateChannelCallbackSettingRequest liveUpdateChannelCallbackSettingRequest =
                new LiveUpdateChannelCallbackSettingRequest();
        Boolean liveUpdateChannelCallbackSettingResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveUpdateChannelCallbackSettingRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelCallbackSettingResponse = new LiveChannelOperateServiceImpl().updateChannelCallbackSetting(
                    liveUpdateChannelCallbackSettingRequest);
            Assert.assertNotNull(liveUpdateChannelCallbackSettingResponse);
            if (liveUpdateChannelCallbackSettingResponse) {
                //to do something ......
                log.debug("测试设置频道回调设置成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试批量创建子频道
     * 约束：2、批量创建子频道，子频道角色支持guest(嘉宾，只支持三分屏场景)、assistant(助教)
     * TODO 需要后台修改json解析功能
     * @throws Exception
     */
//    @Test
    public void testCreateSonChannelList() throws Exception {
        LiveCreateSonChannelListRequest liveCreateSonChannelListRequest = new LiveCreateSonChannelListRequest();
        LiveCreateSonChannelListResponse liveCreateSonChannelListResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            List<LiveCreateSonChannelListRequest.SonChannel> sonChannels =
                    new ArrayList<LiveCreateSonChannelListRequest.SonChannel>();
            LiveCreateSonChannelListRequest.SonChannel sonChannel1 = new LiveCreateSonChannelListRequest.SonChannel();
            sonChannel1.setRole("Guest")
                    .setNickname("嘉宾大大")
                    .setPasswd(getRandomString(10))
                    .setActor("教授")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                            "3965499425&fm=26&gp=0.jpg");
            sonChannels.add(sonChannel1);
            sonChannel1 = new LiveCreateSonChannelListRequest.SonChannel();
            sonChannel1.setRole(null)
                    .setNickname("助教大大")
                    .setPasswd(getRandomString(10))
                    .setActor("王者")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                            "3965499425&fm=26&gp=0.jpg");
            sonChannels.add(sonChannel1);
            liveCreateSonChannelListRequest.setChannelId(channelId)
                    .setSonChannels(sonChannels)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateSonChannelListResponse = new LiveChannelOperateServiceImpl().createSonChannelList(
                    liveCreateSonChannelListRequest);
            Assert.assertNotNull(liveCreateSonChannelListResponse);
            if (liveCreateSonChannelListResponse != null) {
                //to do something ......
                log.debug("测试设置频道回调设置成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试获取账号或频道转播列表信息
     * @throws Exception
     */
    @Test
    public void testGetChannelTransmitList() throws Exception {
        LiveChannelTransmitListRequest liveChannelTransmitListRequest = new LiveChannelTransmitListRequest();
        LiveChannelTransmitListResponse liveChannelTransmitListResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveChannelTransmitListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelTransmitListResponse = new LiveChannelOperateServiceImpl().getChannelTransmitList(
                    liveChannelTransmitListRequest);
            Assert.assertNotNull(liveChannelTransmitListResponse);
            if (liveChannelTransmitListResponse != null) {
                //to do something ......
                log.debug("测试获取账号或频道转播列表信息成功，{}", JSON.toJSONString(liveChannelTransmitListResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置频道最大在线人数
     * 返回：true为设置成功，false为设置失败
     * @throws Exception
     */
    @Test
    public void testUpdateChannelMaxViewer() throws Exception {
        LiveUpdateChannelMaxViewerRequest liveUpdateChannelMaxViewerRequest = new LiveUpdateChannelMaxViewerRequest();
        Boolean liveUpdateChannelMaxViewerResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveUpdateChannelMaxViewerRequest.setChannelId(channelId)
                    .setMaxViewer(Integer.MAX_VALUE)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMaxViewerResponse = new LiveChannelOperateServiceImpl().updateChannelMaxViewer(
                    liveUpdateChannelMaxViewerRequest);
            Assert.assertNotNull(liveUpdateChannelMaxViewerResponse);
            if (liveUpdateChannelMaxViewerResponse) {
                //to do something ......
                log.debug("测试设置频道最大在线人数成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道广告列表
     * 约束：2、提供查询频道轮播广告列表信息，频道广告为空时，获取全局广告
     * @throws Exception
     */
    @Test
    public void testGetChannelAdvertList() throws Exception {
        LiveChannelAdvertListRequest liveChannelAdvertListRequest = new LiveChannelAdvertListRequest();
        LiveChannelAdvertListResponse liveChannelAdvertListResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveChannelAdvertListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelAdvertListResponse = new LiveChannelOperateServiceImpl().getChannelAdvertList(
                    liveChannelAdvertListRequest);
            Assert.assertNotNull(liveChannelAdvertListResponse);
            if (liveChannelAdvertListResponse != null) {
                //to do something ......
                log.debug("测试查询频道广告列表成功,{}", JSON.toJSONString(liveChannelAdvertListResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道广告列表
     * 约束：2、如果直播未开启，将抛出"channel is not live."异常
     * 返回：返回图片http地址，
     * @throws Exception
     */
//    @Test
    public void testGetChannelCapture() throws Exception {
        LiveChannelCaptureRequest liveChannelCaptureRequest = new LiveChannelCaptureRequest();
        String liveChannelCaptureResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveChannelCaptureRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelCaptureResponse = new LiveChannelOperateServiceImpl().getChannelCapture(liveChannelCaptureRequest);
            Assert.assertNotNull(liveChannelCaptureResponse);
            if (liveChannelCaptureResponse != null) {
                //to do something ......
                log.debug("测试查询频道广告列表成功,{}", JSON.toJSONString(liveChannelCaptureResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试修改直播推流方式
     * 约束：2、直播过程中不允许修改直播方式
     * 返回：true为修改推流方式成功，false为修改失败
     * @throws Exception
     */
//    @Test
    public void testUpdateChannelStream() throws Exception {
        LiveUpdateChannelStreamRequest liveUpdateChannelStreamRequest = new LiveUpdateChannelStreamRequest();
        Boolean liveUpdateChannelStreamResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveUpdateChannelStreamRequest.setStreamType("disk")
                    .setChannelId(channelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelStreamResponse = new LiveChannelOperateServiceImpl().updateChannelStream(
                    liveUpdateChannelStreamRequest);
            Assert.assertNotNull(liveUpdateChannelStreamResponse);
            if (liveUpdateChannelStreamResponse) {
                //to do something ......
                log.debug("测试修改直播推流方式成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置硬盘推流直播
     * 约束：2、调用接口后，如果当前频道未在直播中，会自动设置直播方式为“硬盘推流”。如果当前使用其他直播推流方式直播中，则需要在直播结束后，调用《修改直播推流方式》修改为硬盘推流，才会在所设置的开始时间进行直播
     * 返回：true为设置硬盘推流直播成功，false为修改失败
     * @throws Exception
     */
//    @Test
    public void testCreateDiskVideosStream() throws Exception {
        LiveCreateDiskVideosStreamRequest liveCreateDiskVideosStreamRequest = new LiveCreateDiskVideosStreamRequest();
        Boolean liveCreateDiskVideosStreamResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String videoId = listChannelVideoIds(channelId).get(0);
            
            liveCreateDiskVideosStreamRequest.setVideoIds(videoId)
                    .setStartTimes(super.getDate(System.currentTimeMillis() + 3000000))
                    .setChannelId(channelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateDiskVideosStreamResponse = new LiveChannelOperateServiceImpl().createDiskVideosStream(
                    liveCreateDiskVideosStreamRequest);
            Assert.assertNotNull(liveCreateDiskVideosStreamResponse);
            if (liveCreateDiskVideosStreamResponse) {
                //to do something ......
                log.debug("测试设置硬盘推流直播成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试删除硬盘推流的视频
     * 约束：2、调用接口后，如果当前频道未在直播中，会自动设置直播方式为“硬盘推流”。如果当前使用其他直播推流方式直播中，则需要在直播结束后，调用《修改直播推流方式》修改为硬盘推流，才会在所设置的开始时间进行直播
     * 返回：true为设置硬盘推流直播成功，false为修改失败
     * @throws Exception
     */
    @Test
    public void testDeleteDiskVideosStream() throws Exception {
        LiveDeleteDiskVideosStreamRequest liveDeleteDiskVideosStreamRequest = new LiveDeleteDiskVideosStreamRequest();
        Boolean liveDeleteDiskVideosStreamResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveDeleteDiskVideosStreamRequest.setVideoIds("f1574595e1")
                    .setChannelId(channelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteDiskVideosStreamResponse = new LiveChannelOperateServiceImpl().deleteDiskVideosStream(
                    liveDeleteDiskVideosStreamRequest);
            Assert.assertNotNull(liveDeleteDiskVideosStreamResponse);
            if (liveDeleteDiskVideosStreamResponse) {
                //to do something ......
                log.debug("测试删除硬盘推流的视频成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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