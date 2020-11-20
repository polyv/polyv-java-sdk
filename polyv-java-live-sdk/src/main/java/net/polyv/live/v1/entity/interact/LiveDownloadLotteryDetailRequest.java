package net.polyv.live.v1.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 导出频道单场抽奖的中奖记录请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("导出频道单场抽奖的中奖记录请求实体")
public class LiveDownloadLotteryDetailRequest extends LiveCommonRequest {
    
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
