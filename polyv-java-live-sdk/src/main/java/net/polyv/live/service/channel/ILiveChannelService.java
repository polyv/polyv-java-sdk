package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
import net.polyv.live.entity.channel.LiveChannelStreamInfoRequest;
import net.polyv.live.entity.channel.LiveChannelStreamInfoResponse;
import net.polyv.live.entity.channel.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.LiveCreateChannelTokenRequest;
import net.polyv.live.entity.channel.LiveCreateChannelVideoPlaybackRequest;
import net.polyv.live.entity.channel.LiveCreateChannelVideoPlaybackResponse;
import net.polyv.live.entity.channel.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.LiveCreateSonChannelTokenRequest;
import net.polyv.live.entity.channel.LiveCutoffChannelStreamRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelListRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelRequest;
import net.polyv.live.entity.channel.LiveDeleteSonChannelRequest;
import net.polyv.live.entity.channel.LiveListChannelStreamStatusRequest;
import net.polyv.live.entity.channel.LiveListChannelStreamStatusResponse;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.channel.LiveMergeChannelVideoAsyncRequest;
import net.polyv.live.entity.channel.LiveMergeChannelVideoRequest;
import net.polyv.live.entity.channel.LiveResumeChannelStreamRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoListRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoListResponse;
import net.polyv.live.entity.channel.LiveSonChannelInfoRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoResponse;
import net.polyv.live.entity.channel.LiveUpdateSonChannelInfoRequest;

/**
 * 直播频道管理
 * @author: thomas
 **/
public interface ILiveChannelService {
    
    /**
     * 直播频道创建
     * @param liveChannelRequest 直播频道请求实体
     * @return 频道数据
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 创建并初始化频道
     * @param liveChannelInitRequest 请求体
     * @return 响应体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelInitResponse createChannelInit(LiveChannelInitRequest liveChannelInitRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量创建频道
     * @param liveCreateChannelListRequest 批量创建频道请求体
     * @return 批量创建频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveCreateChannelListResponse createChannelList(LiveCreateChannelListRequest liveCreateChannelListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改频道的相关设置
     * @param liveChannelSettingRequest 修改频道的相关设置请求体
     * @return 渠道设置信息
     * @throws IOException 异常
     */
    String updateChannelSetting(LiveChannelSettingRequest liveChannelSettingRequest) throws IOException;
    
    
    /**
     * 设置直播频道详情
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return 频道详情
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询课件重制任务列表
     * @param liveListChannelPPTRecordRequest 查询课件重制任务列表请求实体
     * @return 查询课件重制任务列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelPPTRecordResponse listPPTRecord(LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道密码
     * @param liveChannelPasswordSettingRequest 设置频道密码请求体
     * @return 设置频道密码返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelPassword(LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除直播频道
     * @param liveDeleteChannelRequest 删除直播频道请求体
     * @return 删除直播频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String deleteChannel(LiveDeleteChannelRequest liveDeleteChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量删除频道
     * @param liveDeleteChannelListRequest 批量删除频道请求体
     * @return 批量删除频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String deleteChannelList(LiveDeleteChannelListRequest liveDeleteChannelListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道单点登陆token
     * @param liveCreateChannelTokenRequest 设置频道单点登陆token请求体
     * @return 设置频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createChannelToken(LiveCreateChannelTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道信息
     * @param liveChannelInfoRequest 查询频道信息请求体
     * @return 查询频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelInfoResponse channelInfo(LiveChannelInfoRequest liveChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道基本信息
     * @param liveChannelBasicInfoRequest 查询频道基本信息请求体
     * @return 查询频道基本信息返回提
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelBasicInfoResponse channelBasicInfo(LiveChannelBasicInfoRequest liveChannelBasicInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询授权和连麦的token
     * @param liveCreateChannelTokenRequest 查询授权和连麦的token请求体
     * @return 查询授权和连麦的token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelAuthTokenResponse channelAuthToken(LiveChannelAuthTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 创建子频道
     * @param liveCreateSonChannelRequest 创建子频道请求体
     * @return 创建子频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveCreateSonChannelResponse createSonChannel(LiveCreateSonChannelRequest liveCreateSonChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置子频道信息
     * @param liveUpdateSonChannelInfoRequest 设置子频道信息请求体
     * @return 设置子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateSonChannelInfo(LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置子频道单点登陆token
     * @param liveCreateSonChannelTokenRequest 设置子频道单点登陆token请求体
     * @return 设置子频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createSonChannelToken(LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询子频道信息
     * @param liveSonChannelInfoRequest 查询子频道信息请求体
     * @return 查询子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveSonChannelInfoResponse sonChannelInfo(LiveSonChannelInfoRequest liveSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道号下所有子频道信息
     * @param liveSonChannelInfoListRequest 查询频道号下所有子频道信息请求体
     * @return 查询频道号下所有子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveSonChannelInfoListResponse sonChannelInfoList(LiveSonChannelInfoListRequest liveSonChannelInfoListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除子频道
     * @param liveDeleteSonChannelRequest 删除子频道请求体
     * @return 删除子频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String deleteSonChannel(LiveDeleteSonChannelRequest liveDeleteSonChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 恢复直播频道推流
     * @param liveResumeChannelStreamRequest 恢复直播频道推流请求体
     * @return 恢复直播频道推流返回体，success为成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String resumeChannelStream(LiveResumeChannelStreamRequest liveResumeChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 禁止直播频道推流
     * @param liveCutoffChannelStreamRequest 禁止直播频道推流请求实体
     * @return 禁止直播频道推流返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String cutoffChannelStream(LiveCutoffChannelStreamRequest liveCutoffChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量查询频道直播流状态，API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/live-status/
     * @param liveListChannelStreamStatusRequest 批量查询频道直播流状态请求实体
     * @return 批量查询频道直播流状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelStreamStatusResponse listChannelLiveStream(
            LiveListChannelStreamStatusRequest liveListChannelStreamStatusRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道实时推流信息，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/hqzbxx/get-stream-info/
     * @param liveChannelStreamInfoRequest 查询频道实时推流信息请求实体
     * @return 查询频道实时推流信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelStreamInfoResponse channelStreamInfo(LiveChannelStreamInfoRequest liveChannelStreamInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 将点播中的视频添加到视频库
     * @param liveCreateChannelVideoPlaybackRequest 将点播中的视频添加到视频库请求实体
     * @return 将点播中的视频添加到视频库返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveCreateChannelVideoPlaybackResponse addChannelVideoPlayback(
            LiveCreateChannelVideoPlaybackRequest liveCreateChannelVideoPlaybackRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 合并录制文件
     * @param liveMergeChannelVideoRequest 合并录制文件请求实体
     * @return 合并录制文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String mergeChannelVideo(LiveMergeChannelVideoRequest liveMergeChannelVideoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 异步合并直播录制文件
     * @param liveMergeChannelVideoAsyncRequest 异步合并直播录制文件请求实体
     * @return 异步合并直播录制文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String mergeChannelVideoAsync(LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
