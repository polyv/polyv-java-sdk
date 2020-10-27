package net.polyv.live.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询签到结果
 * @author: thomas
 **/
@Data
@ToString
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
    private Integer channelId;
    
    /**
     * 查询的指定日期，格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "date", value = "查询的指定日期，格式为yyyy-MM-dd", required = false)
    private String date;
    
    /**
     * 场次sessionId,如果传sessionId,
     */
    @ApiModelProperty(name = "sessionId", value = "场次sessionId,如果传sessionId,", required = false)
    private String sessionId;
    
}
