package net.polyv.live.v1.quick;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.StringUtils;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerImgRequest;
import net.polyv.live.v1.entity.player.LiveSetWarmupVedioRequest;
import net.polyv.live.v1.entity.quick.QuickCreatePPTChannelRequest;
import net.polyv.live.v1.entity.quick.QuickCreateVideoChannelRequest;
import net.polyv.live.v1.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.v1.service.player.impl.LivePlayerServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 频道快捷操作类
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelManager {
    
    /**
     * 快速创建简单三分屏频道
     * @param quickCreateChannelRequest 快速创建频道请求实体
     * @return 频道基本信息
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
    public static LiveChannelBasicInfoResponse createEasyPPT(QuickCreatePPTChannelRequest quickCreateChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        String scene = LiveConstant.SceneType.PPT.getDesc();
        //1、创建频道
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        liveChannelRequest.setName(quickCreateChannelRequest.getName())
                .setChannelPasswd(quickCreateChannelRequest.getChannelPasswd())
                .setPureRtcEnabled(quickCreateChannelRequest.getPureRtcEnabled())
                .setScene(scene)
                .setRequestId(LiveSignUtil.generateUUID());
        LiveChannelResponse liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
        if (liveChannelResponse == null) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "创建频道失败");
        }
        log.info("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
        String channelId = liveChannelResponse.getChannelId();
        //2、修改频道相关设置
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        String splashImg = quickCreateChannelRequest.getSplashImg();
        LiveChannelSettingRequest.BasicSetting basicSetting = new LiveChannelSettingRequest.BasicSetting().setName(
                quickCreateChannelRequest.getName())
                //讲师登录密码
                .setChannelPasswd(quickCreateChannelRequest.getChannelPasswd())
                //课程图标
                .setCoverImg(quickCreateChannelRequest.getCoverImg())
                //引导封面图，必须开启引导封面图开关才能生效
                .setSplashImg(splashImg)
                //开启引导封面图
                .setSplashEnabled(StringUtils.isBlank(splashImg) ? LiveConstant.Flag.NO.getFlag() :
                        LiveConstant.Flag.YES.getFlag())
                //课程描述
                .setDesc(quickCreateChannelRequest.getDesc())
                //主讲人
                .setPublisher(quickCreateChannelRequest.getPublisher())
                .setLinkMicLimit(quickCreateChannelRequest.getLinkMicLimit());
        liveChannelSettingRequest.setChannelId(channelId)
                .setBasicSetting(basicSetting)
                .setRequestId(LiveSignUtil.generateUUID());
        Boolean liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                liveChannelSettingRequest);
        if (!liveChannelSettingResponse) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "修改频道的相关设置失败");
        }
        log.info("修改频道相关设置成功");
        //3、查询频道信息
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
        liveChannelBasicInfoRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
        liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().getChannelBasicInfo(
                liveChannelBasicInfoRequest);
        if (liveChannelBasicInfoResponse == null) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "查询频道基本信息失败");
        }
        return liveChannelBasicInfoResponse;
    }
    
    /**
     * 快速创建简单视频直播频道
     * @param quickCreateVideoChannelRequest 快速创建简单视频直播频道请求实体
     * @return 频道信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    public static LiveChannelBasicInfoResponse createEasyVideo(
            QuickCreateVideoChannelRequest quickCreateVideoChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        String scene = LiveConstant.SceneType.ALONE.getDesc();
        //1、创建频道
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        liveChannelRequest.setName(quickCreateVideoChannelRequest.getName())
                .setChannelPasswd(quickCreateVideoChannelRequest.getChannelPasswd())
                .setPureRtcEnabled(quickCreateVideoChannelRequest.getPureRtcEnabled())
                .setScene(scene)
                .setRequestId(LiveSignUtil.generateUUID());
        LiveChannelResponse liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
        if (liveChannelResponse == null) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "创建频道失败");
        }
        log.info("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
        String channelId = liveChannelResponse.getChannelId();
        //2、修改频道相关设置
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        String splashImg = quickCreateVideoChannelRequest.getSplashImg();
        LiveChannelSettingRequest.BasicSetting basicSetting = new LiveChannelSettingRequest.BasicSetting().setName(
                quickCreateVideoChannelRequest.getName())
                //讲师登录密码
                .setChannelPasswd(quickCreateVideoChannelRequest.getChannelPasswd())
                //课程图标
                .setCoverImg(quickCreateVideoChannelRequest.getCoverImg())
                //引导封面图，必须开启引导封面图开关才能生效
                .setSplashImg(splashImg)
                //开启引导封面图
                .setSplashEnabled(StringUtils.isBlank(splashImg) ? LiveConstant.Flag.NO.getFlag() :
                        LiveConstant.Flag.YES.getFlag())
                //课程描述
                .setDesc(quickCreateVideoChannelRequest.getDesc())
                //主讲人
                .setPublisher(quickCreateVideoChannelRequest.getPublisher())
                .setLinkMicLimit(quickCreateVideoChannelRequest.getLinkMicLimit());
        liveChannelSettingRequest.setChannelId(channelId)
                .setBasicSetting(basicSetting)
                .setRequestId(LiveSignUtil.generateUUID());
        Boolean liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                liveChannelSettingRequest);
        if (!liveChannelSettingResponse) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "修改频道的相关设置失败");
        }
        log.info("修改频道相关设置成功");
        //3、修改暖场图片
        if (StringUtils.isNotBlank(quickCreateVideoChannelRequest.getCoverImage())) {
            LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setCoverImage(quickCreateVideoChannelRequest.getCoverImage())
                    .setCoverHref(quickCreateVideoChannelRequest.getCoverHref())
                    .setRequestId(LiveSignUtil.generateUUID());
            Boolean result = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
            if (result == null || !result) {
                throw new PloyvSdkException(LiveConstant.ERROR_CODE, "暖场图片设置失败");
            }
        }
        //4、修改暖场视频
        if (StringUtils.isNotBlank(quickCreateVideoChannelRequest.getWarmUpFlv())) {
            LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
            liveSetWarmupVedioRequest.setChannelId(channelId)
                    .setWarmUpFlv(quickCreateVideoChannelRequest.getWarmUpFlv())
                    .setRequestId(LiveSignUtil.generateUUID());
            Boolean result = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
            if (result == null || !result) {
                throw new PloyvSdkException(LiveConstant.ERROR_CODE, "暖场视频设置失败");
            }
        }
        //5、查询频道信息
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
        liveChannelBasicInfoRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
        liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().getChannelBasicInfo(
                liveChannelBasicInfoRequest);
        if (liveChannelBasicInfoResponse == null) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "查询频道基本信息失败");
        }
        return liveChannelBasicInfoResponse;
    }
    
}
