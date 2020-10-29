package net.polyv.live.entity.channel.state;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * TODO 未完成功能
 * 查询频道直播状态请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道直播状态请求实体")
public class LiveChannelStreamStatusRequest extends LiveCommonRequest {
    
    /**
     * @see net.polyv.channel.ChannelTest#testChannelInfo()
     * 可调用获取频道信息接口，获取stream字段的值
     */
    @ApiModelProperty(name = "stream", value = "可调用获取频道信息接口，获取stream字段的值", required = true)
    @NotNull(message = "属性stream不能为空")
    private String stream;
    
}
