package net.polyv.vod.v1.entity.datastatistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 高级分析-根据视频id查询视频分析数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("高级分析-根据视频id查询视频分析数据返回实体")
public class VodQueryVideoAnalysisDataResponse {
    /**
     * 视频id
     */
    @ApiModelProperty(name = "videoId", value = "视频id", required = false)
    private String videoId;
    
    /**
     * 视频名称
     */
    @ApiModelProperty(name = "videoName", value = "视频名称", required = false)
    private String videoName;
    
    /**
     * 视频时长，单位：秒
     */
    @ApiModelProperty(name = "duration", value = "视频时长，单位：秒", required = false)
    private Integer duration;
    
    /**
     * 播放次数
     */
    @ApiModelProperty(name = "playTimes", value = "播放次数", required = false)
    private Integer playTimes;
    
    /**
     * 唯一观众数
     */
    @ApiModelProperty(name = "uniqueViewerCount", value = "唯一观众数", required = false)
    private Integer uniqueViewerCount;
    
    /**
     * 平均观看完成度
     */
    @ApiModelProperty(name = "avgCompletionRate", value = "平均观看完成度", required = false)
    private Float avgCompletionRate;
    
    /**
     * 观看热力图，例如[\"0-20:662\",\"21-100:665\"]代表视频内容的0~20秒有662次观看，21~100秒有665次观看
     */
    @ApiModelProperty(name = "viewHeatmap", value = "观看热力图，例如[\"0-20:662\",\"21-100:665\"]代表视频内容的0~20秒有662次观看，21~100" +
            "秒有665次观看", required = false)
    private String viewHeatmap;
    
    /**
     * 唯一观看热力图，例如[\"0-20:614\",\"21-100:615\"]代表视频内容的0~20秒有614个观众观看，21~100秒有615个观众观看
     */
    @ApiModelProperty(name = "uniqueViewHeatmap", value = "唯一观看热力图，例如[\"0-20:614\"," +
            "\"21-100:615\"]代表视频内容的0~20秒有614个观众观看，21~100秒有615个观众观看", required = false)
    private String uniqueViewHeatmap;
}
