package net.polyv.live.v1.entity.web.menu;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 删除频道菜单请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("删除频道菜单请求实体")
public class LiveDeleteChannelMenuRequest extends LiveCommonRequest {
    
    /**
     * 菜单id，指定多个以英文逗号,分隔
     */
    @ApiModelProperty(name = "menuIds", value = "菜单id，指定多个以英文逗号,分隔", required = true)
    @NotNull(message = "属性menuIds不能为空")
    private String menuIds;
    
}
