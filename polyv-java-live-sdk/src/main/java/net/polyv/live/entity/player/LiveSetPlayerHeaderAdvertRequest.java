package net.polyv.live.entity.player;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置播放器暂停广告请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置播放器暂停广告请求实体")
public class LiveSetPlayerHeaderAdvertRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    /**
     * Y-开启，N-关闭；设置开关时，其余设置参数无效
     */
    @ApiModelProperty(name = "enabled", value = "Y-开启，N-关闭；设置开关时，其余设置参数无效", required = false)
    private String enabled;
    
    /**
     * 广告类型,NONE-无广告，IMAGE-图片广告，FLV-视频广告
     */
    @ApiModelProperty(name = "headAdvertType", value = "广告类型,NONE-无广告，IMAGE-图片广告，FLV-视频广告", required = false)
    private String headAdvertType;
    
    /**
     * 广告地址
     */
    @ApiModelProperty(name = "headAdvertMediaUrl", value = "广告地址", required = false)
    private String headAdvertMediaUrl;
    
    /**
     * 广告跳转地址
     */
    @ApiModelProperty(name = "headAdvertHref", value = "广告跳转地址", required = false)
    private String headAdvertHref;
    
    /**
     * 广告时长
     */
    @ApiModelProperty(name = "headAdvertDuration", value = "广告时长", required = false)
    private Integer headAdvertDuration;
    
    /**
     * 广告宽度
     */
    @ApiModelProperty(name = "headAdvertWidth", value = "广告宽度", required = false)
    private Integer headAdvertWidth;
    
    /**
     * 广告高度
     */
    @ApiModelProperty(name = "headAdvertHeight", value = "广告高度", required = false)
    private Integer headAdvertHeight;
    
    
    
}
