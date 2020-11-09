package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取直播用户账号信息返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("获取直播用户账号信息返回实体")
public class LiveAccountInfoResponse {
    
    /**
     * 用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID", required = false)
    private String userId;
    
    /**
     * 邮箱账号
     */
    @ApiModelProperty(name = "email", value = "邮箱账号", required = false)
    private String email;
    
    /**
     * 最大可创建频道数
     */
    @ApiModelProperty(name = "maxChannels", value = "最大可创建频道数", required = false)
    private Integer maxChannels;
    
    /**
     * 当前已创建频道总数
     */
    @ApiModelProperty(name = "totalChannels", value = "当前已创建频道总数", required = false)
    private Integer totalChannels;
    
    /**
     * 当前剩余可创建频道数
     */
    @ApiModelProperty(name = "availableChannels", value = "当前剩余可创建频道数", required = false)
    private Integer availableChannels;
    
}
