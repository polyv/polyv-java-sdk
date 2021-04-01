package net.polyv.vod.v1.entity.datastatistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 高级分析–根据观众id查询观众分析结果请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("高级分析–根据观众id查询观众分析结果请求实体")
public class VodQueryAudienceAnalysisResultsRequest extends VodCommonRequest {
    /**
     * 观众id，例如 1555313336634
     */
    @ApiModelProperty(name = "viewerId", value = "观众id，例如 1555313336634", required = true)
    @NotNull(message = "属性viewerId不能为空")
    private String viewerId;
}
