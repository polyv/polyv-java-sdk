package net.polyv.live.entity.interact;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询签到结果响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询签到结果响应实体")
public class LiveCheckinListResponse {
    /**
     * 签到记录数据数组
     */
    @ApiModelProperty(name = "contents", value = "签到记录数据数组", required = false)
    private List<CheckinRecord> contents;
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("查询签到结果响应实体-单条签到记录数据")
    public class CheckinRecord {
        
        /**
         * 查询的签到日期，yyyy-MM-dd格式
         */
        @ApiModelProperty(name = "indate", value = "查询的签到日期，yyyy-MM-dd格式", required = false)
        @JSONField(format = "yyyy-MM-dd hh:mm:ss")
        private Date indate;
        
        /**
         * 昵称
         */
        @ApiModelProperty(name = "nickname", value = "昵称", required = false)
        private String nickname;
        
        /**
         * 用户id
         */
        @ApiModelProperty(name = "userid", value = "用户id", required = false)
        private String userid;
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 签到的具体时间戳
         */
        @ApiModelProperty(name = "time", value = "签到的具体时间戳", required = false)
        @JSONField(format = "yyyy-MM-dd hh:mm:ss")
        private Date time;
        
        /**
         * 签到的格式化详细日期，yyyy-MM-dd
         */
        @ApiModelProperty(name = "timeFormat", value = "签到的格式化详细日期，yyyy-MM-dd", required = false)
        @JSONField(format = "yyyy-MM-dd hh:mm:ss")
        private Date timeFormat;
        
        /**
         * 场次sessionId
         */
        @ApiModelProperty(name = "sessionId", value = "场次sessionId", required = false)
        private String sessionId;
        
        /**
         * 该场次直播开始时间，如果不传sessionId,startTime为空；传sessionId,startTime显示
         */
        @ApiModelProperty(name = "startTime", value = "该场次直播开始时间，如果不传sessionId,startTime为空；传sessionId,startTime显示",
                required = false)
        @JSONField(format = "yyyy-MM-dd hh:mm:ss")
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


