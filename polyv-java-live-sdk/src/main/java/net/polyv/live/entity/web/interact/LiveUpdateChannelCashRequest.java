package net.polyv.live.entity.web.interact;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置现金打赏请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置现金打赏请求体")
public class LiveUpdateChannelCashRequest extends LiveCommonRequest {
    
    /**
     * 频道号，不传为全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为全局设置", required = false)
    private String channelId;
    
    /**
     * 请求体参数，现金打赏数额数组，数组的长度必须为6
     */
    @ApiModelProperty(name = "cashes", value = "请求体参数，现金打赏数额数组，数组的长度必须为6", required = true)
    private List<Double> cashes;
    
    /**
     *
     * 请求体参数，现金打赏自定义最小金额
     */
    @ApiModelProperty(name = "cashMin", value = "请求体参数，现金打赏自定义最小金额", required = true)
    private Double cashMin;
    
    /**
     * 请求体参数，现金打赏开关，不传默认开启，值为 Y/N , Y为开启
     */
    @ApiModelProperty(name = "enabled", value = "请求体参数，现金打赏开关，不传默认开启，值为 Y/N , Y为开启", required = false)
    private String enabled;
    
}
