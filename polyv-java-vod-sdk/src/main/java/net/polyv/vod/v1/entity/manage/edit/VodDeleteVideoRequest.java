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
 * 删除视频请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("删除视频请求实体")
public class VodDeleteVideoRequest extends VodCommonRequest {
    
    /**
     * 视频ID，多个视频以英文逗号(,)隔开，一次最多提交500个
     */
    @ApiModelProperty(name = "videoId", value = "视频ID，多个视频以英文逗号(,)隔开，一次最多提交500个", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 删除方式，1：删除到回收站，2：彻底删除，默认为：1
     */
    @ApiModelProperty(name = "deleteType", value = "删除方式，1：删除到回收站，2：彻底删除，默认为：1", required = false)
    private Integer deleteType;
    
}
