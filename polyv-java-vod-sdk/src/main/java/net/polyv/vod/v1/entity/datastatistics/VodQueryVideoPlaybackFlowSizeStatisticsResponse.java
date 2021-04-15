package net.polyv.vod.v1.entity.datastatistics;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频某个时段的播放流量统计数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频某个时段的播放流量统计数据返回实体")
public class VodQueryVideoPlaybackFlowSizeStatisticsResponse {
    /**
     * 日期，格式 yyyy-MM-dd 例如 2021-03-24
     */
    @ApiModelProperty(name = "currentDay", value = "日期，格式 yyyy-MM-dd 例如 2021-03-24", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date currentDay;
    /**
     * PC端消耗流量,单位字节
     */
    @ApiModelProperty(name = "pcFlowSize", value = "PC端消耗流量,单位字节", required = false)
    private Long pcFlowSize;
    /**
     * 移动端消耗流量，单位字节
     */
    @ApiModelProperty(name = "mobileFlowSize", value = "移动端消耗流量，单位字节", required = false)
    private Long mobileFlowSize;
    /**
     * 总流量消耗，单位字节
     */
    @ApiModelProperty(name = "totalFlowSize", value = "总流量消耗，单位字节", required = false)
    private Long totalFlowSize;
}
