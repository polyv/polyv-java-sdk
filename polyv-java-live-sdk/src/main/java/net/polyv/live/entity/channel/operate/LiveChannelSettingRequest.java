package net.polyv.live.entity.channel.operate;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.channel.operate.LiveChannelInitRequest;

/**
 * 修改频道的相关设置请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("频道相关设置请求基本信息")
public class LiveChannelSettingRequest extends LiveCommonRequest {
    
    /**
     * 需要修改频道相关设置的频道号，例如：1938028
     */
    @ApiModelProperty(name = "channelId", value = "需要设置频道详情的频道号，例如：1938028", required = true, example = "1938028")
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 基础设置
     */
    @ApiModelProperty(name = "basicSetting", value = "基础设置", required = true)
    private BasicSetting basicSetting;
    
    /**
     * 观看条件设置
     */
    @ApiModelProperty(name = "authSettings", value = "观看条件设置", required = false)
    private List<AuthSetting> authSettings;
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("基础设置")
    public class BasicSetting {
        /**
         * 频道名称
         */
        @ApiModelProperty(name = "name", value = "频道名称", required = false)
        private String name;
        
        /**
         * 频道密码,长度不能超过16位
         */
        @ApiModelProperty(name = "channelPasswd", value = "频道密码,长度不能超过16位", required = false)
        private String channelPasswd;
        
        /**
         * 主持人名称
         */
        @ApiModelProperty(name = "publisher", value = "主持人名称", required = false)
        private String publisher;
        
        /**
         * 直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示
         */
        @ApiModelProperty(name = "startTime", value = "直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示", required = false)
        private Long startTime;
        
        /**
         * 累积观看数
         */
        @ApiModelProperty(name = "pageView", value = "累积观看数", required = false)
        private Integer pageView;
        
        /**
         * 点赞数
         */
        @ApiModelProperty(name = "likes", value = "点赞数", required = false)
        private Integer likes;
        
        /**
         * 封面图片地址
         */
        @ApiModelProperty(name = "coverImg", value = "封面图片地址", required = false)
        private String coverImg;
        
        /**
         * 引导图地址
         */
        @ApiModelProperty(name = "splashImg", value = "引导图地址", required = false)
        private String splashImg;
        
        /**
         * 引导页开关(Y、N)
         */
        @ApiModelProperty(name = "splashEnabled", value = "引导页开关(Y、N)", required = false)
        private String splashEnabled;
        
        /**
         * 直播介绍
         */
        @ApiModelProperty(name = "desc", value = "直播介绍", required = false)
        private String desc;
        
        /**
         * 咨询提问开关(Y、N)
         */
        @ApiModelProperty(name = "consultingMenuEnabled", value = "咨询提问开关(Y、N)", required = false)
        private String consultingMenuEnabled;
        
        /**
         * 是否限制最大观看人数(Y、N)
         */
        @ApiModelProperty(name = "maxViewerRestrict", value = "是否限制最大观看人数(Y、N)", required = false)
        private String maxViewerRestrict;
        
        /**
         * 最大在线人数
         */
        @ApiModelProperty(name = "maxViewer", value = "最大在线人数", required = false)
        private Integer maxViewer;
        
        /**
         * 频道的所属分类（分类ID可通过“获取直播分类”接口得到）
         */
        @ApiModelProperty(name = "categoryId", value = "频道的所属分类（分类ID可通过“获取直播分类”接口得到）", required = false)
        private Integer categoryId;
        
        /**
         * 连麦人数，-1：使用账号的连麦人数，范围大于等于-1，小于等于账号的连麦人数，最大16人
         */
        @ApiModelProperty(name = "linkMicLimit", value = "连麦人数，-1：使用账号的连麦人数，范围大于等于-1，小于等于账号的连麦人数，最大16人", required =
                false)
        private Integer linkMicLimit;
        
        /**
         * 是否增加转播关联，Y：表示增加关联，N：表示取消关联 (注：需要开启频道转播功能该参数才生效)(Y、N)
         */
        @ApiModelProperty(name = "operation", value = "是否增加转播关联，Y：表示增加关联，N：表示取消关联 (注：需要开启频道转播功能该参数才生效)(Y、N)",
                required = false)
        private String operation;
        
        /**
         * 接收转播频道号，多个频道号用半角逗号,隔开(注：需要开启频道转播功能该参数才生效)
         */
        @ApiModelProperty(name = "receiveChannelIds", value = "接收转播频道号，多个频道号用半角逗号,隔开(注：需要开启频道转播功能该参数才生效)", required = false)
        private String receiveChannelIds;
    }
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("观看条件设置")
    public static class AuthSetting {
        /**
         * 通用参数：主要观看条件为1，次要观看条件为2
         */
        @ApiModelProperty(name = "rank", value = "通用参数：主要观看条件为1，次要观看条件为2", required = true)
        @NotNull(message = "属性rank不能为空")
        private Integer rank;
        
        /**
         * 通用参数：是否开启，Y为开启，N为关闭
         */
        @ApiModelProperty(name = "enabled", value = "通用参数：是否开启，Y为开启，N为关闭", required = true)
        @NotNull(message = "属性enabled不能为空")
        private String enabled;
        
        /**
         * 通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct
         */
        @ApiModelProperty(name = "authType", value = "通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom" +
                "，外部授权-external,直接授权-direct", required = false)
        private String authType;
        
        /**
         * 付费观看参数：欢迎语标题
         */
        @ApiModelProperty(name = "payAuthTips", value = "付费观看参数：欢迎语标题", required = false)
        private String payAuthTips;
        
        /**
         * 付费观看参数：价格，单位为元
         */
        @ApiModelProperty(name = "price", value = "付费观看参数：价格，单位为元", required = false)
        private Float price;
        
        /**
         * 付费观看参数：付费有效截止日期，格式为yyyy-MM-dd HH:mm。当watchEndTime和validTimePeriod都为空时，表示付费永久有效
         */
        @ApiModelProperty(name = "watchEndTime", value = "付费观看参数：付费有效截止日期，格式为yyyy-MM-dd " +
                "HH:mm。当watchEndTime和validTimePeriod都为空时，表示付费永久有效", required = false)
        private String watchEndTime;
        
        /**
         * 付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效
         */
        @ApiModelProperty(name = "validTimePeriod", value = "付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod" +
                "都为空时，表示付费永久有效", required = false)
        private Integer validTimePeriod;
        
        /**
         * 验证码观看参数：验证码
         */
        @ApiModelProperty(name = "authCode", value = "验证码观看参数：验证码", required = false)
        private String authCode;
        
        /**
         * 验证码观看参数：提示文案
         */
        @ApiModelProperty(name = "qcodeTips", value = "验证码观看参数：提示文案", required = false)
        private String qcodeTips;
        
        /**
         * 验证码观看参数：公众号二维码地址
         */
        @ApiModelProperty(name = "qcodeImg", value = "验证码观看参数：公众号二维码地址", required = false)
        private String qcodeImg;
        
        /**
         * 白名单观看参数：提示文案
         */
        @ApiModelProperty(name = "authTips", value = "白名单观看参数：提示文案", required = false)
        private String authTips;
        
        /**
         * 登记观看参数,上限为5个
         */
        @ApiModelProperty(name = "infoFields", value = "登记观看参数,上限为5个", required = false)
        private List<LiveChannelInitRequest.InfoField> infoFields;
        
        /**
         * 外部授权参数：SecretKey
         */
        @ApiModelProperty(name = "externalKey", value = "外部授权参数：SecretKey", required = false)
        private String externalKey;
        
        /**
         * 外部授权参数：自定义url
         */
        @ApiModelProperty(name = "externalUri", value = "外部授权参数：自定义url", required = false)
        private String externalUri;
        
        /**
         * 外部授权参数：跳转地址
         */
        @ApiModelProperty(name = "externalRedirectUri", value = "外部授权参数：跳转地址", required = false)
        private String externalRedirectUri;
        
        /**
         * 自定义授权参数：SecretKey
         */
        @ApiModelProperty(name = "customKey", value = "自定义授权参数：SecretKey", required = false)
        private String customKey;
        
        /**
         * 自定义授权参数：自定义url
         */
        @ApiModelProperty(name = "customUri", value = "自定义授权参数：自定义url", required = false)
        private String customUri;
        
        /**
         * 直接授权参数：直接授权SecretKey
         */
        @ApiModelProperty(name = "directKey", value = "直接授权参数：直接授权SecretKey", required = false)
        private String directKey;
    }
    
}
