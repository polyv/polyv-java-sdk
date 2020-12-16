package net.polyv.live.v1.entity.web.interact;

import net.polyv.common.v1.validator.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道微信分享信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道微信分享信息请求实体")
public class LiveUpdateChannelWxShareRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 微信分享的标题（30字符内）
     */
    @ApiModelProperty(name = "wxShareTitle", value = "微信分享的标题（30字符内）", required = false)
    @JSONField(name = "weixinShareTitle")
    private String wxShareTitle;
    
    /**
     * 微信分享的描述（120字符内）
     */
    @ApiModelProperty(name = "wxShareDesc", value = "微信分享的描述（120字符内）", required = false)
    @JSONField(name = "weixinShareDesc")
    private String wxShareDesc;

}
