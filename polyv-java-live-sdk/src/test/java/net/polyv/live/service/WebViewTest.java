package net.polyv.live.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.entity.webview.LiveCreateChannelWriteListRequest;
import net.polyv.live.service.webview.impl.LiveWebViewServiceImpl;

/**
 * Web观看页测试用例
 * @author: sadboy
 **/
@Slf4j
public class WebViewTest extends BaseTest{
    
    
    
    /**
     * 测试添加单个白名单-全局白名单
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCreateChannelWriteList() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest = new LiveCreateChannelWriteListRequest();
        liveCreateChannelWriteListRequest.setRank(1).setCode("天王盖地虎").setName("sadboy");
        String liveCreateChannelWriteListResponse = new LiveWebViewServiceImpl().createChannelWriteList(
                liveCreateChannelWriteListRequest);
        Assert.assertNotNull(liveCreateChannelWriteListResponse);
        if ("success".equals(liveCreateChannelWriteListResponse)) {
            //to do something ......
            log.debug("测试添加单个白名单-全局白名单成功" + liveCreateChannelWriteListResponse);
        }
    }
    
}
