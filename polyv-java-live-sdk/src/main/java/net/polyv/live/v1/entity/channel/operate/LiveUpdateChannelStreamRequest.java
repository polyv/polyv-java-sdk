package net.polyv.live.v1.entity.channel.operate;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 修改直播推流方式请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("修改直播推流方式请求实体")
public class LiveUpdateChannelStreamRequest extends LiveCommonRequest {
    
    /**
     * 直播频道号
     */
    @ApiModelProperty(name = "channelId", value = "直播频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 直播方式，client:客户端推流;disk:硬盘推流;audio:音频直播;pull:拉流直播
     */
    @ApiModelProperty(name = "streamType", value = "直播方式，client:客户端推流;disk:硬盘推流;audio:音频直播;pull:拉流直播", required = true)
    @NotNull(message = "属性streamType不能为空")
    private String streamType;
    
}
