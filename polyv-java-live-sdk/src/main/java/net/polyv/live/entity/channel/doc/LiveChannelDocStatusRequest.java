package net.polyv.live.entity.channel.doc;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道文档转换状态请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
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
     * {@link net.polyv.live.service.channel.LiveChannelDocImplTest#testListChannelDoc()}
     */
    @ApiModelProperty(name = "fileId", value = "文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串)", required = true)
    @NotNull(message = "属性fileId不能为空")
    private String fileId;
    
}
