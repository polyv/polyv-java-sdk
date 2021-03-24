package net.polyv.vod.v1.entity.datastatistics;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 查询视频播放时段统计数据请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询视频播放时段统计数据请求实体")
public class VodQueryVideoPlaybackHourlyStatisticsRequest extends VodCommonRequest {
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    @JSONField(name = "userid")
    private String userId;
    
    /**
     * 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month
     * （上个月），this_year（今年），last_year（去年），默认值为7days:最近7天
     */
    @ApiModelProperty(name = "dr", value = "时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days" +
            "（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天", required = false)
    private String dr;
    
    /**
     * 查询开始日期，格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "start", value = "查询开始日期，格式为yyyy-MM-dd", required = false)
    @JSONField(name = "start", format = "yyyy-MM-dd")
    private Date startTime;
    
    /**
     * 查询结束日期，格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "end", value = "查询结束日期，格式为yyyy-MM-dd", required = false)
    @JSONField(name = "end", format = "yyyy-MM-dd")
    private Date endTime;
}
