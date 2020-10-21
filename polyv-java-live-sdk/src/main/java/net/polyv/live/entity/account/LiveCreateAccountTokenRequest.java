package net.polyv.live.entity.account;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置账号单点登录的token请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置账号单点登录的token请求体")
public class LiveCreateAccountTokenRequest extends LiveCommonRequest {
    
    /**
     * 唯一的字符串,请勿过于简单，建议使用16位随机字符串
     */
    @ApiModelProperty(name = "token", value = "唯一的字符串,请勿过于简单，建议使用16位随机字符串", required = true)
    @NotNull(message = "token不能为空")
    private String token;

}
