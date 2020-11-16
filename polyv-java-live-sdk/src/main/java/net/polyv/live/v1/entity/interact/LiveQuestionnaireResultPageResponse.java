package net.polyv.live.v1.entity.interact;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonResponse;

/**
 * 分页查询频道问卷结果响应实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页查询频道问卷结果响应实体")
public class LiveQuestionnaireResultPageResponse extends LivePageCommonResponse {
    
    /**
     * 问卷ID
     */
    @ApiModelProperty(name = "contents", value = "频道的问卷信息和统计结果列表", required = false)
    private List<LiveQuestionnaireResultResponse> contents;
    
}
