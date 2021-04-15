package net.polyv.vod.v1.entity.datastatistics;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频终端环境统计数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频终端环境统计数据返回实体")
public class VodQueryVideoDeviceStatisticsResponse {
    /**
     * 终端环境统计数据
     */
    @ApiModelProperty(name = "device", value = "终端环境统计数据", required = false)
    private List<Device> device;
    
    /**
     * 操作系统环境统计数据
     */
    @ApiModelProperty(name = "operatingSystem", value = "操作系统环境统计数据", required = false)
    private List<OperatingSystem> operatingSystem;
    
    /**
     * 浏览器环境统计数据
     */
    @ApiModelProperty(name = "browser", value = "浏览器环境统计数据", required = false)
    private List<Browser> browser;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("终端信息")
    public static class Device {
        /**
         * 终端环境名称，PC端或移动端
         */
        @ApiModelProperty(name = "deviceName", value = "终端环境名称，PC端或移动端", required = false)
        private String deviceName;
        /**
         * 视频总播放量
         */
        @ApiModelProperty(name = "videoView", value = "视频总播放量", required = false)
        private Integer videoView;
        /**
         * 视频总播放时长，格式 hh:mm:ss 例如00:03:22
         */
        @ApiModelProperty(name = "formatPlayDuration", value = "视频总播放时长，格式 hh:mm:ss 例如00:03:22", required = false)
        private String formatPlayDuration;
        /**
         * 视频总播放时长，单位：秒
         */
        @ApiModelProperty(name = "playDuration", value = "视频总播放时长，单位：秒", required = false)
        private Integer playDuration;
        /**
         * 视频总观众数
         */
        @ApiModelProperty(name = "uniqueViewer", value = "视频总观众数", required = false)
        private Integer uniqueViewer;
        /**
         * 总占比
         */
        @ApiModelProperty(name = "percentage", value = "总占比", required = false)
        private Float percentage;
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("操作系统信息")
    public static class OperatingSystem {
        /**
         * 操作系统环境名称
         */
        @ApiModelProperty(name = "operateSystemName", value = "操作系统环境名称", required = false)
        private String operateSystemName;
        /**
         * 视频总播放量
         */
        @ApiModelProperty(name = "videoView", value = "视频总播放量", required = false)
        private Integer videoView;
        /**
         * 视频总播放时长，格式 hh:mm:ss 例如00:03:22
         */
        @ApiModelProperty(name = "formatPlayDuration", value = "视频总播放时长，格式 hh:mm:ss 例如00:03:22", required = false)
        private String formatPlayDuration;
        /**
         * 视频总播放时长，格式 hh:mm:ss 例如00:03:22
         */
        @ApiModelProperty(name = "playDuration", value = "视频总播放时长，格式 hh:mm:ss 例如00:03:22", required = false)
        private String playDuration;
        /**
         * 视频总观众数
         */
        @ApiModelProperty(name = "uniqueViewer", value = "视频总观众数", required = false)
        private Integer uniqueViewer;
        /**
         * 总占比
         */
        @ApiModelProperty(name = "percentage", value = "总占比", required = false)
        private Float percentage;
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("浏览器信息")
    public static class Browser {
        /**
         * 浏览器环境名称
         */
        @ApiModelProperty(name = "browserName", value = "浏览器环境名称", required = false)
        private String browserName;
        /**
         * 格式化的PC播放时长，格式 hh:mm:ss 例如00:00:00
         */
        @ApiModelProperty(name = "formatPcPlayDuration", value = "格式化的PC播放时长，格式 hh:mm:ss 例如00:00:00", required = false)
        private String formatPcPlayDuration;
        /**
         * PC端播放时长，单位秒
         */
        @ApiModelProperty(name = "pcPlayDuration", value = "PC端播放时长，单位秒", required = false)
        private Integer pcPlayDuration;
        /**
         * PC端播放量
         */
        @ApiModelProperty(name = "pcVideoView", value = "PC端播放量", required = false)
        private Integer pcVideoView;
        /**
         * PC端唯一观众数
         */
        @ApiModelProperty(name = "pcUniqueViewer", value = "PC端唯一观众数", required = false)
        private Integer pcUniqueViewer;
        /**
         * 格式化的移动端播放时长，格式 hh:mm:ss 例如00:00:00
         */
        @ApiModelProperty(name = "formatMobilePlayDuration", value = "格式化的移动端播放时长，格式 hh:mm:ss 例如00:00:00", required =
                false)
        private String formatMobilePlayDuration;
        /**
         * 移动端播放时长，单位秒
         */
        @ApiModelProperty(name = "mobilePlayDuration", value = "移动端播放时长，单位秒", required = false)
        private Integer mobilePlayDuration;
        /**
         * 移动端播放量
         */
        @ApiModelProperty(name = "mobileVideoView", value = "移动端播放量", required = false)
        private Integer mobileVideoView;
        /**
         * 移动端播放量
         */
        @ApiModelProperty(name = "mobileUniqueViewer", value = "移动端播放量", required = false)
        private Integer mobileUniqueViewer;
        /**
         * PC端数据占比
         */
        @ApiModelProperty(name = "pcPercentage", value = "PC端数据占比", required = false)
        private Float pcPercentage;
        /**
         * 移动端数据占比
         */
        @ApiModelProperty(name = "mobilePercentage", value = "移动端数据占比", required = false)
        private Float mobilePercentage;
    }
    
}
