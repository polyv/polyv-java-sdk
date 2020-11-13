package net.polyv.live.entity.web.auth;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 下载频道登记观看记录请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("下载频道登记观看记录请求实体")
public class LiveDownloadChannelAuthInfoRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 1为首要条件，2为次要条件。影响导出的表格表头
     */
    @ApiModelProperty(name = "rank", value = "1为首要条件，2为次要条件。影响导出的表格表头", required = true)
    @NotNull(message = "属性rank不能为空")
    private Integer rank;
    
}
