package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.channel.operate.LiveChannelAdvertListRequest;
import net.polyv.live.entity.channel.operate.LiveChannelAdvertListResponse;
import net.polyv.live.entity.channel.operate.LiveChannelAuthTokenRequest;
import net.polyv.live.entity.channel.operate.LiveChannelAuthTokenResponse;
import net.polyv.live.entity.channel.operate.LiveChannelBasicInfoRequest;
import net.polyv.live.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.entity.channel.operate.LiveChannelCallbackSettingRequest;
import net.polyv.live.entity.channel.operate.LiveChannelCallbackSettingResponse;
import net.polyv.live.entity.channel.operate.LiveChannelCaptureRequest;
import net.polyv.live.entity.channel.operate.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInfoRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInfoResponse;
import net.polyv.live.entity.channel.operate.LiveChannelInitRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInitResponse;
import net.polyv.live.entity.channel.operate.LiveChannelPasswordSettingRequest;
import net.polyv.live.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.entity.channel.operate.LiveChannelTransmitListRequest;
import net.polyv.live.entity.channel.operate.LiveChannelTransmitListResponse;
import net.polyv.live.entity.channel.operate.LiveCreateChannelListRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelListResponse;
import net.polyv.live.entity.channel.operate.LiveCreateChannelPPTRecordRequest;
import net.polyv.live.entity.channel.operate.LiveCreateChannelTokenRequest;
import net.polyv.live.entity.channel.operate.LiveCreateDiskVideosStreamRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelListRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelListResponse;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelTokenRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteChannelListRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteChannelRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteDiskVideosStreamRequest;
import net.polyv.live.entity.channel.operate.LiveDeleteSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.operate.LiveListChannelPPTRecordResponse;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListResponse;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoResponse;
import net.polyv.live.entity.channel.operate.LiveUpdateChannelCallbackSettingRequest;
import net.polyv.live.entity.channel.operate.LiveUpdateChannelMaxViewerRequest;
import net.polyv.live.entity.channel.operate.LiveUpdateChannelStreamRequest;
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
    Boolean updateChannelSetting(LiveChannelSettingRequest liveChannelSettingRequest) throws IOException;
    
    /**
     * 设置频道详情
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/detail-update/
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return 频道详情
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest)
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
    Boolean updateChannelPassword(LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除直播频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/deletechannel/
     * @param liveDeleteChannelRequest 删除直播频道请求体
     * @return 删除直播频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteChannel(LiveDeleteChannelRequest liveDeleteChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量删除频道
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/batch-delete-channels/
     * @param liveDeleteChannelListRequest 批量删除频道请求体
     * @return 批量删除频道返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteChannelList(LiveDeleteChannelListRequest liveDeleteChannelListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道单点登陆token
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/set-token-1/
     * @param liveCreateChannelTokenRequest 设置频道单点登陆token请求体
     * @return 设置频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean createChannelToken(LiveCreateChannelTokenRequest liveCreateChannelTokenRequest)
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
    Boolean updateSonChannelInfo(LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置子频道单点登陆token
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/set-account-token/
     * @param liveCreateSonChannelTokenRequest 设置子频道单点登陆token请求体
     * @return 设置子频道单点登陆token返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean createSonChannelToken(LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest)
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
    Boolean deleteSonChannel(LiveDeleteSonChannelRequest liveDeleteSonChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 创建重制课件任务
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/add-record-task/
     * @param liveCreateChannelPPTRecordRequest 创建重制课件任务请求实体
     * @return 创建重制课件任务返回实体，成功返回""
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean createChannelPPTRecordTask(LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道回调设置接口
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/get-callback-setting/
     * @param liveChannelCallbackSettingRequest 查询频道回调设置接口请求实体
     * @return 查询频道回调设置接口返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelCallbackSettingResponse channelCallbackSetting(
            LiveChannelCallbackSettingRequest liveChannelCallbackSettingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道回调设置
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/update-setting/
     * @param liveUpdateChannelCallbackSettingRequest 设置频道回调设置请求实体
     * @return 设置频道回调设置返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelCallbackSetting(
            LiveUpdateChannelCallbackSettingRequest liveUpdateChannelCallbackSettingRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量创建子频道
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/batch-create/
     * @param liveCreateSonChannelListRequest 批量创建子频道请求实体
     * @return 批量创建子频道返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveCreateSonChannelListResponse createSonChannelList(
            LiveCreateSonChannelListRequest liveCreateSonChannelListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取账号或频道转播列表信息
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/get-transmit-associations/
     * @param liveChannelTransmitListRequest 获取账号或频道转播列表信息请求实体
     * @return 获取账号或频道转播列表信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelTransmitListResponse channelTransmitList(LiveChannelTransmitListRequest liveChannelTransmitListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置频道最大在线人数
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/setmaxviewerv2/
     * @param liveUpdateChannelMaxViewerRequest 设置频道最大在线人数请求实体
     * @return 设置频道最大在线人数返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelMaxViewer(LiveUpdateChannelMaxViewerRequest liveUpdateChannelMaxViewerRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道广告列表
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/pdcz/channel-advert-list/
     * @param liveChannelAdvertListRequest 查询频道广告列表请求实体
     * @return 查询频道广告列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelAdvertListResponse channelAdvertList(LiveChannelAdvertListRequest liveChannelAdvertListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道直播截图
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/get-capture-image/
     * @param liveChannelCaptureRequest 查询频道直播截图请求实体
     * @return 查询频道直播截图返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String channelCapture(LiveChannelCaptureRequest liveChannelCaptureRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改直播推流方式
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/update-stream-type/
     * @param liveUpdateChannelStreamRequest 修改直播推流方式请求实体
     * @return 修改直播推流方式返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelStream(LiveUpdateChannelStreamRequest liveUpdateChannelStreamRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置硬盘推流直播
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/add-disk-videos/
     * @param liveCreateDiskVideosStreamRequest 设置硬盘推流直播请求实体
     * @return 设置硬盘推流直播返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean createDiskVideosStream(LiveCreateDiskVideosStreamRequest liveCreateDiskVideosStreamRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除硬盘推流的视频
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbglgn/pdcz/delete-disk-videos/
     * @param liveDeleteDiskVideosStreamRequest 删除硬盘推流的视频请求实体
     * @return 删除硬盘推流的视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteDiskVideosStream(LiveDeleteDiskVideosStreamRequest liveDeleteDiskVideosStreamRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
