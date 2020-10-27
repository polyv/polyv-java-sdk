package net.polyv.live.entity.player;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置播放器暖场视频请求实体
 * @author: thomas
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置播放器暖场视频请求实体")
public class LiveSetWarmupVedioRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    
    /**
     * 开关值，Y或N，Y表示开启，N表示关闭
     */
    @ApiModelProperty(name = "warmUpFlv", value = "暖场视频地址，移动端不支持FLV视频文件，建议使用MP4视频文件", required = true)
    @NotNull(message = "属性warmUpFlv不能为空")
    private String warmUpFlv;
    
    
    
}
