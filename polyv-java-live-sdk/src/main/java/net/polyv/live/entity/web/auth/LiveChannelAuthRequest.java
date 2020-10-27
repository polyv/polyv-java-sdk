package net.polyv.live.entity.web.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询直播频道观看条件请求体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询直播频道观看条件请求体")
public class LiveChannelAuthRequest extends LiveCommonRequest {
    
    /**
     * 频道号,不填获取全局观看条件
     */
    @ApiModelProperty(name = "channelId", value = "频道号,不填获取全局观看条件", required = false)
    private Integer channelId;
    
}
