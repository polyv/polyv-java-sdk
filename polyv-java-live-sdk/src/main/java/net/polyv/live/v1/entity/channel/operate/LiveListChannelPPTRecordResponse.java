package net.polyv.live.v1.entity.channel.operate;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.LivePageCommonResponse;

/**
 * 查询课件重制任务列表返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询课件重制任务列表返回实体")
public class LiveListChannelPPTRecordResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "课件重制任务列表")
    private List<LivePPTRecord> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("查询课件重制任务列表返回信息")
    public class LivePPTRecord {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "直播频道号")
        private String channelId;
        
        /**
         * 对应回放的名称
         */
        @ApiModelProperty(name = "title", value = "对应回放的名称")
        private String title;
        
        /**
         * 重制mp4下载地址，有24小时的防盗链超时时间
         */
        @ApiModelProperty(name = "url", value = "重制mp4下载地址，有24小时的防盗链超时时间")
        private String url;
        
        /**
         * 场次id
         */
        @ApiModelProperty(name = "sessionId", value = "场次id")
        private String sessionId;
        
        /**
         * 对应回放的直播开始时间,格式为yyyyMMddhhmmss
         */
        @ApiModelProperty(name = "startTime", value = "对应回放的直播开始时间,格式为yyyy-MM-dd HH:mm:ss")
        private String startTime;
        
        /**
         * @see LiveConstant.PPTStatus
         * 状态值
         */
        @ApiModelProperty(name = "sessionId", value = "状态值，分类可见LiveConstant.PPTStatus")
        private String status;
        
        /**
         * 重制剩余的过期时间，过期后将无法访问和下载
         */
        @ApiModelProperty(name = "remainDay", value = "重制剩余的过期时间，过期后将无法访问和下载")
        private Integer remainDay;
        
        /**
         * 重制的视频时长，单位秒
         */
        @ApiModelProperty(name = "duration", value = "重制的视频时长，单位秒")
        private Integer duration;
        
    }
}