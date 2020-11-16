package net.polyv.vod.v1.entity;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.entity.CommonReqeust;

/**
 * 直播公共请求实体
 * @author: thomas
 
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class VodCommonRequest extends CommonReqeust {
    /**
     * POLYV用户APP_ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性userid不能为空，如没有，请从官网获取")
    private String userid;
    /**
     * 请求发送当时的时间戳（ms)，系统自动生成
     */
    @ApiModelProperty(hidden = true )
    @NotNull(message = "属性ptime不能为空，当前时间的毫秒级时间戳（13位），3分钟内有效")
    @JSONField(name="ptime")
    private Long ptime;
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