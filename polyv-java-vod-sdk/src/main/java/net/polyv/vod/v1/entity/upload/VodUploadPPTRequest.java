package net.polyv.vod.v1.entity.upload;

import java.io.File;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotBlank;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 上传PPT文件请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("上传PPT文件请求实体")
public class VodUploadPPTRequest extends VodCommonRequest {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotBlank(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * ppt文件
     */
    @ApiModelProperty(name = "ppt", value = "ppt文件", required = true)
    @NotNull(message = "属性ppt不能为空")
    private File ppt;
    
    /**
     * ppt控制文件,格式见约束
     */
    @ApiModelProperty(name = "controlFile", value = "ppt控制文件,格式见约束", required = true)
    private File controlFile;

}
