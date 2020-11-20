package net.polyv.live.v1.entity.web.menu;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置指定菜单id的频道菜单信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置指定菜单id的频道菜单信息请求实体")
public class LiveUpdateChannelMenuInfoRequest extends LiveCommonRequest {
    
    /**
     * 菜单id（互动聊天或咨询提问的菜单ID不允许设置）
     */
    @ApiModelProperty(name = "menuId", value = "菜单id（互动聊天或咨询提问的菜单ID不允许设置）", required = true)
    @NotNull(message = "属性menuId不能为空")
    private String menuId;
    
    /**
     * 菜单的内容
     */
    @ApiModelProperty(name = "content", value = "菜单的内容", required = true)
    @NotNull(message = "属性content不能为空")
    private String content;
    
    /**
     * 菜单语言类型 默认zh_CN中文、EN英文
     */
    @ApiModelProperty(name = "lang", value = "菜单语言类型 默认zh_CN中文、EN英文", required = false)
    private String lang;
    
}
