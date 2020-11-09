package net.polyv.live.entity.account;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 修改直播频道分类名称请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("修改直播频道分类名称请求实体")
public class LiveUpdateCategoryRequest extends LiveCommonRequest {
    
    /**
     * 分类id
     */
    @ApiModelProperty(name = "categoryId", value = "分类id", required = true)
    @NotNull(message = "属性categoryId不能为空")
    private Integer categoryId;
    
    /**
     * 分类名称
     */
    @ApiModelProperty(name = "categoryName", value = "分类名称", required = true)
    @NotNull(message = "属性categoryName不能为空")
    private String categoryName;

}
