package net.polyv.vod.v1.entity.play.list;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取单个播放列表返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("获取单个播放列表返回实体")
public class VodGetOnePlayListResponse {
    /**
     * 详细介绍
     */
    @ApiModelProperty(name = "desc", value = "详细介绍", required = false)
    @JSONField(name = "describ")
    private String desc;
    
    /**
     * 标签
     */
    @ApiModelProperty(name = "tag", value = "标签", required = false)
    private String tag;
    
    /**
     * 播放列表标题
     */
    @ApiModelProperty(name = "title", value = "播放列表标题", required = false)
    private String title;
    
    /**
     * 最后修改时间，时间格式 yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "lastModify", value = "最后修改时间，时间格式 yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(name = "lmodify", format = "yyyy-MM-dd HH:mm:ss")
    private Date lastModify;
    
    /**
     * 视频数量
     */
    @ApiModelProperty(name = "video_count", value = "视频数量", required = false)
    @JSONField(name = "video_count")
    private Integer videoCount;
    
    /**
     * 视频列表ID
     */
    @ApiModelProperty(name = "videoId", value = "视频列表ID", required = false)
    @JSONField(name = "videoid")
    private String videoId;
    
    /**
     * 创建时间，时间格式 yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "createTime", value = "创建时间，时间格式 yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(name = "ptime", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    /**
     * 视频集合
     */
    @ApiModelProperty(name = "videoList", value = "视频集合", required = false)
    @JSONField(name = "videolist")
    private List<VideoInfo> videoList;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("视频信息")
    public static class VideoInfo {
        /**
         * 视频id
         */
        @ApiModelProperty(name = "videoId", value = "视频id", required = false)
        @JSONField(name = "vid")
        private String videoId;
        /**
         * 视频标题
         */
        @ApiModelProperty(name = "title", value = "视频标题", required = false)
        private String title;
        /**
         * 视频时长,格式 HH:mm:ss。例如 00:03:11
         */
        @ApiModelProperty(name = "duration", value = "视频时长，格式 HH:mm:ss。例如 00:03:11", required = false)
        private String duration;
        /**
         * 外链地址
         */
        @ApiModelProperty(name = "publishUrl", value = "外链地址", required = false)
        @JSONField(name = "publish_url")
        private String publishUrl;
        /**
         * 视频首图
         */
        @ApiModelProperty(name = "firstImage", value = "视频首图", required = false)
        @JSONField(name = "first_image")
        private String firstImage;
        /**
         * 默认视频链接地址
         */
        @ApiModelProperty(name = "defaultVideoLink", value = "默认视频链接地址", required = false)
        @JSONField(name = "default_videolink")
        private String defaultVideoLink;
    }
}
