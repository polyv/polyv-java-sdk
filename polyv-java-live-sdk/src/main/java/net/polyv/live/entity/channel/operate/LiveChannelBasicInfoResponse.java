package net.polyv.live.entity.channel.operate;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道基本信息返回体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道基本信息返回体")
public class LiveChannelBasicInfoResponse {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private String channelId;
    
    /**
     * 频道名称
     */
    @ApiModelProperty(name = "name", value = "频道名称", required = false)
    private String name;
    
    /**
     * 频道密码
     */
    @ApiModelProperty(name = "channelPasswd", value = "频道密码", required = false)
    private String channelPasswd;
    
    /**
     * 主持人名称
     */
    @ApiModelProperty(name = "publisher", value = "主持人名称", required = false)
    private String publisher;
    
    /**
     * 直播开始时间，关闭时为0，开启时为13位毫秒级时间戳
     */
    @ApiModelProperty(name = "startTime", value = "直播开始时间，关闭时为0，开启时为13位毫秒级时间戳", required = false)
    private Long startTime;
    
    /**
     * 页面累计观看数
     */
    @ApiModelProperty(name = "pageView", value = "页面累计观看数", required = false)
    private Integer pageView;
    
    /**
     * 观看页点赞数
     */
    @ApiModelProperty(name = "likes", value = "观看页点赞数", required = false)
    private Integer likes;
    
    /**
     * 频道图标url
     */
    @ApiModelProperty(name = "coverImg", value = "频道图标url", required = false)
    private String coverImg;
    
    /**
     * 频道引导图url
     */
    @ApiModelProperty(name = "splashImg", value = "频道引导图url", required = false)
    private String splashImg;
    
    /**
     * 引导页开关（取值为Y/N）
     */
    @ApiModelProperty(name = "splashEnabled", value = "引导页开关（取值为Y/N）", required = false)
    private String splashEnabled;
    
    /**
     * 直播介绍
     */
    @ApiModelProperty(name = "desc", value = "直播介绍", required = false)
    private String desc;
    
    /**
     * 咨询提问开关（取值为Y/N）
     */
    @ApiModelProperty(name = "consultingMenuEnabled", value = "咨询提问开关（取值为Y/N）", required = false)
    private String consultingMenuEnabled;
    
    /**
     * 限制最大在线观看人数开关（取值为Y/N）
     */
    @ApiModelProperty(name = "maxViewerRestrict", value = "限制最大在线观看人数开关（取值为Y/N）", required = false)
    private String maxViewerRestrict;
    
    /**
     * 最大在线观看人数
     */
    @ApiModelProperty(name = "maxViewer", value = "最大在线观看人数", required = false)
    private Integer maxViewer;
    
    /**
     * 频道的观看页状态，取值为： 频道状态,取值：live（直播中）、end（直播结束）、playback（回放中）、waiting（等待直播）
     */
    @ApiModelProperty(name = "watchStatus", value = "频道的观看页状态，取值为： 频道状态,取值：live（直播中）、end（直播结束）、playback（回放中）、waiting" +
            "（等待直播）", required = false)
    private String watchStatus;
    
    /**
     * 观看页状态描述，直播中，回放中，已结束，未开始
     */
    @ApiModelProperty(name = "watchStatusText", value = "观看页状态描述，直播中，回放中，已结束，未开始", required = false)
    private String watchStatusText;
    
    @ApiModelProperty(name = "userCategory", value = "频道所属分类的信息", required = false)
    private UserCategory userCategory;
    
    @ApiModelProperty(name = "authSettings", value = "直播观看条件列表", required = false)
    private List<AuthSetting> authSettings;
    
    @Data
@EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("频道所属分类的信息")
    public class UserCategory {
        
        /**
         * 分类ID
         */
        @ApiModelProperty(name = "categoryId", value = "分类ID", required = false)
        private Integer categoryId;
        
        /**
         * 分类名称
         */
        @ApiModelProperty(name = "categoryName", value = "分类名称", required = false)
        private String categoryName;
        
        /**
         * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
         */
        @ApiModelProperty(name = "userId", value = "POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）", required = false)
        private String userId;
        
        /**
         * 分类的排序值
         */
        @ApiModelProperty(name = "rank", value = "分类的排序值", required = false)
        private Integer rank;
        
    }
    
    @Data
@EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("直播观看条件列表")
    public class AuthSetting {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 用户ID
         */
        @ApiModelProperty(name = "userId", value = "用户ID", required = false)
        private String userId;
        
        /**
         * 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件）
         */
        @ApiModelProperty(name = "rank", value = "用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件）", required = false)
        private Integer rank;
        
        /**
         * 是否开启全局设置（Y/N）
         */
        @ApiModelProperty(name = "globalSettingEnabled", value = "是否开启全局设置（Y/N）", required = false)
        private String globalSettingEnabled;
        
        /**
         * 是否开启观看条件(Y/N)
         */
        @ApiModelProperty(name = "enabled", value = "是否开启观看条件(Y/N)", required = false)
        private String enabled;
        
        /**
         * 观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom
         * 8. 外部授权观看 external)
         */
        @ApiModelProperty(name = "authType", value = "观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. " +
                "登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external)", required = false)
        private String authType;
        
        /**
         * 白名单观看提示信息
         */
        @ApiModelProperty(name = "authTips", value = "白名单观看提示信息", required = false)
        private String authTips;
        
        /**
         * 付费观看提示信息
         */
        @ApiModelProperty(name = "payAuthTips", value = "付费观看提示信息", required = false)
        private String payAuthTips;
        
        /**
         * 验证码观看的验证码
         */
        @ApiModelProperty(name = "authCode", value = "验证码观看的验证码", required = false)
        private String authCode;
        
        /**
         * 验证码观看的二维码提示
         */
        @ApiModelProperty(name = "qcodeTips", value = "验证码观看的二维码提示", required = false)
        private String qcodeTips;
        
        /**
         * 验证码观看的二维码图片
         */
        @ApiModelProperty(name = "qcodeImg", value = "验证码观看的二维码图片", required = false)
        private String qcodeImg;
        
        /**
         * 付费观看的价格
         */
        @ApiModelProperty(name = "price", value = "付费观看的价格", required = false)
        private Integer price;
        
        /**
         * 付费观看，截止时间，为null表示：一次付费，永久有效
         */
        @ApiModelProperty(name = "watchEndTime", value = "付费观看，截止时间，为null表示：一次付费，永久有效", required = false)
        private String watchEndTime;
        
        /**
         * 付费观看的截止时长 （天）
         */
        @ApiModelProperty(name = "validTimePeriod", value = "付费观看的截止时长 （天）", required = false)
        private Integer validTimePeriod;
        
        /**
         * 自定义授权观看的key
         */
        @ApiModelProperty(name = "customKey", value = "自定义授权观看的key", required = false)
        private String customKey;
        
        /**
         * 自定义授权观看的接口地址
         */
        @ApiModelProperty(name = "customUri", value = "自定义授权观看的接口地址", required = false)
        private String customUri;
        
        /**
         * 外部授权观看的key
         */
        @ApiModelProperty(name = "externalKey", value = "外部授权观看的key", required = false)
        private String externalKey;
        
        /**
         * 外部授权观看的接口地址
         */
        @ApiModelProperty(name = "externalUri", value = "外部授权观看的接口地址", required = false)
        private String externalUri;
        
        /**
         * 外部授权观看，用户直接访问观看页时的跳转地址
         */
        @ApiModelProperty(name = "externalRedirectUri", value = "外部授权观看，用户直接访问观看页时的跳转地址", required = false)
        private String externalRedirectUri;
        
    }
    
}
