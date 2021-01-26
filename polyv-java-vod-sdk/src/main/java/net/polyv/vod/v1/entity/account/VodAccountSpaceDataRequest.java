package net.polyv.vod.v1.entity.account;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
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
     * POLYV用户userid，通过注册保利威官网获取，路径：官网->登录->点播（API接口）
     */
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性userid不能为空")
    private String userid;
    
    /**
     * 要查询的日期，格式 ：yyyy-MM-dd
     */
    @ApiModelProperty(name = "date", value = "要查询的日期，格式 ：yyyy-MM-dd", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date date;
}
