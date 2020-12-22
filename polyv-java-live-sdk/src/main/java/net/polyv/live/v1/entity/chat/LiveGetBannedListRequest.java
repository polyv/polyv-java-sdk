package net.polyv.live.v1.entity.chat;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询频道禁言列表请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道禁言列表请求实体")
public class LiveGetBannedListRequest extends LiveCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 禁言类型,ip/userId
     */
    @ApiModelProperty(name = "type", value = "查询禁言类型,ip ： 聊天室用户用户的机器ip ， userId ： 聊天室用户用户的userId", required = true)
    @NotNull(message = "属性type不能为空")
    private String type;
    
    /**
     * 是否获取子频道，0：不获取，1：获取
     */
    @ApiModelProperty(name = "toGetSubRooms", value = "是否获取子频道，0：不获取，1：获取，默认为 0 ", required = false)
    private Integer toGetSubRooms;
    
}
