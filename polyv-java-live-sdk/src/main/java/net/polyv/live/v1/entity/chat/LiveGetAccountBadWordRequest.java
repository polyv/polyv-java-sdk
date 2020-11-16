package net.polyv.live.v1.entity.chat;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询账号严禁词列表请求实体
 * @author  thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询账号严禁词列表请求实体")
public class LiveGetAccountBadWordRequest extends LiveCommonRequest {
     
    
}
