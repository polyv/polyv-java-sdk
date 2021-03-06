package net.polyv.live.v1.entity.web.auth;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 查询频道观看白名单列表请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道观看白名单列表请求体")
public class LiveChannelWhiteListRequest extends LivePageCommonRequest {
    
    /**
     * 频道号，不传为获取全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为获取全局设置", required = false)
    private String channelId;
    
    /**
     * 1为首要条件，2为次要条件
     */
    @ApiModelProperty(name = "rank", value = "1为首要条件，2为次要条件", required = true)
    @NotNull(message = "属性rank不能为空")
    private Integer rank;
    
    /**
     * 关键词，可根据会员码和名称查询
     */
    @ApiModelProperty(name = "keyword", value = "关键词，可根据会员码和名称查询", required = false)
    private String keyword;
    
}
