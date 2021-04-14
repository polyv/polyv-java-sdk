package net.polyv.live.v1.entity.channel.playback;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道回放设置请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道回放设置请求实体")
public class LiveChannelPlaybackSettingRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
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
    @ApiModelProperty(name = "videoId", value = "单个回放的视频id", required = false)
    private String videoId;
    
}
