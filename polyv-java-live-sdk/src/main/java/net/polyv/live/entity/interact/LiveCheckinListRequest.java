package net.polyv.live.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询签到结果
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询签到结果请求实体")
public class LiveCheckinListRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 查询的指定日期，格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "date", value = "查询的指定日期，格式为yyyy-MM-dd，默认查询当天签到记录", required = false)
    private String date;
    
    /**
     * 场次sessionId,如果传sessionId,
     */
    @ApiModelProperty(name = "sessionId", value = "场次sessionId,sessionId优先级高于date，如传sessionId，date参数无效", required = false)
    private String sessionId;
    
}
