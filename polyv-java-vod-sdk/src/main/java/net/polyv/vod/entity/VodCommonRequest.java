package net.polyv.vod.entity;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.common.entity.CommonReqeust;

/**
 * 直播公共请求实体
 * @author: thomas
 
 **/
@Data
@Accessors(chain = true)
public class VodCommonRequest extends CommonReqeust {
    /**
     * POLYV用户APP_ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性appId不能为空")
    private String userid;
    /**
     * 请求发送当时的时间戳（ms)，系统自动生成
     */
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性timestamp不能为空")
    @JSONField(name="ptime")
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
