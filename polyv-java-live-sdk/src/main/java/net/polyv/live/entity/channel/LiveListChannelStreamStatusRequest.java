package net.polyv.live.entity.channel;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 批量查询频道直播流状态请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("批量查询频道直播流状态请求实体")
public class LiveListChannelStreamStatusRequest extends LiveCommonRequest {
    
    /**
     * 用逗号隔开的频道ID，如：10000,100001 最多20个
     */
    @ApiModelProperty(name = "channelIds", value = "用逗号隔开的频道ID，如：10000,100001 最多20个", required = true)
    @NotNull(message = "channelIds不能为空")
    private String channelIds;
    
}
