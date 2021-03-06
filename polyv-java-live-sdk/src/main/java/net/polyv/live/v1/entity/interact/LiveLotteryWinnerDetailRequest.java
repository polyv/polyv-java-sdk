package net.polyv.live.v1.entity.interact;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 获取频道单场抽奖的中奖记录请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取频道单场抽奖的中奖记录请求实体")
public class LiveLotteryWinnerDetailRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 抽奖ID
     */
    @ApiModelProperty(name = "lotteryId", value = "抽奖ID", required = true)
    @NotNull(message = "属性lotteryId不能为空")
    private String lotteryId;

}
