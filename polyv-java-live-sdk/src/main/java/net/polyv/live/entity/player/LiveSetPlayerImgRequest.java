package net.polyv.live.entity.player;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置播放器暖场图片请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
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
    @ApiModelProperty(name = "coverHref", value = "暖场图片跳转地址", required = false)
    private String coverHref;
    
    
    
}
