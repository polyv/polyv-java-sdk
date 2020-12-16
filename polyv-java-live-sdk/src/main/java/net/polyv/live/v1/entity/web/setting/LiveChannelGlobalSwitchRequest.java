package net.polyv.live.v1.entity.web.setting;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道默认项开关请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道默认项开关请求实体")
public class LiveChannelGlobalSwitchRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 功能类型
     * {@link LiveConstant.GlobalEnabledType}
     */
    @ApiModelProperty(name = "globalEnabledType", value = "功能类型", required = true)
    @NotNull(message = "属性globalEnabledType不能为空")
    private String globalEnabledType;
    
    /**
     * Y或N，Y开启，N关闭
     */
    @ApiModelProperty(name = "enabled", value = "Y或N，Y开启，N关闭", required = true)
    @NotNull(message = "属性enabled不能为空")
    private String enabled;

}
