package net.polyv.live.entity.channel.viewdata;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询多个频道汇总的统计数据返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询多个频道汇总的统计数据返回实体")
public class LiveListChannelSummaryResponse {
    
    /**
     * 统计数据
     */
    @ApiModelProperty(name = "channelSummarys", value = "统计数据", required = false)
    private List<ChannelSummary> channelSummarys;
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("统计数据")
    public static class ChannelSummary {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 频道名称
         */
        @ApiModelProperty(name = "name", value = "频道名称", required = false)
        private String name;
        
        /**
         * pc端播放时长，单位：分钟
         */
        @ApiModelProperty(name = "pcPlayDuration", value = "pc端播放时长，单位：分钟", required = false)
        private Integer pcPlayDuration;
        
        /**
         * pc端播放流量，单位为Byte
         */
        @ApiModelProperty(name = "pcFlowSize", value = "pc端播放流量，单位为Byte", required = false)
        private Long pcFlowSize;
        
        /**
         * pc视频播放量
         */
        @ApiModelProperty(name = "pcVideoView", value = "pc视频播放量", required = false)
        private Integer pcVideoView;
        
        /**
         * pc端唯一观众数
         */
        @ApiModelProperty(name = "pcUniqueViewer", value = "pc端唯一观众数", required = false)
        private Integer pcUniqueViewer;
        
        /**
         * 移动端播放时长，单位：分钟
         */
        @ApiModelProperty(name = "mobilePlayDuration", value = "移动端播放时长，单位：分钟", required = false)
        private Long mobilePlayDuration;
        
        /**
         * 移动端播放流量，单位为Byte
         */
        @ApiModelProperty(name = "mobileFlowSize", value = "移动端播放流量，单位为Byte", required = false)
        private Long mobileFlowSize;
        
        /**
         * 移动端播放量
         */
        @ApiModelProperty(name = "mobileVideoView", value = "移动端播放量", required = false)
        private Integer mobileVideoView;
        
        /**
         * 移动端唯一观众数
         */
        @ApiModelProperty(name = "mobileUniqueViewer", value = "移动端唯一观众数", required = false)
        private Integer mobileUniqueViewer;
        
        /**
         * PC直播播放时长，单位为分钟
         */
        @ApiModelProperty(name = "livePcPlayDuration", value = "PC直播播放时长，单位为分钟", required = false)
        private Integer livePcPlayDuration;
        
        /**
         * PC回放播放时长，单位为分钟
         */
        @ApiModelProperty(name = "playbackPcPlayDuration", value = "PC回放播放时长，单位为分钟", required = false)
        private Long playbackPcPlayDuration;
        
        /**
         * 移动端直播播放时长，单位为分钟
         */
        @ApiModelProperty(name = "liveMobilePlayDuration", value = "移动端直播播放时长，单位为分钟", required = false)
        private Integer liveMobilePlayDuration;
        
        /**
         * 移动端回放播放时长，单位为分钟
         */
        @ApiModelProperty(name = "playbackMobilePlayDuration", value = "移动端回放播放时长，单位为分钟", required = false)
        private Long playbackMobilePlayDuration;
        
        /**
         * pc其他播放时长，单位为分钟
         */
        @ApiModelProperty(name = "unknownPcPlayDuration", value = "pc其他播放时长，单位为分钟", required = false)
        private Integer unknownPcPlayDuration;
        
        /**
         * 移动端其他播放时长，单位为分钟
         */
        @ApiModelProperty(name = "unknownMobilePlayDuration", value = "移动端其他播放时长，单位为分钟", required = false)
        private Integer unknownMobilePlayDuration;
        
    }
    
}
