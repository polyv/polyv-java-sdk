package net.polyv.live.entity.chat;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道踢人列表响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道踢人列表响应实体")
public class LiveKickedListResponse {
    /**
     * 是否禁言
     */
    @ApiModelProperty(name = "banned", value = "是否禁言", required = false)
    private Boolean banned;
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private Integer channelId;
    
    /**
     * 用户ip
     */
    @ApiModelProperty(name = "clientIp", value = "用户ip", required = false)
    private String clientIp;
    
    /**
     * 踢人方式：userId,
     */
    @ApiModelProperty(name = "kickRefer", value = "踢人方式：userId,", required = false)
    private String kickRefer;
    
    /**
     * 昵称
     */
    @ApiModelProperty(name = "nick", value = "昵称", required = false)
    private String nick;
    
    /**
     * 头像图片地址
     */
    @ApiModelProperty(name = "pic", value = "头像图片地址", required = false)
    private String pic;
    
    /**
     * 房间号
     */
    @ApiModelProperty(name = "roomId", value = "房间号", required = false)
    private Integer roomId;
    
    /**
     * 聊天室socketid
     */
    @ApiModelProperty(name = "uid", value = "聊天室socketid", required = false)
    private String uid;
    
    /**
     * 用户userId
     */
    @ApiModelProperty(name = "userId", value = "用户userId", required = false)
    private String userId;
    
    /**
     * 用户身份：管理员 manager，讲师 teacher， 助教 assistant， 嘉宾 guest，参与者 viewer，观看者 slice/student
     */
    @ApiModelProperty(name = "userType", value = "用户身份：管理员 manager，讲师 teacher， 助教 assistant， 嘉宾 guest，参与者 viewer，观看者 slice/student", required = false)
    private String userType;
    
}