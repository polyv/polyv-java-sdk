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
 * 根据vid批量修改视频的授权播放开关状态请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("根据vid批量修改视频的授权播放开关状态请求实体")
public class VodUpdateVideoPlayStatusRequest extends VodCommonRequest {
    
    /**
     * 多个视频id，用英文逗号隔开(状态为半角)，例如 1b8be3,239c2e
     */
    @ApiModelProperty(name = "videoIds", value = "多个视频id，用英文逗号隔开(状态为半角)，例如 1b8be3,239c2e", required = true)
    @NotNull(message = "属性vids不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 是否开启，0：关闭，1：开启，默认为开启
     */
    @ApiModelProperty(name = "playAuth", value = "是否开启，0：关闭，1：开启，默认为开启", required = false)
    @JSONField(name = "playauth")
    private Integer playAuth;

}
