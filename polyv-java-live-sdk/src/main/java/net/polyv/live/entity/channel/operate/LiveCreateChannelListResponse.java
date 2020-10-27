package net.polyv.live.entity.channel.operate;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.channel.operate.LiveChannelResponse;

/**
 * 批量创建频道返回体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("批量创建频道返回体")
public class LiveCreateChannelListResponse {
    
    @ApiModelProperty(name = "channels", value = "频道基本信息")
    private List<LiveChannelResponse> channels;

}
