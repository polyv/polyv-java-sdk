package net.polyv.live.entity.web.auth;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道或全局登记观看字段请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道或全局登记观看字段请求实体")
public class LiveChannelAuthFieldRequest extends LiveCommonRequest {
    
    /**
     * 主要观看条件为1,次要观看条件为2
     */
    @ApiModelProperty(name = "rank", value = "主要观看条件为1,次要观看条件为2", required = true)
    @NotNull(message = "属性rank不能为空")
    private Integer rank;
    
    /**
     * 频道号，不填为获取全局
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不填为获取全局", required = false)
    private String channelId;
    
}
