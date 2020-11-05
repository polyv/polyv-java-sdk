package net.polyv.live.entity.channel.playback;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 异步合并直播录制文件请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("异步合并直播录制文件请求实体")
public class LiveMergeChannelVideoAsyncRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 要合并的录制视频文件ID，多个id用英文逗号,分隔，可通过调用查询视频库列表获取fileId
     */
    @ApiModelProperty(name = "fileIds", value = "要合并的录制视频文件ID，多个id用英文逗号,分隔，可通过调用查询视频库列表获取fileId", required = true)
    @NotNull(message = "属性fileIds不能为空")
    private String fileIds;
    
    /**
     * 合并后的视频的文件名
     */
    @ApiModelProperty(name = "fileName", value = "合并后的视频的文件名", required = false)
    private String fileName;
    
    /**
     * 合并成功或失败回调的url，可以带上自定义参数
     */
    @ApiModelProperty(name = "callbackUrl", value = "合并成功或失败回调的url，可以带上自定义参数", required = false)
    private String callbackUrl;
    
    /**
     * 传入Y，自动转存到对应点播分类下(直播回放-频道号-场次)
     */
    @ApiModelProperty(name = "autoConvert", value = "传入Y，自动转存到对应点播分类下(直播回放-频道号-场次)", required = false)
    private String autoConvert;
    
    /**
     * 传Y合并MP4文件，传N或者不传合并m3u8文件
     */
    @ApiModelProperty(name = "mergeMp4", value = "传Y合并MP4文件，传N或者不传合并m3u8文件", required = false)
    private String mergeMp4;

}
