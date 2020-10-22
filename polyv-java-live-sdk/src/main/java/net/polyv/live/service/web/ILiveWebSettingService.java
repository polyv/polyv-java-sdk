package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.web.setting.LiveChannelGlobalSwitchRequest;

/**
 * @author: sadboy
 **/
public interface ILiveWebSettingService {
    
    /**
     * 设置频道默认项开关
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/kjsz/update-global-enabled/
     * @param liveChannelGlobalSwitchRequest 设置频道默认项开关请求实体
     * @return 设置频道默认项开关返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String channelGlobalSwitch(LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
