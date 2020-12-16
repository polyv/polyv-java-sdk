package net.polyv.live.v1.entity.interact;

import java.util.Date;

import net.polyv.common.v1.validator.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 查询频道答题卡答题结果
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    
    /**
     * 查询的结束时间
     */
    @ApiModelProperty(name = "endDate", value = "查询的结束时间，格式要求：yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    
    
}
