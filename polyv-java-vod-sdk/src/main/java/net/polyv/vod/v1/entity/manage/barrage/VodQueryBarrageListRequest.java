package net.polyv.vod.v1.entity.manage.barrage;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 分页查询用户下所有弹幕信息请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页查询用户下所有弹幕信息请求实体")
public class VodQueryBarrageListRequest extends VodPageCommonRequest {
    
    /**
     * 视频vid,传入则查具体视频弹幕，不传查用户所有弹幕
     */
    @ApiModelProperty(name = "videoId", value = "视频vid,传入则查具体视频弹幕，不传查用户所有弹幕", required = false)
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    @JSONField(name = "userid")
    private String userId;
}
