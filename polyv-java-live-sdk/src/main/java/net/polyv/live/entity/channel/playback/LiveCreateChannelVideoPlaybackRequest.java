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
 * 将点播中的视频添加到视频库请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("将点播中的视频添加到视频库请求实体")
public class LiveCreateChannelVideoPlaybackRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    private Integer channelId;
    
    /**
     * 要添加为回放的的点播视频
     */
    @ApiModelProperty(name = "vid", value = "要添加为回放的的点播视频", required = true)
    @NotNull(message = "vid不能为空")
    private String vid;
    
    /**
     * playback-回放列表，vod-点播列表;
     */
    @ApiModelProperty(name = "listType", value = "playback-回放列表，vod-点播列表;", required = false)
    private String listType;

}
