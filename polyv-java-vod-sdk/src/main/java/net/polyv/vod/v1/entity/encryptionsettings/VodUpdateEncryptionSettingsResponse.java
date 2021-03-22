package net.polyv.vod.v1.entity.encryptionsettings;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置账号加密设置返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("设置账号加密设置返回实体")
public class VodUpdateEncryptionSettingsResponse {
    
    /**
     * 是否开启加密，1为开启，0为关闭
     */
    @ApiModelProperty(name = "encrypt", value = "是否开启加密，1为开启，0为关闭", required = false)
    private Integer encrypt;
    
    /**
     * 加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP授权，wxa_app为小程序授权
     */
    @ApiModelProperty(name = "hlsLevel", value = "加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP" +
            "授权，wxa_app为小程序授权", required = false)
    @JSONField(name = "hlslevel")
    private String hlsLevel;
}
