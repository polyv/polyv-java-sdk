package net.polyv.vod.v1.entity.advertising;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 删除视频广告请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("删除视频广告请求实体")
public class VodDeleteAdvertisingRequest extends VodCommonRequest {
    
    /**
     * 广告信息ID
     */
    @ApiModelProperty(name = "advertisingId", value = "广告信息ID", required = true)
    @NotNull(message = "属性advertisingId不能为空")
    @JSONField(name = "adid")
    private String advertisingId;
    
}
