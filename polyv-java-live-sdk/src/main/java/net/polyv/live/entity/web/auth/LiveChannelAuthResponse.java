package net.polyv.live.entity.web.auth;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.channel.operate.LiveChannelSettingRequest;

/**
 * 查询直播频道观看条件返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询直播频道观看条件返回实体")
public class LiveChannelAuthResponse {
    
    /**
     * 观看条件，最多2个观看条件，由rank字段进行区分，其中 主要观看条件为1，次要观看条件为2
     */
    @ApiModelProperty(name = "authSettings", value = "观看条件", required = true)
    private List<LiveChannelSettingRequest.AuthSetting> authSettings;

}
