package net.polyv.vod.v1.entity.manage.screenshot;

import com.alibaba.fastjson.annotation.JSONField;

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
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    @JSONField(name = "userid")
    private String userId;
    
    /**
     * 当前时间
     */
 /*   @ApiModelProperty(hidden = true)
    @NotNull(message = "属性currentTime不能为空")
    @JSONField(name = "ptime", format = "yyyy-MM-dd HH:mm:ss")
    private Date currentTime;*/
    
    /**
     * 任务id
     */
    @ApiModelProperty(name = "taskId", value = "视频ID", required = true)
    @NotNull(message = "属性taskId不能为空")
    private Integer taskId;
}
