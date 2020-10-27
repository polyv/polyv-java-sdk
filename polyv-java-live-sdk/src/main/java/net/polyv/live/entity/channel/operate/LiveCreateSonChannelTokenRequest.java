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
 * 设置子频道单点登陆token请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置子频道单点登陆token请求体")
public class LiveCreateSonChannelTokenRequest extends LiveCommonRequest {
    
    /**
     * 子频道ID(不能以数字类型提交，否则可能去掉ID前的00)
     */
    @ApiModelProperty(name = "account", value = "子频道ID(不能以数字类型提交，否则可能去掉ID前的00)", required = true)
    @NotNull(message = "属性account不能为空")
    private String account;
    
    /**
     * 唯一的字符串
     */
    @ApiModelProperty(name = "token", value = "唯一的字符串", required = true)
    @NotNull(message = "属性token不能为空")
    private String token;
    
}
