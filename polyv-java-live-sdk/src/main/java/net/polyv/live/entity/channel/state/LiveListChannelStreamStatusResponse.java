package net.polyv.live.entity.channel.state;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.channel.state.LiveChannelStreamStatusResponse;

/**
 * 批量查询频道直播流状态返回实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("批量查询频道直播流状态返回实体")
public class LiveListChannelStreamStatusResponse {
    
    @ApiModelProperty(name = "channelInfo", value = "频道信息", required = false)
    private List<LiveChannelStreamStatusResponse> channelInfo;
    
}