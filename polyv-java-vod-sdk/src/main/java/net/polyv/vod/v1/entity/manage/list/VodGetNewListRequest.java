package net.polyv.vod.v1.entity.manage.list;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 获取最新视频/全部视频列表请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取最新视频/全部视频列表请求实体")
public class VodGetNewListRequest extends VodPageCommonRequest {
    
    /**
     * 视频所在分类树ID，默认为1，多个ID以英文逗号分割（状态为半角）例如 1,1615286323771
     */
    @ApiModelProperty(name = "categoryTree", value = "视频所在分类树ID，默认为1，多个ID以英文逗号分割（状态为半角）例如 1,1615286323771", required = false)
    @JSONField(name = "catatree")
    private String categoryTree;
    
    /**
     * 开始日期，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "startDate", value = "开始日期，格式：yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    
    /**
     * 结束日期，格式：yyyy-MM-dd
     */
    @ApiModelProperty(name = "endDate", value = "结束日期，格式：yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    
    /**
     * 开始时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "startTime", value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    
    /**
     * 结束时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "endTime", value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    
    /**
     * 1表示结果只包含已发布的视频，0或者不传为包含所有状态的视频，默认为 0：包含所有状态的视频
     */
    @ApiModelProperty(name = "published", value = "1表示结果只包含已发布的视频，0或者不传为包含所有状态的视频", required = false)
    private Integer published;
}
