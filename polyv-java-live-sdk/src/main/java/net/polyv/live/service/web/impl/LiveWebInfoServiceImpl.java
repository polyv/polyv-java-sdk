package net.polyv.live.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.web.info.LiveUpdateChannelNameRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.web.ILiveWebInfoService;

/**
 * @author: sadboy
 **/
public class LiveWebInfoServiceImpl extends LiveBaseService implements ILiveWebInfoService {
    
    /**
     * 设置频道名称
     * API地址：https://dev.polyv.net/2016/liveproduct/l-api/szgkygg/ymxxsz/updatechannelname/
     * @param liveUpdateChannelNameRequest 设置频道名称请求实体
     * @return 设置频道名称返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String updateChannelName(LiveUpdateChannelNameRequest liveUpdateChannelNameRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_NAME_SET_URL, liveUpdateChannelNameRequest.getChannelId());
        String liveUpdateChannelNameResponse = this.basePost(url, liveUpdateChannelNameRequest, String.class);
        return liveUpdateChannelNameResponse;
    }
    
}
