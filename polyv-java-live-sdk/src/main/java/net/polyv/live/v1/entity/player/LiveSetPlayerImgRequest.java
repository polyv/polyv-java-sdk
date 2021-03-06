package net.polyv.live.v1.entity.player;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置播放器暖场图片请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置播放器暖场图片请求实体")
public class LiveSetPlayerImgRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    /**
     * 暖场图片地址，图片大小建议：800x450，支持PNG、JPEG、GIF格式
     */
    @ApiModelProperty(name = "coverImage", value = "暖场图片地址，图片大小建议：800x450，支持PNG、JPEG、GIF格式", required = true)
    @NotNull(message = "属性coverImage不能为空")
    private String coverImage;
    
    /**
     * 暖场图片跳转地址
     */
    @ApiModelProperty(name = "coverHref", value = "点击暖场图片后浏览器跳转地址", required = false)
    private String coverHref;
    
    
    
}
