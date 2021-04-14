package net.polyv.live.v1.entity.interact;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询指定签到ID的签到记录
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询指定签到ID的签到记录请求实体")
public class LiveCheckinRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 签到ID
     */
    @ApiModelProperty(name = "checkinId", value = "签到ID", required = true)
    @NotNull(message = "属性checkinId不能为空")
    private String checkinId;
    
}
