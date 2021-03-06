package net.polyv.live.v1.entity.web.info;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道点赞数和观看热度值请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道点赞数和观看热度值请求实体")
public class LiveUpdateChannelLikesRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 点赞数,likes跟viewers可以同时传，也可以只传其中一个，不能都不传
     */
    @ApiModelProperty(name = "likes", value = "点赞数,likes跟viewers可以同时传，也可以只传其中一个，不能都不传", required = false)
    private Integer likes;
    
    /**
     * 观看热度,likes跟viewers可以同时传，也可以只传其中一个，不能都不传
     */
    @ApiModelProperty(name = "viewers", value = "观看热度，likes跟viewers可以同时传，也可以只传其中一个，不能都不传", required = false)
    private Integer viewers;

}
