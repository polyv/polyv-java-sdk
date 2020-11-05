package net.polyv.live.entity.chat;

import java.io.File;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置聊天室管理员信息请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置聊天室管理员信息请求实体")
public class LiveSetChatAdminDataRequest extends LiveCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 管理员昵称，长度不能超过8
     */
    @ApiModelProperty(name = "nickname", value = "管理员昵称，长度不能超过8个字符", required = true)
    @NotNull(message = "属性nickname不能为空")
    private String nickname;
    
    /**
     * 管理员头衔，长度不能超过4
     */
    @ApiModelProperty(name = "actor", value = "管理员头衔，长度不能超过4个字符", required = true)
    @NotNull(message = "属性actor不能为空")
    private String actor;
    
    /**
     * 管理员头像，支持jpg、jpeg、png三种格式，大小不能超过2Mb
     */
    @ApiModelProperty(name = "avatar", value = "管理员头像，支持jpg、jpeg、png三种格式，大小不能超过2Mb", required = true)
    private File avatar;
    
}
