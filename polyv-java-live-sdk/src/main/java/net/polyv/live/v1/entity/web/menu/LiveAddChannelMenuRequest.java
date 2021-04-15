package net.polyv.live.v1.entity.web.menu;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 添加频道菜单请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("添加频道菜单请求实体")
public class LiveAddChannelMenuRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 菜单名称
     */
    @ApiModelProperty(name = "name", value = "菜单名称", required = true)
    @NotNull(message = "属性name不能为空")
    private String name;
    
    /**
     * 菜单类型。desc：直播介绍；chat：互动聊天；quiz：咨询提问；text：图文菜单；iframe：推广外链；
     */
    @ApiModelProperty(name = "type", value = "菜单类型。desc：直播介绍；chat：互动聊天；quiz：咨询提问；text：图文菜单；iframe：推广外链；", required = true)
    @NotNull(message = "属性type不能为空")
    private String type;
    
    /**
     * 菜单内容。当菜单类型为直播介绍、图文菜单时，该值为菜单的内容。当菜单类型为外链推广时，该值为外链链接地址。
     */
    @ApiModelProperty(name = "content", value = "菜单内容。当菜单类型为直播介绍、图文菜单时，该值为菜单的内容。当菜单类型为外链推广时，该值为外链链接地址。", required = true)
    @NotNull(message = "属性content不能为空")
    private String content;
    
    /**
     * 菜单语言类型。默认zh_CN中文、EN英文
     */
    @ApiModelProperty(name = "lang", value = "菜单语言类型，默认zh_CN<br/>zh_CN：中文<br/>EN：英文", required = false)
    private String lang;
    
}
