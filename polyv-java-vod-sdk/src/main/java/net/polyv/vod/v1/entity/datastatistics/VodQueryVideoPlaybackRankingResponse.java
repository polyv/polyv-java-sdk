package net.polyv.vod.v1.entity.datastatistics;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频播放量排行接口返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频播放量排行接口返回实体")
public class VodQueryVideoPlaybackRankingResponse {
    /**
     * pc端总播放量
     */
    @ApiModelProperty(name = "totalPcVideoView", value = "pc端总播放量", required = false)
    private Integer totalPcVideoView;
    
    /**
     * 移动端总播放量
     */
    @ApiModelProperty(name = "totalMoVideoView", value = "移动端总播放量", required = false)
    private Integer totalMoVideoView;
    
    /**
     * pc端播放量排行列表
     */
    @ApiModelProperty(name = "pcVideoDaily", value = "pc端播放量排行列表", required = false)
    @JSONField(name = "pcVideoDailys")
    private List<VideoDaily> pcVideoDaily;
    
    /**
     * 移动端播放量排行列表
     */
    @ApiModelProperty(name = "moVideoDaily", value = "移动端播放量排行列表", required = false)
    @JSONField(name = "moVideoDailys")
    private List<VideoDaily> moVideoDaily;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("播放量排行信息")
    public static class VideoDaily {
        /**
         * 视频vid
         */
        @ApiModelProperty(name = "videoId", value = "视频videoId", required = false)
        private String videoId;
        
        /**
         * 视频标题
         */
        @ApiModelProperty(name = "title", value = "视频标题", required = false)
        private String title;
        
        /**
         * 播放时长，格式 hh:mm:ss 例如 00:03:11
         */
        @ApiModelProperty(name = "duration", value = "播放时长，格式 hh:mm:ss 例如 00:03:11", required = false)
        private String duration;
        
        /**
         * pc端播放量
         */
        @ApiModelProperty(name = "pcVideoView", value = "pc端播放量", required = false)
        private Integer pcVideoView;
        
        /**
         * 移动端播放量
         */
        @ApiModelProperty(name = "mobileVideoView", value = "移动端播放量", required = false)
        private Integer mobileVideoView;
    }
}
