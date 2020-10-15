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
 * 查询频道直播状态请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询频道直播状态请求实体")
public class LiveChannelStreamStatusRequest extends LiveCommonRequest {
    
    /**
     * @see net.polyv.channel.ChannelTest#testChannelInfo()
     * 可调用获取频道信息接口，获取stream字段的值
     */
    @ApiModelProperty(name = "stream", value = "可调用获取频道信息接口，获取stream字段的值", required = true)
    @NotNull(message = "stream不能为空")
    private String stream;
    
}
