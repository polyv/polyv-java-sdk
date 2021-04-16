package net.polyv.vod.v1.entity.manage.info;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取单个视频的首图请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取单个视频的首图请求实体")
public class VodGetVideoFirstImageRequest extends VodCommonRequest {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 是否为视频首图的缩略图，值为1：是；值为0：否。默认为0：非视频首图的缩略图
     */
    @ApiModelProperty(name = "thumbnail", value = "是否为视频首图的缩略图，值为1：是；值为0：否。默认为0：非视频首图的缩略图", required = false)
    @JSONField(name = "t")
    private Integer thumbnail;
    
}
