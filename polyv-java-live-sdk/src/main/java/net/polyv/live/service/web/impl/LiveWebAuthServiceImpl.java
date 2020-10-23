package net.polyv.live.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalRequest;
import net.polyv.live.entity.web.auth.LiveChannelAuthExternalResponse;
import net.polyv.live.entity.web.auth.LiveCreateChannelWriteListRequest;
import net.polyv.live.entity.web.info.LiveUpdateChannelAuthRequest;
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
    public String createChannelWriteList(LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_WRITE_LIST_ADD_URL;
        String liveCreateChannelWriteListResponse = this.basePost(url, liveCreateChannelWriteListRequest, String.class);
        return liveCreateChannelWriteListResponse;
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
        Integer channelId = liveUpdateChannelAuthRequest.getChannelId();
        if (channelId != null) {
            signMap.put("channelId", channelId.toString());
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
        liveChannelAuthExternalRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_AUTH_EXTERNAL_URL, liveChannelAuthExternalRequest.getUserId());
        List<LiveChannelAuthExternalResponse.ChannelAuthExternal> channelAuthExternals = this.basePostReturnArray(url,
                liveChannelAuthExternalRequest, LiveChannelAuthExternalResponse.ChannelAuthExternal.class);
        LiveChannelAuthExternalResponse liveChannelAuthExternalResponse = new LiveChannelAuthExternalResponse();
        liveChannelAuthExternalResponse.setChannelAuthExternals(channelAuthExternals);
        return liveChannelAuthExternalResponse;
    }
    
}
