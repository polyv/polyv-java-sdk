package net.polyv.live.entity.channel.viewdata;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询多个频道的实时在线人数请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询多个频道的实时在线人数请求实体")
public class LiveListChannelViewerCountRequest extends LiveCommonRequest {
    
    /**
     * 频道号，使用英文逗号分开，如：100000,100001
     */
    @ApiModelProperty(name = "channelIds", value = "频道号，使用英文逗号分开，如：100000,100001", required = true)
    @NotNull(message = "属性channelIds不能为空")
    private String channelIds;
    
}
