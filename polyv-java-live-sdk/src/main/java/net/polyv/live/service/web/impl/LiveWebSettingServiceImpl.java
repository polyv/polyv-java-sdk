package net.polyv.live.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.web.setting.LiveChannelGlobalSwitchRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.web.ILiveWebSettingService;

/**
 * @author: sadboy
 **/
public class LiveWebSettingServiceImpl extends LiveBaseService implements ILiveWebSettingService {
    
    /**
     * 设置频道默认项开关
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/kjsz/update-global-enabled/
     * @param liveChannelGlobalSwitchRequest 设置频道默认项开关请求实体
     * @return 设置频道默认项开关返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean channelGlobalSwitch(LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_GLOBAL_SWITCH_URL;
        String liveChannelGlobalSwitchResponse = this.basePost(url, liveChannelGlobalSwitchRequest, String.class);
        return "true".equals(liveChannelGlobalSwitchResponse);
    }
    
}
