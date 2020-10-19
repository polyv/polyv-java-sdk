package net.polyv.live.entity.interact;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道问卷详情响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置频道问卷信息响应实体")
public class LiveQuestionnaireDetailSetResponse {
    
    /**
     * 问卷id
     */
    @ApiModelProperty(name = "questionnaireId", value = "问卷id", required = false)
    private String questionnaireId;
    
    /**
     * 题目的id数组
     */
    @ApiModelProperty(name = "questionIds", value = "同一个问卷下题目的问题id数组", required = false)
    private List<String> questionIds;
    
}


