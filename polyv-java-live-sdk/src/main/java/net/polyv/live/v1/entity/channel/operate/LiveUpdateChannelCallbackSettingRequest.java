package net.polyv.live.v1.entity.channel.operate;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置频道回调设置请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置频道回调设置请求实体")
public class LiveUpdateChannelCallbackSettingRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 录制回调文件类型，可选值m3u8或mp4或m3u8,mp4
     */
    @ApiModelProperty(name = "recordCallbackVideoType", value = "录制回调文件类型，可选值m3u8或mp4或m3u8,mp4", required = false)
    private String recordCallbackVideoType;
    
    /**
     * 录制回调http(s)地址，需要url编码，如果要清空设置传入空串
     */
    @ApiModelProperty(name = "recordCallbackUrl", value = "录制回调http(s)地址，需要url编码，如果要清空设置传入空串", required = false)
    private String recordCallbackUrl;
    
    /**
     * 转存成功回调http(s)地址，需要url编码，如果要清空设置传入空串
     */
    @ApiModelProperty(name = "playbackCallbackUrl", value = "转存成功回调http(s)地址，需要url编码，如果要清空设置传入空串", required = false)
    private String playbackCallbackUrl;
    
    /**
     * 流状态回调http(s)地址，需要url编码，如果要清空设置传入空串
     */
    @ApiModelProperty(name = "streamCallbackUrl", value = "流状态回调http(s)地址，需要url编码，如果要清空设置传入空串", required = false)
    private String streamCallbackUrl;
    
    /**
     * 课件重制成功回调http(s)地址，需要url编码，如果要清空设置传入空串
     */
    @ApiModelProperty(name = "pptRecordCallbackUrl", value = "课件重制成功回调http(s)地址，需要url编码，如果要清空设置传入空串", required = false)
    private String pptRecordCallbackUrl;
    
    /**
     * 直播内容鉴别回调http(s)地址，需要url编码，如果要清空设置传入空串
     */
    @ApiModelProperty(name = "liveScanCallbackUrl", value = "直播内容鉴别回调http(s)地址，需要url编码，如果要清空设置传入空串", required = false)
    private String liveScanCallbackUrl;
    
    /**
     * 回放转存回调http(s)地址，需要url编码，如果要清空设置传入空串
     */
    @ApiModelProperty(name = "playbackCacheCallbackUrl", value = "回放转存回调http(s)地址，需要url编码，如果要清空设置传入空串", required = false)
    private String playbackCacheCallbackUrl;
    
}
