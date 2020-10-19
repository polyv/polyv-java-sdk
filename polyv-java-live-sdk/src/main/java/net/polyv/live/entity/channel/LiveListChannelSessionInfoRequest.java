package net.polyv.live.entity.channel;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询频道直播场次信息请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道直播场次信息请求实体")
public class LiveListChannelSessionInfoRequest extends LivePageCommonRequest {
    
    /**
     * 频道ID
     */
    @ApiModelProperty(name = "channelId", value = "频道ID", required = true)
    @NotNull(message = "channelId不能为空")
    private Integer channelId;
    
    /**
     * 开始日期，格式YYYY-MM-DD
     */
    @ApiModelProperty(name = "startDate", value = "开始日期，格式YYYY-MM-DD", required = false)
    private String startDate;
    
    /**
     * 结束日期，格式YYYY-MM-DD
     */
    @ApiModelProperty(name = "endDate", value = "结束日期，格式YYYY-MM-DD", required = false)
    private String endDate;
    
}
