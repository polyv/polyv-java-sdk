package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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

/**
 * 视频列表
 * @author: fangyan
 */
public interface IVodListService {
    
    /**
     * 获取某分类下某子账号的视频列表
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/get-by-uploader/
     * @param vodGetByUploaderRequest 获取某分类下某子账号的视频列表请求实体
     * @return 获取某分类下某子账号的视频列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetByUploaderResponse getByUploader(VodGetByUploaderRequest vodGetByUploaderRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取最新视频/全部视频列表
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/get-new-list/
     * @param vodGetNewListRequest 获取最新视频/全部视频列表请求实体
     * @return 获取最新视频/全部视频列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetNewListResponse getNewList(VodGetNewListRequest vodGetNewListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取最热视频列表
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/get-host-list/
     * @param vodGetHotListRequest 获取最热视频列表请求实体
     * @return 获取最热视频列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetHotListResponse getHotList(VodGetHotListRequest vodGetHotListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取视频回收站列表
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/get-del-list/
     * @param vodGetDelListRequest 获取视频回收站列表请求实体
     * @return 获取视频回收站列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetDelListResponse getDelList(VodGetDelListRequest vodGetDelListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取不通过视频列表
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-list/getnotpasslist/
     * @param vodGetIllegalListRequest 获取不通过视频列表请求实体
     * @return 获取不通过视频列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetIllegalListResponse getIllegalList(VodGetIllegalListRequest vodGetIllegalListRequest)
            throws IOException, NoSuchAlgorithmException;
}
