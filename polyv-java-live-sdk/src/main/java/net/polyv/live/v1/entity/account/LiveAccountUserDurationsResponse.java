package net.polyv.live.v1.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询账户分钟数返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询账户分钟数返回实体")
public class LiveAccountUserDurationsResponse {
    
    /**
     * POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(name = "userId", value = "POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）", required = false)
    private String userId;
    
    /**
     * 当前可用的分钟数
     */
    @ApiModelProperty(name = "available", value = "当前可用的分钟数", required = false)
    private Long available;
    
    /**
     * 历史已经使用的分钟数
     */
    @ApiModelProperty(name = "used", value = "历史已经使用的分钟数", required = false)
    private Long used;
    
}
