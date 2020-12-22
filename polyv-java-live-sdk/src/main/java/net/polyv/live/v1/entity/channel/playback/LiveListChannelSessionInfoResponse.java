package net.polyv.live.v1.entity.channel.playback;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonResponse;

/**
 * 查询频道直播场次信息返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道直播场次信息返回实体")
public class LiveListChannelSessionInfoResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "频道直播场次信息", required = false)
    private List<LiveListChannelSessionInfoResponse.ChannelSessionInfo> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("频道直播场次信息")
    public static class ChannelSessionInfo {
        
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
         * 直播开始时间
         */
        @ApiModelProperty(name = "startTime", value = "直播开始时间", required = false)
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date startTime;
        
        /**
         * 直播结束时间
         */
        @ApiModelProperty(name = "endTime", value = "直播结束时间", required = false)
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date endTime;
        
    }
}
