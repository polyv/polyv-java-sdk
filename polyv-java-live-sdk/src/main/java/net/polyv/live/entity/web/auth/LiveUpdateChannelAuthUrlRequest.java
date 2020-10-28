package net.polyv.live.entity.web.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置授权认证URL请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置授权认证URL请求实体")
public class LiveUpdateChannelAuthUrlRequest extends LiveCommonRequest {
    
    /**
     * 频道号，无该参数为全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，无该参数为全局设置", required = false)
    private String channelId;
    
    /**
     * 授权认证url，为空时清除设置
     */
    @ApiModelProperty(name = "url", value = "授权认证url，为空时清除设置", required = false)
    private String url;
    
}
