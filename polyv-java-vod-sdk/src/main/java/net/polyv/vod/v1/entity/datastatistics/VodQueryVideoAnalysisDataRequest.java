package net.polyv.vod.v1.entity.datastatistics;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 高级分析-根据视频id查询视频分析数据请求实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("高级分析-根据视频id查询视频分析数据请求实体")
public class VodQueryVideoAnalysisDataRequest extends VodCommonRequest {
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
}
