package net.polyv.live.v1.entity.interact;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonResponse;

/**
 * 查询签到结果响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询签到结果响应实体")
public class LiveCheckinListResponse extends LivePageCommonResponse {
    /**
     * 签到记录数据数组
     */
    @ApiModelProperty(name = "contents", value = "签到记录数据数组", required = false)
    private List<CheckinRecord> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("查询签到结果响应实体-单条签到记录数据")
    public static class CheckinRecord {
        
        /**
         * 查询的签到日期，yyyy-MM-dd格式
         */
        @ApiModelProperty(name = "indate", value = "查询的签到日期，yyyy-MM-dd格式", required = false)
        private Date indate;
        
        /**
         * 昵称
         */
        @ApiModelProperty(name = "nickname", value = "昵称", required = false)
        private String nickname;
        
        /**
         * C端观众ID
         */
        @ApiModelProperty(name = "userId", value = "C端观众ID", required = false)
        @JSONField(name = "userid")
        private String userId;
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 签到时间
         */
        @ApiModelProperty(name = "time", value = "签到时间", required = false)
        private Date time;
        
        /**
         * 签到的格式化详细日期，yyyy-MM-dd HH:mm 格式
         */
        @ApiModelProperty(name = "timeFormat", value = "签到的格式化详细日期，yyyy-MM-dd HH:mm 格式", required = false)
        private Date timeFormat;
        
        /**
         * 场次sessionId
         */
        @ApiModelProperty(name = "sessionId", value = "场次sessionId", required = false)
        private String sessionId;
        
        /**
         * 签到ID，一场签到一个id
         */
        @ApiModelProperty(name = "checkinid", value = "签到ID", required = false)
        private String checkinid;
    
        /**
         * 签到记录主键
         */
        @ApiModelProperty(name = "id", value = "签到记录主键", required = false)
        private String id;
        
        /**
         * 该场次直播开始时间，如果不传sessionId,startTime为空；传sessionId,startTime显示
         */
        @ApiModelProperty(name = "startTime", value = "该场次直播开始时间，只有请求参数传sessionId,该字段才有值", required = false)
        private Date startTime;
        
        /**
         * 在外部授权、直接（独立）授权情况下传过来的自定义参数
         */
        @ApiModelProperty(name = "param4", value = "在外部授权、直接（独立）授权情况下传过来的自定义参数", required = false)
        private String param4;
        
        /**
         * 在外部授权、直接（独立）授权情况下传过来的自定义参数
         */
        @ApiModelProperty(name = "param5", value = "在外部授权、直接（独立）授权情况下传过来的自定义参数", required = false)
        private String param5;
        
    }
}


