package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameRequest;
import net.polyv.vod.v1.entity.manage.info.VodListVideoKeyFrameResponse;

public interface IVodInfoService {
    
    /**
     * 获取单个视频的打点信息
     * URL地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-info/getkeyframe/
     * @param vodListVideoKeyFrameRequest 获取单个视频的打点信息请求实体
     * @return 获取单个视频的打点信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodListVideoKeyFrameResponse listVideoKeyFrame(VodListVideoKeyFrameRequest vodListVideoKeyFrameRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
