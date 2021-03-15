package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.category.VodCreateCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodCreateCategoryVO;
import net.polyv.vod.v1.entity.manage.category.VodDeleteCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodGetCategoryRequest;
import net.polyv.vod.v1.entity.manage.category.VodGetCategoryResponse;
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
    
    /**
     * 新建视频分类
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/addcata/
     * @param vodCreateCategoryRequest 新建视频分类请求实体
     * @return String
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String createCategory(VodCreateCategoryRequest vodCreateCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_CREATE_CATEGORY_URL);
        VodCreateCategoryVO vodCreateCategoryVO = super.postFormBodyReturnOne(url, vodCreateCategoryRequest,
                VodCreateCategoryVO.class);
        if (vodCreateCategoryVO != null && vodCreateCategoryVO.getCategoryId() != null) {
            return vodCreateCategoryVO.getCategoryId();
        }
        return null;
    }
    
    /**
     * 删除分类
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/deletecata/
     * @param vodDeleteCategoryRequest 删除分类请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteCategory(VodDeleteCategoryRequest vodDeleteCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_DELETE_CATEGORY_URL);
        return super.postFormBodyReturnOne(url, vodDeleteCategoryRequest, Boolean.class);
    }
    
    /**
     * 获取视频分类目录
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-vmanage/v-api-vmanage-taxonomy/catajson/
     * @param vodGetCategoryRequest 获取视频分类目录请求实体
     * @return 获取视频分类目录返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodGetCategoryResponse> getCategory(VodGetCategoryRequest vodGetCategoryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_CATEGORY_URL);
        return super.postFormBodyReturnList(url, vodGetCategoryRequest, VodGetCategoryResponse.class);
    }
}
