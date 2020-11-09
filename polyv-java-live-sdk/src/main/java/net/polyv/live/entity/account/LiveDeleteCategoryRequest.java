package net.polyv.live.entity.account;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 删除直播频道分类请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("删除直播频道分类请求实体")
public class LiveDeleteCategoryRequest extends LiveCommonRequest {
    
    /**
     * 分类id
     */
    @ApiModelProperty(name = "categoryId", value = "分类id", required = true)
    @NotNull(message = "属性categoryId不能为空")
    private Integer categoryId;

}
