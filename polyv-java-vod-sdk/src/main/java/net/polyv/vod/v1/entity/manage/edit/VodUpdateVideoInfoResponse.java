package net.polyv.vod.v1.entity.manage.edit;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 修改单个视频的信息返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("修改单个视频的信息返回实体")
public class VodUpdateVideoInfoResponse {
    
    /**
     * 视频截图大图地址
     */
    @ApiModelProperty(name = "imagesBig", value = "视频截图大图地址", required = false)
    @JSONField(name = "images_b")
    private String[] imagesBig;
    
    /**
     * 视频截图小图地址
     */
    @ApiModelProperty(name = "images", value = "视频截图小图地址", required = false)
    private String[] images;
    
    /**
     * 视频标签
     */
    @ApiModelProperty(name = "tag", value = "视频标签", required = false)
    private String tag;
    
    /**
     * 视频标题
     */
    @ApiModelProperty(name = "title", value = "视频标题", required = false)
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
    private String times;
    
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
     * 返回flash链接
     */
    @ApiModelProperty(name = "swfLink", value = "返回flash链接", required = false)
    @JSONField(name = "swf_link")
    private String swfLink;
    
    /**
     * 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除；
     */
    @ApiModelProperty(name = "status", value = "视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除；", required = false)
    private String status;
    
    /**
     * 加密视频为1，非加密为0
     */
    @ApiModelProperty(name = "seed", value = "加密视频为1，非加密为0", required = false)
    private Integer seed;
    
    /**
     * 流畅码率flv格式视频地址
     */
    @ApiModelProperty(name = "SDFlv", value = "流畅码率flv格式视频地址", required = false)
    @JSONField(name = "flv1")
    private String SDFlv;
    
    /**
     * 高清码率flv格式视频地址
     */
    @ApiModelProperty(name = "HDFlv", value = "高清码率flv格式视频地址", required = false)
    @JSONField(name = "flv2")
    private String HDFlv;
    
    /**
     * 超清码率flv格式视频地址
     */
    @ApiModelProperty(name = "FHDFlv", value = "超清码率flv格式视频地址", required = false)
    @JSONField(name = "flv3")
    private String FHDFlv;
    
    /**
     * 源文件
     */
    @ApiModelProperty(name = "sourceFile", value = "源文件", required = false)
    @JSONField(name = "sourcefile")
    private String sourceFile;
    
    /**
     * 视频宽度
     */
    @ApiModelProperty(name = "playerWidth", value = "视频宽度", required = false)
    @JSONField(name = "playerwidth")
    private String playerWidth;
    
    /**
     * 用户默认播放视频
     */
    @ApiModelProperty(name = "defaultVideo", value = "用户默认播放视频", required = false)
    @JSONField(name = "default_video")
    private String defaultVideo;
    
    /**
     * 视频时长,如：00:00:48
     */
    @ApiModelProperty(name = "duration", value = "视频时长,如：00:00:48", required = false)
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
    private String playerHeight;
    
    /**
     * 上传时间
     */
    @ApiModelProperty(name = "uploadTime", value = "上传时间", required = false)
    @JSONField(name = "ptime")
    private String uploadTime;
    
    /**
     * 源文件大小
     */
    @ApiModelProperty(name = "sourceFilesize", value = "源文件大小", required = false)
    @JSONField(name = "source_filesize")
    private String sourceFilesize;
    
    /**
     * 编码后各个清晰度视频的文件大小，类型为array
     */
    @ApiModelProperty(name = "filesize", value = "编码后各个清晰度视频的文件大小，类型为array", required = false)
    private String[] filesize;
    
    /**
     * md5校验值
     */
    @ApiModelProperty(name = "md5Checksum", value = "md5校验值", required = false)
    @JSONField(name = "md5checksum")
    private String md5Checksum;
    
    /**
     * 编码后各个清晰度视频的m3u8地址，类型为array
     */
    @ApiModelProperty(name = "hls", value = "编码后各个清晰度视频的m3u8地址，类型为array", required = false)
    private String[] hls;
    
    /**
     * 是否为源文件，否：0,是：1
     */
    @ApiModelProperty(name = "keepSource", value = "是否为源文件，否：0,是：1", required = false)
    @JSONField(name = "keepsource")
    private String keepSource;
    
    /**
     * 上传者信息
     */
    @ApiModelProperty(name = "uploader", value = "上传者信息", required = false)
    private Uploader uploader;
    
    /**
     * 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权
     */
    @ApiModelProperty(name = "hlsLevel", value = "加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权", required = false)
    private String hlsLevel;
    
    /**
     * 分类名称
     */
    @ApiModelProperty(name = "categoryName", value = "分类名称", required = false)
    @JSONField(name = "cataname")
    private String categoryName;
    
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
