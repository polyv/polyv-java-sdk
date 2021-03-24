package net.polyv.vod.v1.entity.datastatistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询播放域名统计数据接口返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询播放域名统计数据接口返回实体")
public class VodQueryPlayDomainNameStatisticsResponse {
    /**
     * 域名
     */
    @ApiModelProperty(name = "domain", value = "域名", required = false)
    private String domain;
    
    /**
     * PC端播放时长（单位：秒）
     */
    @ApiModelProperty(name = "pcPlayDuration", value = "PC端播放时长（单位：秒）", required = false)
    private Integer pcPlayDuration;
    
    /**
     * PC端消耗流量（单位：字节）
     */
    @ApiModelProperty(name = "pcFlowSize", value = "PC端消耗流量（单位：字节）", required = false)
    private Long pcFlowSize;
    
    /**
     * PC端总播放量
     */
    @ApiModelProperty(name = "pcVideoView", value = "PC端总播放量", required = false)
    private Integer pcVideoView;
    
    /**
     * PC端唯一观众数
     */
    @ApiModelProperty(name = "pcUniqueViewer", value = "PC端唯一观众数", required = false)
    private Integer pcUniqueViewer;
    
    /**
     * 移动端播放时长（单位：秒）
     */
    @ApiModelProperty(name = "mobilePlayDuration", value = "移动端播放时长（单位：秒）", required = false)
    private Integer mobilePlayDuration;
    
    /**
     * 移动端播放量
     */
    @ApiModelProperty(name = "mobileVideoView", value = "移动端播放量", required = false)
    private Integer mobileVideoView;
    
    /**
     * 移动端播放者数量
     */
    @ApiModelProperty(name = "mobileUniqueViewer", value = "移动端播放者数量", required = false)
    private Integer mobileUniqueViewer;
}
