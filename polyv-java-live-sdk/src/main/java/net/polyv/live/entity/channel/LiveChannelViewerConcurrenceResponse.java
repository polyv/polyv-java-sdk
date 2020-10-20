package net.polyv.live.entity.channel;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道的历史并发人数返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道的历史并发人数返回实体")
public class LiveChannelViewerConcurrenceResponse {
    
    @ApiModelProperty(name = "channelViewerConcurrences", value = "频道并发在线人数", required = false)
    private List<ChannelViewerConcurrence> channelViewerConcurrences;
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("频道并发在线人数")
    public static class ChannelViewerConcurrence {
        
        /**
         * 统计的日期（时间格式：yyyy-MM-dd,例：2019-04-10）
         */
        @ApiModelProperty(name = "day", value = "统计的日期（时间格式：yyyy-MM-dd,例：2019-04-10）", required = false)
        @JSONField(name = "day",format = "yyyy-MM-dd")
        private Date day;
        
        /**
         * 统计的时间点（时间格式：12H，例：10:30）
         */
        @ApiModelProperty(name = "minute", value = "统计的时间点（时间格式：12H，例：10:30）", required = false)
        private String minute;
        
        /**
         * 某个时间点实时观看人数
         */
        @ApiModelProperty(name = "viewers", value = "某个时间点实时观看人数", required = false)
        private String viewers;
        
    }
    
}
