package net.polyv.vod.v1.entity.manage.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取微信分享页的视频相关信息返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("获取微信分享页的视频相关信息返回实体")
public class VodGetWeChatShareVideoInfoResponse {
    
    /**
     * 视频封面图
     */
    @ApiModelProperty(name = "videoCoverImg", value = "视频封面图", required = false)
    private String videoCoverImg;
    
    /**
     * 视频微信分享标题
     */
    @ApiModelProperty(name = "videoTitle", value = "视频微信分享标题", required = false)
    private String videoTitle;
    
    /**
     * 视频描述
     */
    @ApiModelProperty(name = "videoDesc", value = "视频描述", required = false)
    private String videoDesc;
    
    /**
     * 视频图标
     */
    @ApiModelProperty(name = "videoIcon", value = "视频图标", required = false)
    private String videoIcon;
    
    /**
     * 初始播放量
     */
    @ApiModelProperty(name = "originalPlayTimes", value = "初始播放量", required = false)
    private Integer originalPlayTimes;
    
    /**
     * 初始点赞量
     */
    @ApiModelProperty(name = "originalLikeNum", value = "初始点赞量", required = false)
    private Integer originalLikeNum;
}
