package net.polyv.live.entity.player;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置播放器Logo请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置播放器Logo请求实体")
public class LiveSetPlayerLogoRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * logo图片地址，建议大小为：长方形140x50或正方形50x50
     */
    @ApiModelProperty(name = "logoImage", value = "logo图片地址，建议大小为：长方形140x50或正方形50x50", required = true)
    @NotNull(message = "属性logoImage不能为空")
    private String logoImage;
    
    /**
     * logo透明度，取值范围为(0,1]，即大于0，并且小于等于1
     */
    @ApiModelProperty(name = "logoOpacity", value = "logo透明度，取值范围为(0,1]，即大于0，并且小于等于1", required = true)
    @NotNull(message = "属性logoOpacity不能为空")
    private Double logoOpacity;
    
    /**
     * logo位置，取值为为左上角(tl)、右上角(tr)、左下角(bl)、右下角(br)
     */
    @ApiModelProperty(name = "logoPosition", value = "logo位置，取值为为左上角(tl)、右上角(tr)、左下角(bl)、右下角(br)", required = true)
    @NotNull(message = "属性logoPosition不能为空")
    private String logoPosition;
    
    /**
     * logo图片点击跳转链接
     */
    @ApiModelProperty(name = "logoHref", value = "logo图片点击跳转链接", required = false)
    private String logoHref;
    
    
    
}
