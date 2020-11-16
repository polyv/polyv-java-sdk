package net.polyv.live.v1.entity.web.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询直播频道观看条件请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询直播频道观看条件请求体")
public class LiveChannelAuthRequest extends LiveCommonRequest {
    
    /**
     * 频道号,不填获取全局观看条件
     */
    @ApiModelProperty(name = "channelId", value = "频道号,不填获取全局观看条件", required = false)
    private String channelId;
    
}
