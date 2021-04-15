package net.polyv.vod.v1.entity.datastatistics;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 高级分析–根据观众id查询观众分析结果返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("高级分析-根据观众id查询观众分析结果返回实体")
public class VodQueryAudienceAnalysisResultsResponse {
    /**
     * 用户id
     */
    @ApiModelProperty(name = "userId", value = "用户id", required = false)
    private String userId;
    
    /**
     * 观众id
     */
    @ApiModelProperty(name = "viewerId", value = "观众id", required = false)
    private String viewerId;
    
    /**
     * 观众昵称
     */
    @ApiModelProperty(name = "viewerNickName", value = "观众昵称", required = false)
    private String viewerNickName;
    
    /**
     * 观众头像
     */
    @ApiModelProperty(name = "viewerAvatar", value = "观众头像", required = false)
    @JSONField(name = "viewerAatar")
    private String viewerAvatar;
    
    /**
     * ip地址
     */
    @ApiModelProperty(name = "ip", value = "ip地址", required = false)
    private String ip;
    
    /**
     * 首次观看时间，格式 yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "firstWatchTime", value = "首次观看时间，格式 yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date firstWatchTime;
    
    /**
     * 最后观看时间，格式 yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "lastWatchTime", value = "最后观看时间，格式 yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastWatchTime;
    
    /**
     * 观看视频总数
     */
    @ApiModelProperty(name = "totalVideoCount", value = "观看视频总数", required = false)
    private Integer totalVideoCount;
    
    /**
     * 观众总时长（秒）
     */
    @ApiModelProperty(name = "totalWatchDuration", value = "观众总时长（秒）", required = false)
    private Integer totalWatchDuration;
    
    /**
     * 平均观看完成度
     */
    @ApiModelProperty(name = "avgCompletionRate", value = "平均观看完成度", required = false)
    private Float avgCompletionRate;
}
