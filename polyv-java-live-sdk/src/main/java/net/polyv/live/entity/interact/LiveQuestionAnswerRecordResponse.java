package net.polyv.live.entity.interact;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道答题卡答题结果响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道答题卡答题结果响应实体响应实体")
public class LiveQuestionAnswerRecordResponse {
    /**
     * 题目ID
     */
    @ApiModelProperty(name = "questionId", value = "题目ID", required = false)
    private String questionId;
    
    /**
     * 第几次发送题目，用于区分相同题目重复发送的情况
     */
    @ApiModelProperty(name = "times", value = "第几次发送题目，用于区分相同题目重复发送的情况", required = false)
    private Integer times;
    
    /**
     * 题目的答案
     */
    @ApiModelProperty(name = "answer", value = "题目的答案", required = false)
    private String answer;
    
    /**
     * 答题人数
     */
    @ApiModelProperty(name = "total", value = "答题人数", required = false)
    private Integer total;
    
    /**
     * 题目选项信息列表
     */
    @ApiModelProperty(name = "options", value = "", required = false)
    private List<Option> options;
    
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("查询频道答题卡答题结果响应实体-题目选项信息列表")
    public class Option {
        /**
         * 选项标题
         */
        @ApiModelProperty(name = "title", value = "选项标题", required = false)
        private String title;
    
        /**
         * 选择该选项的人数
         */
        @ApiModelProperty(name = "count", value = "选择该选项的人数", required = false)
        private Integer count;
    
        /**
         * 选择该选项的人数百分比
         */
        @ApiModelProperty(name = "percent", value = "选择该选项的人数百分比", required = false)
        private String percent;
    }
    
    /**
     * 答题的用户列表
     */
    @ApiModelProperty(name = "records", value = "答题的用户列表", required = false)
    private List<Record> records;
    
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("查询频道答题卡答题结果响应实体-答题的用户列表")
    public class Record {
        /**
         * 答题的用户ID
         */
        @ApiModelProperty(name = "viewerId", value = "答题的用户ID", required = false)
        private String viewerId;
    
        /**
         * 答题的用户昵称
         */
        @ApiModelProperty(name = "nickname", value = "答题的用户昵称", required = false)
        private String nickname;
    
        /**
         * 答题的用户提交的答案
         */
        @ApiModelProperty(name = "answer", value = "答题的用户提交的答案", required = false)
        private String answer;
    
        /**
         * 答题的用户提交的答案是否正确：false不正确，true正确
         */
        @ApiModelProperty(name = "corrent", value = "答题的用户提交的答案是否正确：false不正确，true正确", required = false)
        private Boolean corrent;
    
        /**
         * 答题的用户提交时间，13位毫秒级时间戳
         */
        @ApiModelProperty(name = "submitTime", value = "答题的用户提交时间", required = false)
        private Date submitTime;
    }
    /**
     * 题目类型：R为单选，C为多选，Q为问答
     */
    @ApiModelProperty(name = "type", value = "题目类型：R为单选，C为多选，Q为问答", required = false)
    private String type;
    
    /**
     * 答题类型：1表示问答，0表示答题卡
     */
    @ApiModelProperty(name = "itemType", value = "答题类型：1表示问答，0表示答题卡", required = false)
    private Integer itemType;
    
    
}


