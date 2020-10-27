package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询功能开关状态接口请求实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询功能开关状态接口请求实体")
public class LiveAccountSwitchRequest extends LiveCommonRequest {
    
    /**
     * 频道号，不传该参数为获取全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传该参数为获取全局设置", required = false)
    private Integer channelId;
    
}
