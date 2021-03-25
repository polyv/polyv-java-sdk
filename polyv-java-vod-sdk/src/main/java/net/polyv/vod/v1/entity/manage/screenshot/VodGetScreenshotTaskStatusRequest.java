package net.polyv.vod.v1.entity.manage.screenshot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取截图任务状态请求实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("获取截图任务状态请求实体")
public class VodGetScreenshotTaskStatusRequest extends VodCommonRequest {
    
    /**
     * 任务id
     */
    @ApiModelProperty(name = "taskId", value = "视频ID", required = true)
    @NotNull(message = "属性taskId不能为空")
    private Integer taskId;
}
