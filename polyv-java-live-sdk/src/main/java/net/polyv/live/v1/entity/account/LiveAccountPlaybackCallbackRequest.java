package net.polyv.live.v1.entity.account;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置转存成功回调通知url请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置转存成功回调通知url请求实体")
public class LiveAccountPlaybackCallbackRequest extends LiveCommonRequest {
    
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
    @ApiModel("转存成功回调返回实体")
    public static class PlaybackCallBack {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 转存成功的视频ID
         */
        @ApiModelProperty(name = "vid", value = "转存成功的视频ID", required = false)
        private String vid;
        
        /**
         * 视频标题
         */
        @ApiModelProperty(name = "title", value = "视频标题", required = false)
        private String title;
        
        /**
         * 视频时长 格式为 hh:mm:ss
         */
        @ApiModelProperty(name = "duration", value = "视频时长 格式为 hh:mm:ss", required = false)
        @JSONField(format = "hh:mm:ss")
        private String duration;
        
        /**
         * 视频文件大小，单位为byte
         */
        @ApiModelProperty(name = "fileSize", value = "视频文件大小，单位为byte", required = false)
        private Long fileSize;
        
        /**
         * 13位的时间戳(签名使用)
         */
        @ApiModelProperty(name = "timestamp", value = "13位的时间戳(签名使用)", required = false)
        private Long timestamp;
        
        /**
         * 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的密匙
         */
        @ApiModelProperty(name = "sign", value = "校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的密匙",
                required = false)
        private String sign;
        
        /**
         * 录制的场次和时间对应的数组字符串，格式：["20190703145126,4,fdqbopvtnv","20190703145126,8,fdqbopvtnv"] ，其中："20190703145126,4,
         * fdqbopvtnv" 第一个字段是开始时间，第二个字段是直播的时长，第三个是对应的sessionId。
         */
        @ApiModelProperty(name = "sessionIds", value = "录制的场次和时间对应的数组字符串，格式：[\"20190703145126,4,fdqbopvtnv\"," +
                "\"20190703145126,8,fdqbopvtnv\"] ，其中：\"20190703145126,4,fdqbopvtnv\" " +
                "第一个字段是开始时间，第二个字段是直播的时长，第三个是对应的sessionId。", required = false)
        private String sessionIds;
        
        /**
         * 转存对应的录制文件id
         */
        @ApiModelProperty(name = "fileId", value = "转存对应的录制文件id", required = false)
        private String fileId;
        
        /**
         * 转存回放唯一的id
         */
        @ApiModelProperty(name = "videoId", value = "转存回放唯一的id", required = false)
        private String videoId;
        
        /**
         * 转存的录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪
         */
        @ApiModelProperty(name = "origin", value = "转存的录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪", required = false)
        private String origin;
        
        /**
         * 回放对应的单个场次id
         */
        @ApiModelProperty(name = "sessionId", value = "回放对应的单个场次id", required = false)
        private String sessionId;
        
        /**
         * POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）
         */
        @ApiModelProperty(name = "userId", value = "POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）", required = false)
        private String userId;
        
        /**
         * 转存成功返回success
         */
        @ApiModelProperty(name = "status", value = "转存成功返回success", required = false)
        private String status;
        
    }
    
}
