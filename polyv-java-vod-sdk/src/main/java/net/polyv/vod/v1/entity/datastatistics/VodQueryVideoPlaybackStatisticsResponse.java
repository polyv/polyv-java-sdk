package net.polyv.vod.v1.entity.datastatistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频播放量统计数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频播放量统计数据返回实体")
public class VodQueryVideoPlaybackStatisticsResponse {
    /**
     * 当前日期，格式为：yyyy-MM-dd或yyyy-MM
     */
    @ApiModelProperty(name = "currentTime", value = "当前日期，格式为：yyyy-MM-dd或yyyy-MM", required = false)
    private String currentTime;
    
    /**
     * pc端播放量
     */
    @ApiModelProperty(name = "pcVideoView", value = "pc端播放量", required = false)
    private Integer pcVideoView;
    
    /**
     * 移动端播放量
     */
    @ApiModelProperty(name = "mobileVideoView", value = "移动端播放量", required = false)
    private Integer mobileVideoView;
}
