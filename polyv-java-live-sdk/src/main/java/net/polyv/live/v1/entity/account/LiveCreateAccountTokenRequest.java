package net.polyv.live.v1.entity.account;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置账号单点登录的token请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置账号单点登录的token请求体")
public class LiveCreateAccountTokenRequest extends LiveCommonRequest {
    
    /**
     * 唯一的字符串,请勿过于简单，建议使用16位随机字符串
     */
    @ApiModelProperty(name = "token", value = "唯一的字符串,请勿过于简单，建议使用16位随机字符串", required = true)
    @NotNull(message = "属性token不能为空")
    private String token;
    
}
