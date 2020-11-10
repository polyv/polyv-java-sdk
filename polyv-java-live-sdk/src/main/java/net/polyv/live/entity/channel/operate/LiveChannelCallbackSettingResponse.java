package net.polyv.live.entity.channel.operate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道回调设置接口返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道回调设置接口返回实体")
public class LiveChannelCallbackSettingResponse {
    
    /**
     * 录制生成回调URL
     */
    @ApiModelProperty(name = "recordCallbackUrl", value = "录制生成回调URL", required = false)
    private String recordCallbackUrl;
    
    /**
     * 录制视频转存成功回调URL
     */
    @ApiModelProperty(name = "playbackCallbackUrl", value = "录制视频转存成功回调URL", required = false)
    private String playbackCallbackUrl;
    
    /**
     * 流状态回调URL
     */
    @ApiModelProperty(name = "streamCallbackUrl", value = "流状态回调URL", required = false)
    private String streamCallbackUrl;
    
    /**
     * 直播内容审核回调URL
     */
    @ApiModelProperty(name = "liveScanCallbackUrl", value = "直播内容审核回调URL", required = false)
    private String liveScanCallbackUrl;
    
    /**
     * 录制回调的视频类型,多个视频类型用,分隔
     */
    @ApiModelProperty(name = "recordCallbackVideoType", value = "录制回调的视频类型,多个视频类型用,分隔", required = false)
    private String recordCallbackVideoType;
    
    /**
     * 回放缓存生成成功的回调URL
     */
    @ApiModelProperty(name = "playbackCacheCallbackUrl", value = "回放缓存生成成功的回调URL", required = false)
    private String playbackCacheCallbackUrl;
    
    /**
     * 课件重制成功回调URL
     */
    @ApiModelProperty(name = "pptRecordCallbackUrl", value = "课件重制成功回调URL", required = false)
    private String pptRecordCallbackUrl;
    
    /**
     * 是否应用全局设置开关，Y走用户回调设置，N走频道设置
     */
    @ApiModelProperty(name = "globalSettingEnabled", value = "是否应用全局设置开关，Y走用户回调设置，N走频道设置", required = false)
    private String globalSettingEnabled;
    
}
