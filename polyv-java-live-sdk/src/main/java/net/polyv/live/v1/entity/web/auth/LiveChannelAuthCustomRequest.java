package net.polyv.live.v1.entity.web.auth;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置自定义授权地址请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置自定义授权地址请求实体")
public class LiveChannelAuthCustomRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true, required = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道号，提交后对某频道号设置，不提交则对账号下所有频道号进行设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，提交后对某频道号设置，不提交则对账号下所有频道号进行设置", required = false)
    private String channelId;
    
    /**
     * 自定义授权地址
     */
    @ApiModelProperty(name = "customUri", value = "自定义授权地址", required = true)
    @NotNull(message = "属性customUri不能为空")
    private String customUri;
    
}
