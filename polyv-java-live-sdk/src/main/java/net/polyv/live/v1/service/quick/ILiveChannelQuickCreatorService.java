package net.polyv.live.v1.service.quick;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.v1.entity.channel.operate.LiveCreateSonChannelListRequest;
import net.polyv.live.v1.entity.quick.QuickCreateChannelResponse;
import net.polyv.live.v1.entity.quick.QuickCreatePPTChannelRequest;
import net.polyv.live.v1.entity.quick.QuickCreateVideoChannelRequest;

/**
 * 快速创建直播整套业务逻辑
 * @author: thomas
 **/
public interface ILiveChannelQuickCreatorService {
    
    
    /**
     * 快速创建简单三分屏频道
     * @param quickCreateChannelRequest 快速创建频道请求实体
     * @return 频道基本信息
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 系统异常
     */
    public QuickCreateChannelResponse quickCreatePPTSence(QuickCreatePPTChannelRequest quickCreateChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
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
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 快速创建简单视频直播频道
     * @param quickCreateVideoChannelRequest 快速创建简单视频直播频道请求实体
     * @return 频道信息
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    public QuickCreateChannelResponse quickCreateVideoSence(
            QuickCreateVideoChannelRequest quickCreateVideoChannelRequest)
            throws IOException, NoSuchAlgorithmException;
    
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
            throws IOException, NoSuchAlgorithmException;
}
