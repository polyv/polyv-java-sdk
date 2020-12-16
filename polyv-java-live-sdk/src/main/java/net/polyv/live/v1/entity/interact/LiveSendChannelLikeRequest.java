package net.polyv.live.v1.entity.interact;

import net.polyv.common.v1.validator.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.Max;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 发送点赞请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("发送点赞请求实体")
public class LiveSendChannelLikeRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 点赞观众的ID，由调用方自行创建、区分
     */
    @ApiModelProperty(name = "viewerId", value = "点赞观众的ID，由调用方自行创建、区分", required = true)
    @NotNull(message = "属性viewerId不能为空")
    private String viewerId;
    
    /**
     * 点赞的数目，不能超过30，提交后在(times-1)秒后才能再点赞
     */
    @ApiModelProperty(name = "times", value = "点赞的数目，不能超过30，提交后在(times-1)秒后才能再点赞", required = false)
    @Max(value = 30,message = "属性times不能超过30")
    @Min(value = 0,message = "属性times不能小于0")
    private Integer times;

}
