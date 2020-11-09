package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 获取直播用户账号信息请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("获取直播用户账号信息请求实体")
public class LiveAccountInfoRequest extends LiveCommonRequest {
}
