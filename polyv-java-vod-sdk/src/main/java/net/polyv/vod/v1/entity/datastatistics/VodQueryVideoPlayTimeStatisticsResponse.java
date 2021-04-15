package net.polyv.vod.v1.entity.datastatistics;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频的播放时长统计数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频的播放时长统计数据返回实体")
public class VodQueryVideoPlayTimeStatisticsResponse {
    /**
     * 日期，格式 yyyy-MM-dd 例如 2021-03-24
     */
    @ApiModelProperty(name = "currentDay", value = "日期，格式 yyyy-MM-dd 例如 2021-03-24", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date currentDay;
    /**
     * PC端播放时长（单位：秒）
     */
    @ApiModelProperty(name = "pcPlayDuration", value = "PC端播放时长（单位：秒）", required = false)
    private Integer pcPlayDuration;
    /**
     * 格式化pc端播放时长，格式 hh:mm:ss 例如00:03:22
     */
    @ApiModelProperty(name = "formatPcPlayDuration", value = "格式化pc端播放时长，格式 hh:mm:ss 例如00:03:22", required = false)
    private String formatPcPlayDuration;
    /**
     * PC端视频平均播放时长，单位秒
     */
    @ApiModelProperty(name = "pcPlayDurationVideoAvg", value = "PC端视频平均播放时长，单位秒", required = false)
    private Integer pcPlayDurationVideoAvg;
    /**
     * 格式化PC端视频平均播放时长，格式 hh:mm:ss 例如00:03:22
     */
    @ApiModelProperty(name = "formatPcPlayDurationVideoAvg", value = "格式化PC端视频平均播放时长，格式 hh:mm:ss 例如00:03:22",
            required = false)
    private String formatPcPlayDurationVideoAvg;
    /**
     * PC端人均播放时长，单位秒
     */
    @ApiModelProperty(name = "pcPlayDurationPersonAvg", value = "PC端人均播放时长，单位秒", required = false)
    private Integer pcPlayDurationPersonAvg;
    /**
     * 格式化PC端人均播放时长，格式 hh:mm:ss 例如00:03:22
     */
    @ApiModelProperty(name = "formatPcPlayDurationPersonAvg", value = "格式化PC端人均播放时长，格式 hh:mm:ss 例如00:03:22",
            required = false)
    private String formatPcPlayDurationPersonAvg;
    /**
     * 移动端播放时长，单位秒
     */
    @ApiModelProperty(name = "mobilePlayDuration", value = "移动端播放时长，单位秒", required = false)
    private Integer mobilePlayDuration;
    /**
     * 格式化移动端播放时长，格式 hh:mm:ss 例如00:03:22
     */
    @ApiModelProperty(name = "formatMobilePlayDuration", value = "格式化移动端播放时长，格式 hh:mm:ss 例如00:03:22", required = false)
    private String formatMobilePlayDuration;
    /**
     * 移动端视频平均播放时长，单位秒
     */
    @ApiModelProperty(name = "mobilePlayDurationVideoAvg", value = "移动端视频平均播放时长，单位秒", required = false)
    private Integer mobilePlayDurationVideoAvg;
    /**
     * 格式化移动端视频平均播放时长，格式 hh:mm:ss 例如00:03:22
     */
    @ApiModelProperty(name = "formatMobilePlayDurationVideoAvg", value = "格式化移动端视频平均播放时长，格式 hh:mm:ss 例如00:03:22",
            required = false)
    private String formatMobilePlayDurationVideoAvg;
    /**
     * 移动端人均播放时长，单位秒
     */
    @ApiModelProperty(name = "mobilePlayDurationPersonAvg", value = "移动端人均播放时长，单位秒", required = false)
    private Integer mobilePlayDurationPersonAvg;
    /**
     * 格式化移动端人均播放时长，格式 hh:mm:ss 例如00:03:22
     */
    @ApiModelProperty(name = "formatMobilePlayDurationPersonAvg", value = "格式化移动端人均播放时长，格式 hh:mm:ss 例如00:03:22",
            required = false)
    private String formatMobilePlayDurationPersonAvg;
}
