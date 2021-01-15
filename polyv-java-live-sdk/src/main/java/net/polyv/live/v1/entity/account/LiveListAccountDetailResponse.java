package net.polyv.live.v1.entity.account;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonResponse;


/**
 * 查询账号下所有频道详细信息返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询账号下所有频道详细信息返回实体")
public class LiveListAccountDetailResponse extends LivePageCommonResponse{

    @ApiModelProperty(name = "contents", value = "频道详细信息列表")
    private List<LiveChannelDetail> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("直播频道详情信息")
    public static class LiveChannelDetail {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号")
        private String channelId;
        
        /**
         * 频道名称
         */
        @ApiModelProperty(name = "name", value = "频道名称")
        private String name;
        
        /**
         * 频道密码
         */
        @ApiModelProperty(name = "channelPasswd", value = "频道密码")
        private String channelPasswd;
        
        /**
         * 频道分类ID
         */
        @ApiModelProperty(name = "categoryId", value = "频道分类ID")
        private String categoryId;
        
        /**
         * 场景，alone-活动直播，ppt-三分屏，topclass-大班课
         */
        @ApiModelProperty(name = "scene", value = "场景，alone-活动直播，ppt-三分屏，topclass-大班课")
        private String scene;
        
        /**
         * 场景描述，如：大班课
         */
        @ApiModelProperty(name = "sceneText", value = "场景描述，如：大班课")
        private String sceneText;
        
        /**
         * 观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始
         */
        @ApiModelProperty(name = "watchStatus", value = "观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始")
        private String watchStatus;
        
        /**
         * 观看页状态描述，直播中，回放中，已结束，未开始
         */
        @ApiModelProperty(name = "watchStatusText", value = "观看页状态描述，直播中，回放中，已结束，未开始")
        private String watchStatusText;
        
        /**
         * 观看页链接
         */
        @ApiModelProperty(name = "watchUrl", value = "观看页链接")
        private String watchUrl;
        
        /**
         * 直播介绍
         */
        @ApiModelProperty(name = "content", value = "直播介绍")
        private String content;
        
        /**
         * 直播开始时间
         */
        @ApiModelProperty(name = "startTime", value = "直播开始时间")
        private Date startTime;
        
        /**
         * 直播权限设置数据传输对象
         */
        @ApiModelProperty(name = "authSetting", value = "直播权限设置数据传输对象")
        private List<LiveAuthSetting> authSetting;
        
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("直播权限设置数据传输对象")
    public static class LiveAuthSetting {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号")
        private String channelId;
        
        /**
         * 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件）
         */
        @ApiModelProperty(name = "rank", value = "用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件）")
        private Integer rank;
    
        /**
          *  {@code POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）}
         */
        @ApiModelProperty(name = "userId", value = "POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）")
        private String userId;
        
        /**
         * 是否开启全局设置（Y/N）
         */
        @ApiModelProperty(name = "globalSettingEnabled", value = "是否开启全局设置（Y/N）")
        private String globalSettingEnabled;
        
        /**
         * 是否开启观看条件(Y/N)
         */
        @ApiModelProperty(name = "enabled", value = "是否开启观看条件(Y/N)")
        private String enabled;
        
        /**
         * 观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8.
         * 外部授权观看 external)
         */
        @ApiModelProperty(name = "authType", value = "观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看" +
                " info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external)")
        private String authType;
        
        /**
         * 验证码观看提示信息
         */
        @ApiModelProperty(name = "codeAuthTips", value = "验证码观看提示信息")
        private String codeAuthTips;
        
        /**
         * 验证码观看的验证码
         */
        @ApiModelProperty(name = "authCode", value = "验证码观看的验证码")
        private String authCode;
        
        /**
         * 验证码观看的二维码提示
         */
        @ApiModelProperty(name = "qcodeTips", value = "验证码观看的二维码提示")
        private String qcodeTips;
        
        /**
         * 验证码观看的二维码图片
         */
        @ApiModelProperty(name = "qcodeImg", value = "验证码观看的二维码图片")
        private String qcodeImg;
        
        /**
         * 付费观看提示信息
         */
        @ApiModelProperty(name = "payAuthTips", value = "付费观看提示信息")
        private String payAuthTips;
        
        /**
         * 付费观看的价格
         */
        @ApiModelProperty(name = "price", value = "付费观看的价格")
        private Float price;
        
        /**
         * 付费观看的截止时长 （天）
         */
        @ApiModelProperty(name = "validTimePeriod", value = "付费观看的截止时长 （天）")
        private String validTimePeriod;
        
        /**
         * 付费观看，截止时间，为null表示：一次付费，永久有效
         */
        @ApiModelProperty(name = "watchEndTime", value = "付费观看，截止时间，为null表示：一次付费，永久有效")
        private Date watchEndTime;
        
        /**
         * 白名单观看提示信息
         */
        @ApiModelProperty(name = "authTips", value = "白名单观看提示信息")
        private String authTips;
        
        /**
         * 登记观看提示信息
         */
        @ApiModelProperty(name = "infoAuthTips", value = "登记观看提示信息")
        private String infoAuthTips;
        
        /**
         * 自定义授权观看的key
         */
        @ApiModelProperty(name = "customKey", value = "自定义授权观看的key")
        private String customKey;
        
        /**
         * 自定义授权观看的接口地址
         */
        @ApiModelProperty(name = "customUri", value = "自定义授权观看的接口地址")
        private String customUri;
        
        /**
         * 外部授权观看的key
         */
        @ApiModelProperty(name = "externalKey", value = "自定义授权观看的接口地址")
        private String externalKey;
        
        /**
         * 外部授权观看的接口地址
         */
        @ApiModelProperty(name = "externalUri", value = "外部授权观看的接口地址")
        private String externalUri;
        
        /**
         * 外部授权观看，用户直接访问观看页时的跳转地址
         */
        @ApiModelProperty(name = "externalRedirectUri", value = "外部授权观看，用户直接访问观看页时的跳转地址")
        private String externalRedirectUri;
        
        /**
         * 独立授权key
         */
        @ApiModelProperty(name = "directKey", value = "独立授权key")
        private String directKey;
        
        /**
         * 试看开关，Y:开启试看，N:关闭试看
         */
        @ApiModelProperty(name = "trialWatchEnabled", value = "试看开关，Y:开启试看，N:关闭试看")
        private String trialWatchEnabled;
        
        /**
         * 试看时间，单位为分钟
         */
        @ApiModelProperty(name = "trialWatchTime", value = "试看时间，单位为分钟")
        private Integer trialWatchTime;
        
        /**
         * 试看截止日期，为null 表示对该频道永久有效
         */
        @ApiModelProperty(name = "trialWatchEndTime", value = "试看截止日期，为null 表示对该频道永久有效")
        private Date trialWatchEndTime;
        
    }

}
