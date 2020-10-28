package net.polyv.live.entity.channel.playback;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;

/**
 * 查询视频库列表返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询视频库列表返回实体")
public class LiveListChannelVideoLibraryResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "视频库视频信息", required = false)
    private List<ChannelVideoLibrary> contents;
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("视频库视频信息")
    public class ChannelVideoLibrary {
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
         * 回放视频对应的直播频道号
         */
        @ApiModelProperty(name = "channelId", value = "回放视频对应的直播频道号", required = false)
        private String channelId;
        
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
        private Integer myBr;
        
        /**
         * 访客信息收集id
         */
        @ApiModelProperty(name = "qid", value = "访客信息收集id", required = false)
        private String qid;
        
        /**
         * 视频加密状态，1表示为加密状态，0为非加密
         */
        @ApiModelProperty(name = "seed", value = "视频加密状态，1表示为加密状态，0为非加密", required = false)
        private Integer seed;
        
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
         * 是否为默认播放视频，值为Y/N
         */
        @ApiModelProperty(name = "asDefault", value = "是否为默认播放视频，值为Y/N", required = false)
        private String asDefault;
        
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
         * 视频合并信息，后续补充
         */
        @ApiModelProperty(name = "mergeInfo", value = "视频合并信息，后续补充", required = false)
        private String mergeInfo;
        
        /**
         * 直播开始时间
         */
        @ApiModelProperty(name = "startTime", value = "直播开始时间", required = false)
        private String startTime;
        
        /**
         * playback-回放列表，vod-点播列表;
         */
        @ApiModelProperty(name = "listType", value = "playback-回放列表，vod-点播列表;", required = false)
        private String listType;
    }
}
