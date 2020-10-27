package net.polyv.live.entity.web.menu;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道的菜单信息请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道的菜单信息请求实体")
public class LiveListChannelMenuRequest extends LiveCommonRequest {
    
    /**
     * 频道号，不传为获取全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为获取全局设置", required = true)
    @NotNull(message = "属性channelId不允许为空")
    private Integer channelId;
    
}
