package net.polyv.vod.v1.entity.encryptionsettings;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 设置账号加密设置请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置账号加密设置请求实体")
public class VodUpdateEncryptionSettingsRequest extends VodCommonRequest {
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    @JSONField(name = "userid")
    private String userId;
    
    /**
     * 是否开启加密，1为开启，0为关闭
     */
    @ApiModelProperty(name = "encrypt", value = "是否开启加密，1为开启，0为关闭", required = true)
    @NotNull(message = "属性encrypt不能为空")
    private Integer encrypt;
    
    /**
     * 加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP授权，wxa_app为小程序授权
     */
    @ApiModelProperty(name = "hlsLevel", value = "加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP" +
            "授权，wxa_app为小程序授权", required = true)
    @NotNull(message = "属性hlsLevel不能为空")
    @JSONField(name = "hlslevel")
    private String hlsLevel;
}
