package net.polyv.live.v1.entity.channel.playback;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 异步批量转存录制文件到点播请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("异步批量转存录制文件到点播请求实体")
public class LiveConvertChannelVideoListAsyncRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 要转存的录制视频文件id，多个id用英文逗号,分隔
     */
    @ApiModelProperty(name = "fileIds", value = "要转存的录制视频文件id，多个id用英文逗号,分隔", required = true)
    @NotNull(message = "属性fileIds不能为空")
    private String fileIds;
    
    /**
     * 转存后的文件名，目前暂不支持传多个文件名
     */
    @ApiModelProperty(name = "fileName", value = "转存后的文件名，目前暂不支持传多个文件名", required = false)
    private String fileName;
    
    /**
     * 转存到点播的目录id，默认为点播的根目录id
     */
    @ApiModelProperty(name = "cataId", value = "转存到点播的目录id，默认为点播的根目录id", required = false)
    private Long cataId;
    
    /**
     * 转存成功时候回调通知的url，通知的相关参数见附录
     */
    @ApiModelProperty(name = "callbackUrl", value = "转存成功时候回调通知的url，通知的相关参数见附录", required = false)
    private String callbackUrl;

}
