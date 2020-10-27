package net.polyv.live.entity.dto;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 直播权限设置数据传输对象
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("直播权限设置数据传输对象")
public class LiveBasicSettingDTO {

    /**
     * 频道名称
     */
    @ApiModelProperty(name = "name", value = "频道名称", example = "Spring 知识精讲")
    private String name;

    /**
     * 频道密码,长度不能超过16位
     */
    @ApiModelProperty(name = "channelPasswd", value = "频道密码,长度不能超过16位", example = "666888")
    @Length(max = 16, message = "频道密码不能超过16位")
    private String channelPasswd;

    /**
     * 主持人名称
     */
    @ApiModelProperty(name = "publisher", value = "主持人名称", example = "张教授")
    private String publisher;

    /**
     * 直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示
     */
    @ApiModelProperty(name = "startTime", value = "直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示", example = "1601288553684")
    private Long startTime;

    /**
     * 累积观看数(0-2147483647)
     */
    @ApiModelProperty(name = "pageView", value = "累积观看数(0-2147483647)", example = "1000")
    private Integer pageView;

    /**
     * 点赞数(0-2147483647)
     */
    @ApiModelProperty(name = "likes", value = "点赞数(0-2147483647)", example = "1000")
    private Integer likes;

    /**
     * 封面图片地址
     */
    @ApiModelProperty(name = "coverImg", value = "封面图片地址", example = "http://my.polyv.net/v_22/assets/dist/images/navbar/logo.png")
    private String coverImg;

    /**
     * TODO 此处得确认是否引导页开关开启此选项才有效
     * 引导图地址
     */
    @ApiModelProperty(name = "splashImg", value = "引导图地址", example = "http://my.polyv.net/v_22/assets/dist/images/navbar/logo.png")
    private String splashImg;

    /**
     * 引导页开关(Y：开启；N：关闭)
     */
    @ApiModelProperty(name = "splashEnabled", value = "引导页开关(Y：开启；N：关闭)", example = "Y")
    private String splashEnabled;

    /**
     * TODO 单词规范化
     * 直播介绍
     */
    @ApiModelProperty(name = "description", value = "直播介绍", example = "这是一个由张教授主讲的Spring 知识精讲")
    private String description;

    /**
     * 咨询提问开关(Y：开启；N：关闭)
     */
    @ApiModelProperty(name = "consultingMenuEnabled", value = "咨询提问开关(Y：开启；N：关闭)", example = "Y")
    private String consultingMenuEnabled;

    /**
     * TODO 此处得确认不限制最大观看人数时maxViewer是否就设置无效
     * 是否限制最大观看人数(Y：限制；N：不限制)
     */
    @ApiModelProperty(name = "maxViewerRestrict", value = "是否限制最大观看人数(Y：限制；N：不限制)", example = "Y")
    private String maxViewerRestrict;

    /**
     * 最大观看人数，设值范围0-2147483647，其中0为不限制同时观看人数
     */
    @ApiModelProperty(name = "maxViewer", value = "最大观看人数，设值范围0-2147483647，其中0为不限制同时观看人数", example = "0")
    private Integer maxViewer;

    /**
     * TODO 链接获取直播分类地址
     * 频道的所属分类（分类ID可通过“获取直播分类”接口得到）
     */
    @ApiModelProperty(name = "categoryId", value = "频道的所属分类（分类ID可通过“获取直播分类”接口得到）", example = "")
    private Integer categoryId;

    /**
     * 连麦人数，-1：使用账号的连麦人数，范围大于等于-1，小于等于账号的连麦人数，最大16人
     */
    @ApiModelProperty(name = "linkMicLimit", value = "连麦人数", example = "1000")
    private Integer linkMicLimit;

    /**
     * TODO 频道转播功能地址跳转
     * 增加转播关联（Y：表示增加关联，N：表示取消关联） (注：需要开启频道转播功能该参数才生效)
     */
    @ApiModelProperty(name = "operation", value = "增加转播关联（Y：表示增加关联，N：表示取消关联） (注：需要开启频道转播功能该参数才生效)", example = "")
    private String operation;

    /**
     * 接收转播频道号，多个频道号用半角逗号,隔开(注：需要开启频道转播功能该参数才生效)
     */
    @ApiModelProperty(name = "", value = "", example = "")
    private String receiveChannelIds;
}
