package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.web.setting.LiveChannelGlobalSwitchRequest;
import net.polyv.live.entity.web.setting.LiveUploadImageRequest;
import net.polyv.live.entity.web.setting.LiveUploadImageResponse;

/**
 * @author: sadboy
 **/
public interface ILiveWebSettingService {
    
    /**
     * 设置频道默认项开关
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/kjsz/update-global-enabled/
     * @param liveChannelGlobalSwitchRequest 设置频道默认项开关请求实体
     * @return 设置频道默认项开关返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean setChannelGlobalSwitch(LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 上传图片资源
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/kjsz/uploadimage/
     * @param liveUploadImageRequest 上传图片资源请求实体
     * @return 上传图片资源返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveUploadImageResponse uploadImage(LiveUploadImageRequest liveUploadImageRequest)
            throws IOException, NoSuchAlgorithmException;
}
