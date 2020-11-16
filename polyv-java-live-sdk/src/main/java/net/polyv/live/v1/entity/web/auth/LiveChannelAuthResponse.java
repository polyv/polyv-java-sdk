package net.polyv.live.v1.entity.web.auth;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;

/**
 * 查询直播频道观看条件返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询直播频道观看条件返回实体")
public class LiveChannelAuthResponse {
    
    /**
     * 观看条件，最多2个观看条件，由rank字段进行区分，其中 主要观看条件为1，次要观看条件为2
     */
    @ApiModelProperty(name = "authSettings", value = "观看条件", required = true)
    private List<LiveChannelSettingRequest.AuthSetting> authSettings;

}
