package net.polyv.live.entity.interact;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 获取频道抽奖记录列表请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取频道抽奖记录列表请求实体")
public class LiveListLotteryRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不允许为空")
    private String channelId;
    
    /**
     * 要查询的直播场次ID
     */
    @ApiModelProperty(name = "sessionId", value = "要查询的直播场次ID", required = false)
    private String sessionId;
    
    /**
     * 查询的开始日期的13位时间戳
     */
    @ApiModelProperty(name = "startTime", value = "查询的开始日期的13位时间戳", required = true)
    @NotNull(message = "属性startTime不允许为空")
    private Date startTime;
    
    /**
     * 查询的结束日期的13位时间戳
     */
    @ApiModelProperty(name = "endTime", value = "查询的结束日期的13位时间戳", required = false)
    @NotNull(message = "属性endTime不允许为空")
    private Date endTime;
    
}
