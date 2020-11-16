package net.polyv.live.v1.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.web.menu.LiveAddChannelMenuRequest;
import net.polyv.live.v1.entity.web.menu.LiveAddChannelMenuResponse;
import net.polyv.live.v1.entity.web.menu.LiveListChannelMenuRequest;
import net.polyv.live.v1.entity.web.menu.LiveListChannelMenuResponse;
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
                    .setContent("<html><body><h1>hello world</h1><button onclick=\"console.log('hello world')" +
                            "\"></button></body></html>")
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
     * 约束：2、如果该类型的菜单已经存在，会抛出“menu already exist”异常。
     * 描述：添加一个频道菜单
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testAddChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveAddChannelMenuRequest liveAddChannelMenuRequest = new LiveAddChannelMenuRequest();
        LiveAddChannelMenuResponse liveAddChannelMenuResponse;
        try {
            liveAddChannelMenuRequest.setChannelId(createChannel())
                    .setName("直播描述")
                    .setType("desc")
                    .setContent("XXX为你讲述成功之道")
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
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUpdateChannelMenuSort() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelMenuSortRequest liveUpdateChannelMenuSortRequest = new LiveUpdateChannelMenuSortRequest();
        Boolean liveUpdateChannelMenuSortResponse;
        try {
            String channelId = createChannel();
            List<String> menuIds = listChannelMenuIds(channelId);
            Collections.shuffle(menuIds);
            String menuIdsStr = StringUtils.join(menuIds.toArray(), ",");
            liveUpdateChannelMenuSortRequest.setChannelId(channelId)
                    .setMenuIds(menuIdsStr)
                    .setLang("zh_CN")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuSortResponse = new LiveWebMenuServiceImpl().updateChannelMenuSort(liveUpdateChannelMenuSortRequest);
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
     * 测试用例结束
     */
}
