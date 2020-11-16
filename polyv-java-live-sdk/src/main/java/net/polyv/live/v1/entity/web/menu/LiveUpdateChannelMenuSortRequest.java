package net.polyv.live.v1.entity.web.menu;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道菜单排序请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道菜单排序请求实体")
public class LiveUpdateChannelMenuSortRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 频道菜单ID列表，必须是完整的列表（不能多也不能少），表示按该顺序排列菜单
     */
    @ApiModelProperty(name = "menuIds", value = "频道菜单ID列表，必须是完整的列表（不能多也不能少），表示按该顺序排列菜单", required = true)
    @NotNull(message = "属性menuIds不能为空")
    private String menuIds;
    
    /**
     * 菜单语言类型 默认zh_CN中文、EN英文
     */
    @ApiModelProperty(name = "lang", value = "菜单语言类型 默认zh_CN中文、EN英文", required = false)
    private String lang;
    
}
