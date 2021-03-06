package net.polyv.live.v1.service.web;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.web.setting.LiveChannelGlobalSwitchRequest;
import net.polyv.live.v1.entity.web.setting.LiveUploadImageRequest;
import net.polyv.live.v1.entity.web.setting.LiveUploadImageResponse;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.web.impl.LiveWebSettingServiceImpl;

/**
 * 快捷设置
 * @author: sadboy
 **/
@Slf4j
public class LiveWebSettingImplTest extends BaseTest {
    
    /**
     * 测试设置频道默认项开关
     * 返回：true为设置成功，false为设置失败
     * API地址：CHANNEL_GLOBAL_SWITCH_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetChannelGlobalSwitch() throws Exception, NoSuchAlgorithmException {
        LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest();
        Boolean liveChannelGlobalSwitchResponse;
        try {
            liveChannelGlobalSwitchRequest.setChannelId(createChannel())
                    .setGlobalEnabledType(LiveConstant.GlobalEnabledType.CALLBACK.getDesc())
                    .setEnabled("N");
            liveChannelGlobalSwitchResponse = new LiveWebSettingServiceImpl().setChannelGlobalSwitch(
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
     * 测试上传图片资源
     * 描述：接口用于上传接口所需图片，同时获取图片地址。图片地址可用于 设置道具打赏 goodImg字段等。
     * API地址：UPDATE_IMAGE_FILE_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testUploadImage() throws Exception, NoSuchAlgorithmException {
        LiveUploadImageRequest liveUploadImageRequest = new LiveUploadImageRequest();
        LiveUploadImageResponse liveUploadImageResponse;
        try {
            String path = getClass().getResource("/img/elephant.png").getPath();
            List<File> fileList = new ArrayList<File>();
            fileList.add(new File(path));
            liveUploadImageRequest.setType("coverImage")
                    .setFile(fileList);
            liveUploadImageResponse = new LiveWebSettingServiceImpl().uploadImage(
                    liveUploadImageRequest);
            Assert.assertNotNull(liveUploadImageResponse);
            if (liveUploadImageResponse != null) {
                //to do something ......
                log.debug("测试上传图片资源成功,{}",liveUploadImageResponse);
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
