package net.polyv.vod.v1.entity.datastatistics;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 查询视频播放量排行接口请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询视频播放量排行接口请求实体")
public class VodQueryVideoPlaybackRankingRequest extends VodCommonRequest {
    
    /**
     * 时间段，具体值为以下几个：today（今天），yesterday（昨天），7days（最近7天），30days（最近30天），默认值为7days：最近7天，当start和end都不为空时，dr失效，当start和end
     * 其中一个为空，查询最近7天数据，当start和end都为空，以dr为准
     */
    @ApiModelProperty(name = "dr", value = "时间段，具体值为以下几个：today（今天），yesterday（昨天），7days（最近7天），30days（最近30天），默认值为7days" +
            "：最近7天，当start和end都不为空时，dr失效，当start和end其中一个为空，查询最近7天数据，当start和end都为空，以dr为准", required = false)
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
