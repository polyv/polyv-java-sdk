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
 * 设置后台回放开关请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置后台回放开关请求实体")
public class LiveChannelPlaybackEnabledRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 回放开关是开/关的状态，值为Y/N，必填
     */
    @ApiModelProperty(name = "playBackEnabled", value = "回放开关是开/关的状态，值为Y/N，必填", required = true)
    @NotNull(message = "属性playBackEnabled不能为空")
    private String playBackEnabled;
    
    /**
     * 频道ID，非必填，不填添加该用户的所有频道ID的回放开关都设置为开/关
     */
    @ApiModelProperty(name = "channelId", value = "频道ID，非必填，不填添加该用户的所有频道ID的回放开关都设置为开/关", required = false)
    private Integer channelId;

}
