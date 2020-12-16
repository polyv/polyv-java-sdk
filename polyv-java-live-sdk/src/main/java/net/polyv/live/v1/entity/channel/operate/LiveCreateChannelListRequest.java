package net.polyv.live.v1.entity.channel.operate;

import java.util.List;

import net.polyv.common.v1.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 批量创建频道请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量创建频道请求体")
public class LiveCreateChannelListRequest extends LiveCommonRequest {
    
    /**
     * 频道列表
     */
    @ApiModelProperty(name = "channels", value = "频道列表", required = true)
    private List<LiveChannelBasic> channels;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("频道基础信息")
    public static class LiveChannelBasic {
        
        /**
         * 频道名称
         */
        @ApiModelProperty(name = "name", value = "频道名称", required = true, example = "Spring 知识精讲")
        private String name;
        
        /**
         * 频道密码，长度不能超过16位
         */
        @ApiModelProperty(name = "channelPasswd", value = "频道密码，长度不能超过16位", required = true, example = "12345678")
        @NotNull(message = "属性channelPasswd不能为空")
        @Length(max = 16, message = "频道密码不能超过16位")
        private String channelPasswd;
        
        /**
         * 课程号
         */
        @ApiModelProperty(name = "courseId", value = "课程号", required = false, example = "aaa")
        private String courseId;
        
        /**
         * 是否自动播放，0/1，默认1
         * 注意，如果该值为空，则该频道会使用全局的“功能开关设置”。
         * 如果非空，则会使用频道的“功能开关设置”。
         */
        @ApiModelProperty(name = "autoPlay", value = "是否自动播放，0/1，默认1.注意，如果该值为空，则该频道会使用全局的“功能开关设置”。如果非空，则会使用频道的“功能开关设置”。",
                required = false, example = "1")
        private Integer autoPlay;
        
        /**
         * 播放器控制栏颜色，默认：#666666
         */
        @ApiModelProperty(name = "playerColor", value = "播放器控制栏颜色，默认：#666666", required = false, example = "#666666")
        private String playerColor;
        
        /**
         * 直播场景
         * @see LiveConstant.SceneType
         */
        @ApiModelProperty(name = "scene", value = "直播场景，值可查看LiveConstant.SceneType", required = false, example = "alone")
        private String scene;
        
        /**
         * 新建频道的所属分类，如果不提交，则为默认分类
         * 分类ID可通过“获取直播分类”接口得到
         */
        @ApiModelProperty(name = "categoryId", value = "新建频道的所属分类，如果不提交，则为默认分类。分类ID可通过“获取直播分类”接口得到", required = false, example = "340019")
        private Integer categoryId;
        
    }
    
}
