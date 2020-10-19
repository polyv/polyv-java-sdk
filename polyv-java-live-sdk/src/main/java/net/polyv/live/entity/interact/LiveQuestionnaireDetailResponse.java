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
@ApiModel("查询频道问卷详情响应实体")
public class LiveQuestionnaireDetailResponse {
    
    /**
     * 问卷ID
     */
    @ApiModelProperty(name = "questionnaireId", value = "问卷ID", required = false)
    private String questionnaireId;
    
    /**
     * 用户自定义问卷ID
     */
    @ApiModelProperty(name = "customQuestionnaireId", value = "用户自定义问卷ID", required = false)
    private String customQuestionnaireId;
    
    /**
     * 频道ID
     */
    @ApiModelProperty(name = "channelId", value = "频道ID", required = false)
    private Integer channelId;
    
    /**
     * 用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID", required = false)
    private String userId;
    
    /**
     * 问卷名称
     */
    @ApiModelProperty(name = "name", value = "问卷名称", required = false)
    private String name;
    
    /**
     * 问卷状态
     */
    @ApiModelProperty(name = "status", value = "问卷状态", required = false)
    private String status;
    
    /**
     * 问卷创建时间
     */
    @ApiModelProperty(name = "createdTime", value = "问卷创建时间", required = false)
    private Date createdTime;
    
    /**
     * 停止问卷时间
     */
    @ApiModelProperty(name = "endTime", value = "停止问卷时间", required = false)
    private Date endTime;
    
    /**
     * 问卷问题列表
     */
    @ApiModelProperty(name = "questions", value = "问卷问题列表", required = false)
    private List<QuestionDetail> questions;
    
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("查询频道问卷详情响应实体-问题详情")
    public class QuestionDetail {
        
        
        /**
         * 问题ID
         */
        @ApiModelProperty(name = "questionId", value = "问题ID", required = false)
        private String questionId;
        
        /**
         * 问题题目
         */
        @ApiModelProperty(name = "name", value = "问题题目", required = false)
        private String name;
        
        /**
         * 问题类型，取值：R
         */
        @ApiModelProperty(name = "type", value = "问题类型，取值：R 单选；C 多选；S 评星题；Q 问答", required = false)
        private String type;
        
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option1", value = "选项A~G", required = false)
        private String option1;
        
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option2", value = "选项A~G", required = false)
        private String option2;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option3", value = "选项A~G", required = false)
        private String option3;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option4", value = "选项A~G", required = false)
        private String option4;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option5", value = "选项A~G", required = false)
        private String option5;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option6", value = "选项A~G", required = false)
        private String option6;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option7", value = "选项A~G", required = false)
        private String option7;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option8", value = "选项A~G", required = false)
        private String option8;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option9", value = "选项A~G", required = false)
        private String option9;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option10", value = "选项A~G", required = false)
        private String option10;
        
        /**
         * 创建时间
         */
        @ApiModelProperty(name = "createdTime", value = "创建时间", required = false)
        private Date createdTime;
        
        /**
         * 最后修改时间
         */
        @ApiModelProperty(name = "lastModified", value = "最后修改时间", required = false)
        private Date lastModified;
        
        /**
         * 是否计分，取值Y、N
         */
        @ApiModelProperty(name = "scoreEnabled", value = "是否计分，取值Y、N", required = false)
        private String scoreEnabled;
        
        /**
         * 题目分值
         */
        @ApiModelProperty(name = "score", value = "题目分值", required = false)
        private Integer score;
        
        /**
         * 是否必填，取值Y、N
         */
        @ApiModelProperty(name = "required", value = "是否必填，取值Y、N", required = false)
        private String required;
        
        /**
         * 问题答案
         */
        @ApiModelProperty(name = "answer", value = "问题答案", required = false)
        private String answer;
    }
    
}

