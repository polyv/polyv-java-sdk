package net.polyv.vod.v1.entity.datastatistics;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonResponse;

/**
 * 批量获取视频观看日志返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("批量获取视频观看日志返回实体")
public class VodGetVideoPlayLogResponse extends VodPageCommonResponse {
    
    /**
     * 返回的结果集
     */
    @ApiModelProperty(name = "contents", value = "返回的结果集", required = false)
    private List<VideoPlayLog> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("视频观看日志")
    public static class VideoPlayLog {
        /**
         * 表示此次播放动作的ID
         */
        @ApiModelProperty(name = "playId", value = "表示此次播放动作的ID", required = false)
        private String playId;
        
        /**
         * 用户ID
         */
        @ApiModelProperty(name = "userId", value = "用户ID", required = false)
        private String userId;
        
        /**
         * 视频ID
         */
        @ApiModelProperty(name = "videoId", value = "视频ID", required = false)
        private String videoId;
        
        /**
         * 播放时长 (用户观看的总时间 ，例如：18：00开始看一个视频，看到了18：30，这30分钟就是播放时长)。单位：秒
         */
        @ApiModelProperty(name = "playDuration", value = "播放时长 (用户观看的总时间 ，例如：18：00开始看一个视频，看到了18：30，这30分钟就是播放时长)。单位：秒"
                , required = false)
        private Integer playDuration;
        
        /**
         * 缓存时长。单位：秒
         */
        @ApiModelProperty(name = "stayDuration", value = "缓存时长。单位：秒", required = false)
        private Integer stayDuration;
        
        /**
         * 播放时间 （用户观看的最后时间，例如：停止观看视频的时候，进度条最后的分钟数为35分钟，播放时间就是35分钟）。单位：秒
         */
        @ApiModelProperty(name = "currentTimes", value = "播放时间 （用户观看的最后时间，例如：停止观看视频的时候，进度条最后的分钟数为35分钟，播放时间就是35" +
                "分钟）。单位：秒", required = false)
        private Integer currentTimes;
        
        /**
         * 视频总时长。单位：秒
         */
        @ApiModelProperty(name = "duration", value = "视频总时长。单位：秒", required = false)
        private Integer duration;
        
        /**
         * 流量大小
         */
        @ApiModelProperty(name = "flowSize", value = "流量大小", required = false)
        private Integer flowSize;
        
        /**
         * 用户自定义参数，如学员ID等 ,该参数做了UrlSafeBase64的加密，需要做解密
         */
        @ApiModelProperty(name = "sessionId", value = "用户自定义参数，如学员ID等 ,该参数做了UrlSafeBase64的加密，需要做解密", required = false)
        private String sessionId;
        
        /**
         * POLYV系统参数
         */
        @ApiModelProperty(name = "param1", value = "POLYV系统参数", required = false)
        private String param1;
        
        /**
         * POLYV系统参数
         */
        @ApiModelProperty(name = "param2", value = "POLYV系统参数", required = false)
        private String param2;
        
        /**
         * POLYV系统参数
         */
        @ApiModelProperty(name = "param3", value = "POLYV系统参数", required = false)
        private String param3;
        
        /**
         * POLYV系统参数
         */
        @ApiModelProperty(name = "param4", value = "POLYV系统参数", required = false)
        private String param4;
        
        /**
         * POLYV系统参数
         */
        @ApiModelProperty(name = "param5", value = "POLYV系统参数", required = false)
        private String param5;
        
        /**
         * IP地址
         */
        @ApiModelProperty(name = "ipAddress", value = "IP地址", required = false)
        private String ipAddress;
        
        /**
         * 国家
         */
        @ApiModelProperty(name = "country", value = "国家", required = false)
        private String country;
        
        /**
         * 省份
         */
        @ApiModelProperty(name = "province", value = "省份", required = false)
        private String province;
        
        /**
         * 城市
         */
        @ApiModelProperty(name = "city", value = "城市", required = false)
        private String city;
        
        /**
         * ISP运营商
         */
        @ApiModelProperty(name = "isp", value = "ISP运营商", required = false)
        private String isp;
        
        /**
         * 播放视频页面地址
         */
        @ApiModelProperty(name = "referer", value = "播放视频页面地址", required = false)
        private String referer;
        
        /**
         * 用户设备
         */
        @ApiModelProperty(name = "userAgent", value = "用户设备", required = false)
        private String userAgent;
        
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
         * 是否为移动端
         */
        @ApiModelProperty(name = "isMobile", value = "是否为移动端", required = false)
        private String isMobile;
        
        /**
         * 日志查询日期 (格式为：yyyy-MM-dd)
         */
        @ApiModelProperty(name = "currentDay", value = "日志查询日期 (格式为：yyyy-MM-dd)", required = false)
        @JSONField(format = "yyyy-MM-dd")
        private Date currentDay;
        
        /**
         * 日志查看时间。单位：小时
         */
        @ApiModelProperty(name = "currentHour", value = "日志查看时间。单位：小时", required = false)
        private Integer currentHour;
        
        /**
         * 用户观看渠道，取值有：vod_ios_sdk、vod_android_sdk、vod_flash、vod_pc_html5、vod_wechat_mini_program、vod_mobile_html5
         */
        @ApiModelProperty(name = "viewSource", value = "用户观看渠道，取值有：vod_ios_sdk、vod_android_sdk、vod_flash、vod_pc_html5" +
                "、vod_wechat_mini_program、vod_mobile_html5", required = false)
        private String viewSource;
        
        /**
         * 日志创建时间
         */
        @ApiModelProperty(name = "createdTime", value = "日志创建时间", required = false)
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date createdTime;
        
        /**
         * 日志更新日期
         */
        @ApiModelProperty(name = "lastModified", value = "日志更新日期", required = false)
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date lastModified;
    }
}
