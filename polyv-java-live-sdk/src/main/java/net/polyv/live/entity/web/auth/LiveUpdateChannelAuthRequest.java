package net.polyv.live.entity.web.auth;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;

/**
 * 设置观看条件请求实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置观看条件请求实体")
public class LiveUpdateChannelAuthRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为全局设置", required = false)
    private Integer channelId;
    
    /**
     * 观看条件设置，最多设置2个观看条件，由rank字段进行区分，其中 主要观看条件为1，次要观看条件为2
     */
    @ApiModelProperty(name = "authSettings", value = "观看条件设置", required = true)
    private List<LiveChannelSettingRequest.AuthSetting> authSettings;
    
}
