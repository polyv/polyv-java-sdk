package net.polyv.vod.v1.entity.datastatistics;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 批量获取视频观看日志返回实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量获取视频观看日志返回实体")
public class VodGetVideoPlayLogRequest extends VodPageCommonRequest {
    
    /**
     * 查询月份格式
     */
    public static final String MONTH_FORMAT = "yyyyMM";
    
    /**
     * 查询月份，格式为yyyyMM
     */
    @ApiModelProperty(name = "month", value = "查询月份，格式为yyyyMM", required = true)
    @NotNull(message = "属性month不能为空")
    @JSONField(format = "yyyyMM")
    private Date month;
    
    /**
     * 查询开始日期，格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "start", value = "查询开始日期，格式为yyyy-MM-dd", required = false)
    @JSONField(name = "start", format = "yyyy-MM-dd")
    private Date startTime;
    
    /**
     * 查询结束日期，格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "end", value = "查询结束日期，格式为yyyy-MM-dd", required = false)
    @JSONField(name = "end", format = "yyyy-MM-dd")
    private Date endTime;
    
    /**
     * 所查询视频vid，当vid为空时，查询该用户所有视频的日志
     */
    @ApiModelProperty(name = "vid", value = "所查询视频vid，当vid为空时，查询该用户所有视频的日志", required = false)
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 用户自定义ID，自定义值（比如，表示学员信息的学员ID）
     */
    @ApiModelProperty(name = "sessionId", value = "用户自定义ID，自定义值（比如，表示学员信息的学员ID）", required = false)
    private String sessionId;
    
    /**
     * 月内某一天的数据，格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "currentDay", value = "月内某一天的数据，格式为yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date currentDay;
}
