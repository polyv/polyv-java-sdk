package net.polyv.live.entity.web.auth;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置授权观看类型请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置授权观看类型请求体")
public class LiveChannelAuthTypeRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    /**
     * 观看条件类型,默认取值为none(关闭观看条件)
     */
    @ApiModelProperty(name = "authType", value = "观看条件类型,默认取值为none(关闭观看条件)", required = true)
    @NotNull(message = "属性authType不能为空")
    private String authType;
    
}
