package net.polyv.live.v1.entity.channel.operate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道密码请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道密码请求体")
public class LiveChannelPasswordSettingRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道号，请留意，如果该参数为空，会对该用户所有的频道进行修改
     */
    @ApiModelProperty(name = "channelId", value = "频道号，请留意，如果该参数为空，会对该用户所有的频道进行修改", required = false)
    private String channelId;
    
    /**
     * 修改的密码  
     */
    @ApiModelProperty(name = "passwd", value = "修改的密码", required = true)
    @NotNull(message = "属性passwd不能为空")
    private String passwd;
    
}
