package net.polyv.live.entity.channel.operate;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 创建并初始化频道请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("创建并初始化频道请求体")
public class LiveChannelInitRequest extends LiveCommonRequest {
    
    @ApiModelProperty(name = "basicSetting", value = "基础设置", required = true)
    private BasicSetting basicSetting;
    
    @ApiModelProperty(name = "authSettings", value = "观看条件设置", required = false)
    private List<AuthSetting> authSettings;
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("基础设置")
    public static class BasicSetting {
        /**
         * 频道名称
         */
        @ApiModelProperty(name = "name", value = "频道名称", required = true, example = "Spring从入门到放弃")
        @NotNull(message = "属性name不能为空")
        private String name;
        
        /**
         * 频道密码,长度不能超过16位
         */
        @ApiModelProperty(name = "channelPasswd", value = "频道密码,长度不能超过16位", required = true, example = "123321")
        @NotNull(message = "属性channelPasswd不能为空")
        private String channelPasswd;
        
        /**
         * 是否自动播放，0/1，默认1
         */
        @ApiModelProperty(name = "autoPlay", value = "是否自动播放，0/1，默认1", required = false)
        private Integer autoPlay;
        
        /**
         * 播放器控制栏颜色，默认：#666666
         */
        @ApiModelProperty(name = "playerColor", value = "播放器控制栏颜色，默认：#666666", required = false)
        private String playerColor;
        
        /**
         * 直播场景：alone 活动拍摄；ppt 三分屏；topclass 大班课
         */
        @ApiModelProperty(name = "scene", value = "直播场景：alone 活动拍摄；ppt 三分屏；topclass 大班课", required = false)
        private String scene;
        
        /**
         * 新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到）
         */
        @ApiModelProperty(name = "categoryId", value = "新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到）", required = false)
        private Integer categoryId;
        
        /**
         * 最大同时在线人数
         */
        @ApiModelProperty(name = "maxViewer", value = "最大同时在线人数", required = false)
        private Integer maxViewer;
        
        /**
         * 直播开始时间，13位时间戳
         */
        @ApiModelProperty(name = "startTime", value = "直播开始时间，13位时间戳", required = false)
        private Long startTime;
        
        /**
         * 直播介绍的内容
         */
        @ApiModelProperty(name = "desc", value = "直播介绍的内容", required = false)
        private String desc;
        
        /**
         * 主持人
         */
        @ApiModelProperty(name = "publisher", value = "主持人", required = false)
        private String publisher;
        
        /**
         * 连麦人数，-1：使用账号的连麦人数，范围大于等于-1，小于等于账号的连麦人数，最大16人
         */
        @ApiModelProperty(name = "linkMicLimit", value = "连麦人数，-1：使用账号的连麦人数，范围大于等于-1，小于等于账号的连麦人数，最大16人", required =
                false)
        private Integer linkMicLimit;
        
        /**
         * 是否为无延时直播，Y 表示开启，默认为N
         */
        @ApiModelProperty(name = "pureRtcEnabled", value = "是否为无延时直播，Y 表示开启，默认为N", required = false)
        private String pureRtcEnabled;
        
        /**
         * 是否为接收转播频道，Y 表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效)
         */
        @ApiModelProperty(name = "receive", value = "是否为接收转播频道，Y 表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效)", required =
                false)
        private String receive;
        
        /**
         * 接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效)
         */
        @ApiModelProperty(name = "receiveChannelIds", value = "接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效" +
                "(注：需要开启频道转播功能该参数才生效)", required = false)
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
         * 付费观看参数：付费有效截止日期，格式为13位时间戳。watchEndTime和validTimePeriod只能设置一个，当watchEndTime和validTimePeriod都为空时，表示付费永久有效
         */
        @ApiModelProperty(name = "watchEndTime", value = "付费观看参数：付费有效截止日期，格式为13位时间戳。watchEndTime和validTimePeriod" +
                "只能设置一个，当watchEndTime和validTimePeriod都为空时，表示付费永久有效", required = false)
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
         * 登记观看参数
         */
        @ApiModelProperty(name = "infoFields", value = "登记观看参数", required = false)
        private List<InfoField> infoFields;
        
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
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("登记观看参数")
    public class InfoField {
        /**
         * 登记信息名，最多为8字符
         */
        @ApiModelProperty(name = "name", value = "登记信息名，最多为8字符", required = false)
        private String name;
        
        /**
         * 登记类型，姓名-name，文本-text，手机号码-mobile，数字-number，下拉选项-option
         */
        @ApiModelProperty(name = "type", value = "登记类型，姓名-name，文本-text，手机号码-mobile，数字-number，下拉选项-option", required =
                true)
        @NotNull(message = "属性type不能为空")
        private String type;
        
        /**
         * 下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符
         */
        @ApiModelProperty(name = "options", value = "下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符", required = false)
        private String options;
        
        /**
         * 文本框输入提示，最多为8字符
         */
        @ApiModelProperty(name = "placeholder", value = "文本框输入提示，最多为8字符", required = false)
        private String placeholder;
        
        /**
         * 短信验证开关，Y 开启，N 关闭
         */
        @ApiModelProperty(name = "sms", value = "短信验证开关，Y 开启，N 关闭", required = false)
        private String sms;
    }
}
