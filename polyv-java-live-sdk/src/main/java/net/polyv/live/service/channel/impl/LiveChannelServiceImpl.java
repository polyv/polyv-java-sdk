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
import net.polyv.live.entity.channel.LiveChannelPasswordSettingRequest;
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
import net.polyv.live.entity.channel.LiveConvertChannelVideoListAsyncRequest;
import net.polyv.live.entity.channel.LiveConvertChannelVideoRequest;
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
     * 直播频道创建
     * @param liveChannelRequest 直播频道请求实体
     * @return 频道数据
     * @throws IOException 客户端和服务器读写异常
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
     * @param liveChannelInitRequest 请求体
     * @return 响应体
     * @throws IOException 异常
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
     * @param liveCreateChannelListRequest 批量创建频道请求体
     * @return 批量创建频道返回体
     * @throws IOException 异常
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
     * @param liveChannelSettingRequest 修改频道的相关设置请求体
     * @return 修改频道的相关设置返回体
     * @throws IOException 异常
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
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return 频道详情
     * @throws IOException 异常
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
     * @param liveListChannelPPTRecordRequest 查询课件重制任务列表请求体
     * @return 课件重制任务列表返回体
     * @throws IOException 异常
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
     * @return 设置后台回放开关返回实体,成功返回频道id
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Integer channelPlayBackEnabledSetting(LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelPlaybackEnabledRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PLAYBACK_SET_URL,liveChannelPlaybackEnabledRequest.getUserId());
        return this.basePost(url,liveChannelPlaybackEnabledRequest,Integer.class);
    }
    
}
