package net.polyv.live.v1.entity.account;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置录制回调通知url请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置录制回调通知url请求实体")
public class LiveAccountRecordCallbackRequest extends LiveCommonRequest {
    
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
    @ApiModel("回放生成回调返回实体")
    public static class RecordCallback{
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private Integer channelId;
    
        /**
         * 录制文件地址
         */
        @ApiModelProperty(name = "fileUrl", value = "录制文件地址", required = false)
        private String fileUrl;
    
        /**
         * 文件类型，m3u8或者mp4
         */
        @ApiModelProperty(name = "format", value = "文件类型，m3u8或者mp4", required = false)
        private String format;
    
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
         * 录制唯一的id
         */
        @ApiModelProperty(name = "fileId", value = "录制唯一的id", required = false)
        private String fileId;
    
        /**
         * 录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪
         */
        @ApiModelProperty(name = "origin", value = "录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪", required = false)
        private String origin;
    
        /**
         * （该字段只对开启云录制功能有用），值为 'Y'，表示该场直播录制同时存在云录制和自动录制，值为"N"，该场直播只有自动录制
         */
        @ApiModelProperty(name = "hasRtcRecord", value = "（该字段只对开启云录制功能有用），值为 'Y'，表示该场直播录制同时存在云录制和自动录制，值为\"N\"，该场直播只有自动录制", required = false)
        private String hasRtcRecord;
    
    }
    
}
