package net.polyv.live.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道问卷结果请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道问卷结果请求实体")
public class LiveQuestionnaireResultRequest extends LiveCommonRequest {
   
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 开始时间，格式：2018-12-10
     */
    @ApiModelProperty(name = "startDate", value = "开始时间，格式：yyyy-MM-dd 如 2018-12-10", required = false)
    private String startDate;
    
    /**
     * 结束时间，格式：2018-12-10
     */
    @ApiModelProperty(name = "endDate", value = "结束时间，格式：yyyy-MM-dd 如 2018-12-10 ", required = false)
    private String endDate;
    
    /**
     * 问卷ID，和startDate/endDate同时提交时，会优先以该参数查询
     */
    @ApiModelProperty(name = "questionnaireId", value = "问卷ID，和 startDate / endDate 同时提交时，startDate / endDate 无效，优先以questionnaireId为查询条件", required = false)
    private String questionnaireId;
}
