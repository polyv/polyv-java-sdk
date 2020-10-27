package net.polyv.live.entity.channel.viewdata;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道观看日志请求实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道观看日志请求实体")
public class LiveChannelViewlogRequest extends LiveCommonRequest {
    
    /**
     * 直播账号ID
     */
    @ApiModelProperty(name = "userId", value = "直播账号ID", required = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    /**
     * 查询日期，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "currentDay", value = "查询日期，格式：yyyy-MM-dd", required = true)
    @NotNull(message = "属性currentDay不能为空")
    private String currentDay;
    
    /**
     * 观看用户ID
     */
    @ApiModelProperty(name = "viewerId", value = "观看用户ID", required = false)
    @JSONField(name = "param1")
    private String viewerId;
    
}
