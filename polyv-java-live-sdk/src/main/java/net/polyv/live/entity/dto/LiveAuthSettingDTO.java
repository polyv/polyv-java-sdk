package net.polyv.live.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 直播权限设置数据传输对象
 * @author: sadboy
 * @date: 2020/9/29
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("直播权限设置数据传输对象")
public class LiveAuthSettingDTO {

    /**
     * 频道名称
     */
    @ApiModelProperty(name = "name", value = "频道名称")
    private String channelId;

    /**
     * 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件）
     */
    @ApiModelProperty(name = "rank", value = "用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件）")
    private Integer rank;

    /**
     * 用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
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
     * 观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external)
     */
    @ApiModelProperty(name = "authType", value = "观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external)")
    private String authType;

    /**
     * 白名单观看提示信息
     */
    @ApiModelProperty(name = "authTips", value = "白名单观看提示信息")
    private String authTips;

    /**
     * 付费观看提示信息
     */
    @ApiModelProperty(name = "payAuthTips", value = "付费观看提示信息")
    private String payAuthTips;

    /**
     * 验证码观看提示信息
     */
    @ApiModelProperty(name = "codeAuthTips", value = "验证码观看提示信息")
    private String codeAuthTips;

    /**
     * 登记观看提示信息
     */
    @ApiModelProperty(name = "infoAuthTips", value = "登记观看提示信息")
    private String infoAuthTips;

    /**
     * 验证码观看的验证码
     */
    @ApiModelProperty(name = "authCode", value = "验证码观看的验证码c")
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
     * 付费观看的价格
     */
    @ApiModelProperty(name = "price", value = "付费观看的价格")
    private Float price;

    /**
     * 付费观看，截止时间，为null表示：一次付费，永久有效
     */
    @ApiModelProperty(name = "watchEndTime", value = "付费观看，截止时间，为null表示：一次付费，永久有效")
    private String watchEndTime;

    /**
     * 付费观看的截止时长 （天）
     */
    @ApiModelProperty(name = "validTimePeriod", value = "付费观看的截止时长 （天）")
    private String validTimePeriod;

    /**
     * 自定义授权观看的key
     */
    @ApiModelProperty(name = "customKey", value = "自定义授权观看的key")
    private String customKey;

    /**
     * 	自定义授权观看的接口地址
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
    @ApiModelProperty(name = "trialWatchEndTime", value = "试看截止日期，为null 表示对该频道永久有效\n")
    private String trialWatchEndTime;

}
