package net.polyv.live.v1.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.StringUtils;
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
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.web.impl.LiveWebMenuServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 页面菜单
 * @author: sadboy
 **/
@Slf4j
public class LiveWebMenuImplTest extends BaseTest {
    
    /**
     * 测试设置自定义菜单直播介绍
     * 描述：设置自定义菜单中用户设置菜单的直播介绍
     * 返回：true为设置成功，false为设置失败
     * API地址：CHANNEL_MENU_SET_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelMenuRequest liveUpdateChannelMenuRequest = new LiveUpdateChannelMenuRequest();
        Boolean liveUpdateChannelMenuResponse;
        try {
            liveUpdateChannelMenuRequest.setChannelId(createChannel())
                    .setMenuType("desc")
                    .setContent("<html><body><h1>hello world</h1></body></html>")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuResponse = new LiveWebMenuServiceImpl().updateChannelMenu(
                    liveUpdateChannelMenuRequest);
            Assert.assertNotNull(liveUpdateChannelMenuResponse);
            if (liveUpdateChannelMenuResponse) {
                //to do something ......
                log.debug("测试设置自定义菜单直播介绍成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道的菜单信息
     * 描述：获取频道的菜单信息
     * API地址：CHANNEL_MENU_LIST_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveListChannelMenuRequest liveListChannelMenuRequest = new LiveListChannelMenuRequest();
        LiveListChannelMenuResponse liveListChannelMenuResponse;
        try {
            liveListChannelMenuRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveListChannelMenuResponse = new LiveWebMenuServiceImpl().listChannelMenu(liveListChannelMenuRequest);
            Assert.assertNotNull(liveListChannelMenuResponse);
            if (liveListChannelMenuResponse != null) {
                //to do something ......
                log.debug("测试查询频道的菜单信息成功,{}", JSON.toJSONString(liveListChannelMenuResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试添加频道菜单
     * 约束：2、如果desc类型的菜单已经存在，会抛出“menu already exist”异常。
     * 描述：添加一个频道菜单
     * API地址：ADD_CHANNEL_MENU_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testAddChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveAddChannelMenuRequest liveAddChannelMenuRequest = new LiveAddChannelMenuRequest();
        LiveAddChannelMenuResponse liveAddChannelMenuResponse;
        try {
            liveAddChannelMenuRequest.setChannelId(createChannel())
                    .setName("推广2")
                    .setType("iframe")
                    .setContent("http://live.polyv.net")
                    .setLang("zh_CN")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveAddChannelMenuResponse = new LiveWebMenuServiceImpl().addChannelMenu(liveAddChannelMenuRequest);
            Assert.assertNotNull(liveAddChannelMenuResponse);
            if (liveAddChannelMenuResponse != null) {
                //to do something ......
                log.debug("测试添加频道菜单成功,{}", JSON.toJSONString(liveAddChannelMenuResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置频道菜单排序
     * 约束：2、频道菜单ID列表，必须是完整的列表（不能多也不能少）
     * 描述：设置直播频道的菜单的顺序
     * API地址：UPDATE_CHANNEL_MENU_SORT_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelMenuSort() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelMenuSortRequest liveUpdateChannelMenuSortRequest = new LiveUpdateChannelMenuSortRequest();
        Boolean liveUpdateChannelMenuSortResponse;
        try {
            String channelId = super.createChannel();
            List<String> menuIds = listChannelMenuIds(channelId);
            Collections.shuffle(menuIds);
            String menuIdsStr = StringUtils.join(menuIds.toArray(), ",");
            liveUpdateChannelMenuSortRequest.setChannelId(channelId)
                    .setMenuIds(menuIdsStr)
                    .setLang("zh_CN")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuSortResponse = new LiveWebMenuServiceImpl().updateChannelMenuSort(
                    liveUpdateChannelMenuSortRequest);
            Assert.assertNotNull(liveUpdateChannelMenuSortResponse);
            if (liveUpdateChannelMenuSortResponse != null) {
                //to do something ......
                log.debug("测试设置频道菜单排序成功,{}", JSON.toJSONString(liveUpdateChannelMenuSortResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置指定菜单id的频道菜单信息
     * 约束：2、互动聊天或咨询提问的菜单ID不允许设置
     * API地址：CHANNEL_MENU_UPDATE_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelMenuInfo() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelMenuInfoRequest liveUpdateChannelMenuInfoRequest = new LiveUpdateChannelMenuInfoRequest();
        Boolean liveUpdateChannelMenuInfoResponse;
        try {
            liveUpdateChannelMenuInfoRequest.setMenuId("3e687a3575")
                    .setContent("XXX生财之道(Junit勿删)")
                    .setLang("zh_CN")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuInfoResponse = new LiveWebMenuServiceImpl().updateChannelMenuInfo(
                    liveUpdateChannelMenuInfoRequest);
            Assert.assertNotNull(liveUpdateChannelMenuInfoResponse);
            if (liveUpdateChannelMenuInfoResponse != null) {
                //to do something ......
                log.debug("测试设置指定菜单id的频道菜单信息成功,{}", JSON.toJSONString(liveUpdateChannelMenuInfoResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试删除频道菜单
     * 描述：删除指定的频道菜单，支持批量
     * 返回：true为删除成功，false为删除失败
     * API地址：DELETE_CHANNEL_MENU_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelMenuRequest liveDeleteChannelMenuRequest = new LiveDeleteChannelMenuRequest();
        Boolean liveDeleteChannelMenuResponse;
        try {
            liveDeleteChannelMenuRequest.setMenuIds("db1663823d,d9ba333cdc").setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelMenuResponse = new LiveWebMenuServiceImpl().deleteChannelMenu(
                    liveDeleteChannelMenuRequest);
            Assert.assertTrue(liveDeleteChannelMenuResponse);
            if (liveDeleteChannelMenuResponse) {
                //to do something ......
                log.debug("测试删除频道菜单成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置提问功能显示开关
     * 描述：可以开启或关闭咨询提问功能菜单
     * API地址：UPDATE_CHANNEL_CONSULTING_ENABLED_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetConsultingEnabled() throws Exception, NoSuchAlgorithmException {
        LiveSetConsultingEnabledRequest liveSetConsultingEnabledRequest = new LiveSetConsultingEnabledRequest();
        Boolean liveSetConsultingEnabledResponse;
        try {
            liveSetConsultingEnabledRequest.setChannelId(createChannel())
                    .setEnabled("N")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSetConsultingEnabledResponse = new LiveWebMenuServiceImpl().setConsultingEnabled(
                    liveSetConsultingEnabledRequest);
            Assert.assertTrue(liveSetConsultingEnabledResponse);
            if (liveSetConsultingEnabledResponse) {
                //to do something ......
                log.debug("测试设置提问功能显示开关成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道图文内容列表
     * 描述：可以开启或关闭咨询提问功能菜单
     * API地址：GET_CHANNEL_IMAGE_TEXT_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetChannelImageText() throws Exception, NoSuchAlgorithmException {
        LiveGetChannelImageTextRequest liveGetChannelImageTextRequest = new LiveGetChannelImageTextRequest();
        LiveGetChannelImageTextResponse liveGetChannelImageTextResponse;
        try {
            liveGetChannelImageTextRequest.setChannelId(createChannel())
                    .setId(null)
                    .setImageMode("N")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveGetChannelImageTextResponse = new LiveWebMenuServiceImpl().getChannelImageText(
                    liveGetChannelImageTextRequest);
            Assert.assertNotNull(liveGetChannelImageTextResponse);
            if (liveGetChannelImageTextResponse != null) {
                //to do something ......
                log.debug("测试查询频道图文内容列表成功，{}",JSON.toJSONString(liveGetChannelImageTextResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试用例结束
     */
}
