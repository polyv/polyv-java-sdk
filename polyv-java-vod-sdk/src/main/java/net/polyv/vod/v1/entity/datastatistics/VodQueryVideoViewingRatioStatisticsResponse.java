package net.polyv.vod.v1.entity.datastatistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频的观看比例统计数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频的观看比例统计数据返回实体")
public class VodQueryVideoViewingRatioStatisticsResponse {
    /**
     * 观看比例范围，单位：% 例如 70-80
     */
    @ApiModelProperty(name = "percentage", value = "观看比例范围，单位：% 例如 70-80", required = false)
    private String percentage;
    /**
     * 观看数量
     */
    @ApiModelProperty(name = "playCount", value = "观看数量", required = false)
    private Integer playCount;
}
