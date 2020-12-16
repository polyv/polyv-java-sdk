package net.polyv.live.v1.entity.interact;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 依据指定直播场次sessionId查询签到场次信息
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("依据指定直播场次sessionId查询签到场次信息请求实体")
public class LiveCheckinMetadataBySessionIdRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 场次ID
     */
    @ApiModelProperty(name = "sessionId", value = "场次ID", required = true)
    @NotNull(message = "属性sessionId不能为空")
    private String sessionId;
    
}
