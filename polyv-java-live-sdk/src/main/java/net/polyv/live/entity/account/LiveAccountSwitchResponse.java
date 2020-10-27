package net.polyv.live.entity.account;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.constant.LiveConstant;

/**
 * 查询功能开关状态接口返回实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询功能开关状态接口返回实体")
public class LiveAccountSwitchResponse {
    
    @ApiModelProperty(name = "channelSwitches", value = "频道开关", required = false)
    private List<ChannelSwitch> channelSwitches;
    
    @Data
@ToString
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("频道开关")
    public static class ChannelSwitch {
        
        /**
         * 开关类型
         * {@link LiveConstant.ChannelSwitch}
         */
        @ApiModelProperty(name = "type", value = "开关类型", required = false)
        private String type;
        
        /**
         * 是否已打开开关
         */
        @ApiModelProperty(name = "enabled", value = "是否已打开开关", required = false)
        private String enabled;
        
    }
    
}
