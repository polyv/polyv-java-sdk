package net.polyv.live.entity.channel;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 创建重制课件任务请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("创建重制课件任务请求体")
public class LiveCreateChannelPPTRecordRequest extends LiveCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true, example = "1940343")
    @NotNull(message = "频道号不能为空")
    private Integer channelId;
    
    /**
     * TODO 链接查询视频库列表的方法
     *  回放视频id,从查询视频库列表获取
     */
    @ApiModelProperty(name = "videoId", value = "回放视频id", required = true, example = "6ce905500b")
    @NotNull(message = "频道号不能为空")
    private String videoId;
}