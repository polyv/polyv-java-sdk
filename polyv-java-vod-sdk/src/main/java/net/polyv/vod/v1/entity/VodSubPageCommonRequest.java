package net.polyv.vod.v1.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;

/**
 * 点播分页公共请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class VodSubPageCommonRequest extends VodPageCommonRequest {
    
    /**
     * 子账号appId
     */
    @ApiModelProperty(name = "appId", value = "子账号appId", required = true)
    @NotNull(message = "属性appId不能为空")
    private String appId;
    
    /**
     * 子账号secretKey
     */
    @ApiModelProperty(name = "secretKey", value = "子账号secretKey", required = true)
    @NotNull(message = "属性secretKey不能为空")
    private String secretKey;
    
    /**
     * 请求发送当时的时间戳（ms)，系统自动生成
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性timestamp不能为空")
    private String timestamp;
    
}
