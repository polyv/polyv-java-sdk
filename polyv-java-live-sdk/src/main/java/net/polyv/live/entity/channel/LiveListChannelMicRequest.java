package net.polyv.live.entity.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 分页获取连麦情况使用详情请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页获取连麦情况使用详情请求体")
public class LiveListChannelMicRequest extends LivePageCommonRequest {
    
    /**
     * 频道号，使用英文逗号分开，如：100000,100001
     */
    @ApiModelProperty(name = "channelIds", value = "频道号，使用英文逗号分开，如：100000,100001", required = false)
    private String channelIds;
    
    /**
     * 开始时间，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "startDay", value = "开始时间，格式：yyyy-MM-dd", required = false)
    private String startDay;
    
    /**
     * 结束时间，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "endDay", value = "结束时间，格式：yyyy-MM-dd", required = false)
    private String endDay;

}
