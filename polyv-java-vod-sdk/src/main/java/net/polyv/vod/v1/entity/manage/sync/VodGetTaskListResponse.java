package net.polyv.vod.v1.entity.manage.sync;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonResponse;

/**
 * 分页获取视频同步列表返回实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页获取视频同步列表返回实体")
public class VodGetTaskListResponse extends VodPageCommonResponse {
    
    /**
     * 查询的结果列表
     */
    @ApiModelProperty(name = "contents", value = "查询的结果列表", required = false)
    private List<Task> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("任务信息")
    public static class Task {
        
        /**
         * 抓取任务对应的ID
         */
        @ApiModelProperty(name = "taskId", value = "抓取任务对应的ID", required = false)
        @JSONField(name = "taskid")
        private String taskId;
        
        /**
         * 点播用户的ID
         */
        @ApiModelProperty(name = "userId", value = "点播用户的ID", required = false)
        @JSONField(name = "userid")
        private String userId;
        
        /**
         * 上传的抓取任务csv文件的标题
         */
        @ApiModelProperty(name = "originalName", value = "上传的抓取任务csv文件的标题", required = false)
        @JSONField(name = "originalname")
        private String originalName;
        
        /**
         * 抓取任务的csv文件地址
         */
        @ApiModelProperty(name = "fileUrl", value = "抓取任务的csv文件地址", required = false)
        @JSONField(name = "fileurl")
        private String fileUrl;
        
        /**
         * 抓取成功的数量
         */
        @ApiModelProperty(name = "successCount", value = "抓取成功的数量", required = false)
        @JSONField(name = "seccesscount")
        private Integer successCount;
        
        /**
         * 此任务内总抓取数
         */
        @ApiModelProperty(name = "totalCount", value = "此任务内总抓取数", required = false)
        @JSONField(name = "totalcount")
        private Integer totalCount;
        
        /**
         * 抓取失败的数量
         */
        @ApiModelProperty(name = "failCount", value = "抓取失败的数量", required = false)
        @JSONField(name = "failcount")
        private Integer failCount;
        
        /**
         * 抓取任务完成状态，Y：完成，N：未完成
         */
        @ApiModelProperty(name = "status", value = "抓取任务完成状态", required = false)
        private String status;
        
        /**
         * 任务完成时间，格式：yyyy-MM-dd HH:mm:ss
         */
        @ApiModelProperty(name = "endTime", value = "任务完成时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
        @JSONField(name = "endtime", format = "yyyy-MM-dd HH:mm:ss")
        private Date endTime;
        
        /**
         * 任务创建时间，格式：yyyy-MM-dd HH:mm:ss
         */
        @ApiModelProperty(name = "createTime", value = "任务创建时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
        @JSONField(name = "createtime", format = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;
    }
}
