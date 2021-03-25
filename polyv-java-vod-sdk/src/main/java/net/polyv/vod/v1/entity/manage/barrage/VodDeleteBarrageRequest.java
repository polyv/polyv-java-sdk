package net.polyv.vod.v1.entity.manage.barrage;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 上传点播弹幕文件接口请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("上传点播弹幕文件接口请求实体")
public class VodDeleteBarrageRequest extends VodCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 多个弹幕信息ID，用逗号隔开(英文逗号分割 状态为半角)，例如 123,456
     */
    @ApiModelProperty(name = "videoId", value = "多个弹幕信息ID，用逗号隔开(英文逗号分割 状态为半角)，例如 123,456", required = true)
    @NotNull(message = "属性barrageIds不能为空")
    @JSONField(name = "danmuIds")
    private String barrageIds;
    
}
