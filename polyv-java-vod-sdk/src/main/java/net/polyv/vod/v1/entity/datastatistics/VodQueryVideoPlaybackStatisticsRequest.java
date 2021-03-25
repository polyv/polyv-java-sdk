package net.polyv.vod.v1.entity.datastatistics;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 查询视频播放量统计数据接口请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询视频播放量统计数据接口请求实体")
public class VodQueryVideoPlaybackStatisticsRequest extends VodCommonRequest {
    
    /**
     * 视频ID，不填vid会查所有视频的播放量统计数据
     */
    @ApiModelProperty(name = "videoId", value = "视频ID，不填vid会查所有视频的播放量统计数据", required = false)
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month
     * （上个月），this_year（今年），last_year（去年），默认值为7days:最近7天
     */
    @ApiModelProperty(name = "dr", value = "时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days" +
            "（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天", required = false)
    private String dr;
    
    /**
     * 显示周期，具体为以下几个值：daily（按日显示），weekly（按周显示），monthly（按月显示）。默认值为daily：按日显示。period的值受限于dr的值，当dr的值为today，yesterday
     * ，this_week，last_week，7days时，period只能为daily，当dr的值为this_month，last_month时，period只能为daily或者weekly
     */
    @ApiModelProperty(name = "videoId", value = "显示周期，具体为以下几个值：daily（按日显示），weekly（按周显示），monthly（按月显示）。默认值为daily" +
            "：按日显示。period的值受限于dr的值，当dr的值为today，yesterday，this_week，last_week，7days时，period只能为daily，当dr的值为this_month" +
            "，last_month时，period只能为daily或者weekly", required = false)
    @JSONField(name = "period")
    private String period;
}
