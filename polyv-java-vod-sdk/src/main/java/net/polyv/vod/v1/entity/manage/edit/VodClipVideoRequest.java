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
 * 提交视频裁剪任务请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("提交视频裁剪任务请求实体")
public class VodClipVideoRequest extends VodCommonRequest {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性vid不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 裁剪后的视频名称
     */
    @ApiModelProperty(name = "title", value = "裁剪后的视频名称", required = true)
    @NotNull(message = "属性title不能为空")
    private String title;
    
    /**
     * json格式的特定时间段，格式为[{"start":1,"end":6},{"start":10,"end":16}]. 时间段数量不能超过5个，每个片段开始时间不能大于结束时间，开始与结束时间间隔需要超过或者等于5秒，结束时间不能超过视频的播放时长
     */
    @ApiModelProperty(name = "timeFrame", value = "json格式的特定时间段，格式为[{\"start\":1,\"end\":6},{\"start\":10,\"end\":16}]. 时间段数量不能超过5个，每个片段开始时间不能大于结束时间，开始与结束时间间隔需要超过或者等于5秒，结束时间不能超过视频的播放时长", required = true)
    @NotNull(message = "属性timeFrame不能为空")
    private String timeFrame;
    
}
