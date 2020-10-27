package net.polyv.live.entity.channel.playback;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询视频库列表请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询视频库列表请求实体")
public class LiveListChannelVideoLibraryRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    /**
     * playback-回放列表,vod-点播列表;默认普通直播场景为vod，三分屏为playback
     */
    @ApiModelProperty(name = "listType", value = "playback-回放列表,vod-点播列表;默认普通直播场景为vod，三分屏为playback", required = false)
    private String listType;
    
}
