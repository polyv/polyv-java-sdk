package net.polyv.vod.v1.entity.subaccount;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频信息返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频信息返回实体")
public class VodSubAccountQueryVideoInfoResponse {
    
    /**
     * 视频id
     */
    @ApiModelProperty(name = "videoId", value = "视频id", required = false)
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 查询的视频基本信息
     */
    @ApiModelProperty(name = "basicInfo", value = "查询的视频基本信息", required = false)
    private BasicInfo basicInfo;
    
    /**
     * 查询的视频转码信息
     */
    @ApiModelProperty(name = "transcodeInfos", value = "查询的视频转码信息", required = false)
    private List<TranscodeInfos> transcodeInfos;
    
    /**
     * 查询的视频元数据
     */
    @ApiModelProperty(name = "metaData", value = "查询的视频元数据", required = false)
    private MetaData metaData;
    
    /**
     * 查询的视频截图
     */
    @ApiModelProperty(name = "snapshotInfo", value = "查询的视频截图", required = false)
    private SnapshotInfo snapshotInfo;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("查询的视频截图")
    public static class SnapshotInfo {
        /**
         * 截图url数组
         */
        @ApiModelProperty(name = "imageUrl", value = "截图url数组", required = false)
        private List<String> imageUrl;
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("查询的视频元数据")
    public static class MetaData {
        /**
         * 源文件大小，单位：Bytes
         */
        @ApiModelProperty(name = "size", value = "源文件大小，单位：Bytes", required = false)
        private Long size;
        
        /**
         * 视频容器类型，如mp4、flv等
         */
        @ApiModelProperty(name = "format", value = "视频容器类型，如mp4、flv等", required = false)
        private String format;
        
        /**
         * 源视频时长，单位：秒
         */
        @ApiModelProperty(name = "duration", value = "源视频时长，单位：秒", required = false)
        private Integer duration;
        
        /**
         * 视频码率，单位：bps
         */
        @ApiModelProperty(name = "bitrate", value = "视频码率，单位：bps", required = false)
        private Integer bitrate;
        
        /**
         * 视频帧率
         */
        @ApiModelProperty(name = "fps", value = "视频帧率", required = false)
        private Integer fps;
        
        /**
         * 分辨率高，单位：px
         */
        @ApiModelProperty(name = "height", value = "分辨率高，单位：px", required = false)
        private Integer height;
        
        /**
         * 分辨率宽，单位：px
         */
        @ApiModelProperty(name = "width", value = "分辨率宽，单位：px", required = false)
        private Integer width;
        
        /**
         * 编码格式，如h264、h265等
         */
        @ApiModelProperty(name = "codec", value = "编码格式，如h264、h265等", required = false)
        private String codec;
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("查询的视频转码信息")
    public static class TranscodeInfos {
        /**
         * 播放地址
         */
        @ApiModelProperty(name = "playUrl", value = "播放地址", required = false)
        private String playUrl;
        
        /**
         * 清晰度，SOURCE:原清晰度,LD:普清,SD:标清,HD:高清
         */
        @ApiModelProperty(name = "definition", value = "清晰度，SOURCE:原清晰度,LD:普清,SD:标清,HD:高清", required = false)
        private String definition;
        
        /**
         * 时长，秒
         */
        @ApiModelProperty(name = "duration", value = "时长，秒", required = false)
        private Integer duration;
        
        /**
         * 加密视频为true，非加密为false
         */
        @ApiModelProperty(name = "encrypt", value = "加密视频为true，非加密为false", required = false)
        private Boolean encrypt;
        
        /**
         * 转码格式，如mp4、flv、pdx、hls
         */
        @ApiModelProperty(name = "format", value = "转码格式，如mp4、flv、pdx、hls", required = false)
        private String format;
        
        /**
         * 视频帧率
         */
        @ApiModelProperty(name = "fps", value = "视频帧率", required = false)
        private Integer fps;
        
        /**
         * 码率kbps
         */
        @ApiModelProperty(name = "bitrate", value = "码率kbps", required = false)
        private Integer bitrate;
        
        /**
         * 分辨率高，单位：px
         */
        @ApiModelProperty(name = "height", value = "分辨率高，单位：px", required = false)
        private Integer height;
        
        /**
         * 分辨率宽，单位：px
         */
        @ApiModelProperty(name = "width", value = "分辨率宽，单位：px", required = false)
        private Integer width;
        
        /**
         * 视频状态, normal:可以正常播放,unavailable:不能正常播放
         */
        @ApiModelProperty(name = "status", value = "视频状态, normal:可以正常播放,unavailable:不能正常播放", required = false)
        private String status;
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("查询的视频基本信息")
    public static class BasicInfo {
        
        /**
         * 视频标题
         */
        @ApiModelProperty(name = "title", value = "视频标题", required = false)
        private String title;
        
        /**
         * 视频描述
         */
        @ApiModelProperty(name = "description", value = "视频描述", required = false)
        private String description;
        
        /**
         * 源视频时长，单位：秒
         */
        @ApiModelProperty(name = "duration", value = "源视频时长，单位：秒", required = false)
        private Integer duration;
        
        /**
         * 首图地址，大图
         */
        @ApiModelProperty(name = "coverURL", value = "首图地址，大图", required = false)
        private String coverURL;
        
        /**
         * 创建时间
         */
        @ApiModelProperty(name = "creationTime", value = "创建时间", required = false)
        private Date creationTime;
        
        /**
         * 更新时间
         */
        @ApiModelProperty(name = "updateTime", value = "更新时间", required = false)
        private Date updateTime;
        
        /**
         * 源文件大小，单位：Bytes
         */
        @ApiModelProperty(name = "size", value = "源文件大小，单位：Bytes", required = false)
        private Long size;
        
        /**
         * 视频状态码
         */
        @ApiModelProperty(name = "status", value = "视频状态码;60/61:已发布;10:等待编码;20:正在编码;50:等待审核;51:审核不通过;-1:已删除;",
                required = false)
        private Integer status;
        
        /**
         * 分类id, 如1为根目录
         */
        @ApiModelProperty(name = "categoryId", value = "分类id, 如1为根目录", required = false)
        @JSONField(name = "cateId")
        private String categoryId;
        
        /**
         * 分类名称
         */
        @ApiModelProperty(name = "categoryName", value = "分类名称", required = false)
        @JSONField(name = "cateName")
        private String categoryName;
        
        /**
         * 标签
         */
        @ApiModelProperty(name = "tags", value = "标签", required = false)
        private String tags;
        
        /**
         * 上传者
         */
        @ApiModelProperty(name = "uploader", value = "上传者", required = false)
        private String uploader;
    }
}
