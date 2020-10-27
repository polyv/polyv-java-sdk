package net.polyv.live.entity.web.info;

import java.io.File;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置频道图标请求实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置频道图标请求实体")
public class LiveUpdateChannelLogoRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    /**
     * 图片为大小为2MB的JPG、JPEG、PNG图片
     */
    @ApiModelProperty(name = "imgfile", value = "图片为大小为2MB的JPG、JPEG、PNG图片", required = true)
    @NotNull(message = "属性imgfile不允许为空")
    private File imgfile;

}
