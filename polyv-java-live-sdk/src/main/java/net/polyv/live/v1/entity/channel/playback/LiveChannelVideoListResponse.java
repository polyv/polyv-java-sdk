package net.polyv.live.v1.entity.channel.playback;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道录制视频信息返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道录制视频信息返回实体")
public class LiveChannelVideoListResponse {
    
    @ApiModelProperty(name = "channelVedioInfos", value = "视频库视频信息", required = false)
    private List<ChannelVedioInfo> channelVedioInfos;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("直播视频库视频信息")
    public static class ChannelVedioInfo {
        
        /**
         * 录制文件id
         */
        @ApiModelProperty(name = "fileId", value = "录制文件id", required = false)
        private String fileId;
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 录制文件地址，优先返回mp4，若没有MP4会返回m3u8
         */
        @ApiModelProperty(name = "url", value = "录制文件地址，优先返回mp4，若没有MP4会返回m3u8", required = false)
        private String url;
        
        /**
         * 开始录制时间
         */
        @ApiModelProperty(name = "startTime", value = "开始录制时间", required = false)
        @JSONField(format = "yyyyMMddHHmmss")
        private Date startTime;
        
        /**
         * 结束录制时间
         */
        @ApiModelProperty(name = "endTime", value = "结束录制时间", required = false)
        @JSONField(format = "yyyyMMddHHmmss")
        private Date endTime;
        
        /**
         * 录制文件大小（单位：字节）
         */
        @ApiModelProperty(name = "fileSize", value = "录制文件大小（单位：字节）", required = false)
        private Long fileSize;
        
        /**
         * 时长（单位：秒）
         */
        @ApiModelProperty(name = "duration", value = "时长（单位：秒）", required = false)
        private Integer duration;
        
        /**
         * 录制文件码率（单位：字节）
         */
        @ApiModelProperty(name = "bitrate", value = "录制文件码率（单位：字节）", required = false)
        private Integer bitrate;
        
        /**
         * 分辨率
         */
        @ApiModelProperty(name = "resolution", value = "分辨率", required = false)
        private String resolution;
        
        /**
         * 直播的场次id
         */
        @ApiModelProperty(name = "channelSessionId", value = "直播的场次id", required = false)
        private String channelSessionId;
        
        /**
         * 录制文件名称
         */
        @ApiModelProperty(name = "fileName", value = "录制文件名称", required = false)
        private String fileName;
        
    }
    
}
