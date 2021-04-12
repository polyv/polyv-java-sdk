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
 * 根据授权播放开关状态查找视频返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("根据授权播放开关状态查找视频返回实体")
public class VodQueryVideoListResponse extends VodPageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "视频信息", required = false)
    private List<VodQueryVideoList> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("视频信息")
    public static class VodQueryVideoList{
    
        /**
         * 视频时长，格式为 时:分:秒，如：00:59:54
         */
        @ApiModelProperty(name = "duration", value = "视频时长，格式为 时:分:秒，如：00:59:54", required = false)
        private String duration;
    
        /**
         * 视频ID
         */
        @ApiModelProperty(name = "vid", value = "视频ID", required = false)
        private String vid;
    
        /**
         * 最后修改时间，格式为：yyyy-MM-dd HH:mm
         */
        @ApiModelProperty(name = "date", value = "最后修改时间，格式为：yyyy-MM-dd HH:mm", required = false)
        @JSONField(format = "yyyy-MM-dd HH:mm")
        private Date date;
    
        /**
         * 上传者，取值：主账号、API、${子账号名称}
         */
        @ApiModelProperty(name = "uploader", value = "上传者，取值：主账号、API、${子账号名称}", required = false)
        private String uploader;
    
        /**
         * 分类ID
         */
        @ApiModelProperty(name = "categoryId", value = "分类ID", required = false)
        @JSONField(name = "cataid")
        private String categoryId;
    
        /**
         * 分类名称
         */
        @ApiModelProperty(name = "categoryName", value = "分类名称", required = false)
        @JSONField(name = "cataname")
        private String categoryName;
    
        /**
         * 原文件大小，单位有：Bytes、KB、 MB、GB，例：227.61 MB
         */
        @ApiModelProperty(name = "sourceFileSize", value = "原文件大小，单位有：Bytes、KB、 MB、GB，例：227.61 MB", required = false)
        private String sourceFileSize;
    
        /**
         * 首图地址
         */
        @ApiModelProperty(name = "firstImage", value = "首图地址", required = false)
        private String firstImage;
    
        /**
         * 视频标题
         */
        @ApiModelProperty(name = "title", value = "视频标题", required = false)
        private String title;
    
        /**
         * 视频状态，如：已发布
         */
        @ApiModelProperty(name = "status", value = "视频状态，如：已发布", required = false)
        private String status;
        
    }

}
