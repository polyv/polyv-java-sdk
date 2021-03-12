package net.polyv.vod.v1.entity.subaccount;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 搜索视频请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("搜索视频请求实体")
public class VodSubAccountSearchVideoListRequest extends VodPageCommonRequest {
    /**
     * 视频分类ID
     */
    @ApiModelProperty(name = "categoryId", value = "视频分类ID", required = false)
    @JSONField(name = "cataId")
    private String categoryId;
    
    /**
     * 按标题搜索
     */
    @ApiModelProperty(name = "title", value = "按标题搜索", required = false)
    private String title;
    
    /**
     * 上传者
     */
    @ApiModelProperty(name = "uploader", value = "上传者", required = false)
    private String uploader;
    
    /**
     * 视频状态
     */
    @ApiModelProperty(name = "status", value = "视频状态码;60/61:已发布;10:等待编码;20:正在编码;50:等待审核;51:审核不通过;-1:已删除;", required = false)
    private String status;
    
    /**
     * 是否包含子分类, Y 包含, N 不包含
     */
    @ApiModelProperty(name = "containSubCate", value = "是否包含子分类, Y 包含, N 不包含", required = false)
    private String containSubCate;
    
    /**
     * 按创建时间范围查询，格式为yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "startTime", value = "按创建时间范围查询，起始时间，格式为yyyy-MM-dd HH:mm:ss", required = false)
    private Date startTime;
    
    /**
     * 结束时间戳，格式为yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "endTime", value = "结束时间，格式为yyyy-MM-dd HH:mm:ss", required = false)
    private Date endTime;
    
    /**
     * 排序creationTimeDesc或creationTimeAsc
     */
    @ApiModelProperty(name = "sort", value = "排序creationTimeDesc或creationTimeAsc", required = false)
    private String sort;
}
