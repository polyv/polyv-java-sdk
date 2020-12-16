package net.polyv.live.v1.entity.web.menu;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置提问功能显示开关请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置提问功能显示开关请求实体")
public class LiveSetConsultingEnabledRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 咨询提问开关 Y:开启，N：关闭
     */
    @ApiModelProperty(name = "enabled", value = "咨询提问开关 Y:开启，N：关闭", required = true)
    @NotNull(message = "属性enabled不能为空")
    private String enabled;
    
}
