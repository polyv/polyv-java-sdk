package net.polyv.live.v1.entity.interact;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 发送打赏消息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("发送打赏消息请求实体")
public class LiveSendChannelRewardMsgRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 打赏者昵称
     */
    @ApiModelProperty(name = "nickname", value = "打赏者昵称", required = true)
    @NotNull(message = "属性nickname不能为空")
    private String nickname;
    
    /**
     * 打赏者头像
     */
    @ApiModelProperty(name = "avatar", value = "打赏者头像", required = true)
    @NotNull(message = "属性avatar不能为空")
    private String avatar;
    
    /**
     * 打赏者ID，通过外部授权等观看方式对接，由B端系统产生，通过百名单进入的，此处可使用会员码
     */
    @ApiModelProperty(name = "viewerId", value = "打赏者ID，通过外部授权等观看方式对接，由B端系统产生，通过百名单进入的，此处可使用会员码", required = true)
    @NotNull(message = "属性viewerId不能为空")
    private String viewerId;
    
    /**
     * 打赏类型，(cash:现金打赏;good:道具打赏)
     */
    @ApiModelProperty(name = "donateType", value = "打赏类型，(cash:现金打赏;good:道具打赏)", required = true)
    @NotNull(message = "属性donateType不能为空")
    private String donateType;
    
    /**
     * 打赏内容：礼物打赏为礼物名称，现金打赏为金额
     */
    @ApiModelProperty(name = "content", value = "打赏内容：礼物打赏为礼物名称，现金打赏为金额", required = true)
    @NotNull(message = "属性content不能为空")
    private String content;
    
    /**
     * 礼物打赏时为礼物图片，现金打赏时为空
     */
    @ApiModelProperty(name = "goodImage", value = "礼物打赏时为礼物图片，现金打赏时为空", required = false)
    private String goodImage;
    
    /**
     * 直播场次ID
     */
    @ApiModelProperty(name = "sessionId", value = "直播场次ID", required = false)
    private String sessionId;
    
    /**
     * 打赏数量，不传默认为1
     */
    @ApiModelProperty(name = "goodNum", value = "打赏数量，不传默认为1", required = false)
    private String goodNum;
    
    /**
     * 是否socket消息需要用户图片（是：Y，否：N。不传默认为N）
     */
    @ApiModelProperty(name = "needUserImage", value = "是否socket消息需要用户图片（是：Y，否：N。不传默认为N）", required = false)
    private String needUserImage;
    
}
