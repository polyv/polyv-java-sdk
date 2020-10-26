package net.polyv.live.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.web.menu.LiveUpdateChannelMenuRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.web.ILiveWebMenuService;

/**
 * @author: sadboy
 **/
public class LiveWebMenuServiceImpl extends LiveBaseService implements ILiveWebMenuService {
    
    /**
     * 设置自定义菜单直播介绍
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/menu/setmenu/
     * @param liveUpdateChannelMenuRequest 设置自定义菜单直播介绍请求实体
     * @return 设置自定义菜单直播介绍返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelMenu(LiveUpdateChannelMenuRequest liveUpdateChannelMenuRequest)
            throws IOException, NoSuchAlgorithmException {
        liveUpdateChannelMenuRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_MENU_SET_URL, liveUpdateChannelMenuRequest.getUserId(),
                liveUpdateChannelMenuRequest.getChannelId());
        String liveUpdateChannelMenuResponse = this.basePost(url, liveUpdateChannelMenuRequest, String.class);
        return "success".equals(liveUpdateChannelMenuResponse);
    }
    
}
