package net.polyv.vod.v1.entity.manage.screenshot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取截图任务状态请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取截图任务状态请求实体")
public class VodGetScreenshotTaskStatusRequest extends VodCommonRequest {
    
    /**
     * 任务ID
     */
    @ApiModelProperty(name = "taskId", value = "任务ID", required = true)
    @NotNull(message = "属性taskId不能为空")
    private Integer taskId;
}
