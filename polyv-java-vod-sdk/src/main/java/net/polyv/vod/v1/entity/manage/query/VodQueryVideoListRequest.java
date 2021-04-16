package net.polyv.vod.v1.entity.manage.query;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 根据授权播放开关状态查找视频请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("根据授权播放开关状态查找视频请求实体")
public class VodQueryVideoListRequest extends VodPageCommonRequest {
    
    /**
     * 授权播放开关状态，开启为1，未开启为0
     */
    @ApiModelProperty(name = "playAuth", value = "授权播放开关状态，开启为1，未开启为0", required = true)
    @NotNull(message = "属性playAuth不能为空")
    @JSONField(name = "playauth")
    private Integer playAuth;
    
}
