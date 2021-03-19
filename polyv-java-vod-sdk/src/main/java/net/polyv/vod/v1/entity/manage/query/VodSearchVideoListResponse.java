package net.polyv.vod.v1.entity.manage.query;

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
 * 查找视频返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查找视频返回实体")
public class VodSearchVideoListResponse extends VodPageCommonResponse {
    
    /**
     * 视频信息
     */
    @ApiModelProperty(name = "contents", value = "视频信息", required = false)
    private List<VodSearchVideoList> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("视频信息")
    public static class VodSearchVideoList {
        
        /**
         * 视频标签
         */
        @ApiModelProperty(name = "tag", value = "视频标签", required = false)
        private String tag;
        
        /**
         * MP4源文件
         */
        @ApiModelProperty(name = "mp4", value = "MP4源文件", required = false)
        private String mp4;
        
        /**
         * 标题
         */
        @ApiModelProperty(name = "title", value = "标题", required = false)
        private String title;
        
        /**
         * 视频码率数
         */
        @ApiModelProperty(name = "df", value = "视频码率数", required = false)
        private Integer df;
        
        /**
         * 播放次数
         */
        @ApiModelProperty(name = "times", value = "播放次数", required = false)
        private Integer times;
        
        /**
         * 视频id
         */
        @ApiModelProperty(name = "videoId", value = "视频id", required = false)
        @JSONField(name = "vid")
        private String videoId;
        
        /**
         * 流畅码率mp4格式视频地址
         */
        @ApiModelProperty(name = "SDMp4", value = "流畅码率mp4格式视频地址", required = false)
        @JSONField(name = "mp4_1")
        private String SDMp4;
        
        /**
         * 高清码率mp4格式视频地址
         */
        @ApiModelProperty(name = "HDmp4", value = "高清码率mp4格式视频地址", required = false)
        @JSONField(name = "mp4_2")
        private String HDmp4;
        
        /**
         * 超清码率mp4格式视频地址
         */
        @ApiModelProperty(name = "FHDmp4", value = "超清码率mp4格式视频地址", required = false)
        @JSONField(name = "mp4_3")
        private String FHDmp4;
        
        /**
         * 分类id， 如1为根目录
         */
        @ApiModelProperty(name = "categoryId", value = "分类id， 如1为根目录", required = false)
        @JSONField(name = "cataid")
        private String categoryId;
        
        /**
         * 返回视频flash链接
         */
        @ApiModelProperty(name = "swfLink", value = "返回视频flash链接", required = false)
        @JSONField(name = "swf_link")
        private String swfLink;
        
        /**
         * 视频状态码（60/61：已发布；10：等待编码；20：正在编码；50：等待审核；51：审核不通过，-1：已删除；）
         */
        @ApiModelProperty(name = "status", value = "视频状态码视频状态码（60/61：已发布；10：等待编码；20：正在编码；50：等待审核；51：审核不通过，-1：已删除；）",
                required = false)
        private Integer status;
        
        /**
         * 加密视频为1，非加密为0
         */
        @ApiModelProperty(name = "seed", value = "加密视频为1，非加密为0", required = false)
        private Integer seed;
        
        /**
         * 视频宽度
         */
        @ApiModelProperty(name = "playerWidth", value = "视频宽度", required = false)
        @JSONField(name = "playerwidth")
        private Integer playerWidth;
        
        /**
         * 时长,如：00:15:46
         */
        @ApiModelProperty(name = "duration", value = "时长,如：00:15:46", required = false)
        private String duration;
        
        /**
         * 视频首图
         */
        @ApiModelProperty(name = "firstImage", value = "视频首图", required = false)
        @JSONField(name = "first_image")
        private String firstImage;
        
        /**
         * 最佳分辨率
         */
        @ApiModelProperty(name = "originalDefinition", value = "最佳分辨率", required = false)
        @JSONField(name = "original_definition")
        private String originalDefinition;
        
        /**
         * 视频描述
         */
        @ApiModelProperty(name = "context", value = "视频描述", required = false)
        private String context;
        
        /**
         * 视频高度
         */
        @ApiModelProperty(name = "playerHeight", value = "视频高度", required = false)
        @JSONField(name = "playerheight")
        private Integer playerHeight;
        
        /**
         * 视频上传日期
         */
        @ApiModelProperty(name = "uploadTime", value = "视频上传日期", required = false)
        @JSONField(name = "ptime", format = "yyyy-MM-dd HH:mm:ss")
        private Date uploadTime;
        
        /**
         * 源视频文件大小，单位为byte
         */
        @ApiModelProperty(name = "source_filesize", value = "源视频文件大小，单位为byte", required = false)
        @JSONField(name = "source_filesize")
        private Integer sourceFilesize;
        
        /**
         * 编码后各个码率的视频文件大小，按顺序依次为流畅、高清、超清的视频文件大小，单位为byte
         */
        @ApiModelProperty(name = "filesize", value = "编码后各个码率的视频文件大小，按顺序依次为流畅、高清、超清的视频文件大小，单位为byte", required = false)
        private Integer[] filesize;
        
        /**
         * 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整
         */
        @ApiModelProperty(name = "md5Checksum", value = "上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整", required = false)
        @JSONField(name = "md5checksum")
        private String md5Checksum;
        
        /**
         * 流畅、高清、超清清晰度的m3u8d地址
         */
        @ApiModelProperty(name = "hls", value = "流畅、高清、超清清晰度的m3u8地址", required = false)
        private String[] hls;
        
        /**
         * 上传者信息
         */
        @ApiModelProperty(name = "uploader", value = "上传者信息", required = false)
        private Uploader uploader;
        
        /**
         * 是否为源文件，否：0,是：1
         */
        @ApiModelProperty(name = "keepSource", value = "是否为源文件，否：0,是：1", required = false)
        @JSONField(name = "keepsource")
        private Integer keepSource;
        
        /**
         * 分类名称
         */
        @ApiModelProperty(name = "categoryName", value = "分类名称", required = false)
        @JSONField(name = "cataname")
        private String categoryName;
    }
    
    
    @Data
    @Accessors(chain = true)
    @ApiModel("上传者信息")
    public static class Uploader {
        
        /**
         * 上传者邮箱
         */
        @ApiModelProperty(name = "email", value = "上传者邮箱", required = false)
        private String email;
        
        /**
         * 上传者名称
         */
        @ApiModelProperty(name = "name", value = "上传者名称", required = false)
        private String name;
        
        /**
         * 上传者角色,如管理员,上传者,主账号
         */
        @ApiModelProperty(name = "role", value = "上传者角色,如管理员,上传者,主账号", required = false)
        private String role;
        
    }
}
