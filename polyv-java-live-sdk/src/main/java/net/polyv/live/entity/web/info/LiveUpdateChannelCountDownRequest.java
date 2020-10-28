package net.polyv.live.entity.web.info;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置频道直播倒计时信息请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置频道直播倒计时信息请求实体")
public class LiveUpdateChannelCountDownRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 预约观看开关Y或N
     */
    @ApiModelProperty(name = "bookingEnabled", value = "预约观看开关Y或N", required = false)
    private String bookingEnabled;
    
    /**
     * 直播开始时间，如果不传该值，表示不显示直播时间和倒计时（yyyy-MM-dd HH:mm:ss）
     */
    @ApiModelProperty(name = "startTime", value = "直播开始时间，如果不传该值，表示不显示直播时间和倒计时（yyyy-MM-dd HH:mm:ss）", required = false)
    private String startTime;
    
}
