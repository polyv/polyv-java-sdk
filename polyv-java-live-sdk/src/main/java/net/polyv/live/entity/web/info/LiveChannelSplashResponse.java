package net.polyv.live.entity.web.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 查询直播引导图开关状态及URL返回实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询直播引导图开关状态及URL返回实体")
public class LiveChannelSplashResponse {
    
    /**
     * 引导图片url
     */
    @ApiModelProperty(name = "splashImg", value = "引导图片url", required = false)
    private String splashImg;
    
    /**
     * 引导功能开关
     */
    @ApiModelProperty(name = "splashEnabled", value = "引导功能开关", required = false)
    private String splashEnabled;

}
