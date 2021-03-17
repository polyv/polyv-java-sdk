package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodBarrageService;

/**
 * 视频弹幕
 * @author: fangyan
 */
public class VodBarrageServiceImpl extends VodBaseService implements IVodBarrageService {
    
    /**
     * 分页查询用户下所有弹幕信息
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/danmu/danms/
     * @param vodQueryBarrageListRequest 分页查询用户下所有弹幕信息请求实体
     * @return 分页查询用户下所有弹幕信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodQueryBarrageListResponse queryBarrageList(VodQueryBarrageListRequest vodQueryBarrageListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_BARRAGE_LIST_URL);
        vodQueryBarrageListRequest.setUserId(VodGlobalConfig.getUserId());
        VodQueryBarrageListResponse vodQueryBarrageListResponse = super.getReturnOne(url, vodQueryBarrageListRequest,
                VodQueryBarrageListResponse.class);
        vodQueryBarrageListResponse.setPageSize(vodQueryBarrageListRequest.getPageSize());
        return vodQueryBarrageListResponse;
    }
}
