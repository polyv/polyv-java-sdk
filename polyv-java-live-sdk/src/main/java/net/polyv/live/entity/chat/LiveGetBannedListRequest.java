package net.polyv.live.entity.chat;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道禁言列表请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道禁言列表请求实体")
public class LiveGetBannedListRequest extends LiveCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private Integer channelId;
    
    /**
     * 禁言类型,ip/userId
     */
    @ApiModelProperty(name = "type", value = "查询禁言类型,ip / userId", required = false)
    private String type;
    
    /**
     * 是否获取子频道，0：不获取，1：获取
     */
    @ApiModelProperty(name = "toGetSubRooms", value = "是否获取子频道，0：不获取，1：获取", required = false)
    private Integer toGetSubRooms;
    
}
