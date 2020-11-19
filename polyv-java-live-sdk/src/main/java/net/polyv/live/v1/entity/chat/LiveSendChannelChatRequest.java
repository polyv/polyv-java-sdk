package net.polyv.live.v1.entity.chat;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 管理员发送频道聊天信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("管理员发送频道聊天信息请求实体")
public class LiveSendChannelChatRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 聊天信息内容
     */
    @ApiModelProperty(name = "content", value = "聊天信息内容", required = true)
    @NotNull(message = "属性content不能为空")
    private String content;
    
    /**
     * 发送人角色（目前为只提供管理员角色，值为"ADMIN"）
     */
    @ApiModelProperty(name = "role", value = "发送人角色（目前为只提供管理员角色，值为'ADMIN'）", required = true)
    @NotNull(message = "属性role不能为空")
    private String role;
    
}
