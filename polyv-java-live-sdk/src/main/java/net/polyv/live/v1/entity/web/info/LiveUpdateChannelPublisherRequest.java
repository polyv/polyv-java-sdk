package net.polyv.live.v1.entity.web.info;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置主持人姓名请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置主持人姓名请求实体")
public class LiveUpdateChannelPublisherRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道号，非必填，不提交默认为修改该用户的所有频道号的主持人姓名
     */
    @ApiModelProperty(name = "channelId", value = "频道号，非必填，不提交默认为修改该用户的所有频道号的主持人姓名", required = false)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 主持人姓名，不超过20个字符
     */
    @ApiModelProperty(name = "publisher", value = "主持人姓名，不超过20个字符", required = true)
    @NotNull(message = "属性publisher不能为空")
    private String publisher;
    
}
