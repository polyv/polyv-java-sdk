package net.polyv.live.entity.account;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询账号下的频道列表返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询账号下的频道列表返回实体")
public class LiveListAccountResponse {
    
    @ApiModelProperty(name = "channels", value = "频道号列表")
    private List<Integer> channels;
    
}
