package net.polyv.live.v1.entity.channel.operate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询授权和连麦的token返回体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询授权和连麦的token返回体")
public class LiveChannelAuthTokenResponse {
    
    /**
     * 链接接口需要的token值
     */
    @ApiModelProperty(name = "token", value = "链接接口需要的token值", required = false)
    private String token;
    
    /**
     * 连麦需要的key
     */
    @ApiModelProperty(name = "mediaChannelKey", value = "连麦需要的key", required = false)
    private String mediaChannelKey;

}
