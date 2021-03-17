package net.polyv.vod.v1.entity.manage.subtitle;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取视频字幕返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("获取视频字幕返回实体")
public class VodGetSubtitleListResponse {
    /**
     * 查询的结果列表
     */
    @ApiModelProperty(name = "Subtitles", value = "查询的结果列表", required = false)
    @JSONField(name = "srts")
    private List<Subtitle> subtitles;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("视频字幕信息")
    public static class Subtitle {
        /**
         * 序号，从1开始
         */
        @ApiModelProperty(name = "rank", value = "序号，从1开始", required = false)
        private Integer rank;
        
        /**
         * 字幕名称
         */
        @ApiModelProperty(name = "name", value = "字幕名称", required = false)
        private String name;
    }
}
