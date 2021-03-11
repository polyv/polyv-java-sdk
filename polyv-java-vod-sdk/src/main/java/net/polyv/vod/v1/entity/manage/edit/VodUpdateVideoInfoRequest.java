package net.polyv.vod.v1.entity.manage.edit;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 编辑单个视频的信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("编辑单个视频的信息请求实体")
public class VodUpdateVideoInfoRequest extends VodCommonRequest {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性vid不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 视频描述
     */
    @ApiModelProperty(name = "desc", value = "视频描述", required = false)
    @JSONField(name = "describ")
    private String desc;
    
    /**
     * 视频首发外链地址
     */
    @ApiModelProperty(name = "publishUrl", value = "视频首发外链地址", required = false)
    private String publishUrl;
    
    /**
     * 视频标签
     */
    @ApiModelProperty(name = "tag", value = "视频标签", required = false)
    private String tag;
    
    /**
     * 视频标题
     */
    @ApiModelProperty(name = "title", value = "视频标题", required = false)
    private String title;
    
}
