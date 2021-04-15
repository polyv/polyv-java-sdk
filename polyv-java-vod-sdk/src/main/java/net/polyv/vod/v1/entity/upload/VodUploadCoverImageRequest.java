package net.polyv.vod.v1.entity.upload;

import java.io.File;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 上传多个视频的预览图请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("上传多个视频的预览图请求实体")
public class VodUploadCoverImageRequest extends VodCommonRequest {
    
    /**
     * 多个视频id用逗号隔开(状态为半角)，例如 1b8be3,239c2e
     */
    @ApiModelProperty(name = "videoIds", value = "多个视频id用逗号隔开(状态为半角)，例如 1b8be3,239c2e", required = false)
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 多个分类id用逗号隔开(状态为半角)，例如 1b8be3,239c2e
     */
    @ApiModelProperty(name = "categoryIds", value = "多个分类id用逗号隔开(状态为半角)，例如 1b8be3,239c2e", required = false)
    @JSONField(name="cataids")
    private String categoryIds;
    
    /**
     * 视频预览图片
     */
    @ApiModelProperty(name = "image", value = "视频预览图片", required = true)
    @NotNull(message = "属性image不能为空")
    private File image;

}
