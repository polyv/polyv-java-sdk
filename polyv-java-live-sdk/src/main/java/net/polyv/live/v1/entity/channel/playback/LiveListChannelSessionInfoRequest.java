package net.polyv.live.v1.entity.channel.playback;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 查询频道直播场次信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道直播场次信息请求实体")
public class LiveListChannelSessionInfoRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 开始日期，格式yyyy-MM-dd
     */
    @ApiModelProperty(name = "startDate", value = "开始日期，格式yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    
    /**
     * 结束日期，格式yyyy-MM-dd
     */
    @ApiModelProperty(name = "endDate", value = "结束日期，格式yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    
}
