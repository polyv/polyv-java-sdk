package net.polyv.live.v1.entity.web.info;

import java.util.Date;

import net.polyv.common.v1.validator.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道直播倒计时信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    
}
