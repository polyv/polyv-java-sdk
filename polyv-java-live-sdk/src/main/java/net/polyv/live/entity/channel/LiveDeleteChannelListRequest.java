package net.polyv.live.entity.channel;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 批量删除频道请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("批量删除频道请求体")
public class LiveDeleteChannelListRequest extends LiveCommonRequest {
    
    /**
     * 频道ID列表，每次最多删除100个频道， 必须放在请求体中
     */
    @ApiModelProperty(name = "channelIds", value = "频道ID列表，每次最多删除100个频道，必须放在请求体中", required = true)
    @NotNull(message = "channelIds不能为空")
    private Integer[] channelIds;
    
}
