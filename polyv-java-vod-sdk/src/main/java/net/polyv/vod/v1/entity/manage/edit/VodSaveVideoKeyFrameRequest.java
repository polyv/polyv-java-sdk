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
 * 设置视频打点请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置视频打点请求实体")
public class VodSaveVideoKeyFrameRequest extends VodCommonRequest {
    
    /**
     * 视频的ID
     */
    @ApiModelProperty(name = "videoId", value = "视频的ID", required = true)
    @NotNull(message = "属性vid不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 打点描述，如果上传多个打点用英文逗号","隔开
     */
    @ApiModelProperty(name = "desc", value = "打点描述，如果上传多个打点用英文逗号隔开", required = true)
    @NotNull(message = "属性desc不能为空")
    private String desc;
    
    /**
     * 打点秒数【第seconds秒】，如果上传多个打点用英文逗号","隔开
     */
    @ApiModelProperty(name = "seconds", value = "打点秒数【第seconds秒】，如果上传多个打点用英文逗号隔开", required = true)
    @NotNull(message = "属性seconds不能为空")
    private String seconds;
    
    /**
     * 按钮设置开关，Y:开启;N:为关闭;默认关闭
     */
    @ApiModelProperty(name = "btnSettingSwitch", value = "按钮设置开关，Y:开启;N:为关闭;默认关闭", required = false)
    @JSONField(name = "btnsettingswitch")
    private String btnSettingSwitch;
    
    /**
     * 按钮描述，按钮开关开启时必填，关闭时btnDesc不设置
     */
    @ApiModelProperty(name = "btnDesc", value = "按钮描述，按钮开关开启时必填，关闭时btnDesc不设置", required = false)
    @JSONField(name = "btndesc")
    private String btnDesc;
    
    /**
     * 按钮跳转地址，按钮开关开启时必填，关闭时btnDesc不设置
     */
    @ApiModelProperty(name = "btnHref", value = "按钮跳转地址，按钮开关开启时必填，关闭时btnDesc不设置", required = false)
    @JSONField(name = "btnhref")
    private String btnHref;
    
}
