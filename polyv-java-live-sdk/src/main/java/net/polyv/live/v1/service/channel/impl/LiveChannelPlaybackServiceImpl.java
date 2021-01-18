package net.polyv.live.v1.service.channel.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import net.polyv.live.v1.config.LiveGlobalConfig;
import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.channel.playback.LiveChannelDefaultVideoRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelPlaybackEnabledInfoRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelPlaybackEnabledRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelPlaybackSettingRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelVideoListRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelVideoListResponse;
import net.polyv.live.v1.entity.channel.playback.LiveChannelVideoOnlyRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelVideoOnlyResponse;
import net.polyv.live.v1.entity.channel.playback.LiveChannelVideoSortRequest;
import net.polyv.live.v1.entity.channel.playback.LiveConvertChannelVideoListAsyncRequest;
import net.polyv.live.v1.entity.channel.playback.LiveConvertChannelVideoRequest;
import net.polyv.live.v1.entity.channel.playback.LiveCreateChannelVideoPlaybackRequest;
import net.polyv.live.v1.entity.channel.playback.LiveCreateChannelVideoPlaybackResponse;
import net.polyv.live.v1.entity.channel.playback.LiveDeleteChannelPlaybackVideoRequest;
import net.polyv.live.v1.entity.channel.playback.LiveDeleteChannelVideoRequest;
import net.polyv.live.v1.entity.channel.playback.LiveListChannelSessionInfoRequest;
import net.polyv.live.v1.entity.channel.playback.LiveListChannelSessionInfoResponse;
import net.polyv.live.v1.entity.channel.playback.LiveListChannelVideoLibraryRequest;
import net.polyv.live.v1.entity.channel.playback.LiveListChannelVideoLibraryResponse;
import net.polyv.live.v1.entity.channel.playback.LiveMergeChannelVideoAsyncRequest;
import net.polyv.live.v1.entity.channel.playback.LiveMergeChannelVideoRequest;
import net.polyv.live.v1.entity.channel.playback.LiveMergeMp4RecordRequest;
import net.polyv.live.v1.entity.channel.playback.LiveMergeMp4RecordResponse;
import net.polyv.live.v1.entity.channel.playback.LiveUpdatePlaybackTitleRequest;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.channel.ILiveChannelPlaybackService;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 直播录制回放实现类
 * @author: sadboy
 **/
