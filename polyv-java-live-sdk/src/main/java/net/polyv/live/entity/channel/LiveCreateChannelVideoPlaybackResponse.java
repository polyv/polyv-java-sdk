package net.polyv.live.entity.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 将点播中的视频添加到视频库返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("将点播中的视频添加到视频库返回实体")
public class LiveCreateChannelVideoPlaybackResponse {
    
    /**
     * 直播系统生成的id
     */
    @ApiModelProperty(name = "videoId", value = "直播系统生成的id", required = false)
    private String videoId;
    
    /**
     * 点播视频vid
     */
    @ApiModelProperty(name = "videoPoolId", value = "点播视频vid", required = false)
    private String videoPoolId;
    
    /**
     * 点播后台用户id
     */
    @ApiModelProperty(name = "userId", value = "点播后台用户id", required = false)
    private String userId;
    
    /**
     * 回放视频对应的直播频道id
     */
    @ApiModelProperty(name = "channelId", value = "回放视频对应的直播频道id", required = false)
    private Integer channelId;
    
    /**
     * 视频标题
     */
    @ApiModelProperty(name = "title", value = "视频标题", required = false)
    private String title;
    
    /**
     * 视频首图
     */
    @ApiModelProperty(name = "firstImage", value = "视频首图", required = false)
    private String firstImage;
    
    /**
     * 视频长度
     */
    @ApiModelProperty(name = "duration", value = "视频长度", required = false)
    private String duration;
    
    /**
     * 默认视频的播放清晰度，1为流畅，2为高清，3为超清
     */
    @ApiModelProperty(name = "myBr", value = "默认视频的播放清晰度，1为流畅，2为高清，3为超清", required = false)
    private String myBr;
    
    /**
     * 访客信息收集id
     */
    @ApiModelProperty(name = "qid", value = "访客信息收集id", required = false)
    private String qid;
    
    /**
     * 视频加密状态，1表示为加密状态，0为非加密
     */
    @ApiModelProperty(name = "seed", value = "视频加密状态，1表示为加密状态，0为非加密", required = false)
    private String seed;
    
    /**
     * 添加为回放视频的日期
     */
    @ApiModelProperty(name = "createdTime", value = "添加为回放视频的日期", required = false)
    private Long createdTime;
    
    /**
     * 视频最后修改日期
     */
    @ApiModelProperty(name = "lastModified", value = "视频最后修改日期", required = false)
    private Long lastModified;
    
    /**
     * 视频播放地址，注：如果视频为加密视频，则此地址无法访问
     */
    @ApiModelProperty(name = "url", value = "视频播放地址，注：如果视频为加密视频，则此地址无法访问", required = false)
    private String url;
    
    /**
     * 用于PPT请求数据，与PPT直播的回放相关，普通直播回放值为null
     */
    @ApiModelProperty(name = "channelSessionId", value = "用于PPT请求数据，与PPT直播的回放相关，普通直播回放值为null", required = false)
    private String channelSessionId;
    
    /**
     * 视频合并信息
     */
    @ApiModelProperty(name = "mergeInfo", value = "视频合并信息", required = false)
    private String mergeInfo;
    
    /**
     * 直播开始时间
     */
    @ApiModelProperty(name = "startTime", value = "直播开始时间", required = false)
    private String startTime;
    
    /**
     * 回放视频的场景类型
     */
    @ApiModelProperty(name = "liveType", value = "回放视频的场景类型", required = false)
    private String liveType;
    
}
