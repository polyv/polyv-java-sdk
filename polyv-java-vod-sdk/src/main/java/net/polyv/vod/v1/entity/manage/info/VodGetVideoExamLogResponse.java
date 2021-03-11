package net.polyv.vod.v1.entity.manage.info;

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
         * 观众ID
         */
        @ApiModelProperty(name = "userId", value = "观众ID", required = false)
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
         * 观众回答的答案
         */
        @ApiModelProperty(name = "answer", value = "观众回答的答案", required = false)
        private String answer;
        
        /**
         * 是否回答正确，1：回答正确；0：回答错误
         */
        @ApiModelProperty(name = "isCorrect", value = "是否回答正确，1：回答正确；0：回答错误", required = false)
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
         * 观众的省份
         */
        @ApiModelProperty(name = "province", value = "观众的省份", required = false)
        private String province;
        
        /**
         * 观众使用的ISP运营商
         */
        @ApiModelProperty(name = "isp", value = "观众使用的ISP运营商", required = false)
        private String isp;
        
        /**
         * 观众的操作系统
         */
        @ApiModelProperty(name = "operatingSystem", value = "观众的操作系统", required = false)
        private String operatingSystem;
        
        /**
         * 观众使用的浏览器
         */
        @ApiModelProperty(name = "browser", value = "观众使用的浏览器", required = false)
        private String browser;
        
        /**
         * 回答该问题的日期，格式：yyyy-MM-dd HH:mm:ss
         */
        @ApiModelProperty(name = "dateAdded", value = "回答该问题的日期，格式：yyyy-MM-dd HH:mm:ss", required = false)
        private Date dateAdded;
        
        /**
         * 自定义观众id
         */
        @ApiModelProperty(name = "viewerId", value = "自定义观众id", required = false)
        @JSONField(name = "viewerid")
        private String viewerId;
        
    }
    
}
