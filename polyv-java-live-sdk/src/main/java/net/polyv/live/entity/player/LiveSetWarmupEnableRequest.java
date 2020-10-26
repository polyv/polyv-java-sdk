package net.polyv.live.entity.player;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置频道的暖场设置开关请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置频道的暖场设置开关请求实体")
public class LiveSetWarmupEnableRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    
    /**
     * 开关值，Y或N，Y表示开启，N表示关闭
     */
    @ApiModelProperty(name = "warmUpEnabled", value = "开关值，Y或N，Y表示开启，N表示关闭", required = true)
    @NotNull(message = "属性warmUpEnabled不能为空")
    private String warmUpEnabled;
    
    
    
}
