package net.polyv.vod.v1.upload.bean.vo;

import java.io.File;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.entity.CommonReqeust;
import net.polyv.common.v1.validator.constraints.NotNull;

/**
 * 视频信息
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class VodUploadVideoRequest extends CommonReqeust implements Serializable {
    
    private static final long serialVersionUID = -3132455282961724783L;
    
    /**
     * 视频标题，默认使用带后缀的文件名，如：test.mp4
     */
    @ApiModelProperty(name = "title", value = "视频标题，默认使用带后缀的文件名，如：test.mp4", required = false)
    private String title;
    
    /**
     * 视频简介，默认为空
     */
    @ApiModelProperty(name = "describe", value = "视频简介，默认为空", required = false)
    private String describe;
    
    /**
     * 视频标签，多个使用英文逗号分隔，默认为空
     */
    @ApiModelProperty(name = "tag", value = "视频标签，多个使用英文逗号分隔，默认为空", required = false)
    private String tag;
    
    /**
     * 视频所属分类，默认为“默认分类”
     */
    @ApiModelProperty(name = "categoryId", value = "视频所属分类，默认为“默认分类”", required = false)
    private String categoryId = "1";
    
    /**
     * 是否录屏优化。当值为1时，上传的视频不再采取默认的压缩编码机制，视频尺寸不再压缩，保证视频的清晰度。默认值为0
     */
    @ApiModelProperty(name = "screenCap", value = "是否录屏优化。当值为1时，上传的视频不再采取默认的压缩编码机制，视频尺寸不再压缩，保证视频的清晰度。默认值为0",
            required = false)
    private Integer screenCap = 0;
    
    /**
     * 源文件播放，1为开启，0为关闭；开启时不对视频进行转码（仅对新上传视频有效），默认值为0:非源文件播放
     */
    @ApiModelProperty(name = "keepSource", value = "源文件播放，1为开启，0为关闭；开启时不对视频进行转码（仅对新上传视频有效），默认值为0:非源文件播放", required =
            false)
    private Integer keepSource = 0;
    
    /**
     * 上传的视频文件
     */
    @ApiModelProperty(name = "file", value = "上传的视频文件", required = true)
    @NotNull(message = "属性file不能为空")
    private File file;
    
    /**
     * 如果提交了该字段，会在上传完成的事件回调中透传返回
     */
    @ApiModelProperty(name = "state", value = "如果提交了该字段，会在上传完成的事件回调中透传返回", required = false)
    private String state;
    
    /**
     * 每次请求的业务流水号，便于客户端/服务器端排查问题
     */
    @ApiModelProperty(name="requestId",value  ="每次请求的业务流水号，便于客户端/服务器端排查问题",dataType = "String" ,required = true,example = "1234567" )
    @NotNull(message = "属性requestId不能为空")
    private String requestId;
    
    public VodUploadVideoRequest setCategoryId(String categoryId) {
        if (categoryId == null || "0".equals(categoryId)) {
            this.categoryId = "1";
        } else {
            this.categoryId = categoryId;
        }
        return this;
    }
    
    public VodUploadVideoRequest setScreenCap(Integer screenCap) {
        if (screenCap == null || screenCap > 1) {
            this.screenCap = 0;
        } else {
            this.screenCap = screenCap;
        }
        return this;
    }
    
    public VodUploadVideoRequest setKeepSource(Integer keepSource) {
        if (keepSource == null || keepSource > 1) {
            this.keepSource = 0;
        } else {
            this.keepSource = keepSource;
        }
        return this;
    }
    
    public VideoInfo convert() {
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setTitle(this.getTitle());
        videoInfo.setFileSize(this.getFile().length());
        videoInfo.setDescrib(this.getDescribe());
        videoInfo.setTag(this.getTag());
        videoInfo.setCataId(this.getCategoryId());
        videoInfo.setLuping(this.getScreenCap());
        videoInfo.setKeepSource(this.getKeepSource());
        videoInfo.setFile(this.getFile());
        videoInfo.setRequestId(this.getRequestId());
        videoInfo.setState(this.getState());
        return videoInfo;
    }
    
}
