package net.polyv.live.entity.channel;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置频道单点登陆token请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("设置频道单点登陆token请求体")
public class LiveCreateChannelTokenRequest extends LiveCommonRequest {
    
    /**
     * 频道ID，请留意，如果该参数为空，会对该用户所有的频道进行修改
     */
    @ApiModelProperty(name = "channelId", value = "频道ID", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    /**
     * 唯一的字符串
     */
    @ApiModelProperty(name = "token", value = "唯一的字符串", required = true)
    @NotNull(message = "token不能为空")
    private String token;
    
}
