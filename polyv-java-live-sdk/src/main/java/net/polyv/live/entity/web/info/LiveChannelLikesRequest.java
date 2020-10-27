package net.polyv.live.entity.web.info;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道点赞数和观众热度值请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道点赞数和观众热度值请求实体")
public class LiveChannelLikesRequest extends LiveCommonRequest {
    
    /**
     * 用逗号隔开的频道号，如：10000,100001最多20个
     */
    @ApiModelProperty(name = "channelIds", value = "用逗号隔开的频道号，如：10000,100001最多20个", required = true)
    @NotNull(message = "属性channelIds不能为空")
    private String channelIds;
    
}
