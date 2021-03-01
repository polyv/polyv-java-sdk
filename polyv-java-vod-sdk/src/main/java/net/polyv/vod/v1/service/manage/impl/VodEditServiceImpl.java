package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.edit.VodUpdateVideoPlayStatusRequest;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodEditService;

/**
 * @author: sadboy
 **/
public class VodEditServiceImpl extends VodBaseService implements IVodEditService {
    
    /**
     * 根据vid批量修改视频的授权播放开关状态
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-edit/set-authplay/
     * @param vodUpdateVideoPlayStatusRequest 根据vid批量修改视频的授权播放开关状态请求实体
     * @return 根据vid批量修改视频的授权播放开关状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateVideoPlayStatus(VodUpdateVideoPlayStatusRequest vodUpdateVideoPlayStatusRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.UPDATE_VIDEO_PLAY_STATUS_URL);
        super.postFormBodyReturnOne(url,vodUpdateVideoPlayStatusRequest,String.class);
        return true;
    }
    
}
