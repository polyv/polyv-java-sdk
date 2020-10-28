package net.polyv.live.entity.channel.operate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询授权和连麦的token请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询授权和连麦的token请求体")
public class LiveChannelAuthTokenRequest extends LiveCommonRequest {
    
    /**
     * 观看者用户ID
     */
    @ApiModelProperty(name = "userId", value = "观看者用户ID", required = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道号，请留意，如果该参数为空，会对该用户所有的频道进行修改
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
