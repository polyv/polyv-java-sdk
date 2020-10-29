package net.polyv.live.entity.web.interact;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询打赏设置请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询打赏设置请求实体")
public class LiveChannelDonateRequest extends LiveCommonRequest {
    
    /**
     * 频道号，不传为全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为全局设置", required = false)
    private String channelId;
    
}
