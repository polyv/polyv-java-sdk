package net.polyv.live.entity.channel.viewdata;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询多个频道的实时在线人数返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询多个频道的实时在线人数返回实体")
public class LiveListChannelViewerCountResponse {
    
    @ApiModelProperty(name = "channelViewerCounts", value = "频道实时在线人数", required = false)
    private List<ChannelViewerCount> channelViewerCounts;
    
    @Data
@EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("频道实时在线人数")
    public static class ChannelViewerCount {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 统计的时间点（时间格式：12H）
         */
        @ApiModelProperty(name = "time", value = "统计的时间点（时间格式：12H）", required = false)
        private String time;
        
        /**
         * 某个时间点实时观看人数
         */
        @ApiModelProperty(name = "count", value = "某个时间点实时观看人数", required = false)
        private String count;
        
    }
    
}
