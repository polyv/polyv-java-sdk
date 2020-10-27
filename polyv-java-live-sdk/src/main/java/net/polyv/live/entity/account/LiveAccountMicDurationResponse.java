package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 获取账号连麦分钟数使用量与剩余量请求实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
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
