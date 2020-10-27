package net.polyv.live.entity.channel.state;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道直播状态返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道直播状态返回实体")
public class LiveChannelStreamStatusResponse {
    
    /**
     * 频道号，整型
     */
    @ApiModelProperty(name = "channelId", value = "频道号，整型", required = false)
    private Integer channelId;
    
    
    /**
     * 频道的直播状态，字符串，值包括：live end
     */
    @ApiModelProperty(name = "status", value = "频道的直播状态，字符串，值包括：live end", required = false)
    private String status;
    
}
