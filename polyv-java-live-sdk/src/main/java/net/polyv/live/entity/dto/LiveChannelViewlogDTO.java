package net.polyv.live.entity.dto;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 频道观看日志
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("频道观看日志")
public class LiveChannelViewlogDTO {
    
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
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private String channelId;
    
    /**
     * 播放时长，单位：秒
     */
    @ApiModelProperty(name = "playDuration", value = "播放时长，单位：秒", required = false)
    private Integer playDuration;
    
    /**
     * 停留时长，单位：秒
     */
    @ApiModelProperty(name = "stayDuration", value = "停留时长，单位：秒", required = false)
    private Integer stayDuration;
    
    /**
     * 流量大小
     */
    @ApiModelProperty(name = "flowSize", value = "流量大小", required = false)
    private Long flowSize;
    
    /**
     * 直播的场次ID
     */
    @ApiModelProperty(name = "sessionId", value = "直播的场次ID", required = false)
    private String sessionId;
    
    /**
     * 观众id
     */
    @ApiModelProperty(name = "viewerId", value = "观众id", required = false)
    @JSONField(name = "param1")
    private String viewerId;
    
    /**
     * 观众名称
     */
    @ApiModelProperty(name = "viewerName", value = "观众名称", required = false)
    @JSONField(name = "param2")
    private String viewerName;
    
    /**
     * 观看类型：取值vod 表示观看回放，取值live 表示直播
     */
    @ApiModelProperty(name = "logType", value = "观看类型：取值vod 表示观看回放，取值live 表示直播", required = false)
    @JSONField(name = "param3")
    private String logType;
    
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
     * 是否为移动端,Y:移动端，N：非移动端
     */
    @ApiModelProperty(name = "isMobile", value = "是否为移动端,Y:移动端，N：非移动端", required = false)
    private String isMobile;
    
    /**
     * 日志查询日期
     */
    @ApiModelProperty(name = "currentDay", value = "日志查询日期", required = false)
    private Date currentDay;
    
    /**
     * 日志创建日期
     */
    @ApiModelProperty(name = "createdTime", value = "日志创建日期", required = false)
    private Date createdTime;
    
    /**
     * 日志更新日期
     */
    @ApiModelProperty(name = "lastModified", value = "日志更新日期", required = false)
    private Date lastModified;
    
}
