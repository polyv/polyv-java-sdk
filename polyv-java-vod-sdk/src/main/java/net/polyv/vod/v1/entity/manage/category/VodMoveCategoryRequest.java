package net.polyv.vod.v1.entity.manage.category;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 移动视频分类接口请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("移动视频分类接口请求实体")
public class VodMoveCategoryRequest extends VodCommonRequest {
    
    /**
     * 需要移动的目录分类id, (id=1，表示默认分类)
     */
    @ApiModelProperty(name = "categoryId", value = "需要移动的目录分类id, (id=1，表示默认分类)", required = true)
    @NotNull(message = "属性categoryId不能为空")
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 移动到的目录分类id, (id=1，表示默认分类)
     */
    @ApiModelProperty(name = "destCategoryId", value = "移动到的目录分类id, (id=1，表示默认分类)", required = true)
    @NotNull(message = "属性destCategoryId不能为空")
    @JSONField(name = "destCataid")
    private String destCategoryId;
    
}
