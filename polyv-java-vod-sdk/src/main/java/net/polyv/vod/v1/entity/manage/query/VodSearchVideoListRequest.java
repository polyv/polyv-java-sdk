package net.polyv.vod.v1.entity.manage.query;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 查找视频请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查找视频请求实体")
public class VodSearchVideoListRequest extends VodPageCommonRequest {
    
    /**
     * 分类目录的cataid
     */
    @ApiModelProperty(name = "categoryId", value = "分类目录的cataid", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 视频标题
     */
    @ApiModelProperty(name = "title", value = "视频标题", required = false)
    @JSONField(name = "keyword")
    private String title;
    
    /**
     * 视频标签
     */
    @ApiModelProperty(name = "tag", value = "视频标签", required = false)
    private String tag;
    
    /**
     * 视频是否加密，1:加密，0:非加密
     */
    @ApiModelProperty(name = "encrypt", value = "视频是否加密，1:加密，0:非加密，默认所有", required = false)
    private Integer encrypt;
    
    /**
     * 过滤的videoId，多个以英文逗号分隔
     */
    @ApiModelProperty(name = "videoIds", value = "过滤的videoId，多个以英文逗号分隔", required = false)
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 结果排序，取值（按创建时间或播放次数降序/升序排序）：creationTimeDesc、creationTimeAsc、playTimesDesc、playTimesAsc
     */
    @ApiModelProperty(name = "sort", value = "结果排序，取值（按创建时间或播放次数降序/升序排序）：creationTimeDesc、creationTimeAsc、playTimesDesc、playTimesAsc", required = false)
    private String sort;
    
}
