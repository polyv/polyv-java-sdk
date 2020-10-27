package net.polyv.live.entity.account;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置直播状态回调通知url请求实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置直播状态回调通知url请求实体")
public class LiveAccountStreamCallbackRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头
     */
    @ApiModelProperty(name = "url", value = "回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头", required = false)
    private String url;

}
