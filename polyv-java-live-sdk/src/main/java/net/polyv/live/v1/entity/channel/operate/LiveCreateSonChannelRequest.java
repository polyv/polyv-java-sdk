package net.polyv.live.v1.entity.channel.operate;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 创建子频道请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("创建子频道请求体")
public class LiveCreateSonChannelRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 默认不传为助教，传Guest为嘉宾（只支持三分屏场景的频道）
     */
    @ApiModelProperty(name = "role", value = "默认不传为助教，传Guest为嘉宾（只支持三分屏场景的频道）", required = false)
    private String role;
    
    /**
     * 创建的助教或嘉宾昵称
     */
    @ApiModelProperty(name = "nickname", value = "创建的助教或嘉宾昵称", required = false)
    private String nickname;
    
    /**
     * 创建的助教或嘉宾头衔
     */
    @ApiModelProperty(name = "actor", value = "创建的助教或嘉宾头衔", required = false)
    private String actor;
    
    /**
     * 创建的助教或嘉宾头像
     */
    @ApiModelProperty(name = "avatar", value = "创建的助教或嘉宾头像", required = false)
    private String avatar;
    
}
