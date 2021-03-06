package net.polyv.live.v1.entity.channel.viewdata;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 分页查询频道观看日志请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页查询频道观看日志请求实体")
public class LiveListChannelViewlogRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 查询日期，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "currentDay", value = "查询日期，格式：yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date currentDay;
    
    /**
     * 查询开始时间
     */
    @ApiModelProperty(name = "startTime", value = "查询开始时间", required = false)
    private Date startTime;
    
    /**
     * 查询结束时间
     */
    @ApiModelProperty(name = "endTime", value = "查询结束时间", required = false)
    private Date endTime;
    
    /**
     * 观看用户ID
     */
    @ApiModelProperty(name = "viewerId", value = "观看用户ID", required = false)
    @JSONField(name = "param1")
    private String viewerId;
    
    /**
     * 观看用户名称
     */
    @ApiModelProperty(name = "viewerName", value = "观看用户名称", required = false)
    @JSONField(name = "param2")
    private String viewerName;
    
    /**
     * 观看日志类型，取值 vod 表示观看回放，取值live 表示直播
     */
    @ApiModelProperty(name = "logType", value = "观看日志类型，取值 vod 表示观看回放，取值live 表示直播", required = false)
    @JSONField(name = "param3")
    private String logType;
    
}
