package net.polyv.vod.v1.service.subaccount;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.subaccount.VodSubAccountAddCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountDeleteCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountDeleteVideoRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountGetPlaySafeTokenRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountGetPlaySafeTokenResponse;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryCategoryResponse;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountQueryVideoInfoResponse;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountSearchVideoListRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountSearchVideoListResponse;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateCategoryProfileRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateVideoCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountUpdateVideoInfoRequest;

/**
 * 子账号相关
 * @author: fangyan
 */
public interface IVodSubAccountService {
    
    /**
     * 搜索视频
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/list-video/
     * @param vodSubAccountSearchVideoListRequest 搜索视频请求实体
     * @return 搜索视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodSubAccountSearchVideoListResponse searchVideoList(VodSubAccountSearchVideoListRequest vodSubAccountSearchVideoListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/get-video-info/
     * @param vodSubAccountQueryVideoInfoRequest 查询视频信息请求实体
     * @return 查询视频信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodSubAccountQueryVideoInfoResponse> getVideoInfo(VodSubAccountQueryVideoInfoRequest vodSubAccountQueryVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改视频信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-video-info/
     * @param vodSubAccountUpdateVideoInfoRequest 修改视频信息请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateVideoInfo(VodSubAccountUpdateVideoInfoRequest vodSubAccountUpdateVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量修改视频所属分类请求实体
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-video-category/
     * @param vodSubAccountUpdateVideoCategoryRequest 批量修改视频所属分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateVideoCategory(VodSubAccountUpdateVideoCategoryRequest vodSubAccountUpdateVideoCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除视频
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/delete-video/
     * @param vodSubAccountDeleteVideoRequest 删除视频请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteVideo(VodSubAccountDeleteVideoRequest vodSubAccountDeleteVideoRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频分类
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/get-category/
     * @param vodSubAccountQueryCategoryRequest 查询视频分类请求实体
     * @return 查询视频分类返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodSubAccountQueryCategoryResponse queryCategory(VodSubAccountQueryCategoryRequest vodSubAccountQueryCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 新增视频分类
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/add-category/
     * @param vodSubAccountAddCategoryRequest 新增视频分类请求实体
     * @return String
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String addCategory(VodSubAccountAddCategoryRequest vodSubAccountAddCategoryRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改视频分类信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-category-info/
     * @param vodSubAccountUpdateCategoryRequest 新增视频分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateCategory(VodSubAccountUpdateCategoryRequest vodSubAccountUpdateCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除视频分类
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/delete-video-category/
     * @param vodSubAccountDeleteCategoryRequest 删除视频分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteCategory(VodSubAccountDeleteCategoryRequest vodSubAccountDeleteCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改视频分类属性设置
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-category-profile/
     * @param vodSubAccountUpdateCategoryProfileRequest 修改视频分类属性设置请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateCategoryProfile(VodSubAccountUpdateCategoryProfileRequest vodSubAccountUpdateCategoryProfileRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取PlaySafeToken
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-subaccount/create-playsafe-token-v2/
     * @param vodSubAccountGetPlaySafeTokenRequest 获取PlaySafeToken请求实体
     * @return 获取PlaySafeToken返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodSubAccountGetPlaySafeTokenResponse getPlaySafeToken(VodSubAccountGetPlaySafeTokenRequest vodSubAccountGetPlaySafeTokenRequest)
            throws IOException, NoSuchAlgorithmException ;
}
