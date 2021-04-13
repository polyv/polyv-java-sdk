package net.polyv.vod.v1.entity.subaccount;

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
 * 搜索视频返回实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("搜索视频返回实体")
public class VodSubAccountSearchVideoListResponse extends VodPageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "查询的结果列表", required = false)
    private List<VodSearchVideoList> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("查询的结果列表")
    public static class VodSearchVideoList {
        
        /**
         * 上传者邮箱
         */
        @ApiModelProperty(name = "videoId", value = "视频id", required = false)
        @JSONField(name = "vid")
        private String videoId;
        
        @ApiModelProperty(name = "basicInfo", value = "查询的视频基本信息", required = false)
        private BasicInfo basicInfo;
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
