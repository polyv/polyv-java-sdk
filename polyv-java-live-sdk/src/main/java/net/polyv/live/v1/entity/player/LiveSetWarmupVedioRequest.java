package net.polyv.live.v1.entity.player;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置播放器暖场视频请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置播放器暖场视频请求实体")
public class LiveSetWarmupVedioRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    
    /**
     * 暖场视频地址(http地址)，移动端不支持FLV视频文件，建议使用MP4视频文件
     */
    @ApiModelProperty(name = "warmUpFlv", value = "暖场视频地址(http地址)，移动端不支持FLV视频文件，建议使用MP4视频文件", required = true)
    @NotNull(message = "属性warmUpFlv不能为空")
    private String warmUpFlv;
    
    
    
}
