package net.polyv.live.entity.channel.operate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询课件重制任务列表请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询课件重制任务列表请求实体")
public class LiveListChannelPPTRecordRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true, example = "1940343")
    @NotNull(message = "属性频道号不能为空")
    private String channelId;
    
    /**
     * TODO 写明场次id从哪里获取
     * 场次id
     */
    @ApiModelProperty(name = "sessionId", value = "场次id", required = false, example = "")
    private String sessionId;
    
    /**
     * @see LiveConstant.PPTStatus
     * 课件重置状态值
     */
    @ApiModelProperty(name = "status", value = "课件重置状态值", required = false, example = "success")
    private String status;
    
    /**
     * 直播开始时间开始区间,格式为yyyyMMddHHmmss
     */
    @ApiModelProperty(name = "startTime", value = "直播开始时间开始区间,格式为yyyyMMddHHmmss", required = false, example = "20200101000000")
    private String startTime;
    /**
     * 直播开始时间结束区间,格式为yyyyMMddHHmmss
     */
    @ApiModelProperty(name = "endTime", value = "直播开始时间结束区间,格式为yyyyMMddHHmmss", required = false, example = "2020100123595959")
    private String endTime;
}
