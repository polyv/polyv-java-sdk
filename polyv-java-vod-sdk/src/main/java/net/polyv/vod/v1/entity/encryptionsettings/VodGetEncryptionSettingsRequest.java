package net.polyv.vod.v1.entity.encryptionsettings;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取账号加密设置请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取账号加密设置请求实体")
public class VodGetEncryptionSettingsRequest extends VodCommonRequest {

}
