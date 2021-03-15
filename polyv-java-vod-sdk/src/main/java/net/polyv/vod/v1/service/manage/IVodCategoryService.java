package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.manage.category.VodCreateCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodDeleteCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodGetCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodGetCategoryResponse;
import net.polyv.vod.v1.entity.manage.category.VodGetCategorySizeRequest;
import net.polyv.vod.v1.entity.manage.category.VodMoveCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodMoveVideoRequest;
import net.polyv.vod.v1.entity.manage.category.VodUpdateCategoryProfileRequest;
import net.polyv.vod.v1.entity.manage.category.VodUpdateCategoryNameRequest;

/**
 * 视频分类
 * @author: fangyan
 */
public interface IVodCategoryService {
    
    /**
     * 移动视频分类接口
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/change/
     * @param vodMoveCategoryRequest 移动视频分类接口请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean moveCategory(VodMoveCategoryRequest vodMoveCategoryRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置分类属性
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/setting-category
     * -properties/
     * @param vodUpdateCategoryProfileRequest 设置分类属性请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateCategoryProfile(VodUpdateCategoryProfileRequest vodUpdateCategoryProfileRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 新建视频分类
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/addcata/
     * @param vodCreateCategoryRequest 新建视频分类请求实体
     * @return String
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createCategory(VodCreateCategoryRequest vodCreateCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除分类
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/deletecata/
     * @param vodDeleteCategoryRequest 删除分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteCategory(VodDeleteCategoryRequest vodDeleteCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取视频分类目录
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/catajson/
     * @param vodGetCategoryRequest 获取视频分类目录请求实体
     * @return 获取视频分类目录返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodGetCategoryResponse> getCategory(VodGetCategoryRequest vodGetCategoryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 修改分类名称
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/updatecata/
     * @param vodUpdateCategoryNameRequest 修改分类名称请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateCategoryName(VodUpdateCategoryNameRequest vodUpdateCategoryNameRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 移动视频到指定分类
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/changecata/
     * @param vodMoveVideoRequest 移动视频到指定分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean moveVideo(VodMoveVideoRequest vodMoveVideoRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 通过cataid获取视频目录空间
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/getsize/
     * @param vodGetCategorySizeRequest 通过cataid获取视频目录空间请求实体
     * @return Long
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Long getCategorySize(VodGetCategorySizeRequest vodGetCategorySizeRequest)
            throws IOException, NoSuchAlgorithmException;
}
