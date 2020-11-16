package net.polyv.live.v1.entity.channel.playback;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置后台回放开关请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
     * 频道号，非必填，不填添加该用户的所有频道号的回放开关都设置为开/关
     */
    @ApiModelProperty(name = "channelId", value = "频道号，非必填，不填添加该用户的所有频道号的回放开关都设置为开/关", required = false)
    private String channelId;

}
