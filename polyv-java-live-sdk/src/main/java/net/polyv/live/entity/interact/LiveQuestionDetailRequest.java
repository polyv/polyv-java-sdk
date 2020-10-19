package net.polyv.live.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道问卷详情请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询频道问卷详情请求实体")
public class LiveQuestionDetailRequest extends LiveCommonRequest {
    
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    private Integer channelId;
    
    /**
     * 问卷id
     */
    @ApiModelProperty(name = "questionnaireId", value = "问卷id", required = true)
    @NotNull(message = "questionnaireId不能为空")
    private String questionnaireId;
    
    
}