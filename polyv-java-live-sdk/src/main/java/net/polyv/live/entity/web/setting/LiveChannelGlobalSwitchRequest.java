package net.polyv.live.entity.web.setting;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置频道默认项开关请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置频道默认项开关请求实体")
public class LiveChannelGlobalSwitchRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "channelId不能为空")
    private Integer channelId;
    
    /**
     * 功能类型
     * {@link LiveConstant.GlobalEnabledType}
     */
    @ApiModelProperty(name = "globalEnabledType", value = "功能类型", required = true)
    @NotNull(message = "globalEnabledType不能为空")
    private String globalEnabledType;
    
    /**
     * Y或N，Y开启，N关闭
     */
    @ApiModelProperty(name = "enabled", value = "Y或N，Y开启，N关闭", required = true)
    @NotNull(message = "enabled不能为空")
    private String enabled;

}