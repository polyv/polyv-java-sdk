package net.polyv.live.entity.channel.viewdata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 获取频道一定时间范围之内的历史最高并发人数请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获取频道一定时间范围之内的历史最高并发人数请求实体")
public class LiveChannelMaxHistoryConcurrentRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    private String channelId;
    
    /**
     * 开始时间13位毫秒级时间戳
     */
    @ApiModelProperty(name = "startTime", value = "开始时间13位毫秒级时间戳", required = true)
    private Long startTime;
    
    /**
     * 结束时间13位毫秒级时间戳
     */
    @ApiModelProperty(name = "endTime", value = "结束时间13位毫秒级时间戳", required = true)
    private Long endTime;
    
}
