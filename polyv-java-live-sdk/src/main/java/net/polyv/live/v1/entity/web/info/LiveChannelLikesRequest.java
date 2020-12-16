package net.polyv.live.v1.entity.web.info;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询频道点赞数和观众热度值请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道点赞数和观众热度值请求实体")
public class LiveChannelLikesRequest extends LiveCommonRequest {
    
    /**
     * 用逗号隔开的频道号，如：10000,100001最多20个
     */
    @ApiModelProperty(name = "channelIds", value = "用逗号隔开的频道号，如：10000,100001最多20个", required = true)
    @NotNull(message = "属性channelIds不能为空")
    private String channelIds;
    
}
