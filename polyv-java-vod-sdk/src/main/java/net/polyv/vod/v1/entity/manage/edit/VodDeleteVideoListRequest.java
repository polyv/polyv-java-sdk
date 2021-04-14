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
 * 批量删除视频请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量删除视频请求实体")
public class VodDeleteVideoListRequest extends VodCommonRequest {
    
    /**
     * 用户ID
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 视频ID，多个视频以英文逗号(,)隔开，一次最多提交500个
     */
    @ApiModelProperty(name = "videoIds", value = "视频ID，多个视频以英文逗号(,)隔开，一次最多提交500个", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 删除方式，1：删除到回收站，2：彻底删除，默认为：1
     */
    @ApiModelProperty(name = "deleteType", value = "删除方式，1：删除到回收站，2：彻底删除，默认为：1", required = false)
    private Integer deleteType;

}
