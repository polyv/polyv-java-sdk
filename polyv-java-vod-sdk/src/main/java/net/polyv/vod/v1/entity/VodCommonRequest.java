package net.polyv.vod.v1.entity;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.entity.CommonReqeust;
import net.polyv.common.v1.validator.constraints.NotNull;

/**
 * 直播公共请求实体
 * @author: thomas
 
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class VodCommonRequest extends CommonReqeust {

    /**
     * 请求发送当时的时间戳（ms)，系统自动生成
     */
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性timestamp不能为空，当前时间的毫秒级时间戳（13位），3分钟内有效")
    @JSONField(name="ptime")
    private String timestamp;
    /**
     * 数据MD5签名，系统自动生成
     */
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性sign不能为空")
    private String sign;
    /**
     * 每次请求的业务流水号，便于客户端/服务器端排查问题
     */
    @ApiModelProperty(name="requestId",value  ="每次请求的业务流水号，便于客户端/服务器端排查问题",dataType = "String" ,required = true,example = "1234567" )
    @NotNull(message = "属性requestId不能为空")
    private String requestId;
    
    
}
