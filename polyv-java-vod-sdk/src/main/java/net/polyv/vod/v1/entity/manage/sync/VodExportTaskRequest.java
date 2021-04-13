package net.polyv.vod.v1.entity.manage.sync;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 导出视频同步任务请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("导出视频同步任务请求实体")
public class VodExportTaskRequest extends VodCommonRequest {
    /**
     * 抓取任务ID
     */
    @ApiModelProperty(name = "taskId", value = "抓取任务ID", required = true)
    @NotNull(message = "属性taskId不能为空")
    private String taskId;
}
