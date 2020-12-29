package net.polyv.live.v1.entity.interact;

import java.util.Date;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 查询频道问卷列表请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道问卷列表请求实体")
public class LiveQuestionnaireListRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 查询的记录的开始时间
     */
    @ApiModelProperty(name = "startTime", value = "查询的记录的开始时间", required = false)
    private Date startTime;
    
    /**
     * 查询的记录的结束时间
     */
    @ApiModelProperty(name = "endTime", value = "查询的记录的结束时间", required = false)
    private Date endTime;
    
    
    
}
