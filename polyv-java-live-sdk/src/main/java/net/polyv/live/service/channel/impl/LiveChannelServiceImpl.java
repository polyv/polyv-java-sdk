package net.polyv.live.service.channel.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.channel.LiveChannelAuthTokenRequest;
import net.polyv.live.entity.channel.LiveChannelAuthTokenResponse;
import net.polyv.live.entity.channel.LiveChannelBasicInfoRequest;
import net.polyv.live.entity.channel.LiveChannelBasicInfoResponse;
import net.polyv.live.entity.channel.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.LiveChannelInfoRequest;
import net.polyv.live.entity.channel.LiveChannelInfoResponse;
import net.polyv.live.entity.channel.LiveChannelInitRequest;
import net.polyv.live.entity.channel.LiveChannelInitResponse;
import net.polyv.live.entity.channel.LiveChannelMaxHistoryConcurrentRequest;
import net.polyv.live.entity.channel.LiveChannelPasswordSettingRequest;
import net.polyv.live.entity.channel.LiveChannelPlaybackEnabledInfoRequest;
import net.polyv.live.entity.channel.LiveChannelPlaybackEnabledRequest;
import net.polyv.live.entity.channel.LiveChannelPlaybackSettingRequest;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.entity.channel.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.LiveChannelStreamInfoRequest;
import net.polyv.live.entity.channel.LiveChannelStreamInfoResponse;
import net.polyv.live.entity.channel.LiveChannelStreamStatusResponse;
import net.polyv.live.entity.channel.LiveChannelVideoListRequest;
import net.polyv.live.entity.channel.LiveChannelVideoListResponse;
import net.polyv.live.entity.channel.LiveChannelVideoOnlyRequest;
import net.polyv.live.entity.channel.LiveChannelVideoOnlyResponse;
import net.polyv.live.entity.channel.LiveChannelVideoSortRequest;
import net.polyv.live.entity.channel.LiveChannelViewerConcurrenceRequest;
import net.polyv.live.entity.channel.LiveChannelViewerConcurrenceResponse;
import net.polyv.live.entity.channel.LiveConvertChannelVideoListAsyncRequest;
import net.polyv.live.entity.channel.LiveConvertChannelVideoRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.LiveCreateChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveCreateChannelTokenRequest;
import net.polyv.live.entity.channel.LiveCreateChannelVideoPlaybackRequest;
import net.polyv.live.entity.channel.LiveCreateChannelVideoPlaybackResponse;
import net.polyv.live.entity.channel.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.LiveCreateSonChannelTokenRequest;
import net.polyv.live.entity.channel.LiveCutoffChannelStreamRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelListRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelPlaybackVideoRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelRequest;
import net.polyv.live.entity.channel.LiveDeleteChannelVideoRequest;
import net.polyv.live.entity.channel.LiveDeleteSonChannelRequest;
import net.polyv.live.entity.channel.LiveListChannelMicRequest;
import net.polyv.live.entity.channel.LiveListChannelMicResponse;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.channel.LiveListChannelSessionInfoRequest;
import net.polyv.live.entity.channel.LiveListChannelSessionInfoResponse;
import net.polyv.live.entity.channel.LiveListChannelStreamStatusRequest;
import net.polyv.live.entity.channel.LiveListChannelStreamStatusResponse;
import net.polyv.live.entity.channel.LiveListChannelSummaryRequest;
import net.polyv.live.entity.channel.LiveListChannelSummaryResponse;
import net.polyv.live.entity.channel.LiveListChannelVideoLibraryRequest;
import net.polyv.live.entity.channel.LiveListChannelVideoLibraryResponse;
import net.polyv.live.entity.channel.LiveListChannelViewerCountRequest;
import net.polyv.live.entity.channel.LiveListChannelViewerCountResponse;
import net.polyv.live.entity.channel.LiveListChannelViewlogRequest;
import net.polyv.live.entity.channel.LiveListChannelViewlogResponse;
import net.polyv.live.entity.channel.LiveMergeChannelVideoAsyncRequest;
import net.polyv.live.entity.channel.LiveMergeChannelVideoRequest;
import net.polyv.live.entity.channel.LiveResumeChannelStreamRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoListRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoListResponse;
import net.polyv.live.entity.channel.LiveSonChannelInfoRequest;
import net.polyv.live.entity.channel.LiveSonChannelInfoResponse;
import net.polyv.live.entity.channel.LiveUpdateSonChannelInfoRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.channel.ILiveChannelService;
import net.polyv.live.util.MapUtil;

