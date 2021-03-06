package net.polyv.live.v1.entity.channel.operate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 创建直播频道返回实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@ApiModel("创建直播频道返回实体")
public class LiveChannelResponse {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "直播频道号")
    protected String channelId;
    
    /**
      *  {@code POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）}
     */
    @ApiModelProperty(name = "userId", value = "POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）")
    protected String userId;
    
    /**
     * 直播频道名称
     */
    @ApiModelProperty(name = "name", value = "直播频道名称")
    protected String name;
    
    /**
     * 频道描述
     */
    @ApiModelProperty(name = "description", value = "直播频道描述")
    protected String description;
    
    /**
     * 直播推流地址
     */
    @ApiModelProperty(name = "url", value = "直播推流地址")
    protected String url;
    
    /**
     * 直播流名称
     */
    @ApiModelProperty(name = "stream", value = "直播流名称")
    protected String stream;
    
    /**
     * LOGO图片
     */
    @ApiModelProperty(name = "logoImage", value = "播放器logo")
    protected String logoImage;
    
    /**
     * LOGO透明度
     */
    @ApiModelProperty(name = "logoOpacity", value = "Logo不透明度，1表示完全不透明")
    protected Float logoOpacity;
    
    /**
     * LOGO位置 tr1：左上，tr：右上，b1：左下，br：右下
     */
    @ApiModelProperty(name = "logoPosition", value = "Logo位置<br/>tr1：左上<br/>tr：右上<br/>b1：左下<br/>br：右下")
    protected String logoPosition;
    
    /**
     * Logo的跳转链接
     */
    @ApiModelProperty(name = "logoHref", value = "Logo的跳转链接")
    protected String logoHref;
    
    /**
     * 播放前显示的封面图
     */
    @ApiModelProperty(name = "coverImage", value = "播放前显示的封面图")
    protected String coverImage;
    
    /**
     * 封面图的跳转链接
     */
    @ApiModelProperty(name = "coverHref", value = "封面图的跳转链接")
    protected String coverHref;
    
    /**
     * 等待推流时的显示图片
     */
    @ApiModelProperty(name = "waitImage", value = "等待推流时的显示图片")
    protected String waitImage;
    
    /**
     * 等待推流时显示图片的跳转链接
     */
    @ApiModelProperty(name = "waitHref", value = "等待推流时显示图片的跳转链接")
    protected String waitHref;
    
    /**
     * 切断流时的显示图片
     */
    @ApiModelProperty(name = "cutoffImage", value = "切断流时的显示图片")
    protected String cutoffImage;
    
    /**
     * 切断流时显示图片的跳转链接
     */
    @ApiModelProperty(name = "cutoffHref", value = "切断流时显示图片的跳转链接")
    protected String cutoffHref;
    
    /**
     * 广告类型
     */
    @ApiModelProperty(name = "advertType", value = "广告类型")
    protected String advertType;
    
    /**
     * 广告时长，单位：秒
     */
    @ApiModelProperty(name = "advertDuration", value = "广告时长，单位：秒")
    protected Integer advertDuration;
    
    /**
     * 广告区域宽度
     */
    @ApiModelProperty(name = "advertWidth", value = "广告区域宽度")
    protected Integer advertWidth;
    
    /**
     * 广告区域高度
     */
    @ApiModelProperty(name = "advertHeight", value = "广告区域高度")
    protected Integer advertHeight;
    
    /**
     * 图片广告
     */
    @ApiModelProperty(name = "advertImage", value = "图片广告")
    protected String advertImage;
    
    /**
     * 广告的跳转链接
     */
    @ApiModelProperty(name = "advertHref", value = "广告的跳转链接")
    protected String advertHref;
    
    /**
     * 视频广告ID
     */
    @ApiModelProperty(name = "advertFlvVid", value = "视频广告ID")
    protected String advertFlvVid;
    
    /**
     * 视频广告链接
     */
    @ApiModelProperty(name = "advertFlvUrl", value = "视频广告链接")
    protected String advertFlvUrl;
    
    /**
     * 播放器控制栏颜色
     */
    @ApiModelProperty(name = "playerColor", value = "播放器控制栏颜色" )
    protected String playerColor;
    
    /**
     * 自动播放
     */
    @ApiModelProperty(name = "autoPlay", value = "自动播放，true为自动播放，false为关闭" )
    protected Boolean autoPlay;
    
    /**
     * 一开始的暖场视频
     */
    @ApiModelProperty(name = "warmUpFlv", value = "一开始的暖场视频" )
    protected String warmUpFlv;
    
    /**
     * 观看密码限制，需要输入观看密码才能播放流
     */
    @ApiModelProperty(name = "passwdRestrict", value = "观看密码限制，需要输入观看密码才能播放流"  )
    protected Boolean passwdRestrict;
    
    /**
     * 观看密码加密后的密文
     */
    @ApiModelProperty(name = "", value = "观看密码加密后的密文")
    protected String passwdEncrypted;
    
    /**
     * 仅推音频流，Y-是，N-否
     */
    @ApiModelProperty(name = "isOnlyAudio", value = "仅推音频流，Y-是，N-否")
    protected String isOnlyAudio;
    
    /**
     * 低延迟，Y-是，N-否
     */
    @ApiModelProperty(name = "isLowLatency", value = "低延迟，Y-是，N-否")
    protected String isLowLatency;
    
    /**
     * 直播拉流（播放）m3u8地址
     */
    @ApiModelProperty(name = "m3u8Url", value = "直播拉流（播放）m3u8地址")
    protected String m3u8Url;
    
    /**
     * 直播拉流（播放）m3u8地址1
     */
    @ApiModelProperty(name = "m3u8Url1", value = "直播拉流（播放）m3u8地址1")
    protected String m3u8Url1;
    
    /**
     * 直播拉流（播放）m3u8地址2
     */
    @ApiModelProperty(name = "m3u8Url2", value = "直播拉流（播放）m3u8地址2")
    protected String m3u8Url2;
    
    /**
     * 直播拉流（播放）m3u8地址3
     */
    @ApiModelProperty(name = "m3u8Url3", value = "直播拉流（播放）m3u8地址3")
    protected String m3u8Url3;
    
    /**
     * 服务器返回的时间戳（毫秒）
     */
    @ApiModelProperty(name = "currentTimeMillis", value = "服务器返回的时间戳（毫秒）")
    protected Long currentTimeMillis;
    
    /**
     * 连麦人数
     */
    @ApiModelProperty(name = "linkMicLimit", value = "连麦人数")
    private Integer linkMicLimit;
}
