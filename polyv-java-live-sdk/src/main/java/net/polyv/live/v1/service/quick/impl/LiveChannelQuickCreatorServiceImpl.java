package net.polyv.live.v1.service.quick.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.constant.Constant;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.StringUtils;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.channel.doc.LiveCreateChannelDocRequest;
import net.polyv.live.v1.entity.channel.doc.LiveCreateChannelDocResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveDeleteChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoResponse;
import net.polyv.live.v1.entity.chat.LiveSetTeacherDataRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerImgRequest;
import net.polyv.live.v1.entity.player.LiveSetWarmupVedioRequest;
import net.polyv.live.v1.entity.quick.QuickCreateChannelResponse;
import net.polyv.live.v1.entity.quick.QuickCreatePPTChannelRequest;
import net.polyv.live.v1.entity.quick.QuickCreateVideoChannelRequest;
import net.polyv.live.v1.service.channel.impl.LiveChannelDocServiceImpl;
import net.polyv.live.v1.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.v1.service.chat.impl.LiveChatRoomServiceImpl;
import net.polyv.live.v1.service.player.impl.LivePlayerServiceImpl;
import net.polyv.live.v1.service.quick.ILiveChannelQuickCreatorService;

/**
 * 频道快捷创建器
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelQuickCreatorServiceImpl implements ILiveChannelQuickCreatorService {
    
    /**
     * 快速创建简单三分屏频道
     * @param quickCreateChannelRequest 快速创建频道请求实体
     * @return 频道基本信息
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
    @Override
    public QuickCreateChannelResponse quickCreatePPTSence(QuickCreatePPTChannelRequest quickCreateChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        return quickCreatePPTSence(quickCreateChannelRequest, null);
    }
    
    /**
     * 快速创建简单三分屏频道
     * @param quickCreateChannelRequest 快速创建频道请求实体
     * @param liveCreateSonChannelListRequest 批量创建子频道请求实体
     * @return 频道基本信息
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
    @Override
    public QuickCreateChannelResponse quickCreatePPTSence(QuickCreatePPTChannelRequest quickCreateChannelRequest,
            LiveCreateSonChannelListRequest liveCreateSonChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        QuickCreateChannelResponse quickCreateChannelResponse = new QuickCreateChannelResponse();
        String channelId = null;
        try {
            String scene = LiveConstant.SceneType.PPT.getDesc();
            //1、创建频道
            LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
            liveChannelRequest.setName(quickCreateChannelRequest.getName())
                    .setChannelPasswd(quickCreateChannelRequest.getChannelPasswd())
                    .setPureRtcEnabled(quickCreateChannelRequest.getPureRtcEnabled())
                    .setScene(scene);
            LiveChannelResponse liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(
                    liveChannelRequest);
            if (liveChannelResponse == null) {
                throw new PloyvSdkException(Constant.ERROR_CODE, "创建频道失败");
            }
            log.info("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
            channelId = liveChannelResponse.getChannelId();
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
                    //设置开播时间
                    .setStartTime(quickCreateChannelRequest.getStartTime())
                    //主讲人
                    .setPublisher(quickCreateChannelRequest.getPublisher())
                    .setLinkMicLimit(quickCreateChannelRequest.getLinkMicLimit());
            liveChannelSettingRequest.setChannelId(channelId).setBasicSetting(basicSetting);
            Boolean liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                    liveChannelSettingRequest);
            if (!liveChannelSettingResponse) {
                throw new PloyvSdkException(Constant.ERROR_CODE, "修改频道的相关设置失败");
            }
            log.info("修改频道相关设置成功");
        
            //3、修改暖场图片
            String coverImage = quickCreateChannelRequest.getCoverImage();
            if (StringUtils.isNotBlank(coverImage)) {
                LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
                liveSetChatAdminDataRequest.setChannelId(channelId)
                        .setCoverImage(coverImage)
                        .setCoverHref(quickCreateChannelRequest.getCoverHref());
                Boolean result = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
                if (result == null || !result) {
                    throw new PloyvSdkException(Constant.ERROR_CODE, "暖场图片设置失败");
                }
            }
            //4、修改暖场视频
            String warmUpFlv = quickCreateChannelRequest.getWarmUpFlv();
            if (StringUtils.isNotBlank(warmUpFlv)) {
                LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
                liveSetWarmupVedioRequest.setChannelId(channelId)
                        .setWarmUpFlv(warmUpFlv);
                Boolean result = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
                if (result == null || !result) {
                    throw new PloyvSdkException(Constant.ERROR_CODE, "暖场视频设置失败");
                }
            }
        
            //5、设置讲师信息
            String nickname = quickCreateChannelRequest.getNickname();
            if (StringUtils.isNotBlank(nickname)) {
                LiveSetTeacherDataRequest liveSetTeacherDataRequest = new LiveSetTeacherDataRequest();
                Boolean result = null;
                liveSetTeacherDataRequest.setChannelId(channelId)
                        .setNickname(nickname)
                        .setActor(quickCreateChannelRequest.getActor())
                        .setPasswd(quickCreateChannelRequest.getChannelPasswd())
                        .setAvatar(quickCreateChannelRequest.getAvatar());
                result = new LiveChatRoomServiceImpl().setChannelTeacherMsg(liveSetTeacherDataRequest);
                if (result == null || !result) {
                    throw new PloyvSdkException(Constant.ERROR_CODE, "设置讲师信息失败");
                }
                log.info("设置讲师信息成功");
            }
        
            //6、批量创建子频道
            if (liveCreateSonChannelListRequest != null) {
                createSonChannelList(channelId, liveCreateSonChannelListRequest);
            }
            //7、上传直播文档
            if (quickCreateChannelRequest.getFile() != null) {
                LiveCreateChannelDocRequest liveCreateChannelDocRequest = new LiveCreateChannelDocRequest();
                LiveCreateChannelDocResponse liveCreateChannelDocResponse;
                liveCreateChannelDocRequest.setChannelId(channelId)
                        .setType(quickCreateChannelRequest.getType())
                        .setFile(quickCreateChannelRequest.getFile())
                        .setDocName(quickCreateChannelRequest.getDocName())
                        .setCallbackUrl(quickCreateChannelRequest.getCallbackUrl());
                liveCreateChannelDocResponse = new LiveChannelDocServiceImpl().createChannelDoc(
                        liveCreateChannelDocRequest);
                if (liveCreateChannelDocResponse == null) {
                    throw new PloyvSdkException(Constant.ERROR_CODE, "上传频道文档失败");
                }
            }
            //8、查询频道信息
            LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = getLiveChannelBasicInfoResponse(channelId);
            quickCreateChannelResponse.setLiveChannelBasicInfoResponse(liveChannelBasicInfoResponse);
            //9、查询子频道信息
            List<LiveSonChannelInfoResponse> sonChannelInfoList = getSonChannelInfoList(channelId);
            quickCreateChannelResponse.setSonChannelInfos(sonChannelInfoList);
        } catch (PloyvSdkException e) {
            tryDeleteChannel(channelId);
            throw e;
        }
        return quickCreateChannelResponse;
    }
    
    /**
     * 快速创建简单视频直播频道
     * @param quickCreateVideoChannelRequest 快速创建简单视频直播频道请求实体
     * @return 频道信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public QuickCreateChannelResponse quickCreateVideoSence(QuickCreateVideoChannelRequest quickCreateVideoChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        return quickCreateVideoSence(quickCreateVideoChannelRequest, null);
    }
    
    /**
     * 快速创建简单视频直播频道
     * @param quickCreateVideoChannelRequest 快速创建简单视频直播频道请求实体
     * @param liveCreateSonChannelListRequest 批量创建子频道请求实体
     * @return 频道信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public QuickCreateChannelResponse quickCreateVideoSence(QuickCreateVideoChannelRequest quickCreateVideoChannelRequest,
            LiveCreateSonChannelListRequest liveCreateSonChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        QuickCreateChannelResponse quickCreateChannelResponse = new QuickCreateChannelResponse();
        String channelId = null;
        try {
            String scene = LiveConstant.SceneType.ALONE.getDesc();
            //1、创建频道
            LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
            liveChannelRequest.setName(quickCreateVideoChannelRequest.getName())
                    .setChannelPasswd(quickCreateVideoChannelRequest.getChannelPasswd())
                    .setPureRtcEnabled(quickCreateVideoChannelRequest.getPureRtcEnabled())
                    .setScene(scene);
            LiveChannelResponse liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(
                    liveChannelRequest);
            if (liveChannelResponse == null) {
                throw new PloyvSdkException(Constant.ERROR_CODE, "创建频道失败");
            }
            log.info("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
            channelId = liveChannelResponse.getChannelId();
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
                    //设置开播时间
                    .setStartTime(quickCreateVideoChannelRequest.getStartTime())
                    //开启引导封面图
                    .setSplashEnabled(StringUtils.isBlank(splashImg) ? LiveConstant.Flag.NO.getFlag() : LiveConstant.Flag.YES.getFlag())
                    //课程描述
                    .setDesc(quickCreateVideoChannelRequest.getDesc())
                    //主讲人
                    .setPublisher(quickCreateVideoChannelRequest.getPublisher()).setLinkMicLimit(quickCreateVideoChannelRequest.getLinkMicLimit());
            liveChannelSettingRequest.setChannelId(channelId).setBasicSetting(basicSetting);
            Boolean liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                    liveChannelSettingRequest);
            if (!liveChannelSettingResponse) {
                throw new PloyvSdkException(Constant.ERROR_CODE, "修改频道的相关设置失败");
            }
            log.info("修改频道相关设置成功");
            //3、批量创建子频道
            if (liveCreateSonChannelListRequest != null) {
                createSonChannelList(channelId, liveCreateSonChannelListRequest);
            }
            //4、修改暖场图片
            if (StringUtils.isNotBlank(quickCreateVideoChannelRequest.getCoverImage())) {
                LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
                liveSetChatAdminDataRequest.setChannelId(channelId)
                        .setCoverImage(quickCreateVideoChannelRequest.getCoverImage())
                        .setCoverHref(quickCreateVideoChannelRequest.getCoverHref());
                Boolean result = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
                if (result == null || !result) {
                    throw new PloyvSdkException(Constant.ERROR_CODE, "暖场图片设置失败");
                }
            }
            //5、修改暖场视频
            if (StringUtils.isNotBlank(quickCreateVideoChannelRequest.getWarmUpFlv())) {
                LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
                liveSetWarmupVedioRequest.setChannelId(channelId).setWarmUpFlv(quickCreateVideoChannelRequest.getWarmUpFlv());
                Boolean result = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
                if (result == null || !result) {
                    throw new PloyvSdkException(Constant.ERROR_CODE, "暖场视频设置失败");
                }
            }
            //6、设置讲师信息
            if (StringUtils.isNotBlank(quickCreateVideoChannelRequest.getNickname())) {
                LiveSetTeacherDataRequest liveSetTeacherDataRequest = new LiveSetTeacherDataRequest();
                Boolean result = null;
                liveSetTeacherDataRequest.setChannelId(channelId)
                        .setNickname(quickCreateVideoChannelRequest.getNickname())
                        .setActor(quickCreateVideoChannelRequest.getActor())
                        .setPasswd(quickCreateVideoChannelRequest.getChannelPasswd())
                        .setAvatar(quickCreateVideoChannelRequest.getAvatar());
                result = new LiveChatRoomServiceImpl().setChannelTeacherMsg(liveSetTeacherDataRequest);
                if (result == null || !result) {
                    throw new PloyvSdkException(Constant.ERROR_CODE, "设置讲师信息失败");
                }
                log.info("设置讲师信息成功");
            }
    
            //7、查询频道信息
            LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = getLiveChannelBasicInfoResponse(channelId);
            quickCreateChannelResponse.setLiveChannelBasicInfoResponse(liveChannelBasicInfoResponse);
            //8、查询子频道信息
            List<LiveSonChannelInfoResponse> sonChannelInfoList = getSonChannelInfoList(channelId);
            quickCreateChannelResponse.setSonChannelInfos(sonChannelInfoList);
        }catch (PloyvSdkException e) {
            tryDeleteChannel(channelId);
            throw e;
        }
        return quickCreateChannelResponse;
    }
    
    /**
     * 试图去删除频道
     * 参数不为空时，试图去根据频道号删除频道，内部会捕获所有异常
     * @param channelId 需要删除的频道号
     */
    private void tryDeleteChannel(String channelId) {
        try {
            if (StringUtils.isNotBlank(channelId)) {
                log.info("捕获创建频道异常，即将删除已创建频道");
                LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
                liveDeleteChannelRequest.setChannelId(channelId);
                Boolean deleteChannel = new LiveChannelOperateServiceImpl().deleteChannel(liveDeleteChannelRequest);
                log.info("根据异常删除频道返回值为：{}", deleteChannel);
            }
        } catch (Exception delException) {
            log.error("根据异常删除频道失败，频道号为{}", channelId, delException);
        }
    }
    
    private LiveChannelBasicInfoResponse getLiveChannelBasicInfoResponse(String channelId)
            throws IOException, NoSuchAlgorithmException {
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        liveChannelBasicInfoRequest.setChannelId(channelId);
        liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().getChannelBasicInfo(
                liveChannelBasicInfoRequest);
        if (liveChannelBasicInfoResponse == null) {
            throw new PloyvSdkException(Constant.ERROR_CODE, "查询频道基本信息失败");
        }
        return liveChannelBasicInfoResponse;
    }
    
    /**
     * 查询频道下子频道信息列表
     * @param channelId 频道号
     * @return 子频道信息列表
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
    private List<LiveSonChannelInfoResponse> getSonChannelInfoList(String channelId)
            throws IOException, NoSuchAlgorithmException {
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse;
        liveSonChannelInfoListRequest.setChannelId(channelId);
        liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().getSonChannelInfoList(
                liveSonChannelInfoListRequest);
        if (liveSonChannelInfoResponse == null) {
            throw new PloyvSdkException(Constant.ERROR_CODE, "查询子频道信息失败");
        }
        return liveSonChannelInfoResponse.getSonChannelInfos();
    }
    
    /**
     * 批量创建子频道
     * @param channelId 频道号
     * @param liveCreateSonChannelListRequest 批量创建子频道请求实体
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
    private void createSonChannelList(String channelId, LiveCreateSonChannelListRequest liveCreateSonChannelListRequest) throws IOException, NoSuchAlgorithmException {
        liveCreateSonChannelListRequest.setChannelId(channelId);
        LiveCreateSonChannelListResponse liveCreateSonChannelListResponse =
                new LiveChannelOperateServiceImpl().createSonChannelList(
                liveCreateSonChannelListRequest);
        if (liveCreateSonChannelListResponse == null) {
            throw new PloyvSdkException(Constant.ERROR_CODE, "批量创建子频道失败");
        }
    }
    
}
