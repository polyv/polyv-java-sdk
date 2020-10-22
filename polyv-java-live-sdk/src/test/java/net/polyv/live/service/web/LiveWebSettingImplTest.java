package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.web.setting.LiveChannelGlobalSwitchRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.web.impl.LiveWebSettingServiceImpl;

/**
 * @author: sadboy
 **/
@Slf4j
public class LiveWebSettingImplTest extends BaseTest {
    
    /**
     * 测试设置频道默认项开关
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelGlobalSwitch() throws IOException, NoSuchAlgorithmException {
        LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest();
        liveChannelGlobalSwitchRequest.setChannelId(1965681)
                .setGlobalEnabledType(LiveConstant.GlobalEnabledType.CALLBACK.getDesc())
                .setEnabled("N");
        String liveChannelGlobalSwitchResponse = new LiveWebSettingServiceImpl().channelGlobalSwitch(
                liveChannelGlobalSwitchRequest);
        Assert.assertNotNull(liveChannelGlobalSwitchResponse);
        if ("true".equals(liveChannelGlobalSwitchResponse)) {
            //to do something ......
            log.debug("测试设置频道默认项开关成功{}", liveChannelGlobalSwitchResponse);
        }
    }
    
}
