package net.polyv.live.entity.chat;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询频道的问答统计结果请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道的问答统计结果请求实体")
public class LiveGetQuestionStatisticalRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    private Integer channelId;
    
    
    /**
     * 开始时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "startTime", value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String startTime;
    
    /**
     * 结束时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "endTime", value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String endTime;
    
    
}
