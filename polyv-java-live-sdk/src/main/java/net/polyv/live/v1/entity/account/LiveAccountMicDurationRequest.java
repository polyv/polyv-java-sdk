package net.polyv.live.v1.entity.account;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 获取账号连麦分钟数使用量与剩余量请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取账号连麦分钟数使用量与剩余量请求实体")
public class LiveAccountMicDurationRequest extends LiveCommonRequest {
}
