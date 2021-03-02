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
 * 视频禁播与解禁请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("视频禁播与解禁请求实体")
public class VodSetVideoForbiddenRequest extends VodCommonRequest {
    
    /**
     * 视频vid，多个视频以英文逗号分隔
     */
    @ApiModelProperty(name = "videoIds", value = "视频vid，多个视频以英文逗号分隔", required = true)
    @NotNull(message = "属性vid不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 1：禁播，0：解禁
     */
    @ApiModelProperty(name = "forbidden", value = "1：禁播，0：解禁", required = true)
    @NotNull(message = "属性forbidden不能为空")
    private Integer forbidden;

}
