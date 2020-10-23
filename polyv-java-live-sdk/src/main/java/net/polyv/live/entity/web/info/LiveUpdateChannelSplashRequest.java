package net.polyv.live.entity.web.info;

import java.io.File;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置引导开关以及引导图片请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置引导开关以及引导图片请求实体")
public class LiveUpdateChannelSplashRequest extends LiveCommonRequest {
    
    /**
     * 频道ID，非必填，不提交默认为修改该用户的所有频道ID的主持人姓名
     */
    @ApiModelProperty(name = "channelId", value = "频道ID，非必填，不提交默认为修改该用户的所有频道ID的主持人姓名", required = false)
    @NotNull(message = "channelId不能为空")
    private Integer channelId;
    
    /**
     * 设置开启或关闭引导页Y或N
     */
    @ApiModelProperty(name = "splashEnabled", value = "设置开启或关闭引导页Y或N", required = true)
    @NotNull(message = "splashEnabled不能为空")
    private String splashEnabled;
    
    /**
     * 支持jpg、jpeg、png三种格式，大小不能超过4Mb
     */
    @ApiModelProperty(name = "imgfile", value = "支持jpg、jpeg、png三种格式，大小不能超过4Mb", required = true)
    @NotNull(message = "imgfile不能为空")
    private File imgfile;
    
}
