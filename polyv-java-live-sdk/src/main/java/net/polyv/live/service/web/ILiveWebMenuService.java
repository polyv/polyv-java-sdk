package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.web.menu.LiveAddChannelMenuRequest;
import net.polyv.live.entity.web.menu.LiveAddChannelMenuResponse;
import net.polyv.live.entity.web.menu.LiveListChannelMenuRequest;
import net.polyv.live.entity.web.menu.LiveListChannelMenuResponse;
import net.polyv.live.entity.web.menu.LiveUpdateChannelMenuRequest;

/**
 * @author: sadboy
 **/
public interface ILiveWebMenuService {
    
    /**
     * 设置自定义菜单直播介绍
     * API地址：https://dev.polyv.net/2017/liveproduct/l-api/szgkygg/menu/setmenu/
     * @param liveUpdateChannelMenuRequest 设置自定义菜单直播介绍请求实体
     * @return 设置自定义菜单直播介绍返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelMenu(LiveUpdateChannelMenuRequest liveUpdateChannelMenuRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询频道的菜单信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/menu/channel-menu-list/
     * @param liveListChannelMenuRequest 查询频道的菜单信息请求实体
     * @return 查询频道的菜单信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelMenuResponse listChannelMenu(LiveListChannelMenuRequest liveListChannelMenuRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 添加频道菜单
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/szgkygg/menu/add/
     * @param liveAddChannelMenuRequest 添加频道菜单请求实体
     * @return 添加频道菜单返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveAddChannelMenuResponse addChannelMenu(LiveAddChannelMenuRequest liveAddChannelMenuRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
