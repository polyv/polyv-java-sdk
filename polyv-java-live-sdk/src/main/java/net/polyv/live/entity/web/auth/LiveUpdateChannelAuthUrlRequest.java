package net.polyv.live.entity.web.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置授权认证URL请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
