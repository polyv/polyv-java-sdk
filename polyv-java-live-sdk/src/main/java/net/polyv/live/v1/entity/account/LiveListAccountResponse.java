package net.polyv.live.v1.entity.account;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询账号下的频道列表返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询账号下的频道列表返回实体")
public class LiveListAccountResponse {
    
    @ApiModelProperty(name = "channels", value = "频道号列表")
    private List<Integer> channels;
    
}
