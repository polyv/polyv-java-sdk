package net.polyv.live.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 依据指定直播场次sessionId查询签到场次信息
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
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
