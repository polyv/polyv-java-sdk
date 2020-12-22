package net.polyv.live.v1.entity.channel.viewdata;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道观看日志返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道观看日志返回实体")
@Deprecated
public class LiveChannelViewlogResponse {
    
    @ApiModelProperty(name = "liveChannelViewlogs", value = "频道观看日志信息", required = false)
    private List<LiveListChannelViewlogResponse.LiveChannelViewlog> liveChannelViewlogs;
    
   
}
