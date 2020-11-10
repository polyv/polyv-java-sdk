package net.polyv.live.entity.channel.operate;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 批量创建子频道请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量创建子频道请求实体")
public class LiveCreateSonChannelListRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    @ApiModelProperty(name = "sonChannels", value = "子频道", required = true)
    @NotNull(message = "属性sonChannels不能为空")
    private List<SonChannel> sonChannels;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("子频道")
    public static class SonChannel{
    
        /**
         * 默认不传为助教，传Guest为嘉宾
         */
        @ApiModelProperty(name = "role", value = "默认不传为助教，传Guest为嘉宾", required = false)
        private String role;
    
        /**
         * 创建的助教或嘉宾昵称
         */
        @ApiModelProperty(name = "nickname", value = "创建的助教或嘉宾昵称", required = false)
        private String nickname;
    
        /**
         * 子频道密码
         */
        @ApiModelProperty(name = "passwd", value = "子频道密码", required = true)
        @NotNull(message = "属性passwd不能为空")
        private String passwd;
    
        /**
         * 创建的助教或嘉宾头衔
         */
        @ApiModelProperty(name = "actor", value = "创建的助教或嘉宾头衔", required = true)
        @NotNull(message = "属性actor不能为空")
        private String actor;
    
        /**
         * 创建的助教或嘉宾头像
         */
        @ApiModelProperty(name = "avatar", value = "创建的助教或嘉宾头像", required = true)
        @NotNull(message = "属性avatar不能为空")
        private String avatar;
        
    }
}
