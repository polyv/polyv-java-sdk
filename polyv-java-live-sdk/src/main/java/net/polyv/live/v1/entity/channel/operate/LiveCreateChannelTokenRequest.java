package net.polyv.live.v1.entity.channel.operate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道单点登陆token请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道单点登陆token请求体")
public class LiveCreateChannelTokenRequest extends LiveCommonRequest {
    
    /**
     * 频道号，请留意，如果该参数为空，会对该用户所有的频道进行修改
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 唯一的字符串
     */
    @ApiModelProperty(name = "token", value = "唯一的字符串", required = true)
    @NotNull(message = "属性token不能为空")
    private String token;
    
}
