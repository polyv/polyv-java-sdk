package net.polyv.vod.v1.entity.manage.info;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonResponse;

/**
 * 批量获取答题日志返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量获取答题日志返回实体")
public class VodGetVideoExamLogResponse extends VodPageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "答题日志列表")
    private List<ExamLog> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("答题日志")
    public static class ExamLog {
        
        /**
         * 此条日志的ID
         */
        @ApiModelProperty(name = "logId", value = "此条日志的ID", required = false)
        @JSONField(name = "logid")
        private Integer logId;
        
        /**
         * 日志所属的问答的ID
         */
        @ApiModelProperty(name = "examId", value = "日志所属的问答的ID", required = false)
        private String examId;
        
        /**
         * 用户ID
         */
        @ApiModelProperty(name = "userId", value = "用户ID", required = false)
        private String userId;
        
        /**
         * 问答所属的视频ID
         */
        @ApiModelProperty(name = "videoId", value = "问答所属的视频ID", required = false)
        @JSONField(name = "videoPoolId")
        private String videoId;
        
        /**
         * 问答标题
         */
        @ApiModelProperty(name = "question", value = "问答标题", required = false)
        private String question;
        
        /**
         * 问答的正确答案
         */
        @ApiModelProperty(name = "answer", value = "问答的正确答案", required = false)
        private String answer;
        
        /**
         * 是否回答正确
         */
        @ApiModelProperty(name = "isCorrect", value = "是否回答正确", required = false)
        private Integer isCorrect;
        
        /**
         * 播放器ID
         */
        @ApiModelProperty(name = "playerId", value = "播放器ID", required = false)
        private String playerId;
        
        /**
         * IP地址
         */
        @ApiModelProperty(name = "ipAddress", value = "IP地址", required = false)
        private String ipAddress;
        
        /**
         * 省份
         */
        @ApiModelProperty(name = "province", value = "省份", required = false)
        private String province;
        
        /**
         * ISP运营商
         */
        @ApiModelProperty(name = "isp", value = "ISP运营商", required = false)
        private String isp;
        
        /**
         * 操作系统
         */
        @ApiModelProperty(name = "operatingSystem", value = "操作系统", required = false)
        private String operatingSystem;
        
        /**
         * 浏览器
         */
        @ApiModelProperty(name = "browser", value = "浏览器", required = false)
        private String browser;
        
        /**
         * 回答该问题的日期（时间戳形式）
         */
        @ApiModelProperty(name = "dateAdded", value = "回答该问题的日期（时间戳形式）", required = false)
        private Long dateAdded;
        
        /**
         * 自定义用户id
         */
        @ApiModelProperty(name = "viewerId", value = "自定义用户id", required = false)
        @JSONField(name = "viewerid")
        private String viewerId;
        
    }
    
}
