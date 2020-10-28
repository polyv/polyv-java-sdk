package net.polyv.live.entity.chat;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置讲师信息请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置讲师信息请求实体")
public class LiveSetTeacherDataRequest   extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 讲师昵称
     */
    @ApiModelProperty(name = "nickname", value = "讲师昵称", required = true)
    @NotNull(message = "属性nickname不能为空")
    private String nickname;
    
    /**
     * 讲师头衔
     */
    @ApiModelProperty(name = "actor", value = "讲师头衔", required = true)
    @NotNull(message = "属性actor不能为空")
    private String actor;
    
    /**
     * 频道密码
     */
    @ApiModelProperty(name = "passwd", value = "频道密码", required = true)
    @NotNull(message = "属性passwd不能为空")
    private String passwd;
    
    /**
     * 头像图片地址
     */
    @ApiModelProperty(name = "avatar", value = "头像图片地址", required = false)
    private String avatar;
    
}
