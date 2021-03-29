package net.polyv.vod.v1.entity.datastatistics;

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
 * 高级分析-分页查询观看行为列表返回实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("高级分析-分页查询观看行为列表返回实体")
public class VodQueryViewingBehaviorListResponse extends VodPageCommonResponse {
    /**
     * 返回的结果集
     */
    @ApiModelProperty(name = "contents", value = "返回的结果集", required = false)
    private List<ViewingBehaviorInfo> contents;
    
    /**
     * 查询下一页时传的凭证
     */
    @ApiModelProperty(name = "token", value = "查询下一页时传的凭证", required = false)
    private String token;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("观看行为信息")
    public static class ViewingBehaviorInfo {
        /**
         * 首次观看日期，格式yyyy-MM-dd HH:mm:ss 例如 2019-10-01 11:12:05
         */
        @ApiModelProperty(name = "startTime", value = "首次观看日期，格式yyyy-MM-dd HH:mm:ss 例如 2019-10-01 11:12:05",
                required = false)
        @JSONField(name = "startTime", format = "yyyy-MM-dd HH:mm:ss")
        private Date startTime;
        
        /**
         * 视频id
         */
        @ApiModelProperty(name = "videoId", value = "视频id", required = false)
        private String videoId;
        
        /**
         * 视频名称
         */
        @ApiModelProperty(name = "videoName", value = "视频名称", required = false)
        private String videoName;
        
        /**
         * 视频首图（没有添加协议头）
         */
        @ApiModelProperty(name = "videoImage", value = "视频首图（没有添加协议头）", required = false)
        private String videoImage;
        
        /**
         * 视频时长，单位：秒
         */
        @ApiModelProperty(name = "videoDuration", value = "视频时长，单位：秒", required = false)
        private Integer videoDuration;
        
        /**
         * 设备名称
         */
        @ApiModelProperty(name = "deviceClass", value = "设备名称", required = false)
        private String deviceClass;
        
        /**
         * 操作系统
         */
        @ApiModelProperty(name = "osName", value = "操作系统", required = false)
        private String osName;
        
        /**
         * 终端名称
         */
        @ApiModelProperty(name = "agentName", value = "终端名称", required = false)
        private String agentName;
        
        /**
         * 终端版本
         */
        @ApiModelProperty(name = "agentVersion", value = "终端版本", required = false)
        private String agentVersion;
        
        /**
         * 来源
         */
        @ApiModelProperty(name = "referer", value = "来源", required = false)
        private String referer;
        
        /**
         * ip地址
         */
        @ApiModelProperty(name = "ip", value = "ip地址", required = false)
        private String ip;
        
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
         * 地区
         */
        @ApiModelProperty(name = "city", value = "地区", required = false)
        private String city;
        
        /**
         * 运营商
         */
        @ApiModelProperty(name = "isp", value = "运营商", required = false)
        private String isp;
        
        /**
         * 观众id
         */
        @ApiModelProperty(name = "viewerId", value = "观众id", required = false)
        private String viewerId;
        
        /**
         * 观众昵称
         */
        @ApiModelProperty(name = "viewerNickName", value = "观众昵称", required = false)
        private String viewerNickName;
        
        /**
         * 观众头像
         */
        @ApiModelProperty(name = "viewerAvatar", value = "观众头像", required = false)
        private String viewerAvatar;
        
        /**
         * 观众看的视频总量
         */
        @ApiModelProperty(name = "totalVideoCount", value = "观众看的视频总量", required = false)
        private Integer totalVideoCount;
        
        /**
         * 热力图（["0-1:1","3-4:2"]表示视频的0到1秒有1次观看，3到4秒有2次观看）
         */
        @ApiModelProperty(name = "heatmap", value = "热力图（[\"0-1:1\",\"3-4:2\"]表示视频的0到1秒有1次观看，3到4秒有2次观看）", required =
                false)
        private String heatmap;
        
        /**
         * 观看完成度
         */
        @ApiModelProperty(name = "completionRate", value = "观看完成度", required = false)
        private Float completionRate;
        
        /**
         * 视频的状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除；
         */
        @ApiModelProperty(name = "status", value = "视频的状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除；", required =
                false)
        private Integer status;
    }
}
