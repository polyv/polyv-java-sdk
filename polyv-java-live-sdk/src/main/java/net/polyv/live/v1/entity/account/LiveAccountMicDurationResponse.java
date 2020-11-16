package net.polyv.live.v1.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取账号连麦分钟数使用量与剩余量请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("获取账号连麦分钟数使用量与剩余量请求实体")
public class LiveAccountMicDurationResponse {
    
    /**
     * 可用连麦分钟数，单位分钟
     */
    @ApiModelProperty(name = "available", value = "可用连麦分钟数，单位分钟", required = false)
    private Integer available;
    
    /**
     * 历史已使用连麦分钟数，单位分钟
     */
    @ApiModelProperty(name = "history", value = "历史已使用连麦分钟数，单位分钟", required = false)
    private Integer history;
    
}
