package net.polyv.live.v1.entity.web.auth;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 删除白名单请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("删除白名单请求实体")
public class LiveDeleteChannelWriteListRequest extends LiveCommonRequest {
    
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
     * 是否一键清空白名单（Y ：清空白名单；N：根据code请求白名单，code）
     */
    @ApiModelProperty(name = "isClear", value = "是否一键清空白名单（Y ：清空白名单；N：根据code请求白名单，code）", required = true)
    @NotNull(message = "属性isClear不能为空")
    private String isClear;
    
    /**
     * 会员码（isClear 为N时为必传参数）
     */
    @ApiModelProperty(name = "code", value = "会员码（isClear 为N时为必传参数）", required = false)
    private String code;
    
}
