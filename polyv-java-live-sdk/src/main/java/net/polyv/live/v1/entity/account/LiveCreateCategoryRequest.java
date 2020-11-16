package net.polyv.live.v1.entity.account;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 创建账号下直播分类请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("创建账号下直播分类请求实体")
public class LiveCreateCategoryRequest extends LiveCommonRequest {
    
    /**
     * 频道分类名称
     */
    @ApiModelProperty(name = "categoryName", value = "频道分类名称", required = true)
    @NotNull(message = "属性categoryName不能为空")
    private String categoryName;
    
}
