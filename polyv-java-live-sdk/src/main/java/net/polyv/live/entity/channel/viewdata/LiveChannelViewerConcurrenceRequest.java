package net.polyv.live.entity.channel.viewdata;

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
 * 查询频道的历史并发人数请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道的历史并发人数请求实体")
public class LiveChannelViewerConcurrenceRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    private String channelId;
    
    /**
     * 开始日期格式，yyyy-MM-dd,开始日期和结束日期的时间跨度：最多查两个月内的数据
     */
    @ApiModelProperty(name = "startDate", value = "开始日期格式，yyyy-MM-dd,开始日期和结束日期的时间跨度：最多查两个月内的数据", required = true)
    @NotNull(message = "属性startDate不能为空")
    private String startDate;
    
    /**
     * 结束日期格式，yyyy-MM-dd,开始日期和结束日期的时间跨度：最多查两个月内的数据
     */
    @ApiModelProperty(name = "endDate", value = "结束日期格式，yyyy-MM-dd，开始日期和结束日期的时间跨度：最多查两个月内的数据", required = true)
    @NotNull(message = "属性endDate不能为空")
    private String endDate;

}
