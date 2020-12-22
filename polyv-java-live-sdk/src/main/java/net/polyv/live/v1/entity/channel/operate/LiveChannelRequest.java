package net.polyv.live.v1.entity.channel.operate;

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
 * 创建直播频道请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("创建直播频道请求实体")
public class LiveChannelRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
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
     * 是否自动播放标识，0：关闭自动播放；1：开启，默认取值 1
     */
    @ApiModelProperty(name = "autoPlay", value = "是否自动播放标识，0：关闭自动播放；1：开启，默认取值 1")
    private Integer autoPlay;
    
    /**
     * 播放器控制栏颜色，默认：#666666
     */
    @ApiModelProperty(name = "playerColor", value = "播放器控制栏颜色，默认：[#666666](https://www.colorgg.com/666666)")
    private String playerColor;
    
    /**
     * 直播场景：alone 活动拍摄; ppt 三分屏; topclass 大班课 ， 默认：alone
     */
    @ApiModelProperty(name = "scene", value = "直播场景：alone 活动拍摄; ppt 三分屏; topclass 大班课 ， 默认：alone")
    private String scene;
    
    /**
     * 分类ID ,新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到）
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID ,新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到）")
    private Integer categoryId;
    
    /**
     * 频道的最大在线观看的人数
     */
    @ApiModelProperty(name = "maxViewer", value = "频道的最大在线人数观看限制的人数")
    private Integer maxViewer;
    
    /**
     * 三分屏频道的观看布局，ppt：文档为主；video：视频为主；不设置会使用账号的通用设置
     */
    @ApiModelProperty(name = "watchLayout", value = "三分屏频道的观看布局，ppt：文档为主；video：视频为主；不设置会使用账号的通用设置")
    private String watchLayout;
    
    /**
     * 连麦人数 ，-1=<取值范围<=账号级的连麦人数，-1：表示使用账号默认的连麦人数，最大16人（注：账号级连麦人数需通知平台管理员设置才生效）
     */
    @ApiModelProperty(name = "linkMicLimit", value = "连麦人数，-1=<取值范围<=账号级的连麦人数，-1：表示使用账号默认的连麦人数，最大16" +
            "人（注：账号级连麦人数需通知平台管理员设置才生效）")
    @NotNull(message = "属性linkMicLimit不能为空")
    @Max(value = 16, message = "属性linkMicLimit连麦人数不能大于16人")
    @Min(value = -1, message = "属性linkMicLimit连麦人数不能小于-1人")
    private Integer linkMicLimit;
    
    /**
     * 是否为无延时直播，Y 表示开启，默认为N
     */
    @ApiModelProperty(name = "pureRtcEnabled", value = "是否为无延时直播，Y 表示开启，默认为N")
    private String pureRtcEnabled;
    
    /**
     * 是否为接收转播频道，Y表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效)
     */
    @ApiModelProperty(name = "receive", value = "是否为接收转播频道，Y表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效)")
    private String receive;
    
    /**
     * 接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效)
     */
    @ApiModelProperty(name = "receiveChannelIds", value = "接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效" +
            "(注：需要开启频道转播功能该参数才生效)")
    private String receiveChannelIds;
    
}
