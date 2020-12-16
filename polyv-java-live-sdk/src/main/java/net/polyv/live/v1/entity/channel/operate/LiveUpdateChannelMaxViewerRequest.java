package net.polyv.live.v1.entity.channel.operate;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道最大在线人数请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道最大在线人数请求实体")
public class LiveUpdateChannelMaxViewerRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 直播账号ID
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 最大观看在线人数，等于0时表示关闭在线人数观看限制，最大为2147483647
     */
    @ApiModelProperty(name = "maxViewer", value = "最大观看在线人数，等于0时表示关闭在线人数观看限制，最大为2147483647", required = true)
    @NotNull(message = "属性maxViewer不能为空")
    private Integer maxViewer;
    
}
