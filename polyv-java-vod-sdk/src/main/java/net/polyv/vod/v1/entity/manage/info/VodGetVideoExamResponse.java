package net.polyv.vod.v1.entity.manage.info;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取单个视频的问答题目返回实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取单个视频的问答题目返回实体")
public class VodGetVideoExamResponse extends VodCommonRequest {
    /**
     * 问答题目的id
     */
    @ApiModelProperty(name = "examId", value = "问答题目的id", required = false)
    private String examId;
    
    /**
     * 用户的id
     */
    @ApiModelProperty(name = "userid", value = "用户的id", required = false)
    private String userid;
    
    /**
     * 视频的id
     */
    @ApiModelProperty(name = "videoId", value = "视频的id", required = false)
    @JSONField(name = "videoPoolId")
    private String videoId;
    
    /**
     * 问答题目开始显示的时间，格式：时分秒
     */
    @ApiModelProperty(name = "showTime", value = "问答题目开始显示的时间，格式：时分秒", required = false)
    private String showTime;
    
    /**
     * 时
     */
    @ApiModelProperty(name = "hours", value = "时", required = false)
    private Integer hours;
    
    /**
     * 分
     */
    @ApiModelProperty(name = "minute", value = "分", required = false)
    private Integer minute;
    
    /**
     * 秒
     */
    @ApiModelProperty(name = "seconds", value = "秒", required = false)
    private Integer seconds;
    
    /**
     * 问题
     */
    @ApiModelProperty(name = "questions", value = "问题", required = false)
    private String questions;
    
    /**
     * 选项
     */
    @ApiModelProperty(name = "choices", value = "选项", required = false)
    private String choices;
    
    /**
     * 回答正确提示语
     */
    @ApiModelProperty(name = "answer", value = "回答正确提示语", required = false)
    private String answer;
    
    /**
     * 回答错误提示语
     */
    @ApiModelProperty(name = "wrongAnswer", value = "回答错误提示语", required = false)
    private String wrongAnswer;
    
    /**
     * 能否跳过问答
     */
    @ApiModelProperty(name = "skip", value = "能否跳过问答", required = false)
    private Boolean skip;
    
    /**
     * 回答错误后跳回到第几秒，-1指不退回
     */
    @ApiModelProperty(name = "wrongTime", value = "回答错误后跳回到第几秒，-1指不退回", required = false)
    private Integer wrongTime;
    
    /**
     * 回答错误是否提示
     */
    @ApiModelProperty(name = "wrongShow", value = "回答错误是否提示", required = false)
    private Integer wrongShow;
    
    /**
     * 创建问答题目的时间，格式：yyyy-MM-dd HH:mm
     */
    @ApiModelProperty(name = "createdTime", value = "创建问答题目的时间，格式：yyyy-MM-dd HH:mm", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createdTime;
    
    /**
     * 问答所在的问卷的ID
     */
    @ApiModelProperty(name = "groupId", value = "问答所在的问卷的ID", required = false)
    private String groupId;
    
}
