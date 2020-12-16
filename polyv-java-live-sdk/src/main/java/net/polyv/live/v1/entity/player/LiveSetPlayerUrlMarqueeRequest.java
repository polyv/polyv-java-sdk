package net.polyv.live.v1.entity.player;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置播放器自定义url跑马灯请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置播放器自定义url跑马灯请求实体")
public class LiveSetPlayerUrlMarqueeRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 自定义url防录屏跑马灯开关,Y或N
     */
    @ApiModelProperty(name = "marqueeRestrict", value = "自定义url防录屏跑马灯开关,Y或N", required = true)
    @NotNull(message = "属性marqueeRestrict不能为空")
    private String marqueeRestrict;
    
    /**
     * 自定义url， 在开关为关时可为空，开启开关时为必填
     */
    @ApiModelProperty(name = "url", value = "自定义url， 在开关为关时可为空，开启开关时为必填", required = false)
    private String url;

}
