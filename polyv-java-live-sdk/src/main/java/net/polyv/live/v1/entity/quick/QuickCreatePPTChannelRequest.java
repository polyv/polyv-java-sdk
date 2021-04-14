package net.polyv.live.v1.entity.quick;

import java.io.File;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.Length;
import net.polyv.common.v1.validator.constraints.Max;
import net.polyv.common.v1.validator.constraints.Min;
import net.polyv.common.v1.validator.constraints.NotBlank;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 快速创建直播三分屏频道请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("快速创建直播三分屏频道请求实体")
public class QuickCreatePPTChannelRequest extends LiveCommonRequest {
    
    /**
     * 自定义频道名称，一般是课程主题、会议主题、培训主题等，例如 财务制度培训、乌镇峰会
     */
    @ApiModelProperty(name = "name", value = "自定义频道名称，一般是课程主题、会议主题、培训主题等，例如 财务制度培训、乌镇峰会", required = true, example =
            "直播测试")
    @NotNull(message = "属性name不能为空")
    private String name;
    
    /**
     * 自定义频道密码，B端讲师通过该密码进入直播间开播，长度不能超过16位,必须同时包含字母和数字
     */
    @ApiModelProperty(name = "channelPasswd", value = "自定义频道密码，B端讲师通过该密码进入直播间开播，长度不能超过16位,必须同时包含字母和数字", required = true, example =
            "666888")
    @NotBlank(message = "属性channelPasswd不能为空")
    @Length(max = 16, message = "频道密码不能超过16位")
    private String channelPasswd;
    
    /**
     * 连麦人数 ，-1=<取值范围<=账号级的连麦人数，-1：表示使用账号默认的连麦人数，最大16人（注：账号级连麦人数需通知平台管理员设置才生效）
     */
    @ApiModelProperty(name = "linkMicLimit", value = "连麦人数，-1=<取值范围<=账号级的连麦人数，-1：表示使用账号默认的连麦人数，最大16" +
            "人（注：账号级连麦人数需通知平台管理员设置才生效）")
    @Max(value = 16, message = "属性linkMicLimit连麦人数不能大于16人")
    @Min(value = -1, message = "属性linkMicLimit连麦人数不能小于-1人")
    private Integer linkMicLimit;
    
    /**
     * 主持人名称
     */
    @ApiModelProperty(name = "publisher", value = "主持人名称", required = false)
    private String publisher;
    
    /**
     * 是否为无延时直播，Y 表示开启，默认为N
     */
    @ApiModelProperty(name = "pureRtcEnabled", value = "是否为无延时直播，Y 表示开启，默认为N")
    private String pureRtcEnabled;
    
    /**
     * 频道图标地址
     */
    @ApiModelProperty(name = "coverImg", value = "频道图标地址", required = false)
    private String coverImg;
    
    /**
     * 引导图地址
     */
    @ApiModelProperty(name = "splashImg", value = "引导图地址", required = false)
    private String splashImg;
    
    
    /**
     * 直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示
     */
    @ApiModelProperty(name = "startTime", value = "直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示", required = false)
    private Long startTime;
    
    /**
     * 直播介绍
     */
    @ApiModelProperty(name = "desc", value = "直播介绍", required = false)
    private String desc;
    
    
    /**
     * 讲师昵称
     */
    @ApiModelProperty(name = "nickname", value = "讲师昵称", required = false)
    private String nickname;
    
    /**
     * 讲师头衔，讲师昵称不为空时该参数必填
     */
    @ApiModelProperty(name = "actor", value = "讲师头衔，讲师昵称不为空时该参数必填", required = false)
    private String actor;
    
    /**
     * 头像图片地址
     */
    @ApiModelProperty(name = "avatar", value = "头像图片地址", required = false)
    private String avatar;
    
    /**
     * 暖场图片地址，图片大小建议：800x450，支持PNG、JPEG、GIF格式
     */
    @ApiModelProperty(name = "coverImage", value = "暖场图片地址，图片大小建议：800x450，支持PNG、JPEG、GIF格式", required = false)
    private String coverImage;
    
    /**
     * 暖场图片跳转地址
     */
    @ApiModelProperty(name = "coverHref", value = "点击暖场图片后浏览器跳转地址", required = false)
    private String coverHref;
    
    /**
     * 暖场视频地址(http地址)，移动端不支持FLV视频文件，建议使用MP4视频文件
     */
    @ApiModelProperty(name = "warmUpFlv", value = "暖场视频地址(http地址)，移动端不支持FLV视频文件，建议使用MP4视频文件", required = false)
    private String warmUpFlv;
    
    /**
     * 上传的文件不超过50M，格式限制为（ppt， pdf，pptx，doc，docx，wps, xls，xlsx）
     */
    @ApiModelProperty(name = "file", value = "上传的文件不超过50M，格式限制为（ppt， pdf，pptx，doc，docx，wps, xls，xlsx）", required = false)
    private File file;
    
    /**
     * 转换类型（‘common’：转普通图片， ‘animate’：转动画效果）默认不传转普通，因为只有ppt，pptx可以转动画，其他类型文件会自动转成普通；文件转动画转失败会直接把类型转为普通
     */
    @ApiModelProperty(name = "type", value = "转换类型（‘common’：转普通图片， ‘animate’：转动画效果）默认不传转普通，因为只有ppt，pptx可以转动画，其他类型文件会自动转成普通；文件转动画转失败会直接把类型转为普通", required = false)
    private String type;
    
    /**
     * 文档名称（不传默认使用ppt上传的文件获取到的文件名作为文档名称，文档名称不得超过100个字符）
     */
    @ApiModelProperty(name = "docName", value = "文档名称（不传默认使用ppt上传的文件获取到的文件名作为文档名称，文档名称不得超过100个字符）", required = false)
    private String docName;
    
    /**
     * 文档上传转换成功回调地址
     */
    @ApiModelProperty(name = "callbackUrl", value = "文档上传转换成功回调地址", required = false)
    private String callbackUrl;
    
}
