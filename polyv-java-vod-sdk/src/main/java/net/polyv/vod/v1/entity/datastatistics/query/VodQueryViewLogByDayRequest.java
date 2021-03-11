package net.polyv.vod.v1.entity.datastatistics.query;

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
     * 查询某天的日志时间，格式：yyyyMMdd
     */
    @ApiModelProperty(name = "day", value = "查询某天的日志时间，格式：yyyyMMdd", required = true)
    @NotNull(message = "属性day不能为空")
    private String day;
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = false)
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 分类ID，注意视频ID（vid）和分类ID（cataid）为空时，获取账号当天所有视频日志；当vid为空、cataid不为空时，查询对应cataid下的日志；当vid不为空时查询对应vid的日志
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID，注意视频ID（vid）和分类ID（cataid）为空时，获取账号当天所有视频日志；当vid为空、cataid" +
            "不为空时，查询对应cataid下的日志；当vid不为空时查询对应vid的日志", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 用户自定义ID
     */
    @ApiModelProperty(name = "sessionId", value = "用户自定义ID", required = false)
    private String sessionId;
    
    /**
     * 用户自定义ID，当和sessionId同时传递时，会以viewerId为准
     */
    @ApiModelProperty(name = "viewerId", value = "用户自定义ID，当和sessionId同时传递时，会以viewerId为准", required = false)
    private String viewerId;
}
