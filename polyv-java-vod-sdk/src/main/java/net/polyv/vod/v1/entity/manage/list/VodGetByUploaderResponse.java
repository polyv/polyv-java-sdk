package net.polyv.vod.v1.entity.manage.list;

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
 * 获取某分类下某子账号的视频列表返回实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取某分类下某子账号的视频列表返回实体")
public class VodGetByUploaderResponse extends VodPageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "视频列表", required = false)
    private List<VideoInfo> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("视频信息")
    public static class VideoInfo {
        /**
         * 视频id
         */
        @ApiModelProperty(name = "videoId", value = "视频vid", required = false)
        @JSONField(name = "vid")
        private String videoId;
        
        /**
         * 分类id
         */
        @ApiModelProperty(name = "categoryId", value = "分类id", required = false)
        @JSONField(name = "cataid")
        private String categoryId;
        
        /**
         * 视频标题
         */
        @ApiModelProperty(name = "title", value = "视频标题", required = false)
        private String title;
        
        /**
         * 视频简介
         */
        @ApiModelProperty(name = "context", value = "视频简介", required = false)
        private String context;
        
        /**
         * 播放次数
         */
        @ApiModelProperty(name = "times", value = "播放次数", required = false)
        private Integer times;
        
        /**
         * 视频首图，封面
         */
        @ApiModelProperty(name = "firstImage", value = "视频首图，封面", required = false)
        private String firstImage;
        
        /**
         * 标签，以英文逗号(,)分割，没有标签时返回空串
         */
        @ApiModelProperty(name = "tag", value = "标签，以英文逗号(,)分割，没有标签时返回空串", required = false)
        private String tag;
        
        /**
         * 音频地址
         */
        @ApiModelProperty(name = "aacLink", value = "音频地址", required = false)
        private String aacLink;
        
        /**
         * 视频状态码（60/61：已发布；10：等待编码；20：正在编码；50：等待审核；51：审核不通过，-1：已删除；）
         */
        @ApiModelProperty(name = "status", value = "视频状态码（60/61：已发布；10：等待编码；20：正在编码；50：等待审核；51：审核不通过，-1：已删除；）",
                required = false)
        private Integer status;
        
        /**
         * 子账号邮箱
         */
        @ApiModelProperty(name = "uploaderEmail", value = "子账号邮箱", required = false)
        private String uploaderEmail;
        
        /**
         * 上传时间，格式 yyyy-MM-dd HH:mm
         */
        @ApiModelProperty(name = "uploadTime", value = "上传时间，格式 yyyy-MM-dd HH:mm", required = false)
        @JSONField(name = "ptime", format = "yyyy-MM-dd HH:mm")
        private Date uploadTime;
    }
}
