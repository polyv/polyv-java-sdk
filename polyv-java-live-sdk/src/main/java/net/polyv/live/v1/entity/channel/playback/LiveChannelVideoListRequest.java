package net.polyv.live.v1.entity.channel.playback;

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
 * 查询频道录制视频信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    
    /**
     * 结束日期，格式为：yyyy-MM-dd
     */
    @ApiModelProperty(name = "endDate", value = "结束日期，格式为：yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    
    /**
     * 直播的场次ID
     */
    @ApiModelProperty(name = "sessionId", value = "直播的场次ID", required = false)
    private String sessionId;
    
}
