package net.polyv.vod.v1.entity;

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
//    /**
//     * POLYV用户userid，通过注册保利威官网获取，路径：官网->登录->点播（API接口）
//     */
//    @ApiModelProperty(hidden = true )
//    @NotNull(message = "属性userId不能为空")
//    private String userid;
    
    /**
     * 请求发送当时的时间戳（ms)，系统自动生成
     */
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性ptime不能为空")
    private String ptime;
    
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
