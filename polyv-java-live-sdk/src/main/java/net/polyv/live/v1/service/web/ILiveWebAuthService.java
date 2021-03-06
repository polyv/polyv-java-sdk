package net.polyv.live.v1.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.v1.entity.web.auth.LiveChannelAuthCustomRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthCustomResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthExternalRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthExternalResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthFieldRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthFieldResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthInfoRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthInfoResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthTypeRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelWhiteListResponse;
import net.polyv.live.v1.entity.web.auth.LiveCreateChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveDeleteChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveDownloadChannelAuthInfoRequest;
import net.polyv.live.v1.entity.web.auth.LiveDownloadChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveUpdateChannelAuthRequest;
import net.polyv.live.v1.entity.web.auth.LiveUpdateChannelAuthUrlRequest;
import net.polyv.live.v1.entity.web.auth.LiveUpdateChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveUploadWhiteListRequest;


/**
 * 直播Web观看页管理
 * @author: sadboy
 **/
public interface ILiveWebAuthService {
    
    /**
     * 添加单个白名单
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/szgkygg/ymgktj/add-white-list/
     * @param liveCreateChannelWhiteListRequest 添加单个白名单请求体
     * @return 添加单个白名单返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean createChannelWhiteList(LiveCreateChannelWhiteListRequest liveCreateChannelWhiteListRequest)
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
    LiveChannelAuthResponse getChannelAuth(LiveChannelAuthRequest liveChannelAuthRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道观看白名单列表
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/get-white-list/
     * @param liveChannelWhiteListRequest 查询频道观看白名单列表请求实体
     * @return 查询频道观看白名单列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelWhiteListResponse getChannelWhiteList(LiveChannelWhiteListRequest liveChannelWhiteListRequest)
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
     * @param liveUpdateChannelWhiteListRequest 更新白名单请求实体
     * @return 更新白名单返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelWhiteList(LiveUpdateChannelWhiteListRequest liveUpdateChannelWhiteListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除白名单
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/szgkygg/ymgktj/delete-white-list/
     * @param liveDeleteChannelWhiteListRequest 删除白名单请求实体
     * @return 删除白名单返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteChannelWhiteList(LiveDeleteChannelWhiteListRequest liveDeleteChannelWhiteListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道或全局登记观看字段
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/get-record-field/
     * @param liveChannelAuthFieldRequest 查询频道或全局登记观看字段请求实体
     * @return 查询频道或全局登记观看字段返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelAuthFieldResponse getChannelAuthField(LiveChannelAuthFieldRequest liveChannelAuthFieldRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询页面登记观看列表
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/get-record-info/
     * @param liveChannelAuthInfoRequest 查询页面登记观看列表请求实体
     * @return 查询页面登记观看列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveChannelAuthInfoResponse getChannelAuthInfo(LiveChannelAuthInfoRequest liveChannelAuthInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 下载频道登记观看记录
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/download-record-info/
     * @param liveDownloadChannelAuthInfoRequest 下载频道登记观看记录请求实体
     * @return 下载频道登记观看记录返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    byte[] downloadChannelAuthInfo(LiveDownloadChannelAuthInfoRequest liveDownloadChannelAuthInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 新增白名单
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/upload-white-list/
     * @param liveUploadWhiteListRequest 新增白名单请求实体
     * @return 新增白名单返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean uploadWhiteList(LiveUploadWhiteListRequest liveUploadWhiteListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     *下载频道观看白名单列表
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/download-white-list/
     * @param liveDownloadChannelWhiteListRequest 下载频道观看白名单列表请求实体
     * @return 下载频道观看白名单列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    byte[] downloadChannelWhiteList(LiveDownloadChannelWhiteListRequest liveDownloadChannelWhiteListRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
