package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.web.auth.LiveChannelAuthExternalRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalResponse;
import net.polyv.live.entity.web.auth.LiveCreateChannelWriteListRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelAuthRequest;

/**
 * 直播Web观看页管理
 * @author: sadboy
 **/
public interface ILiveWebAuthService {
    
    /**
     * 添加单个白名单
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/szgkygg/ymgktj/add-white-list/
     * @param liveCreateChannelWriteListRequest 添加单个白名单请求体
     * @return 添加单个白名单返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createChannelWriteList(LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置观看条件
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/channel-auth-update/
     * @param liveUpdateChannelAuthRequest 设置观看条件请求实体
     * @return 设置观看条件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelAuth(LiveUpdateChannelAuthRequest liveUpdateChannelAuthRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 通过接口设置外部授权
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymgktj/externalauth/
     * @param liveChannelAuthExternalRequest 通过接口设置外部授权请求实体
     * @return 通过接口设置外部授权返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelAuthExternalResponse updateChannelAuthExternal(
            LiveChannelAuthExternalRequest liveChannelAuthExternalRequest) throws IOException, NoSuchAlgorithmException;
    
}
