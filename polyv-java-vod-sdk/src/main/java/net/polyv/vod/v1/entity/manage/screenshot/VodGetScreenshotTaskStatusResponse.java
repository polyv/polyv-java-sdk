package net.polyv.vod.v1.entity.manage.screenshot;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取截图任务状态返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("获取截图任务状态返回实体")
public class VodGetScreenshotTaskStatusResponse {
    /**
     * 任务id
     */
    @ApiModelProperty(name = "taskId", value = "任务id", required = false)
    private Integer taskId;
    
    /**
     * 视频vid
     */
    @ApiModelProperty(name = "vid", value = "视频vid", required = false)
    private String vid;
    
    /**
     * 任务状态，waiting-等待截图,processing-截图处理中,success-任务成功,fail-任务失败
     */
    @ApiModelProperty(name = "status", value = "任务状态，waiting-等待截图,processing-截图处理中,success-任务成功,fail-任务失败", required
            = false)
    private String status;
    
    /**
     * 任务创建时间，时间格式 yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "createTime", value = "任务创建时间,时间格式 yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    /**
     * 开始截图的时间，时间格式 yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "beginProcessTime", value = "开始截图的时间，时间格式 yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date beginProcessTime;
    
    /**
     * 完成截图的时间，时间格式 yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "finishProcessTime", value = "完成截图的时间，时间格式 yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date finishProcessTime;
    
    /**
     * 截图信息组，失败或未开始时为空
     */
    @ApiModelProperty(name = "screenshots", value = "截图信息组，失败或未开始时为空", required = false)
    @JSONField(name = "snapshots")
    private List<Screenshot> screenshots;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("截图信息")
    public static class Screenshot {
        /**
         * 截图时间点，单位：秒
         */
        @ApiModelProperty(name = "offsetTime", value = "截图时间点，单位：秒", required = false)
        private Integer offsetTime;
        
        /**
         * 截图访问的url
         */
        @ApiModelProperty(name = "imageUrl", value = "截图访问的url", required = false)
        private String imageUrl;
    }
}
