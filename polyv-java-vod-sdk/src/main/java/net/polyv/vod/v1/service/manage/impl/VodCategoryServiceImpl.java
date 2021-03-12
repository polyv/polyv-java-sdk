package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.category.VodMoveCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodUpdateCategoryProfileRequest;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodCategoryService;

/**
 * 视频分类
 * @author: fangyan
 */
public class VodCategoryServiceImpl extends VodBaseService implements IVodCategoryService {
    
    /**
     * 移动视频分类接口
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/change/
     * @param vodMoveCategoryRequest 移动视频分类接口请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean moveCategory(VodMoveCategoryRequest vodMoveCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.MOVE_CATEGORY_URL);
        return super.postFormBodyReturnOne(url, vodMoveCategoryRequest, Boolean.class);
    }
    
    /**
     * 设置分类属性
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/setting-category
     * -properties/
     * @param vodUpdateCategoryProfileRequest 设置分类属性请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateCategoryProfile(VodUpdateCategoryProfileRequest vodUpdateCategoryProfileRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_UPDATE_CATEGORY_PROFILE_URL);
        return super.postFormBodyReturnOne(url, vodUpdateCategoryProfileRequest, Boolean.class);
    }
}
