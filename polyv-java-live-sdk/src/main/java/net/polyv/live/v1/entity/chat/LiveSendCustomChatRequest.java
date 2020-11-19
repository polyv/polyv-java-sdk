package net.polyv.live.v1.entity.chat;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 发送自定义聊天信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("发送自定义聊天信息请求实体")
public class LiveSendCustomChatRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * POLYV用户ID，可通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 需要发送的文字, 需要进行base64编码，content、imgUrl不能同时为空，可以同时提交
     */
    @ApiModelProperty(name = "content", value = "需要发送的文字, 需要进行base64编码，content、imgUrl不能同时为空，可以同时提交", required = false)
    private String content;
    
    /**
     * 需要发送的图片，content、imgUrl不能同时为空，可以同时提交
     */
    @ApiModelProperty(name = "imgUrl", value = "需要发送的图片，content、imgUrl不能同时为空，可以同时提交", required = false)
    private String imgUrl;
    
}
