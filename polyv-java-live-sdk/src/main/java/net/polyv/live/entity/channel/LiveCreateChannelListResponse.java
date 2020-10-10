package net.polyv.live.entity.channel;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 批量创建频道返回体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("批量创建频道返回体")
public class LiveCreateChannelListResponse {
    
    @ApiModelProperty(name = "channels", value = "频道基本信息")
    private List<LiveChannelResponse> channels;

}
