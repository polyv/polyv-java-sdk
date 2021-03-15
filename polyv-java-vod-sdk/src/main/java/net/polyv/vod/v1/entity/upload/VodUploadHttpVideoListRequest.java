package net.polyv.vod.v1.entity.upload;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 远程批量上传视频请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("远程批量上传视频请求实体")
public class VodUploadHttpVideoListRequest extends VodCommonRequest {
    
    /**
     * 远程文件的http连接(带http://)，多个地址间使用英文逗号隔开
     */
    @ApiModelProperty(name = "fileUrl", value = "远程文件的http连接(带http://)，多个地址间使用英文逗号隔开", required = true)
    @NotNull(message = "属性fileUrl不能为空")
    private String fileUrl;
    
    /**
     * 标题，多个标题使用逗号隔开(标题数量必须和文件地址数量一致)
     */
    @ApiModelProperty(name = "title", value = "标题，多个标题使用逗号隔开(标题数量必须和文件地址数量一致)", required = true)
    @NotNull(message = "属性title不能为空")
    private String title;
    
    /**
     * 设定上传视频的分类，当categoryId值为1时，表示用户上传空间的根目录。
     */
    @ApiModelProperty(name = "categoryId", value = "设定上传视频的分类，当categoryId值为1时，表示用户上传空间的根目录。", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 是否录屏优化。当值为1时，上传的视频不再采取默认的压缩编码机制，视频尺寸不再压缩，保证视频的清晰度。默认值为0
     */
    @ApiModelProperty(name = "screenCap", value = "是否录屏优化。当值为1时，上传的视频不再采取默认的压缩编码机制，视频尺寸不再压缩，保证视频的清晰度。默认值为0", required = false)
    @JSONField(name = "luping")
    private Integer screenCap;
    
    /**
     * 自定义水印图片地址,图片格式必须是png格式，支持http、https。
     */
    @ApiModelProperty(name = "watermark", value = "自定义水印图片地址,图片格式必须是png格式，支持http、https。", required = false)
    private String watermark;
    
    /**
     * 自定义水印图片位置，如没该参数，则自定义水印的显示情况跟随分类或账号设置。1：左上角；2：右上角；3：左下角；4：右下角
     */
    @ApiModelProperty(name = "watermarkLocation", value = "自定义水印图片位置，如没该参数，则自定义水印的显示情况跟随分类或账号设置。1：左上角；2：右上角；3：左下角；4：右下角", required = false)
    private String watermarkLocation;

}
