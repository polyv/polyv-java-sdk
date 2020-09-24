package net.polyv.live.entity;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "属性appId不能为空")
    private String appId;
    @NotNull(message = "属性timestamp不能为空")
    private String timestamp;
    @NotNull(message = "属性sign不能为空")
    private String sign;
    @NotNull(message = "属性requestId不能为空")
    private String requestId;
    
    
}
