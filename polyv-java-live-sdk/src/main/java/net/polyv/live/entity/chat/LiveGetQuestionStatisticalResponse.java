package net.polyv.live.entity.chat;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道的问答统计结果响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道的问答统计结果响应实体")
public class LiveGetQuestionStatisticalResponse {
    /**
     * 题目ID，字符串
     */
    @ApiModelProperty(name = "questionId", value = "题目ID，字符串", required = false)
    private String questionId;
    
    /**
     * 频道号，整型
     */
    @ApiModelProperty(name = "channelId", value = "频道号，整型", required = false)
    private String channelId;
    
    /**
     * 问答类型，radio为单选，judge为判断题
     */
    @ApiModelProperty(name = "type", value = "问答类型，radio为单选，judge为判断题", required = false)
    private String type;
    
    /**
     * 开始时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "startTIme", value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTIme;
    
    /**
     * 结束时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "endTIme", value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTIme;
    
    /**
     * 第一个选择项的答题人数，如果是判断题就是正确选择项的答题人数
     */
    @ApiModelProperty(name = "option1", value = "第一个选择项的答题人数，如果是判断题就是正确选择项的答题人数", required = false)
    private Integer option1;
    
    /**
     * 第二个选择项的答题人数，如果是判断题就是错误选择项的答题人数
     */
    @ApiModelProperty(name = "option2", value = "第二个选择项的答题人数，如果是判断题就是错误选择项的答题人数", required = false)
    private Integer option2;
    
    /**
     * 第三个选择项的答题人数，如果是判断题就是错误选择项的答题人数
     */
    @ApiModelProperty(name = "option3", value = "第三个选择项的答题人数，如果是判断题就是错误选择项的答题人数", required = false)
    private Integer option3;
    
    /**
     * 第四个选择项的答题人数，如果是判断题就是错误选择项的答题人数
     */
    @ApiModelProperty(name = "option4", value = "第四个选择项的答题人数，如果是判断题就是错误选择项的答题人数", required = false)
    private Integer option4;
    
    /**
     * 第五个选择项的答题人数，如果是判断题就是错误选择项的答题人数
     */
    @ApiModelProperty(name = "option5", value = "第五个选择项的答题人数，如果是判断题就是错误选择项的答题人数", required = false)
    private Integer option5;
    
}
