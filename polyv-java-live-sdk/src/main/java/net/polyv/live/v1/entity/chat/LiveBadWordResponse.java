package net.polyv.live.v1.entity.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 批量导入频道严禁词响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@ApiModel("批量导入频道严禁词响应实体")
public class LiveBadWordResponse {
    
    /**
     * 为该频道严禁词的数量，如果修改全部频道，count为该该账户严禁词的全部数量
     */
    @ApiModelProperty(name = "count", value = "请求参设设置了频道号，count则为该频道严禁词的数量，如果没有设置频道号，count为该该账户严禁词的数量", required = false)
    private Integer count;
    
    /**
     * 直播账号ID
     */
    @ApiModelProperty(name = "userId", value = "直播账号ID", required = false)
    private String userId;
}