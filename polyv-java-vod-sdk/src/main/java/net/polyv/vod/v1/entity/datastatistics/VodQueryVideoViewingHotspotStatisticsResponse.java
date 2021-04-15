package net.polyv.vod.v1.entity.datastatistics;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询单个视频的观看热点统计数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询单个视频的观看热点统计数据返回实体")
public class VodQueryVideoViewingHotspotStatisticsResponse {
    /**
     * 视频时长（单位：秒）
     */
    @ApiModelProperty(name = "second", value = "视频时长（单位：秒）", required = false)
    private Integer second;
    /**
     * 播放量
     */
    @ApiModelProperty(name = "viewCount", value = "播放量", required = false)
    @JSONField(name = "viewcount")
    private Integer viewCount;
}
