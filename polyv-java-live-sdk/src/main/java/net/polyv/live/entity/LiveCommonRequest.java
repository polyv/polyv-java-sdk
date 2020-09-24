package net.polyv.live.entity;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.common.entity.CommonReqeust;

/**
 * @author: thomas
 * @date: 2020/9/22
 **/
@Data
@Accessors(chain = true)

public class LiveCommonRequest extends CommonReqeust {
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性appId不能为空")
    private String appId;
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性timestamp不能为空")
    private String timestamp;
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性sign不能为空")
    private String sign;
    @ApiModelProperty(name="requestId",value  ="请求流水号",dataType = "String" ,required = true,example = "1234567" )
    @NotNull(message = "属性requestId不能为空")
    private String requestId;
    
    
}
