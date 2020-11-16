package net.polyv.live.v1.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询频道问卷详情请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道问卷详情请求实体")
public class LiveQuestionnaireDetailRequest extends LiveCommonRequest {
    
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 问卷id
     */
    @ApiModelProperty(name = "questionnaireId", value = "问卷id", required = true)
    @NotNull(message = "属性questionnaireId不能为空")
    private String questionnaireId;
    
    
}
