package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.web.menu.LiveListChannelMenuRequest;
import net.polyv.live.entity.web.menu.LiveListChannelMenuResponse;
import net.polyv.live.entity.web.menu.LiveUpdateChannelMenuRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.web.impl.LiveWebMenuServiceImpl;

/**
 * 页面菜单
 * @author: sadboy
 **/
@Slf4j
public class LiveWebMenuImplTest extends BaseTest {
    
    /**
     * 测试设置自定义菜单直播介绍
     * 描述:设置自定义菜单中用户设置菜单的直播介绍
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelMenu() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelMenuRequest liveUpdateChannelMenuRequest = new LiveUpdateChannelMenuRequest();
        Boolean liveUpdateChannelMenuResponse;
        liveUpdateChannelMenuRequest.setChannelId(1965681)
                .setMenuType("desc")
                .setContent("<html><body><h1>hello world</h1><button onclick=\"console.log('hello world')" +
                        "\"></button></body></html>");
        liveUpdateChannelMenuResponse = new LiveWebMenuServiceImpl().updateChannelMenu(liveUpdateChannelMenuRequest);
        Assert.assertNotNull(liveUpdateChannelMenuResponse);
        if (liveUpdateChannelMenuResponse) {
            //to do something ......
            log.debug("测试设置自定义菜单直播介绍成功");
        }
    }
    
    /**
     * 测试查询频道的菜单信息
     * 描述:获取频道的菜单信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelMenu() throws IOException, NoSuchAlgorithmException {
        LiveListChannelMenuRequest liveListChannelMenuRequest = new LiveListChannelMenuRequest();
        LiveListChannelMenuResponse liveListChannelMenuResponse;
        liveListChannelMenuRequest.setChannelId(1965681);
        liveListChannelMenuResponse = new LiveWebMenuServiceImpl().listChannelMenu(
                liveListChannelMenuRequest);
        Assert.assertNotNull(liveListChannelMenuResponse);
        if (liveListChannelMenuResponse != null) {
            //to do something ......
            log.debug("测试查询频道的菜单信息成功,{}", JSON.toJSONString(liveListChannelMenuResponse));
        }
    }
    
}
