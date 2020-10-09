package net.polyv.live.entity.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

import javax.validation.constraints.NotNull;

/**
 * 频道相关设置请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("频道相关设置请求基本信息")
public class LiveChannelSettingRequest extends LiveCommonRequest {

    /**
     * 需要修改频道相关设置的频道号，例如：1938028
     */
    @ApiModelProperty(name = "channelId", value = "需要设置频道详情的频道号，例如：1938028", required = true, example =
            "1938028")
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;

}
