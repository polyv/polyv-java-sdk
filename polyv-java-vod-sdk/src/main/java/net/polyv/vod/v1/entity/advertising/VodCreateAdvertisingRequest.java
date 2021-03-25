package net.polyv.vod.v1.entity.advertising;

import java.io.File;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 创建视频广告请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("创建视频广告请求实体")
public class VodCreateAdvertisingRequest extends VodCommonRequest {
    
    public final String FILE_NAME = "file";
    
    
    /**
     * 广告开始日期,格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "startDate", value = "广告开始日期,格式为yyyy-MM-dd", required = true)
    @NotNull(message = "属性startDate不能为空")
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    
    /**
     * 广告结束日期,格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "endDate", value = "广告结束日期,格式为yyyy-MM-dd", required = true)
    @NotNull(message = "属性endDate不能为空")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    
    /**
     * 广告标题
     */
    @ApiModelProperty(name = "title", value = "广告标题", required = true)
    @NotNull(message = "属性title不能为空")
    private String title;
    
    /**
     * 广告素材，片头、片尾广告素材支持：JPEG,GIF,PNG,FLV,MP4;暂停广告支持：SWF,PNG,JPEG,GIF;弹窗广告支持PNG,JPEG,GIF.
     */
    @ApiModelProperty(name = "file", value = "广告素材，片头、片尾广告素材支持：JPEG,GIF,PNG,FLV,MP4;暂停广告支持：SWF,PNG,JPEG,GIF;" +
            "弹窗广告支持PNG,JPEG,GIF.", required = true)
    @NotNull(message = "属性file不能为空")
    private File file;
    
    /**
     * 广告时长，除暂停广告外，都为必填参数。单位：秒
     */
    @ApiModelProperty(name = "size", value = "广告时长，除暂停广告外，都为必填参数。单位：秒", required = true)
    @NotNull(message = "属性size不能为空")
    private Integer size;
    
    /**
     * 分类id，关联多分类时，以英文逗号分隔，默认值为默认分类1
     */
    @ApiModelProperty(name = "categoryIds", value = "分类id，关联多分类时，以英文逗号分隔，默认值为默认分类1", required = false)
    @JSONField(name = "cataids")
    private String categoryIds;
    
    /**
     * 广告类型，片头1，暂停2，片尾3，弹窗4;默认为1:片头
     */
    @ApiModelProperty(name = "location", value = "广告类型，片头1，暂停2，片尾3，弹窗4;默认为1:片头", required = false)
    private Integer location;
    
    /**
     * 广告弹窗位置，广告类型为弹窗时必填，右下角1,右上角2,左下角3,左上角4
     */
    @ApiModelProperty(name = "popLocation", value = "广告弹窗位置，广告类型为弹窗时必填，右下角1,右上角2,左下角3,左上角4", required = false)
    private Integer popLocation;
    
    /**
     * 弹窗出现的时间,单位秒，广告类型为弹窗时必填
     */
    @ApiModelProperty(name = "popUpTime", value = "弹窗出现的时间,单位秒，广告类型为弹窗时必填", required = false)
    private Integer popUpTime;
    
    /**
     * 广告状态，已上线10,待上线1,已下线0;默认为10：已上线
     */
    @ApiModelProperty(name = "status", value = "广告状态，已上线10,待上线1,已下线0;默认为10：已上线", required = false)
    private Integer status;
    
    /**
     * 广告开始时间，格式为HH:mm:ss，默认为00:00:00
     */
    @ApiModelProperty(name = "upTime", value = "广告开始时间，格式为HH:mm:ss，默认为00:00:00", required = false)
    private String upTime;
    
    /**
     * 广告结束时间，格式为HH:mm:ss，默认为23:59:59
     */
    @ApiModelProperty(name = "offTime", value = "广告结束时间，格式为HH:mm:ss，默认为23:59:59", required = false)
    private String offTime;
    
    /**
     * 广告描述
     */
    @ApiModelProperty(name = "note", value = "广告描述", required = false)
    private String note;
    
    /**
     * Y表示开启跳过广告，N表示关闭跳过广告。仅片头广告有效，默认为N：关闭跳过广告
     */
    @ApiModelProperty(name = "skipAd", value = "Y表示开启跳过广告，N表示关闭跳过广告。仅片头广告有效，默认为N：关闭跳过广告", required = false)
    private String skipAd;
    
    /**
     * 多少秒后允许跳过，当skipAd为Y时，该字段为必要参数
     */
    @ApiModelProperty(name = "skipOffset", value = "多少秒后允许跳过，当skipAd为Y时，该字段为必要参数", required = false)
    private Integer skipOffset;
    
    /**
     * 跳过按钮标签，默认为跳过
     */
    @ApiModelProperty(name = "skipButtonLabel", value = "跳过按钮标签，默认为跳过", required = false)
    private String skipButtonLabel;
}
