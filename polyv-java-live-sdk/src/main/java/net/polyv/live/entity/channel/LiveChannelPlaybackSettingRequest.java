package net.polyv.live.entity.channel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置频道回放设置请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("设置频道回放设置请求实体")
public class LiveChannelPlaybackSettingRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "stream不能为空")
    private Integer channelId;
    
    /**
     * 回放开关，Y-开启，N-关闭
     */
    @ApiModelProperty(name = "playbackEnabled", value = "回放开关，Y-开启，N-关闭", required = false)
    private String playbackEnabled;
    
    /**
     * 回放方式，single-单个回放，list-列表回放
     */
    @ApiModelProperty(name = "type", value = "回放方式，single-单个回放，list-列表回放", required = false)
    private String type;
    
    /**
     * 回放来源，record-暂存，playback-回放列表，vod-点播列表
     */
    @ApiModelProperty(name = "origin", value = "回放来源，record-暂存，playback-回放列表，vod-点播列表", required = false)
    private String origin;
    
    /**
     * 单个回放的视频id
     */
    @ApiModelProperty(name = "videoId", value = "单个回放的视频id", required = true)
    @NotEmpty(message = "videoId不能为空")
    private String videoId;
    
}