/**
 * 直播频道管理
 * @author: thomas
 **/
@Slf4j
public class LiveChannelServiceImpl extends LiveBaseService implements ILiveChannelService {
    
    /**
     * 创建直播频道
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/create-channel/
     * @param liveChannelRequest 直播频道请求实体
     * @return 频道数据
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.CHANNEL_CREATE_URL;
        LiveChannelResponse liveChannelResponse = this.basePost(url, liveChannelRequest, LiveChannelResponse.class);
        return liveChannelResponse;
    }
    
    /**
     * 创建并初始化频道
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/basic-create/
     * @param liveChannelInitRequest 创建并初始化频道请求体
     * @return 创建并初始化频道返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelInitResponse createChannelInit(LiveChannelInitRequest liveChannelInitRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_BASIC_CREATE_URL;
        LiveChannelInitResponse liveChannelInitResponse = this.basePostJson(url, liveChannelInitRequest,
                LiveChannelInitResponse.class);
        return liveChannelInitResponse;
    }
    
    /**
     * 批量创建频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/batch-create-channels/
     * @param liveCreateChannelListRequest 批量创建频道请求体
     * @return 批量创建频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCreateChannelListResponse createChannelList(LiveCreateChannelListRequest liveCreateChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_CREATE_URL;
        LiveCreateChannelListResponse liveCreateChannelListResponse = this.basePostJson(url,
                liveCreateChannelListRequest, LiveCreateChannelListResponse.class);
        return liveCreateChannelListResponse;
    }
    
    /**
     * 修改频道的相关设置
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/update-channel-detail-setting/
     * @param liveChannelSettingRequest 修改频道的相关设置请求体
     * @return 修改频道的相关设置返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @SneakyThrows
    @Override
    public String updateChannelSetting(LiveChannelSettingRequest liveChannelSettingRequest) throws IOException {
        String url = LiveURL.CHANNEL_BASIC_UPDATE_URL;
        Map<String, String> signMap = MapUtil.getSignMap(liveChannelSettingRequest);
        signMap.put("channelId", liveChannelSettingRequest.getChannelId() + "");
        return this.basePostJson(url, signMap, liveChannelSettingRequest, String.class);
    }
    
    /**
     * 设置频道详情
     * 注意：设置前，请确认您的套餐是否包含对应场景
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/detail-update/
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return 频道详情
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest)
            throws IOException, NoSuchAlgorithmException {
        //此处password字段与channelPasswd都表示频道密码，先做兼容
        if ("channelPasswd".equals(liveChannelDetailRequest.getField())) {
            liveChannelDetailRequest.setField("password");
        }
        String url = LiveURL.CHANNEL_DETAIL_SET_URL;
        return this.basePost(url, liveChannelDetailRequest, String.class);
    }
    
    /**
     * 查询课件重制任务列表
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/pptrecord-list/
     * @param liveListChannelPPTRecordRequest 查询课件重制任务列表请求体
     * @return 课件重制任务列表返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelPPTRecordResponse listPPTRecord(
            LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_PPTRECORD_URL;
        return this.baseGet(url, liveListChannelPPTRecordRequest, LiveListChannelPPTRecordResponse.class);
    }
    
    /**
     * 设置频道密码
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/updatepasswd/
     * @param liveChannelPasswordSettingRequest 设置频道密码请求体
     * @return 设置频道密码返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateChannelPassword(LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelPasswordSettingRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PWD_SET_URL, liveChannelPasswordSettingRequest.getUserId());
        return this.basePost(url, liveChannelPasswordSettingRequest, String.class);
    }
    
    /**
     * 删除直播频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/deletechannel/
     * @param liveDeleteChannelRequest 删除直播频道请求体
     * @return 删除直播频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String deleteChannel(LiveDeleteChannelRequest liveDeleteChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        liveDeleteChannelRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_DELETE_URL, liveDeleteChannelRequest.getChannelId());
        return this.basePost(url, liveDeleteChannelRequest, String.class);
    }
    
    /**
     * 批量删除频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/batch-delete-channels/
     * @param liveDeleteChannelListRequest 批量删除频道请求体
     * @return 批量删除频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String deleteChannelList(LiveDeleteChannelListRequest liveDeleteChannelListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIST_DELETE_URL;
        return this.basePostJson(url, liveDeleteChannelListRequest, String.class);
    }
    
    /**
     * 设置频道单点登陆token
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/set-token-1/
     * @param liveCreateChannelTokenRequest 设置频道单点登陆token请求体
     * @return 设置频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String createChannelToken(LiveCreateChannelTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_TOKEN_CREATE_URL, liveCreateChannelTokenRequest.getChannelId());
        return this.basePost(url, liveCreateChannelTokenRequest, String.class);
    }
    
    /**
     * 查询频道信息
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/pdcz/getchannelid-2/
     * @param liveChannelInfoRequest 查询频道信息请求体
     * @return 查询频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelInfoResponse channelInfo(LiveChannelInfoRequest liveChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_GET_URL, liveChannelInfoRequest.getChannelId());
        LiveChannelInfoResponse liveChannelInfoResponse = this.baseGet(url, liveChannelInfoRequest,
                LiveChannelInfoResponse.class);
        return liveChannelInfoResponse;
    }
    
    /**
     * 查询频道基本信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/get-detail-setting/
     * @param liveChannelBasicInfoRequest 查询频道基本信息请求体
     * @return 查询频道基本信息返回提
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelBasicInfoResponse channelBasicInfo(LiveChannelBasicInfoRequest liveChannelBasicInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_BASIC_INFO_URL;
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = this.basePost(url, liveChannelBasicInfoRequest,
                LiveChannelBasicInfoResponse.class);
        return liveChannelBasicInfoResponse;
    }
    
    /**
     * 查询授权和连麦的token
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/get-chat-token/
     * @param liveCreateChannelTokenRequest 查询授权和连麦的token请求体
     * @return 查询授权和连麦的token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelAuthTokenResponse channelAuthToken(LiveChannelAuthTokenRequest liveCreateChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_AUTH_TOKEN_URL;
        liveCreateChannelTokenRequest.setUserId(LiveGlobalConfig.USER_ID);
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse = this.basePost(url, liveCreateChannelTokenRequest,
                LiveChannelAuthTokenResponse.class);
        return liveChannelAuthTokenResponse;
    }
    
    /**
     * 创建子频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/add-account/
     * @param liveCreateSonChannelRequest 创建子频道请求体
     * @return 创建子频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCreateSonChannelResponse createSonChannel(LiveCreateSonChannelRequest liveCreateSonChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_CREATE_URL, liveCreateSonChannelRequest.getChannelId());
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = this.basePost(url, liveCreateSonChannelRequest,
                LiveCreateSonChannelResponse.class);
        return liveCreateSonChannelResponse;
    }
    
    /**
     * 设置子频道信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/update-account/
     * @param liveUpdateSonChannelInfoRequest 设置子频道信息请求体
     * @return 设置子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateSonChannelInfo(LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_INFO_UPDATE_URL,
                liveUpdateSonChannelInfoRequest.getChannelId());
        return this.basePost(url, liveUpdateSonChannelInfoRequest, String.class);
    }
    
    /**
     * 设置子频道单点登陆token
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/set-account-token/
     * @param liveCreateSonChannelTokenRequest 设置子频道单点登陆token请求体
     * @return 设置子频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String createSonChannelToken(LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_TOKEN_CREATE_URL,
                liveCreateSonChannelTokenRequest.getAccount());
        String liveCreateSonChannelTokenResponse = this.basePost(url, liveCreateSonChannelTokenRequest, String.class);
        return liveCreateSonChannelTokenResponse;
    }
    
    /**
     * 查询子频道信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-account/
     * @param liveSonChannelInfoRequest 查询子频道信息请求体
     * @return 查询子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveSonChannelInfoResponse sonChannelInfo(LiveSonChannelInfoRequest liveSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_INFO_GET_URL, liveSonChannelInfoRequest.getChannelId());
        LiveSonChannelInfoResponse liveSonChannelInfoResponse = this.baseGet(url, liveSonChannelInfoRequest,
                LiveSonChannelInfoResponse.class);
        return liveSonChannelInfoResponse;
    }
    
    /**
     * 查询频道号下所有子频道信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-accounts/
     * @param liveSonChannelInfoListRequest 查询频道号下所有子频道信息请求体
     * @return 查询频道号下所有子频道信息返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveSonChannelInfoListResponse sonChannelInfoList(
            LiveSonChannelInfoListRequest liveSonChannelInfoListRequest) throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_ACCOUNTS_GET_URL, liveSonChannelInfoListRequest.getChannelId());
        LiveSonChannelInfoResponse[] liveSonChannelInfoResponses = this.baseGet(url, liveSonChannelInfoListRequest,
                LiveSonChannelInfoResponse[].class);
        if (liveSonChannelInfoResponses == null) {
            liveSonChannelInfoResponses = new LiveSonChannelInfoResponse[]{};
        }
        LiveSonChannelInfoListResponse liveSonChannelInfoListResponse = new LiveSonChannelInfoListResponse();
        liveSonChannelInfoListResponse.setSonChannelInfos(Arrays.asList(liveSonChannelInfoResponses));
        return liveSonChannelInfoListResponse;
    }
    
    /**
     * 删除子频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/delete-account/
     * @param liveDeleteSonChannelRequest 删除子频道请求体
     * @return 删除子频道返回体 true为删除成功，空字符串为删除失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String deleteSonChannel(LiveDeleteSonChannelRequest liveDeleteSonChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SON_CHANNEL_DELETE_URL, liveDeleteSonChannelRequest.getChannelId());
        String liveDeleteSonChannelResponse = this.basePost(url, liveDeleteSonChannelRequest, String.class);
        return liveDeleteSonChannelResponse;
    }
    
    /**
     * 恢复直播频道推流
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/resume/
     * @param liveResumeChannelStreamRequest 恢复直播频道推流请求体
     * @return 恢复直播频道推流返回体，success为成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String resumeChannelStream(LiveResumeChannelStreamRequest liveResumeChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException {
        liveResumeChannelStreamRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_STREAM_RESUME_URL,
                liveResumeChannelStreamRequest.getChannelId());
        String liveResumeChannelStreamResponse = this.basePost(url, liveResumeChannelStreamRequest, String.class);
        return liveResumeChannelStreamResponse;
    }
    
    /**
     * 禁止直播频道推流
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/hqzbxx/banpush/
     * @param liveCutoffChannelStreamRequest 禁止直播频道推流请求实体
     * @return 禁止直播频道推流返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String cutoffChannelStream(LiveCutoffChannelStreamRequest liveCutoffChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException {
        liveCutoffChannelStreamRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_STREAM_CUTOFF_URL,
                liveCutoffChannelStreamRequest.getChannelId());
        return this.basePost(url, liveCutoffChannelStreamRequest, String.class);
    }
    
    /**
     * 批量查询频道直播流状态
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/hqzbxx/live-status/
     * @param liveListChannelStreamStatusRequest 批量查询频道直播流状态请求实体
     * @return 批量查询频道直播流状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelStreamStatusResponse listChannelLiveStream(
            LiveListChannelStreamStatusRequest liveListChannelStreamStatusRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIVE_STREAM_STATUS_LIST_URL;
        LiveChannelStreamStatusResponse[] liveChannelStreamStatusResponses = this.basePost(url,
                liveListChannelStreamStatusRequest, LiveChannelStreamStatusResponse[].class);
        if (liveChannelStreamStatusResponses == null) {
            liveChannelStreamStatusResponses = new LiveChannelStreamStatusResponse[]{};
        }
        LiveListChannelStreamStatusResponse liveListChannelStreamStatusResponse =
                new LiveListChannelStreamStatusResponse();
        liveListChannelStreamStatusResponse.setChannelInfo(Arrays.asList(liveChannelStreamStatusResponses));
        return liveListChannelStreamStatusResponse;
    }
    
    /**
     * 查询频道实时推流信息(讲师未进入直播间或未开启上课等情况，将抛出"channel status not live"异常)，
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/hqzbxx/get-stream-info/
     * @param liveChannelStreamInfoRequest 查询频道实时推流信息请求实体
     * @return 查询频道实时推流信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelStreamInfoResponse channelStreamInfo(LiveChannelStreamInfoRequest liveChannelStreamInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LIVE_STREAM_INFO_URL;
        LiveChannelStreamInfoResponse liveChannelStreamInfoResponse = this.baseGet(url, liveChannelStreamInfoRequest,
                LiveChannelStreamInfoResponse.class);
        return liveChannelStreamInfoResponse;
    }
    
    /**
     * 将点播中的视频添加到视频库
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/add-vod-playback/
     * @param liveCreateChannelVideoPlaybackRequest 将点播中的视频添加到视频库请求实体
     * @return 将点播中的视频添加到视频库返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCreateChannelVideoPlaybackResponse addChannelVideoPlayback(
            LiveCreateChannelVideoPlaybackRequest liveCreateChannelVideoPlaybackRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_VIDEO_PLAYBACK_ADD_URL;
        LiveCreateChannelVideoPlaybackResponse liveCreateChannelVideoPlaybackResponse = this.basePost(url,
                liveCreateChannelVideoPlaybackRequest, LiveCreateChannelVideoPlaybackResponse.class);
        return liveCreateChannelVideoPlaybackResponse;
    }
    
    /**
     * 合并录制文件(由于同步上传影响性能，暂时不对外提供)
     * 注意：urls 和 fileIds 参数不能同时不传；两个参数都传时，urls生效。
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/recordfile-merge/
     * @param liveMergeChannelVideoRequest 合并录制文件请求实体
     * @return 合并录制文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String mergeChannelVideo(LiveMergeChannelVideoRequest liveMergeChannelVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_RECORD_FILE_MERGE_URL,
                liveMergeChannelVideoRequest.getChannelId());
        String liveMergeChannelVideoResponse = this.basePost(url, liveMergeChannelVideoRequest, String.class);
        return liveMergeChannelVideoResponse;
    }
    
    /**
     * 异步合并直播录制文件
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/async-merge/
     * @param liveMergeChannelVideoAsyncRequest 异步合并直播录制文件请求实体
     * @return 异步合并直播录制文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String mergeChannelVideoAsync(LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_RECORD_FILE_MERGE_ASYNC_URL;
        String liveMergeChannelVideoAsyncResponse = this.basePost(url, liveMergeChannelVideoAsyncRequest, String.class);
        return liveMergeChannelVideoAsyncResponse;
    }
    
    /**
     * 异步批量转存录制文件到点播
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/async-convert/
     * @param liveConvertChannelVideoListAsyncRequest 异步批量转存录制文件到点播晴天实体
     * @return 异步批量转存录制文件到点播返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String convertChannelVideoListAsync(
            LiveConvertChannelVideoListAsyncRequest liveConvertChannelVideoListAsyncRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_RECORD_CONVERT_URL;
        String liveConvertChannelVideoResponse = this.basePost(url, liveConvertChannelVideoListAsyncRequest,
                String.class);
        return liveConvertChannelVideoResponse;
    }
    
    /**
     * 查询频道录制视频信息
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/recordfilesinfo/#fileUrl
     * @param liveChannelVideoListRequest 查询频道录制视频信息请求实体
     * @return 查询频道录制视频信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelVideoListResponse listChannelVideo(LiveChannelVideoListRequest liveChannelVideoListRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelVideoListRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_RECORD_FILES_URL, liveChannelVideoListRequest.getChannelId());
        LiveChannelVideoListResponse.ChannelVedioInfo[] channelVedioInfos = this.baseGet(url,
                liveChannelVideoListRequest, LiveChannelVideoListResponse.ChannelVedioInfo[].class);
        channelVedioInfos =
                channelVedioInfos == null ? new LiveChannelVideoListResponse.ChannelVedioInfo[]{} : channelVedioInfos;
        LiveChannelVideoListResponse liveChannelVideoListResponse = new LiveChannelVideoListResponse();
        liveChannelVideoListResponse.setChannelVedioInfos(Arrays.asList(channelVedioInfos));
        return liveChannelVideoListResponse;
    }
    
    /**
     * 同步转存录制文件到点播，已经转存的视频再次调用会提示record already convert
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/livetovod/
     * @param liveConvertChannelVideoRequest 同步转存录制文件到点播请求实体
     * @return 同步转存录制文件到点播返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String convertChannelVideo(LiveConvertChannelVideoRequest liveConvertChannelVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        liveConvertChannelVideoRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_RECORD_FILE_CONVERT_URL,
                liveConvertChannelVideoRequest.getChannelId());
        String liveConvertChannelVideoResponse = this.basePost(url, liveConvertChannelVideoRequest, String.class);
        return liveConvertChannelVideoResponse;
    }
    
    /**
     * 设置频道回放设置
     * API地址：http://api.polyv.net/live/v3/channel/playback/set-setting
     * @param liveChannelPlaybackSettingRequest 设置频道回放设置请求实体
     * @return 设置频道回放设置返回实体，true为成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String channelPlaybackSetting(LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_PLAYBACK_SETTING_URL;
        String liveChannelPlaybackSettingResponse = this.basePost(url, liveChannelPlaybackSettingRequest, String.class);
        return liveChannelPlaybackSettingResponse;
    }
    
    /**
     * 设置后台回放开关
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/setplaybackenabled/
     * @param liveChannelPlaybackEnabledRequest 设置后台回放开关请求实体
     * @return 设置后台回放开关返回实体, 成功返回频道id
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Integer channelPlayBackEnabledSetting(LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelPlaybackEnabledRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PLAYBACK_SET_URL,
                liveChannelPlaybackEnabledRequest.getUserId());
        return this.basePost(url, liveChannelPlaybackEnabledRequest, Integer.class);
    }
    
    /**
     * 查询视频库列表
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/getplaybacklist/
     * @param liveListChannelVideoLibraryRequest 查询视频库列表请求实体
     * @return 查询视频库列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelVideoLibraryResponse listChannelVideoLibrary(
            LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PLAYBACK_LIST_URL,
                liveListChannelVideoLibraryRequest.getChannelId());
        LiveListChannelVideoLibraryResponse liveListChannelVideoLibraryResponse = this.baseGet(url,
                liveListChannelVideoLibraryRequest, LiveListChannelVideoLibraryResponse.class);
        return liveListChannelVideoLibraryResponse;
    }
    
    /**
     * 设置视频库列表排序
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/sort-playback/
     * @param liveChannelVideoSortRequest 设置视频库列表排序请求体
     * @return 设置视频库列表排序返回实体，""为成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String channelVideoSort(LiveChannelVideoSortRequest liveChannelVideoSortRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_VIDEO_SORT_URL;
        Map<String, String> signMap = MapUtil.getSignMap(liveChannelVideoSortRequest);
        signMap.put("channelId", liveChannelVideoSortRequest.getChannelId().toString());
        String liveListChannelVideoLibraryResponse = this.basePostJson(url, signMap, liveChannelVideoSortRequest,
                String.class);
        return liveListChannelVideoLibraryResponse;
    }
    
    /**
     * 查询频道直播场次信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/get-channel-sessions/
     * @param liveListChannelSessionInfoRequest 查询频道直播场次信息请求实体
     * @return 查询频道直播场次信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelSessionInfoResponse listChannelSessionInfo(
            LiveListChannelSessionInfoRequest liveListChannelSessionInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_SESSION_INFO_LIST_URL;
        LiveListChannelSessionInfoResponse liveListChannelSessionInfoResponse = this.baseGet(url,
                liveListChannelSessionInfoRequest, LiveListChannelSessionInfoResponse.class);
        return liveListChannelSessionInfoResponse;
    }
    
    /**
     * 查询指定文件ID的录制文件信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/get-record-file/
     * @param liveChannelVideoOnlyRequest 查询指定文件ID的录制文件信息请求实体
     * @return 查询指定文件ID的录制文件信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelVideoOnlyResponse channelVideoOnly(LiveChannelVideoOnlyRequest liveChannelVideoOnlyRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_RECORD_GET_URL;
        LiveChannelVideoOnlyResponse liveChannelVideoOnlyResponse = this.baseGet(url, liveChannelVideoOnlyRequest,
                LiveChannelVideoOnlyResponse.class);
        return liveChannelVideoOnlyResponse;
    }
    
    /**
     * 查询频道的回放开关状态
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/get-playback-enbaled/
     * @param liveChannelPlaybackEnabledInfoRequest 查询频道的回放开关状态请求实体
     * @return 查询频道的回放开关状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String channelPlayBackEnabledInfo(
            LiveChannelPlaybackEnabledInfoRequest liveChannelPlaybackEnabledInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_PLAYBACK_ENABLED_INFO_URL;
        String liveChannelPlaybackEnabledInfoResponse = this.baseGet(url, liveChannelPlaybackEnabledInfoRequest,
                String.class);
        return liveChannelPlaybackEnabledInfoResponse;
    }
    
    /**
     * 创建重制课件任务
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/add-record-task/
     * @param liveCreateChannelPPTRecordRequest 创建重制课件任务请求实体
     * @return 创建重制课件任务返回实体，成功返回""
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String createChannelPPTRecordTask(LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_PPTRECORD_CREATE__URL;
        String liveCreateChannelPPTRecordResponse = this.basePost(url, liveCreateChannelPPTRecordRequest, String.class);
        return liveCreateChannelPPTRecordResponse;
    }
    
    /**
     * 删除直播暂存中的录制文件,sessionId和startTime不能同时为空，可单独提交某一参数。
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/delete-record/
     * @param liveDeleteChannelVideoRequest 删除直播暂存中的录制文件请求实体
     * @return 删除直播暂存中的录制文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String deleteChannelVideo(LiveDeleteChannelVideoRequest liveDeleteChannelVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_VIDEO_DELETE_URL, liveDeleteChannelVideoRequest.getChannelId());
        String liveDeleteChannelVideoResponse = this.basePost(url, liveDeleteChannelVideoRequest, String.class);
        return liveDeleteChannelVideoResponse;
    }
    
    /**
     * 删除视频库列表中的视频
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/deleteplaybackvideo/
     * @param liveDeleteChannelPlaybackVideoRequest 删除视频库列表中的视频请求实体
     * @return 删除视频库列表中的视频返回实体，success为删除成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String deleteChannelPlaybackVideo(
            LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PLAYBACK_DELETE_URL,
                liveDeleteChannelPlaybackVideoRequest.getChannelId());
        String liveDeleteChannelPlaybackVideoResponse = this.basePost(url, liveDeleteChannelPlaybackVideoRequest,
                String.class);
        return liveDeleteChannelPlaybackVideoResponse;
    }
    
    /**
     * 获取频道一定时间范围之内的历史最高并发人数,粒度可以支持到分钟
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/gksj/get-max-history-concurrent/
     * @param liveChannelMaxHistoryConcurrentRequest 获取频道一定时间范围之内的历史最高并发人数请求实体
     * @return 获取频道一定时间范围之内的历史最高并发人数返回实体，返回并发人数，如：100
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Integer maxChannelHistoryConcurrent(
            LiveChannelMaxHistoryConcurrentRequest liveChannelMaxHistoryConcurrentRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_MAX_HISTORY_CONCURRENT_URL;
        Integer liveChannelMaxHistoryConcurrentResponse = (Integer) this.baseGet(url,
                liveChannelMaxHistoryConcurrentRequest, Integer.class);
        return liveChannelMaxHistoryConcurrentResponse;
    }
    
    /**
     * 分页获取连麦情况使用详情
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/gksj/mic-detail-list/
     * @param liveListChannelMicRequest 分页获取连麦情况使用详情请求实体
     * @return 分页获取连麦情况使用详情返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelMicResponse listChannelMic(LiveListChannelMicRequest liveListChannelMicRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_MIC_LIST_URL;
        LiveListChannelMicResponse liveListChannelMicResponse = this.baseGet(url, liveListChannelMicRequest,
                LiveListChannelMicResponse.class);
        return liveListChannelMicResponse;
    }
    
    /**
     * 分页查询频道观看日志
     * 1. 如果查询一段时间的记录，可以传：startTime、endTime （startTime和endTime 必须在同一个月），如果查询某天的记录，则传currentDay；
     * 2. startTime、endTime 和 currentDay不能都不传；
     * 3. currentDay与startTime、endTime 同时传将使用currentDay的值。
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/viewlog-page/
     * @param liveChannelViewlogRequest 分页查询频道观看日志请求实体
     * @return 分页查询频道观看日志返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelViewlogResponse listChannelViewlog(LiveListChannelViewlogRequest liveChannelViewlogRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_VIEW_LOGS_GET_URL, liveChannelViewlogRequest.getChannelId());
        LiveListChannelViewlogResponse liveListChannelViewlogResponse = this.baseGet(url, liveChannelViewlogRequest,
                LiveListChannelViewlogResponse.class);
        return liveListChannelViewlogResponse;
    }
    
    /**
     * 查询多个频道汇总的统计数据
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/channel_play_summary/
     * @param liveListChannelSummaryRequest 查询多个频道汇总的统计数据请求实体
     * @return 查询多个频道汇总的统计数据返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelSummaryResponse listChannelSummary(
            LiveListChannelSummaryRequest liveListChannelSummaryRequest) throws IOException, NoSuchAlgorithmException {
        liveListChannelSummaryRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_SUMMARY_LIST_GET_URL,
                liveListChannelSummaryRequest.getUserId());
        LiveListChannelSummaryResponse.ChannelSummary[] channelSummaries = this.basePost(url,
                liveListChannelSummaryRequest, LiveListChannelSummaryResponse.ChannelSummary[].class);
        channelSummaries =
                channelSummaries == null ? new LiveListChannelSummaryResponse.ChannelSummary[]{} : channelSummaries;
        LiveListChannelSummaryResponse liveListChannelSummaryResponse = new LiveListChannelSummaryResponse();
        liveListChannelSummaryResponse.setChannelSummarys(Arrays.asList(channelSummaries));
        return liveListChannelSummaryResponse;
    }
    
    /**
     * 查询多个频道的实时在线人数
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/get-realtime-viewers/
     * @param liveListChannelViewerCountRequest 查询多个频道的实时在线人数请求实体
     * @return 查询多个频道的实时在线人数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelViewerCountResponse listChannelViewerCount(
            LiveListChannelViewerCountRequest liveListChannelViewerCountRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_REAL_TIME_VIEWERS_GET_URL;
        LiveListChannelViewerCountResponse.ChannelViewerCount[] channelViewerCounts = this.baseGet(url,
                liveListChannelViewerCountRequest, LiveListChannelViewerCountResponse.ChannelViewerCount[].class);
        channelViewerCounts = channelViewerCounts == null ?
                channelViewerCounts = new LiveListChannelViewerCountResponse.ChannelViewerCount[]{} :
                channelViewerCounts;
        LiveListChannelViewerCountResponse liveListChannelViewerCountResponse =
                new LiveListChannelViewerCountResponse();
        liveListChannelViewerCountResponse.setChannelViewerCounts(Arrays.asList(channelViewerCounts));
        return liveListChannelViewerCountResponse;
    }
    
    /**
     * 查询频道的历史并发人数
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/gksj/concurrency/
     * @param liveChannelViewerConcurrenceRequest 查询频道的历史并发人数请求实体
     * @return 查询频道的历史并发人数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelViewerConcurrenceResponse channelViewerConcurrence(
            LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_VIEWER_CONCURRENCE_URL;
        LiveChannelViewerConcurrenceResponse.ChannelViewerConcurrence[] channelViewerConcurrences = this.baseGet(url,
                liveChannelViewerConcurrenceRequest,
                LiveChannelViewerConcurrenceResponse.ChannelViewerConcurrence[].class);
        channelViewerConcurrences = channelViewerConcurrences == null ?
                new LiveChannelViewerConcurrenceResponse.ChannelViewerConcurrence[]{} : channelViewerConcurrences;
        LiveChannelViewerConcurrenceResponse liveChannelViewerConcurrenceResponse =
                new LiveChannelViewerConcurrenceResponse();
        liveChannelViewerConcurrenceResponse.setChannelViewerConcurrences(Arrays.asList(channelViewerConcurrences));
        return liveChannelViewerConcurrenceResponse;
    }
    
}
