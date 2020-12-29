package net.polyv.live.v1.entity.web.interact;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询打赏设置请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询打赏设置请求实体")
public class LiveChannelDonateRequest extends LiveCommonRequest {
    
    /**
     * 频道号，不传为获取全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为获取全局设置", required = false)
    private String channelId;
    
}
