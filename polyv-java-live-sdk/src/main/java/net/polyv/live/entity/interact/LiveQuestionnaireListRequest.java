package net.polyv.live.entity.interact;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询频道问卷列表请求实体
 * @author: thomas
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道问卷列表请求实体")
public class LiveQuestionnaireListRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    /**
     * 查询的记录的开始时间，13位位毫秒级时间戳
     */
    @ApiModelProperty(name = "startTime", value = "查询的记录的开始时间，13位位毫秒级时间戳", required = false)
    private Long startTime;
    
    /**
     * 查询的记录的结束时间，13位毫秒级时间戳
     */
    @ApiModelProperty(name = "endTime", value = "查询的记录的结束时间，13位毫秒级时间戳", required = false)
    private Long endTime;
    
    
    
}
