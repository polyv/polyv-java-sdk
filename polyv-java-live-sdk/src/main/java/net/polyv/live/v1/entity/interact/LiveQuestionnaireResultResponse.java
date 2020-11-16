package net.polyv.live.v1.entity.interact;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道问卷结果响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道问卷结果响应实体")
public class LiveQuestionnaireResultResponse {
    
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
     * 问卷名称
     */
    @ApiModelProperty(name = "questionnaireTitle", value = "问卷名称", required = false)
    private String questionnaireTitle;
 
    
    /**
     * 问卷最后修改时间
     */
    @ApiModelProperty(name = "lastModified", value = "问卷最后修改时间", required = false)
    private Date lastModified;
    
    /**
     * 问卷最后修改时间
     */
    @ApiModelProperty(name = "endTime", value = "问卷最后修改时间", required = false)
    private Date endTime;
    
    /**
     * 问卷下各个问题的答题统计
     */
    @ApiModelProperty(name = "questionStats", value = "问卷下各个问题的答题统计", required = false)
    private List<QuestionStats> questionStats;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("查询频道问卷结果响应实体-问卷下各个问题的答题统计")
    public class QuestionStats {
        
        /**
         * 题目的答题人数
         */
        @ApiModelProperty(name = "total", value = "题目的答题人数", required = false)
        private Integer total;
        
        /**
         * 题目的答题统计信息
         */
        @ApiModelProperty(name = "questions", value = "问卷下单个问题的答题统计信息", required = false)
        private List<QuestionStat> questions;
        
        @Data
        @Accessors(chain = true)
        @ApiModel("查询频道问卷结果响应实体-问卷下单个问题的答题统计")
        public class QuestionStat {
            
            /**
             * 题目ID,
             */
            @ApiModelProperty(name = "questionId", value = "题目ID,", required = false)
            private String questionId;
            
            /**
             * 题目名称
             */
            @ApiModelProperty(name = "questionName", value = "题目名称", required = false)
            private String questionName;
            
            /**
             * 题目分数
             */
            @ApiModelProperty(name = "score", value = "题目分数", required = false)
            private Integer score;
            
            /**
             * 题目的总得分
             */
            @ApiModelProperty(name = "totalScore", value = "题目的总得分", required = false)
            private Integer totalScore;
        
            /**
             * 题目的答对人数
             */
            @ApiModelProperty(name = "correctCount", value = "题目的答对人数", required = false)
            private Integer correctCount;
            
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "a", value = "选择项a的答题人数", required = false)
            private Integer a;
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "b", value = "选择项b的答题人数", required = false)
            private Integer b;
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "c", value = "选择项c的答题人数", required = false)
            private Integer c;
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "d", value = "选择项d的答题人数", required = false)
            private Integer d;
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "e", value = "选择项e的答题人数", required = false)
            private Integer e;
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "f", value = "选择项f的答题人数", required = false)
            private Integer f;
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "g", value = "选择项g的答题人数", required = false)
            private Integer g;
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "h", value = "选择项g的答题人数", required = false)
            private Integer h;
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "i", value = "选择项g的答题人数", required = false)
            private Integer i;
            /**
             * 选择项a到g分别的答题人数
             */
            @ApiModelProperty(name = "j", value = "选择项g的答题人数", required = false)
            private Integer j;
            
        }
        
    }
    
    
    /**
     * 观看端提交答题的信息
     */
    @ApiModelProperty(name = "users", value = "观看端提交答题的用户信息", required = false)
    private List<Users> users;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("查询频道问卷结果响应实体-观看端提交答题的用户信息")
    public class Users {
        /**
         * 提交问卷的用户ID
         */
        @ApiModelProperty(name = "viewerId", value = "提交问卷的用户ID", required = false)
        private String viewerId;
        
        /**
         * 提交问卷的用户昵称
         */
        @ApiModelProperty(name = "nickname", value = "提交问卷的用户昵称", required = false)
        private String nickname;
    
        /**
         * 提交问卷的用户的总得分
         */
        @ApiModelProperty(name = "submitTime", value = "提交问卷时间", required = false)
        private Date submitTime;
        
        /**
         * 提交问卷的用户的总得分
         */
        @ApiModelProperty(name = "totalScore", value = "提交问卷的用户的总得分", required = false)
        private String totalScore;
        
        /**
         * 用户每道题目的答题情况
         */
        @ApiModelProperty(name = "answers", value = "用户每道题目的答题情况", required = false)
        private List<Answers> answers;
        
        @Data
        @Accessors(chain = true)
        @ApiModel("查询频道问卷结果响应实体-用户每道题目的答题情况")
        public class Answers {
            /**
             * 题目ID
             */
            @ApiModelProperty(name = "questionId", value = "题目ID", required = false)
            private String questionId;
            
            /**
             * 题目名称
             */
            @ApiModelProperty(name = "questionName", value = "题目名称", required = false)
            private String questionName;
            
            /**
             * 提交的题目答案
             */
            @ApiModelProperty(name = "answer", value = "提交的题目答案", required = false)
            private String answer;
            
            /**
             * 用户答题的得分
             */
            @ApiModelProperty(name = "score", value = "用户答题的得分", required = false)
            private String score;
            
            /**
             * 题目的类型，R
             */
            @ApiModelProperty(name = "type", value = "题目的类型，R", required = false)
            private String type;
            
           
        }
        /**
         * 在外部授权、直接（独立）授权情况下传过来的自定义参数
         */
        @ApiModelProperty(name = "param4", value = "在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传", required = false)
        private String param4;
    
        /**
         * 在外部授权、直接（独立）授权情况下传过来的自定义参数
         */
        @ApiModelProperty(name = "param5", value = "在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传", required = false)
        private String param5;
    }
}
