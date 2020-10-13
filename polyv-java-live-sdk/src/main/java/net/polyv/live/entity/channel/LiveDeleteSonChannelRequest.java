package net.polyv.live.entity.channel;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 删除子频道请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除子频道请求体")
public class LiveDeleteSonChannelRequest extends LiveCommonRequest {
    
    /**
     * 频道ID
     */
    @ApiModelProperty(name = "channelId", value = "频道ID", required = true)
    @NotNull(message = "channelId不能为空")
    private Integer channelId;
    
    /**
     * 子频道ID(不能以数字类型提交，否则可能去掉ID前的00)
     */
    @ApiModelProperty(name = "account", value = "子频道ID(不能以数字类型提交，否则可能去掉ID前的00)", required = true)
    @NotNull(message = "account不能为空")
    private String account;
    
}
