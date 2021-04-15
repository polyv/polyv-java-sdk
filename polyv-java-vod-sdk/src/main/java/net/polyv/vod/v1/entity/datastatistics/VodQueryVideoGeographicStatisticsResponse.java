package net.polyv.vod.v1.entity.datastatistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频播放地理位置统计数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频播放地理位置统计数据返回实体")
public class VodQueryVideoGeographicStatisticsResponse {
    /**
     * 省份
     */
    @ApiModelProperty(name = "province", value = "省份", required = false)
    private String province;
    
    /**
     * pc端播放时长，单位为秒
     */
    @ApiModelProperty(name = "pcPlayDuration", value = "pc端播放时长，单位为秒", required = false)
    private Integer pcPlayDuration;
    
    /**
     * 播放时长，格式 hh:mm:ss 例如00:03:22
     */
    @ApiModelProperty(name = "formatPcPlayDuration", value = "播放时长，格式 hh:mm:ss 例如00:03:22", required = false)
    private String formatPcPlayDuration;
    
    /**
     * PC端消耗流量,单位字节
     */
    @ApiModelProperty(name = "pcFlowSize", value = "PC端消耗流量,单位字节", required = false)
    private Long pcFlowSize;
    
    /**
     * PC端播放量
     */
    @ApiModelProperty(name = "pcVideoView", value = "PC端播放量", required = false)
    private Integer pcVideoView;
    
    /**
     * PC端观众量
     */
    @ApiModelProperty(name = "pcUniqueViewer", value = "PC端观众量", required = false)
    private Integer pcUniqueViewer;
    
    /**
     * 移动端播放时长，单位为秒
     */
    @ApiModelProperty(name = "mobilePlayDuration", value = "移动端播放时长，单位为秒", required = false)
    private Integer mobilePlayDuration;
    
    /**
     * 移动端播放时长，格式 hh:mm:ss 例如00:03:22
     */
    @ApiModelProperty(name = "formatMobilePlayDuration", value = "移动端播放时长，格式 hh:mm:ss 例如00:03:22", required = false)
    private String formatMobilePlayDuration;
    
    /**
     * 移动端消耗流量,单位字节
     */
    @ApiModelProperty(name = "mobileFlowSize", value = "移动端消耗流量,单位字节", required = false)
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
