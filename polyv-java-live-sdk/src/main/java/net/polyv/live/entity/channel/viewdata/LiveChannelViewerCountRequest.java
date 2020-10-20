package net.polyv.live.entity.channel.viewdata;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道实时在线人数请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道实时在线人数请求实体")
public class LiveChannelViewerCountRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "channelId不能为空")
    private Integer channelId;
    
}
