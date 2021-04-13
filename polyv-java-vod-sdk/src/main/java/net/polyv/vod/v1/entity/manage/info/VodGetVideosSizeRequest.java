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
 * 批量获取视频的时长和大小请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量获取视频的时长和大小请求实体")
public class VodGetVideosSizeRequest extends VodCommonRequest {
    
    /**
     * 多个视频ID(英文逗号分割 状态为半角)，例如 1b8be3,239c2e
     */
    @ApiModelProperty(name = "videoIds", value = "多个视频ID(英文逗号分割 状态为半角)，例如 1b8be3,239c2e", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
}
