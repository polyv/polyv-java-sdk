package net.polyv.live.v1.entity.web.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 添加频道菜单返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("添加频道菜单返回实体")
public class LiveAddChannelMenuResponse {
    
    /**
     * 菜单ID
     */
    @ApiModelProperty(name = "menuId", value = "菜单ID", required = false)
    private String menuId;
    
    /**
     * 菜单类型。desc：直播介绍；chat：互动聊天；quiz：咨询提问；text：图文菜单；iframe：推广外链；
     */
    @ApiModelProperty(name = "menuType", value = "菜单类型。desc：直播介绍；chat：互动聊天；quiz：咨询提问；text：图文菜单；iframe：推广外链；", required = false)
    private String menuType;
    
    /**
     * 菜单名称
     */
    @ApiModelProperty(name = "name", value = "菜单名称", required = false)
    private String name;
    
    /**
     * 菜单顺序，值越小，越靠前。新添加的菜单默认位于最后。
     */
    @ApiModelProperty(name = "ordered", value = "菜单顺序，值越小，越靠前。新添加的菜单默认位于最后。", required = false)
    private String ordered;
    
    /**
     * 菜单内容。当菜单类型为直播介绍、图文菜单时，该值为菜单的内容。当菜单类型为外链推广时，该值为外链链接地址。
     */
    @ApiModelProperty(name = "content", value = "菜单内容。当菜单类型为直播介绍、图文菜单时，该值为菜单的内容。当菜单类型为外链推广时，该值为外链链接地址。", required = false)
    private String content;
    
    /**
     * 菜单语言类型 zh_CN中文、EN英文
     */
    @ApiModelProperty(name = "lang", value = "菜单语言类型 zh_CN中文、EN英文", required = false)
    private String lang;
    
    
}
