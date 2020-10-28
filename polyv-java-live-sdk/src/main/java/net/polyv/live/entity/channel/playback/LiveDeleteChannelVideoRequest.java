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
 * 删除直播暂存中的录制文件请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("删除直播暂存中的录制文件请求实体")
public class LiveDeleteChannelVideoRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 录制视频的场次ID
     */
    @ApiModelProperty(name = "sessionId", value = "录制视频的场次ID", required = false)
    private String sessionId;
    
    /**
     * 录制视频的开始录制时间，可从 获取频道录制信息接口中获取
     */
    @ApiModelProperty(name = "startTime", value = "录制视频的开始录制时间，可从 获取频道录制信息接口中获取", required = false)
    private String startTime;
    
}
