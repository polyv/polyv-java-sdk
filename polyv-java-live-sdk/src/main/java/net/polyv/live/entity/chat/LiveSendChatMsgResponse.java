package net.polyv.live.entity.chat;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 通过HTTP接口发送聊天消息签到记录
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("通过HTTP接口发送聊天消息响应实体")
public class LiveSendChatMsgResponse  {
    
    /**
     * 发送消息ID
     */
    @ApiModelProperty(name = "msgId", value = "发送消息ID", required = false)
    private String msgId;
    
}
