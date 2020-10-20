package net.polyv.live.entity.interact;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;

/**
 * 查询频道问卷结果响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道问卷结果响应实体")
public class LiveQuestionnaireResultPageResponse extends LivePageCommonResponse {
    
    /**
     * 问卷ID
     */
    @ApiModelProperty(name = "contents", value = "频道的问卷信息和统计结果", required = false)
    private List<LiveQuestionnaireResultResponse> contents;
    
}
