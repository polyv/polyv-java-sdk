package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.web.auth.LiveChannelAuthCustomRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthCustomResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthTypeRequest;
import net.polyv.live.entity.web.auth.LiveChannelWriteListRequest;
import net.polyv.live.entity.web.auth.LiveChannelWriteListResponse;
import net.polyv.live.entity.web.auth.LiveCreateChannelWriteListRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelAuthRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelAuthUrlRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelWriteListRequest;

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
    Boolean createChannelWriteList(LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest)
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
    
    /**
     * 设置自定义授权地址
     * API地址：https://dev.polyv.net/2016/liveproduct/l-api/szgkygg/ymgktj/zbsq/
     * @param liveChannelAuthCustomRequest 设置自定义授权地址请求实体
     * @return 设置自定义授权地址返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelAuthCustomResponse updateChannelAuthCustom(LiveChannelAuthCustomRequest liveChannelAuthCustomRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置授权观看类型
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymgktj/set-auth-type/
     * @param liveChannelAuthTypeRequest 设置授权观看类型请求实体
     * @return 设置授权观看类型返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelAuthType(LiveChannelAuthTypeRequest liveChannelAuthTypeRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询直播频道观看条件
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/auth/
     * @param liveChannelAuthRequest 查询直播频道观看条件请求实体
     * @return 查询直播频道观看条件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelAuthResponse channelAuth(LiveChannelAuthRequest liveChannelAuthRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道观看白名单列表
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/get-white-list/
     * @param liveChannelWriteListRequest 查询频道观看白名单列表请求实体
     * @return 查询频道观看白名单列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelWriteListResponse channelWriteList(LiveChannelWriteListRequest liveChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置授权认证URL
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/update-auth-url/
     * @param liveUpdateChannelAuthUrlRequest 设置授权认证URL请求实体
     * @return 设置授权认证URL返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelAuthUrl(LiveUpdateChannelAuthUrlRequest liveUpdateChannelAuthUrlRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 更新白名单
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/szgkygg/ymgktj/update-white-list/
     * @param liveUpdateChannelWriteListRequest 更新白名单请求实体
     * @return 更新白名单返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelWriteList(LiveUpdateChannelWriteListRequest liveUpdateChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
