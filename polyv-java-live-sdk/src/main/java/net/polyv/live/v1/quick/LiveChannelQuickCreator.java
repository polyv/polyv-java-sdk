package net.polyv.live.v1.quick;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.StringUtils;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.channel.QuickCreateChannelResponse;
import net.polyv.live.v1.entity.channel.doc.LiveCreateChannelDocRequest;
import net.polyv.live.v1.entity.channel.doc.LiveCreateChannelDocResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoListRequest;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoListResponse;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoResponse;
import net.polyv.live.v1.entity.chat.LiveSetTeacherDataRequest;
import net.polyv.live.v1.entity.player.LiveSetPlayerImgRequest;
import net.polyv.live.v1.entity.player.LiveSetWarmupVedioRequest;
import net.polyv.live.v1.entity.quick.QuickCreatePPTChannelRequest;
import net.polyv.live.v1.entity.quick.QuickCreateVideoChannelRequest;
import net.polyv.live.v1.service.channel.impl.LiveChannelDocServiceImpl;
import net.polyv.live.v1.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.v1.service.chat.impl.LiveChatRoomServiceImpl;
import net.polyv.live.v1.service.player.impl.LivePlayerServiceImpl;

/**
 * 频道快捷创建器
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelQuickCreator {
    
    /**
     * 快速创建简单三分屏频道
     * @param quickCreateChannelRequest 快速创建频道请求实体
     * @return 频道基本信息
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
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
    public QuickCreateChannelResponse quickCreatePPTSence(QuickCreatePPTChannelRequest quickCreateChannelRequest,
            LiveCreateSonChannelListRequest liveCreateSonChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        QuickCreateChannelResponse quickCreateChannelResponse = new QuickCreateChannelResponse();
        String scene = LiveConstant.SceneType.PPT.getDesc();
        //1、创建频道
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        liveChannelRequest.setName(quickCreateChannelRequest.getName())
                .setChannelPasswd(quickCreateChannelRequest.getChannelPasswd())
                .setPureRtcEnabled(quickCreateChannelRequest.getPureRtcEnabled())
                .setScene(scene)
                .setRequestId(quickCreateChannelRequest.getRequestId());
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
                //设置开播时间
                .setStartTime(quickCreateChannelRequest.getStartTime())
                //主讲人
                .setPublisher(quickCreateChannelRequest.getPublisher())
                .setLinkMicLimit(quickCreateChannelRequest.getLinkMicLimit());
        liveChannelSettingRequest.setChannelId(channelId)
                .setBasicSetting(basicSetting)
                .setRequestId(quickCreateChannelRequest.getRequestId());
        Boolean liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                liveChannelSettingRequest);
        if (!liveChannelSettingResponse) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "修改频道的相关设置失败");
        }
        log.info("修改频道相关设置成功");
        
        //3、修改暖场图片
        if (StringUtils.isNotBlank(quickCreateChannelRequest.getCoverImage())) {
            LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setCoverImage(quickCreateChannelRequest.getCoverImage())
                    .setCoverHref(quickCreateChannelRequest.getCoverHref())
                    .setRequestId(quickCreateChannelRequest.getRequestId());
            Boolean result = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
            if (result == null || !result) {
                throw new PloyvSdkException(LiveConstant.ERROR_CODE, "暖场图片设置失败");
            }
        }
        //4、修改暖场视频
        if (StringUtils.isNotBlank(quickCreateChannelRequest.getWarmUpFlv())) {
            LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
            liveSetWarmupVedioRequest.setChannelId(channelId)
                    .setWarmUpFlv(quickCreateChannelRequest.getWarmUpFlv())
                    .setRequestId(quickCreateChannelRequest.getRequestId());
            Boolean result = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
            if (result == null || !result) {
                throw new PloyvSdkException(LiveConstant.ERROR_CODE, "暖场视频设置失败");
            }
        }
        
        //5、设置讲师信息
        if (StringUtils.isNotBlank(quickCreateChannelRequest.getNickname())) {
            LiveSetTeacherDataRequest liveSetTeacherDataRequest = new LiveSetTeacherDataRequest();
            Boolean result = null;
            liveSetTeacherDataRequest.setChannelId(channelId)
                    .setNickname(quickCreateChannelRequest.getNickname())
                    .setActor(quickCreateChannelRequest.getActor())
                    .setPasswd(quickCreateChannelRequest.getChannelPasswd())
                    .setAvatar(quickCreateChannelRequest.getAvatar())
                    .setRequestId(quickCreateChannelRequest.getRequestId());
            result = new LiveChatRoomServiceImpl().setChannelTeacherMsg(liveSetTeacherDataRequest);
            if (result == null || !result) {
                throw new PloyvSdkException(LiveConstant.ERROR_CODE, "设置讲师信息失败");
            }
            log.info("设置讲师信息成功");
        }
        
        //6、批量创建子频道
        if (liveCreateSonChannelListRequest != null) {
            createSonChannelList(channelId, liveCreateSonChannelListRequest, quickCreateChannelRequest.getRequestId());
        }
        //7、上传直播文档
        if (quickCreateChannelRequest.getFile() != null) {
            LiveCreateChannelDocRequest liveCreateChannelDocRequest = new LiveCreateChannelDocRequest();
            LiveCreateChannelDocResponse liveCreateChannelDocResponse;
            liveCreateChannelDocRequest.setChannelId(channelId)
                    .setType(quickCreateChannelRequest.getType())
                    .setFile(quickCreateChannelRequest.getFile())
                    .setDocName(quickCreateChannelRequest.getDocName())
                    .setCallbackUrl(quickCreateChannelRequest.getCallbackUrl())
                    .setRequestId(quickCreateChannelRequest.getRequestId());
            liveCreateChannelDocResponse = new LiveChannelDocServiceImpl().createChannelDoc(
                    liveCreateChannelDocRequest);
            if (liveCreateChannelDocResponse == null) {
                throw new PloyvSdkException(LiveConstant.ERROR_CODE, "上传频道文档失败");
            }
        }
        //8、查询频道信息
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = getLiveChannelBasicInfoResponse(channelId,
                quickCreateChannelRequest.getRequestId());
        quickCreateChannelResponse.setLiveChannelBasicInfoResponse(liveChannelBasicInfoResponse);
        //9、查询子频道信息
        List<LiveSonChannelInfoResponse> sonChannelInfoList = getSonChannelInfoList(channelId,
                quickCreateChannelRequest.getRequestId());
        quickCreateChannelResponse.setSonChannelInfos(sonChannelInfoList);
        return quickCreateChannelResponse;
    }
    
    /**
     * 快速创建简单视频直播频道
     * @param quickCreateVideoChannelRequest 快速创建简单视频直播频道请求实体
     * @return 频道信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
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
    public QuickCreateChannelResponse quickCreateVideoSence(QuickCreateVideoChannelRequest quickCreateVideoChannelRequest,
            LiveCreateSonChannelListRequest liveCreateSonChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        QuickCreateChannelResponse quickCreateChannelResponse = new QuickCreateChannelResponse();
        String scene = LiveConstant.SceneType.ALONE.getDesc();
        //1、创建频道
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        liveChannelRequest.setName(quickCreateVideoChannelRequest.getName())
                .setChannelPasswd(quickCreateVideoChannelRequest.getChannelPasswd())
                .setPureRtcEnabled(quickCreateVideoChannelRequest.getPureRtcEnabled())
                .setScene(scene)
                .setRequestId(quickCreateVideoChannelRequest.getRequestId());
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
                //设置开播时间
                .setStartTime(quickCreateVideoChannelRequest.getStartTime())
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
                .setRequestId(quickCreateVideoChannelRequest.getRequestId());
        Boolean liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                liveChannelSettingRequest);
        if (!liveChannelSettingResponse) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "修改频道的相关设置失败");
        }
        log.info("修改频道相关设置成功");
        //3、批量创建子频道
        if (liveCreateSonChannelListRequest != null) {
            createSonChannelList(channelId, liveCreateSonChannelListRequest,
                    quickCreateVideoChannelRequest.getRequestId());
        }
        //4、修改暖场图片
        if (StringUtils.isNotBlank(quickCreateVideoChannelRequest.getCoverImage())) {
            LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setCoverImage(quickCreateVideoChannelRequest.getCoverImage())
                    .setCoverHref(quickCreateVideoChannelRequest.getCoverHref())
                    .setRequestId(quickCreateVideoChannelRequest.getRequestId());
            Boolean result = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
            if (result == null || !result) {
                throw new PloyvSdkException(LiveConstant.ERROR_CODE, "暖场图片设置失败");
            }
        }
        //5、修改暖场视频
        if (StringUtils.isNotBlank(quickCreateVideoChannelRequest.getWarmUpFlv())) {
            LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
            liveSetWarmupVedioRequest.setChannelId(channelId)
                    .setWarmUpFlv(quickCreateVideoChannelRequest.getWarmUpFlv())
                    .setRequestId(quickCreateVideoChannelRequest.getRequestId());
            Boolean result = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
            if (result == null || !result) {
                throw new PloyvSdkException(LiveConstant.ERROR_CODE, "暖场视频设置失败");
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
                    .setAvatar(quickCreateVideoChannelRequest.getAvatar())
                    .setRequestId(quickCreateVideoChannelRequest.getRequestId());
            result = new LiveChatRoomServiceImpl().setChannelTeacherMsg(liveSetTeacherDataRequest);
            if (result == null || !result) {
                throw new PloyvSdkException(LiveConstant.ERROR_CODE, "设置讲师信息失败");
            }
            log.info("设置讲师信息成功");
        }
        
        //7、查询频道信息
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = getLiveChannelBasicInfoResponse(channelId,
                quickCreateVideoChannelRequest.getRequestId());
        quickCreateChannelResponse.setLiveChannelBasicInfoResponse(liveChannelBasicInfoResponse);
        //8、查询子频道信息
        List<LiveSonChannelInfoResponse> sonChannelInfoList = getSonChannelInfoList(channelId,
                quickCreateVideoChannelRequest.getRequestId());
        quickCreateChannelResponse.setSonChannelInfos(sonChannelInfoList);
        return quickCreateChannelResponse;
    }
    
    private LiveChannelBasicInfoResponse getLiveChannelBasicInfoResponse(String channelId, String requestId)
            throws IOException, NoSuchAlgorithmException {
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        liveChannelBasicInfoRequest.setChannelId(channelId).setRequestId(requestId);
        liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().getChannelBasicInfo(
                liveChannelBasicInfoRequest);
        if (liveChannelBasicInfoResponse == null) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "查询频道基本信息失败");
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
    private List<LiveSonChannelInfoResponse> getSonChannelInfoList(String channelId, String requestId)
            throws IOException, NoSuchAlgorithmException {
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse;
        liveSonChannelInfoListRequest.setChannelId(channelId).setRequestId(requestId);
        liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().getSonChannelInfoList(
                liveSonChannelInfoListRequest);
        if (liveSonChannelInfoResponse == null) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "查询子频道信息失败");
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
    private void createSonChannelList(String channelId, LiveCreateSonChannelListRequest liveCreateSonChannelListRequest,
            String requestId) throws IOException, NoSuchAlgorithmException {
        liveCreateSonChannelListRequest.setChannelId(channelId).setRequestId(requestId);
        LiveCreateSonChannelListResponse liveCreateSonChannelListResponse =
                new LiveChannelOperateServiceImpl().createSonChannelList(
                liveCreateSonChannelListRequest);
        if (liveCreateSonChannelListResponse == null) {
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, "批量创建子频道失败");
        }
    }
    
}
