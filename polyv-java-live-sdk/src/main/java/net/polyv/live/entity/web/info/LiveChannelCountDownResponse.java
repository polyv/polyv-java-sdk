package net.polyv.live.entity.web.info;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道直播倒计时信息返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道直播倒计时信息返回实体")
public class LiveChannelCountDownResponse {
    
    /**
     * 预约观看开关Y或N
     */
    @ApiModelProperty(name = "bookingEnabled", value = "预约观看开关Y或N", required = false)
    private String bookingEnabled;
    
    /**
     * 直播开始时间,为空则没有直播开始时间
     */
    @ApiModelProperty(name = "startTime", value = "直播开始时间,为空则没有直播开始时间", required = false)
    private Date startTime;

}
