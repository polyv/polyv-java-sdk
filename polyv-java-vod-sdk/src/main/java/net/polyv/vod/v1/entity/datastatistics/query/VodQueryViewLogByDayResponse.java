package net.polyv.vod.v1.entity.datastatistics.query;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonResponse;

/**
 * 获取某一天视频观看日志返回实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取某一天视频观看日志返回实体")
public class VodQueryViewLogByDayResponse extends VodCommonResponse {
    
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
     * 播放时长，单位为秒 (用户观看的总时间 ，例如：18：00开始看一个视频，看到了18：30，这30分钟就是播放时长)
     */
    @ApiModelProperty(name = "playDuration", value = "播放时长，单位为秒 (用户观看的总时间 ，例如：18：00开始看一个视频，看到了18：30，这30分钟就是播放时长)",
            required = false)
    private Integer playDuration;
    
    /**
     * 缓存时长，单位为秒
     */
    @ApiModelProperty(name = "stayDuration", value = "缓存时长，单位为秒", required = false)
    private Integer stayDuration;
    
    /**
     * 播放时间，单位为秒 （用户观看的最后时间，例如：停止观看视频的时候，进度条最后的分钟数为35分钟，播放时间就是35分钟）
     */
    @ApiModelProperty(name = "currentTimes", value = "播放时间，单位为秒 （用户观看的最后时间，例如：停止观看视频的时候，进度条最后的分钟数为35分钟，播放时间就是35分钟）",
            required = false)
    private Integer currentTimes;
    
    /**
     * 视频总时长，单位为秒
     */
    @ApiModelProperty(name = "duration", value = "视频总时长，单位为秒", required = false)
    private Integer duration;
    
    /**
     * 流量大小，单位为字节
     */
    @ApiModelProperty(name = "flowSize", value = "流量大小，单位为字节", required = false)
    private Long flowSize;
    
    /**
     * 用户自定义参数，如学员ID等
     */
    @ApiModelProperty(name = "sessionId", value = "用户自定义参数，如学员ID等", required = false)
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
     * 是否为移动端，Y:是；N：否
     */
    @ApiModelProperty(name = "isMobile", value = "是否为移动端，Y:是；N：否", required = false)
    private String isMobile;
    
    /**
     * 日志查询日期 (格式为：yyyy-MM-dd)
     */
    @ApiModelProperty(name = "currentDay", value = "日志查询日期 (格式为：yyyy-MM-dd)", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date currentDay;
    
    /**
     * 日志查看时间，单位为小时
     */
    @ApiModelProperty(name = "currentHour", value = "日志查看时间，单位为小时", required = false)
    private Integer currentHour;
    
    /**
     * 用户观看渠道，取值有：
     * vod_ios_sdk：ios端、vod_android_sdk：安卓端、vod_flash：flash、vod_wechat_mini_program：微信小程序、
     * vod_pc_html5：pc端web、vod_mobile_html5：移动端web、vod_mobile_html5_v2：移动端web v2
     */
    @ApiModelProperty(name = "viewSource", value = "用户观看渠道，取值有：vod_ios_sdk：ios端、vod_android_sdk：安卓端、vod_flash：flash" +
            "、vod_wechat_mini_program：微信小程序;vod_pc_html5：pc端web、vod_mobile_html5：移动端web、vod_mobile_html5_v2：移动端web " +
            "v2", required = false)
    private String viewSource;
    
    /**
     * 日志创建时间，格式：yyyy-MM-dd HH:mm
     */
    @ApiModelProperty(name = "createdTime", value = "日志创建时间，格式：yyyy-MM-dd HH:mm", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createdTime;
    
    /**
     * 日志更新日期，格式：yyyy-MM-dd HH:mm
     */
    @ApiModelProperty(name = "lastModified", value = "日志更新日期，格式：yyyy-MM-dd HH:mm", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date lastModified;
    
}
