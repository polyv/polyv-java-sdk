package net.polyv.live.entity.chat;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询账号严禁词列表请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
//@NoArgsConstructor
@ApiModel("查询账号严禁词列表请求实体")
public class LiveGetAccountBadWordRequest extends LiveCommonRequest {
     
    
}
