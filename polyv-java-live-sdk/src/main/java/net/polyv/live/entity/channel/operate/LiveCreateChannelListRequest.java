package net.polyv.live.entity.channel.operate;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.dto.LiveChannelBasicDTO;

/**
 * 批量创建频道请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("批量创建频道请求体")
public class LiveCreateChannelListRequest extends LiveCommonRequest {
    
    /**
     * 频道列表
     */
    @ApiModelProperty(name = "channels", value = "频道列表", required = true)
    private List<LiveChannelBasicDTO> channels;

}