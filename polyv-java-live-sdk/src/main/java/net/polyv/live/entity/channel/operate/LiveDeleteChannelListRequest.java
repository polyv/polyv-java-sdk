package net.polyv.live.entity.channel.operate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 批量删除频道请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量删除频道请求体")
public class LiveDeleteChannelListRequest extends LiveCommonRequest {
    
    /**
     * 频道号列表，每次最多删除100个频道， 必须放在请求体中
     */
    @ApiModelProperty(name = "channelIds", value = "频道号列表，每次最多删除100个频道，必须放在请求体中", required = true)
    @NotNull(message = "属性channelIds不能为空")
    private String[] channelIds;
    
}
