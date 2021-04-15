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
 * 修改分类名称请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("修改分类名称请求实体")
public class VodUpdateCategoryNameRequest extends VodCommonRequest {
    
    /**
     * 分类id
     */
    @ApiModelProperty(name = "categoryId", value = "分类id", required = true)
    @NotNull(message = "属性categoryId不能为空")
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 修改后的分类名称
     */
    @ApiModelProperty(name = "categoryName", value = "修改后的分类名称", required = true)
    @NotNull(message = "属性categoryName不能为空")
    @JSONField(name = "cataname")
    private String categoryName;
}
