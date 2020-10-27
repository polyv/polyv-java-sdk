package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询账户分钟数请求实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@ApiModel("查询账户分钟数请求实体")
public class LiveAccountUserDurationsRequest extends LiveCommonRequest {
}
