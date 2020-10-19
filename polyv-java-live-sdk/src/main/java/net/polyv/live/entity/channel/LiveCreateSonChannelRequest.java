package net.polyv.live.entity.channel;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 创建子频道请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("创建子频道请求体")
public class LiveCreateSonChannelRequest extends LiveCommonRequest {
    
    /**
     * 频道ID，请留意，如果该参数为空，会对该用户所有的频道进行修改
     */
    @ApiModelProperty(name = "channelId", value = "频道ID", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
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
