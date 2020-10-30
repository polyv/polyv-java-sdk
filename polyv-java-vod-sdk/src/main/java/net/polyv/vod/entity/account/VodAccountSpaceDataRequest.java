package net.polyv.vod.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.vod.entity.VodCommonRequest;

/**
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获取用户空间及流量情况请求实体")
public class VodAccountSpaceDataRequest extends VodCommonRequest {
    /**
     * 要查询的日期，格式 ：yyyy-MM-dd
     */
    @ApiModelProperty(name = "date", value = "要查询的日期，格式 ：yyyy-MM-dd", required = false)
    private String date;
}
