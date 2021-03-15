package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.category.VodCreateCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodMoveCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodUpdateCategoryProfileRequest;

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
}
