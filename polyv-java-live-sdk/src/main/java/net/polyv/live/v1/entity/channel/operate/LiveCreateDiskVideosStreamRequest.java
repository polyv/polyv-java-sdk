package net.polyv.live.v1.entity.channel.operate;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置硬盘推流直播请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置硬盘推流直播请求实体")
public class LiveCreateDiskVideosStreamRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 要设置硬盘推流的点播视频ID
     */
    @ApiModelProperty(name = "videoIds", value = "要设置硬盘推流的点播视频ID", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 硬盘推流开始时间
     */
    @ApiModelProperty(name = "startTimes", value = "硬盘推流开始时间", required = true)
    @NotNull(message = "属性startTimes不能为空")
    private Date startTimes;
    
}
