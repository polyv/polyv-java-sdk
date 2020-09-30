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
 * 设置频道详情请求实体
 * @author: sadboy
 
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("设置频道详情请求信息")
public class LiveChannelDetailRequest extends LiveCommonRequest {

    /**
     * 需要设置频道详情的频道号，例如：1938028
     */
    @ApiModelProperty(name = "channelId", value = "需要设置频道详情的频道号，例如：1938028", required = true, example =
            "1938028")
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;

    /**
     * 要更新的字段名称：channelPasswd 密码 scene 直播场景 maxViewer 最大同时观看人数
     */
    @ApiModelProperty(name = "field", value = "要更新的字段名称：password 密码 scene 直播场景 maxViewer 最大同时观看人数", required = true, example =
            "channelPasswd")
    @NotNull(message = "属性field不能为空")
    private String field;

    /**
     * 要更新的字段值，除设置无限制最大观看人数时可不提交，其他情况都为必填
     * field字段为channelPasswd时，value长度为1-16位，必填
     * field字段为scene时，value取值为(alone:活动拍摄；ppt:三分屏；topclass:大班课)，必填
     * field字段为maxViewer时，value取值为(0-2147483647),其中0和不传为不限制同时观看人数，非必填
     */
    @ApiModelProperty(name = "value", value = "要更新的字段值，除设置无限制最大观看人数时可不提交，其他情况都为必填", required = false, example =
            "123456")
    private String value;
}
