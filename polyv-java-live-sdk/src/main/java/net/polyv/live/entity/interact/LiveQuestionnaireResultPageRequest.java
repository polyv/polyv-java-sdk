package net.polyv.live.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 分页查询频道问卷结果请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页查询频道问卷结果请求实体")
public class LiveQuestionnaireResultPageRequest extends LivePageCommonRequest {
   
    
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
    @ApiModelProperty(name = "endDate", value = "结束时间，格式：yyyy-MM-dd 如 2018-12-10", required = false)
    private String endDate;
    
}
