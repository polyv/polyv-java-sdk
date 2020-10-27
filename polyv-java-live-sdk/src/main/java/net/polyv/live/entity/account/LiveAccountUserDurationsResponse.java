package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 查询账户分钟数返回实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询账户分钟数返回实体")
public class LiveAccountUserDurationsResponse {
    
    /**
     * 用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID", required = false)
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
