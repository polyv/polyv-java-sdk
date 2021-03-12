package net.polyv.vod.v1.entity.subaccount.query;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频分类返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频分类返回实体")
public class VodQueryCategoryResponse {
    
    /**
     * 当前分类
     */
    @ApiModelProperty(name = "category", value = "当前分类", required = false)
    private Category category;
    
    /**
     * 下一级子分类个数
     */
    @ApiModelProperty(name = "subCategoryTotal", value = "下一级子分类个数", required = false)
    private Integer subCategoryTotal;
    
    /**
     * 下一级子分类列表
     */
    @ApiModelProperty(name = "subCategories", value = "下一级子分类列表", required = false)
    private List<Category> subCategories;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("当前分类")
    public static class Category{
        /**
         * 分类id
         */
        @ApiModelProperty(name = "categoryId", value = "分类id", required = false)
        @JSONField(name = "cateId")
        private String categoryId;
        
        /**
         * 分类名称
         */
        @ApiModelProperty(name = "categoryName", value = "分类名称", required = false)
        @JSONField(name = "cateName")
        private String categoryName;
    
        /**
         * 父分类id
         */
        @ApiModelProperty(name = "parentId", value = "父分类id", required = false)
        private String parentId;
    }
}
