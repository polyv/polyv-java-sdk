package net.polyv.live.v1.entity.chat;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 删除单条聊天记录请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
