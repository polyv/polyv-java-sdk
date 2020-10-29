package net.polyv.live.entity.channel.viewdata;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 分页查询频道观看日志请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页查询频道观看日志请求实体")
public class LiveListChannelViewlogRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 查询日期，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "currentDay", value = "查询日期，格式：yyyy-MM-dd", required = true)
    private String currentDay;
    
    /**
     * 查询开始时间，为13位毫秒级时间戳
     */
    @ApiModelProperty(name = "startTime", value = "查询开始时间，为13位毫秒级时间戳", required = false)
    private String startTime;
    
    /**
     * 查询结束时间，13位毫秒级时间戳
     */
    @ApiModelProperty(name = "endTime", value = "查询结束时间，13位毫秒级时间戳", required = false)
    private String endTime;
    
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
