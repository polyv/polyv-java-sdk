package net.polyv.live.entity.channel.playback;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道录制视频信息请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道录制视频信息请求实体")
public class LiveChannelVideoListRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 需要设置频道详情的频道号，例如：1938028
     */
    @ApiModelProperty(name = "channelId", value = "需要设置频道详情的频道号，例如：1938028", required = true, example =
            "1938028")
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 开始日期（录制生成的日期），格式为：yyyy-MM-dd
     */
    @ApiModelProperty(name = "startDate", value = "开始日期（录制生成的日期），格式为：yyyy-MM-dd", required = false)
    private String startDate;
    
    /**
     * 结束日期，格式为：yyyy-MM-dd
     */
    @ApiModelProperty(name = "endDate", value = "结束日期，格式为：yyyy-MM-dd", required = false)
    private String endDate;
    
    /**
     * 直播的场次ID
     */
    @ApiModelProperty(name = "sessionId", value = "直播的场次ID", required = false)
    private String sessionId;
    
}
