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
 * 设置主持人姓名请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置主持人姓名请求实体")
public class LiveUpdateChannelPublisherRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道ID，非必填，不提交默认为修改该用户的所有频道ID的主持人姓名
     */
    @ApiModelProperty(name = "channelId", value = "频道ID，非必填，不提交默认为修改该用户的所有频道ID的主持人姓名", required = false)
    @NotNull(message = "channelId不能为空")
    private Integer channelId;
    
    /**
     * 主持人姓名，不超过20个字符
     */
    @ApiModelProperty(name = "publisher", value = "主持人姓名，不超过20个字符", required = true)
    @NotNull(message = "publisher不能为空")
    private String publisher;
    
}
