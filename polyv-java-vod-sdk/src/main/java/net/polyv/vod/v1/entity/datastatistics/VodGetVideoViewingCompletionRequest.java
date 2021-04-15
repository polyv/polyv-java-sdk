package net.polyv.vod.v1.entity.datastatistics;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取视频观看完成度请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取视频观看完成度请求实体")
public class VodGetVideoViewingCompletionRequest extends VodCommonRequest {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 自定义观众id，例如 1555313336634
     */
    @ApiModelProperty(name = "viewerId", value = "自定义观众id，例如 1555313336634", required = true)
    @NotNull(message = "属性viewerId不能为空")
    private String viewerId;
}
