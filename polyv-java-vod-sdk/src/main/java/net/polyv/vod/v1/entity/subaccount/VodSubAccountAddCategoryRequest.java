package net.polyv.vod.v1.entity.subaccount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodSubCommonRequest;

/**
 * 新增视频分类请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("新增视频分类请求实体")
public class VodSubAccountAddCategoryRequest extends VodSubCommonRequest {
    
    /**
     * 分类名
     */
    @ApiModelProperty(name = "name", value = "分类名", required = true)
    @NotNull(message = "属性name不能为空")
    private String name;
    
    /**
     * 父分类id, 默认位1, 放在根目录下
     */
    @ApiModelProperty(name = "parentId", value = "父分类id, 默认位1, 放在根目录下", required = false)
    private String parentId;
    
}
