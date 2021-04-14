package net.polyv.live.v1.entity.web.auth;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LiveCommonRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;

/**
 * 设置观看条件请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置观看条件请求实体")
public class LiveUpdateChannelAuthRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为全局设置", required = false)
    private String channelId;
    
    /**
     * 观看条件设置，最多设置2个观看条件，由rank字段进行区分，其中 主要观看条件为1，次要观看条件为2
     */
    @ApiModelProperty(name = "authSettings", value = "观看条件设置", required = true)
    @NotNull(message = "属性authSettings不能为空")
    private List<LiveChannelSettingRequest.AuthSetting> authSettings;
    
}
