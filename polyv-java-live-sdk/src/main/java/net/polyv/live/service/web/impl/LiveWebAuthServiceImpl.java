package net.polyv.live.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthCustomRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthCustomResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthFieldRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthFieldResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthInfoRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthInfoResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthResponse;
import net.polyv.live.entity.web.auth.LiveChannelAuthTypeRequest;
import net.polyv.live.entity.web.auth.LiveChannelWriteListRequest;
import net.polyv.live.entity.web.auth.LiveChannelWriteListResponse;
import net.polyv.live.entity.web.auth.LiveCreateChannelWriteListRequest;
import net.polyv.live.entity.web.auth.LiveDeleteChannelWriteListRequest;
import net.polyv.live.entity.web.auth.LiveDownloadChannelAuthInfoRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelAuthRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelAuthUrlRequest;
import net.polyv.live.entity.web.auth.LiveUpdateChannelWriteListRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.web.ILiveWebAuthService;
import net.polyv.live.util.MapUtil;

/**
 * 直播Web观看页管理
 * @author: sadboy
 **/
public class LiveWebAuthServiceImpl extends LiveBaseService implements ILiveWebAuthService {
    
    /**
     * 添加单个白名单
     * @param liveCreateChannelWriteListRequest 添加单个白名单请求体
     * @return 添加单个白名单返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean createChannelWriteList(LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_WRITE_LIST_ADD_URL;
        String liveCreateChannelWriteListResponse = this.basePost(url, liveCreateChannelWriteListRequest, String.class);
        return "success".equals(liveCreateChannelWriteListResponse);
    }
    
    /**
     * 设置观看条件
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/channel-auth-update/
     * @param liveUpdateChannelAuthRequest 设置观看条件请求实体
     * @return 设置观看条件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelAuth(LiveUpdateChannelAuthRequest liveUpdateChannelAuthRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_AUTH_UPDATE_URL;
        Map<String, String> signMap = MapUtil.getSignMap(liveUpdateChannelAuthRequest);
        String channelId = liveUpdateChannelAuthRequest.getChannelId();
        if (channelId != null) {
            signMap.put("channelId", channelId);
        }
        String liveUpdateChannelAuthResponse = this.basePostJson(url, signMap, liveUpdateChannelAuthRequest,
                String.class);
        return "true".equals(liveUpdateChannelAuthResponse);
    }
    
    /**
     * 通过接口设置外部授权
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymgktj/externalauth/
     * @param liveChannelAuthExternalRequest 通过接口设置外部授权请求实体
     * @return 通过接口设置外部授权返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelAuthExternalResponse updateChannelAuthExternal(
            LiveChannelAuthExternalRequest liveChannelAuthExternalRequest)
            throws IOException, NoSuchAlgorithmException {
        liveChannelAuthExternalRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_AUTH_EXTERNAL_URL, liveChannelAuthExternalRequest.getUserId());
        List<LiveChannelAuthExternalResponse.ChannelAuthExternal> channelAuthExternals = this.basePostReturnArray(url,
                liveChannelAuthExternalRequest, LiveChannelAuthExternalResponse.ChannelAuthExternal.class);
        LiveChannelAuthExternalResponse liveChannelAuthExternalResponse = new LiveChannelAuthExternalResponse();
        liveChannelAuthExternalResponse.setChannelAuthExternals(channelAuthExternals);
        return liveChannelAuthExternalResponse;
    }
    
    /**
     * 设置自定义授权地址
     * API地址：https://dev.polyv.net/2016/liveproduct/l-api/szgkygg/ymgktj/zbsq/
     * @param liveChannelAuthCustomRequest 设置自定义授权地址请求实体
     * @return 设置自定义授权地址返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelAuthCustomResponse updateChannelAuthCustom(
            LiveChannelAuthCustomRequest liveChannelAuthCustomRequest) throws IOException, NoSuchAlgorithmException {
        liveChannelAuthCustomRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_AUTH_CUSTOM_URL, liveChannelAuthCustomRequest.getUserId());
        List<LiveChannelAuthCustomResponse.ChannelAuthExternal> channelAuthExternals = this.basePostReturnArray(url,
                liveChannelAuthCustomRequest, LiveChannelAuthCustomResponse.ChannelAuthExternal.class);
        LiveChannelAuthCustomResponse liveChannelAuthCustomResponse = new LiveChannelAuthCustomResponse();
        liveChannelAuthCustomResponse.setChannelAuthExternals(channelAuthExternals);
        return liveChannelAuthCustomResponse;
    }
    
    /**
     * 设置授权观看类型
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/ymgktj/set-auth-type/
     * @param liveChannelAuthTypeRequest 设置授权观看类型请求实体
     * @return 设置授权观看类型返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelAuthType(LiveChannelAuthTypeRequest liveChannelAuthTypeRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_AUTH_TYPE_URL, liveChannelAuthTypeRequest.getChannelId());
        String liveChannelAuthTypeResponse = this.basePost(url, liveChannelAuthTypeRequest, String.class);
        return "修改成功".equals(liveChannelAuthTypeResponse);
    }
    
    /**
     * 查询直播频道观看条件
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/auth/
     * @param liveChannelAuthRequest 查询直播频道观看条件请求实体
     * @return 查询直播频道观看条件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelAuthResponse getChannelAuth(LiveChannelAuthRequest liveChannelAuthRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_AUTH_GET_URL;
        List<LiveChannelSettingRequest.AuthSetting> authSettings = this.basePostReturnArray(url, liveChannelAuthRequest,
                LiveChannelSettingRequest.AuthSetting.class);
        LiveChannelAuthResponse liveChannelAuthResponse = new LiveChannelAuthResponse();
        liveChannelAuthResponse.setAuthSettings(authSettings);
        return liveChannelAuthResponse;
    }
    
    /**
     * 查询频道观看白名单列表
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/get-white-list/
     * @param liveChannelWriteListRequest 查询频道观看白名单列表请求实体
     * @return 查询频道观看白名单列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelWriteListResponse getChannelWriteList(LiveChannelWriteListRequest liveChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_WRITE_LIST_GET_URL;
        return this.baseGet(url, liveChannelWriteListRequest, LiveChannelWriteListResponse.class);
        
    }
    
    /**
     * 设置授权认证URL
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/update-auth-url/
     * @param liveUpdateChannelAuthUrlRequest 设置授权认证URL请求实体
     * @return 设置授权认证URL返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelAuthUrl(LiveUpdateChannelAuthUrlRequest liveUpdateChannelAuthUrlRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_AUTH_URL_UPDATE_URL;
        String liveUpdateChannelAuthUrlResponse = this.basePost(url, liveUpdateChannelAuthUrlRequest, String.class);
        return "true".equals(liveUpdateChannelAuthUrlResponse);
    }
    
    /**
     * 更新白名单
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/szgkygg/ymgktj/update-white-list/
     * @param liveUpdateChannelWriteListRequest 更新白名单请求实体
     * @return 更新白名单返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelWriteList(LiveUpdateChannelWriteListRequest liveUpdateChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_CHANNEL_WRITE_LIST_URL;
        String liveUpdateChannelWriteListResponse = this.basePost(url, liveUpdateChannelWriteListRequest, String.class);
        return "success".equals(liveUpdateChannelWriteListResponse);
    }
    
    /**
     * 删除白名单
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/szgkygg/ymgktj/delete-white-list/
     * @param liveDeleteChannelWriteListRequest 删除白名单请求实体
     * @return 删除白名单返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteChannelWriteList(LiveDeleteChannelWriteListRequest liveDeleteChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.DELETE_CHANNEL_WRITE_LIST_URL;
        String liveDeleteChannelWriteListResponse = this.basePost(url, liveDeleteChannelWriteListRequest, String.class);
        return "success".equals(liveDeleteChannelWriteListResponse);
    }
    
    /**
     * 查询频道或全局登记观看字段
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/get-record-field/
     * @param liveChannelAuthFieldRequest 查询频道或全局登记观看字段请求实体
     * @return 查询频道或全局登记观看字段返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelAuthFieldResponse getChannelAuthField(LiveChannelAuthFieldRequest liveChannelAuthFieldRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_AUTH_FIELD_GET_URL;
        List<LiveChannelAuthFieldResponse.ChannelAuthField> channelAuthFields = this.baseGetReturnArray(url,
                liveChannelAuthFieldRequest, LiveChannelAuthFieldResponse.ChannelAuthField.class);
        LiveChannelAuthFieldResponse liveChannelAuthFieldResponse = new LiveChannelAuthFieldResponse();
        liveChannelAuthFieldResponse.setChannelAuthFields(channelAuthFields);
        return liveChannelAuthFieldResponse;
    }
    
    /**
     * 查询页面登记观看列表
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/get-record-info/
     * @param liveChannelAuthInfoRequest 查询页面登记观看列表请求实体
     * @return 查询页面登记观看列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelAuthInfoResponse getChannelAuthInfo(LiveChannelAuthInfoRequest liveChannelAuthInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_AUTH_INFO_GET_URL;
        return this.baseGet(url, liveChannelAuthInfoRequest, LiveChannelAuthInfoResponse.class);
    }
    
    /**
     * 下载频道登记观看记录
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymgktj/download-record-info/
     * @param liveDownloadChannelAuthInfoRequest 下载频道登记观看记录请求实体
     * @return 下载频道登记观看记录返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public byte[] downloadChannelAuthInfo(LiveDownloadChannelAuthInfoRequest liveDownloadChannelAuthInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.DOWNLOAD_CHANNEL_AUTH_INFO_URL;
        return this.baseGetReturnArray(url, liveDownloadChannelAuthInfoRequest);
    }
    
}
