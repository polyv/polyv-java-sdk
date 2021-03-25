package net.polyv.vod.v1.entity.manage.barrage;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
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
}
