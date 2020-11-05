package net.polyv.live.entity.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道的问答统计结果请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道的问答统计结果请求实体")
public class LiveGetQuestionStatisticalRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    private String channelId;
    
    
    /**
     * 开始时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "startTime", value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
    private String startTime;
    
    /**
     * 结束时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "endTime", value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
    private String endTime;
    
    
}
