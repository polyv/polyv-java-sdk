package net.polyv.vod.v1.entity.play.list;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取单个播放列表请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取单个播放列表请求实体")
public class VodGetOnePlayListRequest extends VodCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    @JSONField(name = "userid")
    private String userId;
    
    /**
     * 播放列表ID
     */
    @ApiModelProperty(name = "id", value = "播放列表ID", required = true)
    @NotNull(message = "属性id不能为空")
    private String id;
}
