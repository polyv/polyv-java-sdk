package net.polyv.vod.v1.entity.upload;

import java.io.File;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 上传视频水印请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("上传视频水印请求实体")
public class VodUploadWatermarkRequest extends VodCommonRequest {
    
    /**
     * 上传的水印图片
     */
    @ApiModelProperty(name = "image", value = "上传的水印图片", required = true)
    private File image;
    
    /**
     * 分类ID,仅一级分类能设置水印。不传为设置用户级别的水印
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID,仅一级分类能设置水印。不传为设置用户级别的水印", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 水印显示的位置：1：左上角；2：右上角；3：左下角；4：右下角；0：不显示水印
     */
    @ApiModelProperty(name = "watermarkLocation", value = "水印显示的位置：1：左上角；2：右上角；3：左下角；4：右下角；0：不显示水印", required = false)
    private String watermarkLocation;
    
}
