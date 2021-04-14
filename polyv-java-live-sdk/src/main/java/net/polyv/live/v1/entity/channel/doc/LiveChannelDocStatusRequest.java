package net.polyv.live.v1.entity.channel.doc;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询频道文档转换状态请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道文档转换状态请求实体")
public class LiveChannelDocStatusRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串)
     */
    @ApiModelProperty(name = "fileId", value = "文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串)", required = true)
    @NotNull(message = "属性fileId不能为空")
    private String fileId;
    
}
