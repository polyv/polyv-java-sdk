package net.polyv.live.entity.channel.operate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 创建并初始化频道返回体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("创建并初始化频道返回体")
public class LiveChannelInitResponse {
    /**
     * 直播频道号
     */
    @ApiModelProperty(name = "channelId", value = "直播频道号", required = false)
    private Integer channelId;
    
    /**
     * 直播用户ID
     */
    @ApiModelProperty(name = "userId", value = "直播用户ID", required = false)
    private String userId;
    
    /**
     * 直播频道名称
     */
    @ApiModelProperty(name = "name", value = "直播频道名称", required = false)
    private String name;
    
    /**
     * 直播频道描述
     */
    @ApiModelProperty(name = "description", value = "直播频道描述", required = false)
    private String description;
    
    /**
     * 直播推流地址
     */
    @ApiModelProperty(name = "url", value = "直播推流地址", required = false)
    private String url;
    
    /**
     * 直播流名称
     */
    @ApiModelProperty(name = "stream", value = "直播流名称", required = false)
    private String stream;
    
    /**
     * 播放器logo
     */
    @ApiModelProperty(name = "logoImage", value = "播放器logo", required = false)
    private String logoImage;
    
    /**
     * Logo不透明度，1表示完全不透明
     */
    @ApiModelProperty(name = "logoOpacity", value = "Logo不透明度，1表示完全不透明", required = false)
    private Float logoOpacity;
    
    /**
     * Logo位置
     */
    @ApiModelProperty(name = "logoPosition", value = "Logo位置", required = false)
    private String logoPosition;
    
    /**
     * Logo的跳转链接
     */
    @ApiModelProperty(name = "logoHref", value = "Logo的跳转链接", required = false)
    private String logoHref;
    
    /**
     * 播放前显示的封面图
     */
    @ApiModelProperty(name = "coverImage", value = "播放前显示的封面图", required = false)
    private String coverImage;
    
    /**
     * 封面图的跳转链接
     */
    @ApiModelProperty(name = "coverHref", value = "封面图的跳转链接", required = false)
    private String coverHref;
    
    /**
     * 等待推流时的显示图片
     */
    @ApiModelProperty(name = "waitImage", value = "等待推流时的显示图片", required = false)
    private String waitImage;
    
    /**
     * 等待推流时显示图片的跳转链接
     */
    @ApiModelProperty(name = "waitHref", value = "等待推流时显示图片的跳转链接", required = false)
    private String waitHref;
    
    /**
     * 切断流时的显示图片
     */
    @ApiModelProperty(name = "cutoffImage", value = "切断流时的显示图片", required = false)
    private String cutoffImage;
    
    /**
     * 切断流时显示图片的跳转链接
     */
    @ApiModelProperty(name = "cutoffHref", value = "切断流时显示图片的跳转链接", required = false)
    private String cutoffHref;
    
    /**
     * 广告类型
     */
    @ApiModelProperty(name = "advertType", value = "广告类型", required = false)
    private String advertType;
    
    /**
     * 广告时长
     */
    @ApiModelProperty(name = "advertDuration", value = "广告时长", required = false)
    private String advertDuration;
    
    /**
     * 广告区域宽度
     */
    @ApiModelProperty(name = "advertWidth", value = "广告区域宽度", required = false)
    private String advertWidth;
    
    /**
     * 广告区域高度
     */
    @ApiModelProperty(name = "advertHeight", value = "广告区域高度", required = false)
    private String advertHeight;
    
    /**
     * 图片广告
     */
    @ApiModelProperty(name = "advertImage", value = "图片广告", required = false)
    private String advertImage;
    
    /**
     * 广告的跳转链接
     */
    @ApiModelProperty(name = "advertHref", value = "广告的跳转链接", required = false)
    private String advertHref;
    
    /**
     * 视频广告ID
     */
    @ApiModelProperty(name = "advertFlvVid", value = "视频广告ID", required = false)
    private String advertFlvVid;
    
    /**
     * 视频广告链接
     */
    @ApiModelProperty(name = "advertFlvUrl", value = "视频广告链接", required = false)
    private String advertFlvUrl;
    
    /**
     * 播放器控制栏颜色
     */
    @ApiModelProperty(name = "playerColor", value = "播放器控制栏颜色", required = false)
    private String playerColor;
    
    /**
     * 自动播放
     */
    @ApiModelProperty(name = "autoPlay", value = "自动播放", required = false)
    private Boolean autoPlay;
    
    /**
     * 一开始的暖场视频
     */
    @ApiModelProperty(name = "warmUpFlv", value = "一开始的暖场视频", required = false)
    private String warmUpFlv;
    
    /**
     * 观看密码限制，需要输入观看密码才能播放流
     */
    @ApiModelProperty(name = "passwdRestrict", value = "观看密码限制，需要输入观看密码才能播放流", required = false)
    private Boolean passwdRestrict;
    
    /**
     * 观看密码加密后的密文
     */
    @ApiModelProperty(name = "passwdEncrypted", value = "观看密码加密后的密文", required = false)
    private String passwdEncrypted;
    
    /**
     * 仅推音频流
     */
    @ApiModelProperty(name = "isOnlyAudio", value = "仅推音频流", required = false)
    private String isOnlyAudio;
    
    /**
     * 低延迟
     */
    @ApiModelProperty(name = "isLowLatency", value = "低延迟", required = false)
    private String isLowLatency;
    
    /**
     * 直播拉流（播放）m3u8地址
     */
    @ApiModelProperty(name = "m3u8Url", value = "直播拉流（播放）m3u8地址", required = false)
    private String m3u8Url;
    
    /**
     * 直播拉流（播放）m3u8地址1
     */
    @ApiModelProperty(name = "m3u8Url1", value = "直播拉流（播放）m3u8地址1", required = false)
    private String m3u8Url1;
    
    /**
     * 直播拉流（播放）m3u8地址2
     */
    @ApiModelProperty(name = "m3u8Url2", value = "直播拉流（播放）m3u8地址2", required = false)
    private String m3u8Url2;
    
    /**
     * 直播拉流（播放）m3u8地址3
     */
    @ApiModelProperty(name = "m3u8Url3", value = "直播拉流（播放）m3u8地址3", required = false)
    private String m3u8Url3;
    
    /**
     * 服务器返回的时间戳（毫秒）
     */
    @ApiModelProperty(name = "currentTimeMillis", value = "服务器返回的时间戳（毫秒）", required = false)
    private Long currentTimeMillis;
    
}
