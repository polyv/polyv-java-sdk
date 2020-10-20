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
 * 删除视频库列表中的视频请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("删除视频库列表中的视频请求实体")
public class LiveDeleteChannelPlaybackVideoRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "channelId不能为空")
    private Integer channelId;
    
    /**
     * 直播系统生成的id，可在回放列表接口的返回数据获取
     * {@link net.polyv.live.service.ChannelTest#testListChannelVideoLibrary()}
     */
    @ApiModelProperty(name = "videoId", value = "直播系统生成的id，可在回放列表接口的返回数据获取", required = true)
    @NotNull(message = "videoId不能为空")
    private String videoId;
    
    /**
     * playback-回放列表，vod-点播列表;
     */
    @ApiModelProperty(name = "listType", value = "playback-回放列表，vod-点播列表;", required = false)
    private String listType;
    
}
