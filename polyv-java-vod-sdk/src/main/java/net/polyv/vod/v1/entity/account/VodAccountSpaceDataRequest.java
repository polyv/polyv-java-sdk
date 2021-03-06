package net.polyv.vod.v1.entity.account;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取用户空间及流量情况请求实体")
public class VodAccountSpaceDataRequest extends VodCommonRequest {
    /**
     * 要查询的日期，格式 ：yyyy-MM-dd
     */
    @ApiModelProperty(name = "date", value = "要查询的日期，格式 ：yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date date;
}
