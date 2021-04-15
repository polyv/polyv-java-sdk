package net.polyv.vod.v1.entity.manage.courseware;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频关联的课件返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频关联的课件返回实体")
public class VodQueryCoursewareResponse {
    /**
     * 课件页码
     */
    @ApiModelProperty(name = "pageNo", value = "课件页码", required = false)
    private Integer pageNo;
    
    /**
     * 页面标题
     */
    @ApiModelProperty(name = "pageTitle", value = "页面标题", required = false)
    private String pageTitle;
    
    /**
     * 转码后的图片URL
     */
    @ApiModelProperty(name = "pageImage", value = "转码后的图片URL", required = false)
    private String pageImage;
    
    /**
     * 缩略图URL
     */
    @ApiModelProperty(name = "pageThumbnail", value = "缩略图URL", required = false)
    private String pageThumbnail;
    
    /**
     * 视频播放到第几秒时显示该页PPT，单位：秒
     */
    @ApiModelProperty(name = "showTime", value = "视频播放到第几秒时显示该页PPT，单位：秒", required = false)
    private Integer showTime;
}
