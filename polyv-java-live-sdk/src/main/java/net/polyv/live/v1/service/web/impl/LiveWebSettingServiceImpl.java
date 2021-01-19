package net.polyv.live.v1.service.web.impl;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.web.setting.LiveChannelGlobalSwitchRequest;
import net.polyv.live.v1.entity.web.setting.LiveUploadImageRequest;
import net.polyv.live.v1.entity.web.setting.LiveUploadImageResponse;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.web.ILiveWebSettingService;

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
    public Boolean setChannelGlobalSwitch(LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_GLOBAL_SWITCH_URL;
        String liveChannelGlobalSwitchResponse = this.postFormBodyReturnOne(url, liveChannelGlobalSwitchRequest, String.class);
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
        Map<String,List<File>> fileListMap = new HashMap<String, List<File>>();
        fileListMap.put("file",liveUploadImageRequest.getFile());
        List<String> imgUrls = this.uploadMultipartFile(url, liveUploadImageRequest, fileListMap,
                String.class);
//        List<String> imgUrls = this.basePostJsonReturnArray(url, MapUtil.getSignMap(liveUploadImageRequest),
//                liveUploadImageRequest, String.class);
        LiveUploadImageResponse liveUploadImageResponse = new LiveUploadImageResponse();
        liveUploadImageResponse.setImgUrls(imgUrls);
        return liveUploadImageResponse;
    }
    
}
