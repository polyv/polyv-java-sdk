package net.polyv.live.v1.entity.web.auth;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置授权观看类型请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置授权观看类型请求体")
public class LiveChannelAuthTypeRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 观看条件类型,默认取值为none(关闭观看条件)
     */
    @ApiModelProperty(name = "authType", value = "观看条件类型,默认取值为none(关闭观看条件)", required = true)
    @NotNull(message = "属性authType不能为空")
    private String authType;
    
}
