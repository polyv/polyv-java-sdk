package net.polyv.live.v1.entity.player;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置播放器暂停广告请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置播放器暂停广告请求实体")
public class LiveSetPlayerPauseAdvertRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * Y-打开，N-关闭；设置开关时，其余设置参数无效
     */
    @ApiModelProperty(name = "enabled", value = "设置播放器暂停广告开关：Y-打开，N-关闭", required = false)
    private String enabled;
    
    /**
     * 图片地址，不填代表删除
     */
    @ApiModelProperty(name = "stopAdvertImage", value = "图片地址，不填代表删除", required = false)
    private String stopAdvertImage;
    
    /**
     * 点击图片跳转Url
     */
    @ApiModelProperty(name = "stopAdvertHref", value = "点击图片跳转Url", required = false)
    private String stopAdvertHref;
    
    
}
