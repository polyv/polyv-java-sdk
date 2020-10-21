package net.polyv.live.entity.chat;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 设置聊天室禁言ip响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置聊天室禁言ip响应实体")
public class LiveChatBannedIPResponse {
    
    /**
     * 禁言IP列表
     */
    @ApiModelProperty(name = "ip", value = "当前禁言IP列表", required = true)
    private List<String> ip;
    
}
