package net.polyv.live.v1.entity.channel;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveSonChannelInfoResponse;

/**
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@ApiModel("快速创建频道响应实体")
public class QuickCreateChannelResponse {
    
    @ApiModelProperty(name = "liveChannelBasicInfoResponse", value = "频道信息")
    private LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
    
    @ApiModelProperty(name = "sonChannelInfos", value = "子频道信息")
    private List<LiveSonChannelInfoResponse> sonChannelInfos;
    
}