public class LiveChannelPlaybackServiceImpl extends LiveBaseService implements ILiveChannelPlaybackService {
    
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
    public Boolean mergeChannelVideoAsync(LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_RECORD_FILE_MERGE_ASYNC_URL;
        String liveMergeChannelVideoAsyncResponse = this.basePost(url, liveMergeChannelVideoAsyncRequest, String.class);
        return "submit success".equals(liveMergeChannelVideoAsyncResponse);
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
    public Boolean convertChannelVideoListAsync(
            LiveConvertChannelVideoListAsyncRequest liveConvertChannelVideoListAsyncRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_RECORD_CONVERT_URL;
        String liveConvertChannelVideoResponse = this.basePost(url, liveConvertChannelVideoListAsyncRequest,
                String.class);
        return "submit success".equals(liveConvertChannelVideoResponse);
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
        liveChannelVideoListRequest.setUserId(LiveGlobalConfig.getUserId());
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
    @Deprecated
    public String convertChannelVideo(LiveConvertChannelVideoRequest liveConvertChannelVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        liveConvertChannelVideoRequest.setUserId(LiveGlobalConfig.getUserId());
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
    public Boolean updateChannelPlaybackSetting(LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_PLAYBACK_SETTING_URL;
        String liveChannelPlaybackSettingResponse = this.basePost(url, liveChannelPlaybackSettingRequest, String.class);
        return "true".equals(liveChannelPlaybackSettingResponse);
    }
    
    /**
     * 设置后台回放开关
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/setplaybackenabled/
     * @param liveChannelPlaybackEnabledRequest 设置后台回放开关请求实体
     * @return 设置后台回放开关返回实体, 成功返回频道号
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateChannelPlayBackEnabledSetting(LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelPlaybackEnabledRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PLAYBACK_SET_URL,
                liveChannelPlaybackEnabledRequest.getUserId());
        Integer liveChannelPlaybackEnabledResponse = this.basePost(url, liveChannelPlaybackEnabledRequest,
                Integer.class);
        return String.valueOf(liveChannelPlaybackEnabledResponse);
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
    public Boolean setChannelVideoSort(LiveChannelVideoSortRequest liveChannelVideoSortRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_VIDEO_SORT_URL;
        Map<String, String> signMap = LiveSignUtil.getSignMap(liveChannelVideoSortRequest);
        signMap.put("channelId", String.valueOf(liveChannelVideoSortRequest.getChannelId()));
        String liveChannelVideoSortResponse = this.basePostJson(url, signMap, liveChannelVideoSortRequest,
                String.class);
        return "success".equals(liveChannelVideoSortResponse);
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
    public LiveChannelVideoOnlyResponse getChannelVideoOnly(LiveChannelVideoOnlyRequest liveChannelVideoOnlyRequest)
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
    public String getChannelPlayBackEnabledInfo(
            LiveChannelPlaybackEnabledInfoRequest liveChannelPlaybackEnabledInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_PLAYBACK_ENABLED_INFO_URL;
        String liveChannelPlaybackEnabledInfoResponse = this.baseGet(url, liveChannelPlaybackEnabledInfoRequest,
                String.class);
        return liveChannelPlaybackEnabledInfoResponse;
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
    public Boolean deleteChannelVideo(LiveDeleteChannelVideoRequest liveDeleteChannelVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_VIDEO_DELETE_URL, liveDeleteChannelVideoRequest.getChannelId());
        String liveDeleteChannelVideoResponse = this.basePost(url, liveDeleteChannelVideoRequest, String.class);
        return "success".equals(liveDeleteChannelVideoResponse);
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
    public Boolean deleteChannelPlaybackVideo(
            LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PLAYBACK_DELETE_URL,
                liveDeleteChannelPlaybackVideoRequest.getChannelId());
        String liveDeleteChannelPlaybackVideoResponse = this.basePost(url, liveDeleteChannelPlaybackVideoRequest,
                String.class);
        return "success".equals(liveDeleteChannelPlaybackVideoResponse);
    }
    
    /**
     * 设置视频库列表的默认视频
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/setdefault/
     * @param liveChannelDefaultVideoRequest 设置视频库列表的默认视频请求实体
     * @return 设置视频库列表的默认视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setChannelDefaultVideo(LiveChannelDefaultVideoRequest liveChannelDefaultVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_PLAYBACK_SET_DEFAULT_URL,
                liveChannelDefaultVideoRequest.getChannelId());
        String liveChannelDefaultVideoResponse = this.basePost(url, liveChannelDefaultVideoRequest, String.class);
        return "success".equals(liveChannelDefaultVideoResponse);
    }
    
    /**
     * 修改回放视频名称
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/lzhf/update-playback-title/
     * @param liveUpdatePlaybackTitleRequest 修改回放视频名称请求实体
     * @return 修改回放视频名称返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updatePlaybackTitle(LiveUpdatePlaybackTitleRequest liveUpdatePlaybackTitleRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_PLAYBACK_TITLE_URL;
        String liveUpdatePlaybackTitleResponse = this.basePost(url, liveUpdatePlaybackTitleRequest, String.class);
        return "success".equals(liveUpdatePlaybackTitleResponse);
    }
    
    /**
     * 导出合并的录制文件并回调mp4下载地址
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/merge-record-mp4/
     * @param liveMergeMp4RecordRequest 导出合并的录制文件并回调mp4下载地址请求实体
     * @return 导出合并的录制文件并回调mp4下载地址返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveMergeMp4RecordResponse mergeMp4Record(LiveMergeMp4RecordRequest liveMergeMp4RecordRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_RECORD_MERGE_MP4_URL;
        return this.basePost(url,liveMergeMp4RecordRequest,LiveMergeMp4RecordResponse.class);
    }
    
}
