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
 * 删除视频字幕请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("删除视频字幕请求实体")
public class VodDeleteSubtitleRequest extends VodCommonRequest {
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 字幕序号列表，序号从1开始，多个以英文逗号分隔，例如 2,3
     */
    @ApiModelProperty(name = "ranks", value = "字幕序号列表，序号从1开始，多个以英文逗号分隔，例如 2,3", required = true)
    @NotNull(message = "属性ranks不能为空")
    private String ranks;
}
