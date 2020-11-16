package net.polyv.live.v1.entity.web.auth;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 更新白名单请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("更新白名单请求实体")
public class LiveUpdateChannelWriteListRequest extends LiveCommonRequest {
    
    /**
     * 频道号（传频道号则修改频道观看白名单，不传频道号则修改全局观看白名单）
     */
    @ApiModelProperty(name = "channelId", value = "频道号（传频道号则修改频道观看白名单，不传频道号则修改全局观看白名单）", required = false)
    private String channelId;
    
    /**
     * 主要观看条件为1,次要观看条件为2
     */
    @ApiModelProperty(name = "rank", value = "主要观看条件为1,次要观看条件为2", required = true)
    @NotNull(message = "属性rank不能为空")
    private Integer rank;
    
    /**
     * 旧会员码
     */
    @ApiModelProperty(name = "oldCode", value = "旧会员码", required = true)
    @NotNull(message = "属性oldCode不能为空")
    private String oldCode;
    
    /**
     * 会员码（最多为50个字符）
     */
    @ApiModelProperty(name = "code", value = "会员码（最多为50个字符）", required = true)
    @NotNull(message = "属性code不能为空")
    private String code;
    
    /**
     * 昵称（最多为50个字符）
     */
    @ApiModelProperty(name = "name", value = "昵称（最多为50个字符）", required = true)
    @NotNull(message = "属性name不能为空")
    private String name;

}
