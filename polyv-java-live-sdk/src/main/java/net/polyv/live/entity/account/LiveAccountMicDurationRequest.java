package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 获取账号连麦分钟数使用量与剩余量请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("获取账号连麦分钟数使用量与剩余量请求实体")
public class LiveAccountMicDurationRequest extends LiveCommonRequest {
}
