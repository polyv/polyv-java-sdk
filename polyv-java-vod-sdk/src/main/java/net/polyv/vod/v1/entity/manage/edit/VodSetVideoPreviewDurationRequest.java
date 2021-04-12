package net.polyv.vod.v1.entity.manage.edit;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 修改视频的播放预览时长请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("修改视频的播放预览时长请求实体")
public class VodSetVideoPreviewDurationRequest extends VodCommonRequest {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性vid不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 预览的时长（单位是秒），例如：20
     */
    @ApiModelProperty(name = "duration", value = "预览的时长（单位是秒），例如：20", required = true)
    @NotNull(message = "属性duration不能为空")
    private Integer duration;
    
}
