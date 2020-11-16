package net.polyv.live.v1.entity.chat;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置聊天室禁言ip请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置聊天室禁言ip请求实体")
public class LiveChatBannedIPRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 禁言IP列表
     */
    @ApiModelProperty(name = "ip", value = "禁言IP,如 234.22.3.34", required = true)
    @NotNull(message = "属性ip不能为空")
    private String ip;
    
}
