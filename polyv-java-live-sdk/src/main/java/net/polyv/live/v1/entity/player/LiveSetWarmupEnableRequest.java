package net.polyv.live.v1.entity.player;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道的暖场设置开关请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道的暖场设置开关请求实体")
public class LiveSetWarmupEnableRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    
    /**
     * 开关值，Y或N，Y表示开启，N表示关闭
     */
    @ApiModelProperty(name = "warmUpEnabled", value = "开关值，Y或N，Y表示开启，N表示关闭", required = true)
    @NotNull(message = "属性warmUpEnabled不能为空")
    private String warmUpEnabled;
    
    
    
}
