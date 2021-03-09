package net.polyv.vod.v1.service.subaccount;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.subaccount.edit.VodAddCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.edit.VodDeleteVideoRequest;
import net.polyv.vod.v1.entity.subaccount.edit.VodUpdateCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.edit.VodUpdateVideoCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.edit.VodUpdateVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryCategoryRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryCategoryResponse;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryVideoInfoRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodQueryVideoInfoResponse;
import net.polyv.vod.v1.entity.subaccount.query.VodSearchVideoListRequest;
import net.polyv.vod.v1.entity.subaccount.query.VodSearchVideoListResponse;

/**
 * 子账号相关
 * @author: fangyan
 */
public interface IVodSubAccountService {
    
    /**
     * 搜索视频
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/list-video/
     * @param vodSearchVideoListRequest 搜索视频请求实体
     * @return 搜索视频返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodSearchVideoListResponse searchVideoList(VodSearchVideoListRequest vodSearchVideoListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/get-video-info/
     * @param vodQueryVideoInfoRequest 查询视频信息请求实体
     * @return 查询视频信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodQueryVideoInfoResponse> getVideoInfo(VodQueryVideoInfoRequest vodQueryVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改视频信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-video-info/
     * @param vodUpdateVideoInfoRequest 修改视频信息请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateVideoInfo(VodUpdateVideoInfoRequest vodUpdateVideoInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量修改视频所属分类请求实体
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-video-category/
     * @param vodUpdateVideoCategoryRequest 批量修改视频所属分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateVideoCategory(VodUpdateVideoCategoryRequest vodUpdateVideoCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除视频
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/delete-video/
     * @param vodDeleteVideoRequest 删除视频请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteVideo(VodDeleteVideoRequest vodDeleteVideoRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频分类
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/get-category/
     * @param vodQueryCategoryRequest 查询视频分类请求实体
     * @return 查询视频分类返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodQueryCategoryResponse queryCategory(VodQueryCategoryRequest vodQueryCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 新增视频分类
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/add-category/
     * @param vodAddCategoryRequest 新增视频分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean addCategory(VodAddCategoryRequest vodAddCategoryRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改视频分类信息
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-subaccount/update-category-info/
     * @param vodUpdateCategoryRequest 新增视频分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateCategory(VodUpdateCategoryRequest vodUpdateCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
}
