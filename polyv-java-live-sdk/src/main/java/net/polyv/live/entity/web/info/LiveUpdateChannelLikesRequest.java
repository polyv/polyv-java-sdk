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
 * 设置频道点赞数和观看热度值请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置频道点赞数和观看热度值请求实体")
public class LiveUpdateChannelLikesRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
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
