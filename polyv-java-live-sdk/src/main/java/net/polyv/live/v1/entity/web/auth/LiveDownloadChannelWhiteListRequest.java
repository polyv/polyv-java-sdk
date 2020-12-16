package net.polyv.live.v1.entity.web.auth;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 下载频道观看白名单列表请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("下载频道观看白名单列表请求实体")
public class LiveDownloadChannelWhiteListRequest extends LiveCommonRequest {
    
    /**
     * 频道号，要下载的频道号，不传为全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，要下载的频道号，不传为全局设置", required = false)
    private String channelId;
    
    /**
     * 1为首要条件，2为次要条件
     */
    @ApiModelProperty(name = "rank", value = "1为首要条件，2为次要条件", required = true)
    @NotNull(message = "属性rank不能为空")
    private Integer rank;
    
}
