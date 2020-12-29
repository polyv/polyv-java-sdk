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
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(name = "userId", value = "POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）", required = false)
    private String userId;
}
