package net.polyv.live.v1.entity.channel.state;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道直播状态返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道直播状态返回实体")
public class LiveChannelStreamStatusResponse {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private String channelId;
    
    
    /**
     * 频道的直播状态，字符串，值包括：live end
     */
    @ApiModelProperty(name = "status", value = "频道的直播状态，字符串，值包括：live end", required = false)
    private String status;
    
}
