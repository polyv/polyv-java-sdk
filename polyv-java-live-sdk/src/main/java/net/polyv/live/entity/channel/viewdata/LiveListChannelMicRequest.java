package net.polyv.live.entity.channel.viewdata;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 分页获取连麦情况使用详情请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页获取连麦情况使用详情请求体")
public class LiveListChannelMicRequest extends LivePageCommonRequest {
    
    /**
     * 频道号，使用英文逗号分开，如：100000,100001
     */
    @ApiModelProperty(name = "channelIds", value = "频道号，使用英文逗号分开，如：100000,100001", required = false)
    private String channelIds;
    
    /**
     * 开始时间，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "startDay", value = "开始时间，格式：yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date startDay;
    
    /**
     * 结束时间，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "endDay", value = "结束时间，格式：yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date endDay;

}
