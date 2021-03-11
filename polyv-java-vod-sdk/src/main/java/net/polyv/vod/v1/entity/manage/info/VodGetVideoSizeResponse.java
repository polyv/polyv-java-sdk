package net.polyv.vod.v1.entity.manage.info;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonResponse;

/**
 * 根据分类批量获取视频时长和大小返回实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("根据分类批量获取视频时长和大小返回实体")
public class VodGetVideoSizeResponse extends VodCommonResponse {
    
    /**
     * 分类ID
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 视频结果列表
     */
    @ApiModelProperty(name = "videos", value = "视频结果列表")
    private List<Video> videos;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("视频信息")
    public static class Video {
        
        /**
         * 视频ID
         */
        @ApiModelProperty(name = "videoId", value = "视频ID")
        @JSONField(name = "vid")
        private String videoId;
        
        /**
         * 时长，格式为时分秒
         */
        @ApiModelProperty(name = "duration", value = "时长，格式为时分秒")
        private String duration;
        
        /**
         * 编码后码率1FLV的大小
         */
        @ApiModelProperty(name = "filesize1", value = "编码后码率1FLV的大小")
        private Integer filesize1;
        
        /**
         * 编码后码率2FLV的大小
         */
        @ApiModelProperty(name = "filesize2", value = "编码后码率2FLV的大小")
        private Integer filesize2;
        
        /**
         * 编码后码率3FLV的大小
         */
        @ApiModelProperty(name = "filesize3", value = "编码后码率3FLV的大小")
        private Integer filesize3;
    }
    
}
