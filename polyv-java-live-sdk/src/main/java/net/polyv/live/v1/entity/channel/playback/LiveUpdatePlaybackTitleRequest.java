package net.polyv.live.v1.entity.channel.playback;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 修改回放视频名称请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("修改回放视频名称请求实体")
public class LiveUpdatePlaybackTitleRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 回放视频ID
     */
    @ApiModelProperty(name = "videoId", value = "回放视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    private String videoId;
    
    /**
     * 回放视频名称
     */
    @ApiModelProperty(name = "title", value = "回放视频名称", required = true)
    @NotNull(message = "属性title不能为空")
    private String title;

}
