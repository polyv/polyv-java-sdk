package net.polyv.vod.v1.entity.datastatistics;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 高级分析-分页查询观看行为列表请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("高级分析-分页查询观看行为列表请求实体")
public class VodQueryViewingBehaviorListRequest extends VodPageCommonRequest {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = false)
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 开始时间，格式为yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss，查询范围不超过31天
     */
    @ApiModelProperty(name = "startTime", value = "开始时间，格式为yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss，查询范围不超过31天", required =
            false)
    @JSONField(name = "start", format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    
    /**
     * 结束时间，格式为yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss，查询范围不超过31天
     */
    @ApiModelProperty(name = "endTime", value = "结束时间，格式为yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss，查询范围不超过31天", required = false)
    @JSONField(name = "end", format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    
    /**
     * 观众id，例如 1555313336634
     */
    @ApiModelProperty(name = "viewerId", value = "观众id，例如 1555313336634", required = false)
    private String viewerId;
    
    /**
     * 观众昵称
     */
    @ApiModelProperty(name = "viewerName", value = "观众昵称", required = false)
    private String viewerName;
    
    /**
     * 下一页的凭证，从当前页的返回数据里获取，第一页不需要传
     */
    @ApiModelProperty(name = "token", value = "下一页的凭证，从当前页的返回数据里获取，第一页不需要传", required = false)
    private String token;
}
