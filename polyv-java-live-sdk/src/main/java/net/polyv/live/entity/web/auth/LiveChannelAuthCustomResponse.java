package net.polyv.live.entity.web.auth;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 设置自定义授权地址返回实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置自定义授权地址返回实体")
public class LiveChannelAuthCustomResponse {
    
    @ApiModelProperty(name = "channelAuthExternals", value = "外部授权", required = false)
    private List<ChannelAuthExternal> channelAuthExternals;
    
    
    @Data
@ToString
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("外部授权")
    public static class ChannelAuthExternal {
        
        /**
         * 设置的频道号
         */
        @ApiModelProperty(name = "channelId", value = "设置的频道号", required = false)
        private Integer channelId;
        
        /**
         * 频道号对应外部授权的secretKey
         */
        @ApiModelProperty(name = "secretKey", value = "频道号对应外部授权的secretKey", required = false)
        private String secretKey;
        
    }
    
}
