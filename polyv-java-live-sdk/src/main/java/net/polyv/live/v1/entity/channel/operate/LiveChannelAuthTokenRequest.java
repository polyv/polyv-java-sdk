package net.polyv.live.v1.entity.channel.operate;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询授权和连麦的token请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询授权和连麦的token请求体")
public class LiveChannelAuthTokenRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 角色，值有：teacher admin guest assistant viewer等
     */
    @ApiModelProperty(name = "role", value = "角色，值有：teacher admin guest assistant viewer等", required = true)
    @NotNull(message = "属性role不能为空")
    private String role;
    
    /**
     * 观看来源,可以有web,client,app等
     */
    @ApiModelProperty(name = "origin", value = "观看来源,可以有web,client,app等", required = false)
    private String origin;

}
