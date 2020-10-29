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
 * 删除单条聊天记录请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("删除单条聊天记录请求实体")
public class LiveChatDelSingleMsgRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    /**
     * 	聊天记录对应的id
     */
    @ApiModelProperty(name = "id", value = "聊天记录对应的id", required = true)
    @NotNull(message = "属性id不能为空")
    private String id;
    
}
