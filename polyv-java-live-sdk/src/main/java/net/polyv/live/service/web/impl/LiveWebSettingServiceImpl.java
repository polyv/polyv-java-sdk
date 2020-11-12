package net.polyv.live.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.web.setting.LiveChannelGlobalSwitchRequest;
import net.polyv.live.entity.web.setting.LiveUploadImageRequest;
import net.polyv.live.entity.web.setting.LiveUploadImageResponse;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.web.ILiveWebSettingService;
import net.polyv.live.util.MapUtil;

/**
 * @author: sadboy
 **/
public class LiveWebSettingServiceImpl extends LiveBaseService implements ILiveWebSettingService {
    
    /**
     * 设置频道默认项开关
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/kjsz/update-global-enabled/
     * @param liveChannelGlobalSwitchRequest 设置频道默认项开关请求实体
     * @return 设置频道默认项开关返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean channelGlobalSwitch(LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_GLOBAL_SWITCH_URL;
        String liveChannelGlobalSwitchResponse = this.basePost(url, liveChannelGlobalSwitchRequest, String.class);
        return "true".equals(liveChannelGlobalSwitchResponse);
    }
    
    /**
     * 上传图片资源
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/kjsz/uploadimage/
     * @param liveUploadImageRequest 上传图片资源请求实体
     * @return 上传图片资源返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveUploadImageResponse uploadImage(LiveUploadImageRequest liveUploadImageRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_IMAGE_FILE_URL;
        List<String> imgUrls = this.basePostJsonReturnArray(url, MapUtil.getSignMap(liveUploadImageRequest),
                liveUploadImageRequest, String.class);
        LiveUploadImageResponse liveUploadImageResponse = new LiveUploadImageResponse();
        liveUploadImageResponse.setImgUrls(imgUrls);
        return liveUploadImageResponse;
    }
    
}
