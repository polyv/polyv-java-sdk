package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.web.setting.LiveChannelGlobalSwitchRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.web.impl.LiveWebSettingServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * 快捷设置
 * @author: sadboy
 **/
@Slf4j
public class LiveWebSettingImplTest extends BaseTest {
    
    /**
     * 测试设置频道默认项开关
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelGlobalSwitch() throws Exception, NoSuchAlgorithmException {
        LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest();
        Boolean liveChannelGlobalSwitchResponse;
        try {
            liveChannelGlobalSwitchRequest.setChannelId(createChannel())
                    .setGlobalEnabledType(LiveConstant.GlobalEnabledType.CALLBACK.getDesc())
                    .setEnabled("N")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelGlobalSwitchResponse = new LiveWebSettingServiceImpl().channelGlobalSwitch(
                    liveChannelGlobalSwitchRequest);
            Assert.assertNotNull(liveChannelGlobalSwitchResponse);
            if (liveChannelGlobalSwitchResponse) {
                //to do something ......
                log.debug("测试设置频道默认项开关成功");
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
