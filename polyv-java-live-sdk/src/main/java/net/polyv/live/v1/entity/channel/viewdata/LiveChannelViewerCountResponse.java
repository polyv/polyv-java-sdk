package net.polyv.live.v1.entity.channel.viewdata;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道实时在线人数返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道实时在线人数返回实体")
public class LiveChannelViewerCountResponse {
    
    /**
     * 频道实时在线人数,10秒一个点，15条数据
     */
    @ApiModelProperty(name = "channelViewerCounts", value = "频道实时在线人数,10秒一个点，15条数据", required = false)
    private List<ChannelViewerCount> channelViewerCounts;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("频道实时在线人数")
    public static class ChannelViewerCount{
        
        /**
         * 统计的时间点（时间格式：12H）,如：04:01:38
         */
        @ApiModelProperty(name = "time", value = "统计的时间点（时间格式：12H）,如：04:01:38", required = false)
        private String time;
    
        /**
         * 某个时间点实时观看人数
         */
        @ApiModelProperty(name = "count", value = "某个时间点实时观看人数", required = false)
        private String count;
        
    }

}
