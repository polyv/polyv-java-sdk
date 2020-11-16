package net.polyv.live.v1.entity.channel.viewdata;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询频道的历史并发人数请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    
    /**
     * 结束日期格式，yyyy-MM-dd,开始日期和结束日期的时间跨度：最多查两个月内的数据
     */
    @ApiModelProperty(name = "endDate", value = "结束日期格式，yyyy-MM-dd，开始日期和结束日期的时间跨度：最多查两个月内的数据", required = true)
    @NotNull(message = "属性endDate不能为空")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

}
