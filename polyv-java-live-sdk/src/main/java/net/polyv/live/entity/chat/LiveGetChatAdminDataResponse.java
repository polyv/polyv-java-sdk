package net.polyv.live.entity.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询聊天室管理员信息响应实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询聊天室管理员信息响应实体")
public class LiveGetChatAdminDataResponse extends LiveCommonRequest {
    
    
    /**
     * 讲师昵称
     */
    @ApiModelProperty(name = "nickname", value = "讲师昵称", required = false)
    private String nickname;
    
    /**
     * 讲师头衔
     */
    @ApiModelProperty(name = "actor", value = "讲师头衔", required = false)
    private String actor;
    
    /**
     * 头像图片地址
     */
    @ApiModelProperty(name = "avatar", value = "头像图片地址", required = false)
    private String avatar;
    
}
