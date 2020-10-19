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
import net.polyv.live.entity.channel.LiveChannelVideoListRequest;
import net.polyv.live.entity.channel.LiveChannelVideoListResponse;
import net.polyv.live.entity.channel.LiveChannelVideoOnlyRequest;
import net.polyv.live.entity.channel.LiveChannelVideoOnlyResponse;
import net.polyv.live.entity.channel.LiveChannelVideoSortRequest;
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
    
    /**
     * 异步批量转存录制文件到点播
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/async-convert/
     * @param liveConvertChannelVideoListAsyncRequest 异步批量转存录制文件到点播晴天实体
     * @return 异步批量转存录制文件到点播返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String convertChannelVideoListAsync(LiveConvertChannelVideoListAsyncRequest liveConvertChannelVideoListAsyncRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道录制视频信息
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/recordfilesinfo/#fileUrl
     * @param liveChannelVideoListRequest 查询频道录制视频信息请求实体
     * @return 查询频道录制视频信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelVideoListResponse listChannelVideo(LiveChannelVideoListRequest liveChannelVideoListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 同步转存录制文件到点播
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/livetovod/
     * @param liveConvertChannelVideoRequest 同步转存录制文件到点播请求实体
     * @return 同步转存录制文件到点播返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String convertChannelVideo(LiveConvertChannelVideoRequest liveConvertChannelVideoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道回放设置
     * API地址：http://api.polyv.net/live/v3/channel/playback/set-setting
     * @param liveChannelPlaybackSettingRequest 设置频道回放设置请求实体
     * @return 设置频道回放设置返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String channelPlaybackSetting(LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置后台回放开关
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/setplaybackenabled/
     * @param liveChannelPlaybackEnabledRequest 设置后台回放开关请求实体
     * @return 设置后台回放开关返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Integer channelPlayBackEnabledSetting(LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频库列表
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/getplaybacklist/
     * @param liveListChannelVideoLibraryRequest 查询视频库列表请求实体
     * @return 查询视频库列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelVideoLibraryResponse listChannelVideoLibrary(
            LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置视频库列表排序
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/sort-playback/
     * @param liveChannelVideoSortRequest 设置视频库列表排序请求体
     * @return 设置视频库列表排序返回实体，""为成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String channelVideoSort(LiveChannelVideoSortRequest liveChannelVideoSortRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道直播场次信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/get-channel-sessions/
     * @param liveListChannelSessionInfoRequest 查询频道直播场次信息请求实体
     * @return 查询频道直播场次信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelSessionInfoResponse listChannelSessionInfo(
            LiveListChannelSessionInfoRequest liveListChannelSessionInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询指定文件ID的录制文件信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/get-record-file/
     * @param liveChannelVideoOnlyRequest 查询指定文件ID的录制文件信息请求实体
     * @return 查询指定文件ID的录制文件信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelVideoOnlyResponse channelVideoOnly(LiveChannelVideoOnlyRequest liveChannelVideoOnlyRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道的回放开关状态
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/get-playback-enbaled/
     * @param liveChannelPlaybackEnabledInfoRequest 查询频道的回放开关状态请求实体
     * @return 查询频道的回放开关状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String channelPlayBackEnabledInfo(LiveChannelPlaybackEnabledInfoRequest liveChannelPlaybackEnabledInfoRequest)
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
    
    /**
     * 删除直播暂存中的录制文件
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/delete-record/
     * @param liveDeleteChannelVideoRequest 删除直播暂存中的录制文件请求实体
     * @return 删除直播暂存中的录制文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String deleteChannelVideo(LiveDeleteChannelVideoRequest liveDeleteChannelVideoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除视频库列表中的视频
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/deleteplaybackvideo/
     * @param liveDeleteChannelPlaybackVideoRequest 删除视频库列表中的视频请求实体
     * @return 删除视频库列表中的视频返回实体，success为删除成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String deleteChannelPlaybackVideo(LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取频道一定时间范围之内的历史最高并发人数
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/gksj/get-max-history-concurrent/
     * @param liveChannelMaxHistoryConcurrentRequest 获取频道一定时间范围之内的历史最高并发人数请求实体
     * @return 获取频道一定时间范围之内的历史最高并发人数返回实体，返回并发人数，如：100
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Integer maxChannelHistoryConcurrent(LiveChannelMaxHistoryConcurrentRequest liveChannelMaxHistoryConcurrentRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 分页获取连麦情况使用详情
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/gksj/mic-detail-list/
     * @param liveListChannelMicRequest 分页获取连麦情况使用详情请求实体
     * @return 分页获取连麦情况使用详情返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelMicResponse listChannelMic(LiveListChannelMicRequest liveListChannelMicRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 分页查询频道观看日志
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/viewlog-page/
     * @param liveChannelViewlogRequest 分页查询频道观看日志请求实体
     * @return 分页查询频道观看日志返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelViewlogResponse listChannelViewlog(LiveListChannelViewlogRequest liveChannelViewlogRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询多个频道汇总的统计数据
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/channel_play_summary/
     * @param liveListChannelSummaryRequest 查询多个频道汇总的统计数据请求实体
     * @return 查询多个频道汇总的统计数据返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelSummaryResponse listChannelSummary(LiveListChannelSummaryRequest liveListChannelSummaryRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
