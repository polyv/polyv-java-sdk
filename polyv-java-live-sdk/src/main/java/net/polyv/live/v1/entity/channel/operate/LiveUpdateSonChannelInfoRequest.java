package net.polyv.live.v1.entity.channel.operate;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置子频道信息请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置子频道信息请求体")
public class LiveUpdateSonChannelInfoRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 子频道号
     */
    @ApiModelProperty(name = "account", value = "子频道号", required = true)
    @NotNull(message = "属性account不能为空")
    private String account;
    
    /**
     * 昵称
     */
    @ApiModelProperty(name = "nickname", value = "昵称", required = false)
    private String nickname;
    
    /**
     * 子频道密码
     */
    @ApiModelProperty(name = "password", value = "子频道密码", required = false)
    private String password;
    
    /**
     * 头像
     */
    @ApiModelProperty(name = "avatar", value = "头像", required = false)
    private String avatar;
    
    /**
     * 子频道头衔
     */
    @ApiModelProperty(name = "actor", value = "子频道头衔", required = false)
    private String actor;
    
    /**
     * 子频道翻页权限,值为Y或N，Y为开启，N为关闭
     */
    @ApiModelProperty(name = "pageTurnEnabled", value = "子频道翻页权限,值为Y或N，Y为开启，N为关闭", required = false)
    private String pageTurnEnabled;
    
    /**
     * 子频道公告权限,值为Y或N，Y为开启，N为关闭
     */
    @ApiModelProperty(name = "notifyEnabled", value = "子频道公告权限,值为Y或N，Y为开启，N为关闭", required = false)
    private String notifyEnabled;

}
