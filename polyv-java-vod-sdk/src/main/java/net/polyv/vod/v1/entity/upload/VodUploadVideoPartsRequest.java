package net.polyv.vod.v1.entity.upload;

import java.io.File;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.entity.CommonReqeust;
import net.polyv.common.v1.validator.constraints.NotNull;

/**
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class VodUploadVideoPartsRequest extends CommonReqeust {
    
    /**
     * 上传的视频文件
     */
    @ApiModelProperty(name = "file", value = "上传的视频文件", required = true)
    @NotNull(message = "属性file不能为空")
    private File file;
    
    @ApiModelProperty(name = "videoId", value = "续传的视频id", required = true)
    @NotNull(message = "属性videoId不能为空")
    private String videoId;
    
    /**
     * 如果提交了该字段，会在上传完成的事件回调中透传返回
     */
    @ApiModelProperty(name = "state", value = "如果提交了该字段，会在上传完成的事件回调中透传返回", required = false)
    private String state;
    
}
