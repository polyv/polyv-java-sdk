package net.polyv.vod.v1.entity.datastatistics.query;

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
 * 获取某一天视频观看日志请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取某一天视频观看日志请求实体")
public class VodQueryViewLogByDayRequest extends VodCommonRequest {
    
    /**
     * 查询某天的日志时间，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "day", value = "查询某天的日志时间，格式：yyyy-MM-dd", required = true)
    @NotNull(message = "属性day不能为空")
    @JSONField(format = "yyyyMMdd")
    private Date day;
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = false)
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 分类ID
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 用户自定义ID，自定义值（比如，表示学员信息的学员ID）
     */
    @ApiModelProperty(name = "sessionId", value = "用户自定义ID，自定义值（比如，表示学员信息的学员ID），最长不能超过50个英文字符。", required = false)
    private String sessionId;
    
    /**
     * 用户自定义ID，当和sessionId同时传递时，会以viewerId为准
     */
    @ApiModelProperty(name = "viewerId", value = "用户自定义ID，当和sessionId同时传递时，会以viewerId为准", required = false)
    private String viewerId;
}
