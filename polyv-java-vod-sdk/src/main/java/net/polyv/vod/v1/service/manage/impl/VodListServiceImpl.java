package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.list.VodGetByUploaderRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetByUploaderResponse;
import net.polyv.vod.v1.entity.manage.list.VodGetDelListRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetDelListResponse;
import net.polyv.vod.v1.entity.manage.list.VodGetHotListRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetHotListResponse;
import net.polyv.vod.v1.entity.manage.list.VodGetIllegalListRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetIllegalListResponse;
import net.polyv.vod.v1.entity.manage.list.VodGetNewListRequest;
import net.polyv.vod.v1.entity.manage.list.VodGetNewListResponse;
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
    
    /**
     * 获取最新视频/全部视频列表
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/get-new-list/
     * @param vodGetNewListRequest 获取最新视频/全部视频列表请求实体
     * @return 获取最新视频/全部视频列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetNewListResponse> getNewList(VodGetNewListRequest vodGetNewListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_NEW_LIST_URL);
        vodGetNewListRequest.setUserId(VodGlobalConfig.getUserId());
        return super.postFormBodyReturnList(url, vodGetNewListRequest, VodGetNewListResponse.class);
    }
    
    /**
     * 获取最热视频列表
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/get-host-list/
     * @param vodGetHotListRequest 获取最热视频列表请求实体
     * @return 获取最热视频列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetHotListResponse> getHotList(VodGetHotListRequest vodGetHotListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_HOT_LIST_URL);
        vodGetHotListRequest.setUserId(VodGlobalConfig.getUserId());
        return super.postFormBodyReturnList(url, vodGetHotListRequest, VodGetHotListResponse.class);
    }
    
    /**
     * 获取视频回收站列表
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/get-del-list/
     * @param vodGetDelListRequest 获取视频回收站列表请求实体
     * @return 获取视频回收站列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetDelListResponse> getDelList(VodGetDelListRequest vodGetDelListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_DEL_LIST_URL);
        vodGetDelListRequest.setUserId(VodGlobalConfig.getUserId());
        return super.postFormBodyReturnList(url, vodGetDelListRequest, VodGetDelListResponse.class);
    }
    
    /**
     * 获取不通过视频列表
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/getnotpasslist/
     * @param vodGetIllegalListRequest 获取不通过视频列表请求实体
     * @return 获取不通过视频列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetIllegalListResponse> getIllegalList(VodGetIllegalListRequest vodGetIllegalListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_ILLEGAL_LIST_URL);
        vodGetIllegalListRequest.setUserId(VodGlobalConfig.getUserId());
        return super.postFormBodyReturnList(url, vodGetIllegalListRequest, VodGetIllegalListResponse.class);
    }
}
