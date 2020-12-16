package net.polyv.live.v1.entity.account;

import java.util.Date;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置直播状态回调通知url请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置直播状态回调通知url请求实体")
public class LiveAccountStreamCallbackRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头
     */
    @ApiModelProperty(name = "url", value = "回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头", required = false)
    private String url;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("直播状态改变回调返回实体")
    public static class LiveStateChangeCallback{
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private Integer channelId;
        
        /**
         * 直播频道的状态：live正在直播，end直播结束
         */
        @ApiModelProperty(name = "status", value = "直播频道的状态：live正在直播，end直播结束", required = false)
        private String status;
        
        /**
         * 13位的时间戳
         */
        @ApiModelProperty(name = "timestamp", value = "13位的时间戳", required = false)
        private Long timestamp;
        
        /**
         * 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙
         */
        @ApiModelProperty(name = "sign", value = "校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙", required = false)
        private String sign;
        
        /**
         * 直播的场次ID
         */
        @ApiModelProperty(name = "sessionId", value = "直播的场次ID", required = false)
        private String sessionId;
        
        /**
         * 直播的开始时间,13位的时间戳
         */
        @ApiModelProperty(name = "startTime", value = "直播的开始时间,13位的时间戳", required = false)
        private Date startTime;
        
        /**
         * 直播的结束时间(当status=end的时候有值，status=live的时候为空值),13位的时间戳
         */
        @ApiModelProperty(name = "endTime", value = "直播的结束时间(当status=end的时候有值，status=live的时候为空值),13位的时间戳", required = false)
        private Date endTime;
        
    }
    
}
