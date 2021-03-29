package net.polyv.vod.v1.entity.manage.subtitle;

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
 * 上传点播视频字幕文件请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取视频字幕请求实体")
public class VodUploadSubtitleRequest extends VodCommonRequest {
    
    public static final String FILE_NAME = "file";
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 字幕名称
     */
    @ApiModelProperty(name = "title", value = "字幕名称", required = true)
    @NotNull(message = "属性title不能为空")
    private String title;
    
    /**
     * 字幕文件，支持utf-8编码
     */
    @ApiModelProperty(name = "file", value = "字幕文件，支持utf-8编码", required = true)
    @NotNull(message = "属性file不能为空")
    private File file;
    
    /**
     * 是否作为默认字幕，Y：是，N:否。默认为N:否。首次上传字幕为Y：是
     */
    @ApiModelProperty(name = "asDefault", value = "是否作为默认字幕，Y：是，N:否。默认为N:否。首次上传字幕为Y：是", required = false)
    private String asDefault;
    
    /**
     * 语言，默认自动检测，支持语言：中文、繁体中文 、英语、日语、韩语、法语、德语、俄语、西班牙语、阿拉伯语、葡萄牙语、其他
     */
    @ApiModelProperty(name = "language", value = "语言，默认自动检测，支持语言：中文、繁体中文 、英语、日语、韩语、法语、德语、俄语、西班牙语、阿拉伯语、葡萄牙语、其他",
            required = false)
    private String language;
}
