package net.polyv.live.v1.entity.web.menu;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置自定义菜单直播介绍请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置自定义菜单直播介绍请求实体")
public class LiveUpdateChannelMenuRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道号，提交后对某频道号设置，不提交则对账号下所有频道号进行设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，提交后对某频道号设置，不提交则对账号下所有频道号进行设置", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 直播介绍的内容（此处可以填html页面的相关内容，如增加图片、增加文字样式等）
     */
    @ApiModelProperty(name = "content", value = "直播介绍的内容（此处可以填html页面的相关内容，如增加图片、增加文字样式等）", required = true)
    @NotNull(message = "属性content不能为空")
    private String content;
    
    /**
     * 菜单类型，目前仅支持取值为desc
     */
    @ApiModelProperty(name = "menuType", value = "菜单类型，目前仅支持取值为desc", required = true)
    @NotNull(message = "属性menuType不能为空")
    private String menuType;

}
