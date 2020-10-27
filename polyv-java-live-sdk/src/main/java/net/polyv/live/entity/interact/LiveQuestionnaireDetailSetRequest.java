package net.polyv.live.entity.interact;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@ApiModel("设置频道问卷信息请求实体")
public class LiveQuestionnaireDetailSetRequest extends LiveCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    /**
     * 问卷id,修改问卷时需要
     */
    @ApiModelProperty(name = "questionnaireId", value = "问卷id,新增问卷该字段为null，修改问卷该字段问卷id", required = false)
    private String questionnaireId;
    
    /**
     * 客户自定义问卷id
     */
    @ApiModelProperty(name = "customQuestionnaireId", value = "客户自定义问卷id，用于关联自己系统的主键id", required = false)
    private String customQuestionnaireId;
    
    /**
     * 问卷标题
     */
    @ApiModelProperty(name = "questionnaireTitle", value = "问卷标题", required = true)
    @NotNull(message = "属性questionnaireTitle不能为空")
    private String questionnaireTitle;
    
    /**
     * 题目数组
     */
    @ApiModelProperty(name = "questions", value = "问卷的单个题目详情列表", required = true)
    private List<QuestionDetail> questions;
    
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("设置频道问卷信息请求实体-问卷的单个题目详情列表")
    public  class QuestionDetail {
    
        /**
         * 题目id，修改问卷时需要传
         */
        @ApiModelProperty(name = "questionId", value = "题目id，新增时为null，修改问卷必须传", required = false)
        private String questionId;
        /**
         * 题目分值
         */
        @ApiModelProperty(name = "score", value = "题目分值，自动化打分使用", required = false)
        private Integer score;
        /**
         * 题目
         */
        @ApiModelProperty(name = "name", value = "题目信息描述", required = true)
        @NotNull(message = "属性name不能为空")
        private String name;
    
        /**
         * 题目类型,R为单选，C为多选，Q为问答
         */
        @ApiModelProperty(name = "type", value = "题目类型,R为单选，C为多选，Q为问答", required = true)
        @NotNull(message = "属性type不能为空")
        private String type;
    
        /**
         * 题目是否需要评分，Y为需要，N为不需要
         */
        @ApiModelProperty(name = "scoreEnabled", value = "题目是否需要评分，Y为需要，N为不需要，默认为N", required = false)
        private String scoreEnabled;
    
        /**
         * 需要评分的选择题才有答案，填入对应选项序号，如：A或AB
         */
        @ApiModelProperty(name = "answer", value = "选择题答案，需要评分的选择题才有答案，填入对应选项序号，如：A或AB", required = false)
        private String answer;
    
        /**
         * 题目是否为必答，Y为必答，N为非必答
         */
        @ApiModelProperty(name = "required", value = "题目是否为必答，Y为必答，N为非必答，默认为N", required = false)
        private String required;
    
        /**
         * 题目为单选题或多选题为必填，选项数组下标0-9对应答案A-J
         */
        @ApiModelProperty(name = "options", value = "题目为单选题或多选题的选项数据列表，选项数组下标0-9对应答案A-J", required = false)
        private List<String> options;
     
    
    }
    
}
