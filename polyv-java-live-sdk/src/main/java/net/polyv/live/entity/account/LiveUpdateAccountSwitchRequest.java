package net.polyv.live.entity.account;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置功能开关状态请求实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置功能开关状态请求实体")
public class LiveUpdateAccountSwitchRequest extends LiveCommonRequest {
    
    /**
     * 频道号，不传该参数则表示修改全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传该参数则表示修改全局设置", required = false)
    private Integer channelId;
    
    /**
     * 开关类型
     */
    @ApiModelProperty(name = "type", value = "开关类型", required = true)
    @NotNull(message = "属性type不能为空")
    private String type;
    
    /**
     * 开关值，Y或N
     */
    @ApiModelProperty(name = "enabled", value = "开关值，Y或N", required = true)
    @NotNull(message = "属性enabled不能为空")
    private String enabled;
    
}
