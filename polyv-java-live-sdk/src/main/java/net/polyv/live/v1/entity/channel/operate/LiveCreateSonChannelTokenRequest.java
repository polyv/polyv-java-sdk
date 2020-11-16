package net.polyv.live.v1.entity.channel.operate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置子频道单点登陆token请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置子频道单点登陆token请求体")
public class LiveCreateSonChannelTokenRequest extends LiveCommonRequest {
    
    /**
     * 子频道号(不能以数字类型提交，否则可能去掉ID前的00)
     */
    @ApiModelProperty(name = "account", value = "子频道号(不能以数字类型提交，否则可能去掉ID前的00)", required = true)
    @NotNull(message = "属性account不能为空")
    private String account;
    
    /**
     * 唯一的字符串
     */
    @ApiModelProperty(name = "token", value = "唯一的字符串", required = true)
    @NotNull(message = "属性token不能为空")
    private String token;
    
}
