package net.polyv.live.entity.channel.playback;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;

/**
 * 查询频道直播场次信息返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道直播场次信息返回实体")
public class LiveListChannelSessionInfoResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "频道直播场次信息", required = false)
    private List<LiveListChannelSessionInfoResponse.ChannelSessionInfo> contents;
    
    @Data
@EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("频道直播场次信息")
    public class ChannelSessionInfo {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 场次ID
         */
        @ApiModelProperty(name = "sessionId", value = "场次ID", required = false)
        private String sessionId;
        
        /**
         * 直播开始时间，13位时间戳
         */
        @ApiModelProperty(name = "startTime", value = "直播开始时间，13位时间戳", required = false)
        private String startTime;
        
        /**
         * 直播结束时间，13位时间戳
         */
        @ApiModelProperty(name = "endTime", value = "直播结束时间，13位时间戳", required = false)
        private String endTime;
        
    }
}
