package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.channel.playback.LiveChannelDefaultVideoRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackEnabledInfoRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackEnabledRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackSettingRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoSortRequest;
import net.polyv.live.entity.channel.playback.LiveConvertChannelVideoListAsyncRequest;
import net.polyv.live.entity.channel.playback.LiveConvertChannelVideoRequest;
import net.polyv.live.entity.channel.playback.LiveCreateChannelVideoPlaybackRequest;
import net.polyv.live.entity.channel.playback.LiveCreateChannelVideoPlaybackResponse;
import net.polyv.live.entity.channel.playback.LiveDeleteChannelPlaybackVideoRequest;
import net.polyv.live.entity.channel.playback.LiveDeleteChannelVideoRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelSessionInfoRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelSessionInfoResponse;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryResponse;
import net.polyv.live.entity.channel.playback.LiveMergeChannelVideoAsyncRequest;
import net.polyv.live.entity.channel.playback.LiveMergeChannelVideoRequest;
import net.polyv.live.entity.channel.playback.LiveMergeMp4RecordRequest;
import net.polyv.live.entity.channel.playback.LiveMergeMp4RecordResponse;
import net.polyv.live.entity.channel.playback.LiveUpdatePlaybackTitleRequest;

/**
 * 直播录制回放接口
 * @author: sadboy
 **/
public interface ILiveChannelPlaybackService {
    
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
    Boolean mergeChannelVideoAsync(LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 异步批量转存录制文件到点播
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/async-convert/
     * @param liveConvertChannelVideoListAsyncRequest 异步批量转存录制文件到点播晴天实体
     * @return 异步批量转存录制文件到点播返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean convertChannelVideoListAsync(
            LiveConvertChannelVideoListAsyncRequest liveConvertChannelVideoListAsyncRequest)
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
    Boolean channelPlaybackSetting(LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置后台回放开关
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/setplaybackenabled/
     * @param liveChannelPlaybackEnabledRequest 设置后台回放开关请求实体
     * @return 设置后台回放开关返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String channelPlayBackEnabledSetting(LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest)
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
    Boolean channelVideoSort(LiveChannelVideoSortRequest liveChannelVideoSortRequest)
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
     * 删除直播暂存中的录制文件
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/lzhf/delete-record/
     * @param liveDeleteChannelVideoRequest 删除直播暂存中的录制文件请求实体
     * @return 删除直播暂存中的录制文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteChannelVideo(LiveDeleteChannelVideoRequest liveDeleteChannelVideoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除视频库列表中的视频
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/deleteplaybackvideo/
     * @param liveDeleteChannelPlaybackVideoRequest 删除视频库列表中的视频请求实体
     * @return 删除视频库列表中的视频返回实体，success为删除成功
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteChannelPlaybackVideo(LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置视频库列表的默认视频
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/setdefault/
     * @param liveChannelDefaultVideoRequest 设置视频库列表的默认视频请求实体
     * @return 设置视频库列表的默认视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean channelDefaultVideo(LiveChannelDefaultVideoRequest liveChannelDefaultVideoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改回放视频名称
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/lzhf/update-playback-title/
     * @param liveUpdatePlaybackTitleRequest 修改回放视频名称请求实体
     * @return 修改回放视频名称返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updatePlaybackTitle(LiveUpdatePlaybackTitleRequest liveUpdatePlaybackTitleRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 导出合并的录制文件并回调mp4下载地址
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/lzhf/merge-record-mp4/
     * @param liveMergeMp4RecordRequest 导出合并的录制文件并回调mp4下载地址请求实体
     * @return 导出合并的录制文件并回调mp4下载地址返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveMergeMp4RecordResponse mergeMp4Record(LiveMergeMp4RecordRequest liveMergeMp4RecordRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
