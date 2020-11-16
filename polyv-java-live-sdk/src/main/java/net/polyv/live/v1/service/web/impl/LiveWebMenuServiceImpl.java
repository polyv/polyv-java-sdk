package net.polyv.live.v1.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.live.v1.config.LiveGlobalConfig;
import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.web.menu.LiveAddChannelMenuRequest;
import net.polyv.live.v1.entity.web.menu.LiveAddChannelMenuResponse;
import net.polyv.live.v1.entity.web.menu.LiveListChannelMenuRequest;
import net.polyv.live.v1.entity.web.menu.LiveListChannelMenuResponse;
import net.polyv.live.v1.entity.web.menu.LiveUpdateChannelMenuRequest;
import net.polyv.live.v1.entity.web.menu.LiveUpdateChannelMenuSortRequest;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.web.ILiveWebMenuService;

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
        liveUpdateChannelMenuRequest.setUserId(LiveGlobalConfig.getUserId());
        String url = LiveURL.getRealUrl(LiveURL.CHANNEL_MENU_SET_URL, liveUpdateChannelMenuRequest.getUserId(),
                liveUpdateChannelMenuRequest.getChannelId());
        String liveUpdateChannelMenuResponse = this.basePost(url, liveUpdateChannelMenuRequest, String.class);
        return "success".equals(liveUpdateChannelMenuResponse);
    }
    
    /**
     * 查询频道的菜单信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/menu/channel-menu-list/
     * @param liveListChannelMenuRequest 查询频道的菜单信息请求实体
     * @return 查询频道的菜单信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelMenuResponse listChannelMenu(LiveListChannelMenuRequest liveListChannelMenuRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_MENU_LIST_URL;
        List<LiveListChannelMenuResponse.ChannelMenu> channelMenus = this.baseGetReturnArray(url,
                liveListChannelMenuRequest, LiveListChannelMenuResponse.ChannelMenu.class);
        LiveListChannelMenuResponse liveListChannelMenuResponse = new LiveListChannelMenuResponse();
        liveListChannelMenuResponse.setChannelMenus(channelMenus);
        return liveListChannelMenuResponse;
    }
    
    /**
     * 添加频道菜单
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/szgkygg/menu/add/
     * @param liveAddChannelMenuRequest 添加频道菜单请求实体
     * @return 添加频道菜单返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveAddChannelMenuResponse addChannelMenu(LiveAddChannelMenuRequest liveAddChannelMenuRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.ADD_CHANNEL_MENU_URL;
        return this.basePost(url, liveAddChannelMenuRequest, LiveAddChannelMenuResponse.class);
    }
    
    /**
     * 设置频道菜单排序
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/menu/update-rank/
     * @param liveUpdateChannelMenuSortRequest 设置频道菜单排序请求实体
     * @return 设置频道菜单排序返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelMenuSort(LiveUpdateChannelMenuSortRequest liveUpdateChannelMenuSortRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_CHANNEL_MENU_SORT_URL;
        String liveUpdateChannelMenuSortResponse = this.basePost(url, liveUpdateChannelMenuSortRequest, String.class);
        return "success".equals(liveUpdateChannelMenuSortResponse);
    }
    
}

