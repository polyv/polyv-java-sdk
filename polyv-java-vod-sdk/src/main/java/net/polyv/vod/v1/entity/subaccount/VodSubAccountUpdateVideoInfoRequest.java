package net.polyv.vod.v1.entity.subaccount;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodSubCommonRequest;

/**
 * 修改视频信息请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("修改视频信息请求实体")
public class VodSubAccountUpdateVideoInfoRequest extends VodSubCommonRequest {
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 视频标题
     */
    @ApiModelProperty(name = "title", value = "视频标题", required = false)
    private String title;
    
    /**
     * 视频描述
     */
    @ApiModelProperty(name = "desc", value = "视频描述", required = false)
    @JSONField(name = "describ")
    private String desc;
    
    /**
     * 视频标签信息
     */
    @ApiModelProperty(name = "tag", value = "视频标签信息", required = false)
    private String tag;
    
    /**
     * 外链地址
     */
    @ApiModelProperty(name = "publishUrl", value = "外链地址", required = false)
    private String publishUrl;
}
