package net.polyv.live.v1.entity.channel.playback;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置视频库列表排序请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置视频库列表排序请求实体")
public class LiveChannelVideoSortRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 完整回放视频ID列表,存放在请求体中,请求视频ID数量必须和回放列表数量一致，且不能少或者缺或者多
     */
    @ApiModelProperty(name = "videoIds", value = "完整回放视频ID列表,存放在请求体中,请求视频ID数量必须和回放列表数量一致，且不能少或者缺或者多", required = true)
    @NotNull(message = "属性videoIds不能为空")
    private List<String> videoIds;
    
    /**
     * playback-回放列表，vod-点播列表;
     */
    @ApiModelProperty(name = "listType", value = "playback-回放列表，vod-点播列表;", required = false)
    private String listType;
    
}
