package net.polyv.live.entity.channel.operate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置子频道信息请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置子频道信息请求体")
public class LiveUpdateSonChannelInfoRequest extends LiveCommonRequest {
    
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
