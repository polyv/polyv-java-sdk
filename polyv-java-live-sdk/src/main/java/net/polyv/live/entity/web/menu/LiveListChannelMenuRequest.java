package net.polyv.live.entity.web.menu;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道的菜单信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道的菜单信息请求实体")
public class LiveListChannelMenuRequest extends LiveCommonRequest {
    
    /**
     * 频道号，不传为获取全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为获取全局设置", required = true)
    @NotNull(message = "属性channelId不允许为空")
    private String channelId;
    
}
