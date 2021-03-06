package net.polyv.live.v1.entity.web.info;

import java.io.File;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道图标请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道图标请求实体")
public class LiveUpdateChannelLogoRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 图片为大小为2MB的JPG、JPEG、PNG图片
     */
    @ApiModelProperty(name = "imgfile", value = "图片为大小为2MB的JPG、JPEG、PNG图片", required = true)
    @NotNull(message = "属性imgfile不允许为空")
    private File imgfile;

}
