package net.polyv.live.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询频道答题卡答题结果
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道答题卡答题结果请求实体")
public class LiveQuestionAnswerRecordRequest extends LivePageCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 查询的开始时间
     */
    @ApiModelProperty(name = "startDate", value = "查询的开始时间，格式要求：yyyy-MM-dd", required = false)
    private String startDate;
    
    /**
     * 查询的结束时间
     */
    @ApiModelProperty(name = "endDate", value = "查询的结束时间，格式要求：yyyy-MM-dd", required = false)
    private String endDate;
    
    
}
