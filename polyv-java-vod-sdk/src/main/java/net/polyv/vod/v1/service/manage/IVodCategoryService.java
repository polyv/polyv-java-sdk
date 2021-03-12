package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.category.VodMoveCategoryRequest;

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
}
