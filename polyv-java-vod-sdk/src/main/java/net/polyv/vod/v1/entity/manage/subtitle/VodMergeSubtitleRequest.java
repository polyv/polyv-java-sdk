package net.polyv.vod.v1.entity.manage.subtitle;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 合并字幕文件请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("合并字幕文件请求实体")
public class VodMergeSubtitleRequest extends VodCommonRequest {
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 原始字幕名称，必须传两个值。以英文逗号分隔，合并后第一个字幕的内容在上方显示。
     */
    @ApiModelProperty(name = "sourceSubtitleNames", value = "原始字幕名称，必须传两个值。以英文逗号分隔，合并后第一个字幕的内容在上方显示。", required = true)
    @NotNull(message = "属性sourceSubtitleNames不能为空")
    @JSONField(name = "sourceSrtNames")
    private String sourceSubtitleNames;
    
    /**
     * 合并字幕的名称，默认：双语。不超过5个中文字符。
     */
    @ApiModelProperty(name = "mergedSubtitleName", value = "合并字幕的名称，默认：双语。不超过5个中文字符。", required = false)
    @JSONField(name = "mergedSrtName")
    private String mergedSubtitleName;
    
    /**
     * 是否设置为默认显示的字幕。默认值：true。
     */
    @ApiModelProperty(name = "setAsDefault", value = "是否设置为默认显示的字幕。默认值：true。", required = false)
    private Boolean setAsDefault;
}
