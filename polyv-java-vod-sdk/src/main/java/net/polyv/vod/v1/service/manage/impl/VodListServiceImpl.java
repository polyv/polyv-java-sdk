package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.list.VodGetByUploaderRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetByUploaderResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodListService;

/**
 * 视频列表
 * @author: fangyan
 */
public class VodListServiceImpl extends VodBaseService implements IVodListService {
    
    /**
     * 获取某分类下某子账号的视频列表
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/get-by-uploader/
     * @param vodGetByUploaderRequest 获取某分类下某子账号的视频列表请求实体
     * @return 获取某分类下某子账号的视频列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetByUploaderResponse getByUploader(VodGetByUploaderRequest vodGetByUploaderRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_BY_UPLOADER_URL);
        return super.getReturnOne(url, vodGetByUploaderRequest, VodGetByUploaderResponse.class);
    }
}
