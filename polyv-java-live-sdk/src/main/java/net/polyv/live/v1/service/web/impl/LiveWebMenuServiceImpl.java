package net.polyv.live.v1.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.live.v1.config.LiveGlobalConfig;
import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.web.menu.LiveAddChannelMenuRequest;
import net.polyv.live.v1.entity.web.menu.LiveAddChannelMenuResponse;
import net.polyv.live.v1.entity.web.menu.LiveDeleteChannelMenuRequest;
import net.polyv.live.v1.entity.web.menu.LiveGetChannelImageTextRequest;
import net.polyv.live.v1.entity.web.menu.LiveGetChannelImageTextResponse;
import net.polyv.live.v1.entity.web.menu.LiveListChannelMenuRequest;
import net.polyv.live.v1.entity.web.menu.LiveListChannelMenuResponse;
import net.polyv.live.v1.entity.web.menu.LiveSetConsultingEnabledRequest;
import net.polyv.live.v1.entity.web.menu.LiveUpdateChannelMenuInfoRequest;
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
    
    /**
     * 设置指定菜单id的频道菜单信息
     * URL地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/menu/update-channel-menu/
     * @param liveUpdateChannelMenuInfoRequest 设置指定菜单id的频道菜单信息请求实体
     * @return 设置指定菜单id的频道菜单信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelMenuInfo(LiveUpdateChannelMenuInfoRequest liveUpdateChannelMenuInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_MENU_UPDATE_URL;
        String liveUpdateChannelMenuInfoResponse = this.basePost(url, liveUpdateChannelMenuInfoRequest, String.class);
        return "success".equals(liveUpdateChannelMenuInfoResponse);
    }
    
    /**
     * 删除频道菜单
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/menu/menu-delete/
     * @param liveDeleteChannelMenuRequest 删除频道菜单请求实体
     * @return 删除频道菜单返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteChannelMenu(LiveDeleteChannelMenuRequest liveDeleteChannelMenuRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.DELETE_CHANNEL_MENU_URL;
        String liveDeleteChannelMenuResponse = this.basePost(url, liveDeleteChannelMenuRequest, String.class);
        return "2".equals(liveDeleteChannelMenuResponse);
    }
    
    /**
     * 设置提问功能显示开关
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/menu/update-consulting-enabled/
     * @param liveSetConsultingEnabledRequest 设置提问功能显示开关请求实体
     * @return 设置提问功能显示开关返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setConsultingEnabled(LiveSetConsultingEnabledRequest liveSetConsultingEnabledRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.UPDATE_CHANNEL_CONSULTING_ENABLED_URL,
                liveSetConsultingEnabledRequest.getChannelId());
        String liveSetConsultingEnabledResponse = this.basePost(url, liveSetConsultingEnabledRequest, String.class);
        return "".equals(liveSetConsultingEnabledResponse);
    }
    
    /**
     * 查询频道图文内容列表
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/szgkygg/menu/tuwen-list/
     * @param liveGetChannelImageTextRequest 查询频道图文内容列表请求实体
     * @return 查询频道图文内容列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveGetChannelImageTextResponse getChannelImageText(
            LiveGetChannelImageTextRequest liveGetChannelImageTextRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.GET_CHANNEL_IMAGE_TEXT_URL;
        return this.basePost(url,liveGetChannelImageTextRequest,LiveGetChannelImageTextResponse.class);
    }
    
}

