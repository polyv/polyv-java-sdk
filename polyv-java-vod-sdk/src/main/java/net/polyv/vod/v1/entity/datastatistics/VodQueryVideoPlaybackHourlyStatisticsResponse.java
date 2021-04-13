package net.polyv.vod.v1.entity.datastatistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频播放时段统计数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频播放时段统计数据返回实体")
public class VodQueryVideoPlaybackHourlyStatisticsResponse {
    /**
     * 时间段，24小时制，例如 18
     */
    @ApiModelProperty(name = "currentHour", value = "时间段，24小时制，例如 18", required = false)
    private Integer currentHour;
    
    /**
     * pc播放时长,单位为秒
     */
    @ApiModelProperty(name = "pcPlayDuration", value = "pc播放时长,单位为秒", required = false)
    private Integer pcPlayDuration;
    
    /**
     * pc播放时长，格式 hh:mm:ss 例如03：02：22
     */
    @ApiModelProperty(name = "formatPcPlayDuration", value = "pc播放时长，格式 hh:mm:ss 例如03：02：22", required = false)
    private String formatPcPlayDuration;
    
    /**
     * pc消耗流量,单位为字节
     */
    @ApiModelProperty(name = "pcFlowSize", value = "pc消耗流量,单位为字节", required = false)
    private Long pcFlowSize;
    
    /**
     * pc端播放量
     */
    @ApiModelProperty(name = "pcVideoView", value = "pc端播放量", required = false)
    private Integer pcVideoView;
    
    /**
     * pc端观众量
     */
    @ApiModelProperty(name = "pcUniqueViewer", value = "pc端观众量", required = false)
    private Integer pcUniqueViewer;
    
    /**
     * 移动端播放时长，单位为秒
     */
    @ApiModelProperty(name = "mobilePlayDuration", value = "移动端播放时长，单位为秒", required = false)
    private Integer mobilePlayDuration;
    
    /**
     * 移动端播放时长，格式 hh:mm:ss 例如03：02：22
     */
    @ApiModelProperty(name = "formatMobilePlayDuration", value = "移动端播放时长，格式 hh:mm:ss 例如03：02：22", required = false)
    private String formatMobilePlayDuration;
    
    /**
     * 移动端消耗流量,单位为字节
     */
    @ApiModelProperty(name = "mobileFlowSize", value = "移动端消耗流量,单位为字节", required = false)
    private Long mobileFlowSize;
    
    /**
     * 移动端播放量
     */
    @ApiModelProperty(name = "mobileVideoView", value = "移动端播放量", required = false)
    private Integer mobileVideoView;
    
    /**
     * 移动端观众量
     */
    @ApiModelProperty(name = "mobileUniqueViewer", value = "移动端观众量", required = false)
    private Integer mobileUniqueViewer;
}
