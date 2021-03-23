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
    
    public final String FILE_NAME = "file";
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    @JSONField(name = "userid")
    private String userId;
    
    /**
     * 广告信息ID
     */
    @ApiModelProperty(name = "advertisingId", value = "广告信息ID", required = true)
    @NotNull(message = "属性advertisingId不能为空")
    @JSONField(name = "adid")
    private String advertisingId;
    
}
