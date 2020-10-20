package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.channel.operate.LiveChannelAuthTokenRequest;
import net.polyv.live.entity.channel.operate.LiveChannelAuthTokenResponse;
import net.polyv.live.entity.channel.operate.LiveChannelBasicInfoRequest;
import net.polyv.live.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.entity.channel.operate.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInfoRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInfoResponse;
import net.polyv.live.entity.channel.operate.LiveChannelInitRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInitResponse;
import net.polyv.live.entity.channel.viewdata.LiveChannelMaxHistoryConcurrentRequest;
import net.polyv.live.entity.channel.operate.LiveChannelPasswordSettingRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackEnabledInfoRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackEnabledRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackSettingRequest;
import net.polyv.live.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.state.LiveChannelStreamInfoRequest;
import net.polyv.live.entity.channel.state.LiveChannelStreamInfoResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoSortRequest;
import net.polyv.live.entity.channel.viewdata.LiveChannelViewerConcurrenceRequest;
import net.polyv.live.entity.channel.viewdata.LiveChannelViewerConcurrenceResponse;
import net.polyv.live.entity.channel.playback.LiveConvertChannelVideoListAsyncRequest;
import net.polyv.live.entity.channel.playback.LiveConvertChannelVideoRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.operate.LiveCreateChannelPPTRecordRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelTokenRequest;
import net.polyv.live.entity.channel.playback.LiveCreateChannelVideoPlaybackRequest;
import net.polyv.live.entity.channel.playback.LiveCreateChannelVideoPlaybackResponse;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelTokenRequest;
import net.polyv.live.entity.channel.state.LiveCutoffChannelStreamRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteChannelListRequest;
import net.polyv.live.entity.channel.playback.LiveDeleteChannelPlaybackVideoRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteChannelRequest;
import net.polyv.live.entity.channel.playback.LiveDeleteChannelVideoRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteSonChannelRequest;
import net.polyv.live.entity.channel.doc.LiveListChannelDocRequest;
import net.polyv.live.entity.channel.doc.LiveListChannelDocResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelMicRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelMicResponse;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.channel.playback.LiveListChannelSessionInfoRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelSessionInfoResponse;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusRequest;
import net.polyv.live.entity.channel.state.LiveListChannelStreamStatusResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelSummaryRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelSummaryResponse;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewerCountRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewerCountResponse;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewlogRequest;
import net.polyv.live.entity.channel.viewdata.LiveListChannelViewlogResponse;
import net.polyv.live.entity.channel.playback.LiveMergeChannelVideoAsyncRequest;
import net.polyv.live.entity.channel.playback.LiveMergeChannelVideoRequest;
import net.polyv.live.entity.channel.state.LiveResumeChannelStreamRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListResponse;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoResponse;
import net.polyv.live.entity.channel.operate.LiveUpdateSonChannelInfoRequest;

/**
 * 直播频道操作接口
 * @author: thomas
 **/
public interface ILiveChannelOperateService {
    
    /**
     * 创建直播频道
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/create-channel/
     * @param liveChannelRequest 直播频道请求实体
     * @return 频道数据
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 创建并初始化频道
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/basic-create/
     * @param liveChannelInitRequest 请求体
     * @return 响应体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelInitResponse createChannelInit(LiveChannelInitRequest liveChannelInitRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量创建频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/batch-create-channels/
     * @param liveCreateChannelListRequest 批量创建频道请求体
     * @return 批量创建频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveCreateChannelListResponse createChannelList(LiveCreateChannelListRequest liveCreateChannelListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改频道的相关设置
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/update-channel-detail-setting/
     * @param liveChannelSettingRequest 修改频道的相关设置请求体
     * @return 渠道设置信息
     * @throws IOException 异常
     */
    String updateChannelSetting(LiveChannelSettingRequest liveChannelSettingRequest) throws IOException;
    
    /**
     * 设置频道详情
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/detail-update/
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return 频道详情
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询课件重制任务列表
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/pptrecord-list/
     * @param liveListChannelPPTRecordRequest 查询课件重制任务列表请求实体
     * @return 查询课件重制任务列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelPPTRecordResponse listPPTRecord(LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道密码
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/updatepasswd/
     * @param liveChannelPasswordSettingRequest 设置频道密码请求体
     * @return 设置频道密码返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateChannelPassword(LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除直播频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/deletechannel/
     * @param liveDeleteChannelRequest 删除直播频道请求体
     * @return 删除直播频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String deleteChannel(LiveDeleteChannelRequest liveDeleteChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量删除频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/batch-delete-channels/
     * @param liveDeleteChannelListRequest 批量删除频道请求体
     * @return 批量删除频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String deleteChannelList(LiveDeleteChannelListRequest liveDeleteChannelListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道单点登陆token
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/set-token-1/
     * @param liveCreateChannelTokenRequest 设置频道单点登陆token请求体
     * @return 设置频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createChannelToken(LiveCreateChannelTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道信息
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/getchannelid-2/
     * @param liveChannelInfoRequest 查询频道信息请求体
     * @return 查询频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelInfoResponse channelInfo(LiveChannelInfoRequest liveChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道基本信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/get-detail-setting/
     * @param liveChannelBasicInfoRequest 查询频道基本信息请求体
     * @return 查询频道基本信息返回提
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelBasicInfoResponse channelBasicInfo(LiveChannelBasicInfoRequest liveChannelBasicInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询授权和连麦的token
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/get-chat-token/
     * @param liveCreateChannelTokenRequest 查询授权和连麦的token请求体
     * @return 查询授权和连麦的token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelAuthTokenResponse channelAuthToken(LiveChannelAuthTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 创建子频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/add-account/
     * @param liveCreateSonChannelRequest 创建子频道请求体
     * @return 创建子频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveCreateSonChannelResponse createSonChannel(LiveCreateSonChannelRequest liveCreateSonChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置子频道信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/update-account/
     * @param liveUpdateSonChannelInfoRequest 设置子频道信息请求体
     * @return 设置子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String updateSonChannelInfo(LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置子频道单点登陆token
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/set-account-token/
     * @param liveCreateSonChannelTokenRequest 设置子频道单点登陆token请求体
     * @return 设置子频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createSonChannelToken(LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询子频道信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-account/
     * @param liveSonChannelInfoRequest 查询子频道信息请求体
     * @return 查询子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveSonChannelInfoResponse sonChannelInfo(LiveSonChannelInfoRequest liveSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道号下所有子频道信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-accounts/
     * @param liveSonChannelInfoListRequest 查询频道号下所有子频道信息请求体
     * @return 查询频道号下所有子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveSonChannelInfoListResponse sonChannelInfoList(LiveSonChannelInfoListRequest liveSonChannelInfoListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除子频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/delete-account/
     * @param liveDeleteSonChannelRequest 删除子频道请求体
     * @return 删除子频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String deleteSonChannel(LiveDeleteSonChannelRequest liveDeleteSonChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 创建重制课件任务
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/add-record-task/
     * @param liveCreateChannelPPTRecordRequest 创建重制课件任务请求实体
     * @return 创建重制课件任务返回实体，成功返回""
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createChannelPPTRecordTask(LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
