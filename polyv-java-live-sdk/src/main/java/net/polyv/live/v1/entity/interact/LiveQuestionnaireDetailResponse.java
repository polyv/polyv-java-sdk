package net.polyv.live.v1.entity.interact;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道问卷详情响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
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
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private String channelId;
    
    /**
      *  {@code POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）}
     */
    @ApiModelProperty(name = "userId", value = "POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）", required = false)
    private String userId;
    
 
    /**
     * 问卷名称
     */
    @ApiModelProperty(name = "questionnaireTitle", value = "问卷标题", required = false)
    @JSONField(name = "name")
    private String questionnaireTitle;
    
    /**
     *问卷状态，draft：草稿，send：已发送，delete：已删除
     */
    @ApiModelProperty(name = "status", value = "问卷状态，draft：草稿，send：已发送，delete：已删除", required = false)
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
    @ApiModel("查询频道问卷详情响应实体-问题详情")
    public static class QuestionDetail {
        
        
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
        @ApiModelProperty(name = "option1", value = "选项A", required = false)
        private String option1;
        
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option2", value = "选项B", required = false)
        private String option2;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option3", value = "选项C", required = false)
        private String option3;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option4", value = "选项D", required = false)
        private String option4;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option5", value = "选项E", required = false)
        private String option5;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option6", value = "选项F", required = false)
        private String option6;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option7", value = "选项G", required = false)
        private String option7;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option8", value = "选项H", required = false)
        private String option8;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option9", value = "选项I", required = false)
        private String option9;
        /**
         * 选项A~G
         */
        @ApiModelProperty(name = "option10", value = "选项J", required = false)
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
        @ApiModelProperty(name = "scoreEnabled", value = "是否计分，取值： Y 计分 、N  不计分，默认N", required = false)
        private String scoreEnabled;
        
        /**
         * 题目分值
         */
        @ApiModelProperty(name = "score", value = "题目分值", required = false)
        private Integer score;
        
        /**
         * 是否必填，取值Y、N
         */
        @ApiModelProperty(name = "required", value = "是否必答，取值Y 必填 、N 非必填 ，默认 N", required = false)
        private String required;
        
        /**
         * 问题答案
         */
        @ApiModelProperty(name = "answer", value = "问题答案", required = false)
        private String answer;
    }
    
}


