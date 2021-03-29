package net.polyv.vod.v1.service.subaccount.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountAddCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountDeleteCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountDeleteVideoRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateCategoryProfileRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateVideoCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryCategoryResponse;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryVideoInfoResponse;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountSearchVideoListRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountSearchVideoListResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.subaccount.IVodSubAccountService;

/**
 * 子账号相关
 * @author: fangyan
 */
public class VodSubAccountServiceImpl extends VodBaseService implements IVodSubAccountService {
    
    /**
     * 搜索视频
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/list-video/
     * @param vodSubAccountSearchVideoListRequest 搜索视频请求实体
     * @return 搜索视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodSubAccountSearchVideoListResponse searchVideoList(
            VodSubAccountSearchVideoListRequest vodSubAccountSearchVideoListRequest)
            throws IOException, NoSuchAlgorithmException {
        return super.getReturnOne(VodURL.LIST_VIDEO_URL, vodSubAccountSearchVideoListRequest, VodSubAccountSearchVideoListResponse.class);
    }
    
    /**
     * 查询视频信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/get-video-info/
     * @param vodSubAccountQueryVideoInfoRequest 查询视频信息请求实体
     * @return 查询视频信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodSubAccountQueryVideoInfoResponse> getVideoInfo(
            VodSubAccountQueryVideoInfoRequest vodSubAccountQueryVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        return super.getReturnList(VodURL.GET_VIDEO_INFO_URL, vodSubAccountQueryVideoInfoRequest,
                VodSubAccountQueryVideoInfoResponse.class);
    }
    
    /**
     * 修改视频信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-video-info/
     * @param vodSubAccountUpdateVideoInfoRequest 修改视频信息请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateVideoInfo(VodSubAccountUpdateVideoInfoRequest vodSubAccountUpdateVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        super.postFormBodyReturnOne(VodURL.UPDATE_INFO_URL, vodSubAccountUpdateVideoInfoRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 批量修改视频所属分类请求实体
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-video-category/
     * @param vodSubAccountUpdateVideoCategoryRequest 批量修改视频所属分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateVideoCategory(VodSubAccountUpdateVideoCategoryRequest vodSubAccountUpdateVideoCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        super.postFormBodyReturnOne(VodURL.UPDATE_VIDEO_CATEGORY, vodSubAccountUpdateVideoCategoryRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 删除视频
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/delete-video/
     * @param vodSubAccountDeleteVideoRequest 删除视频请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteVideo(VodSubAccountDeleteVideoRequest vodSubAccountDeleteVideoRequest)
            throws IOException, NoSuchAlgorithmException {
        super.postFormBodyReturnOne(VodURL.DELETE_VIDEO, vodSubAccountDeleteVideoRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 查询视频分类
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/get-category/
     * @param vodSubAccountQueryCategoryRequest 查询视频分类请求实体
     * @return 查询视频分类返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodSubAccountQueryCategoryResponse queryCategory(VodSubAccountQueryCategoryRequest vodSubAccountQueryCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        return super.getReturnOne(VodURL.GET_CATEGORY_URL, vodSubAccountQueryCategoryRequest, VodSubAccountQueryCategoryResponse.class);
    }
    
    /**
     * 新增视频分类
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/add-category/
     * @param vodSubAccountAddCategoryRequest 新增视频分类请求实体
     * @return String
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String addCategory(VodSubAccountAddCategoryRequest vodSubAccountAddCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        return super.postFormBodyReturnOne(VodURL.ADD_CATEGORY_URL, vodSubAccountAddCategoryRequest, String.class);
    }
    
    /**
     * 修改视频分类信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-category-info/
     * @param vodSubAccountUpdateCategoryRequest 新增视频分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateCategory(VodSubAccountUpdateCategoryRequest vodSubAccountUpdateCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        super.postFormBodyReturnOne(VodURL.UPDATE_CATEGORY_URL, vodSubAccountUpdateCategoryRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 删除视频分类
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/delete-video-category/
     * @param vodSubAccountDeleteCategoryRequest 删除视频分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteCategory(VodSubAccountDeleteCategoryRequest vodSubAccountDeleteCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        super.postFormBodyReturnOne(VodURL.DELETE_CATEGORY_URL, vodSubAccountDeleteCategoryRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 修改视频分类属性设置
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-category-profile/
     * @param vodSubAccountUpdateCategoryProfileRequest 修改视频分类属性设置请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateCategoryProfile(
            VodSubAccountUpdateCategoryProfileRequest vodSubAccountUpdateCategoryProfileRequest)
            throws IOException, NoSuchAlgorithmException {
        vodSubAccountUpdateCategoryProfileRequest.setUserId(VodGlobalConfig.getUserId());
        super.postFormBodyReturnOne(VodURL.UPDATE_CATEGORY_PROFILE_URL, vodSubAccountUpdateCategoryProfileRequest, String.class);
        return Boolean.TRUE;
    }
    
}
