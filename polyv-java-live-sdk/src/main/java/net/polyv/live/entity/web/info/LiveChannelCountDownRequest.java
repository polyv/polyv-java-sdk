package net.polyv.live.entity.web.info;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道直播倒计时信息请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道直播倒计时信息请求实体")
public class LiveChannelCountDownRequest extends LiveCommonRequest {
    
    /**
     * 频道ID
     */
    @ApiModelProperty(name = "channelId", value = "频道ID", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
}
